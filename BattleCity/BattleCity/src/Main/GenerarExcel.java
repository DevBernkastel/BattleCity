 /*************************************************************
 * ITM - Departamento de Ingeniería en Sistemas Computacionales
 * Semestre Agosto - Diciembre 2020
 * Projecto: BattleCity
 * Paquete: Main
 * Archivo: Colisiones
 * Descripción: Clase para generar el excel  
 *
 *************************************************************/

package Main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GenerarExcel {

    public static void crearExcel() {
        //creamos el libro 
        Workbook libro = new XSSFWorkbook();
        Sheet hoja = libro.createSheet("datos del juego");
        //creamos la conexion 
        Conectar c = new Conectar();
        PreparedStatement ps;
        ResultSet rs;

        //creamos la fila de las cabeceras
        String[] cabecera = {"IDJugador", "Nombre del jugador", "Puntuación del jugador"};

        Row filaCabecera = hoja.createRow(0);  //creamos la primera fila

        for (int i = 0; i < cabecera.length; i++) {
            Cell celda = filaCabecera.createCell(i);
            celda.setCellValue(cabecera[i]);

        }

        int numFila = 1; //tenemos que especificar que la primera fila fue la cabecera, sigue la otra

        try {
            Connection connection = c.conectarMYSQL();
            String query = "select idJugador,nombreJugador,puntuacionJugador from mejores_puntajes";

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            int numColumnas = rs.getMetaData().getColumnCount(); //obtenemos el numero de columnas del libro

            while (rs.next()) {
                Row filaDatos = hoja.createRow(numFila);

                for (int i = 0; i < numColumnas; i++) {
                    Cell celda = filaDatos.createCell(i);

                    //debemos verificar el tipo de dato que vayamos a escribir en excel
                    if (numColumnas == 0 || numColumnas == 2) {//la columna 0 y 2 son tipo numerico
                        celda.setCellValue(rs.getInt(i + 1));
                    } else {//la celda 1 es tipo cadena de texto
                        celda.setCellValue(rs.getString(i + 1));
                    }

                }

                numFila++;
            }

            FileOutputStream archivo = new FileOutputStream("Battlecity.xlsx");
            libro.write(archivo);
            //cerramos el archivo
            archivo.close();
            
            

        } catch (IOException | SQLException e) {
            System.err.println("ha ocurrido un error");
            e.printStackTrace(System.out);
        }

    }
}
