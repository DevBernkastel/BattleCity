 /*************************************************************
 * ITM - Departamento de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 * Projecto: BattleCity
 * Paquete: Sprites
 * Archivo: Arbusto
 * Descripción: Clase para el diseño de los bloques con aspecto
 * de arbusto
 *
 *************************************************************/

package Sprites;

public class Arbusto extends Bloque
{
    public Arbusto(int x, int y) 
    {
        super(x, y);
        cargarImagen("Recursos/Arbusto.png");
        getImageDimensions();
        setDurabilidad(1);
        setTipo(5);
    }
}