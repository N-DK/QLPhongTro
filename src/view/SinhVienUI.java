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

import com.toedter.calendar.JDateChooser;

import dao.LopHocDAO;
import dao.SinhVienDAO;
import entity.LopHoc;
import entity.SinhVien;

public class SinhVienUI {
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
		container.setBackground(Color.WHITE);
		container.setBorder(new EmptyBorder(15, 0, 15, 0));
		JLabel title = new JLabel("QUẢN LÝ SINH VIÊN");
		title.setFont(new Font("Arial", Font.BOLD, 28));
		container.add(title);
		return container;
	}

	private JPanel getButtons() {
		JPanel container = new JPanel();
		container.setBackground(Color.WHITE);
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

		String[] cols = { "Mã sinh viên", "Họ", "Tên", "Mã lớp", "Quê quán", "Giới tính", "Ngày sinh", "SĐT" };

		tableModel = new DefaultTableModel(cols, 0);
		table = createCustomTable(tableModel);

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
		wrapper.setBackground(Color.WHITE);
		container.setBackground(Color.WHITE);
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
		btnContainer.setBackground(Color.WHITE);
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
				themSinhVien();
			} else if (label.equals(XOA)) {
				xoaSinhVien();
			} else if (label.equals(SUA)) {
				chinhSuaSinhVien();
			}
		});

		btnContainer.add(btn, BorderLayout.CENTER);
		btn.setPreferredSize(new Dimension(btn.getPreferredSize().width + 30, 45));
		return btnContainer;
	}

	private String[] createOptionsLopHoc() {
		String[] options = new String[dslh.size()];
		for (int i = 0; i < options.length; i++) {
			options[0] = dslh.get(i).getMa();
		}
		return options;
	}

	private void themSinhVien() {
		int gioiTinh = ((String) this.gioiTinh.getSelectedItem()).equals("Nam") ? 1 : 0;
		SinhVien sinhVien = new SinhVien(ho.getText(), ten.getText(), gioiTinh, ngaySinh.getDate(), sdt.getText(),
				ma.getText(), queQuan.getText(), (String) maLop.getSelectedItem());
		tableModel.addRow(sinhVien.getObjects());
		svDAO.insert(sinhVien);
	}

	private void xoaSinhVien() {

	}

	private void chinhSuaSinhVien() {

	}

}
