package simuladorCaminos;

import java.util.*;
import raza.*;
import juego.*;
import algoritmos.*;
import pueblo.*;
/*
public class SimuladorDeCamino {
    private Mapa mapa;
    private Pueblo origen;
    private Pueblo destino;
    private Map<Pueblo, Map<Pueblo, Boolean>> resultadosBatalla; // Memoización de batallas
    private Map<Pueblo, List<List<Pueblo>>> caminosPosibles; // Memoización de caminos posibles

    public SimuladorDeCamino(Mapa mapa, Pueblo origen, Pueblo destino) {
        this.mapa = mapa;
        this.origen = origen;
        this.destino = destino;
        this.resultadosBatalla = new HashMap<>();
        this.caminosPosibles = new HashMap<>();
    }

    public void iniciarSimulacion() {
        List<Pueblo> ruta = new ArrayList<>();
        ruta.add(origen);
        explorarCaminos(origen, ruta);
    }

    private void explorarCaminos(Pueblo actual, List<Pueblo> caminoActual) {
        if (caminosPosibles.containsKey(actual)) {
            System.out.println("Camino pre-calculado reutilizado para el pueblo " + actual.getNumeroPueblo());
            return;
        }

        List<List<Pueblo>> caminosDesdeActual = new ArrayList<>();

        // Si llegamos al destino
        if (actual.equals(destino)) {
            caminosDesdeActual.add(new ArrayList<>(caminoActual));
            caminosPosibles.put(actual, caminosDesdeActual);
            return;
        }

        for (Pueblo siguiente : mapa.obtenerPueblosConectados(actual)) {
            if (!caminoActual.contains(siguiente)) {
                List<Pueblo> nuevoCamino = new ArrayList<>(caminoActual);
                nuevoCamino.add(siguiente);

                if (siguiente.getTipoDePueblo() == TipoDePueblo.ALIADO) {
                    descansarYReforzar(actual, siguiente);
                } else if (siguiente.getTipoDePueblo() == TipoDePueblo.ENEMIGO) {
                    if (!verificarResultadoBatallaMemoizado(actual, siguiente)) {
                        if (!intentarConseguirAliados(actual)) {
                            System.out.println("No se encontraron aliados. Fin de la simulación.");
                            return;
                        }
                    }
                }

                explorarCaminos(siguiente, nuevoCamino);
                caminosDesdeActual.add(nuevoCamino);
            }
        }

        caminosPosibles.put(actual, caminosDesdeActual);
    }

    private boolean verificarResultadoBatallaMemoizado(Pueblo aliados, Pueblo enemigos) {
        if (resultadosBatalla.containsKey(aliados) && resultadosBatalla.get(aliados).containsKey(enemigos)) {
            return resultadosBatalla.get(aliados).get(enemigos);
        }

        boolean resultado = simularBatalla(aliados, enemigos);
        resultadosBatalla.computeIfAbsent(aliados, k -> new HashMap<>()).put(enemigos, resultado);
        return resultado;
    }

    private boolean simularBatalla(Pueblo aliados, Pueblo enemigos) {
        System.out.println("Simulando batalla contra pueblo enemigo " + enemigos.getNumeroPueblo());

        Ejercito ejercitoAliadoSimulado = new Ejercito(aliados.getEjercito().getEjercito());
        Ejercito ejercitoEnemigoSimulado = new Ejercito(enemigos.getEjercito().getEjercito());

        Batalla.batalla(ejercitoAliadoSimulado, ejercitoEnemigoSimulado);
        return ejercitoAliadoSimulado.haySoldados();
    }

    private void descansarYReforzar(Pueblo actual, Pueblo aliado) {
        actual.getEjercito().descansar();
        actual.agregarAliados(aliado);
        System.out.println("Ejército descansado y reforzado en el pueblo aliado " + aliado.getNumeroPueblo());
    }

    private boolean intentarConseguirAliados(Pueblo actual) {
        for (Pueblo posibleAliado : mapa.obtenerPueblos()) {
            if (posibleAliado.getTipoDePueblo() == TipoDePueblo.ALIADO && posibleAliado.hayEjercito()) {
                actual.agregarAliados(posibleAliado);
                System.out.println("Aliado encontrado en pueblo " + posibleAliado.getNumeroPueblo() + ". Refuerzos añadidos.");
                return true;
            }
        }
        return false;
    }
}
*/
