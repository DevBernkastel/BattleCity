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
 * Archivo: PowerUp
 * Descripción: Clase para el diseño de los PowerUps
 *
 *************************************************************/

package Sprites.PowerUps;

import Sprites.Sprite;

public class PowerUp extends Sprite
{
    long cargarTiempo;
    private int tipo;
    boolean giro = false;
    String s;

    public PowerUp(int x, int y) 
    {
        super(x, y);
        cargarTiempo = System.currentTimeMillis();
    }

    public void setTipo(int tipo) 
    {
        this.tipo = tipo;
    }

    public int getTipo() 
    {
        return tipo;
    }

    public long getCargarTiempo() 
    {
        return cargarTiempo;
    }

    public void actualizarAnimacion() 
    {
        long diferenciaTiempo = (System.currentTimeMillis() - cargarTiempo);
        if (diferenciaTiempo > 5000) 
        {
            if (diferenciaTiempo % 10 == 0 && giro == false) 
            {
                cargarImagen("");
                getImageDimensions();
                giro = true;
            } 
            else if (diferenciaTiempo % 10 == 0 && giro == true) 
            {
                cargarImagen(s);
                getImageDimensions();
                giro = false;
            }
        }
    }
}