import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.sun.image.codec.jpeg.JPEGCodec;


public class Server extends Thread {  
    private Dimension screenSize;  
    private Rectangle rectangle;  
    private Robot robot;  
  
    public Server() {  
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();  
        rectangle = new Rectangle(screenSize);// ����ָ��������Ļ����  
        try {  
            robot = new Robot();  
        } catch (Exception e) {  
            e.printStackTrace();  
            System.out.println(e);  
        }  
    }  
  
    public void run() {  
        ZipOutputStream os = null;  
        Socket socket = null;  
        while (true) {  
            try {  
                socket = new Socket("172.25.240.60", 5001);// ����Զ��IP  
                BufferedImage image = robot.createScreenCapture(rectangle);// �����ƶ���Ļ��������  
                os = new ZipOutputStream(socket.getOutputStream());// ����ѹ����  
                // os = new ZipOutputStream(new FileOutputStream("C:/1.zip"));  
  
                os.setLevel(9);  
                os.putNextEntry(new ZipEntry("test.jpg"));  
                JPEGCodec.createJPEGEncoder(os).encode(image);// ͼ������JPEG  
                os.close();  
                Thread.sleep(50);// ÿ��20֡  
            } catch (Exception e) {  
                e.printStackTrace();  
            } finally {  
                if (os != null) {  
                    try {  
                        os.close();  
                    } catch (Exception ioe) {  
                    }  
                }  
                if (socket != null) {  
                    try {  
                        socket.close();  
                    } catch (IOException e) {  
                    }  
                }  
            }  
        }  
    }  
  
    public static void main(String[] args) {  
        new Server().start();  
    }  
}  