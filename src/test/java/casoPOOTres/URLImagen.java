package casoPOOTres;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFrame;
 
//pone una imagen en el canvas a traves de un link


public class URLImagen {
	
	public String direcc;

    public URLImagen(final String link) {
    	//el constructor toma la url como parametro
    	EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    //hay que encargarse de excepciones
                } catch (InstantiationException | ClassNotFoundException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }
                try {
                    String direcc = link;
                    URL url = new URL(direcc);
                    BufferedImage imagen = ImageIO.read(url);
                    Image image = imagen.getScaledInstance(500, 600, Image.SCALE_DEFAULT);  //para darle un tamano predeterminado
                    ImageIcon icon = new ImageIcon(image);
                    JFrame frame = new JFrame();
                    frame.setLayout(new FlowLayout());
                    frame.setSize(1200, 800);
                    JLabel label = new JLabel();
                    label.setIcon(icon);
                    
                    JFrame f = new JFrame();
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    f.getContentPane().add(label);
                  
                    f.pack();
                    f.setLocation(250, 50); 
                    f.setVisible(true);
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            
            }
    });
    }   
}