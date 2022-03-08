 /*************************************************************
 * ITM - Departamento de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 * Projecto: BattleCity
 * Paquete: Main
 * Archivo: Sonidos
 * Descripción: Clase para el desarrollo de los sonidos
 *
 *************************************************************/

package Main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sonidos 
{
    private static Clip balaLadrilloSE, balaTanqueSE, disparoSE, explosion1SE, explosion2SE, comenzarEscenarioSE, pausaSE, aparicionPowerUpSE, tomarPowerUpSE, gameOverSE, estadisticasSE;
    private static boolean inicializado = false;

    public static void inicializar() {
        System.out.println("INICIALIZAR");
        try 
        {
            File bBse = new File("Sonidos/bullet_hit_2.wav");
            balaLadrilloSE = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(bBse);
            balaLadrilloSE.open(ais);
            balaLadrilloSE.setFramePosition(balaLadrilloSE.getFrameLength());

            File bTse = new File("Sonidos/bullet_hit_1.wav");
            balaTanqueSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(bTse);
            balaTanqueSE.open(ais);
            balaTanqueSE.setFramePosition(balaTanqueSE.getFrameLength());

            File fSE = new File("Sonidos/bullet_shot.wav");
            disparoSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(fSE);
            disparoSE.open(ais);
            disparoSE.setFramePosition(disparoSE.getFrameLength());

            File ex1 = new File("Sonidos/explosion_1.wav");
            explosion1SE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(ex1);
            explosion1SE.open(ais);
            explosion1SE.setFramePosition(explosion1SE.getFrameLength());

            File ex2 = new File("Sonidos/explosion_2.wav");
            explosion2SE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(ex2);
            explosion2SE.open(ais);
            explosion2SE.setFramePosition(explosion2SE.getFrameLength());

            File stageStart = new File("Sonidos/stage_start.wav");
            comenzarEscenarioSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(stageStart);
            comenzarEscenarioSE.open(ais);
            comenzarEscenarioSE.setFramePosition(comenzarEscenarioSE.getFrameLength());

            File pausa = new File("Sonidos/pause.wav");
            pausaSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(pausa);
            pausaSE.open(ais);
            pausaSE.setFramePosition(pausaSE.getFrameLength());

            File powerUpAppear = new File("Sonidos/powerup_appear.wav");
            aparicionPowerUpSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(powerUpAppear);
            aparicionPowerUpSE.open(ais);
            aparicionPowerUpSE.setFramePosition(aparicionPowerUpSE.getFrameLength());

            File powerUpPickup = new File("Sonidos/powerup_pick.wav");
            tomarPowerUpSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(powerUpPickup);
            tomarPowerUpSE.open(ais);
            tomarPowerUpSE.setFramePosition(tomarPowerUpSE.getFrameLength());

            File game_over = new File("Sonidos/game_over.wav");
            gameOverSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(game_over);
            gameOverSE.open(ais);
            gameOverSE.setFramePosition(gameOverSE.getFrameLength());

            File estadisticas = new File("Sonidos/statistics_1.wav");
            estadisticasSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(estadisticas);
            estadisticasSE.open(ais);
            estadisticasSE.setFramePosition(estadisticasSE.getFrameLength());
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) 
        {
            Logger.getLogger(Sonidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        inicializado = true;
    }

    public static void balaGolpeaLadrillo() 
    {
        if (inicializado)
            balaLadrilloSE.loop(1);
        else
        {
            inicializar();
            balaLadrilloSE.loop(1);
        }
    }

    public static void balaGolpeaTanque() 
    {
        if (inicializado)
            balaTanqueSE.loop(1);
        else 
        {
            inicializar();
            balaTanqueSE.loop(1);
        }

    }

    public static void sonidoDisparo() 
    {
        if (inicializado)
            disparoSE.loop(1);
        else 
        {
            inicializar();
            disparoSE.loop(1);
        }
    }

    public static void sonidoExplosion1() 
    {
        if (inicializado)
            explosion1SE.loop(1);
        else 
        {
            inicializar();
            explosion1SE.loop(1);
        }
    }

    public static void sonidoExplosion2() 
    {
        if (inicializado)
            explosion2SE.loop(1);
        else 
        {
            inicializar();
            explosion2SE.loop(1);
        }
    }

    public static void comenzarEscenario() 
    {
        if (inicializado)
            comenzarEscenarioSE.loop(1);
        else
        {
            inicializar();
            comenzarEscenarioSE.loop(1);
        }
    }

    public static void pausa() 
    {
        if (inicializado)
            pausaSE.loop(1);
        else 
        {
            System.out.println("pausa");
            inicializar();
            pausaSE.loop(1);
        }
    }

    public static void tomarPowerUp() 
    {
        if (inicializado)
            tomarPowerUpSE.loop(1);
        else 
        {
            inicializar();
            tomarPowerUpSE.loop(1);
        }
    }

    public static void gameOver() 
    {
        if (inicializado)
            gameOverSE.loop(1);
        else 
        {
            inicializar();
            gameOverSE.loop(1);
        }
    }

    public static void estadisticas() 
    {
        if (inicializado)
            estadisticasSE.loop(1);
        else 
        {
            inicializar();
            estadisticasSE.loop(1);
        }
    }
}