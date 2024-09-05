package Algoritmo;

import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.*;
import org.junit.Test;

public class TestTiempos {

  private ImagenPGM imagenGato;
  private ImagenPGM imagenMontaña;
  private ImagenPGM imagenOla;
  private ImagenPGM imagenPaisaje;
  private ImagenPGM imagenPixelesHasta20;
  private ImagenPGM imagenPixelesDesde240;
  private ImagenPGM imagenBosque;
  private ImagenPGM imagenCiervo;
  private ImagenPGM imagenLuna;
  private ImagenPGM imagenPantera;

  @Test
  public void tiempoGato() {
    this.imagenGato = LeerPGM.leerCabeceraPGM("Entrada//gato.pgm"); 
    LeerPGM.leerImagenPGM(imagenGato);

    // Cuento el tiempo de inverto Maximo archivo

        long tiempoInicio = System.nanoTime(); // Empieza el contador
        this.imagenGato.invertirImagenMaximoArchivo(); // Metodo que mido
        long tiempoFin = System.nanoTime(); // Termina el contador
        long tiempoEjecucionInvertirMaximoArchivo = tiempoFin - tiempoInicio;

        tiempoInicio = System.nanoTime();
        this.imagenGato.invertirImagenMaximoReal();
        tiempoFin = System.nanoTime();
        long tiempoEjecucionInvertirMaximoReal = tiempoFin - tiempoInicio;

        // Mostrar el tiempo de ejecución en la consola
        System.out.println("Nombre del Archivo: gato.pgm");
        System.out.println("Tamaño: " + imagenGato.getCantidadFilas() + " filas x " + imagenGato.getCantidadColumnas() + " columnas.");
        System.out.println("Tiempo de ejecución Invertir Maximo Archivo: " + (tiempoEjecucionInvertirMaximoArchivo / 1_000_000.0) + " milisegundos");
        System.out.println("Tiempo de ejecución Invertir Maximo Real: " + (tiempoEjecucionInvertirMaximoReal / 1_000_000.0) + " milisegundos");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        // Opcional: Agregar una aserción para verificar que el tiempo esté dentro de un límite aceptable
        long limiteTiempo = 10_000_000_000L; // 10 segundos en nanosegundos
        assertTrue("El método es demasiado lento", tiempoEjecucionInvertirMaximoArchivo < limiteTiempo);
  }


  @Test
  public void tiempoMontaña() {
    this.imagenMontaña = LeerPGM.leerCabeceraPGM("Entrada\\Montaña.pgm"); 
    LeerPGM.leerImagenPGM(imagenMontaña);

    // Cuento el tiempo de inverto Maximo archivo

        long tiempoInicio = System.nanoTime(); // Empieza el contador
        this.imagenMontaña.invertirImagenMaximoArchivo(); // Metodo que mido
        long tiempoFin = System.nanoTime(); // Termina el contador
        long tiempoEjecucionInvertirMaximoArchivo = tiempoFin - tiempoInicio;

        tiempoInicio = System.nanoTime();
        this.imagenMontaña.invertirImagenMaximoReal();
        tiempoFin = System.nanoTime();
        long tiempoEjecucionInvertirMaximoReal = tiempoFin - tiempoInicio;

        // Mostrar el tiempo de ejecución en la consola
        System.out.println("Nombre del Archivo: Montaña.pgm");
        System.out.println("Tamaño: " + imagenMontaña.getCantidadFilas() + " filas x " + imagenMontaña.getCantidadColumnas() + " columnas.");
        System.out.println("Tiempo de ejecución Invertir Maximo Archivo: " + (tiempoEjecucionInvertirMaximoArchivo / 1_000_000.0) + " milisegundos");
        System.out.println("Tiempo de ejecución Invertir Maximo Real: " + (tiempoEjecucionInvertirMaximoReal / 1_000_000.0) + " milisegundos");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        // Opcional: Agregar una aserción para verificar que el tiempo esté dentro de un límite aceptable
        long limiteTiempo = 10_000_000_000L; // 10 segundos en nanosegundos
        assertTrue("El método es demasiado lento", tiempoEjecucionInvertirMaximoArchivo < limiteTiempo);
  }

