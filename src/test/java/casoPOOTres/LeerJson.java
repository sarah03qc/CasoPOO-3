package casoPOOTres;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class LeerJson {

	public JSONObject read(String leyendo) throws IOException, ParseException {
		JSONParser jsonparser = new JSONParser();
		
		FileReader reader = new FileReader(".\\filejson\\jardin.json");
		
		Object obj = jsonparser.parse(reader);
		
		JSONObject vacjson = (JSONObject)obj;
		
		
		JSONObject toreturn = (JSONObject) vacjson.get(leyendo);
		
		return toreturn;
		
	}
}
