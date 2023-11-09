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

public class LopHocUI {
	private JPanel wrapper;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField ma, ten, tenCN;

	public LopHocUI() {
		wrapper = new JPanel();
	}

	private JPanel getHeader() {
		JPanel container = new JPanel();
		container.setBackground(Color.WHITE);
		container.setBorder(new EmptyBorder(15, 0, 15, 0));
		JLabel title = new JLabel("LỚP HỌC");
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

		String[] cols = { "Mã Lớp", "Tên lớp", "Tên GVCN", "Mã khoa" };

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
		container.add(getInput("Mã lớp", ma = new JTextField()));
		container.add(getInput("Tên lớp", ten = new JTextField()));
		container.add(getInput("Tên giáo viên chủ nhiệm", tenCN = new JTextField()));
		container.add(getInputComboBox("Mã khoa"));
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
				themLopHoc();
			} else if (label.equals(XOA)) {
				xoaLopHoc();
			} else if (label.equals(SUA)) {
				chinhSuaLopHoc();
			}
		});

		btnContainer.add(btn, BorderLayout.CENTER);
		btn.setPreferredSize(new Dimension(btn.getPreferredSize().width + 30, 45));
		return btnContainer;
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

	private void themLopHoc() {
	}

	private void xoaLopHoc() {

	}

	private void chinhSuaLopHoc() {

	}
}
