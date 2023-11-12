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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.SinhVienDAO;
import entity.HopDong;
import entity.SinhVien;

public class HopDongUI {
	private JPanel wrapper;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField ma;
	private JComboBox<String> maSV;
	private JDateChooser ngayKy, ngayHet;
	private List<SinhVien> dssv;
	private SinhVienDAO svDAO;

	public HopDongUI() {
		wrapper = new JPanel();
		svDAO = new SinhVienDAO();
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

	private JPanel getBody() {
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBackground(Color.WHITE);

		JPanel tableContainer = new JPanel();

		tableContainer.setLayout(new BorderLayout());

		String[] cols = { "Mã hợp đồng", "Mã sinh viên", "Mã phòng", "Ngày ký hợp đồng", "Ngày hết hợp đồng" };

		tableModel = new DefaultTableModel(cols, 0);
		table = createCustomTable(tableModel);

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
		container.add(getInputComboBox("Mã sinh viên", maSV = new JComboBox<String>(createSVOptions())));
		container.add(Box.createVerticalStrut(15));
		container.add(getInputComboBox("Mã phòng", new JComboBox<String>()));
		container.add(Box.createVerticalStrut(15));
		container.add(getInputCalender("Ngày ký hợp đồng", ngayKy = new JDateChooser()));
		container.add(Box.createVerticalStrut(15));
		container.add(getInputCalender("Ngày hết hợp đồng", ngayHet = new JDateChooser()));
		wrapper.add(container);
		return wrapper;
	}

	public JPanel getLayout() {
		dssv = svDAO.findAll();
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
			HopDong hopDong = new HopDong(ma.getText(), null, null, ngayKy.getDate(), ngayHet.getDate());
		}
	}

	private void xoa() {

	}

	private void chinhSua() {

	}

	private void lamMoi() {
		ma.setText("");
		maSV.setSelectedIndex(0);
		ngayHet.setDate(null);
		ngayKy.setDate(null);
		table.clearSelection();
		ma.requestFocus();
	}

	private boolean isValid() {
		if (ma.getText().equals("")) {
			JOptionPane.showMessageDialog(wrapper, "Mã hợp đồng không được rỗng");
			return false;
		} else if (ma.getText().matches("HD[0-9]{3}")) {
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
		return true;
	}

	private String[] createSVOptions() {
		String[] options = new String[dssv.size()];
		for (int i = 0; i < options.length; i++) {
			options[i] = dssv.get(i).getMaSinhVien();
		}
		return options;
	}
}
