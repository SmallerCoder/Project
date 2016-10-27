package com.server;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.server.control.SendFileThread;
import com.server.util.GetFileInputStream;
import com.server.util.SendFile;
import com.server.util.WriteUserText;

public class Server {

	public static Map<String, Integer> ipMap = Collections.synchronizedMap(new HashMap<String, Integer>());
	public static Map<String, Socket> ipMap1 = Collections.synchronizedMap(new HashMap<String, Socket>());
	/*
	 * 服务器初始化窗口
	 */
	public static Server_Frame sf = null;
	/*
	 * 每个客户机链接对应的Map标识
	 */
	public static int flag = 0;
	public static int count = 0;

	public static boolean fileFlag = false;

	public static void util(Socket socket, String ip) {

		if (ipMap.containsKey(ip)) {

			flag = ipMap.get(ip);// 取出IP对应的序号
			/*
			 * 如果监控功能没有开启，我肯定是不放行的，但是链接依旧保存
			 */
			ServerThread a = new ServerThread(socket, flag,ip);
			System.out.println("1、isAlive" + a.isAlive());
			a.start();
			System.out.println("2、isAlive" + a.isAlive());
		} else {

			ipMap.put(ip, (ipMap.size()) + 1);// 第一台机器对应1

			flag = ipMap.size();// 第一台机器输出1
			/*
			 * 开启日志记录线程
			 */
			new WriteUserText(ip).start();

			ServerThread a = new ServerThread(socket, flag,ip);
			System.out.println("1、isAlive" + a.isAlive());
			a.start();
			System.out.println("2、isAlive" + a.isAlive());
		}
	}

	public static boolean mainListener(ServerSocket server) {
		// 服务器端的Socket
		Socket socket = null;
		try {
			socket = server.accept();

			String ip = socket.getInetAddress().getHostAddress();
			util(socket, ip);
			/*
			 * if (ipMap.size() > 52) {
			 * 
			 * break;// 应该拒绝链接 }
			 */

			// 如果点击了发送文件按钮，则传输图片的功能应该被取消掉
			if (fileFlag/* 按钮被点击 */) {
				server.close();
				return false;
			}
		} catch (IOException e) {
			System.out.println("1");
			e.printStackTrace();
		}
		return true;
	}

	public static void sendFile(ServerSocket server) {
		// 此处要循环监听所有客户端
		Socket socket = null;
		InputStream in = null;
		DataOutputStream os = null;
		System.out.println("count=" + count);
		System.out.println("size=" + ipMap.size());
		/*
		 * 暂时还没有解决in为空的情况，即老师取消发送文件
		 */
		in = GetFileInputStream.getFileInputStream();
		while (true) {
			try {
				socket = server.accept();
				/*
				 * 获取输入流，输出流，告诉客户端我是要发文件
				 */
				os = new DataOutputStream(socket.getOutputStream());
				
				os.writeUTF("file");//到最后在关闭
				
				String ip = socket.getInetAddress().getHostAddress();
				if (ipMap1.containsKey(ip)) {
					in = new FileInputStream(new File(SendFile.fileName));
					System.out.println(4122);
					SendFileThread b = new SendFileThread(socket, in);
					System.out.println(77);
					b.start();
					try {
						b.join();
						count++;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(78);
					System.out.println("count=" + count);
					System.out.println("size=" + ipMap.size());
				} else {
					System.out.println(5453);
					ipMap1.put(ip, socket);
					in = new FileInputStream(new File(SendFile.fileName));
					SendFileThread b = new SendFileThread(socket, in);
					
					System.out.println(777);
					System.out.println(in);
					b.start();
					try {
						b.join();
						count++;
						System.out.println(count);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(7875);
					System.out.println("count=" + count);
					System.out.println("size=" + ipMap.size());
				}
				System.out.println(4552);
			} catch (IOException e1) {
				System.out.println("233");
			}

			if (ipMap.size() == count) {
				System.out.println(12312);
				JOptionPane.showMessageDialog(null, "文件发送完成！");
				count = 0;
				if (in != null)
					try {
						in.close();
					} catch (IOException e) {
						System.out.println(11);
						e.printStackTrace();
					}
				if (os != null)
					try {
						os.close();
					} catch (IOException e) {
						System.out.println(11);
						e.printStackTrace();
					}
				break;
			}
		}
	}

	public static void main(String[] args)  {
		// 创建一个服务器端ServerSocket，端口号为12000
		ServerSocket server;
		try {
			sf = new Server_Frame();
			server = new ServerSocket(12000);
			/*
			 * 采用死循环，不断监听来自客户端的链接
			 */
			while (true) {
				System.out.println(23);
				boolean isTrue = mainListener(server);
				// 已经关闭
				if (!isTrue) {
					System.out.println(1);
					server = new ServerSocket(10000);
					sendFile(server);
					//server.close();
					//因为是本机，所以提示被占用
					fileFlag = false;
					server = new ServerSocket(12000);
				}
				
				/*
				 * if 消息按钮点击了，......
				 */
				
				/*
				 * if 远程操控按钮点击了，......
				 */
			}
		} catch (IOException e) {
			System.out.println(12121);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(121);
//			try {
//				server = new ServerSocket(12000);
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
			e.printStackTrace();
		}
	}
}
