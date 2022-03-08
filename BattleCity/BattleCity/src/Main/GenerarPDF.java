package Main;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenerarPDF {
    
    public static void crearPDF() {
        
        Document documento = new Document(PageSize.A4, 35, 30, 50, 50);        
        
        try {
            //creamos el archivo
            FileOutputStream archivo = new FileOutputStream("Battlecity.pdf");
            //obtenemos la instancia del pdfWriter
            PdfWriter.getInstance(documento, archivo);
            //abrimos el archivo 
            documento.open();
            
             //creamos una imagen
            Image tanque = null;

            //obtenemos la imagen
            tanque = Image.getInstance("tanque.jpg");
            tanque.setAlignment(Element.ALIGN_CENTER);
            tanque.scaleToFit(100,100);
            //tanque.scaleAbsolute(100f, 60f);
            documento.add(tanque); 
            
            //creamos una fuente para el titulo del documento
            Font contenido = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
            
             // Creacion del parrafo
            Paragraph miParrafo = new Paragraph();

            // Agregar un titulo con su respectiva fuente
            miParrafo.add(new Phrase("Datos de BattleCity:", contenido));
            miParrafo.setAlignment(Element.ALIGN_CENTER);
            documento.add(miParrafo); 
            
            miParrafo.add(new Phrase(Chunk.NEWLINE));
            miParrafo.add(new Phrase(Chunk.NEWLINE));
            
            //-----------------------------------------------------------
            PdfPTable miTabla = new PdfPTable(3);            
            miTabla.addCell("ID jugador");
            miTabla.addCell("Nombre del jugador");
            miTabla.addCell("Puntuación del jugador");

            //creamos la conexion a la base de datos
            Conectar c = new Conectar();            
            Connection connection = c.conectarMYSQL();            
            
            PreparedStatement ps;            
            ResultSet rs;            
            String query = "select idJugador,nombreJugador,puntuacionJugador from mejores_puntajes";            
            
            ps = connection.prepareStatement(query);            
            
            rs = ps.executeQuery();            
            
            if (rs.next()) {
                
                do {      
                    miTabla.addCell(String.valueOf(rs.getInt(1))); 
                    miTabla.addCell(rs.getString(2)); 
                    miTabla.addCell(String.valueOf(rs.getInt(3))); 
                } while (rs.next());
                documento.add(miTabla); 
            }
            
            //cerramos el archivo y el documento
            documento.close();
            archivo.close();
        } catch (DocumentException | FileNotFoundException | SQLException e) {
            System.err.println("ha ocurrido un error");
            e.printStackTrace(System.out);
        } catch (IOException ex) {
             System.err.println("ha ocurrido un error");
            ex.printStackTrace(System.out);
        }
        
    }
    
}
/*
        //creamos el documento con los margenes
        Document documento = new Document(PageSize.A4, 35, 30, 50, 50);

        try {
            //generemos el documento 
            FileOutputStream archivo = new FileOutputStream("Battlecity.pdf");

            //obtenemos la instancia del pdfWriter
            PdfWriter.getInstance(documento, archivo);

            //abrimos el documento
            documento.open();

            //creamos una imagen
            Image tanque = null;

            //obtenemos la imagen
            tanque = Image.getInstance("tanque.jpg");
            tanque.setAlignment(Element.ALIGN_CENTER);
            tanque.scaleToFit(100,100);
            //tanque.scaleAbsolute(100f, 60f);
            documento.add(tanque); 

            //creamos una fuente para el titulo del documento
            Font contenido = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);

         
            // Creacion del parrafo
            Paragraph miParrafo = new Paragraph();

            // Agregar un titulo con su respectiva fuente
            miParrafo.add(new Phrase("Datos de BattleCity:", titulo));
            miParrafo.setAlignment(Element.ALIGN_CENTER);
            documento.add(miParrafo); 
            
            //-----------------------------------------------
            
             //creamos la tabla de datos
             PdfPTable tabla = new PdfPTable(3); 
             tabla.setWidthPercentage(100);
             
             PdfPCell celda1 = new PdfPCell(new Phrase("idJugador", contenido)); 
             PdfPCell celda2 = new PdfPCell(new Phrase("Nombre del jugador", contenido)); 
             PdfPCell celda3 = new PdfPCell(new Phrase("Putuación del jugador", contenido)); 
            
            celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda3.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            celda1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            celda2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            celda3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            
            //agregamos las celdas a la tabla 
            tabla.addCell(celda1);
            tabla.addCell(celda2);
            tabla.addCell(celda3);
            
            
            //--------------------agregamos los registros
            
           //creamos la conexion a la base de datos
            Conectar c = new Conectar(); 
            Connection connection = c.conectarMYSQL(); 
            
            PreparedStatement ps; 
            ResultSet rs; 
            String query = "select idJugador,nombreJugador,puntuacionJugador from mejores_puntajes"; 
            
           ps = connection.prepareStatement(query); 
            
           rs = ps.executeQuery(); 
            
           //leemos el contenido de la nuestra base y procedemos a vaciar en la tabla
            while(rs.next()){
               tabla.addCell(String.valueOf(rs.getInt(celda1)));
            
            }
            
            
            
            
            //agregamos la tabla al documento
            documento.add(tabla); 
            
            
            
            
            
            
            
            
            
         
     
            //cerramos el documento y el archivo
            documento.close();
            archivo.close();
            
           
        } catch (DocumentException | IOException | SQLException e) {
            System.err.println("ha ocurrido un error");
            e.printStackTrace(System.out);
        }

    }
 */
