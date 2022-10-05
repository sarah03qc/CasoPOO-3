package casoPOOTres;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class LeerJson {

	public JSONObject read(String leyendo) throws IOException, ParseException {
		
		Object obj = new JSONParser().parse(new FileReader(".\\src/test/java\\casoPOOTres\\jardin.json"));
		
		JSONObject jo = (JSONObject) obj;
		
		JSONObject toreturn = (JSONObject) jo.get(leyendo);
		
		return toreturn;
		
	}
}
