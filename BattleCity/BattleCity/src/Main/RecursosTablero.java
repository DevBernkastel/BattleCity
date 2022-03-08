 /*************************************************************
 * ITM - Departamento de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 * Projecto: BattleCity
 * Paquete: Main
 * Archivo: RecursosTablero
 * Descripción: Clase para el desarrollo de las utilitys del tablero
 *
 *************************************************************/

package Main;

import Sprites.Animacion;
import Sprites.Bala;
import Sprites.Bloque;
import Sprites.EscudoTanque;
import Sprites.ExplosionTanque;
import Sprites.PowerUps.BombaPU;
import Sprites.PowerUps.EscudoPU;
import Sprites.PowerUps.EstrellaPU;
import Sprites.PowerUps.PowerUp;
import Sprites.PowerUps.RelojPU;
import Sprites.PowerUps.TanquePU;
import Sprites.SpawnTanque;
import Sprites.Tanque;
import Sprites.TanqueIA;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class RecursosTablero 
{
    private static ArrayList<TanqueIA> enemigo = new ArrayList<>();
    private static ArrayList<Bloque> bloques = new ArrayList<>();
    private static ArrayList<Animacion> animaciones = new ArrayList<>();
    private static ArrayList<PowerUp> powerUps = new ArrayList<>();
    private static Tanque tanque;

    public static void loadBoardUtility(ArrayList<TanqueIA> enemigo1, ArrayList<Bloque> bloques1, ArrayList<Animacion> animaciones1, ArrayList<PowerUp> powerUps1, Tanque tanque1) 
    {
        enemigo = enemigo1;
        bloques = bloques1;
        animaciones = animaciones1;
        powerUps = powerUps1;
        tanque = tanque1;
    }

    public static void actualizarPowerUps() 
    {
        for (int i = 0; i < powerUps.size(); i++) 
        {
            PowerUp p = powerUps.get(i);
            p.actualizarAnimacion();
            TipoBloque tipo = TipoBloque.getTypeFromInt(p.getTipo());
            Rectangle r1 = tanque.getBounds();
            Rectangle r2 = p.getBounds();
            if (System.currentTimeMillis() - p.getCargarTiempo() > 10000)
            if (r1.intersects(r2)) 
            {
                powerUps.remove(i);
                Sonidos.tomarPowerUp();
                if (tipo.equals(TipoBloque.TANQUE)) 
                {
                    tanque.subirSalud();
                } else if (tipo.equals(TipoBloque.ESCUDO)) 
                {
                    tanque.escudo = true;
                    animaciones.add(new EscudoTanque(tanque, 1));
                } else if (tipo.equals(TipoBloque.PALA)) 
                {

                } else if (tipo.equals(TipoBloque.ESTRELLA)) 
                {
                    tanque.subirNivelEstrella();
                } else if (tipo.equals(TipoBloque.RELOJ)) 
                {
                    for (int x = 0; x < enemigo.size(); x++) 
                    {
                        enemigo.get(x).congelado = true;
                        enemigo.get(x).inicioTiempoCongelado = System.currentTimeMillis();
                    }
                } else if (tipo.equals(TipoBloque.BOMBA)) 
                {
                    for (TanqueIA enemigo1 : enemigo) 
                    {
                        enemigo1.vis = false;
                        for (TanqueIA ai : enemigo) 
                        {
                            Colisiones.incrementoCantidad(ai);
                        }
                        Tablero.disminuirEnemigos(enemigo.size());
                        animaciones.add(new ExplosionTanque(enemigo1.x, enemigo1.y));
                    }
                }
            }
        }

    }

    public static void spawnPowerUp() 
    {
        Random random = new Random();
        int randomPow = random.nextInt(5);
        if (Colisiones.powerUpX != 0 || Colisiones.powerUpY != 0) 
        {
            switch (randomPow) 
            {
                case 0:
                    powerUps.add(new BombaPU(Colisiones.powerUpX, Colisiones.powerUpY));
                    break;
                case 1:
                    powerUps.add(new RelojPU(Colisiones.powerUpX, Colisiones.powerUpY));
                    break;
                case 2:
                    powerUps.add(new EscudoPU(Colisiones.powerUpX, Colisiones.powerUpY));
                    break;
                case 3:
                    powerUps.add(new EstrellaPU(Colisiones.powerUpX, Colisiones.powerUpY));
                    break;
                case 4:
                    powerUps.add(new TanquePU(Colisiones.powerUpX, Colisiones.powerUpY));
                    break;
                default:
                    break;
            }
            Colisiones.powerUpX = 0;
            Colisiones.powerUpY = 0;
        }
    }

    public static void spawnTanqueIA(String dificultad, boolean powerUp) 
    {
        Random random = new Random();
        int randomPos = random.nextInt(3);
        int randomTipo = random.nextInt(20);
        String tipo;
        if (randomTipo < 2) 
        {
            tipo = "armor";
        } else if (randomTipo >= 2 && randomTipo < 7) 
        {
            tipo = "power";
        } else if (randomTipo >= 8 && randomTipo < 13) 
        {
            tipo = "fast";
        } else 
        {
            tipo = "basic";
        }
        if (randomPos == 0) 
        {
            animaciones.add(new SpawnTanque(2 * 16, 1 * 16));
            TanqueIA IA = new TanqueIA(2 * 16, 1 * 16, dificultad, tipo, powerUp);
            enemigo.add(IA);
        } else if (randomPos == 1) 
        {
            animaciones.add(new SpawnTanque(14 * 16, 1 * 16));
            TanqueIA IA = new TanqueIA(14 * 16, 1 * 16, dificultad, tipo, powerUp);
            enemigo.add(IA);
        } else 
        {
            animaciones.add(new SpawnTanque(26 * 16, 1 * 16));
            TanqueIA IA = new TanqueIA(26 * 16, 1 * 16, dificultad, tipo, powerUp);
            enemigo.add(IA);
        }
    }

    public static void actualizarBalasTanqueIA() 
    {
        for (TanqueIA tanqueIA : enemigo) 
        {
            ArrayList<Bala> balas = tanqueIA.getBalas();
            for (int i = 0; i < balas.size(); i++) 
            {
                Bala b = balas.get(i);
                if (b.isVisible()) 
                {
                    b.movimiento();
                } else if (b.isVisible() == false) 
                {
                    balas.remove(i);
                }
            }
        }
    }

    public static void actualizarBalasTanque() 
    {
        ArrayList<Bala> balas = tanque.getBalas();
        for (int i = 0; i < balas.size(); i++) 
        {
            Bala b = balas.get(i);
            if (b.isVisible()) 
            {
                b.movimiento();
            } else if (b.isVisible() == false) 
            {
                balas.remove(i);
            }
        }
    }

    public static void actualizarBloques() 
    {
        for (int i = 0; i < bloques.size(); i++) 
        {
            Bloque b = bloques.get(i);
            TipoBloque tipo = TipoBloque.getTypeFromInt(b.getTipo());
            if (tipo.equals(TipoBloque.AGUA)) 
            {
                b.actualizarAnimacion();
            } else if (tipo.equals(TipoBloque.BASE)) 
            {
                b.actualizarAnimacion();
            }
            if (b.isVisible() == false) 
                bloques.remove(i);
        }
    }

    public static void actualizarAnimaciones() 
    {
        for (int i = 0; i < animaciones.size(); i++) 
        {
            if (animaciones.get(i).vis == false) 
            {
                animaciones.remove(i);
            } else 
            {
                animaciones.get(i).actualizarAnimacion();
            }
        }
    }

    public static void actualizarTanque() 
    {
        if (tanque.isVisible())
            tanque.movimiento();
    }

    public static void verificarColisiones() 
    {
        ArrayList<Bala> balas = new ArrayList<>();
        balas.addAll(tanque.getBalas());
        for (TanqueIA tanqueIA : enemigo) 
        {
            balas.addAll(tanqueIA.getBalas());
        }
        Colisiones.verificadorColisionBalasBloques(balas, bloques);
        Colisiones.verificadorColisionBalasTanques(balas, tanque);
        Colisiones.verificadorColisionBalasTanquesIA(balas, enemigo);
        Colisiones.verificadorColisionTanqueTanqueIA(enemigo, tanque);
    }
}