  @Test
  public void tiempoOla() {
    this.imagenOla = LeerPGM.leerCabeceraPGM("Entrada\\Ola.pgm"); 
    LeerPGM.leerImagenPGM(imagenOla);

    // Cuento el tiempo de inverto Maximo archivo

        long tiempoInicio = System.nanoTime(); // Empieza el contador
        this.imagenOla.invertirImagenMaximoArchivo(); // Metodo que mido
        long tiempoFin = System.nanoTime(); // Termina el contador
        long tiempoEjecucionInvertirMaximoArchivo = tiempoFin - tiempoInicio;

        tiempoInicio = System.nanoTime();
        this.imagenOla.invertirImagenMaximoReal();
        tiempoFin = System.nanoTime();
        long tiempoEjecucionInvertirMaximoReal = tiempoFin - tiempoInicio;

        // Mostrar el tiempo de ejecución en la consola
        System.out.println("Nombre del Archivo: Ola.pgm");
        System.out.println("Tamaño: " + imagenOla.getCantidadFilas() + " filas x " + imagenOla.getCantidadColumnas() + " columnas.");
        System.out.println("Tiempo de ejecución Invertir Maximo Archivo: " + (tiempoEjecucionInvertirMaximoArchivo / 1_000_000.0) + " milisegundos");
        System.out.println("Tiempo de ejecución Invertir Maximo Real: " + (tiempoEjecucionInvertirMaximoReal / 1_000_000.0) + " milisegundos");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        // Opcional: Agregar una aserción para verificar que el tiempo esté dentro de un límite aceptable
        long limiteTiempo = 10_000_000_000L; // 10 segundos en nanosegundos
        assertTrue("El método es demasiado lento", tiempoEjecucionInvertirMaximoArchivo < limiteTiempo);
  }

  @Test
  public void tiempoPaisaje() {
    this.imagenPaisaje = LeerPGM.leerCabeceraPGM("Entrada\\Paisaje.pgm"); 
    LeerPGM.leerImagenPGM(imagenPaisaje);

    // Cuento el tiempo de inverto Maximo archivo

        long tiempoInicio = System.nanoTime(); // Empieza el contador
        this.imagenPaisaje.invertirImagenMaximoArchivo(); // Metodo que mido
        long tiempoFin = System.nanoTime(); // Termina el contador
        long tiempoEjecucionInvertirMaximoArchivo = tiempoFin - tiempoInicio;

        tiempoInicio = System.nanoTime();
        this.imagenPaisaje.invertirImagenMaximoReal();
        tiempoFin = System.nanoTime();
        long tiempoEjecucionInvertirMaximoReal = tiempoFin - tiempoInicio;

        // Mostrar el tiempo de ejecución en la consola
        System.out.println("Nombre del Archivo: Paisaje.pgm");
        System.out.println("Tamaño: " + imagenPaisaje.getCantidadFilas() + " filas x " + imagenPaisaje.getCantidadColumnas() + " columnas.");
        System.out.println("Tiempo de ejecución Invertir Maximo Archivo: " + (tiempoEjecucionInvertirMaximoArchivo / 1_000_000.0) + " milisegundos");
        System.out.println("Tiempo de ejecución Invertir Maximo Real: " + (tiempoEjecucionInvertirMaximoReal / 1_000_000.0) + " milisegundos");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        // Opcional: Agregar una aserción para verificar que el tiempo esté dentro de un límite aceptable
        long limiteTiempo = 10_000_000_000L; // 10 segundos en nanosegundos
        assertTrue("El método es demasiado lento", tiempoEjecucionInvertirMaximoArchivo < limiteTiempo);
  }

  @Test
  public void tiempoPixelesHasta20() {
    this.imagenPixelesHasta20 = LeerPGM.leerCabeceraPGM("Entrada\\pixelesHasta20.pgm"); 
    LeerPGM.leerImagenPGM(imagenPixelesHasta20);

    // Cuento el tiempo de inverto Maximo archivo

        long tiempoInicio = System.nanoTime(); // Empieza el contador
        this.imagenPixelesHasta20.invertirImagenMaximoArchivo(); // Metodo que mido
        long tiempoFin = System.nanoTime(); // Termina el contador
        long tiempoEjecucionInvertirMaximoArchivo = tiempoFin - tiempoInicio;

        tiempoInicio = System.nanoTime();
        this.imagenPixelesHasta20.invertirImagenMaximoReal();
        tiempoFin = System.nanoTime();
        long tiempoEjecucionInvertirMaximoReal = tiempoFin - tiempoInicio;

        // Mostrar el tiempo de ejecución en la consola
        System.out.println("Nombre del Archivo: PixelesHasta20.pgm");
        System.out.println("Tamaño: " + imagenPixelesHasta20.getCantidadFilas() + " filas x " + imagenPixelesHasta20.getCantidadColumnas() + " columnas.");
        System.out.println("Tiempo de ejecución Invertir Maximo Archivo: " + (tiempoEjecucionInvertirMaximoArchivo / 1_000_000.0) + " milisegundos");
        System.out.println("Tiempo de ejecución Invertir Maximo Real: " + (tiempoEjecucionInvertirMaximoReal / 1_000_000.0) + " milisegundos");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        // Opcional: Agregar una aserción para verificar que el tiempo esté dentro de un límite aceptable
        long limiteTiempo = 10_000_000_000L; // 10 segundos en nanosegundos
        assertTrue("El método es demasiado lento", tiempoEjecucionInvertirMaximoArchivo < limiteTiempo);
  }

