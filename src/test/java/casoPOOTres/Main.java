package casoPOOTres;


import java.io.FileReader;
import java.io.IOException;
import org.json.simple.parser.ParseException;

import java.util.Iterator;
import java.util.Map;
  
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Main {

	public static void main(String[] args) throws IOException, ParseException, InterruptedException {
		
		
		PlantLife vida = new PlantLife(180); //180 seria 3 min por ejemplo
		
		vida.life();
		
		
			
	}

}
