 /*************************************************************
 * ITM - Departamente de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 *
 * Nombre: Oscar Hernan Chan Tun
 * Fecha: 25 de Octubre del 2020
 * Hora: 10:16 AM
 *
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