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

import dao.ChuyenNganhDAO;
import dao.KhoaDAO;
import entity.ChuyenNganh;
import entity.Khoa;

public class ChuyenNganhUI implements MouseListener {
	private JPanel wrapper;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField ma, ten;
	private JComboBox<String> maKhoa;
	private ChuyenNganhDAO cnDAO;
	private KhoaDAO khoaDAO;
	private List<ChuyenNganh> dscn;
	private List<Khoa> dsKhoa;

	public ChuyenNganhUI() {
		wrapper = new JPanel();
		cnDAO = new ChuyenNganhDAO();
		khoaDAO = new KhoaDAO();
	}

	private JPanel getHeader() {
		JPanel container = new JPanel();
		container.setBackground(new Color(181, 181, 181));
		container.setBorder(new EmptyBorder(15, 0, 15, 0));
		JLabel title = new JLabel("CHUYÊN NGÀNH");
		title.setFont(new Font("Arial", Font.BOLD, 28));
		container.add(title);
		return container;
	}

	private JPanel getButtons() {
		JPanel container = new JPanel();
		container.setBackground(new Color(181, 181, 181));
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

		JPanel tableContainer = new JPanel();

		tableContainer.setLayout(new BorderLayout());

		String[] cols = { "Mã chuyên ngành", "Tên chuyên ngành", "Mã khoa" };

		tableModel = new DefaultTableModel(cols, 0);
		table = createCustomTable(tableModel);

		for (ChuyenNganh chuyenNganh : dscn) {
			tableModel.addRow(chuyenNganh.getObjects());
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
		container.setBorder(new EmptyBorder(30, 30, 0, 30));
		wrapper.setBackground(new Color(181, 181, 181));
		container.setBackground(new Color(181, 181, 181));
		container.add(getInput("Mã chuyên ngành", ma = new JTextField()));
		container.add(getInput("Tên chuyên ngành", ten = new JTextField()));
		container.add(getInputComboBox("Mã khoa", maKhoa = new JComboBox<String>(createOptionKhoa())));
		wrapper.add(container);
		return wrapper;
	}

	public JPanel getLayout() {
		dscn = cnDAO.findAll();
		dsKhoa = khoaDAO.findAll();
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
		btnContainer.setBackground(new Color(181, 181, 181));
		btnContainer.setBorder(new EmptyBorder(0, 40, 0, 40));
		btnContainer.setLayout(new BorderLayout());
		JButton btn = new JButton(label);
		btn.setBackground(Color.GRAY);
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

	private String[] createOptionKhoa() {
		String[] options = new String[dsKhoa.size()];
		for (int i = 0; i < options.length; i++) {
			options[i] = dsKhoa.get(i).getMa();
		}

		return options;
	}

	private void them() {
		Khoa khoa = khoaDAO.findOneById((String) maKhoa.getSelectedItem());
		ChuyenNganh chuyenNganh = new ChuyenNganh(ma.getText(), ten.getText(), khoa);
		if (cnDAO.save(chuyenNganh, "insert")) {
			tableModel.addRow(chuyenNganh.getObjects());
			JOptionPane.showMessageDialog(wrapper, "Thêm chuyên ngành thành công");
			lamMoi();
		} else {
			JOptionPane.showMessageDialog(wrapper, "Mã chuyên ngành không được trùng");
		}
	}

	private void xoa() {
		int row = table.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(wrapper, "Vui lòng chọn dòng cần xóa");
		} else {
			if (JOptionPane.showConfirmDialog(wrapper, "Bạn có chắc xóa dòng này không", "Cảnh báo",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				boolean isSuccess = cnDAO.deleteOneById((String) table.getValueAt(row, 0));
				if (isSuccess) {
					tableModel.removeRow(row);
					JOptionPane.showMessageDialog(wrapper, "Xóa chuyên ngành thành công");
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
				Khoa khoa = khoaDAO.findOneById((String) maKhoa.getSelectedItem());
				ChuyenNganh chuyenNganh = new ChuyenNganh(ma.getText(), ten.getText(), khoa);
				boolean isSuccess = cnDAO.save(chuyenNganh, "update");
				if (isSuccess) {
					table.setValueAt(chuyenNganh.getMa(), row, 0);
					table.setValueAt(chuyenNganh.getTen(), row, 1);
					table.setValueAt(chuyenNganh.getKhoa().getMa(), row, 2);
					JOptionPane.showMessageDialog(wrapper, "Sửa chuyên ngành thành công");
					lamMoi();
				} else {
					JOptionPane.showMessageDialog(wrapper, "Không được sửa mã chuyên ngành!");
				}
			}
		}
	}

	private void lamMoi() {
		ma.setText("");
		ten.setText("");
		maKhoa.setSelectedIndex(0);
		ma.requestFocus();
		table.clearSelection();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		ma.setText(table.getValueAt(row, 0) + "");
		ten.setText(table.getValueAt(row, 1) + "");
		maKhoa.setSelectedItem(table.getValueAt(row, 2));
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
