package MostrarImagen;

import javax.swing.*;

import Algoritmo.ImagenPGM;

import java.awt.*;
import java.awt.image.BufferedImage;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Scanner;

public class MostrarImagen {

    // Método para mostrar dos imágenes en la misma ventana con títulos
    public static void displayImages(BufferedImage image1, BufferedImage image2, String title1, String title2) {
        JFrame frame = new JFrame("PGM Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Usar GridLayout para colocar las imágenes una al lado de la otra
        frame.setLayout(new GridLayout(1, 2));

        // Panel para la primera imagen con título
        JPanel panel1 = new JPanel(new BorderLayout());
        JLabel titleLabel1 = new JLabel(title1, JLabel.CENTER);
        titleLabel1.setFont(new Font("SansSerif", Font.BOLD, 24)); // Establecer el tamaño de la fuente
        panel1.add(titleLabel1, BorderLayout.NORTH);
        panel1.add(new ImagenPanel(image1), BorderLayout.CENTER);
        panel1.setOpaque(false); // Hacer el panel transparente

        // Panel para la segunda imagen con título
        JPanel panel2 = new JPanel(new BorderLayout());
        JLabel titleLabel2 = new JLabel(title2, JLabel.CENTER);
        titleLabel2.setFont(new Font("SansSerif", Font.BOLD, 24)); // Establecer el tamaño de la fuente
        panel2.add(titleLabel2, BorderLayout.NORTH);
        panel2.add(new ImagenPanel(image2), BorderLayout.CENTER);
        panel2.setOpaque(false); // Hacer el panel transparente

        frame.add(panel1);
        frame.add(panel2);

        frame.pack();
        frame.setVisible(true);
    }

	public static void MostrarComparacion(ImagenPGM imagen) {
		BufferedImage imagenOrignal = imagen.getBufferedImageOriginal();
		BufferedImage imagenInvertidaMaximoArchivo = imagen.getBufferedImageInvertidaMaximoArchivo();
		BufferedImage imagenInvertidaMaximoReal = imagen.getBufferedImageInvertidaMaximoReal();

		// Mostrar las dos imágenes en la misma ventana con títulos
		MostrarImagen.displayImages(imagenOrignal, imagenInvertidaMaximoArchivo, "Imagen Original",
				"Imagen InvertidaMaximoArchivo");
		MostrarImagen.displayImages(imagenOrignal, imagenInvertidaMaximoReal, "Imagen Original",
				"Imagen InvertidaMaximoReal");
		MostrarImagen.displayImages(imagenInvertidaMaximoArchivo, imagenInvertidaMaximoReal,
				"Imagen InvertidaMaximoArchivo", "Imagen InvertidaMaximoReal");

	}
    
    
}