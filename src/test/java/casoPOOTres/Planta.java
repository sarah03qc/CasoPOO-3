package casoPOOTres;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Planta {
	
	public String type;
	public String url;
	public int edad = 0;
	public int lifepoints = 100;
	public int stage = 1;
	public int waterpoints;
	public int waterminreq;
	public int watermaxreq;
	public int sunpoints;
	public int sunminreq;
	public int sunmaxreq;
	public int fertilizerpoints = 0;
	public int fertilminreq;
	public int fertilmaxreq;
	
	public Planta(String tipo) throws IOException, ParseException {
		
		//las cantidades requeridas son por mes
		LeerJson lector = new LeerJson();
		JSONObject planta = lector.read("Plant");

		waterminreq = ((Long) planta.get("WaterReqMin")).intValue();  //asignamos a las variables el valor del json
		watermaxreq = ((Long) planta.get("WaterReqMax")).intValue();
		sunminreq = ((Long) planta.get("SunReqMin")).intValue();
		sunmaxreq = ((Long) planta.get("SunReqMax")).intValue();
		fertilminreq = ((Long) planta.get("FertilizerReqMin")).intValue();
		fertilmaxreq = ((Long) planta.get("FertilizerReqMax")).intValue();
		
		//planta.put("Type", tipo);
		//type = tipo;
		//url = (String) planta.get("url");
	}
	
	public void abonar(int cantidad) {
		//metodo para abonar
		fertilizerpoints = fertilizerpoints + cantidad;  //se le agrega
		if(fertilizerpoints <= fertilmaxreq && fertilizerpoints >= fertilminreq) {
			//revisar que este en el rango adecuado
			System.out.println("Fertilizante agregado, ahora en: " + fertilizerpoints);
			lifepoints = lifepoints + 5;  //agregamos vida
		} else {
			//se paso, o le falta, esto afecta la planta
			lifepoints = lifepoints - 5;  //se decrementan los puntos de vida
			System.out.println("Fertilizante agregado, ahora en: " + fertilizerpoints);
			System.out.println("Mala decision, -5 puntos de vida: " + lifepoints);
		}
	}
	
	public void regar(int cantidad) {
		//metodo para regar
		waterpoints = waterpoints + cantidad;  //se le agrega
		if(waterpoints <= watermaxreq && waterpoints >= waterminreq) {
			//revisar que este en el rango adecuado
			System.out.println("Planta regada, ahora en: " + waterpoints);
			lifepoints = lifepoints + 8;  //agregamos vida
		} else {
			//se paso, o le falta, esto afecta la planta
			lifepoints = lifepoints - 10;  //se decrementan los puntos de vida
			System.out.println("Planta regada, ahora en: " + waterpoints);
			System.out.println("Mala decision, -5 puntos de vida: " + lifepoints);
		}
	}
	
	public void crecer() {
		//metodo para crecer una etapa
		if(stage < 3) {
			//significa que aun no esta en la etapa final de su vida, y puede crecer mas sin morir
			stage++;
			System.out.println("Planta ha crecido, ahora en stage: " + stage);
		} else {
			//si ya es igual a 3, o sea su etapa final
			System.out.println("Planta ha completado su ciclo de vida");
			this.morir();
		}
	}

	public void morir() {
		//metodo para que una planta muera
		lifepoints = 0;	
	}
}
