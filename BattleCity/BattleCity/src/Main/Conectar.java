 /*************************************************************
 * ITM - Departamento de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 * Projecto: BattleCity
 * Paquete: Main
 * Archivo: Colisiones
 * Descripción: Clase para conectar con MySQL 
 *
 *************************************************************/

package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabri
 */
public class Conectar {
    
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/battlecity?autoReconnet=true&&useSSL=false";
    public static final String usuario = "root";
    public static final String contraseña = "Warhammer122599";
    
    
    public Connection conectarMYSQL() {
        Connection conexion = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection(URL, usuario, contraseña);

        } catch (SQLException e) {
            System.err.println("Error, " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexion;
    }
    

}