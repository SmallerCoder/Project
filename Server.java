package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws Exception {
		new Server_Frame();
		// 创建一个服务器端ServerSocket，端口号为12000
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(12000);
		// 服务器端的Socket
		Socket socket = null;
		/*
		 * 采用死循环，不断监听来自客户端的链接
		 */
		while (true) {
			try {
				// 监听客户端的连接
				socket = server.accept();
				// 每得到一个链接，单独启动一个Thread线程处理该链接
				new ServerThread(socket).start();
			} catch (IOException ee) {
				System.err.println("服务器已关闭！");
			}
		}
	}
}
