 /*************************************************************
 * ITM - Departamente de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 *
 * Nombre: Oscar Hernan Chan Tun
 * Fecha: 25 de Octubre del 2020
 * Hora: 09:46 AM
 *
 * Projecto: BattleCity
 * Paquete: Sprites
 * Archivo: Sprite
 * Descripción: Clase principal para el diseño de sprites
 *
 *************************************************************/

package Sprites;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Sprite 
{
    public int x, y, ancho, alto;
    public boolean vis;
    public Image imagen;

    public Sprite(int x, int y) 
    {
        this.x = x;
        this.y = y;
        vis = true;
    }

    protected void getImageDimensions() 
    {
        ancho = imagen.getWidth(null);
        alto = imagen.getHeight(null);
    }

    protected void cargarImagen(String nombre) 
    {
        ImageIcon i = new ImageIcon(nombre);
        imagen = i.getImage();
    }

    public Image getImage() 
    {
        return imagen;
    }

    public int getX() 
    {
        return x;
    }

    public int getY() 
    {
        return y;
    }

    public boolean isVisible() 
    {
        return vis;
    }

    public void setVisible(Boolean visible) 
    {
        vis = visible;
    }

    public Rectangle getBounds() 
    {
        return new Rectangle(x, y, ancho, alto);
    }
}