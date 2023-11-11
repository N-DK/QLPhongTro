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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.spec.DSAGenParameterSpec;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

import com.toedter.calendar.JDateChooser;

import dao.ChuTroDAO;
import entity.ChuPhong;

public class ChuTroUI extends JFrame implements MouseListener{
	private JPanel wrapper;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField maCP, hoCP, tenCP, queQuanCP, sdtCP;
	private JDateChooser ngaySinh;
	private ChuTroDAO ctDAO;
	private ArrayList<ChuPhong> cp;
	private JComboBox< String> gioiTinh; 
	public ChuTroUI() {
		setSize(1280,720);
		setLocationRelativeTo(null);
		wrapper = new JPanel();
		add(fgetLayout());
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	private JPanel getHeader() {
		JPanel container = new JPanel();
		container.setBackground(new Color(181, 181, 181));
		container.setBorder(new EmptyBorder(15, 0, 15, 0));
		JLabel title = new JLabel("QUẢN LÝ CHỦ TRỌ");
		title.setFont(new Font("Arial", Font.BOLD, 28));
		container.add(title);
		return container;
	}

	private JPanel getButtons() {
		JPanel container = new JPanel();
		container.setBackground(new Color(181, 181, 181));
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

		String[] cols = { "Mã chủ phòng", "Họ", "Tên", "SĐT", "Địa chỉ", "Ngày sinh","Giới tính" };

		tableModel = new DefaultTableModel(cols, 0);
		table = createCustomTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		table.addMouseListener(this);
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
		wrapper.setBackground(Color.WHITE);
		container.setBackground(Color.WHITE);
		container.add(getInput("Mã chủ phòng", maCP = new JTextField()));
		container.add(getInput("Họ", hoCP = new JTextField()));
		container.add(getInput("Tên", tenCP = new JTextField()));
		container.add(getInput("SĐT", sdtCP = new JTextField()));
		container.add(getInput("Địa chỉ", queQuanCP = new JTextField()));
		container.add(getInputCalender("Ngày sinh", ngaySinh= new JDateChooser()));
		container.add(getInputComboBox("Giới tính",gioiTinh= new JComboBox<String>(new String[] { "","Nam", "Nữ" })));
=======
		container.setBorder(new EmptyBorder(30, 30, 0, 30));
		wrapper.setBackground(new Color(181, 181, 181));
		container.setBackground(new Color(181, 181, 181));
		container.add(getInput("Mã chủ phòng", ma = new JTextField()));
		container.add(getInput("Họ", ho = new JTextField()));
		container.add(getInput("Tên", ten = new JTextField()));
		container.add(getInput("SĐT", sdt = new JTextField()));
		container.add(getInput("Địa chỉ", queQuan = new JTextField()));
		container.add(getInputCalender("Ngày sinh", new JDateChooser()));
>>>>>>> 7dec6fc5f219c28b7de406250d9b4ebe5f4dc059
		wrapper.add(container);
		return wrapper;
	}

	public JPanel fgetLayout() {
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
		btnContainer.setBackground(new Color(181, 181, 181));
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
			}else {
				XoaTrang();
			}
		});

		btnContainer.add(btn, BorderLayout.CENTER);
		btn.setPreferredSize(new Dimension(btn.getPreferredSize().width + 30, 45));
		return btnContainer;
	}

	private void XoaTrang() {
		maCP.setText("");
		hoCP.setText("");
		tenCP.setText("");
		queQuanCP.setText("");
		ngaySinh.setDate(null);
		sdtCP.setText("");
		gioiTinh.setSelectedIndex(0);
		maCP.requestDefaultFocus();
	}

	private void themSinhVien() {
		if(getRegex()) {
			String ma = maCP.getText();
			String ho = hoCP.getText();
			String ten = tenCP.getText();
			String sdt = sdtCP.getText();
			String diachi = queQuanCP.getText();
			int gioitinh = gioiTinh.getSelectedItem().toString().equals("Nam") ? 1:0;
			
			ChuPhong cp =new ChuPhong(ma, ho, ten, sdt, diachi, ngaySinh.getDate(), gioitinh);
			ctDAO = new ChuTroDAO();
			if(ctDAO.themList(cp)) {
					
				if(ctDAO.them(cp)) {
					
					String t[] = {ma,ho, ten,sdt,diachi,ngaySinh.getDate()+"",gioiTinh.getSelectedItem().toString()};
					tableModel.addRow(t);
					JOptionPane.showMessageDialog(this, "Thêm sinh viên thành công");
					XoaTrang();
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Mã chủ phòng trùng");
			}
		}
	}

	private void xoaSinhVien() {

	}

	private void chinhSuaSinhVien() {

	}
	public static void main(String[] args) {
		new ChuTroUI();
	}
	public boolean getRegex() {
		String ma = maCP.getText();
		String ho = hoCP.getText();
		String ten = tenCP.getText();
		String sdt = sdtCP.getText();
		String diachi = queQuanCP.getText();
	
		String gioitinh = gioiTinh.getSelectedItem().toString();
		if(!ma.matches("CP\\d+")) {
			JOptionPane.showMessageDialog(this, "Nhập sai ma");
			return false;
		}
		if(!ho.matches("[a-zA-Z]+")) {
			JOptionPane.showMessageDialog(this, "Họ phải là kí tự");
			return false;
		}
		if(!ten.matches("[a-zA-Z]+")) {
			JOptionPane.showMessageDialog(this, "Tên phải là kí tự");
			return false;
		}
		if(!sdt.matches("[0-9]{10}")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại phải là chữ số và có 11 số");
			return false;
		}
		if(!diachi.matches("\\w+")) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không có kí tự đặc biệt");
			return false;
		}
		if(ngaySinh.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Chưa chọn ngày sinh");
			return false;
		}
		if(gioitinh.equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa chọn nhân viên");
			return false;
		}
		return true;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int index = table.getSelectedRow();
		maCP.setText(table.getValueAt(index, 0).toString());
		hoCP.setText(table.getValueAt(index, 1).toString());
		tenCP.setText(table.getValueAt(index, 2).toString());
		sdtCP.setText(table.getValueAt(index, 3).toString());
		queQuanCP.setText(table.getValueAt(index, 4).toString());
		try {
			ngaySinh.setDate(new SimpleDateFormat("dd-MM-yyyy").parse((String) table.getValueAt(index, 5)));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		gioiTinh.setSelectedItem(table.getValueAt(index, 6));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
