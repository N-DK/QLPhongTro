package view;

import static constant.Main.SUA;
import static constant.Main.THEM;
import static constant.Main.XOA;
import static constant.Main.XR;
import static view.DefaultLayout.createCustomTable;
import static view.DefaultLayout.getInput;
import static view.DefaultLayout.getInputCalender;
import static view.DefaultLayout.getInputComboBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.LopHocDAO;
import dao.SinhVienDAO;
import entity.LopHoc;
import entity.SinhVien;

public class SinhVienUI implements MouseListener {
	private JPanel wrapper;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField ma, ho, ten, queQuan, sdt;
	private JComboBox<String> maLop, gioiTinh;
	private JDateChooser ngaySinh;
	private SinhVienDAO svDAO;
	private LopHocDAO lopDAO;
	private List<SinhVien> dssv;
	private List<LopHoc> dslh;

	public SinhVienUI() {
		wrapper = new JPanel();
		svDAO = new SinhVienDAO();
		lopDAO = new LopHocDAO();
	}

	private JPanel getHeader() {
		JPanel container = new JPanel();
		container.setBackground(new Color(176, 226, 255));
		container.setBorder(new EmptyBorder(15, 0, 15, 0));
		JLabel title = new JLabel("QUẢN LÝ SINH VIÊN");
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

		String[] cols = { "Mã sinh viên", "Họ", "Tên", "Mã lớp", "Quê quán", "Giới tính", "Ngày sinh", "SĐT" };

		tableModel = new DefaultTableModel(cols, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = createCustomTable(tableModel);
		table.addMouseListener(this);

		for (SinhVien sinhVien : dssv) {
			tableModel.addRow(sinhVien.getObjects());
		}

		JScrollPane scrollPane = new JScrollPane(table);

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
		container.setBorder(new EmptyBorder(50, 30, 400, 30));
		wrapper.setBackground(new Color(176, 226, 255));
		container.setBackground(new Color(176, 226, 255));
		container.setBorder(new EmptyBorder(50, 30, 0, 30));
		wrapper.setBackground(new Color(176, 226, 255));
		container.setBackground(new Color(176, 226, 255));
		container.add(getInput("Mã sinh viên", ma = new JTextField()));
		container.add(getInput("Họ", ho = new JTextField()));
		container.add(getInput("Tên", ten = new JTextField()));
		container.add(getInputComboBox("Mã lớp", maLop = new JComboBox<String>(createOptionsLopHoc())));
		container.add(getInput("Quê quán", queQuan = new JTextField()));
		container.add(getInputComboBox("Giới tính", gioiTinh = new JComboBox<String>(new String[] { "Nam", "Nữ" })));
		container.add(getInput("SĐT", sdt = new JTextField()));
		container.add(getInputCalender("Ngày sinh", ngaySinh = new JDateChooser()));
		wrapper.add(container);
		return wrapper;
	}

	public JPanel getLayout() {
		dssv = svDAO.findAll();
		dslh = lopDAO.findAll();
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

	private String[] createOptionsLopHoc() {
		String[] options = new String[dslh.size()];
		for (int i = 0; i < options.length; i++) {
			options[i] = dslh.get(i).getMa();
		}
		return options;
	}

	private void them() {
		if (isValid()) {
			LopHoc lop = lopDAO.findOneById((String) maLop.getSelectedItem());
			int gioiTinh = ((String) this.gioiTinh.getSelectedItem()).equals("Nam") ? 1 : 0;
			SinhVien sinhVien = new SinhVien(ho.getText(), ten.getText(), gioiTinh, ngaySinh.getDate(), sdt.getText(),
					ma.getText(), queQuan.getText(), lop);
			if (svDAO.save(sinhVien, "insert")) {
				tableModel.addRow(sinhVien.getObjects());
				JOptionPane.showMessageDialog(wrapper, "Thêm sinh viên thành công");
				lamMoi();
			} else {
				JOptionPane.showMessageDialog(wrapper, "Mã sinh viên không được trùng");
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
				boolean isSuccess = svDAO.deleteOneById((String) table.getValueAt(row, 0));
				if (isSuccess) {
					tableModel.removeRow(row);
					JOptionPane.showMessageDialog(wrapper, "Xóa sinh viên thành công");
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
				LopHoc lop = lopDAO.findOneById((String) maLop.getSelectedItem());
				int gioiTinh = ((String) this.gioiTinh.getSelectedItem()).equals("Nam") ? 1 : 0;
				SinhVien sinhVien = new SinhVien(ho.getText(), ten.getText(), gioiTinh, ngaySinh.getDate(),
						sdt.getText(), ma.getText(), queQuan.getText(), lop);
				boolean isSuccess = svDAO.save(sinhVien, "update");
				if (isSuccess) {
					table.setValueAt(sinhVien.getMaSinhVien(), row, 0);
					table.setValueAt(sinhVien.getHo(), row, 1);
					table.setValueAt(sinhVien.getTen(), row, 2);
					table.setValueAt(sinhVien.getLopHoc().getMa(), row, 3);
					table.setValueAt(sinhVien.getQueQuan(), row, 4);
					table.setValueAt(sinhVien.getGioiTinh() == 1 ? "Nam" : "Nữ", row, 5);
					table.setValueAt(new SimpleDateFormat("dd-MM-yyyy").format(sinhVien.getNgaySinh()), row, 6);
					table.setValueAt(sinhVien.getSdt(), row, 7);
					JOptionPane.showMessageDialog(wrapper, "Sửa sinh viên thành công");
					lamMoi();
				} else {
					JOptionPane.showMessageDialog(wrapper, "Không được sửa mã sinh viên!");
				}
			}
		}
	}

	private boolean isValid() {
		if (ma.getText().isEmpty()) {
			JOptionPane.showMessageDialog(wrapper, "Mã sinh viên không được rỗng");
			return false;
		} else if (!ma.getText().matches("[0-9]{8}")) {
			JOptionPane.showMessageDialog(wrapper, "Mã sinh viên phải đủ 8 số");
			return false;
		}
		if (ho.getText().isEmpty()) {
			JOptionPane.showMessageDialog(wrapper, "Họ sinh viên không được rỗng");
			return false;
		}
		if (ten.getText().isEmpty()) {
			JOptionPane.showMessageDialog(wrapper, "Tên sinh viên không được rỗng");
			return false;
		}
		if (queQuan.getText().isEmpty()) {
			JOptionPane.showMessageDialog(wrapper, "Quê quán sinh viên không được rỗng");
			return false;
		}
		if (sdt.getText().isEmpty()) {
			JOptionPane.showMessageDialog(wrapper, "Số điện thoại sinh viên không được rỗng");
			return false;
		} else if (!sdt.getText().matches("[0-9]{10,11}")) {
			JOptionPane.showMessageDialog(wrapper, "Số điện thoại không được chứa chữ và không quá 11 số");
			return false;
		}
		if (ngaySinh.getDate() == null) {
			JOptionPane.showMessageDialog(wrapper, "Ngày sinh sinh viên không được rỗng");
			return false;
		}
		return true;
	}

	private void lamMoi() {
		ma.setText("");
		ho.setText("");
		ten.setText("");
		maLop.setSelectedItem(0);
		queQuan.setText("");
		gioiTinh.setSelectedIndex(0);
		sdt.setText("");
		ngaySinh.setDate(null);
		ma.requestFocus();
		table.clearSelection();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		ma.setText(table.getValueAt(row, 0) + "");
		ho.setText(table.getValueAt(row, 1) + "");
		ten.setText(table.getValueAt(row, 2) + "");
		maLop.setSelectedItem(table.getValueAt(row, 3));
		queQuan.setText(table.getValueAt(row, 4) + "");
		gioiTinh.setSelectedItem(table.getValueAt(row, 5));
		try {
			ngaySinh.setDate(new SimpleDateFormat("dd-MM-yyyy").parse((String) table.getValueAt(row, 6)));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sdt.setText(table.getValueAt(row, 7) + "");
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
