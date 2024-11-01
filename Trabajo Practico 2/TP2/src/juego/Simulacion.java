package juego;

import pueblo.Pueblo;
import pueblo.TipoDePueblo;
import archivo.*;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import algoritmos.*;

public class Simulacion {
	private Pueblo[] pueblos;
	private int puebloInicio;
	private int puebloFin;

	public Simulacion() {
		super();
	}
	
	// ---------------Setters-------------------//
	
	public void setPuebloInicio(int puebloInicio) {
		this.puebloInicio = puebloInicio;
	}

	public void setPuebloFin(int puebloFin) {
		this.puebloFin = puebloFin;
	}

	public void setPueblos(Pueblo[] pueblos) {
		this.pueblos = pueblos;
	}
	
	// ---------------Metodos-------------------//

	public void iniciarSimulacion(String rutaArchivo, String rutaLog) {
		System.out.println("-----------------------------------------------------------------------");
        System.out.println("\t\t\tInicio de la Aventura");
        System.out.println("-----------------------------------------------------------------------\n");
		// Crear instancia de LogWriter para log
        LogWriter.iniciar(rutaLog);
        LogWriter logWriter = LogWriter.getInstancia();

        // Leer archivo de datos
        Archivo.leerArchivo(rutaArchivo, this);

        //System.out.println("-----------------------------------------------------------------------");
        System.out.println("Inicia una nueva aventura para el pueblo: " + puebloInicio + "...");
        System.out.println("Su destino? ~Reconquistar la tierra de fantasia~");
        System.out.println("Lograrán nuestros héroes llegar al pueblo: " + puebloFin + "?\n");
        //System.out.println("-----------------------------------------------------------------------");

        // Obtener el grafo desde la instancia de Mapa
        Mapa mapa = Mapa.getInstancia();
                
        Dijkstra dijkstra = new Dijkstra(mapa, puebloInicio);
        dijkstra.calcularDistanciaDijkstra();

        int[] distancias = dijkstra.getVectorDistancia();
        if (distancias[puebloFin - 1] == Integer.MAX_VALUE) {
            System.out.println("Es fisicamente imposible llegar desde el pueblo de inicio hasta el destino.");
        	return;
        }
        
        int[] predecesores = dijkstra.getVectorPredecesores();
               
        Stack<Integer> pila = new Stack<>();
        pila.push(puebloFin-1);
        
        //int i = puebloFin - 1;
        int i = predecesores[puebloFin-1];
        while (i != puebloInicio - 1) {
        	pila.push(i);
        	i = predecesores[i];
        }

        Pueblo miPueblo = pueblos[puebloInicio - 1];

        //int proximo = pila.pop();
        int proximo = puebloInicio;

        // Registrar el inicio de la aventura en el log
        logWriter.escribirLog("-----------------------------------------------------------------------");
        logWriter.escribirLog("Comienza la aventura!");
        logWriter.escribirLog("Partimos desde el pueblo: " + proximo);
        
        System.out.println("Comienza la aventura!");
        System.out.println("Partimos desde el pueblo: " + proximo + "\n");

        int tiempoRecorrido = 0;
        int tiempoPrevio = 0;
        
        while (miPueblo.hayEjercito() && !pila.isEmpty()) {                       
        	proximo = pila.pop();
        	//proximo++;
        	Pueblo puebloAVisitar = pueblos[proximo];
        	
        	System.out.println("-----------------------------------------------------------------------");
        	System.out.println("\t\t\t\tDia " + (tiempoPrevio/24));
            System.out.println("-----------------------------------------------------------------------");
        	tiempoRecorrido += distancias[proximo] - distancias[proximo-1];
        	//System.out.println("Tiempo = " + tiempoRecorrido + "/tiempo unico: " + distancias[proximo] + "/pueblo: " + (proximo+1));

            logWriter.escribirLog("-----------------------------------------------------------------------");
            logWriter.escribirLog("Partiendo hacia el pueblo: " + (proximo+1) + "...");

            // Imprimir en consola
            System.out.println("Partiendo hacia el pueblo: " + (proximo+1) + "...");
            
            while((tiempoRecorrido - tiempoPrevio) >= 24) {
            	tiempoPrevio+=24;
            	System.out.println("-----------------------------------------------------------------------");
                System.out.println("\t\t\t\tDia " + (tiempoPrevio/24));
                System.out.println("Seguimos en camino hacia el pueblo: " + (proximo+1));
                System.out.println("-----------------------------------------------------------------------");
            }
                        
            System.out.println("Luego de " + convertirHorasADias(tiempoRecorrido) + " llegamos al pueblo: " + (proximo+1));

            TipoDePueblo tipoPuebloAVisitar = puebloAVisitar.getTipoDePueblo();
            if (tipoPuebloAVisitar.equals(TipoDePueblo.ENEMIGO)) {
                logWriter.escribirLog("Oh no! Este pueblo resultó ser hostil :( Hay que pararse de manos!");
                logWriter.escribirLog("Comienza la batalla en el pueblo: " + puebloAVisitar.getNumeroPueblo());

                System.out.println("Oh no! Este pueblo resultó ser hostil :( Hay que pararse de manos!");
                System.out.println("-----------------------------------------------------------------------");

                Batalla.batalla(miPueblo, puebloAVisitar);
                System.out.println("La batalla ha durado todo un dia");

            } else if (tipoPuebloAVisitar.equals(TipoDePueblo.ALIADO)) {
                logWriter.escribirLog("Qué agradables sujetos, nos dejaron descansar y se sumarán al ejército :)");
                logWriter.escribirLog("El ejército descansa y gana aliados.");

                System.out.println("Hemos descansado todo un dia en esta bella ciudad");
                miPueblo.agregarAliados(puebloAVisitar);
                miPueblo.getEjercito().descansar();
                logWriter.escribirLog("Se sumará la mitad de su ejercito");
            }
            
            tiempoRecorrido += 24;
            tiempoPrevio = tiempoRecorrido;
        }

        if (miPueblo.hayEjercito()) {
            System.out.println("El ejército del pueblo " + miPueblo.getNumeroPueblo() + " llegó a destino.");
            System.out.println("Luego de " + convertirHorasADias(tiempoRecorrido) + " llegamos al destino");
        } else {
            System.out.println("El ejército de " + miPueblo.getNumeroPueblo() + " no llegó a destino.");
            System.out.println("Siguiendo la ruta mas corta no fue posible llegar a destino");
            System.out.println("Buscaremos una ruta alternativa...");
            
            int k = 1; // Inicia con el segundo mejor camino
            boolean caminoExitoso = false; // Variable para indicar si se encontró un camino

            while (!caminoExitoso && k < 10) {
                KCamino kCamino = new KCamino(mapa);
                List<int[]> caminos = kCamino.kEsimoMejorCamino(puebloInicio-1, puebloFin-1, k);
                for (int[] camino : caminos) {
                    System.out.println(Arrays.toString(camino));
                }
                k++;
                System.out.println(k);
                caminoExitoso = caminos.isEmpty();
                

                /*
                if (caminos != null && !caminos.isEmpty()) {
                    int[] caminoAlternativo = caminos.get(0);
                    System.out.println("Encontrado " + k + "º mejor camino con costo: " + caminoAlternativo[0]);
                    
                    
                    // Aquí puedes hacer lo que necesites con el camino alternativo
                    // LOGICA SIMULACION
                    
                    if(mori){
                    	k++
                    }
                    else {
                    	caminoExitoso = true; // Se encontró un camino, se puede salir del bucle
                    }
                    
                }                 
                else {
                    System.out.println("No fue posible encontrar un camino alternativo que nos permita llegar al destino.");
                    System.out.println("En cada uno de los caminos posibles mueren en batalla.");
                    System.out.println("Les falto danonino de chicos, muchachos");
                    
                }*/
                
            }
        }

        System.out.println("\n-----------------------------------------------------------------------");
        System.out.println("\t\t\tFin de la aventura");
        System.out.println("-----------------------------------------------------------------------");

        // Cerrar el archivo de log
        logWriter.cerrar();
    }
	
	public String convertirHorasADias(int horas) {
		int dias = horas / 24;
		int horasRestantes = horas % 24;

		// Construcción de la cadena de resultado usando solo concatenación con +
		String resultado = "";

		// Formato para días
		if (dias > 0) {
			resultado += dias + (dias == 1 ? " día" : " días");
		}

		// Formato para horas
		if (horasRestantes > 0) {
			if (!resultado.isEmpty()) {
				resultado += " y ";
			}
			resultado += horasRestantes + (horasRestantes == 1 ? " hora" : " horas");
		}

		return resultado;
	}
}