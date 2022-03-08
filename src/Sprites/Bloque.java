 /*************************************************************
 * ITM - Departamente de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 *
 * Nombre: Oscar Hernan Chan Tun
 * Fecha: 25 de Octubre del 2020
 * Hora: 10:05 AM
 *
 * Projecto: BattleCity
 * Paquete: Sprites
 * Archivo: Bloque
 * Descripción: Clase para el diseño de los bloques
 *
 *************************************************************/

package Sprites;

public class Bloque extends Sprite
{
    public int durabilidad = 1;
    private int tipo;

    public Bloque(int x, int y) 
    {
        super(x, y);
    }

    public void durabilidadMenor() 
    {
        durabilidad -= 1;
    }

    public int getDurabilidad() 
    {
        return durabilidad;
    }

    public void setDurabilidad(int durabilidad) 
    {
        this.durabilidad = durabilidad;
    }

    public int getTipo() 
    {
        return tipo;
    }

    public void setTipo(int tipo) 
    {
        this.tipo = tipo;
    }

    public void actualizarAnimacion() {}
}