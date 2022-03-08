 /*************************************************************
 * ITM - Departamente de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 *
 * Nombre: Oscar Hernan Chan Tun
 * Fecha: 25 de Octubre del 2020
 * Hora: 13: PM
 *
 * Projecto: BattleCity
 * Paquete: Main
 * Archivo: Imagenes
 * Descripción: Clase para el desarrollo de las imagenes
 *
 *************************************************************/

package Main;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Imagenes 
{
    private final Image vidas, bandera, enemigo;
    private final Image flecha, tanqueBasico, tanqueRapido, tanquePoderoso, tanqueArmado;
    private final Image background, tanque, arbusto2;
    private static Imagenes instance;

    public static Imagenes getInstance() 
    {
        if (instance == null)
            return new Imagenes();
        return instance;
    }

    private Imagenes() 
    {
        vidas = cargarImagen("Recursos/Vidas.png");
        bandera = cargarImagen("Recursos/Bandera.png");
        enemigo = cargarImagen("Recursos/Enemigo.png");
        flecha = cargarImagen("Recursos/Flecha.png");
        tanqueBasico = cargarImagen("Recursos/tank_basic.png");
        tanqueRapido = cargarImagen("Recursos/tank_fast.png");
        tanquePoderoso = cargarImagen("Recursos/tank_power.png");
        tanqueArmado = cargarImagen("Recursos/tank_armor.png");
        background = cargarImagen("/Imagenes/Battle_City.png");
        tanque = cargarImagen("Recursos/Tanque1_Derecha.png");
        arbusto2 = cargarImagen("Recursos/Arbusto2.png");
    }

    public Image cargarImagen(String imageAddress) 
    {
        ImageIcon icon = new ImageIcon(imageAddress);
        return icon.getImage();
    }

    public Image getVidas() 
    {
        return vidas;
    }

    public Image getBandera() 
    {
        return bandera;
    }

    public Image getEnemigo() 
    {
        return enemigo;
    }

    public Image getFlecha() 
    {
        return flecha;
    }

    public Image getTanqueBasico() 
    {
        return tanqueBasico;
    }

    public Image getTanqueRapido() 
    {
        return tanqueRapido;
    }

    public Image getTanquePoderoso() 
    {
        return tanquePoderoso;
    }

    public Image getTanqueArmado() 
    {
        return tanqueArmado;
    }

    public Image getBackground() 
    {
        return background;
    }

    public Image getArbusto2() 
    {
        return arbusto2;
    }

    public Image getTanque() 
    {
        return tanque;
    }
}