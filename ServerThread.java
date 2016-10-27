package com.server;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.server.control.NewWatchFrame;
import com.server.util.GenerateDate;

public class ServerThread extends Thread {

	public Socket socket;
	public int flag;
	private String ip;
	public ServerThread(Socket socket, int flag,String ip) {
		try {
			this.socket = socket;
			this.flag = flag;
			this.ip=ip;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void run() {

		System.out.println(this.getName());

		try {
			
			BufferedImage image = ImageIO.read(socket.getInputStream());

			// ����
			System.out.println("���ڶ�ȡͼ��");
			// ����
			if (socket.getInputStream() == null) {
				System.out.println("�����Ϊ�գ�");
			}
			// ����
			if (socket == null) {
				System.out.println("socketΪ��1");
			}
			// ����
			if (socket == null) {
				System.out.println("socketΪ��2");
			}
			// ����
			if (image == null) {
				System.out.println("ͼ��Ϊ��");
			}
			// ����
			if (socket.getInputStream() == null) {
				System.out.println("������Ϊ�գ�");
			}
			// ����
			System.out.println("�ɹ��յ�ͼ��");
			if (Server_Frame.isMonitor == true) {
				// size����������1����ťдͼƬ
				Server_Frame.bt[flag - 1]
						.setIcon(new ImageIcon(image.getScaledInstance(290, 180, Image.SCALE_AREA_AVERAGING)));
				Server_Frame.bt[flag - 1].setText(ip+"   "+flag+"�Ż�");
				Server_Frame.bt[flag - 1].setIconTextGap(10);
				/*
				 * ��ʾ�鿴�Ǹ���ť�����ԣ���ͼ��
				 */
				if (Server_Frame.clickedNum == flag) {

					Server_Frame.bt[flag - 1].setIcon(new ImageIcon(image));

					NewWatchFrame.label.setIcon(Server_Frame.bt[flag - 1].getIcon());
					/*
					 * ������һ��СС��bug,�Ǹ���������һ��ʱ����ֱ��Ŵ���
					 */
				}
			}

			// ����
			System.out.println("�ɹ�д�밴ť");
			socket.close();
			System.out.println("3��"+Thread.currentThread().isAlive());
			System.out.println("4��"+socket.isClosed());
			System.out.println("5��"+socket.isConnected());//�ر�socket�÷��������᷵��false
		} catch (Exception e) {
			Server.count++;
			Server_Frame.getTextArea03().append(GenerateDate.getDate() + flag+"�Ż�������"+"\n");
			Server_Frame.getTextArea03().append(GenerateDate.getDate() + Server.count +"\n");
			
			
			System.out.println("�������ѹر�!");
		}
	}
}
