 /*************************************************************
 * ITM - Departamente de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 *
 * Nombre: Oscar Hernan Chan Tun
 * Fecha: 25 de Octubre del 2020
 * Hora: 13: PM
 *
 * Projecto: BattleCity
 * Paquete: Main
 * Archivo: Tablero
 * Descripción: Clase para el desarrollo del tablero
 *
 *************************************************************/

package Main;

import static Main.Colisiones.loadColisiones;
import static Main.Colisiones.resetPosicionTanque;
import static Main.Menu.loadFont;
import Sprites.Agua;
import Sprites.Animacion;
import Sprites.Arbusto;
import Sprites.Bala;
import Sprites.Base;
import Sprites.Bloque;
import Sprites.Borde;
import Sprites.Ladrillo;
import Sprites.Metal;
import Sprites.PowerUps.PowerUp;
import Sprites.Tanque;
import Sprites.TanqueIA;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tablero extends JPanel implements ActionListener
{
    private Timer timer;
    private Tanque tanque;
    private ArrayList<TanqueIA> enemigo = new ArrayList<>();
    private ArrayList<Bloque> bloques = new ArrayList<>();
    private ArrayList<Animacion> animaciones = new ArrayList<>();
    private ArrayList<PowerUp> powerUps = new ArrayList<>();
    private final Imagenes imageInstance = Imagenes.getInstance();

    private final int INIT_PLAYER_X = 10 * 16, INIT_PLAYER_Y = (Mapa.nivel0.length - 3) * 16, B_WIDTH = Mapa.ANCHO_BORDE, B_HEIGHT = Mapa.ALTO_BORDE, DELAY = 15, initX = 29;
    private boolean pausa = false;
    public static boolean gameOver = false;
    private int yPos = Mapa.ALTO_BORDE, direccion = -1;
    private final int stopYPos = 250;
    private Ventana ventana;
    private static int stage = 1;
    private int numIA;
    private static final int goal = 10;
    public static int numEnemigos = goal;

    public Tablero(Ventana ventana) 
    {
        this.ventana = ventana;
        timer = new Timer(DELAY, this);
        timer.start();
        initBoard();
    }

    private void initBoard() 
    {
        stage = 1;
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        numIA = 0;
        tanque = new Tanque(INIT_PLAYER_X, INIT_PLAYER_Y, 4);
        initBloques();
        Colisiones.loadColisiones(bloques, animaciones);
        RecursosTablero.loadBoardUtility(enemigo, bloques, animaciones, powerUps, tanque);
    }

    public void initBloques() 
    {
        int[][] mapa = Mapa.getMap(stage);
        Sonidos.comenzarEscenario();
        int tipo;
        for (int x = 0; x < mapa.length; x++) 
        {
            for (int y = 0; y < mapa[0].length; y++) 
            {
                tipo = mapa[x][y];
                TipoBloque bTipo = TipoBloque.getTypeFromInt(tipo);
                switch (bTipo) 
                {
                    case LADRILLO:
                        bloques.add(new Ladrillo(y * 16, x * 16));
                        break;
                    case METAL:
                        bloques.add(new Metal(y * 16, x * 16));
                        break;
                    case BASE:
                        bloques.add(new Base(y * 16, x * 16));
                        break;
                    case AGUA:
                        bloques.add(new Agua(y * 16, x * 16));
                        break;
                    case ARBUSTO:
                        bloques.add(new Arbusto(y * 16, x * 16));
                        break;
                    case BORDE:
                        bloques.add(new Borde(y * 16, x * 16));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        drawObjetos(g);
        drawMuro(g);
        endGame(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void checkGameOver() 
    {
        if (tanque.getSalud() < 0)
            setFinDelJuego();
    }

    private void drawObjetos(Graphics g) 
    {
        for (TanqueIA tanqueIA : enemigo) 
        {
            if (tanqueIA.isVisible())
                g.drawImage(tanqueIA.getImage(), tanqueIA.getX(), tanqueIA.getY(), this);
        }
        if (tanque.isVisible())
            g.drawImage(tanque.getImage(), tanque.getX(), tanque.getY(), this);
        ArrayList<Bala> balas = new ArrayList<>();
        balas.addAll(tanque.getBalas());
        for (TanqueIA tanqueIA : enemigo) 
        {
            balas.addAll(tanqueIA.getBalas());
        }

        for (Bala b : balas) 
        {
            if (b.isVisible())
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
        }
        for (Bloque a : bloques) 
        {
            if (a.isVisible()) 
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
        }
        for (Animacion e : animaciones) 
        {
            if (e.isVisible()) 
                g.drawImage(e.getImage(), e.getX(), e.getY(), this);
        }
        for (PowerUp p : powerUps) 
        {
            if (p.isVisible())
                g.drawImage(p.getImage(), p.getX(), p.getY(), this);
        }
    }

    private void drawMuro(Graphics g) 
    {
        drawEnemigos(g, numEnemigos);
        String ipText = "IP";
        int vidas = tanque.getSalud();
        Font font = loadFont();
        g.setFont(font);
        g.drawString(ipText, initX * 16, 17 * 16);
        
        Image iconoVida = imageInstance.getVidas();
        g.drawImage(iconoVida, initX * 16, 17 * 16, this);
        g.drawString(String.valueOf(vidas < 0 ? 0 : vidas), (initX + 1) * 16,
                     18 * 16);
        Image iconoBandera = imageInstance.getBandera();
        g.drawImage(iconoBandera, initX * 16, 22 * 16, this);
        g.drawString(String.valueOf(stage), (initX + 1) * 16, 25 * 16);
    }

    private void drawEnemigos(Graphics g, int numEnemigos) 
    {
        Image iconoEnemigo = imageInstance.getEnemigo();
        int count = 1;
        int initY = 4;
        for (int i = 0; i < numEnemigos; i++) 
        {
            switch (count) 
            {
                case 0:
                    count = 1;
                    g.drawImage(iconoEnemigo, (initX + 1) * 16, initY * 16, this);
                    initY++;
                    break;
                case 1:
                    count--;
                    g.drawImage(iconoEnemigo, initX * 16, initY * 16, this);
                    break;
                default:
                    break;
            }
        }
    }

    public static void disminuirEnemigos(int num) 
    {
        numEnemigos -= num;
    }

    public void actualizarSprites() 
    {
        spawnTanqueIA();
        spawnPowerUp();
        actualizarTanque();
        actualizarTanqueIA();
        actualizarBalas();
        actualizarBloques();
        actualizarAnimaciones();
        actualizarBloques();
        actualizarPowerUps();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (Menu.getMenuStatus() == false && pausa == true)
            return;
        if (gameOver) 
        {
            timer.stop();
            return;
        }
        actualizarSprites();
        verificarColisiones();
        checkGameOver();
        siguienteNivel();
        repaint();
    }

    public void siguienteNivel() 
    {
        if (enemigo.isEmpty()) 
        {
            if (stage == 35) 
            {
                System.out.println("You win!");
                loadScoreBoard(ventana);
            } else 
            {
                stage += 1;
                numIA = 0;
                numEnemigos = goal;
                clearBoard();
                initBloques();
                Colisiones.loadColisiones(bloques, animaciones);
                RecursosTablero.loadBoardUtility(enemigo, bloques, animaciones, powerUps, tanque);
            }
        }
    }

    public void actualizarAnimaciones() 
    {
        RecursosTablero.actualizarAnimaciones();
    }

    private void actualizarBloques() 
    {
        RecursosTablero.actualizarBloques();
    }

    private void actualizarTanque() 
    {
        RecursosTablero.actualizarTanque();
    }

    private void actualizarTanqueIA() 
    {
        for (TanqueIA tanqueIA : enemigo) {
            
            if (tanqueIA.isVisible()) 
            {
                if (System.currentTimeMillis() - tanqueIA.inicioTiempoCongelado > 5000 && tanqueIA.congelado)
                    tanqueIA.congelado = false;
                if (null != tanqueIA.getDificultad()) 
                    switch (tanqueIA.getDificultad()) 
                    {
                        case "easy":
                            tanqueIA.accionFacil();
                            break;
                        case "normal":
                            tanqueIA.accionNormal(this.tanque);
                            break;
                        case "hard":
                            tanqueIA.accionDificil(this.tanque);
                            break;
                    }
            }
        }
        for (int i = 0; i < enemigo.size(); i++) 
        {
            if (enemigo.get(i).vis == false)
                enemigo.remove(i);
        }
    }

    private void spawnTanqueIA() 
    {
        while (numIA < goal) 
        {
            if (enemigo.size() < 4) 
            {
                boolean powerUp;
                powerUp = (numIA % 4 == 1);
                if (numIA < 2) 
                {
                    RecursosTablero.spawnTanqueIA("easy", powerUp);
                } else if (numIA >= 2 && numIA < 6) 
                {
                    RecursosTablero.spawnTanqueIA("normal", powerUp);
                } else if (numIA >= 6) 
                {
                    RecursosTablero.spawnTanqueIA("hard", powerUp);
                }
                numIA++;
            } else 
            {
                return;
            }
        }
    }

    private void actualizarPowerUps() 
    {
        RecursosTablero.actualizarPowerUps();
    }

    private void spawnPowerUp() 
    {
        RecursosTablero.spawnPowerUp();
    }

    private void actualizarBalasTanque() 
    {
        RecursosTablero.actualizarBalasTanque();

    }

    private void actualizarBalasTanqueIA() 
    {
        RecursosTablero.actualizarBalasTanqueIA();
    }

    private void actualizarBalas() 
    {
        actualizarBalasTanque();
        actualizarBalasTanqueIA();
    }

    public void verificarColisiones() 
    {
        RecursosTablero.verificarColisiones();
    }

    public void endGame(Graphics g) 
    {
        if (gameOver) 
        {
            Timer gameOverTimer = new Timer(80, new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    yPos += direccion;
                    if (yPos == stopYPos) 
                    {
                        direccion = 0;
                    } else if (yPos > getHeight()) 
                    {
                        yPos = getHeight();
                    } else if (yPos < 0) 
                    {
                        yPos = 0;
                        direccion *= -1;
                    }
                    repaint();
                }
            });
            gameOverTimer.setRepeats(true);
            gameOverTimer.setCoalesce(true);
            gameOverTimer.start();
            Font font = loadFont();
            g.setFont(font);
            g.setColor(Color.RED);
            g.drawString("GAME OVER", Mapa.ANCHO_BORDE / 2 - 85, yPos);
            if (yPos == stopYPos) 
            {
                gameOverTimer.stop();
                Timer sorceBoardTimer = new Timer(3000, new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        loadScoreBoard(ventana);
                    }
                });
                sorceBoardTimer.setRepeats(false);
                sorceBoardTimer.start();
            }
        }
    }

    public void loadScoreBoard(Ventana ventana) 
    {
        ventana.getPanel().removeAll();
        ScoreBoard scoreBoard = new ScoreBoard(ventana);
        scoreBoard.setBackground(Color.BLACK);
        ventana.getPanel().add(scoreBoard);
        scoreBoard.requestFocusInWindow();
        Sonidos.estadisticas();
        ventana.setVisible(true);
    }

    public static void setFinDelJuego() 
    {
        System.out.println("Game Over Played");
        Sonidos.gameOver();
        gameOver = true;
    }

    public static void restartGame() 
    {
        gameOver = false;
    }

    public void clearBoard() 
    {
        animaciones = new ArrayList<>();
        bloques = new ArrayList<>();
        powerUps = new ArrayList<>();
        actualizarSprites();
        resetPosicionTanque(tanque, 2);
        loadColisiones(bloques, animaciones);
    }

    public static int getStage() 
    {
        return stage;
    }

    private class TAdapter extends KeyAdapter 
    {
        @Override
        public void keyReleased(KeyEvent e) 
        {
            tanque.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) 
        {
            tanque.keyPressed(e);
            if (e.getKeyCode() == KeyEvent.VK_ENTER) 
            {
                if (!pausa)
                    Sonidos.pausa();
                pausa = !pausa;
            }
        }
    }
}