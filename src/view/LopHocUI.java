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
import dao.LopHocDAO;
import entity.ChuyenNganh;
import entity.LopHoc;

public class LopHocUI implements MouseListener {
	private JPanel wrapper;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField ma, ten, tenCN;
	private JComboBox<String> macn;
	private LopHocDAO lopDAO;
	private ChuyenNganhDAO cnDAO;
	private List<LopHoc> dslh;
	private List<ChuyenNganh> dscn;

	public LopHocUI() {
		wrapper = new JPanel();
		lopDAO = new LopHocDAO();
		cnDAO = new ChuyenNganhDAO();
	}

	private JPanel getHeader() {
		JPanel container = new JPanel();
		container.setBackground(new Color(176, 226, 255));
		container.setBorder(new EmptyBorder(15, 0, 15, 0));
		JLabel title = new JLabel("LỚP HỌC");
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

		String[] cols = { "Mã Lớp", "Tên lớp", "Tên GVCN", "Mã chuyên ngành" };

		tableModel = new DefaultTableModel(cols, 0);
		table = createCustomTable(tableModel);
		table.addMouseListener(this);

		for (LopHoc lop : dslh) {
			tableModel.addRow(lop.getObjects());
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
		container.setBorder(new EmptyBorder(30, 30, 400, 30));
		wrapper.setBackground(new Color(176, 226, 255));
		container.setBackground(new Color(176, 226, 255));
		container.setBorder(new EmptyBorder(30, 30, 0, 30));
		wrapper.setBackground(new Color(176, 226, 255));
		container.setBackground(new Color(176, 226, 255));
		container.add(getInput("Mã lớp", ma = new JTextField()));
		container.add(getInput("Tên lớp", ten = new JTextField()));
		container.add(getInput("Tên giáo viên chủ nhiệm", tenCN = new JTextField()));
		container.add(getInputComboBox("Mã chuyên ngành", macn = new JComboBox<String>(createOptionsCN())));
		wrapper.add(container);
		return wrapper;
	}

	public JPanel getLayout() {
		dslh = lopDAO.findAll();
		dscn = cnDAO.findAll();
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

	private String[] createOptionsCN() {
		String[] options = new String[dscn.size()];
		for (int i = 0; i < options.length; i++) {
			options[i] = dscn.get(i).getMa();
		}
		return options;
	}

	private void them() {
		ChuyenNganh chuyenNganh = cnDAO.findOneById((String) macn.getSelectedItem());
		LopHoc lop = new LopHoc(ma.getText(), ten.getText(), tenCN.getText(), chuyenNganh);
		if (lopDAO.save(lop, "insert")) {
			tableModel.addRow(lop.getObjects());
			JOptionPane.showMessageDialog(wrapper, "Thêm lớp thành công");
			lamMoi();
		} else {
			JOptionPane.showMessageDialog(wrapper, "Mã lớp không được trùng");
		}
	}

	private void xoa() {
		int row = table.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(wrapper, "Vui lòng chọn dòng cần xóa");
		} else {
			if (JOptionPane.showConfirmDialog(wrapper, "Bạn có chắc xóa dòng này không", "Cảnh báo",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				boolean isSuccess = lopDAO.deleteOneById((String) table.getValueAt(row, 0));
				if (isSuccess) {
					tableModel.removeRow(row);
					JOptionPane.showMessageDialog(wrapper, "Xóa lớp thành công");
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
				ChuyenNganh chuyenNganh = cnDAO.findOneById((String) macn.getSelectedItem());
				LopHoc lop = new LopHoc(ma.getText(), ten.getText(), tenCN.getText(), chuyenNganh);
				boolean isSuccess = lopDAO.save(lop, "update");
				if (isSuccess) {
					table.setValueAt(lop.getMa(), row, 0);
					table.setValueAt(lop.getTen(), row, 1);
					table.setValueAt(lop.getGvcn(), row, 2);
					table.setValueAt(lop.getChuyenNganh().getMa(), row, 3);
					JOptionPane.showMessageDialog(wrapper, "Sửa lớp thành công");
					lamMoi();
				} else {
					JOptionPane.showMessageDialog(wrapper, "Không được sửa mã lớp!");
				}
			}
		}
	}

	private void lamMoi() {
		ma.setText("");
		ten.setText("");
		tenCN.setText("");
		macn.setSelectedIndex(0);
		ma.requestFocus();
		table.clearSelection();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		ma.setText(table.getValueAt(row, 0) + "");
		ten.setText(table.getValueAt(row, 1) + "");
		tenCN.setText(table.getValueAt(row, 2) + "");
		macn.setSelectedItem(table.getValueAt(row, 3));
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
