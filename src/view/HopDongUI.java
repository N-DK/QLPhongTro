package view;

import static constant.Main.SUA;
import static constant.Main.THEM;
import static constant.Main.XOA;
import static constant.Main.XR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Locale;

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

public class HopDongUI {
	private JPanel wrapper;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField ma, ho, ten, queQuan, sdt;

	public HopDongUI() {
		wrapper = new JPanel();
	}

	private JPanel getHeader() {
		JPanel container = new JPanel();
		container.setBackground(Color.WHITE);
		container.setBorder(new EmptyBorder(15, 0, 15, 0));
		JLabel title = new JLabel("QUẢN LÝ HỢP ĐỒNG");
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

		String[] cols = { "Mã hợp đồng", "Mã sinh viên", "Mã phòng", "Ngày ký hợp đồng", "Ngày hết hợp đồng" };

		tableModel = new DefaultTableModel(cols, 0);
		table = new JTable(tableModel);

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
		wrapper.setBackground(Color.WHITE);
		container.setBackground(Color.WHITE);
		container.add(getInput("Mã hợp đồng", ma = new JTextField()));
		container.add(Box.createVerticalStrut(15));
		container.add(getInputComboBox("Mã sinh viên"));
		container.add(Box.createVerticalStrut(15));
		container.add(getInputComboBox("Mã phòng"));
		container.add(Box.createVerticalStrut(15));
		container.add(getInputCalender("Ngày ký hợp đồng"));
		container.add(Box.createVerticalStrut(15));
		container.add(getInputCalender("Ngày hết hợp đồng"));
		wrapper.add(container);
		return wrapper;
	}

	public JPanel getLayout() {
		wrapper.setBorder(new EmptyBorder(0, 0, 15, 0));
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
		wrapper.add(Box.createHorizontalStrut(15));
		wrapper.add(getForm());
		wrapper.add(Box.createHorizontalStrut(15));
		wrapper.add(getBody());
		return wrapper;
	}

	private JPanel getInput(String label, JTextField textField) {
		JPanel container = new JPanel();
		container.setBackground(Color.WHITE);
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		JPanel Lable = new JPanel();
		Lable.setBackground(Color.WHITE);
		Lable.add(createLabel(label));
		container.add(Lable);
		JPanel TextField = new JPanel();
		TextField.setBackground(Color.WHITE);
		textField.setPreferredSize(new Dimension(208, 30));
		TextField.add(textField);
		container.add(TextField);
		return container;
	}

	private JPanel getInputComboBox(String label) {
		JPanel container = new JPanel();
		container.setBackground(Color.WHITE);
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		JPanel Lable = new JPanel();
		Lable.setBackground(Color.WHITE);
		Lable.add(createLabel(label));
		container.add(Lable);
		JPanel Combox = new JPanel();
		Combox.setBackground(Color.WHITE);
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setPreferredSize(new Dimension(208, 30));
		comboBox.addItem("DHKHMT17B");
		Combox.add(comboBox);
		container.add(Combox);
		return container;
	}

	private JLabel createLabel(String label) {
		JLabel title = new JLabel(label);
		title.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
		return title;
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

	private JPanel getInputCalender(String label) {
		JPanel container = new JPanel();
		container.setBackground(Color.WHITE);
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		JPanel Lable = new JPanel();
		Lable.setBackground(Color.WHITE);
		Lable.add(createLabel(label));
		container.add(Lable);
		JPanel TextField = new JPanel();
		TextField.setBackground(Color.WHITE);
		JDateChooser chooser = new JDateChooser();
		chooser.setLocale(Locale.US);
		chooser.setPreferredSize(new Dimension(208, 30));
		TextField.add(chooser);
		container.add(TextField);
		return container;
	}

	private void themSinhVien() {
		System.out.println(ma.getText() + ho.getText() + ten.getText());
	}

	private void xoaSinhVien() {

	}

	private void chinhSuaSinhVien() {

	}
}