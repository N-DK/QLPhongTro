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
import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import connectDatabase.Main;
import javax.management.loading.PrivateClassLoader;
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

public class ChuTroUI implements MouseListener{
	private JPanel wrapper;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField maCP, hoCP, tenCP, queQuanCP, sdtCP;
	private JDateChooser ngaySinh;
	private ChuTroDAO ctDAO;
	private ArrayList<ChuPhong> cp;
	private JComboBox< String> gioiTinh; 
	
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

		String[] cols = { "Mã chủ phòng", "Họ", "Tên", "SĐT", "Địa chỉ", "Ngày sinh","Giới tính" };

		tableModel = new DefaultTableModel(cols, 0);
		table = createCustomTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);

		table.addMouseListener(this);

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
		wrapper.setBackground(Color.WHITE);
		container.setBackground(Color.WHITE);
		container.add(getInput("Mã chủ phòng", maCP = new JTextField()));
		container.add(getInput("Họ", hoCP = new JTextField()));
		container.add(getInput("Tên", tenCP = new JTextField()));
		container.add(getInput("SĐT", sdtCP = new JTextField()));
		container.add(getInput("Địa chỉ", queQuanCP = new JTextField()));
		container.add(getInputCalender("Ngày sinh", ngaySinh= new JDateChooser()));
		container.add(getInputComboBox("Giới tính",gioiTinh= new JComboBox<String>(new String[] { "","Nam", "Nữ" })));

		
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
			String sql = "{call themChuPhong(?,?,?,?,?,?,?)}";
			ctDAO = new ChuTroDAO();
			if(ctDAO.themList(cp)) {
					
				if(ctDAO.them(cp)) {
					
					String t[] = {ma,ho, ten,sdt,diachi,ngaySinh.getDate()+"",gioiTinh.getSelectedItem().toString()};
					tableModel.addRow(t);
					JOptionPane.showMessageDialog(wrapper, "Thêm sinh viên thành công");
					XoaTrang();
				}
			}
			else {
				JOptionPane.showMessageDialog(wrapper, "Mã chủ phòng trùng");
			}
		}
	}

	private void xoaSinhVien() {
		ctDAO = new ChuTroDAO();
		int index = table.getSelectedRow();
		String ma = table.getValueAt(index, 0).toString();
		int hoi = JOptionPane.showConfirmDialog(wrapper, "Bạn có chắc muốn xóa không ?","Lưu ý",JOptionPane.YES_NO_OPTION);
		if(index>-1) {
			if(hoi==JOptionPane.YES_OPTION) {
				if(ctDAO.delete(ma)) {
					JOptionPane.showMessageDialog(wrapper,"Xoa thành công");
					tableModel.removeRow(index);
					XoaTrang();
				}
			}
		}
	}

	private void chinhSuaSinhVien() {
		ctDAO = new ChuTroDAO();
		int index = table.getSelectedRow();
		String maTruoc = table.getValueAt(index, 0).toString();
		String maThayDoi = maCP.getText();
		if(!maTruoc.equals(maThayDoi)) {
			JOptionPane.showMessageDialog(wrapper, "Không được thay đổi mã chủ phòng");
		}else {
			String ma = maCP.getText();
			String ho = hoCP.getText();
			String ten = tenCP.getText();
			String sdt = sdtCP.getText();
			String diachi = queQuanCP.getText();
			int gioitinh = gioiTinh.getSelectedItem().toString().equals("Nam") ? 1:0;
			
			ChuPhong cp =new ChuPhong(ma, ho, ten, sdt, diachi, ngaySinh.getDate(), gioitinh);
			
			if(ctDAO.updateChuPhong(cp)) {
				System.out.println(":");
				table.setValueAt(cp, index, 1);
				table.setValueAt(cp, index, 2);
				table.setValueAt(cp, index, 3);
				table.setValueAt(cp, index, 4);
				table.setValueAt(cp, index, 5);
				table.setValueAt(cp, index, 6);
				
			}
		}
	}
	public boolean getRegex() {
		String ma = maCP.getText();
		String ho = hoCP.getText();
		String ten = tenCP.getText();
		String sdt = sdtCP.getText();
		String diachi = queQuanCP.getText();
	
		String gioitinh = gioiTinh.getSelectedItem().toString();
		if(!ma.matches("CP\\d+")) {
			JOptionPane.showMessageDialog(wrapper, "Nhập sai ma");
			return false;
		}
		if(!ho.matches("[a-zA-Z]+")) {
			JOptionPane.showMessageDialog(wrapper, "Họ phải là kí tự");
			return false;
		}
		if(!ten.matches("[a-zA-Z]+")) {
			JOptionPane.showMessageDialog(wrapper, "Tên phải là kí tự");
			return false;
		}
		if(!sdt.matches("[0-9]{10}")) {
			JOptionPane.showMessageDialog(wrapper, "Số điện thoại phải là chữ số và có 10 số");
			return false;
		}
		if(!diachi.matches("\\w+")) {
			JOptionPane.showMessageDialog(wrapper, "Địa chỉ không có kí tự đặc biệt");
			return false;
		}
		if(ngaySinh.getDate() == null) {
			JOptionPane.showMessageDialog(wrapper, "Chưa chọn ngày sinh");
			return false;
		}
		if(gioitinh.equals("")) {
			JOptionPane.showMessageDialog(wrapper, "Chưa chọn nhân viên");
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
		
		ngaySinh.setDate(java.sql.Date.valueOf(table.getValueAt(index, 5).toString()));
		
		
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
	public void hienData() {
		ctDAO =new  ChuTroDAO();
		ArrayList<ChuPhong> cp = ctDAO.docTuDTB();
		for (ChuPhong c : cp) {
			String d = c.getGioiTinh()== 1 ? "Nam":"Nữ"  ;
			String[] row = {c.getMaChuPhong(),c.getHo(),c.getTen(),c.getSdt(),c.getDiaChi(),c.getNgaySinh()+"",d};
			tableModel.addRow(row);
		}
		table.setModel(tableModel);
	}

}
