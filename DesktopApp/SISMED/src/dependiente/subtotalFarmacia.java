package dependiente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JFrame.*;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import sismed.ConexionDB;


/**
 *
 * @author ricar
 */
public class subtotalFarmacia {
    /*public static void main(String[] args){
        ventana4 pantalla=new ventana4();
        pantalla.setVisible(true);
        pantalla.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }*/
    
}

final class ventana4 {
    public Image imagenFondo;
    public URL fondo;
    private int monto;
    private ArrayList<String> nombres;
    private ArrayList<Integer> cantidades;
    private ArrayList<Integer> precios;
    private JFrame padre;
    public ventana4( int monto, ArrayList<Integer> precios, ArrayList<String> nombres, ArrayList<Integer> cantidades, JFrame padre ){
        this.padre = padre;
        this.precios = precios;
        this.monto = monto;
        this.nombres = nombres;
        this.cantidades = cantidades;
        boolean abastece = true;  
        
        //Realiza la consulta para saber si hay suficiente medicamento en el almacen
        ConexionDB dbCon = new ConexionDB();
        ResultSet rs;
        int stock_actual;
        //Revisa el stock de cada medicamento para saber si es posible abastecer la receta médicas
        for(int i = 0 ; i < nombres.size() ; i++ ){
            rs = dbCon.consulta("SELECT * FROM almacen WHERE idMedicamento='" + nombres.get( i ) + "'");
            try {
                if( rs.next() ){
                    stock_actual =  rs.getInt("stock");
                    if( stock_actual < cantidades.get ( i ) )
                        abastece = false;
                }
            } catch (SQLException ex) {
                System.out.println("EXCEPCION SQL");
            }
        }
        
        System.out.println(" ID's: " + nombres.toString() + "\nCANTIDADES: " + cantidades.toString());
        if( abastece == true ){
            //Crea la cadena de salida:
            String aux = "ID        Cantidad      Precio\n";
            for( int i = 0 ; i < nombres.size() ; i++ ){  
                aux += nombres.get( i ) + "          " + cantidades.get( i ) + "               $" + (precios.get( i ) * cantidades.get( i )) + "\n";
            }
            String si = "Aceptar"; //Aqui puede ir cualquier nombre 
            String cancelar ="Cancelar"; 
            Object[] options = {si, cancelar}; 
            int opc = JOptionPane.showOptionDialog(null, aux + "Subtotal: $" + monto, "Subtotal", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, cancelar); 
            
            if( opc == JOptionPane.OK_OPTION ){
                //ACTUALIZA LA BASE DE DATOS QUITANDOLE AL STOCK INICIAL
                int stock, newstock;
                for( int i = 0 ; i < nombres.size() ; i++ ){
                    rs = dbCon.consulta("SELECT * FROM almacen WHERE idMedicamento='" + nombres.get( i ) + "'");
                    try {
                        if( rs.next() ){
                            stock = rs.getInt("stock");
                            newstock = stock - cantidades.get( i );
                            if( newstock == 0 )
                                JOptionPane.showInternalMessageDialog( null, "ALERTA!\nSe ha agotado el medicamento con ID: " + nombres.get( i ) + "\nFavor de contactar con el proveedor");
                            System.out.println("EL NUEVO STOCK PARA " + nombres.get( i ) + " ES: " + newstock + " UNIDADES" );
                            dbCon.actualizar("UPDATE almacen SET stock=" + newstock + " WHERE idMedicamento='" + nombres.get( i ) + "'");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ventana4.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                generarRecibo( monto, precios, nombres, cantidades);
                //padre.limpiarCampos();
            }
            else if( opc == JOptionPane.NO_OPTION ){
            }
            else if( opc == JOptionPane.CANCEL_OPTION ){
                
            }
            
        }
        else{
            JOptionPane.showMessageDialog( null, "NO ES POSIBLE ABASTECER LA RECETA.\nFAVOR DE REVSAR EL STOCK DE CADA MEDICAMENTO");
            ventana2 pantalla=new ventana2();
            padre.dispose();
            pantalla.setVisible(true);
            pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
        }
    }   
   
    private class accionConfirmar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            
        }
    }  
    private class cancelar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
        }
    }  
    
    public void generarRecibo( int monto, ArrayList<Integer> precios, ArrayList<String> nombres, ArrayList<Integer> cantidades ){
        ConexionDB dbCon = new ConexionDB();
        
        try {
            //Genera un número aleatorio hasta que no haya ninguna entrada con ese ID
            int rnd;
            do{
                rnd = (int) (Math.random() * 100000 + 1);
            }while( dbCon.consulta("SELECT * FROM transacciones WHERE id='" + rnd + "'").next() );
            
            dbCon.insertar("INSERT INTO transacciones(id, tipo, monto, status) VALUES (" + rnd +", 'Receta', " + monto + ",'Pendiente')");
            
            
            //Generar el documento de WORD del ticket
            XWPFDocument receta = new XWPFDocument();
            XWPFParagraph parrafo =  receta.createParagraph();
            XWPFRun run = parrafo.createRun();
            run.setText("Recibo #" + rnd );
            run.setFontSize(12);
            run.setFontFamily("Helvetica");
            // es para dibujar y tipo de linea en debajo de titulo
            run.setUnderline(UnderlinePatterns.THICK);
            parrafo.setAlignment(ParagraphAlignment.CENTER);
            // declaramos otra para el parrafo
            XWPFParagraph parrafo2 =  receta.createParagraph();
            XWPFRun runs = parrafo2.createRun();
            runs.addBreak();
            runs.setFontSize(13);
            runs.setFontFamily("Arial");
            //Agrega las entradas por cada medicamento
            for( int i = 0 ; i < nombres.size() ; i++ ){
                runs.setText("  " + nombres.get( i ) + "           " + cantidades.get( i ) +  " UNIDADES        " + " ->  $" + (precios.get( i ) * cantidades.get( i ))  + ".00" );
                runs.addBreak();
            }
            parrafo2.setAlignment(ParagraphAlignment.LEFT);
            //Otro parrafo
            XWPFParagraph parrafo3 =  receta.createParagraph();
            XWPFRun run3 = parrafo3.createRun();
            run3.setFontSize(15);
            run3.setText("TOTAL:  $" + monto + ".00");
            run3.addBreak();
            parrafo3.setAlignment(ParagraphAlignment.RIGHT);
            //Otro parrafo
            XWPFParagraph parrafo4 =  receta.createParagraph();
            XWPFRun run4 = parrafo4.createRun();
            run4.setFontSize( 20 );
            run4.setText("( SELLO DE PAGO )");
            parrafo4.setAlignment(ParagraphAlignment.CENTER);
            
            //Guarda el documento como prueba.docx
            try{
                FileOutputStream output = new FileOutputStream("prueba.docx");
                receta.write(output);
                output.close();
                
                //Ésta linea imprime directamente el documento previamente creado:
                Desktop.getDesktop().print(new File("prueba.docx"));
            }
            catch(Exception e){
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ventana4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}

