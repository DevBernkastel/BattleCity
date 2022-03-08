 /*************************************************************
 * ITM - Departamente de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 *
 * Nombre: Oscar Hernan Chan Tun
 * Fecha: 25 de Octubre del 2020
 * Hora: 11:20 AM
 *
 * Projecto: BattleCity
 * Paquete: Sprites
 * Archivo: Animacion
 * Descripción: Clase para la base de las animaciones
 *
 *************************************************************/

package Sprites;

public class Animacion extends Sprite
{
    long tiempoInicial = System.currentTimeMillis();

    public Animacion(int x, int y) 
    {
        super(x, y);
    }

    public void actualizarAnimacion() {}
}