 /*************************************************************
 * ITM - Departamento de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 * Projecto: BattleCity
 * Paquete: Main
 * Archivo: Colisiones
 * Descripción: Clase para el desarrollo de las colisiones 
 *
 *************************************************************/

package Main;

import Sprites.Animacion;
import Sprites.Bala;
import Sprites.Base;
import Sprites.Bloque;
import Sprites.EscudoTanque;
import Sprites.Explosion;
import Sprites.ExplosionTanque;
import Sprites.Tanque;
import Sprites.TanqueIA;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Colisiones 
{
    public static int powerUpX = 0, powerUpY = 0;
    private static ArrayList<Bloque> bloques;
    private static ArrayList<Animacion> explosiones;
    private static int[] cantidadTanquesEnemigos = {0, 0, 0, 0};
    
    static public void loadColisiones(ArrayList<Bloque> enBloques, ArrayList<Animacion> enExplosiones) 
    {
        bloques = enBloques;
        explosiones = enExplosiones;
    }

    public static void resetPuntuacion() 
    {
        cantidadTanquesEnemigos = new int[]{0, 0, 0, 0};
    }

    public static void colisionesBalasBloques(Bala bala, Bloque bloque) 
    {
        TipoBloque tipo = TipoBloque.getTypeFromInt(bloque.getTipo());
        if (tipo.equals(TipoBloque.LADRILLO)) 
        {
            bloque.durabilidadMenor();
            bala.setVisible(false);
        }
        if (tipo.equals(TipoBloque.METAL) && bala.getActualizacion()) 
        {
            bloque.durabilidadMenor();
            bala.setVisible(false);
        }
        if (tipo.equals(TipoBloque.METAL) && bala.getActualizacion() == false) 
            bala.setVisible(false);
        if (tipo.equals(TipoBloque.BASE) && bloque.durabilidad != 0) 
        {
            bala.setVisible(false);
            Base b = (Base) bloque;
            b.gameOver = true;
            explosiones.add(new ExplosionTanque(bloque.x, bloque.y));
            Sonidos.sonidoExplosion2();
            Tablero.setFinDelJuego();
            Sonidos.gameOver();

        }
        if (bloque.getDurabilidad() == 0) 
        {
            Sonidos.sonidoExplosion2();
            bloque.vis = false;
            explosiones.add(new Explosion(bloque.x, bloque.y));

        }
        if (bloque.getDurabilidad() == 0)
            bloque.vis = false;
    }

    public static boolean verificadorColisionTanqueBloque(Rectangle r3) 
    {
        for (Bloque bloque : bloques) {
            Rectangle r2 = bloque.getBounds();
            TipoBloque tipo = TipoBloque.getTypeFromInt(bloque.getTipo());
            if (tipo.equals(TipoBloque.ARBUSTO)) {
            } else if (r3.intersects(r2)) {
                return true;
            }
        }
        return false;
    }

    public static void verificadorColisionBalasBloques(ArrayList<Bala> balas, ArrayList<Bloque> bloques) 
    {
        for (Bala b : balas) 
        {
            Rectangle r1 = b.getBounds();
            for (Bloque aBlock : bloques) 
            {
                Rectangle r2 = aBlock.getBounds();
                if (r1.intersects(r2)) 
                {
                    Sonidos.balaGolpeaLadrillo();
                    colisionesBalasBloques(b, aBlock);
                }
            }
        }
    }

    public static void verificadorColisionBalasTanques(ArrayList<Bala> balas, Tanque tanque) 
    {
        Rectangle r2 = tanque.getBounds();
        for (Bala b : balas) 
        {
            Rectangle r1 = b.getBounds();
            if (r1.intersects(r2) && b.esEnemigo == true) 
            {
                b.vis = false;
                if (tanque.escudo == false) 
                {
                    Sonidos.sonidoExplosion1();
                    explosiones.add(new ExplosionTanque(tanque.x, tanque.y));
                    tanque.bajarSalud();
                    resetPosicionTanque(tanque, 1);
                } 
                else
                    Sonidos.balaGolpeaTanque();
            }
        }
    }

    public static void verificadorColisionBalasTanquesIA(ArrayList<Bala> balas, ArrayList<TanqueIA> tanquesIA) 
    {
        for (Bala b : balas) 
        {
            Rectangle r1 = b.getBounds();
            for (TanqueIA tanqueIA : tanquesIA) 
            {
                Rectangle r2 = tanqueIA.getBounds();
                if (r1.intersects(r2) && b.esEnemigo == false) 
                {
                    tanqueIA.decreaseSalud();
                    b.vis = false;
                    Sonidos.balaGolpeaTanque();
                    if (tanqueIA.getSalud() < 1) 
                    {
                        incrementoCantidad(tanqueIA);
                        if (tanqueIA.hasPowerUp()) 
                        {
                            powerUpX = tanqueIA.getX();
                            powerUpY = tanqueIA.getY();
                        }
                        tanqueIA.vis = false;
                        Tablero.disminuirEnemigos(1);
                        explosiones.add(new ExplosionTanque(tanqueIA.x, tanqueIA.y));
                        Sonidos.sonidoExplosion1();
                    }
                }
            }
        }
    }

    public static void incrementoCantidad(TanqueIA tanqueIA) 
    {
        String tipo = tanqueIA.getTipo();
        switch (tipo) {
            case "basic":
                cantidadTanquesEnemigos[0] += 1;
                break;
            case "fast":
                cantidadTanquesEnemigos[1] += 1;
                break;
            case "power":
                cantidadTanquesEnemigos[2] += 1;
                break;
            case "armor":
                cantidadTanquesEnemigos[3] += 1;
                break;
            default:
                break;
        }
    }

    public static int[] getCantidadTanquesEnemigos() 
    {
        return cantidadTanquesEnemigos;
    }
    
    public static void resetPosicionTanque(Tanque tanqueA, int tipo) 
    {
        tanqueA.x = 10 * 16;
        tanqueA.y = (Mapa.nivel0.length - 3) * 16;
        tanqueA.escudo = true;
        explosiones.add(new EscudoTanque(tanqueA, 2));
        if (tipo == 1)
            tanqueA.nivelInicial = 0;
        else 
            tanqueA.escudo = false;
    }

    public static void verificadorColisionTanqueTanqueIA(ArrayList<TanqueIA> tanquesIA, Tanque tanqueA) {
        Rectangle r1 = tanqueA.getBounds();
        for (TanqueIA tanqueIA : tanquesIA) 
        {
            Rectangle r2 = tanqueIA.getBounds();
            if (r1.intersects(r2)) 
            {
                if (tanqueA.escudo == false) 
                {
                    explosiones.add(new ExplosionTanque(tanqueA.x, tanqueA.y));
                    tanqueA.bajarSalud();
                    resetPosicionTanque(tanqueA, 1);
                } 
                else if (tanqueA.escudo == true) 
                {
                    incrementoCantidad(tanqueIA);
                    Tablero.disminuirEnemigos(1);
                    tanqueIA.vis = false;
                    explosiones.add(new ExplosionTanque(tanqueA.x, tanqueA.y));
                }
            }
        }
    }
}