 /*************************************************************
 * ITM - Departamento de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 * Projecto: BattleCity
 * Paquete: Sprites
 * Archivo: Ladrillo
 * Descripción: Clase para el diseño de los bloques con aspecto
 * de ladrillo
 *
 *************************************************************/

package Sprites;

public class Ladrillo extends Bloque
{
    public Ladrillo(int x, int y) 
    {
        super(x, y);
        cargarImagen("Recursos/Ladrillo.png");
        getImageDimensions();
        setDurabilidad(1);
        setTipo(1);
    }
}