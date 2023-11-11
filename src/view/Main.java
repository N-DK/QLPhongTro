package view;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame jFrame = new JFrame("Quản lý phòng trọ");
//		SinhVienUI sinhVienUI = new SinhVienUI();
		InitialUI initialUI = new InitialUI();
		DefaultLayout layout = new DefaultLayout(initialUI.getLayout(), jFrame);

		jFrame.add(layout.getLayout());
		jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jFrame.setDefaultCloseOperation(3);
		jFrame.setVisible(true);
		jFrame.setResizable(false);
	}
}
