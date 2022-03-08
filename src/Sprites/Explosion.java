 /*************************************************************
 * ITM - Departamente de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 *
 * Nombre: Oscar Hernan Chan Tun
 * Fecha: 25 de Octubre del 2020
 * Hora: 11:33 AM
 *
 * Projecto: BattleCity
 * Paquete: Sprites
 * Archivo: Explosion
 * Descripción: Clase para la animación de las explosiones
 *
 *************************************************************/

package Sprites;

public class Explosion extends Animacion
{
    public Explosion(int x, int y) 
    {
        super(x - 8, y - 8);
        cargarImagen("Recursos/Explosion_Bala_1.png");
        getImageDimensions();
    }

    @Override
    public void actualizarAnimacion() 
    {
        if ((System.currentTimeMillis() - tiempoInicial) > 125) 
        {
            cargarImagen("Recursos/Explosion_Bala_2.png");
            getImageDimensions();
        }
        if ((System.currentTimeMillis() - tiempoInicial > 250))
        {
            cargarImagen("Recursos/Explosion_Bala_3.png");
            getImageDimensions();
        }
        if ((System.currentTimeMillis() - tiempoInicial > 300))
            super.vis = false;
    }
}