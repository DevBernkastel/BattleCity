 /*************************************************************
 * ITM - Departamento de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 *Projecto: BattleCity
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