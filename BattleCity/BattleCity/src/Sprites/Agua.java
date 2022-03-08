 /*************************************************************
 * ITM - Departamento de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 * Projecto: BattleCity
 * Paquete: Sprites
 * Archivo: Agua
 * Descripción: Clase para el diseño de los bloques con aspecto
 * de agua
 *
 *************************************************************/

package Sprites;

public class Agua extends Bloque
{
    long ultimaImagen = 0;
    boolean ultimaCarga = false;

    @Override
    public void actualizarAnimacion() 
    {
        if ((System.currentTimeMillis() - ultimaImagen) > 500) 
        {
            if (ultimaCarga) 
            {
                cargarImagen("Recursos/Agua_1.png");
                ultimaImagen = System.currentTimeMillis();
                ultimaCarga = false;
            } 
            else 
            {
                cargarImagen("Recursos/Agua_2.png");
                ultimaImagen = System.currentTimeMillis();
                ultimaCarga = true;
            }
        }
    }

    public Agua(int x, int y) 
    {
        super(x, y);
        cargarImagen("Recursos/Agua_1.png");
        getImageDimensions();
        setDurabilidad(1);
        setTipo(4);
    }
}