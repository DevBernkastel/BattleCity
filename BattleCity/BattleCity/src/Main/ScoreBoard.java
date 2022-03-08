/** ***********************************************************
 * ITM - Departamento de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 * Projecto: BattleCity
 * Paquete: Main
 * Archivo: ScoreBoard
 * Descripción: Clase para el diseño del ScoreBoard
 *
 ************************************************************ */
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ScoreBoard extends JPanel implements ActionListener, KeyListener {

    private Ventana ventana;
    private int stage, cantidadNumeroTanque;
    public int totalScore = 0;
    private final int SHIFT = 80;
    private JButton restartButton;
    private final Imagenes imageInstance = Imagenes.getInstance();
    private int[] scoreList = {0, 0, 0, 0};
    private int[] listaTanque = {0, 0, 0, 0};
    public PreparedStatement ps;
    public ResultSet rs;
    JButton btnMostrarPuntajes;
    JButton btnGuardarJugador;

    public ScoreBoard(Ventana ventana) {
        Font font = loadFont();
        font = font.deriveFont(java.awt.Font.PLAIN, 12);
        this.ventana = ventana;
        this.setFocusable(true);
        ventana.setForeground(Color.BLACK);
        this.setLayout(null);
        
        restartButton = new JButton();
        restartButton.setText("Reiniciar");
        this.add(restartButton);
        restartButton.setBounds(320, 400, 200, 30);
        restartButton.setFont(font);
        restartButton.setForeground(new java.awt.Color(0, 0, 204));
        restartButton.setContentAreaFilled(false);
        restartButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        restartButton.addActionListener(this);
        
        btnMostrarPuntajes = new JButton();
        btnMostrarPuntajes.setText("Puntajes");
        this.add(btnMostrarPuntajes);
        btnMostrarPuntajes.setBounds(0, 400, 150, 30);
        btnMostrarPuntajes.setFont(font);
        btnMostrarPuntajes.setForeground(new java.awt.Color(0, 0, 204));
        btnMostrarPuntajes.setContentAreaFilled(false);
        btnMostrarPuntajes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMostrarPuntajes.addActionListener(this);

        btnGuardarJugador = new JButton();
        btnGuardarJugador.setText("Guardar_Jugador");
        btnGuardarJugador.setBounds(100, 400, 300, 30);
        btnGuardarJugador.setFont(font);
        btnGuardarJugador.setForeground(new java.awt.Color(0, 0, 204));
        btnGuardarJugador.setContentAreaFilled(false);
        btnGuardarJugador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        this.add(btnGuardarJugador);
        btnGuardarJugador.addActionListener(this);

    }

    @Override
    public void paintComponent(Graphics g) {
        cargarScore();
        stage = Tablero.getStage();
        super.paintComponent(g);
        Font font = loadFont();
        ArrayList<Image> listaTanques = new ArrayList<>(Arrays.asList(imageInstance.getTanqueBasico(), imageInstance.getTanqueRapido(), imageInstance.getTanquePoderoso(), imageInstance.getTanqueArmado()));

        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("STAGE   " + String.valueOf(stage), 97 + SHIFT, 60);

        g.setColor(Color.RED);
        g.drawString(Menu.jugador, 37 + SHIFT, 95);

        g.setColor(Color.orange);
        g.drawString(String.valueOf(totalScore), 121 + SHIFT, 130);

        for (int i = 0; i < 4; i++) {
            g.drawImage(listaTanques.get(i), 226 + SHIFT, 160 + (i * 45), this);
            g.drawImage(imageInstance.getFlecha(), 206 + SHIFT, 168 + (i * 45), this);
        }
        for (int i = 0; i < 4; i++) {
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(scoreList[i]), 55 + SHIFT, 180 + (i * 45));
            g.drawString("PTS", 115 + SHIFT, 180 + (i * 45));
        }
        for (int i = 0; i < 4; i++) {
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(listaTanque[i]), 180 + SHIFT, 180 + (i * 45));
        }
        g.drawLine(170, 330, 307, 330);
        g.drawString("TOTAL", 85 + SHIFT, 355);
        g.drawString(String.valueOf(cantidadNumeroTanque), 180 + SHIFT, 355);
        g.setFont(font);
        g.setColor(Color.WHITE);

        //this.cargarDatosBD();
    }

    public void cargarScore() {
        for (int i = 0; i < 4; i++) {
            int[] cantidadTanquesEnemigos = Colisiones.getCantidadTanquesEnemigos();
            listaTanque[i] = cantidadTanquesEnemigos[i];
        }
        for (int i = 0; i < 4; i++) {
            scoreList[i] = listaTanque[i] * 100 * (i + 1);
        }
        for (Integer score : scoreList) {
            totalScore += score;
        }
        for (Integer num : listaTanque) {
            cantidadNumeroTanque += num;
        }

    }

    public void restart() {
        Tablero.gameOver = false;
        Colisiones.resetPuntuacion();
        cargarMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restartButton) {
            restart();
        } else if (e.getSource() == btnMostrarPuntajes) {

            //abrimos la ventana de puntajes
            Puntajes p = new Puntajes();
            p.setVisible(true);
        }

        if (e.getSource() == btnGuardarJugador) {
            this.guardarJugador();

        }

    }

    private void cargarMenu() {
        ventana.getPanel().removeAll();
        Menu menu = new Menu(ventana);
        menu.revalidate();
        menu.repaint();
        ventana.getPanel().add(menu);
        menu.requestFocusInWindow();
        ventana.setVisible(true);

        //this.cargarDatosBD();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarMenu();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarMenu();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarMenu();
        }
    }

    //este método se encarga de cargar los datos a la base 
    public void cargarDatosBD() {

        //instanciamos la clase conexion
        Conectar conectar = new Conectar();
        Connection cn = conectar.conectarMYSQL();

        try {
            String query = "insert into mejores_puntajes(nombreJugador,puntuacionJugador)values(?,?)";

            ps = cn.prepareStatement(query);

            ps.setString(1, Menu.jugador);
            ps.setInt(2, totalScore);

            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                //JOptionPane.showMessageDialog(null, "Registrado insertado correctamente");
            } else {
                //JOptionPane.showMessageDialog(null, "El registro no fue insertado");
            }

        } catch (SQLException e) {
            System.err.println("Ha ocurrido un error imprevisto");
            e.printStackTrace(System.out);
        }
    }

    public void guardarJugador() {
        this.cargarDatosBD();
    }

}