  @Test
  public void tiempoPixelesDesde240() {
    this.imagenPixelesDesde240 = LeerPGM.leerCabeceraPGM("Entrada\\pixelesDesde240.pgm"); 
    LeerPGM.leerImagenPGM(imagenPixelesDesde240);

    // Cuento el tiempo de inverto Maximo archivo

        long tiempoInicio = System.nanoTime(); // Empieza el contador
        this.imagenPixelesDesde240.invertirImagenMaximoArchivo(); // Metodo que mido
        long tiempoFin = System.nanoTime(); // Termina el contador
        long tiempoEjecucionInvertirMaximoArchivo = tiempoFin - tiempoInicio;

        tiempoInicio = System.nanoTime();
        this.imagenPixelesDesde240.invertirImagenMaximoReal();
        tiempoFin = System.nanoTime();
        long tiempoEjecucionInvertirMaximoReal = tiempoFin - tiempoInicio;

        // Mostrar el tiempo de ejecución en la consola
        System.out.println("Nombre del Archivo: PixelesDesde240.pgm");
        System.out.println("Tamaño: " + imagenPixelesDesde240.getCantidadFilas() + " filas x " + imagenPixelesDesde240.getCantidadColumnas() + " columnas.");
        System.out.println("Tiempo de ejecución Invertir Maximo Archivo: " + (tiempoEjecucionInvertirMaximoArchivo / 1_000_000.0) + " milisegundos");
        System.out.println("Tiempo de ejecución Invertir Maximo Real: " + (tiempoEjecucionInvertirMaximoReal / 1_000_000.0) + " milisegundos");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        // Opcional: Agregar una aserción para verificar que el tiempo esté dentro de un límite aceptable
        long limiteTiempo = 10_000_000_000L; // 10 segundos en nanosegundos
        assertTrue("El método es demasiado lento", tiempoEjecucionInvertirMaximoArchivo < limiteTiempo);
  }

  @Test
  public void tiempoBosque() {
    this.imagenBosque = LeerPGM.leerCabeceraPGM("Entrada\\Bosque.pgm"); 
    LeerPGM.leerImagenPGM(imagenBosque);

    // Cuento el tiempo de inverto Maximo archivo

        long tiempoInicio = System.nanoTime(); // Empieza el contador
        this.imagenBosque.invertirImagenMaximoArchivo(); // Metodo que mido
        long tiempoFin = System.nanoTime(); // Termina el contador
        long tiempoEjecucionInvertirMaximoArchivo = tiempoFin - tiempoInicio;

        tiempoInicio = System.nanoTime();
        this.imagenBosque.invertirImagenMaximoReal();
        tiempoFin = System.nanoTime();
        long tiempoEjecucionInvertirMaximoReal = tiempoFin - tiempoInicio;

        // Mostrar el tiempo de ejecución en la consola
        System.out.println("Nombre del Archivo: Bosque.pgm");
        System.out.println("Tamaño: " + imagenBosque.getCantidadFilas() + " filas x " + imagenBosque.getCantidadColumnas() + " columnas.");
        System.out.println("Tiempo de ejecución Invertir Maximo Archivo: " + (tiempoEjecucionInvertirMaximoArchivo / 1_000_000.0) + " milisegundos");
        System.out.println("Tiempo de ejecución Invertir Maximo Real: " + (tiempoEjecucionInvertirMaximoReal / 1_000_000.0) + " milisegundos");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        // Opcional: Agregar una aserción para verificar que el tiempo esté dentro de un límite aceptable
        long limiteTiempo = 10_000_000_000L; // 10 segundos en nanosegundos
        assertTrue("El método es demasiado lento", tiempoEjecucionInvertirMaximoArchivo < limiteTiempo);
  }

