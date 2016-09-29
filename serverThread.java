package com.server;

import java.awt.Image;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class ServerThread extends Thread {

	public Socket socket;

	public Image image;

	public ServerThread(Socket socket) {
		try {
			this.socket = socket;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
					image = ImageIO.read(socket.getInputStream()).getScaledInstance(300, 160, Image.SCALE_AREA_AVERAGING);
					if(image!=null){
						System.out.println("不空");
					}else{
						System.out.println("空");
					}
					
					if(socket.getInputStream()!=null){
						
					}else{
						System.out.println("a");
					}
					System.out.println("成功收到图像");
					 Server_Frame.bt[1].setIcon(new ImageIcon(image));
					 System.out.println("成功写入按钮");
					socket.close();
		} catch (IOException e) {
			System.out.println("服务器已关闭！");
		}

	}

}
