package view;

<<<<<<< HEAD
import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame jFrame = new JFrame("Quản lý phòng trọ");
		SinhVienUI sinhVienUI = new SinhVienUI();
		DefaultLayout layout = new DefaultLayout(sinhVienUI.getLayout(), jFrame);

		jFrame.add(layout.getLayout());
		jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jFrame.setDefaultCloseOperation(3);
		jFrame.setVisible(true);

	}
=======
public class Main {
    public static void main(String[] args) {
        
    }
>>>>>>> 34c95781100bd9924a1cbf048dc63a8cb84ee8ce
}
