package view;

import static constant.Main.SUA;
import static constant.Main.THEM;
import static constant.Main.XOA;
import static constant.Main.XR;
import static view.DefaultLayout.createCustomTable;
import static view.DefaultLayout.getInput;
import static view.DefaultLayout.getInputCalender;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class ChuTroUI {
	private JPanel wrapper;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField ma, ho, ten, queQuan, sdt;

	public ChuTroUI() {
		wrapper = new JPanel();
	}

	private JPanel getHeader() {
		JPanel container = new JPanel();
		container.setBackground(new Color(176, 226, 255));
		container.setBorder(new EmptyBorder(15, 0, 15, 0));
		JLabel title = new JLabel("QUẢN LÝ CHỦ TRỌ");
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

		String[] cols = { "Mã chủ phòng", "Họ", "Tên", "SĐT", "Địa chỉ", "Ngày sinh" };

		tableModel = new DefaultTableModel(cols, 0);
		table = createCustomTable(tableModel);
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
<<<<<<< HEAD
		container.setBorder(new EmptyBorder(30, 30, 400, 30));
		wrapper.setBackground(new Color(176, 226, 255));
		container.setBackground(new Color(176, 226, 255));
=======
		container.setBorder(new EmptyBorder(30, 30, 0, 30));
		wrapper.setBackground(new Color(181, 181, 181));
		container.setBackground(new Color(181, 181, 181));
>>>>>>> 1efa92c4308e578dcbc44a3b39e92858465bfc10
		container.add(getInput("Mã chủ phòng", ma = new JTextField()));
		container.add(getInput("Họ", ho = new JTextField()));
		container.add(getInput("Tên", ten = new JTextField()));
		container.add(getInput("SĐT", sdt = new JTextField()));
		container.add(getInput("Địa chỉ", queQuan = new JTextField()));
		container.add(getInputCalender("Ngày sinh", new JDateChooser()));
		wrapper.add(container);
		return wrapper;
	}

	public JPanel getLayout() {
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

	private void themSinhVien() {
		System.out.println(ma.getText() + ho.getText() + ten.getText());
	}

	private void xoaSinhVien() {

	}

	private void chinhSuaSinhVien() {

	}
}
