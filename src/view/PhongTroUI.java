package view;

import static constant.Main.SUA;
import static constant.Main.THEM;
import static constant.Main.XOA;
import static constant.Main.XR;
import static view.DefaultLayout.createCustomTable;
import static view.DefaultLayout.getInput;
import static view.DefaultLayout.getInputComboBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import dao.ChuTroDAO;
import dao.PhongTroDAO;
import entity.ChuPhong;
import entity.PhongTro;

public class PhongTroUI implements MouseListener {
	private JPanel wrapper;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField ma, gia, diaChi;
	private JComboBox<String> maChuPhong, tinhTrang;
	private PhongTroDAO phongTroDAO;
	private ChuTroDAO chuTroDAO;
	private List<PhongTro> dsPhongTro;
	private List<ChuPhong> dsChuPhong;

	public PhongTroUI() {
		wrapper = new JPanel();
		phongTroDAO = new PhongTroDAO();
		chuTroDAO = new ChuTroDAO();
	}

	private JPanel getHeader() {
		JPanel container = new JPanel();
		container.setBackground(new Color(176, 226, 255));
		container.setBorder(new EmptyBorder(15, 0, 15, 0));
		JLabel title = new JLabel("QUẢN LÝ PHÒNG TRỌ");
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

	private JPanel getBody() {
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBackground(Color.WHITE);

		JPanel tableContainer = new JPanel();

		tableContainer.setLayout(new BorderLayout());

		String[] cols = { "Mã phòng trọ", "Địa Chỉ", "Giá", "Mã chủ phòng", "Tình trạng phòng" };

		tableModel = new DefaultTableModel(cols, 0);
		table = createCustomTable(tableModel);

		table.addMouseListener(this);

		for (PhongTro phongTro : dsPhongTro) {
			tableModel.addRow(phongTro.getObject());
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
		container.add(getInput("Mã phòng trọ", ma = new JTextField()));
		container.add(getInput("Giá", gia = new JTextField()));
		container.add(getInput("Địa chỉ", diaChi = new JTextField()));
		container.add(getInputComboBox("Mã Chủ phòng", maChuPhong = new JComboBox<String>(createOptionChutro())));
		container.add(getInputComboBox("Tình trạng phòng",
				tinhTrang = new JComboBox<String>(new String[] { "Disable", "Enable" })));
		wrapper.add(container);
		return wrapper;
	}

	public JPanel getLayout() {
		dsPhongTro = phongTroDAO.findAll();
		dsChuPhong = chuTroDAO.findAll();
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
				themPhong();
			} else if (label.equals(XOA)) {
				xoaPhong();
				;
			} else if (label.equals(SUA)) {
				chinhSuaPhong();
			}
		});

		btnContainer.add(btn, BorderLayout.CENTER);
		btn.setPreferredSize(new Dimension(btn.getPreferredSize().width + 30, 45));
		return btnContainer;
	}

	private String[] createOptionChutro() {
		String[] options = new String[dsChuPhong.size()];
		for (int i = 0; i < options.length; i++) {
			options[i] = dsChuPhong.get(i).getMaChuPhong();
		}

		return options;
	}

	private void themPhong() {
		ChuPhong chuPhong = chuTroDAO.findOneById((String) maChuPhong.getSelectedItem());
		int tinhTrang = ((String) this.tinhTrang.getSelectedItem()).equals("Disable") ? 1 : 0;
		PhongTro phongTro = new PhongTro(ma.getText(), diaChi.getText(), Float.parseFloat(gia.getText()), chuPhong,
				tinhTrang);
		if (phongTroDAO.save(phongTro, "insert")) {
			tableModel.addRow(phongTro.getObject());
			JOptionPane.showMessageDialog(wrapper, "Thêm phòng thành công");
			lamMoi();
		} else {
			JOptionPane.showMessageDialog(wrapper, "Mã phòng không được trùng");
		}
	}

	private void lamMoi() {
		ma.setText("");
		gia.setText("");
		diaChi.setText("");
		maChuPhong.setSelectedIndex(0);
		tinhTrang.setSelectedIndex(0);
		ma.requestFocus();
		table.clearSelection();
	}

	private void xoaPhong() {
		int row = table.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(wrapper, "Vui lòng chọn dòng cần xóa");
		} else {
			if (JOptionPane.showConfirmDialog(wrapper, "Bạn có chắc xóa dòng này không", "Cảnh báo",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				boolean isSuccess = phongTroDAO.deleteOneById((String) table.getValueAt(row, 0));
				if (isSuccess) {
					tableModel.removeRow(row);
					JOptionPane.showMessageDialog(wrapper, "Xóa phòng thành công");
					lamMoi();
				}
			}
		}
	}

	private void chinhSuaPhong() {
		int row = table.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(wrapper, "Vui lòng chọn dòng cần sửa");
		} else {
			if (JOptionPane.showConfirmDialog(wrapper, "Bạn có chắc sửa dòng này không", "Cảnh báo",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				ChuPhong chuPhong = chuTroDAO.findOneById((String) maChuPhong.getSelectedItem());
				int tinhTrang = ((String) this.tinhTrang.getSelectedItem()).equals("Disable") ? 1 : 0;
				PhongTro phongTro = new PhongTro(ma.getText(), diaChi.getText(), Float.parseFloat(gia.getText()),
						chuPhong, tinhTrang);
				boolean isSuccess = phongTroDAO.save(phongTro, "update");
				if (isSuccess) {
					table.setValueAt(phongTro.getMaPhong(), row, 0);
					table.setValueAt(phongTro.getDiaChi(), row, 1);
					table.setValueAt(phongTro.getGia(), row, 2);
					table.setValueAt(phongTro.getTinhTrang() == 1 ? "Disable" : "Enable", row, 4);
					JOptionPane.showMessageDialog(wrapper, "Sửa phòng thành công");
					lamMoi();
				} else {
					JOptionPane.showMessageDialog(wrapper, "Không được sửa mã phòng!");
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		ma.setText(table.getValueAt(row, 0) + "");
		gia.setText(table.getValueAt(row, 1) + "");
		diaChi.setText(table.getValueAt(row, 2) + "");
		maChuPhong.setSelectedItem(table.getValueAt(row, 3));
		tinhTrang.setSelectedItem(table.getValueAt(row, 4));
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
