 /*************************************************************
 * ITM - Departamento de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 * Projecto: BattleCity
 * Paquete: Sprites.PowerUps
 * Archivo: PalaPU
 * Descripción: Clase para el diseño de la pala (PowerUp)
 *
 *************************************************************/

package Sprites.PowerUps;

public class PalaPU extends PowerUp
{
    public PalaPU(int x, int y) 
    {
        super(x, y);
        cargarImagen("Recursos/PU_Pala.png");
        getImageDimensions();
        setTipo(11);
        s = "Recursos/PU_Pala.png";
    }
}