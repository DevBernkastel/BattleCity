 /*************************************************************
 * ITM - Departamento de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 * Projecto: BattleCity
 * Paquete: Sprites
 * Archivo: Borde
 * Descripción: Clase para el diseño del borde
 *
 *************************************************************/

package Sprites;

public class Borde extends Bloque 
{
    public Borde(int x, int y) 
    {
        super(x, y);
        cargarImagen("Recursos/Borde.png");
        getImageDimensions();
        setTipo(6);
        setDurabilidad(1);
    }
}