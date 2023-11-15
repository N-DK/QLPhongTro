package view;

import static constant.Main.CHON;
import static constant.Main.TIMKIEM;
import static constant.Main.XR;
import static view.DefaultLayout.createCustomTable;
import static view.DefaultLayout.getInput;
import static view.DefaultLayout.getInputComboBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.ChuyenNganhDAO;
import dao.HopDongDAO;
import dao.KhoaDAO;
import dao.LopHocDAO;
import dao.SinhVienDAO;
import entity.ChuyenNganh;
import entity.HopDong;
import entity.Khoa;
import entity.LopHoc;
import entity.SinhVien;

public class TimKiemSVUI {
	private JPanel wrapper;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField ma, ho, ten, queQuan, maSV_PhongTro;
	private JComboBox<String> maLop, maKhoa, maCN;
	private SinhVienDAO svDAO;
	private LopHocDAO lopDAO;
	private HopDongDAO hdDAO;
	private KhoaDAO khoaDAO;
	private ChuyenNganhDAO cnDAO;
	private List<SinhVien> dssv;
	private List<LopHoc> dslh;
	private List<Khoa> dsKhoa;
	private List<ChuyenNganh> dscn;
	private boolean isSupportBtn;
	private JFrame jFrame;

	public TimKiemSVUI() {
		wrapper = new JPanel();
		svDAO = new SinhVienDAO();
		lopDAO = new LopHocDAO();
		khoaDAO = new KhoaDAO();
		cnDAO = new ChuyenNganhDAO();
		hdDAO = new HopDongDAO();
	}

	public TimKiemSVUI(JTextField maSV, JFrame jFrame) {
		wrapper = new JPanel();
		svDAO = new SinhVienDAO();
		lopDAO = new LopHocDAO();
		khoaDAO = new KhoaDAO();
		cnDAO = new ChuyenNganhDAO();
		hdDAO = new HopDongDAO();
		this.isSupportBtn = true;
		this.maSV_PhongTro = maSV;
		this.jFrame = jFrame;
	}

