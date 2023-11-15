package view;

import static constant.Main.SUA;
import static constant.Main.THEM;
import static constant.Main.XOA;
import static constant.Main.XR;
import static view.DefaultLayout.createCustomTable;
import static view.DefaultLayout.getInput;
import static view.DefaultLayout.getInputCalender;
import static view.DefaultLayout.getInputSelect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.HopDongDAO;
import dao.PhongTroDAO;
import dao.SinhVienDAO;
import entity.HopDong;
import entity.PhongTro;
import entity.SinhVien;

public class HopDongUI implements MouseListener {
	private JPanel wrapper;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField ma, maSV, maPhong;
	private JDateChooser ngayKy, ngayHet;
	private SinhVienDAO svDAO;
	private PhongTroDAO phongTroDAO;
	private HopDongDAO hdDAO;
	private List<HopDong> dshd;

	public HopDongUI() {
		wrapper = new JPanel();
		svDAO = new SinhVienDAO();
		phongTroDAO = new PhongTroDAO();
		hdDAO = new HopDongDAO();
	}

	private JPanel getHeader() {
		JPanel container = new JPanel();
		container.setBackground(new Color(176, 226, 255));
		container.setBorder(new EmptyBorder(15, 0, 15, 0));
		JLabel title = new JLabel("QUẢN LÝ HỢP ĐỒNG");
		title.setFont(new Font("Arial", Font.BOLD, 28));
		container.add(title);
		return container;
	}

	private JPanel getButtons() {
		JPanel container = new JPanel();
		container.setBackground(new Color(176, 226, 255));
		container.setBorder(new EmptyBorder(20, 0, 20, 0));
		JPanel btnsContainer = new JPanel();
		btnsContainer.setLayout(new GridLayout(1, 4));

		btnsContainer.add(createBtn(THEM, "src//image//add.gif"));
		btnsContainer.add(createBtn(XOA, "src//image//delete.gif"));
		btnsContainer.add(createBtn(SUA, "src//image//edit.gif"));
		btnsContainer.add(createBtn(XR, "src//image//refresh.gif"));

		container.add(btnsContainer);
		return container;
	}

