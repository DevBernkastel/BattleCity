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
 * Archivo: ScoreBoard
 * Descripción: Clase para el diseño del ScoreBoard
 *
 *************************************************************/

package Main;

import static Main.Menu.loadFont;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ScoreBoard extends JPanel implements ActionListener, KeyListener
{
    private Ventana ventana;
    private int stage, cantidadNumeroTanque;
    private int totalScore = 0;
    private final int SHIFT = 80;
    private JButton restartButton;
    private final Imagenes imageInstance = Imagenes.getInstance();
    private int[] scoreList = {0, 0, 0, 0};
    private int[] listaTanque = {0, 0, 0, 0};

    public ScoreBoard(Ventana ventana) 
    {
        this.ventana = ventana;
        this.setFocusable(true);
        ventana.setForeground(Color.BLACK);
        this.setLayout(null);
        restartButton = new JButton();
        restartButton.setText("Restart");
        this.add(restartButton);
        restartButton.setBounds(400, 400, 100, 30);
        restartButton.addActionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        cargarScore();
        stage = Tablero.getStage();
        super.paintComponent(g);
        Font font = loadFont();
        ArrayList<Image> listaTanques = new ArrayList<>(Arrays.asList(imageInstance.getTanqueBasico(), imageInstance.getTanqueRapido(), imageInstance.getTanquePoderoso(), imageInstance.getTanqueArmado()));

        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("STAGE   " + String.valueOf(stage), 97 + SHIFT, 60);

        g.setColor(Color.RED);
        g.drawString("1-PLAYER", 37 + SHIFT, 95);

        g.setColor(Color.orange);
        g.drawString(String.valueOf(totalScore), 121 + SHIFT, 130);

        for (int i = 0; i < 4; i++) 
        {
            g.drawImage(listaTanques.get(i), 226 + SHIFT, 160 + (i * 45), this);
            g.drawImage(imageInstance.getFlecha(), 206 + SHIFT, 168 + (i * 45), this);
        }
        for (int i = 0; i < 4; i++) 
        {
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(scoreList[i]), 55 + SHIFT, 180 + (i * 45));
            g.drawString("PTS", 115 + SHIFT, 180 + (i * 45));
        }
        for (int i = 0; i < 4; i++) 
        {
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(listaTanque[i]), 180 + SHIFT, 180 + (i * 45));
        }
        g.drawLine(170, 330, 307, 330);
        g.drawString("TOTAL", 85 + SHIFT, 355);
        g.drawString(String.valueOf(cantidadNumeroTanque), 180 + SHIFT, 355);
        g.setFont(font);
        g.setColor(Color.WHITE);
    }

    public void cargarScore() 
    {
        for (int i = 0; i < 4; i++) 
        {
            int[] cantidadTanquesEnemigos = Colisiones.getCantidadTanquesEnemigos();
            listaTanque[i] = cantidadTanquesEnemigos[i];
        }
        for (int i = 0; i < 4; i++) 
        {
            scoreList[i] = listaTanque[i] * 100 * (i + 1);
        }
        for (Integer score : scoreList) 
        {
            totalScore += score;
        }
        for (Integer num : listaTanque) 
        {
            cantidadNumeroTanque += num;
        }
    }

    public void restart() 
    {
        Tablero.gameOver = false;
        Colisiones.resetPuntuacion();
        cargarMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == restartButton)
            restart();
    }

    private void cargarMenu() 
    {
        ventana.getPanel().removeAll();
        Menu menu = new Menu(ventana);
        menu.revalidate();
        menu.repaint();
        ventana.getPanel().add(menu);
        menu.requestFocusInWindow();
        ventana.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            cargarMenu();
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            cargarMenu();
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            cargarMenu();
    }
}
