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
 * Archivo: RelojPU
 * Descripción: Clase para el diseño del reloj (PowerUp)
 *
 *************************************************************/

package Sprites.PowerUps;

public class RelojPU extends PowerUp
{
    public RelojPU(int x, int y) 
    {
        super(x, y);
        cargarImagen("Recursos/PU_Reloj.png");
        getImageDimensions();
        setTipo(10);
        s = "Recursos/PU_Reloj.png";
    }
}