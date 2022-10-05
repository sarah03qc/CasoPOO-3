package casoPOOTres;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class Simulacion {
	
	public void newplant() throws InterruptedException, IOException, ParseException {
		PlantLife vida = new PlantLife(600); //tiempo predeterminado pero se puede modificar
		//JFrame frame1 = new JFrame("Virtual Garden"); //creamos el frame
		//vida.life(frame1);
		vida.start();       
	}
}
