package casoPOOTres;

import java.awt.Label;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Planta {
	
	public String type;
	
	//los urls serian los siguientes, los numeros significan la etapa de vida
	public String urlbad1;
	public String urlgood1;
	public String urlmed1;
	
	public String urlbad2;
	public String urlgood2;
	public String urlmed2;
	
	public String urlbad3;
	public String urlgood3;
	public String urlmed3;
	
	public int lifepoints = 100;
	public int stage = 1;
	public int waterpoints = 10;
	public int waterminreq;
	public int watermaxreq;
	public int sunpoints;
	public int sunminreq;
	public int sunmaxreq;
	public int fertilizerpoints = 10;
	public int fertilminreq;
	public int fertilmaxreq;
	public int lifeexpectancy;
	
	public boolean createAnother;
	
	public Planta() throws IOException, ParseException {
		
		LeerJson lector = new LeerJson();
		JSONObject planta = lector.read("Plant");

		waterminreq = ((Long) planta.get("WaterReqMin")).intValue();  //asignamos a las variables el valor del json
		watermaxreq = ((Long) planta.get("WaterReqMax")).intValue();
		sunminreq = ((Long) planta.get("SunReqMin")).intValue();
		sunmaxreq = ((Long) planta.get("SunReqMax")).intValue();
		fertilminreq = ((Long) planta.get("FertilizerReqMin")).intValue();
		fertilmaxreq = ((Long) planta.get("FertilizerReqMax")).intValue();
		
		lifeexpectancy = ((Long) planta.get("lifeExpectancy")).intValue();  //se mide en meses, al igual que la edad
		
		type = (String) planta.get("Type");
		
		//tomar las urls del json, dependen del estado y etapa de la planta
		urlbad1 = (String) planta.get("urlBad1");
		urlgood1 = (String) planta.get("urlGood1");
		urlmed1 = (String) planta.get("urlMed1");
		
		urlbad2 = (String) planta.get("urlBad2");
		urlgood2 = (String) planta.get("urlGood2");
		urlmed2 = (String) planta.get("urlMed2");
		
		urlbad3 = (String) planta.get("urlBad3");
		urlgood3 = (String) planta.get("urlGood3");
		urlmed3 = (String) planta.get("urlMed3");
	}
	
	public void abonar(int cantidad) {
		//metodo para abonar
		fertilizerpoints = fertilizerpoints + cantidad;  //se le agrega
		if(fertilizerpoints <= fertilmaxreq && fertilizerpoints >= fertilminreq) {
			//revisar que este en el rango adecuado
			System.out.println("Fertilizante agregado, ahora en: " + fertilizerpoints);
			if(lifepoints <= 95) {
				lifepoints = lifepoints + 5;  //agregamos vida
			}
			
		} else {
			//se paso, o le falta, esto afecta la planta
			lifepoints = lifepoints - 2;  //se decrementan los puntos de vida
			System.out.println("Fertilizante agregado, ahora en: " + fertilizerpoints);
			System.out.println("Mala decision, -2 puntos de vida: " + lifepoints);
		}
	}
	
	public void regar(int cantidad) {
		//metodo para regar
		waterpoints = waterpoints + cantidad;  //se le agrega
		if(waterpoints <= watermaxreq && waterpoints >= waterminreq) {
			//revisar que este en el rango adecuado
			System.out.println("Planta regada, ahora en: " + waterpoints);
			if(lifepoints <= 92) {
				lifepoints = lifepoints + 8;  //agregamos vida
			}
			
		} else {
			//se paso, o le falta, esto afecta la planta
			lifepoints = lifepoints - 3;  //se decrementan los puntos de vida
			System.out.println("Planta regada, ahora en: " + waterpoints);
			System.out.println("Mala decision, -3 puntos de vida: " + lifepoints);
		}
	}
	
	@SuppressWarnings("unused")
	public void crecer(JFrame frame, JLabel label) {
		//metodo para crecer una etapa
		if(stage < 3) {
			//significa que aun no esta en la etapa final de su vida, y puede crecer mas sin morir
			stage++;
			System.out.println("Planta ha crecido, ahora en stage: " + stage);
			DisplayCondition mostrar = new DisplayCondition(this, frame, label);
		} else {
			//si ya es igual a 3, o sea su etapa final
			System.out.println("Planta ha completado su ciclo de vida");
			DisplayCondition mostrar = new DisplayCondition(this, frame, label);
			Label gamewon = new Label("GAME WON: ha finalizado el ciclo de vida");  //si se completa el ciclo de vida de la planta 
			//                                                                        con exito, es como ganar el juego
	        frame.add(gamewon);
			this.morir();
		}
	}

	public void morir() {
		//metodo para que una planta muera
		lifepoints = 0;	
		
	}
	
	public void fotosintesis(int sunLevel) {
		//metodo en el que una planta absorve la luz del sol y se alimenta de ella, pero debe ser en cantidades necesarias
		//no mas ni menos
		//este metodo recibe el nivel de sol actual, segun la epoca y reacciona acorde a este  (nivel de sol actual, randomizer de rango)
		if(sunLevel > this.sunmaxreq) {
			//si el nivel de sol sobrepasa el requerido de la planta
			this.sunpoints++;
			this.lifepoints = this.lifepoints - 3;
		} else if(sunLevel < this.sunminreq) {
			if(this.sunpoints >= 1) {
				this.sunpoints--;
			}
			this.lifepoints = this.lifepoints - 3;
		} else {
			//no se sale del min y el max que requiere la planta, entonces
			if(lifepoints <= 99) {
				lifepoints++;  //agregamos vida
			}
		}
	}
	
	public void absorberRain(int rainLevel) {
		//metodo en el que una planta absorve la lluvia y se alimenta de ella, pero debe ser en cantidades necesarias
		//no mas ni menos
		//este metodo recibe el nivel de lluvia actual, segun la epoca y reacciona acorde a este  (nivel de lluvia actual, randomizer de rango)
		if(rainLevel > this.watermaxreq) {
			//si el nivel de lluvia sobrepasa el requerido de la planta
			this.waterpoints++;
			this.lifepoints = this.lifepoints - 3;
		} else if(rainLevel < this.waterminreq) {
			if(this.waterpoints >= 1 ) {
				//para que no de negativo
				this.waterpoints--;
			}
			this.lifepoints = this.lifepoints - 3;
		} else {
			//no se sale del min y el max que requiere la planta, entonces
			if(lifepoints <= 99) {
				lifepoints++;  //agregamos vida
			}
		}
	}
}
