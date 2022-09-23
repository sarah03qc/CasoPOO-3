package casoPOOTres;


public class DisplayCondition {
	
    //esto toma un entero que contiene el nivel de vida representado en puntos del 0 al 100
	//y una hilera que contiene el tipo de planta.
	//hace que se despliegue una imagen acorde al estado actual
	
	public DisplayCondition(int lifepoints, String tipo) {
		//posibles plantas son tulipan, tomate, rosa, girasol
		
		if(tipo == "tulipan" || tipo == "Tulipan") {
			//en caso de que sea un tulipan
			if(lifepoints >= 70 && lifepoints <= 100) {
				//en caso de que la condicion de la planta sea buena
				//que este entre 100 y 70
				new URLImagen("https://img.freepik.com/vector-gratis/ramo-tulipanes-amarillos-primavera-blanco_87521-2751.jpg?w=2000");
				//se despliega esta imagen
			} else if(lifepoints < 70 && lifepoints >= 30) {
				//en caso de condicion media, entre 70 y 30
				new URLImagen("https://img.freepik.com/fotos-premium/tulipan-secado-florece-primer-sunlights-brillantes-blanco-rosa-petalos-marchitados-flores-tulipan-flores-primavera-marchitandose-florero-concepto-marchito-belleza-flores-que-desvanecen_110194-148.jpg?w=2000");
				//se despliega esta
			} else {
				//de 30 para abajo
				new URLImagen("https://st.focusedcollection.com/14026668/i/650/focused_492291538-stock-photo-studio-shot-wilting-tulips-daffodils.jpg");
				//desplegando imagen en mala condicion
			}
		} else if(tipo == "tomate" || tipo == "Tomate") {
			//si es este tipo
			if(lifepoints >= 70 && lifepoints <= 100) {
				//en caso de que la condicion de la planta sea buena
				//que este entre 100 y 70
				new URLImagen("https://www.hola.com/imagenes/decoracion/20200813173533/plantas-tomate-cultivo-cuidados-huerto-mc/0-855-565/cultivar-plantas-tomateras-m.jpg");
				//se despliega esta imagen
			} else if(lifepoints < 70 && lifepoints >= 30) {
				//en caso de condicion media, entre 70 y 30
				new URLImagen("https://i.pinimg.com/736x/8e/89/a9/8e89a9ae18532f41faa01d68b17b1925--torna.jpg");
				//se despliega esta
			} else {
				//de 30 para abajo
				new URLImagen("https://agriculturers.com/wp-content/uploads/2016/07/tomate-podrido.jpg");
				//desplegando imagen en mala condicion
			}
		} else if(tipo == "rosa" || tipo == "Rosa") {
			//si es este tipo
			if(lifepoints >= 70 && lifepoints <= 100) {
				//en caso de que la condicion de la planta sea buena
				//que este entre 100 y 70
				new URLImagen("https://media.istockphoto.com/photos/pink-roses-in-a-black-flower-pot-on-white-background-picture-id183424956?k=20&m=183424956&s=612x612&w=0&h=znLBBJE5piZGgeIu38mEA5pVoLD_79bugO7ovJ7AtPk=");
				//se despliega esta imagen
			} else if(lifepoints < 70 && lifepoints >= 30) {
				//en caso de condicion media, entre 70 y 30
				new URLImagen("https://previews.123rf.com/images/monsterchef/monsterchef1606/monsterchef160600509/59124360-todav%C3%ADa-vida-de-las-rosas-se-marchitan-amor-perdido-.jpg");
				//se despliega esta
			} else {
				//de 30 para abajo
				new URLImagen("https://us.123rf.com/450wm/nikom1234/nikom12341505/nikom1234150500007/39654766-marchita-rosa-en-blanco-blackground-mano.jpg?ver=6");
				//desplegando imagen en mala condicion
			}
		} else if(tipo == "girasol" || tipo == "Girasol") {
			//si es este tipo
			if(lifepoints >= 70 && lifepoints <= 100) {
				//en caso de que la condicion de la planta sea buena
				//que este entre 100 y 70
				new URLImagen("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/A_sunflower.jpg/250px-A_sunflower.jpg");
				//se despliega esta imagen
			} else if(lifepoints < 70 && lifepoints >= 30) {
				//en caso de condicion media, entre 70 y 30
				new URLImagen("http://photos.demandstudios.com/getty/article/88/90/86495475.jpg");
				//se despliega esta
			} else {
				//de 30 para abajo
				new URLImagen("https://previews.123rf.com/images/lalalulustock/lalalulustock1710/lalalulustock171000326/87418728-girasol-marchito-presentado-en-oto%C3%B1o-extremo-del-verano.jpg");
				//desplegando imagen en mala condicion
			}
	    
	    }

    }
}
