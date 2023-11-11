package view;

import static constant.Main.CN;
import static constant.Main.CT;
import static constant.Main.DSPT;
import static constant.Main.DSSV;
import static constant.Main.HD;
import static constant.Main.K;
import static constant.Main.LH;
import static constant.Main.PT;
import static constant.Main.TKPT;
import static constant.Main.TKSV;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class DefaultLayout {
	private JPanel wrapper;
	private JPanel children;
	private JFrame jFrame;

	public DefaultLayout(JPanel children, JFrame jFrame) {
		this.children = children;
		this.jFrame = jFrame;
		wrapper = new JPanel();
	}

	private JPanel getSidebar() {
		JPanel container = new JPanel();
		container.setBackground(new Color(108, 166, 205));
		container.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
				new EmptyBorder(0, 15, 0, 700)));
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

		String[][] StudentOptions = { { DSSV, "src//image//list.gif" }, { LH, "src//image//school.gif" },
				{ K, "src//image//department.gif" }, { CN, "src//image//specialized.gif" },
				{ TKSV, "src//image//search.gif" } };
		String[][] phongTroOptions = { { DSPT, "src//image//list.gif" }, { TKPT, "src//image//search.gif" } };
		String[][] emptyOptions = {};

		container.add(makeNavButton("Sinh Viên", StudentOptions, "src//image//sinh_vien.gif"));
		container.add(makeNavButton(PT, phongTroOptions, "src//image//phong_tro.gif"));
		container.add(makeNavButton(CT, emptyOptions, "src//image//chu_tro.gif"));
		container.add(makeNavButton(HD, emptyOptions, "src//image//hop_dong.gif"));

		return container;
	}

	private JPanel getBody() {
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(new EmptyBorder(12, 0, 0, 0));
		container.setBackground(Color.WHITE);

		container.add(children);
		return container;
	}

	public JPanel getLayout() {
		wrapper.setLayout(new BorderLayout());
		wrapper.add(getSidebar(), BorderLayout.NORTH);
		wrapper.add(getBody(), BorderLayout.CENTER);
		return wrapper;
	}

	private JButton createNavItem(String title, String iconPath) {
		JButton btn = new JButton(title);
		ImageIcon icon = createImageIcon(iconPath);
		btn.setBorder(new EmptyBorder(15, 0, 15, 0));
		btn.setBackground(new Color(108, 166, 205));
		btn.setIcon(icon);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setOpaque(true);
		btn.setFont(new Font("Arial", Font.BOLD, 30));
		return btn;
	}

	private JPanel makeNavButton(String tilte, String[][] options, String iconPath) {
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());

		JButton btn = createNavItem(tilte, iconPath);

		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setOpaque(true);
		popupMenu.setBackground(Color.WHITE);
		popupMenu.setBorder(BorderFactory.createLineBorder(new Color(108, 166, 205)));

		for (String[] option : options) {
			JMenuItem item = new JMenuItem(option[0]);
			item.setFont(new Font("Arial", Font.BOLD, 15));
			item.setBackground(Color.WHITE);
			item.setIcon(createImageIcon(option[1]));
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					repaintBody(option[0]);
				}
			});
			popupMenu.add(item);
		}

		popupMenu.setPreferredSize(
				new Dimension(btn.getPreferredSize().width + 80, popupMenu.getPreferredSize().height));

		btn.setComponentPopupMenu(popupMenu);

		container.add(btn, BorderLayout.CENTER);

		btn.addActionListener(e -> {
			if (options.length == 0) {
				repaintBody(tilte);
			} else {
				showPopup(e, popupMenu);
			}
		});

		return container;
	}

	private void showPopup(ActionEvent e, JPopupMenu popupMenu) {
		Component b = (Component) e.getSource();

		Point p = b.getLocationOnScreen();

		popupMenu.show(b, 0, 0);

		popupMenu.setLocation(p.x, p.y + b.getHeight());

	}

	private ImageIcon createImageIcon(String path) {
		try {
			ImageIcon icon = new ImageIcon(path);
			return icon;
		} catch (Exception e) {
			System.out.println("Cannot find image");
		}
		return null;
	}

	private void repaintBody(String type) {
		switch (type) {
		case DSSV: {
			SinhVienUI sinhVienUI = new SinhVienUI();
			children = sinhVienUI.getLayout();
			break;
		}
		case LH: {
			LopHocUI lpoHocUI = new LopHocUI();
			children = lpoHocUI.getLayout();
			break;
		}
		case K: {
			KhoaUI khoaUI = new KhoaUI();
			children = khoaUI.getLayout();
			break;
		}
		case TKSV: {
			TimKiemSVUI timKiemSVUI = new TimKiemSVUI();
			children = timKiemSVUI.getLayout();
			break;
		}
		case HD: {
			HopDongUI hopDongUI = new HopDongUI();
			children = hopDongUI.getLayout();
			break;
		}
		case DSPT: {
			PhongTroUI phongTroUI = new PhongTroUI();
			children = phongTroUI.getLayout();
			break;
		}
		case TKPT: {
			TimKiemPhongTroUI timKiemPhongTroUI = new TimKiemPhongTroUI();
			children = timKiemPhongTroUI.getLayout();
			break;
		}
		case CT: {
			ChuTroUI chuTroUI = new ChuTroUI();
			children = chuTroUI.getLayout();
			break;
		}
		case CN: {
			ChuyenNganhUI chuyenNganhUI = new ChuyenNganhUI();
			children = chuyenNganhUI.getLayout();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
		wrapper.removeAll();
		getLayout().revalidate();
		getLayout().repaint();
		jFrame.add(getLayout());
	}

	@SuppressWarnings("serial")
	public static JTable createCustomTable(DefaultTableModel tableModel) {
		JTable table = new JTable(tableModel) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// TODO Auto-generated method stub
				Component component = super.prepareRenderer(renderer, row, column);
				if (!isRowSelected(row)) {
					component.setBackground(row % 2 != 0 ? Color.WHITE : new Color(232, 232, 232));
				} else {
					component = super.prepareRenderer(renderer, row, column);
				}
				return component;
			}
		};

		table.setFont(new Font("Arial", Font.PLAIN, 16));
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		table.getTableHeader().setBackground(new Color(176, 196, 222));
		table.getTableHeader().setForeground(Color.BLACK);
		table.getTableHeader().setPreferredSize(new Dimension(table.getPreferredSize().width, 30));
		table.setRowHeight(30);

		return table;
	}

	public static JPanel getInput(String label, JTextField textField) {
		JPanel container = new JPanel();
		container.setBackground(new Color(176, 226, 255));
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		JPanel Lable = new JPanel();
		Lable.setBackground(new Color(176, 226, 255));
		Lable.add(createLabel(label));
		container.add(Lable);

		JPanel TextField = new JPanel();
		TextField.setBackground(new Color(176, 226, 255));
		textField.setFont(new Font("Arial", Font.PLAIN, 16));
		textField.setPreferredSize(new Dimension(208, 30));
		TextField.add(textField);
		container.add(TextField);

		return container;
	}

	public static JLabel createLabel(String label) {
		JLabel title = new JLabel(label);
		title.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
		return title;
	}

	public static JPanel getInputComboBox(String label, JComboBox<String> comboBox) {
		JPanel container = new JPanel();
		container.setBackground(new Color(176, 226, 255));
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		JPanel Lable = new JPanel();
		Lable.setBackground(new Color(176, 226, 255));
		Lable.add(createLabel(label));
		container.add(Lable);
		JPanel Combox = new JPanel();
		Combox.setBackground(new Color(176, 226, 255));
		comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
		comboBox.setBackground(Color.WHITE);
		comboBox.setPreferredSize(new Dimension(208, 30));
		Combox.add(comboBox);
		container.add(Combox);
		return container;
	}

	public static JPanel getInputCalender(String label, JDateChooser chooser) {
		JPanel container = new JPanel();
		container.setBackground(new Color(176, 226, 255));
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		JPanel Lable = new JPanel();
		Lable.setBackground(new Color(176, 226, 255));
		Lable.add(createLabel(label));
		container.add(Lable);
		JPanel chooserContainer = new JPanel();
		chooserContainer.setBackground(new Color(176, 226, 255));
		chooser.setLocale(Locale.US);
		chooser.setFont(new Font("Arial", Font.PLAIN, 12));
		chooser.getDateEditor().setEnabled(false);
		JTextFieldDateEditor textFieldDateEditor = (JTextFieldDateEditor) chooser.getDateEditor();
		textFieldDateEditor.setFont(new Font("Arial", Font.PLAIN, 16));
		textFieldDateEditor.setBackground(Color.WHITE);
		textFieldDateEditor.setDisabledTextColor(Color.BLACK);
		chooser.setPreferredSize(new Dimension(208, 30));
		chooserContainer.add(chooser);
		container.add(chooserContainer);
		return container;
	}
}
