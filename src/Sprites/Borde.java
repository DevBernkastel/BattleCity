 /*************************************************************
 * ITM - Departamente de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 *
 * Nombre: Oscar Hernan Chan Tun
 * Fecha: 25 de Octubre del 2020
 * Hora: 10:40 AM
 *
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