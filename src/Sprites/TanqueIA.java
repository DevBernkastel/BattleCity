 /*************************************************************
 * ITM - Departamente de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 *
 * Nombre: Oscar Hernan Chan Tun
 * Fecha: 25 de Octubre del 2020
 * Hora: 13: PM
 *
 * Projecto: BattleCity
 * Paquete: Sprites
 * Archivo: TanqueIA
 * Descripción: Clase para el desarrollo de la IA del tanque 
 *
 *************************************************************/

package Sprites;

import Main.Colisiones;
import Main.Mapa;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class TanqueIA extends Sprite
{
    private final int ANCHO_BORDE = Mapa.ANCHO_BORDE;
    private final int ALTO_BORDE = Mapa.ALTO_BORDE;
    private ArrayList<Bala> balas;
    private boolean powerUp;
    private int dx, dy;
    public int direccion;
    private int salud;
    private String dificultad;
    private String tipo;
    private int timerDireccion = 0;
    private int actualizarIntervaloDireccion;
    private int timerDisparo = 0;
    private int actualizarIntervaloDisparo;
    private double velocidadConstante;
    public boolean congelado = false;
    public long inicioTiempoCongelado;
    private String imagenArriba;
    private String imagenAbajo;
    private String imagenIzquierda;
    private String imagenDerecha;

    public TanqueIA(int x, int y, String dificultad, String tipo, boolean powerUp) 
    {
        super(x, y);
        balas = new ArrayList<>();
        direccion = 0;
        this.vis = true;
        this.powerUp = powerUp;
        this.dificultad = dificultad;
        this.tipo = tipo;
        this.setUp();
        this.imageSetUp();
    }

    private void setUp() 
    {
        if (null != this.tipo) switch (this.tipo) 
        {
            case "basic":
                this.salud = 1;
                this.velocidadConstante = 1;
                switch (dificultad) 
                {
                    case "easy":
                        actualizarIntervaloDireccion = 30;
                        actualizarIntervaloDisparo = 80;
                        break;
                    case "normal":
                        actualizarIntervaloDireccion = 30;
                        actualizarIntervaloDisparo = 75;
                        break;
                    case "hard":
                        actualizarIntervaloDireccion = 30;
                        actualizarIntervaloDisparo = 70;
                        break;
                }
                break;
            case "armor":
                this.salud = 4;
                this.velocidadConstante = 1;
                switch (dificultad) 
                {
                    case "easy":
                        actualizarIntervaloDireccion = 30;
                        actualizarIntervaloDisparo = 80;
                        break;
                    case "normal":
                        actualizarIntervaloDireccion = 30;
                        actualizarIntervaloDisparo = 75;
                        break;
                    case "hard":
                        actualizarIntervaloDireccion = 30;
                        actualizarIntervaloDisparo = 70;
                        break;
                }
                break;
            case "power":
                this.salud = 1;
                this.velocidadConstante = 1;
                switch (dificultad) 
                {
                    case "easy":
                        actualizarIntervaloDireccion = 30;
                        actualizarIntervaloDisparo = 40;
                        break;
                    case "normal":
                        actualizarIntervaloDireccion = 30;
                        actualizarIntervaloDisparo = 35;
                        break;
                    case "hard":
                        actualizarIntervaloDireccion = 30;
                        actualizarIntervaloDisparo = 30;
                        break;
                }
                break;
            case "fast":
                this.salud = 1;
                this.velocidadConstante = 2;
                switch (dificultad) 
                {
                    case "easy":
                        actualizarIntervaloDireccion = 30;
                        actualizarIntervaloDisparo = 80;
                        break;
                    case "normal":
                        actualizarIntervaloDireccion = 30;
                        actualizarIntervaloDisparo = 75;
                        break;
                    case "hard":
                        actualizarIntervaloDireccion = 30;
                        actualizarIntervaloDisparo = 70;
                        break;
                }
                break;
        }
    }

    private void imageSetUp() 
    {
        if (null != this.tipo) switch (this.tipo) 
        {
            case "basic":
                cargarImagen("Recursos/tank_basic_up.png");
                getImageDimensions();
                this.imagenArriba = "Recursos/tank_basic_up.png";
                this.imagenAbajo = "Recursos/tank_basic_down.png";
                this.imagenIzquierda = "Recursos/tank_basic_left.png";
                this.imagenDerecha = "Recursos/tank_basic_right.png";
                break;
            case "armor":
                cargarImagen("Recursos/tank_armor_up.png");
                getImageDimensions();
                this.imagenArriba = "Recursos/tank_armor_up.png";
                this.imagenAbajo = "Recursos/tank_armor_down.png";
                this.imagenIzquierda = "Recursos/tank_armor_left.png";
                this.imagenDerecha = "Recursos/tank_armor_right.png";
                break;
            case "power":
                cargarImagen("Recursos/tank_power_up.png");
                getImageDimensions();
                this.imagenArriba = "Recursos/tank_power_up.png";
                this.imagenAbajo = "Recursos/tank_power_down.png";
                this.imagenIzquierda = "Recursos/tank_power_left.png";
                this.imagenDerecha = "Recursos/tank_power_right.png";
                break;
            case "fast":
                cargarImagen("Recursos/tank_fast_up.png");
                getImageDimensions();
                this.imagenArriba = "Recursos/tank_fast_up.png";
                this.imagenAbajo = "Recursos/tank_fast_down.png";
                this.imagenIzquierda = "Recursos/tank_fast_left.png";
                this.imagenDerecha = "Recursos/tank_fast_right.png";
                break;
        }
    }

    @Override
    public int getX() 
    {
        return x;
    }

    @Override
    public int getY() 
    {
        return y;
    }

    @Override
    public Image getImage() 
    {
        return imagen;
    }

    public String getDificultad() 
    {
        return dificultad;
    }

    public ArrayList<Bala> getBalas() 
    {
        return balas;
    }

    public boolean hasPowerUp() 
    {
        return powerUp;
    }

    public void decreaseSalud() 
    {
        this.salud -= 1;
    }

    public int getSalud() 
    {
        return salud;
    }

    public void accionFacil() 
    {
        if (this.timerDireccion >= this.actualizarIntervaloDireccion) 
        {
            direccionRandom();
            this.timerDireccion = 0;
        } 
        else 
            this.timerDireccion++;
        this.movimiento();
        if (this.timerDisparo >= this.actualizarIntervaloDisparo) 
        {
            this.disparo();
            this.timerDisparo = 0;
        } 
        else
            this.timerDisparo++;
    }
    
    public void accionNormal(Tanque tanque) 
    {
        Random direccionRandom = new Random();
        if (this.timerDireccion >= this.actualizarIntervaloDireccion) 
        {
            int random = direccionRandom.nextInt(20);
            if (random % 8 == 1)
                enDireccionBase();
            else if (random % 4 == 0) 
                direccionRandom();
            else
                enDireccionTanque(tanque);
            this.timerDireccion = 0;
        }
        else 
            this.timerDireccion++;
        this.movimiento();
        Rectangle elTanque = new Rectangle(x + dx, y + dy, ancho, alto);
        if (Colisiones.verificadorColisionTanqueBloque(elTanque) == true) 
        {
            if (direccionRandom.nextBoolean() && this.timerDisparo < 3) 
            {
                this.disparo();
                this.timerDisparo++;
            }
        }
        if (this.timerDisparo >= this.actualizarIntervaloDisparo) 
        {
            this.disparo();
            this.timerDisparo = 0;
        } 
        else
            this.timerDisparo++;
    }

    public void accionDificil(Tanque tanque) 
    {
        Random direccionRandom = new Random();
        if (this.timerDireccion >= this.actualizarIntervaloDireccion) 
        {
            int random = direccionRandom.nextInt(7);
            if (random % 5 == 0)
                enDireccionBase();
            else if (random % 6 == 1)
                direccionRandom();
            else
                enDireccionTanque(tanque);
            this.timerDireccion = 0;
        }
        else
            this.timerDireccion++;
        Rectangle elTanque = new Rectangle(x + dx, y + dy, ancho, alto);
        this.movimiento();
        if (Colisiones.verificadorColisionTanqueBloque(elTanque) == true) 
        {
            if (direccionRandom.nextBoolean() && this.timerDisparo < 3) 
            {
                this.disparo();
                this.timerDisparo++;
            }
        }
        if (this.timerDisparo >= this.actualizarIntervaloDisparo) 
        {
            this.disparo();
            this.timerDisparo = 0;
        } 
        else
            this.timerDisparo++;
    }

    public void movimiento() 
    {
        Rectangle elTanque = new Rectangle(x + dx, y + dy, ancho, alto);
        if (Colisiones.verificadorColisionTanqueBloque(elTanque) == false) 
        {
            x += dx;
            y += dy;
        }
        if (x > ANCHO_BORDE - ancho) 
            x = ANCHO_BORDE - ancho;
        if (y > ALTO_BORDE - alto)
            y = ALTO_BORDE - alto;
        if (x < 1)
            x = 1;
        if (y < 1)
            y = 1;
    }

    public void disparo() 
    {
        Bala balaA;
        switch (direccion) 
        {
            case 0:
                balaA = new Bala(x + ancho / 3, y, 0, true);
                break;
            case 1:
                balaA = new Bala(x + ancho, y + alto / 3, 1, true);
                break;
            case 2:
                balaA = new Bala(x + ancho / 3, y + alto, 2, true);
                break;
            default:
                balaA = new Bala(x, y + alto / 3, 3, true);
                break;
        }
        if (!congelado)
            balas.add(balaA);
//      Sonidos.sonidoDisparo();
    }

    public void direccionRandom() 
    {
        Random direccionRandom = new Random();
        this.direccion = direccionRandom.nextInt(4);
        actualizarDireccion();
    }

    public void enDireccionTanque(Tanque tanque) 
    {
        int tanqueX = tanque.getX();
        int tanqueY = tanque.getY();
        Random direccionRandom = new Random();
        if (direccionRandom.nextBoolean())
        {
            if (this.getY() > tanqueY)
                this.direccion = 0;
            else
                this.direccion = 2;
        }
        else if (this.getX() > tanqueX)
            this.direccion = 3;
        else if (this.getX() < tanqueX)
            this.direccion = 1;
        else 
            this.direccion = 3;
        actualizarDireccion();
    }

    public void enDireccionBase() 
    {
        if (this.getX() > 14 * 16) 
            this.direccion = 3;
        else if (this.getX() < 14 * 16)
            this.direccion = 1;
        else
            this.direccion = 2;
        actualizarDireccion();
    }

    private void actualizarDireccion() 
    {
        ImageIcon ii;
        if (congelado) 
        {
            this.dx = 0;
            this.dy = 0;
        } 
        else 
        {
            switch (this.direccion) 
            {
                case 0:
                    ii = new ImageIcon(this.imagenArriba);
                    imagen = ii.getImage();
                    this.dx = (int) (0 * this.velocidadConstante);
                    this.dy = (int) (-1 * this.velocidadConstante);
                    break;
                case 1:
                    ii = new ImageIcon(this.imagenDerecha);
                    imagen = ii.getImage();
                    this.dx = (int) (1 * this.velocidadConstante);
                    this.dy = (int) (0 * this.velocidadConstante);
                    break;
                case 2:
                    ii = new ImageIcon(this.imagenAbajo);
                    imagen = ii.getImage();
                    this.dx = (int) (0 * this.velocidadConstante);
                    this.dy = (int) (1 * this.velocidadConstante);
                    break;
                case 3:
                    ii = new ImageIcon(this.imagenIzquierda);
                    imagen = ii.getImage();
                    this.dx = (int) (-1 * this.velocidadConstante);
                    this.dy = (int) (0 * this.velocidadConstante);
                    break;
            }
        }
    }

    public String getTipo() 
    {
        return tipo;
    }
}
