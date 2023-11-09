package view;

import static constant.Main.*;
import static constant.Main.DSSV;
import static constant.Main.HD;
import static constant.Main.K;
import static constant.Main.LH;
import static constant.Main.PT;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

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
		container.setBackground(Color.GRAY);
		container.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
				new EmptyBorder(0, 15, 0, 700)));
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

		String[][] StudentOptions = { { DSSV, "src//image//list.gif" }, { LH, "src//image//school.gif" },
				{ K, "src//image//department.gif" }, { TKSV, "src//image//search.gif" } };
		String[][] chuTroOptions = { { QLCT, "src//image//list.gif" } };
		String[][] emptyOptions = {};

		container.add(makeNavButton("Sinh Viên", StudentOptions, "src//image//sinh_vien.gif"));
//		container.add(Box.createHorizontalStrut(10));
		container.add(makeNavButton(PT, emptyOptions, "src//image//phong_tro.gif"));
//		container.add(Box.createHorizontalStrut(10));
		container.add(makeNavButton(CT, chuTroOptions, "src//image//chu_tro.gif"));
//		container.add(Box.createHorizontalStrut(10));
		container.add(makeNavButton(HD, emptyOptions, "src//image//hop_dong.gif"));

		return container;
	}

	private JPanel getBody() {
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(new EmptyBorder(12, 0, 0, 0));

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
//		JButton container = new JPanel();

//		JButton jLabel = new JButton(title);
//		jLabel.setFont(new Font("Arial", Font.BOLD, 30));
		JButton btn = new JButton(title);
		ImageIcon icon = createImageIcon(iconPath);
		btn.setBorder(new EmptyBorder(15, 0, 15, 0));
		btn.setBackground(Color.GRAY);
		btn.setIcon(icon);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setOpaque(true);
		btn.setFont(new Font("Arial", Font.BOLD, 30));
//		container.add(btn);
		return btn;
	}

	private JPanel makeNavButton(String tilte, String[][] options, String iconPath) {
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());

		JButton btn = createNavItem(tilte, iconPath);

		JPopupMenu popupMenu = new JPopupMenu();

		for (String[] option : options) {
			JMenuItem item = new JMenuItem(option[0]);
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
		case K:
			KhoaUI khoaUI = new KhoaUI();
			children = khoaUI.getLayout();
			break;
		case TKSV:
			TimKiemSVUI timKiemSVUI = new TimKiemSVUI();
			children = timKiemSVUI.getLayout();
			break;
		case HD:
			HopDongUI hopDongUI = new HopDongUI();
			children = hopDongUI.getLayout();
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
		wrapper.removeAll();
		getLayout().revalidate();
		getLayout().repaint();
		jFrame.add(getLayout());
	}

}
