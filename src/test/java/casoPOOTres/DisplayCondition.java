package casoPOOTres;

import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class DisplayCondition {
	
    //se recibe la planta cuya condicion hay que desplegar
	//hace que se despliegue la imagen acorde al estado actual, (que se brinda en el json)
	
	public DisplayCondition(Planta plant, JFrame frame, JLabel label) {
		 
		int lifepoints = plant.lifepoints;
		int etapa = plant.stage;
		
	    if(lifepoints >= 70 && lifepoints <= 100) {
	    	//en caso de que la condicion de la planta sea buena
			//que este entre 100 y 70
			//revisamos la etapa en la que se encuentra
	    	if(etapa == 1) {
	    		new URLImagen(plant.urlgood1, frame, label, plant); 
				//se despliega esta imagen
	    	} else if(etapa == 2) {
	    		new URLImagen(plant.urlgood2, frame, label, plant);
				//se despliega esta imagen
	    	} else {
	    		//si es tres la etapa
	    		new URLImagen(plant.urlgood3, frame, label, plant);
				//se despliega esta imagen
	    	}
		} else if(lifepoints < 70 && lifepoints >= 30) {
			//en caso de condicion media, entre 70 y 30
			//revisamos la etapa en la que se encuentra
	    	if(etapa == 1) {
	    		new URLImagen(plant.urlmed1, frame, label, plant);
				//se despliega esta imagen
	    	} else if(etapa == 2) {
	    		new URLImagen(plant.urlmed2, frame, label, plant);
				//se despliega esta imagen
	    	} else {
	    		//si es tres la etapa
	    		new URLImagen(plant.urlmed3, frame, label, plant);
				//se despliega esta imagen
	    	}
		} else {
			//de 30 para abajo, mala condicion
			//revisamos la etapa en la que se encuentra
	    	if(etapa == 1) {
	    		new URLImagen(plant.urlbad1, frame, label, plant);
				//se despliega esta imagen
	    	} else if(etapa == 2) {
	    		new URLImagen(plant.urlbad2, frame, label, plant);
				//se despliega esta imagen
	    	} else {
	    		//si es tres la etapa
	    		new URLImagen(plant.urlbad3, frame, label, plant);
				//se despliega esta imagen
	    	}
	    	Label gameover = new Label("GAME OVER: tu planta ha muerto");
	        frame.add(gameover);
	    	plant.morir();
	    	
		}
	}
}
		