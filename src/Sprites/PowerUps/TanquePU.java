 /*************************************************************
 * ITM - Departamente de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 *
 * Nombre: Oscar Hernan Chan Tun
 * Fecha: 25 de Octubre del 2020
 * Hora: 13: PM
 *
 * Projecto: BattleCity
 * Paquete: Sprites.PowerUps
 * Archivo: TanquePU
 * Descripción: Clase para el diseño del tanque (PowerUp)
 *
 *************************************************************/

package Sprites.PowerUps;

public class TanquePU extends PowerUp
{
    public TanquePU(int x, int y) 
    {
        super(x, y);
        cargarImagen("Recursos/PU_Tanque.png");
        getImageDimensions();
        setTipo(7);
        s = "Recursos/PU_Tanque.png";
    }
}