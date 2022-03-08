 /*************************************************************
 * ITM - Departamente de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 *
 * Nombre: Oscar Hernan Chan Tun
 * Fecha: 25 de Octubre del 2020
 * Hora: 11:15 AM
 *
 * Projecto: BattleCity
 * Paquete: Sprites
 * Archivo: Bala
 * Descripción: Clase para el diseño de la bala del tanque
 *
 *************************************************************/

package Sprites;

import Main.Mapa;

public class Bala extends Sprite
{
    private final int VELOCIDAD_BALA = 3, ANCHO_BORDE = Mapa.ANCHO_BORDE, ALTO_BORDE = Mapa.ALTO_BORDE;
    private int direccion;
    private boolean actualizacion = false;
    public boolean esEnemigo;

    public Bala(int x, int y, int direccion, boolean enemigo) 
    {
        super(x, y);
        this.direccion = direccion;
        if (direccion == 0)
            cargarImagen("Recursos/Bala_Arriba.png");
        if (direccion == 1)
            cargarImagen("Recursos/Bala_Derecha.png");
        if (direccion == 2)
            cargarImagen("Recursos/Bala_Abajo.png");
        if (direccion == 3)
            cargarImagen("Recursos/Bala_Izquierda.png");
        esEnemigo = enemigo;
        getImageDimensions();
    }

    public void movimiento() 
    {
        if (direccion == 0)
            y -= VELOCIDAD_BALA;
        else if (direccion == 1)
            x += VELOCIDAD_BALA;
        else if (direccion == 2)
            y += VELOCIDAD_BALA;
        else if (direccion == 3)
            x -= VELOCIDAD_BALA;
        if (x > ANCHO_BORDE + 100 + ancho) 
            vis = false;
        if (x < -ancho - 100) 
            vis = false;
        if (y > ALTO_BORDE + alto + 100)
            vis = false;
        if (y < -alto - 100)
            vis = false;
    }

    public void actualizacion() 
    {
        this.actualizacion = true;
    }

    public boolean getActualizacion() 
    {
        return this.actualizacion;
    }
}