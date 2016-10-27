package com.server;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.server.control.NewWatchFrame;
import com.server.util.GenerateDate;

public class Server_Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static JPanel p1 = new JPanel(new GridLayout(1, 10)); // ����һ���м���������Ŷ���9����ť+1����ǩ
	private static JButton[] btn = new JButton[13];// Ϊ13����ť�����Ϣ��ʾ
	private static JLabel label = new JLabel("       �˴���ʾʱ��"); // ��ʾʱ��ı�ǩ
	private static JPanel p2 = new JPanel();
	private static Box box = Box.createVerticalBox();
	private static JPanel p3 = new JPanel(new BorderLayout());
	private static JPanel p4 = new JPanel(new BorderLayout());// ���1���ı���
	private static JTextArea textArea01 = new JTextArea(5, 40);// �����2������3���ı���
	private static JTextArea textArea02 = new JTextArea(3, 6);
	private static JTextArea textArea03 = new JTextArea(5, 55);
	private static Box box01 = Box.createVerticalBox();
	private static Box box02 = Box.createHorizontalBox();
	private static JButton bt01 = new JButton("������ȫ���Ա");// ���������Ű�ť
	private static JButton bt02 = new JButton("����");
	private static JPanel p5 = new JPanel();
	private static JPanel p6 = new JPanel(new GridLayout());
	private static String[] message = new String[] { "��Ƶ¼��", "ѧ����Ϣ", "��Ļ���", "��ס�ͻ���", "����ǩ��", "Զ�̿���", "�Ŵ�ѧ����Ļ", "�ļ��ַ�", "��Ϣ����", "�ܿ���", "",
			"", "����" };// ���ð�ť��ʾ��Ϣ
	/*
	 * ���52����ť�����ʶ��Map���� �߳�ͬ����
	 */
	public static Map<JButton, Integer> btMap = Collections.synchronizedMap(new HashMap<JButton, Integer>());
	/*
	 * �����ť�ı�ʶ
	 */
	public static int clickedNum = -1;
	/*
	 * �Ŵ�Ȩ��
	 */
	public static boolean isLarge = false;
	/*
	 * ��ع���Ȩ��
	 */
	public static boolean isMonitor = false;
	/*
	 * getter\setter
	 */
	public static JButton[] bt = new JButton[52];

	private SystemTray tray;

	private TrayIcon trayIcon;

	/**
	 * @return the textArea03
	 */
	public static JTextArea getTextArea03() {
		return textArea03;
	}

	/**
	 * @param textArea03
	 *            the textArea03 to set
	 */
	public static void setTextArea03(JTextArea textArea03) {
		Server_Frame.textArea03 = textArea03;
	}

	// ��ʼ�����ں���
	public Server_Frame() throws Exception {
		// ���ô������
		this.setTitle("Զ��������ϵͳ");
		// ���ô����С
		this.setSize(1331, 736);
		/*
		 * ���ô��ڴ�����Ļ���м�
		 */
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		// ��ȡ��Ļ�豸�Ŀ��
		int width = (int) d.getWidth();// 1366
		// ��ȡ��Ļ�豸�ĸ߶�
		int height = (int) d.getHeight();// 768
		// ���ô��崦�����м�
		this.setLocation((width - 1331) / 2, (height - 736) / 2);

		/*
		 * ���ó������е�ͼ��
		 */

		Toolkit tk = Toolkit.getDefaultToolkit();

		Image img = tk.getImage(this.getClass().getResource("/images/0.png"));

		this.setIconImage(img);

		// ���ô��ڹر��¼�����

		addWindowListener(new WindowAdapter() {

//			public void windowClosing(WindowEvent e) {
//
//				// ������ͼ����ӵ�ϵͳ������ʵ����
//
//				try {
//
//					tray.add(trayIcon);
//
//					System.out.println("�ر�");
//					//��ǰ���ڹر�
//					Server_Frame.this.setVisible(false);
//
//				} catch (AWTException e1) {
//
//					e1.printStackTrace();
//
//				}
//
//			}
//
		});

		/*
		 * ��������
		 */

		if (SystemTray.isSupported()) {

			System.out.println("�� ��tray");

			tray();

		}

		// �ֱ𴴽�9���ϱ��ⰴť
		for (int i = 0; i < 9; i++) {
			btn[i] = new JButton(new Font(message[i],Font.ITALIC,10).getName(), new ImageIcon(this.getClass().getResource("/images/" + i + ".png")));
			btn[i].setVerticalTextPosition(JButton.BOTTOM);
			btn[i].setHorizontalTextPosition(JButton.CENTER);
			btn[i].setIconTextGap(10);
			p1.add(btn[i]);
			btn[i].setToolTipText(message[i]);
		}
		p1.add(label);
		p2.setBorder(new TitledBorder(new EtchedBorder(), "�û��б�"));
		/*
		 * ������ߵ�4����ť
		 */
		for (int i = 9; i < btn.length; i++) {
			btn[i] = new JButton(new ImageIcon(this.getClass().getResource("/images/" + i + ".png")));
			box.add(Box.createHorizontalStrut(1));
			box.add(btn[i]);
			btn[i].setToolTipText(message[i]);
		}

		/*
		 * Ϊp2��ӹ����� Ϊ52����ť�����ʾ
		 */

		p2.setLayout(new GridLayout(13, 2));

		for (int i = 0; i < bt.length; i++) {
			bt[i] = new JButton((i + 1) + "�Ż�", new ImageIcon(this.getClass().getResource("/images/huaji.png")));
			bt[i].setToolTipText((i + 1) + " �� �� �� ");
			bt[i].setVerticalTextPosition(JButton.BOTTOM);
			bt[i].setHorizontalTextPosition(JButton.CENTER);
			bt[i].setIconTextGap(90);
			p2.add(bt[i]);
		}

		/*
		 * Ϊp2��ӹ�����
		 */
		JScrollPane js_p2 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		js_p2.setViewportView(p2);
		p2.setPreferredSize(new Dimension(400, 2800));
		p2.revalidate();

		p3.add(js_p2, BorderLayout.CENTER);
		p3.add(box, BorderLayout.EAST);

		p4.setBorder(new TitledBorder(new EtchedBorder(), "��Ϣ����"));

		textArea01.setLineWrap(true);
		textArea01.setWrapStyleWord(true);
		JScrollPane js_01 = new JScrollPane(textArea01);

		textArea02.setLineWrap(true);
		textArea02.setWrapStyleWord(true);
		JScrollPane js_02 = new JScrollPane(textArea02);

		box02.add(bt01);
		box02.add(Box.createHorizontalStrut(10));
		box02.add(bt02);

		box01.add(js_02);
		box01.add(box02);

		/*
		 * ���ò���
		 */
		p4.add(js_01, BorderLayout.WEST);
		p4.add(box01, BorderLayout.EAST);

		p5.setBorder(new TitledBorder(new EtchedBorder(), "�û���¼"));

		/*
		 * �ı���
		 */
		// �����Զ����С��Զ����ֹ���
		textArea03.setLineWrap(true);
		textArea03.setWrapStyleWord(true);
		textArea03.setEditable(false);
		JScrollPane js_03 = new JScrollPane(textArea03);

		p5.add(js_03);

		// ���p4,p5
		p6.add(p4);
		p6.add(p5);

		/*
		 * ��52����ť����ӳ�� ÿ����ť��Ŷ�Ӧ��1~52
		 */
		for (int i = 0; i < bt.length; i++) {
			btMap.put(bt[i], i + 1);
			bt[i].setEnabled(false);
		}
		/*
		 * ������������ӵ�JFrame��
		 */
		this.add(p1, BorderLayout.NORTH);
		this.add(p3, BorderLayout.CENTER);
		this.add(p6, BorderLayout.SOUTH);
		componentListener();

		// ���ô�����ʾ����
		this.setVisible(true);
		// ���ô��ڲ����޸Ĵ�С
		this.setResizable(false);
		// ���ÿ��Ե���ر�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void tray() {

		// ��ñ�����ϵͳ���̵�ʵ��

		tray = SystemTray.getSystemTray();

		// ��ʾ�������е�ͼ��

		ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/1.png"));

		// ����һ���Ҽ�����ʽ�˵�

		PopupMenu pop = new PopupMenu();
		MenuItem file = new MenuItem("�����ļ�");
		MenuItem screen = new MenuItem("�Ŵ�ѧ����Ļ");
		MenuItem monitor = new MenuItem("�������");
		MenuItem exit = new MenuItem("�رշ���");
		
		pop.add(monitor);
		pop.addSeparator();
		pop.add(file);
		pop.addSeparator();
		pop.add(screen);
		pop.addSeparator();
		pop.add(exit);
		pop.addSeparator();
		
		trayIcon = new TrayIcon(icon.getImage(), "ʵ���ҵ��ӽ�ѧ���ϵͳ������ˣ�", pop);

		// ������Ҫ��û�лᵼ��ͼƬ��ʾ������

		trayIcon.setImageAutoSize(true);

		/*
		 * ������½�ͼ����¼�
		 */
		trayIcon.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {

						trayIcon.setImageAutoSize(true);
						Server_Frame.this.setVisible(true);
						
				}

			}

		});
		
		try {
			tray.add(trayIcon);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				tray.remove(trayIcon);
				System.exit(0);

			}

		});

	}

	public void componentListener() {
		/*
		 * ��С��ʱ���ظô���
		 */

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowIconified(WindowEvent e) {
				super.windowIconified(e);
				Server_Frame.this.setVisible(false);
			}
		});

		this.addWindowFocusListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
		/*
		 * ����ð�ť��ʾ�����˼�ع���
		 */
		btn[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (int i = 0; i < bt.length; i++) {
					bt[i].setEnabled(true);
				}
				isMonitor = true;
				Server_Frame.getTextArea03().append(GenerateDate.getDate() + "��Ļ����ѿ�����\n");
				JOptionPane.showMessageDialog(null, "��Ļ����ѿ�����");
			}
		});
		/*
		 * �漰�ı���Ϊ��isLarge
		 */
		btn[6].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				isLarge = true;
				JOptionPane.showMessageDialog(null, "�Ŵ����ѿ�����");
				Server_Frame.getTextArea03().append(GenerateDate.getDate() + "�Ŵ����ѿ�����\n");
			}
		});

		btn[7].addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * �ȿ�����ع��ܲ��ܷ��ļ�
				 */
				if (!isMonitor) {
					JOptionPane.showMessageDialog(null, "���ȿ�����ع��ܣ�ôô�գ�");
				} else if (Server.ipMap.size() <= 0) {
					JOptionPane.showMessageDialog(null, "�ַ��ļ�����������һ̨ѧ������������");
				} else {
					Server.fileFlag = true;
					Server_Frame.getTextArea03().append("true");
				}

			}
		});
		/*
		 * 52����ť,����Ŵ�������
		 */

		for (int i = 0; i < bt.length; i++) {
			bt[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (isMonitor == true) {
						clickedNum = btMap.get(e.getSource());
						if (isLarge == true) {
							/*
							 * ��ȡ��ť���Դͷ��Ӧ�����1~52
							 */
							/*
							 * ����õ���δ����
							 */
							if (clickedNum > Server.ipMap.size()) {
								Server_Frame.getTextArea03()
										.append(GenerateDate.getDate() + clickedNum + " �� �� �� δ �� �� ��\n");
							} else {
								new NewWatchFrame();
							}
						} else {

							if (clickedNum > Server.ipMap.size()) {
								Server_Frame.getTextArea03()
										.append(GenerateDate.getDate() + clickedNum + " �� �� �� δ �� �� ��\n");
							} else {
								JOptionPane.showMessageDialog(null, "���ȿ����Ŵ��ܣ�ôô�գ�");
								Server_Frame.getTextArea03().append(GenerateDate.getDate() + "���ȿ����Ŵ��ܣ�\n");
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "���ȿ�����ع��ܣ�");
					}
				}
			});
		}
		/*
		 * ����ȫ���Ա��ť
		 */
		bt01.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label.setText("���");
			}
		});
		/*
		 * ���Ͱ�ť
		 */
		bt02.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Server_Frame.getTextArea03().append("����\n");
			}
		});

	}

}
