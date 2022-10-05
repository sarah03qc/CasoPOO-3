package casoPOOTres;

import java.io.IOException;
import org.json.simple.parser.ParseException;


public class Main {

	public static void main(String[] args) throws IOException, ParseException, InterruptedException {
		
		PlantLife vida = new PlantLife(650); //180 seria 3 min por ejemplo
		vida.start();	
	}

}
