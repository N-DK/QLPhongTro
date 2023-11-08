package view;

import static constant.Main.CT;
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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
		container.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK),
				new EmptyBorder(0, 15, 0, 700)));
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

		String[][] StudentOptions = { { DSSV, "src//image//list.gif" }, { LH, "src//image//school.gif" },
				{ K, "src//image//department.gif" } };
		String[][] emptyOptions = {};

		container.add(makeNavButton("Sinh Viên", StudentOptions, "src//image//sinh_vien.gif"));
		container.add(makeNavButton(PT, emptyOptions, "src//image//phong_tro.gif"));
		container.add(makeNavButton(CT, emptyOptions, "src//image//chu_tro.gif"));
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

	private JPanel createNavItem(String title, String iconPath) {
		JPanel container = new JPanel();
		container.setBorder(new EmptyBorder(15, 0, 15, 0));

		JLabel jLabel = new JLabel(title);
		jLabel.setFont(new Font("Arial", Font.BOLD, 30));
		ImageIcon icon = createImageIcon(iconPath);
		jLabel.setIcon(icon);
		container.add(jLabel);
		return container;
	}

	private JPanel makeNavButton(String tilte, String[][] options, String iconPath) {
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());

		JPanel btn = createNavItem(tilte, iconPath);

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

		MouseListener action = new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btn.setBackground(null);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				btn.setBackground(new Color(135, 206, 235));
				showPopup(e, popupMenu);
			}
		};

		btn.addMouseListener(action);

		return container;
	}

	private void showPopup(MouseEvent e, JPopupMenu popupMenu) {
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
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
		wrapper.removeAll();
		getLayout().revalidate();
		getLayout().repaint();
		jFrame.add(getLayout());
	}

}
