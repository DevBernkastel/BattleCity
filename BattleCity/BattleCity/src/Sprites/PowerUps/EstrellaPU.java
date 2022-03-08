 /*************************************************************
 * ITM - Departamento de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 * Projecto: BattleCity
 * Paquete: Sprites.PowerUps
 * Archivo: EstrellaPU
 * Descripción: Clase para el diseño de la estrella (PowerUp)
 *
 *************************************************************/

package Sprites.PowerUps;

public class EstrellaPU extends PowerUp
{
    public EstrellaPU (int x, int y) 
    {
        super(x, y);
        cargarImagen("Recursos/PU_Estrella.png");
        getImageDimensions();
        setTipo(8);
        s = "Recursos/PU_Estrella.png";
    }
}