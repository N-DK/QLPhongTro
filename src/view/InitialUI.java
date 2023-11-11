package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InitialUI {
	private JPanel wrapper;

	public InitialUI() {
		wrapper = new JPanel();
	}

	private JPanel getHeader() {
		JPanel container = new JPanel();
		container.setBackground(Color.WHITE);

		JLabel title = new JLabel("CHƯƠNG TRÌNH QUẢN LÝ THÔNG TIN Ở TRỌ CỦA SINH VIÊN");
		title.setFont(new Font("Arial", Font.BOLD, 32));
		title.setForeground(new Color(32, 90, 167));
		container.add(title);
		return container;
	}

	private JPanel getBackground() {
		JPanel container = new JPanel();
		JLabel screen = new JLabel();
		ImageIcon originalIcon = new ImageIcon("src//image//initial.jpg");
		Image originalImage = originalIcon.getImage();
		Image scaledImage = originalImage.getScaledInstance(1200, 670, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		screen.setIcon(scaledIcon);

		container.add(screen);
		container.setBackground(Color.WHITE);
		return container;
	}

	public JPanel getLayout() {
		wrapper.setBorder(new EmptyBorder(20, 0, 0, 0));
		wrapper.setBackground(Color.WHITE);
		wrapper.setLayout(new BorderLayout());
		wrapper.add(getHeader(), BorderLayout.NORTH);
		wrapper.add(getBackground(), BorderLayout.CENTER);
		return wrapper;
	}
}