  @Test
  public void tiempoCiervo() {
    this.imagenCiervo = LeerPGM.leerCabeceraPGM("Entrada\\Ciervo.pgm"); 
    LeerPGM.leerImagenPGM(imagenCiervo);

    // Cuento el tiempo de inverto Maximo archivo

        long tiempoInicio = System.nanoTime(); // Empieza el contador
        this.imagenCiervo.invertirImagenMaximoArchivo(); // Metodo que mido
        long tiempoFin = System.nanoTime(); // Termina el contador
        long tiempoEjecucionInvertirMaximoArchivo = tiempoFin - tiempoInicio;

        tiempoInicio = System.nanoTime();
        this.imagenCiervo.invertirImagenMaximoReal();
        tiempoFin = System.nanoTime();
        long tiempoEjecucionInvertirMaximoReal = tiempoFin - tiempoInicio;

        // Mostrar el tiempo de ejecución en la consola
        System.out.println("Nombre del Archivo: Ciervo.pgm");
        System.out.println("Tamaño: " + imagenCiervo.getCantidadFilas() + " filas x " + imagenCiervo.getCantidadColumnas() + " columnas.");
        System.out.println("Tiempo de ejecución Invertir Maximo Archivo: " + (tiempoEjecucionInvertirMaximoArchivo / 1_000_000.0) + " milisegundos");
        System.out.println("Tiempo de ejecución Invertir Maximo Real: " + (tiempoEjecucionInvertirMaximoReal / 1_000_000.0) + " milisegundos");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        // Opcional: Agregar una aserción para verificar que el tiempo esté dentro de un límite aceptable
        long limiteTiempo = 10_000_000_000L; // 10 segundos en nanosegundos
        assertTrue("El método es demasiado lento", tiempoEjecucionInvertirMaximoArchivo < limiteTiempo);
  }

  @Test
  public void tiempoLuna() {
    this.imagenLuna = LeerPGM.leerCabeceraPGM("Entrada\\Luna.pgm"); 
    LeerPGM.leerImagenPGM(imagenLuna);

    // Cuento el tiempo de inverto Maximo archivo

        long tiempoInicio = System.nanoTime(); // Empieza el contador
        this.imagenLuna.invertirImagenMaximoArchivo(); // Metodo que mido
        long tiempoFin = System.nanoTime(); // Termina el contador
        long tiempoEjecucionInvertirMaximoArchivo = tiempoFin - tiempoInicio;

        tiempoInicio = System.nanoTime();
        this.imagenLuna.invertirImagenMaximoReal();
        tiempoFin = System.nanoTime();
        long tiempoEjecucionInvertirMaximoReal = tiempoFin - tiempoInicio;

        // Mostrar el tiempo de ejecución en la consola
        System.out.println("Nombre del Archivo: Luna.pgm");
        System.out.println("Tamaño: " + imagenLuna.getCantidadFilas() + " filas x " + imagenLuna.getCantidadColumnas() + " columnas.");
        System.out.println("Tiempo de ejecución Invertir Maximo Archivo: " + (tiempoEjecucionInvertirMaximoArchivo / 1_000_000.0) + " milisegundos");
        System.out.println("Tiempo de ejecución Invertir Maximo Real: " + (tiempoEjecucionInvertirMaximoReal / 1_000_000.0) + " milisegundos");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        // Opcional: Agregar una aserción para verificar que el tiempo esté dentro de un límite aceptable
        long limiteTiempo = 10_000_000_000L; // 10 segundos en nanosegundos
        assertTrue("El método es demasiado lento", tiempoEjecucionInvertirMaximoArchivo < limiteTiempo);
  }

  @Test
  public void tiempoPantera() {
    this.imagenPantera = LeerPGM.leerCabeceraPGM("Entrada\\Pantera.pgm"); 
    LeerPGM.leerImagenPGM(imagenPantera);

    // Cuento el tiempo de inverto Maximo archivo

        long tiempoInicio = System.nanoTime(); // Empieza el contador
        this.imagenPantera.invertirImagenMaximoArchivo(); // Metodo que mido
        long tiempoFin = System.nanoTime(); // Termina el contador
        long tiempoEjecucionInvertirMaximoArchivo = tiempoFin - tiempoInicio;

        tiempoInicio = System.nanoTime();
        this.imagenPantera.invertirImagenMaximoReal();
        tiempoFin = System.nanoTime();
        long tiempoEjecucionInvertirMaximoReal = tiempoFin - tiempoInicio;

        // Mostrar el tiempo de ejecución en la consola
        System.out.println("Nombre del Archivo: Pantera.pgm");
        System.out.println("Tamaño: " + imagenPantera.getCantidadFilas() + " filas x " + imagenPantera.getCantidadColumnas() + " columnas.");
        System.out.println("Tiempo de ejecución Invertir Maximo Archivo: " + (tiempoEjecucionInvertirMaximoArchivo / 1_000_000.0) + " milisegundos");
        System.out.println("Tiempo de ejecución Invertir Maximo Real: " + (tiempoEjecucionInvertirMaximoReal / 1_000_000.0) + " milisegundos");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        // Opcional: Agregar una aserción para verificar que el tiempo esté dentro de un límite aceptable
        long limiteTiempo = 10_000_000_000L; // 10 segundos en nanosegundos
        assertTrue("El método es demasiado lento", tiempoEjecucionInvertirMaximoArchivo < limiteTiempo);
  }

}