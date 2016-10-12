import java.awt.*;
import javax.swing.*;

public class Client extends JFrame { 
   private static final long serialVersionUID = 1L; 
   Dimension screenSize; 

   public Client() { 
       super(); 
       screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
       this.setSize(800, 640); 
       Screen p = new Screen(); 
        Container c = this.getContentPane(); 
        c.setLayout(new BorderLayout()); 
       c.add(p, SwingConstants.CENTER); 
       new Thread(p).start(); 
        SwingUtilities.invokeLater(new Runnable(){ 
            public void run() { 
               setVisible(true); 
           }}); 
   } 

   public static void main(String[] args) { 
       new Client(); 
   } 
} 
   