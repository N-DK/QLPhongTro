package view;

import static constant.Main.SUA;
import static constant.Main.THEM;
import static constant.Main.TIMKIEM;
import static constant.Main.XOA;
import static constant.Main.XR;
import static view.DefaultLayout.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

<<<<<<< HEAD
import dao.PhongTroDAO;
import entity.PhongTro;
=======
import dao.ChuTroDAO;
import dao.PhongTroDAO;
import entity.ChuPhong;
import entity.PhongTro;
import entity.SinhVien;
>>>>>>> 7c0dc3078a0ed3a8199e84f2c58de8edc26431fb

public class TimKiemPhongTroUI {
	private JPanel wrapper;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField ma, gia, diaChi, queQuan, sdt;
<<<<<<< HEAD
	private List<PhongTro> dspt;
	private PhongTroDAO ptDAO;

	public TimKiemPhongTroUI() {
		wrapper = new JPanel();
		ptDAO = new PhongTroDAO();
=======
	private JComboBox<String> maChuPhong, tinhTrang;
	private PhongTroDAO phongTroDAO;
	private ChuTroDAO chuTroDAO;
	private List<PhongTro> dsPhongTro;
	private List<ChuPhong> dsChuPhong;

	public TimKiemPhongTroUI() {
		wrapper = new JPanel();
		phongTroDAO = new PhongTroDAO();
		chuTroDAO = new ChuTroDAO();
>>>>>>> 7c0dc3078a0ed3a8199e84f2c58de8edc26431fb
	}

	private JPanel getHeader() {
		JPanel container = new JPanel();
		container.setBackground(new Color(176, 226, 255));
		container.setBorder(new EmptyBorder(15, 0, 15, 0));
		JLabel title = new JLabel("TÌM KIẾM PHÒNG TRỌ");
		title.setFont(new Font("Arial", Font.BOLD, 28));
		container.add(title);
		return container;
	}

	@SuppressWarnings("serial")
	private JPanel getBody() {
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBackground(Color.WHITE);

		JPanel tableContainer = new JPanel();

		tableContainer.setLayout(new BorderLayout());

		String[] cols = { "Mã phòng trọ", "Địa Chỉ", "Giá", "Mã chủ phòng", "Tình trạng phòng" };

		tableModel = new DefaultTableModel(cols, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		table = createCustomTable(tableModel);

<<<<<<< HEAD
		for (PhongTro phongTro : dspt) {
			tableModel.addRow(phongTro.getObject());
		}

=======
		for (PhongTro phongTro : dsPhongTro) {
			tableModel.addRow(phongTro.getObject());
		}
>>>>>>> 7c0dc3078a0ed3a8199e84f2c58de8edc26431fb
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(Color.WHITE);
		tableContainer.add(scrollPane);

		container.add(getHeader());
		container.add(Box.createVerticalStrut(15));
		container.add(tableContainer);
		return container;
	}

	private JPanel getForm() {
		JPanel wrapper = new JPanel();
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBorder(new EmptyBorder(30, 30, 0, 30));
		container.setBorder(new EmptyBorder(30, 30, 400, 30));
		wrapper.setBackground(new Color(176, 226, 255));
		container.setBackground(new Color(176, 226, 255));
		container.add(getInput("Mã phòng trọ", ma = new JTextField()));
//		container.add(getInput("SĐT", sdt = new JTextField()));
		container.add(getInput("Địa chỉ", diaChi = new JTextField()));
		container.add(getInputComboBox("Mã Chủ phòng", new JComboBox<String>(createOptionChutro())));
		container.add(Box.createVerticalStrut(15));
		container.add(createBtn(TIMKIEM, "src//image//search.gif"));
		container.add(Box.createVerticalStrut(15));
		container.add(createBtn(XR, "src//image//refresh.gif"));
		wrapper.add(container);
		wrapper.add(container);
		return wrapper;
	}

	public JPanel getLayout() {
		dspt = ptDAO.findAll();
		wrapper.setBackground(Color.WHITE);
		dsChuPhong = chuTroDAO.findAll();
		dsPhongTro = phongTroDAO.findAll();
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
			if (label.equals(TIMKIEM)) {
				timPhong();
			} else if (label.equals(XR)) {
				lamMoi();
			}
		});

		btnContainer.add(btn, BorderLayout.CENTER);
		btn.setPreferredSize(new Dimension(btn.getPreferredSize().width, 45));
		return btnContainer;
	}

	private String[] createOptionChutro() {
		String[] options = new String[dsChuPhong.size()];
		for (int i = 0; i < options.length; i++) {
			options[i] = dsChuPhong.get(i).getMaChuPhong();
		}

		return options;
	}
	
	private void timPhong() {
		dsPhongTro = phongTroDAO.findBy(createText(ma.getText()), createText(diaChi.getText()), createText((String) maChuPhong.getSelectedItem()));
		clearTable();
		for (PhongTro phongTro : dsPhongTro) {
			tableModel.addColumn(phongTro.getObject());
		}
		resetTexts();
	}

	private void lamMoi() {
		resetTexts();
		dsPhongTro = phongTroDAO.findAll();
		clearTable();
		for (PhongTro phongTro : dsPhongTro) {
			tableModel.addRow(phongTro.getObject());
		}
	}
	
	private void resetTexts() {
		ma.setText("");
		diaChi.setText("");
		maChuPhong.setSelectedItem(0);
		table.clearSelection();
	}
	
	private String createText(String text) {
		return text.equals("") ? null : text;
	}
	
	private void clearTable() {
		int length = tableModel.getRowCount();
		for (int i = 0; i < length; i++) {
			tableModel.removeRow(0);
		}
	}
}




