 /*************************************************************
 * ITM - Departamento de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 * Projecto: BattleCity
 * Paquete: Sprites
 * Archivo: Metal
 * Descripción: Clase para el diseño de los bloques con aspecto
 * de metal
 *
 *************************************************************/

package Sprites;

public class Metal extends Bloque
{
    public Metal(int x, int y) 
    {
        super(x, y);
        cargarImagen("Recursos/Metal.png");
        getImageDimensions();
        setDurabilidad(1);
        setTipo(2);
    }
}