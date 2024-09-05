package Algoritmo;

import java.awt.image.BufferedImage;

public class ImagenPGM {

	private String formato; // "P2" o "P5"
	private String path;
	private int cantidadColumnas;
	private int cantidadFilas;
	private int maximoValorArchivo;
	private int maximoValorReal;
	private int[][] imagenOriginal;
	private int[][] imagenInvertidaMaximoArchivo;
	private int [][] imagenInvertidaMaximoReal;
	private int minimo;
	private BufferedImage bufferedImageOriginal;
	private BufferedImage bufferedImageInvertidaMaximoArchivo;
	private BufferedImage bufferedImageInvertidaMaximoReal;

	public ImagenPGM(String path, String formato, int cantidadColumnas, int cantidadFilas, int maximoValorArchivo, BufferedImage imagen) {
		this.path = path;
		this.formato = formato;
		this.cantidadColumnas = cantidadColumnas;
		this.cantidadFilas = cantidadFilas;
		this.maximoValorArchivo = maximoValorArchivo;
		this.imagenOriginal = new int[cantidadFilas][cantidadColumnas];
		this.imagenInvertidaMaximoArchivo = new int[cantidadFilas][cantidadColumnas];
		this.imagenInvertidaMaximoReal = new int[cantidadFilas][cantidadColumnas];
		this.bufferedImageOriginal = imagen;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	public void setMaximoValorReal(int maximo) {
		this.maximoValorReal = maximo;
	}

	public int getMinimo() {
		return this.minimo;
	}

	public String getFormato() {
		return formato;
	}

	public String getPath() {
		return path;
	}

	public int getCantidadColumnas() {
		return cantidadColumnas;
	}

	public int getCantidadFilas() {
		return cantidadFilas;
	}

	public int getMaximoValorArchivo() {
		return maximoValorArchivo;
	}

	public int getMaximoValorReal() {
		return maximoValorReal;
	}

	// Imagen Original
	public void setImagenOriginal(int i, int j, int valor) {
		this.imagenOriginal[i][j] = valor;
	}

	public int getImagenOriginal(int i, int j) {
		return this.imagenOriginal[i][j];
	}

	// Imagen Invertida Maximo Archivo
	public void setImagenInvertidaMaximoArchivo(int i, int j, int valor) {
		this.imagenInvertidaMaximoArchivo[i][j] = valor;
	}

	public int getImagenInvertidaMaximoArchivo(int i, int j) {
		return this.imagenInvertidaMaximoArchivo[i][j];
	}

	// Imagen invertida Maximo Real
	public void setImagenInvertidaMaximoReal(int i, int j, int valor) {
		this.imagenInvertidaMaximoReal[i][j] = valor;
	}

	public int getImagenInvertidaMaximoReal(int i, int j) {
		return this.imagenInvertidaMaximoReal[i][j];
	}

	// BufferedImage Original
	public BufferedImage getBufferedImageOriginal() {
		return bufferedImageOriginal;
	}

	public void setBufferedImageOriginal(BufferedImage bufferedImageOriginal) {
		this.bufferedImageOriginal = bufferedImageOriginal;
	}

	// BufferedImage Invertida Maximo Archivo
	public BufferedImage getBufferedImageInvertidaMaximoArchivo() {
		return bufferedImageInvertidaMaximoArchivo;
	}

	public void setBufferedImageInvertidaMaximoArchivo(BufferedImage bufferedImageInvertidaMaximoArchivo) {
		this.bufferedImageInvertidaMaximoArchivo = bufferedImageInvertidaMaximoArchivo;
	}

	// BufferedImage Invertida Maximo Real
	public BufferedImage getBufferedImageInvertidaMaximoReal() {
		return bufferedImageInvertidaMaximoReal;
	}

	public void setBufferedImageInvertidaMaximoReal(BufferedImage bufferedImageInvertidaMaximoReal) {
		this.bufferedImageInvertidaMaximoReal = bufferedImageInvertidaMaximoReal;
	}

/*****************************************************************************************/	
									/* Metodos para invertir */
/*****************************************************************************************/
	public void invertirImagenMaximoArchivo() {
		//Para guardar la bufferedImage
		BufferedImage bufferedImage = new BufferedImage(cantidadColumnas, cantidadFilas, BufferedImage.TYPE_BYTE_GRAY); 
		int valorPixel;

		for (int i = 0; i < getCantidadFilas(); i++) {
			for (int j = 0; j < getCantidadColumnas(); j++) {
				setImagenInvertidaMaximoArchivo(i, j, maximoValorArchivo - getImagenOriginal(i, j));

				// aca empieza el buffer para mostrar la imagen
				valorPixel = this.getImagenInvertidaMaximoArchivo(i, j); // obtengo el valor del pixel invertido
				bufferedImage.getRaster().setSample(j, i, 0, valorPixel); // cargo el valor en la buffered image
			}
		}

		//guardo en la imagen la bufferedImage leida
		this.setBufferedImageInvertidaMaximoArchivo(bufferedImage);
	}

	public void invertirImagenMaximoReal() {
		int valorInvertido;
		int valorEscalado;

		//Para guardar la bufferedImage
		BufferedImage bufferedImage = new BufferedImage(cantidadColumnas, cantidadFilas, BufferedImage.TYPE_BYTE_GRAY); 
		int valorPixel;

		for (int i = 0; i < getCantidadFilas(); i++) {
			for (int j = 0; j < getCantidadColumnas(); j++) {
				valorEscalado = maximoValorArchivo/maximoValorReal*getImagenOriginal(i, j);
				valorInvertido = maximoValorArchivo - (valorEscalado);
				setImagenInvertidaMaximoReal(i, j, valorInvertido);

				// aca empieza el buffer para mostrar la imagen
				valorPixel = this.getImagenInvertidaMaximoReal(i, j); // obtengo el valor del pixel invertido
				bufferedImage.getRaster().setSample(j, i, 0, valorPixel); // cargo el valor en la buffered image
			}
		}

		//guardo en la imagen la bufferedImage leida
		this.setBufferedImageInvertidaMaximoReal(bufferedImage);
	}
}

