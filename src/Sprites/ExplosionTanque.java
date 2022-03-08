 /*************************************************************
 * ITM - Departamente de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 *
 * Nombre: Oscar Hernan Chan Tun
 * Fecha: 25 de Octubre del 2020
 * Hora: 12:05 PM
 *
 * Projecto: BattleCity
 * Paquete: Sprites
 * Archivo: ExplosionTanque
 * Descripción: Clase para la animación del tanque en explosion
 *
 *************************************************************/

package Sprites;

public class ExplosionTanque extends Explosion
{
    private long tiempoInicial = System.currentTimeMillis();
    
    public ExplosionTanque(int x, int y) 
    {
        super(x - 8, y - 8);
        cargarImagen("Recursos/Gran_Explosion_1.png");
        getImageDimensions();
    }
    
    @Override
    public void actualizarAnimacion() 
    {
        if ((System.currentTimeMillis() - tiempoInicial) > 125) 
        {
            cargarImagen("Recursos/Gran_Explosion_2.png");
            getImageDimensions();
        }
        if ((System.currentTimeMillis() - tiempoInicial > 500)) 
        {
            cargarImagen("Recursos/Gran_Explosion_3.png");
            getImageDimensions();
        }
        if ((System.currentTimeMillis() - tiempoInicial > 700)) 
        {
            cargarImagen("Recursos/Gran_Explosion_4.png");
            getImageDimensions();
        }
        if ((System.currentTimeMillis() - tiempoInicial > 900)) 
        {
            cargarImagen("Recursos/Gran_Explosion_5.png");
            getImageDimensions();
        }
        if ((System.currentTimeMillis() - tiempoInicial > 1100))
            super.vis = false;
    }
}