	@SuppressWarnings("serial")
	private JPanel getBody() {
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBackground(Color.WHITE);

		JPanel tableContainer = new JPanel();

		tableContainer.setLayout(new BorderLayout());

		String[] cols = { "Mã hợp đồng", "Mã sinh viên", "Mã phòng", "Ngày ký hợp đồng", "Ngày hết hợp đồng" };

		tableModel = new DefaultTableModel(cols, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = createCustomTable(tableModel);
		table.addMouseListener(this);

		for (HopDong hopDong : dshd) {
			tableModel.addRow(hopDong.getObjects());
		}

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(Color.WHITE);
		tableContainer.add(scrollPane);

		container.add(getHeader());
		container.add(Box.createVerticalStrut(15));
		container.add(tableContainer);
		container.add(Box.createVerticalStrut(15));
		container.add(getButtons());
		return container;
	}

	private JPanel getForm() {
		JPanel wrapper = new JPanel();
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBorder(new EmptyBorder(30, 30, 400, 30));
		wrapper.setBackground(new Color(176, 226, 255));
		container.setBackground(new Color(176, 226, 255));
		container.setBorder(new EmptyBorder(30, 30, 0, 30));
		wrapper.setBackground(new Color(176, 226, 255));
		container.setBackground(new Color(176, 226, 255));
		container.add(getInput("Mã hợp đồng", ma = new JTextField()));
		container.add(Box.createVerticalStrut(15));
		container.add(getInputSelect("Mã sinh viên", maSV = new JTextField()));
		container.add(Box.createVerticalStrut(15));
		container.add(getInputSelect("Mã phòng", maPhong = new JTextField()));
		container.add(Box.createVerticalStrut(15));
		container.add(getInputCalender("Ngày ký hợp đồng", ngayKy = new JDateChooser()));
		container.add(Box.createVerticalStrut(15));
		container.add(getInputCalender("Ngày hết hợp đồng", ngayHet = new JDateChooser()));
		wrapper.add(container);
		return wrapper;
	}

	public JPanel getLayout() {
		dshd = hdDAO.findAll();
		wrapper.setBackground(Color.WHITE);
		wrapper.setBorder(new EmptyBorder(0, 0, 15, 0));
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
		wrapper.add(Box.createHorizontalStrut(15));
		wrapper.add(getForm());
		wrapper.add(Box.createHorizontalStrut(15));
		wrapper.add(getBody());
		return wrapper;
	}

	private JPanel createBtn(String label, String path) {
		ImageIcon icon = new ImageIcon(path);
		JPanel btnContainer = new JPanel();
		btnContainer.setBackground(new Color(176, 226, 255));
		btnContainer.setBorder(new EmptyBorder(0, 40, 0, 40));
		btnContainer.setLayout(new BorderLayout());
		JButton btn = new JButton(label);
		btn.setBackground(new Color(162, 181, 205));
		btn.setIcon(icon);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setOpaque(true);
		btn.setFont(new Font("Arial", Font.BOLD, 18));
		btn.addActionListener(e -> {
			if (label.equals(THEM)) {
				them();
			} else if (label.equals(XOA)) {
				xoa();
			} else if (label.equals(SUA)) {
				chinhSua();
			} else if (label.equals(XR)) {
				lamMoi();
			}
		});
		btnContainer.add(btn, BorderLayout.CENTER);
		btn.setPreferredSize(new Dimension(btn.getPreferredSize().width + 30, 45));
		return btnContainer;
	}

	private void them() {
		if (isValid()) {
			PhongTro phongTro = phongTroDAO.findOneById(maPhong.getText());
			SinhVien sinhVien = svDAO.findBy(maSV.getText(), null, null, null, null).get(0);
			HopDong hopDong = new HopDong(ma.getText(), sinhVien, phongTro, ngayKy.getDate(), ngayHet.getDate());
			if (hdDAO.save(hopDong, "insert")) {
				tableModel.addRow(hopDong.getObjects());
				JOptionPane.showMessageDialog(wrapper, "Thêm hợp đồng thành công");
				lamMoi();
			} else {
				JOptionPane.showMessageDialog(wrapper, "Mã hợp đồng không được trùngF");
			}
		}
	}

	private void xoa() {
		int row = table.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(wrapper, "Vui lòng chọn dòng cần xóa");
		} else {
			if (JOptionPane.showConfirmDialog(wrapper, "Bạn có chắc xóa dòng này không", "Cảnh báo",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				boolean isSuccess = hdDAO.deleteOneById((String) table.getValueAt(row, 0),
						(String) table.getValueAt(row, 2));
				if (isSuccess) {
					tableModel.removeRow(row);
					JOptionPane.showMessageDialog(wrapper, "Xóa hợp đồng thành công");
					lamMoi();
				}
			}
		}
	}

	private void chinhSua() {
		int row = table.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(wrapper, "Vui lòng chọn dòng cần sửa");
		} else {
			if (JOptionPane.showConfirmDialog(wrapper, "Bạn có chắc sửa dòng này không", "Cảnh báo",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				String maSv = table.getValueAt(row, 1) + "";
				String maPhong = table.getValueAt(row, 2) + "";
				SinhVien sinhVien = svDAO.findBy(maSv, null, null, null, null).get(0);
				PhongTro phongTro = phongTroDAO.findOneById(maPhong);
				HopDong hopDong = new HopDong(ma.getText(), sinhVien, phongTro, ngayKy.getDate(), ngayHet.getDate());
				hdDAO.save(hopDong, "update");
				if (ma.getText().equals((String) table.getValueAt(row, 0))) {
					table.setValueAt(hopDong.getMa(), row, 0);
					table.setValueAt(hopDong.getSinhVien().getMaSinhVien(), row, 1);
					table.setValueAt(hopDong.getPhongTro().getMaPhong(), row, 2);
					table.setValueAt(new SimpleDateFormat("dd-MM-yyyy").format(hopDong.getNgayKiHopDong()), row, 3);
					table.setValueAt(new SimpleDateFormat("dd-MM-yyyy").format(hopDong.getNgayHetHopDong()), row, 4);
					JOptionPane.showMessageDialog(wrapper, "Sửa hợp đồng thành công");
					lamMoi();
				} else {
					JOptionPane.showMessageDialog(wrapper, "Không được sửa mã hợp đồng!");
				}
			}
		}
	}

	private void lamMoi() {
		ma.setText("");
		maSV.setText("");
		maPhong.setText("");
		ngayHet.setDate(null);
		ngayKy.setDate(null);
		table.clearSelection();
		ma.requestFocus();
	}

	private boolean isValid() {
		if (ma.getText().equals("")) {
			JOptionPane.showMessageDialog(wrapper, "Mã hợp đồng không được rỗng");
			return false;
		} else if (!ma.getText().matches("HD[0-9]{3}")) {
			JOptionPane.showMessageDialog(wrapper, "Mã hợp đồng có dạng là HD và đi theo sau là 3 số");
			return false;
		}
		Object[][] objects = { { ngayKy.getDate(), "Ngày ký hợp đồng" }, { ngayHet.getDate(), "Ngày hết hợp đồng" } };
		for (Object[] object : objects) {
			if (object[0] == null) {
				JOptionPane.showMessageDialog(wrapper, object[1] + " không được rỗng");
				return false;
			}
		}
		if (ngayHet.getDate().before(ngayKy.getDate())) {
			JOptionPane.showMessageDialog(wrapper, "Ngày hết phải sau ngày ký hợp đồng");
			return false;
		}
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		ma.setText(table.getValueAt(row, 0) + "");
		try {
			ngayKy.setDate(new SimpleDateFormat("dd-MM-yyyy").parse((String) table.getValueAt(row, 3)));
			ngayHet.setDate(new SimpleDateFormat("dd-MM-yyyy").parse((String) table.getValueAt(row, 4)));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
