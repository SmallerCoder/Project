package com.client;

import java.awt.image.BufferedImage;
import java.net.Socket;

import javax.imageio.ImageIO;

public class Client {
	public static void main(String[] args) throws Exception {
		
		Socket socket = null;
		try {
			while (true) {
				socket = new Socket("172.24.1.110", 12000);
				// 获取屏幕画布
				BufferedImage image = new ShotImage().snapShot();
				/*
				 * 如果屏幕画布获取不到，则报出异常IoException
				 */
				if (image == null){
					System.out.println("图像获取不到！");
					break;
				}
				// 使用支持给定格式的任意 ImageWriter 将一个图像写入 OutputStream。
				ImageIO.write(image, "jpg", socket.getOutputStream());
				System.out.println("写出图像成功");
				 socket.close();
			}
			// new ClientThread(socket).start();
		} catch (Exception ee) {
			System.out.println("你已与服务端断开连接！");
		}
	}
}
