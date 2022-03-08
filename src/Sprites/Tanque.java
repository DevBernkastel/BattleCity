 /*************************************************************
 * ITM - Departamente de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 *
 * Nombre: Oscar Hernan Chan Tun
 * Fecha: 25 de Octubre del 2020
 * Hora: 13: PM
 *
 * Projecto: BattleCity
 * Paquete: Sprites
 * Archivo: Tanque
 * Descripción: Clase para el diseño del tanque 
 *
 *************************************************************/

package Sprites;

import Main.Colisiones;
import Main.Mapa;
import Main.Sonidos;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Tanque extends Sprite
{
    private final int ANCHO_BORDE = Mapa.ANCHO_BORDE, ALTO_BORDE = Mapa.ALTO_BORDE;
    private int dx, dy, salud = 2;
    private ArrayList<Bala> balas;
    private long ultimoDisparo = 0;
    public int nivelInicial = 0, vidas, direccion;
    public boolean escudo = false;

    public void ganarVida() 
    {
        this.vidas += 1;
    }

    public int getSalud() 
    {
        return salud;
    }

    public void subirNivelEstrella() 
    {
        nivelInicial += 1;
    }

    public int getVidas() 
    {
        return this.vidas;
    }

    public void bajarSalud() 
    {
        if (escudo == false) 
            this.salud -= 1;
    }
    
    public Tanque(int x, int y, int vidas) 
    {
        super(x, y);
        cargarImagen("Recursos/Tanque1_Arriba.png");
        getImageDimensions();
        balas = new ArrayList<>();
        direccion = 0;
        this.vidas = vidas;
    }

    public void movimiento() 
    {
        Rectangle elTanque = new Rectangle(x + dx, y + dy, ancho, alto);
        if (Colisiones.verificadorColisionTanqueBloque(elTanque) == false) 
        {
            x += dx;
            y += dy;
        }
        if (x > ANCHO_BORDE - ancho)
            x = ANCHO_BORDE - ancho;
        if (y > ALTO_BORDE - alto)
            y = ALTO_BORDE - alto;
        if (x < 1) 
            x = 1;
        if (y < 1) 
            y = 1;
    }

    public ArrayList<Bala> getBalas() 
    {
        return balas;
    }

    public void disparo() 
    {
        Bala balaA;
        if (direccion == 0) 
            balaA = new Bala(x + ancho / 3, y, 0, false); 
        else if (direccion == 1) 
            balaA = new Bala(x + ancho - 3, y + alto / 3, 1, false);
        else if (direccion == 2)
            balaA = new Bala(x + ancho / 3, (y + alto) - 3, 2, false);
        else
            balaA = new Bala(x, y + alto / 3, 3, false);
        if (nivelInicial == 3)
            balaA.actualizacion();
        balas.add(balaA);
        Sonidos.sonidoDisparo();
    }

    public int getX() 
    {
        return x;
    }

    public int getY() 
    {
        return y;
    }

    public Image getImage() 
    {
        return imagen;
    }

    public void keyPressed(KeyEvent e) 
    {
        int tiempo;
        int key = e.getKeyCode();
        if (nivelInicial == 0) 
            tiempo = 700;
        else
            tiempo = 250;
        if (key == KeyEvent.VK_SPACE && (System.currentTimeMillis() - ultimoDisparo) > tiempo) 
        {
            disparo();
            ultimoDisparo = System.currentTimeMillis();
        } 
        else if (key == KeyEvent.VK_LEFT) 
        {
            dx = -1;
            dy = 0;
            if (nivelInicial > 1) 
                dx = -2;
            ImageIcon ii = new ImageIcon("Recursos/Tanque1_Izquierda.png");
            imagen = ii.getImage();
            direccion = 3;
        } 
        else if (key == KeyEvent.VK_RIGHT) 
        {
            dx = 1;
            dy = 0;
            if (nivelInicial > 1)
                dx = 2;
            ImageIcon ii = new ImageIcon("Recursos/Tanque1_Derecha.png");
            imagen = ii.getImage();
            direccion = 1;
        } 
        else if (key == KeyEvent.VK_UP) 
        {
            ImageIcon ii = new ImageIcon("Recursos/Tanque1_Arriba.png");
            imagen = ii.getImage();
            dy = -1;
            dx = 0;
            if (nivelInicial > 1)
                dy = -2;
            direccion = 0;
        } 
        else if (key == KeyEvent.VK_DOWN) 
        {
            ImageIcon ii = new ImageIcon("Recursos/Tanque1_Abajo.png");
            imagen = ii.getImage();
            dy = 1;
            dx = 0;
            if (nivelInicial > 1)
                dy = 2;
            direccion = 2;
        }
    }

    public void keyReleased(KeyEvent e) 
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT)
            dx = 0;
        if (key == KeyEvent.VK_RIGHT) 
            dx = 0;
        if (key == KeyEvent.VK_UP) 
            dy = 0;
        if (key == KeyEvent.VK_DOWN)
            dy = 0;
    }

    public void subirSalud() 
    {
        this.salud += 1;
    }

    public int getNivelInicial() 
    {
        return nivelInicial;
    }
}