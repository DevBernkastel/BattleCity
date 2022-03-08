 /*************************************************************
 * ITM - Departamento de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 * Projecto: BattleCity
 * Paquete: Sprites.PowerUps
 * Archivo: BombaPU
 * Descripción: Clase para el diseño de la bomba (PowerUp)
 *
 *************************************************************/

package Sprites.PowerUps;

public class BombaPU extends PowerUp 
{
    public BombaPU(int x, int y) 
    {
        super(x, y);
        cargarImagen("Recursos/PU_Granada.png");
        getImageDimensions();
        setTipo(9);
        s = "Recursos/PU_Granada.png";
    }
}