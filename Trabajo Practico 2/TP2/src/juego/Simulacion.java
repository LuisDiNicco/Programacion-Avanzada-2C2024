package juego;

import pueblo.Pueblo;
import pueblo.TipoDePueblo;
import archivo.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import algoritmos.*;

public class Simulacion {
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
                
        //Dijkstra dijkstra = new Dijkstra(mapa, puebloInicio);
        Dijkstra dijkstra = new Dijkstra(mapa.getGrafo(), puebloInicio);
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
        

        Pueblo miPueblo = mapa.getPueblo(puebloInicio-1);

        int proximo = puebloInicio;

        logWriter.escribirSeparador();
        logWriter.escribirTextoIncioBatalla(proximo);
        
        System.out.println("Comienza la aventura!");
        System.out.println("Partimos desde el pueblo: " + proximo + "\n");

        int kmRecorrido = 0;
        double tiempoRecorrido = 0;
        double tiempoPrevio = 0;
        int kmAnterior=0;
        
        while (miPueblo.hayEjercito() && !pila.isEmpty()) {                       
        	proximo = pila.pop();        	

            Pueblo puebloAVisitar = mapa.getPueblo(proximo);
        	
        	System.out.println("-----------------------------------------------------------------------");
        	System.out.println("\t\t\t\tDia: " + convertirDiasAHoras(tiempoRecorrido/24.0));
            System.out.println("-----------------------------------------------------------------------");
            kmRecorrido += distancias[proximo] - distancias[predecesores[proximo]];
            kmRecorrido=(kmRecorrido-kmAnterior);
            tiempoRecorrido+=(double)kmRecorrido/10*24;
            
        	logWriter.escribirSeparador();
        	logWriter.escribirTextoPartida(proximo);

            // Imprimir en consola
            System.out.println("Partiendo hacia el pueblo: " + (proximo+1) + "...");
            
            //tiempoPrevio=tiempoRecorrido;
            while((tiempoRecorrido - tiempoPrevio) >= 24) {
            	tiempoPrevio+=24;
            	System.out.println("-----------------------------------------------------------------------");
                System.out.println("\t\t\t\tDia: " + convertirDiasAHoras(tiempoPrevio/24.0));
                System.out.println("Seguimos en camino hacia el pueblo: " + (proximo+1));
                System.out.println("-----------------------------------------------------------------------");
            }
                        
           // System.out.println("Luego de " + convertirHorasADias(tiempoRecorrido) + " llegamos al pueblo: " + (proximo+1));

            TipoDePueblo tipoPuebloAVisitar = puebloAVisitar.getTipoDePueblo();
            if (tipoPuebloAVisitar.equals(TipoDePueblo.ENEMIGO)) {

                logWriter.escribirTextoBatallaConPuebloEnemigo(puebloAVisitar.getNumeroPueblo());

                System.out.println("Oh no! Este pueblo resultó ser hostil :( Hay que pararse de manos!");
                System.out.println("-----------------------------------------------------------------------");

                Batalla.batalla(miPueblo, puebloAVisitar);
                System.out.println("La batalla ha durado todo un dia");

            } else if (tipoPuebloAVisitar.equals(TipoDePueblo.ALIADO)) {

                System.out.println("Hemos descansado todo un dia en esta bella ciudad");
                miPueblo.agregarAliados(puebloAVisitar);
                miPueblo.getEjercito().descansar();

                logWriter.escribirTextoLLegadaPuebloAliado();
            }
            
            tiempoRecorrido += 24;
            tiempoPrevio = tiempoRecorrido;
            kmAnterior=kmRecorrido;
        }

        if (miPueblo.hayEjercito()) {
            System.out.println("El ejército del pueblo " + (miPueblo.getNumeroPueblo()+1) + " llegó a destino.");
            System.out.println("Luego de " + convertirDiasAHoras(tiempoRecorrido/24.0) + " llegamos al destino");
        } else {
            System.out.println("\n-----------------------------------------------------------------------");
            System.out.println("\t\t\tLa aventura duró " + convertirDiasAHoras(tiempoRecorrido/24.0));
            System.out.println("-----------------------------------------------------------------------");

        	
            System.out.println("El ejército de " + (miPueblo.getNumeroPueblo()+1) + " no llegó a destino.");
            System.out.println("Siguiendo la ruta mas corta no fue posible llegar a destino");
            System.out.println("Buscaremos una ruta alternativa...");
            
            //dijkstra.calcularSegundaDistancia(distancias, puebloFin-1);
            
            
            boolean caminoExitoso=false;
            int [][]grafo= mapa.getGrafo();
            int N = 2;
            
            SolucionAlternativa solucionaAlternativa = new SolucionAlternativa(grafo);
            List<Camino> listaCaminos = new LinkedList<Camino>();

            while(caminoExitoso == false) {

            	listaCaminos=solucionaAlternativa.encontrarCaminoNMinimo(puebloInicio-1, puebloFin-1, N);
            	
            	if(listaCaminos.size() < N) {
					System.out.println("No fue posible encontrar un camino alternativo que nos permita llegar al destino.");
					System.out.println("En cada uno de los caminos posibles mueren en batalla.");
					System.out.println("Les falto danonino de chicos, muchachos");
					 break;
            	}
            	caminoExitoso=simularBatalla(listaCaminos,N, mapa);
            	N++;
            }
        }
        System.out.println("\n-----------------------------------------------------------------------");
        System.out.println("\t\t\tFin de la aventura");
        System.out.println("-----------------------------------------------------------------------");

        logWriter.cerrar();
    }
	
	private boolean simularBatalla(List<Camino> listaCaminos, int N, Mapa mapa) {

        Camino camino = listaCaminos.get(N-1);
        List<Integer> listaNodos = camino.getNodos();

        Pueblo miPueblo = mapa.getPueblo(puebloInicio-1);

        int proximo = puebloInicio;

        while (miPueblo.hayEjercito() && !listaNodos.isEmpty()) {                       
        	proximo = listaNodos.removeFirst();
        	
            Pueblo puebloAVisitar = mapa.getPueblo(proximo);

            TipoDePueblo tipoPuebloAVisitar = puebloAVisitar.getTipoDePueblo();
            if (tipoPuebloAVisitar.equals(TipoDePueblo.ENEMIGO)) {
                Batalla.batalla(miPueblo, puebloAVisitar);

            } else if (tipoPuebloAVisitar.equals(TipoDePueblo.ALIADO)) {

                miPueblo.agregarAliados(puebloAVisitar);
                miPueblo.getEjercito().descansar();
            }
            
        }

        if (miPueblo.hayEjercito()) {
            return true;
        } else {
           return false;
        }
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
		if (horasRestantes >= 0) {
			if (!resultado.isEmpty()) {
				resultado += " y ";
			}
			resultado += horasRestantes + (horasRestantes == 1 ? " hora" : " horas");
		}

		return resultado;
	}

    public static String convertirDiasAHoras(double dias) {
        int diasEnteros = (int) dias; // Parte entera en días
        double horasDecimales = (dias - diasEnteros) * 24;
        int horasEnteras = (int) Math.round(horasDecimales); // Redondear horas

        // Construcción de la cadena resultante
        String resultado = "";

        if (diasEnteros >= 0) {
            resultado += diasEnteros + ((diasEnteros == 1 || diasEnteros == 0)  ? " día " : " días "); // Singular o plural para días
        }

        if (horasEnteras >= 0) {
            if (diasEnteros > 0) {
                resultado += "y ";
            }
            resultado += horasEnteras + (horasEnteras == 1  ? " hora " : " horas "); // Singular o plural para horas
        }

        return resultado;
    }
}