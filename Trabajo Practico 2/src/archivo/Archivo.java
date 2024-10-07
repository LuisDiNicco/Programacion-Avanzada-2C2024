package archivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import juego.Simulacion;
import pueblo.*;
import raza.*;

public class Archivo {
	public static void leerArchivo(String ruta, Simulacion simulacion) {
		File archivo= new File(ruta);
		Scanner sc=null;
		try {
			sc = new Scanner(archivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int numeroDePueblos=sc.nextInt();
		
		Pueblo[] pueblos=new Pueblo[numeroDePueblos];
		
		for(int i=0;i < numeroDePueblos;i++) {
			int totalHabitantes=sc.nextInt();
			String[] razas=sc.nextLine().split(" ");
			TipoDePueblo tipo= TipoDePueblo.valueOf(razas[1].toUpperCase());
			
			Raza raza= null;
			if(razas[0].equals("Wrives")){
				raza = new Wrives();
			}else if(razas[0].equals("Reralopes")) {
				raza = new Reralopes();				
			}else if(razas[0].equals("Radaiteran")) {
				raza = new Radaiteran();				
			}else if(razas[0].equals("Nortaichian")) {
				raza = new Nortaichian();				
			}
			
			pueblos[i]= new Pueblo(totalHabitantes,raza,tipo);
		}
		
		simulacion.setPueblos(pueblos);
		
		String[] camino=sc.nextLine().split("->");
		int puebloInicio= Integer.valueOf(camino[0]);
		int puebloFin= Integer.valueOf(camino[1]);
		
		simulacion.setPuebloFin(puebloFin);
		simulacion.setPuebloInicio(puebloInicio);
		
		int[][] grafo= new int[numeroDePueblos][numeroDePueblos];
		
		for(int i=0;i < grafo.length;i++) {
			for(int j=0;j < grafo[i].length;j++) {
				grafo[i][j]=Integer.MAX_VALUE;
			}
		}
		
		while(sc.hasNext()) {
			int inicio = sc.nextInt();
			int fin = sc.nextInt();
			int distancia=sc.nextInt();
			
			grafo[inicio][fin]=distancia;
			grafo[fin][inicio]=distancia;
		}
		
		simulacion.setGrafo(grafo);
	}
}
