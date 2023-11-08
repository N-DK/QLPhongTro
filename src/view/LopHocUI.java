package view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LopHocUI {
	private JPanel wrapper;

	public LopHocUI() {
		wrapper = new JPanel();
	}

	private JPanel getHeader() {
		JPanel container = new JPanel();
		JLabel title = new JLabel("QUẢN LÝ LỚP HỌC");
		title.setFont(new Font("Arial", Font.BOLD, 28));
		container.add(title);
		return container;
	}

	public JPanel getLayout() {
		wrapper.add(getHeader());
		return wrapper;
	}
}
