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
 * Archivo: EscudoPU
 * Descripción: Clase para el diseño del escudo (PowerUp)
 *
 *************************************************************/

package Sprites.PowerUps;

public class EscudoPU extends PowerUp
{
    public EscudoPU(int x, int y) 
    {
        super(x, y);
        cargarImagen("Recursos/PU_Escudo.png");
        getImageDimensions();
        setTipo(12);
        s = "Recursos/PU_Escudo.png";
    }
}