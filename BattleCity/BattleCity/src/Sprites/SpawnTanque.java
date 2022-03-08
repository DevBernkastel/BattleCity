 /*************************************************************
 * ITM - Departamento de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 * Projecto: BattleCity
 * Paquete: Sprites
 * Archivo: SpawnTanque
 * Descripción: Clase para la animación del escudo del tanque 
 *
 *************************************************************/

package Sprites;

public class SpawnTanque extends Animacion
{
    public SpawnTanque(int x, int y) 
    {
        super(x, y);
        cargarImagen("Recursos/Aparicion_1.png");
        getImageDimensions();
    }
    
    @Override
    public void actualizarAnimacion() 
    {
        if ((System.currentTimeMillis() - tiempoInicial) > 100) 
        {
            cargarImagen("Recursos/Aparicion_2.png");
            getImageDimensions();
        }
        if ((System.currentTimeMillis() - tiempoInicial > 200)) 
        {
            cargarImagen("Recursos/Aparicion_3.png");
            getImageDimensions();
        }
        if ((System.currentTimeMillis() - tiempoInicial > 300)) 
        {
            cargarImagen("Recursos/Aparicion_4.png");
            getImageDimensions();
        }
        if ((System.currentTimeMillis() - tiempoInicial > 400)) 
        {
            cargarImagen("Recursos/Aparicion_1.png");
            getImageDimensions();
        }
        if ((System.currentTimeMillis() - tiempoInicial > 500)) 
        {
            cargarImagen("Recursos/Aparicion_2.png");
            getImageDimensions();
        }
        if ((System.currentTimeMillis() - tiempoInicial > 600)) 
        {
            cargarImagen("Recursos/Aparicion_3.png");
            getImageDimensions();
        }
        if ((System.currentTimeMillis() - tiempoInicial > 700)) 
        {
            cargarImagen("Recursos/Aparicion_4.png");
            getImageDimensions();
        }
        if ((System.currentTimeMillis() - tiempoInicial > 800)) 
            super.vis = false;
    }
}
