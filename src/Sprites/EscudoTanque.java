 /*************************************************************
 * ITM - Departamente de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 *
 * Nombre: Oscar Hernan Chan Tun
 * Fecha: 25 de Octubre del 2020
 * Hora: 12:42 PM
 *
 * Projecto: BattleCity
 * Paquete: Sprites
 * Archivo: EscudoTanque
 * Descripción: Clase para la animación del escudo del tanque 
 *
 *************************************************************/

package Sprites;

public class EscudoTanque extends Animacion
{
    long tiempoInicial = System.currentTimeMillis();
    private Tanque tanque;
    private boolean giro = false;
    private int tipo;

    public EscudoTanque(Tanque tanqueA, int tipo) 
    {
        super(tanqueA.x, tanqueA.y);
        tanque = tanqueA;
        cargarImagen("Recursos/Escudo_1.png");
        getImageDimensions();
        this.tipo = tipo;
    }

    @Override
    public void actualizarAnimacion() 
    {
        int tiempoEscudo;
        if (tipo == 1)
            tiempoEscudo = 10000;
        else
            tiempoEscudo = 3000;
        super.x = tanque.x;
        super.y = tanque.y;
        long diferenciaTiempo = (System.currentTimeMillis() - tiempoInicial);
        if (diferenciaTiempo % 10 == 0 && giro == false) 
        {
            cargarImagen("Recursos/Escudo_1.png");
            getImageDimensions();
            giro = true;
        } 
        else if (diferenciaTiempo % 10 == 0 && giro == true) 
        {
            cargarImagen("Recursos/Escudo_2.png");
            getImageDimensions();
            giro = false;
        }
        if ((System.currentTimeMillis() - tiempoInicial > tiempoEscudo)) 
        {
            tanque.escudo = false;
            super.vis = false;
        }
    }
}