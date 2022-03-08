 /*************************************************************
 * ITM - Departamente de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 *
 * Nombre: Oscar Hernan Chan Tun
 * Fecha: 25 de Octubre del 2020
 * Hora: 10:20 AM
 *
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