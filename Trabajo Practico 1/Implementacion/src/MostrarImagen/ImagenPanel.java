package MostrarImagen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagenPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private BufferedImage image;

    public ImagenPanel(BufferedImage image) {
        this.image = image;
        setOpaque(false); // Hacer el panel transparente
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int panelWidth = this.getWidth();
            int panelHeight = this.getHeight();
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();

            // Calcular la escala para ajustar la imagen al tamaño del panel
            double scaleX = (double) panelWidth / imageWidth;
            double scaleY = (double) panelHeight / imageHeight;
            double scale = Math.min(scaleX, scaleY);

            // Calcular el nuevo tamaño de la imagen
            int newWidth = (int) (imageWidth * scale);
            int newHeight = (int) (imageHeight * scale);
            int x = (panelWidth - newWidth) / 2;
            int y = (panelHeight - newHeight) / 2;

            // Dibujar la imagen escalada y centrada
            g.drawImage(image, x, y, newWidth, newHeight, this);
        }
    }
}