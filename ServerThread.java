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

			// 测试
			System.out.println("正在读取图像");
			// 测试
			if (socket.getInputStream() == null) {
				System.out.println("输出流为空！");
			}
			// 测试
			if (socket == null) {
				System.out.println("socket为空1");
			}
			// 测试
			if (socket == null) {
				System.out.println("socket为空2");
			}
			// 测试
			if (image == null) {
				System.out.println("图像为空");
			}
			// 测试
			if (socket.getInputStream() == null) {
				System.out.println("输入流为空！");
			}
			// 测试
			System.out.println("成功收到图像");
			if (Server_Frame.isMonitor == true) {
				// size数量，往第1个按钮写图片
				Server_Frame.bt[flag - 1]
						.setIcon(new ImageIcon(image.getScaledInstance(290, 180, Image.SCALE_AREA_AVERAGING)));
				Server_Frame.bt[flag - 1].setText(ip+"   "+flag+"号机");
				Server_Frame.bt[flag - 1].setIconTextGap(10);
				/*
				 * 显示查看那个按钮（电脑）的图像
				 */
				if (Server_Frame.clickedNum == flag) {

					Server_Frame.bt[flag - 1].setIcon(new ImageIcon(image));

					NewWatchFrame.label.setIcon(Server_Frame.bt[flag - 1].getIcon());
					/*
					 * 这里有一个小小的bug,那个如果点击第一个时会出现被放大了
					 */
				}
			}

			// 测试
			System.out.println("成功写入按钮");
			socket.close();
			System.out.println("3、"+Thread.currentThread().isAlive());
			System.out.println("4、"+socket.isClosed());
			System.out.println("5、"+socket.isConnected());//关闭socket该方法并不会返回false
		} catch (Exception e) {
			Server.count++;
			Server_Frame.getTextArea03().append(GenerateDate.getDate() + flag+"号机已下线"+"\n");
			Server_Frame.getTextArea03().append(GenerateDate.getDate() + Server.count +"\n");
			
			
			System.out.println("服务器已关闭!");
		}
	}
}