	private JPanel getHeader() {
		JPanel container = new JPanel();
		container.setBackground(new Color(176, 226, 255));
		container.setBorder(new EmptyBorder(15, 0, 15, 0));
		JLabel title = new JLabel("TÌM KIẾM SINH VIÊN");
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

		String[] cols = { "Mã sinh viên", "Họ", "Tên", "Mã lớp", "Quê quán", "Giới tính", "Ngày sinh", "SĐT" };

		tableModel = new DefaultTableModel(cols, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		table = createCustomTable(tableModel);

		for (SinhVien sinhVien : dssv) {
			tableModel.addRow(sinhVien.getObjects());
		}
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(Color.WHITE);
		tableContainer.add(scrollPane);

		container.add(getHeader());
		container.add(Box.createVerticalStrut(15));
		container.add(tableContainer);
		if (isSupportBtn) {
			container.add(Box.createVerticalStrut(15));
			container.add(getButtons());
		}
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
		container.add(getInput("Mã sinh viên", ma = new JTextField()));
		container.add(getInput("Họ", ho = new JTextField()));
		container.add(getInput("Tên", ten = new JTextField()));
		container.add(getInputComboBox("Mã lớp", maLop = new JComboBox<String>(createOptionsLopHoc())));
		container.add(getInputComboBox("Mã khoa", maKhoa = new JComboBox<String>(createOptionsKhoa())));
		container.add(getInputComboBox("Mã chuyên ngành", maCN = new JComboBox<String>(createOptionsChuyenNganh())));
		container.add(getInput("Quê quán", queQuan = new JTextField()));
		container.add(Box.createVerticalStrut(15));
		container.add(createBtn(TIMKIEM, "src//image//search.gif"));
		container.add(Box.createVerticalStrut(15));
		container.add(createBtn(XR, "src//image//refresh.gif"));
		wrapper.add(container);
		return wrapper;
	}

	public JPanel getLayout() {
		wrapper.setBackground(Color.WHITE);
		dssv = isSupportBtn ? getListNotInHopDong() : svDAO.findAll();
		dslh = lopDAO.findAll();
		dsKhoa = khoaDAO.findAll();
		dscn = cnDAO.findAll();
		wrapper.setBorder(new EmptyBorder(0, 0, 15, 0));
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
		wrapper.add(Box.createHorizontalStrut(15));
		wrapper.add(getForm());
		wrapper.add(Box.createHorizontalStrut(15));
		wrapper.add(getBody());
		return wrapper;
	}

	private JPanel getButtons() {
		JPanel container = new JPanel();
		container.setBackground(new Color(176, 226, 255));
		container.setBorder(new EmptyBorder(20, 0, 20, 0));
		JPanel btnsContainer = new JPanel();
		btnsContainer.setLayout(new GridLayout(1, 4));

		btnsContainer.add(createBtn(CHON, "src//image//check.gif"));

		container.add(btnsContainer);
		return container;
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
				timKiem();
			} else if (label.equals(XR)) {
				lamMoi();
			} else if (label.equals(CHON)) {
				chon();
			}
		});

		btnContainer.add(btn, BorderLayout.CENTER);
		btn.setPreferredSize(new Dimension(btn.getPreferredSize().width + 10, 45));
		return btnContainer;
	}

	private void chon() {
		int row = table.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(wrapper, "Vui lòng chọn sinh viên");
			return;
		}
		maSV_PhongTro.setText(table.getValueAt(row, 0) + "");
		jFrame.dispose();
	}

	private String[] createOptionsLopHoc() {
		String[] options = new String[dslh.size() + 1];
		options[0] = "";
		for (int i = 1; i < options.length; i++) {
			options[i] = dslh.get(i - 1).getMa();
		}
		return options;
	}

	private String[] createOptionsKhoa() {
		String[] options = new String[dsKhoa.size() + 1];
		options[0] = "";
		for (int i = 1; i < options.length; i++) {
			options[i] = dsKhoa.get(i - 1).getMa();
		}
		return options;
	}

	private String[] createOptionsChuyenNganh() {
		String[] options = new String[dscn.size() + 1];
		options[0] = "";
		for (int i = 1; i < options.length; i++) {
			options[i] = dscn.get(i - 1).getMa();
		}
		return options;
	}

	private void timKiem() {
		dssv = svDAO.findBy(createText(ma.getText()), createText(ho.getText()), createText(ten.getText()),
				createText((String) maLop.getSelectedItem()), createText(queQuan.getText()),
				createText((String) maKhoa.getSelectedItem()), createText((String) maCN.getSelectedItem()));
		if (isSupportBtn) {
			for (HopDong hopDong : hdDAO.findAll()) {
				if (dssv.contains(hopDong.getSinhVien())) {
					dssv.remove(hopDong.getSinhVien());
				}
			}
		}
		clearTable();
		for (SinhVien sinhVien : dssv) {
			tableModel.addRow(sinhVien.getObjects());
		}
		resetTexts();
	}

	private void lamMoi() {
		resetTexts();
		dssv = isSupportBtn ? getListNotInHopDong() : svDAO.findAll();
		clearTable();
		for (SinhVien sinhVien : dssv) {
			tableModel.addRow(sinhVien.getObjects());
		}
	}

	private void resetTexts() {
		ma.setText("");
		ho.setText("");
		ten.setText("");
		maLop.setSelectedIndex(0);
		maKhoa.setSelectedIndex(0);
		maCN.setSelectedIndex(0);
		queQuan.setText("");
		ma.requestFocus();
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

	private List<SinhVien> getListNotInHopDong() {
		List<SinhVien> resutls = svDAO.findAll();
		for (HopDong hopDong : hdDAO.findAll()) {
			if (resutls.contains(hopDong.getSinhVien())) {
				resutls.remove(hopDong.getSinhVien());
			}
		}
		return resutls;
	}
}
