ackage com.server;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Server_Frame extends JFrame{
	private static final long serialVersionUID = 1L;
	private static JPanel p1=new JPanel(new GridLayout(1, 10)); //定义一个中间容器，存放顶部9个按钮+1个标签
	private static JButton[] btn = new JButton[13];//为13个按钮添加消息提示
	private static JLabel label = new JLabel(   "       此处显示时间"); //显示时间的标签
	private static JPanel p2 = new JPanel();
	private static Box box = Box.createVerticalBox();
	private static JPanel p3 = new JPanel(new BorderLayout());
	private static JPanel p4 = new JPanel(new BorderLayout());//存第1个文本框
	private static JTextArea textArea01 = new JTextArea(5,40);//定义第2个，第3个文本框
	private static JTextArea textArea02 = new JTextArea(3,6);
	private static JTextArea textArea03 = new JTextArea(5,55);
	private static Box box01 = Box.createVerticalBox();
	private static Box box02 = Box.createHorizontalBox();
	private static JButton bt01 = new JButton("发送至全体成员");//定义两个放按钮
	private static JButton bt02 = new JButton("发送");
	private static JPanel p5 = new JPanel();
	private static JPanel p6 = new JPanel(new GridLayout());
	private static String[] message = new String[]{
			"视频录像","","",
			"锁住客户机","","",
			"","","",
			"","","","设置"
	};//设置按钮提示信息

	public static JButton[] bt = new JButton[16];
	//初始化窗口函数
	public Server_Frame() {
		//设置窗体标题
		this.setTitle("远程桌面监控系统");
		//设置窗体大小
		this.setSize(1331,736);
		/*
		 * 设置窗口处于屏幕正中间
		 */
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		//获取屏幕设备的宽度
		int width = (int) d.getWidth();//1366
		//获取屏幕设备的高度
		int height = (int) d.getHeight();//768
		//设置窗体处于正中间
		this.setLocation((width-1331)/2, (height-736)/2);
		//分别创建9个上标题按钮
		for (int i = 0; i <9; i++) {
			btn[i] = new JButton(new ImageIcon(this.getClass().getResource("/images/"+i+".png")));
			p1.add(btn[i]);
			btn[i].setToolTipText(message[i]);
		}
		p1.add(label);
		p2.setBorder(new TitledBorder(new EtchedBorder(),"用户列表"));
		/*
		 * 创建左边的4个按钮
		 */
		for (int i = 9; i < btn.length; i++) {
			btn[i] = new JButton(new ImageIcon(this.getClass().getResource("/images/"+i+".png")));
			box.add(Box.createHorizontalStrut(1));
			box.add(btn[i]);
			btn[i].setToolTipText(message[i]);
		}
		p3.add(p2,BorderLayout.CENTER);
		p3.add(box,BorderLayout.EAST);
		
		p4.setBorder(new TitledBorder(new EtchedBorder(),"信息管理"));
		
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
		 * 设置布局
		 */
		p4.add(js_01,BorderLayout.WEST);
		p4.add(box01,BorderLayout.EAST);

		p5.setBorder(new TitledBorder(new EtchedBorder(),"用户记录"));

		/*
		 * 文本框
		 */
		//设置自动换行、自动换字功能
		textArea03.setLineWrap(true);
		textArea03.setWrapStyleWord(true);
		JScrollPane js_03 = new JScrollPane(textArea03);
		
		p5.add(js_03);
		
		//存第p4,p5
		p6.add(p4);
		p6.add(p5);
		
		/*
		 * 将三个容器添加到JFrame中
		 */
		this.add(p1,BorderLayout.NORTH);
		this.add(p3,BorderLayout.CENTER);
		this.add(p6,BorderLayout.SOUTH);
		
		
		p2.setLayout(new GridLayout(4, 4));
		
		
		for (int i = 0; i < bt.length; i++) {
			bt[i] = new JButton(new ImageIcon(this.getClass().getResource("/images/"+i+".png")));
			p2.add(bt[i]);
		}
		
		//设置窗体显示出来
		this.setVisible(true);
		//设置窗口不能修改大小
		this.setResizable(false);
		//设置可以点击关闭
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void componentListener(){
		/*
		 * 发送全体成员按钮
		 */
		bt01.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label.setText("你好");
			}
		});
		/*
		 * 发送按钮
		 */
		bt02.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
	}

}
