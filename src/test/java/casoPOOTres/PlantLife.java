package casoPOOTres;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import java.util.concurrent.ThreadLocalRandom;

public class PlantLife extends Thread {
	
	public float year;
	public float month;
	public float day;
	public int dayToSleep;
	public float hour;
	public float minute;
	public float second;
	public float milisegundos;
	
	public int precipitacionMin;
	public int precipitacionMax;
	
	public int solMin;
	public int solMax;
	
	public int niveldeSol;
	public int niveldeLluvia;
	
	public PlantLife(int duraYear) {
		//constructor que toma lo que se desea que sea la duracion de un year, en segundos
		
		//estas variables van a representar lo que es UNA unidad de tiempo de cada una de ellas
		year = duraYear;
		month = year / 12;
		day = month / 30;  //asumimos que son siempre 30 dias por mes
		dayToSleep = Math.round(day * 1000); //lo hacemos por mil para usarlo en sleep
		
		
		System.out.println("dia es: " + day * 1000);
		System.out.println("dia redondeado es: " + Math.round(day * 1000));
		System.out.println("va a dormir por: " + (long) (day * 1000));
	}
	
	@Override
    public void run(){}	
	
	@SuppressWarnings("unused")
	public void life() throws InterruptedException, IOException, ParseException {
		
		
		Planta plant = new Planta();  //creamos la planta
		int onestage = plant.lifeexpectancy / 3;  //la expectativa de vida se divide en tres etapas, para saber cuanto es una
		//por cierto, la expectativa de vida se mide en meses
		System.out.println("una etapa es: " + onestage + " mes.");
		
		//requerimos otras variables para medir el tiempo, ya que las anteriores sirven para saber cuanto tiempo representa
		//cada una
		int yyearsPassed = 0;
		int monthcounterforYear = 0;
		int mesesPassed = 0;
		int respaldomesesPassed = 0;
		int daysPassed = 0;
		int respaldoDaysPassed = 0;
		int hoursPassed = 0;
		//double minuto;
		//double segundo;
		
		//variables que guarden la fecha actual, utiles para el cambio de estaciones
		//la fecha predeterminada de inicio de la simulacion es 1ero de marzo de 2022, empieza en primavera
		//se toma de manera estandar que todos los meses van a tener 30 dias.
		int day = 0;  //porque se va a incrementar later
		int mes = 3; 
		int year = 2022;
		String fechaActual;
		String nameSeason;
		
		
		LeerJson lector = new LeerJson();
		JSONObject temporada = lector.read("Season");  //tomamos las seasons para guardarlas
		JSONObject spring = (JSONObject) temporada.get("Spring");  //empieza en mes 3 acaba en mes 6
		JSONObject summer = (JSONObject) temporada.get("Summer");  //de 6 a 9
		JSONObject fall = (JSONObject) temporada.get("Fall");      //de 9 a 12
		JSONObject winter = (JSONObject) temporada.get("Winter");  //de 12 a 3
		
		//String name = (String) spring.get("Name");
		
		//tomamos la fecha de inicio y final de cada una
		int startSummer = ((Long) summer.get("startMonth")).intValue();
		int startSpring = ((Long) spring.get("startMonth")).intValue();
		int startFall = ((Long) fall.get("startMonth")).intValue();
		int startWinter = ((Long) winter.get("startMonth")).intValue();
		
		int endSummer = ((Long) summer.get("endMonth")).intValue();
		int endSpring = ((Long) spring.get("endMonth")).intValue();
		int endFall = ((Long) fall.get("endMonth")).intValue();
		int endWinter = ((Long) winter.get("endMonth")).intValue();
	
		while(plant.lifepoints >= 30) {
			System.out.println("Vida en: " + plant.lifepoints);
			//ciclo que acaba cuando ya esta en una condicion irreparable
			
			DisplayCondition mostrar = new DisplayCondition(plant);  //llamamos metodo en clase que despliega una imagen segun
			//                                                         el estado de la planta
			
			
			//ahora con las variables que  muestran la fecha actual
			if(day < 30) {
				day++;    //se incrementa a un dia mas
			} else {
				//si es 30 ya entonces
				mes++;  //se cambia el mes
				day = 1;  //y ahora es el dia uno del nuevo mes
			}
			if(mes == 1 && day == 1) {
				//sigifica nuevo año
				year++;
			}
			
			fechaActual = day + "/" + mes + "/" + year;
			System.out.println("Fecha actual es: " + fechaActual);
			
			if(startSpring <= mes && mes <= endSpring) {
				//significa primavera, si el mes actual esta entre el de inicio y final de spring
				System.out.println("La temporada actual es primavera.");
				nameSeason = "Spring";
				precipitacionMin = ((Long) spring.get("precipitacionMin")).intValue();
				precipitacionMax = ((Long) spring.get("precipitacionMax")).intValue();
				solMin = ((Long) spring.get("solMin")).intValue();
				solMax = ((Long) spring.get("solMax")).intValue();
			} else if(startSummer <= mes && mes <= endSummer) {
				//significa verano, si el mes actual esta entre el de inicio y final de summer
				System.out.println("La temporada actual es verano.");
				nameSeason = "Summer";
				precipitacionMin = ((Long) summer.get("precipitacionMin")).intValue();
				precipitacionMax = ((Long) summer.get("precipitacionMax")).intValue();
				solMin = ((Long) summer.get("solMin")).intValue();
				solMax = ((Long) summer.get("solMax")).intValue();
			} else if(startFall <= mes && mes <= endFall) {
				//significa otoño, si el mes actual esta entre el de inicio y final de fall
				System.out.println("La temporada actual es otoño.");
				nameSeason = "Fall";
				precipitacionMin = ((Long) fall.get("precipitacionMin")).intValue();
				precipitacionMax = ((Long) fall.get("precipitacionMax")).intValue();
				solMin = ((Long) fall.get("solMin")).intValue();
				solMax = ((Long) fall.get("solMax")).intValue();
			} else {
				//si no son las demas, es winter
				System.out.println("La temporada actual es invierno.");
				nameSeason = "Winter";
				precipitacionMin = ((Long) winter.get("precipitacionMin")).intValue();
				precipitacionMax = ((Long) winter.get("precipitacionMax")).intValue();
				solMin = ((Long) winter.get("solMin")).intValue();
				solMax = ((Long) winter.get("solMax")).intValue();
			}
			
			//los siguientes son randomizers del nivel de intensidad del sol y lluvia
			niveldeLluvia = ThreadLocalRandom.current().nextInt(precipitacionMin, precipitacionMax + 1);
			System.out.println("LLUVIA AHORA ESTA EN: " + niveldeLluvia);
			
			niveldeSol = ThreadLocalRandom.current().nextInt(solMin, solMax + 1);
			System.out.println("SOL AHORA ESTA EN: " + niveldeSol);
			
			plant.fotosintesis(niveldeSol);
			plant.absorverRain(niveldeLluvia);  //con esos niveles de sol y lluvia hacemos que la planta los absorva
			
			//para que el tiempo pase como se desea
			//en cada sleep lo que pasa es un dia
	        Thread.sleep(dayToSleep);   //esto va a repsesentar el paso de un dia
	        plant.abonar(10);
	        plant.regar(10);
	       
	        
	        hoursPassed = hoursPassed + 24;
	        System.out.println("Han pasado " + hoursPassed + " horas.");
			daysPassed = hoursPassed / 24;
			System.out.println("Han pasado " + daysPassed + " dias.");
			respaldoDaysPassed++;
			if(respaldoDaysPassed == 30) {
				mesesPassed++; //se suben los meses
				respaldomesesPassed++;
				monthcounterforYear++; //ese cuenta los meses especificamente para medir years
				//y se reinicia el contador de dias que tiene de proposito contar meses
				respaldoDaysPassed = 0;
				System.out.println("Han pasado " + mesesPassed + " meses.");
			} else {
				System.out.println("Han pasado " + mesesPassed + " meses.");
			}
			if(respaldomesesPassed == onestage) {
				//para saber si ya esta lista para crecer una etapa de la vida
				plant.crecer();
				respaldomesesPassed = 0;
			}
			if(monthcounterforYear == 12) {
				//ver si ha pasado un year desde que nacio la planta
				yyearsPassed++;
				monthcounterforYear = 0;   //se reinicia el contador de este proposito
			}
			System.out.println("Han pasado " + yyearsPassed + " años.");
			
			
			
				
	        plant.lifepoints = plant.lifepoints - 1;               //se va quitando uno por dia, cuando va envejeciendo poco a poco
	        plant.fertilizerpoints = plant.fertilizerpoints - 6;   //por dia se van quitando 6 de abono y agua, que es lo que usa
	                                                               //para su crecimiento
	        plant.waterpoints = plant.waterpoints - 6;   
	        
		}
		
	}
}
