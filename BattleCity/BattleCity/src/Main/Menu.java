 /*************************************************************
 * ITM - Departamento de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 * Projecto: BattleCity
 * Paquete: Main
 * Archivo: Menu
 * Descripción: Clase para el diseño del menú de inicio
 *
 *************************************************************/

package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Menu extends JPanel implements ActionListener, KeyListener 
{
    public Image colorFondo, tanque, arbusto;
    public Ventana ventana;
    private int yPos = Mapa.ALTO_BORDE;
    private int direccion = -1;
    private final int stopYPos = 100;
    private static boolean menuStatus = true;
    private final Imagenes imageInstance = Imagenes.getInstance();
    static public String jugador; 

    public Menu(Ventana ventana) 
    {
        this.ventana = ventana;
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setLayout(null);
        cargarImagenesMenu();
        addTimer();
    }

    private void addTimer() 
    {
        Timer timer = new Timer(10, new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                yPos += direccion;
                if (yPos == stopYPos)
                    direccion = 0;
                else if (yPos + colorFondo.getHeight(null) > getHeight())
                    yPos = getHeight() - colorFondo.getHeight(null);
                else if (yPos < 0) 
                {
                    yPos = 0;
                    direccion *= -1;
                }
                repaint();
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }

    private void cargarImagenesMenu() 
    {
        colorFondo = imageInstance.getBackground();
        tanque = imageInstance.getTanque();
        arbusto = imageInstance.getArbusto2();
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Font font = loadFont();
        g.drawImage(colorFondo, Mapa.ANCHO_BORDE / 2 - colorFondo.getWidth(null) / 2 - 10, yPos, this);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("1 PLAYER", Mapa.ANCHO_BORDE / 2 - 56, yPos + colorFondo.getHeight(null) + 50);
        if (yPos == stopYPos)
            drawMenuComponentes(g);
    }

    private void drawMenuComponentes(Graphics g) 
    {
        g.drawImage(arbusto, 10, 50, this);
        g.drawImage(arbusto, 10, 90, this);
        g.drawImage(tanque, Mapa.ANCHO_BORDE / 2 - 90, yPos + colorFondo.getHeight(null) + 25, this);
        Font font = loadFont();
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("PRESS ENTER", Mapa.ANCHO_BORDE / 2 - 80, Mapa.ALTO_BORDE * 4 / 5 + 25);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    public static Font loadFont() {
        Font font = null;
        try 
        {
            font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, new File("prstart.ttf"));
            font = font.deriveFont(java.awt.Font.PLAIN, 15);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            
        } catch (FontFormatException | IOException ex) 
        {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return font;
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
        if (e.getKeyCode() == e.VK_ENTER) 
        {
            menuStatus = false;
            ventana.getPanel().removeAll();
            Tablero panel = new Tablero(ventana);
            ventana.getPanel().add(panel);
            panel.requestFocusInWindow();
            ventana.setVisible(true);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        if (e.getKeyCode() == e.VK_ENTER)
            cargarTablero();
    }

    public void cargarTablero() 
    {
            //pedimos el nombre del usuario
         jugador = JOptionPane.showInputDialog(ventana, "Nombre del jugador","Escribe aquí"); 
         while(jugador == null || jugador.compareTo("Escribe aquí") == 0 || jugador.compareTo(" ") == 0){
              
            jugador = JOptionPane.showInputDialog(ventana, "Debes ingresar un jugador"); 
         }
                 
        
        menuStatus = false;
        ventana.getPanel().removeAll();
        Tablero panel = new Tablero(ventana);
        panel.revalidate();
        panel.repaint();
        ventana.getPanel().add(panel);
        panel.requestFocusInWindow();
        ventana.setVisible(true);
        
    
        
        
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        if (e.getKeyCode() == e.VK_ENTER)
            cargarTablero();
    }

    public static boolean getMenuStatus() 
    {
        return menuStatus;
    }   
}
