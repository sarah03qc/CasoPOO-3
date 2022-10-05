package casoPOOTres;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
 
//despliega una imagen a traves de un link


public class URLImagen {
	
	public String direcc;

    public URLImagen(final String link, final JFrame frame, final JLabel label, Planta plant) {
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
                    
                    frame.setSize(new Dimension(950, 800));
                  
                    label.setIcon(icon);
                    
                   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                   
                    frame.getContentPane().add(label);
    
                    frame.setLocation(250, 50); 
                    frame.setVisible(true);
                    
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            
            }
    });
    }   
}