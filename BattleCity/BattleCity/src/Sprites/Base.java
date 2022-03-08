 /*************************************************************
 * ITM - Departamento de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 * Projecto: BattleCity
 * Paquete: Sprites
 * Archivo: Base
 * Descripción: Clase para el diseño de la base
 *
 *************************************************************/

package Sprites;

public class Base extends Bloque
{
    public boolean gameOver = false;

    public Base(int x, int y) 
    {
        super(x, y);
        cargarImagen("Recursos/Base.png");
        getImageDimensions();
        setDurabilidad(1);
        setTipo(3);
    }

    public void actualizarAnimacion() 
    {
        if (gameOver == true) 
        {
            cargarImagen("Recursos/Base_Destruida.png");
            getImageDimensions();
        }
    }
}