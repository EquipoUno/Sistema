/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medico;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.*;
import dependiente.buscarSustancia;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author GerardoAramis
 */
public class Receta extends JFrame {
    private JPanel panel;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private JTextField nom_sus;
    private JLabel notasl, nombrel, dosis, buscar_tit, receta_tit, idl;
    private JButton buscar_nom, buscar_sus, regresar, generar, agregar;
    private ArrayList<JTextField> id, notas;
    private ArrayList<JButton> eliminar;
    
    private int ancho = 700, altura = 550, x = 150, y = 20, filas = 0;
    public static void main( String s[] ){
        new Receta();
    }
    public Receta(){
        setLocation( (int)dim.getWidth()/2 - (int)ancho/2, (int)dim.getHeight()/2 - (int)altura/2 );
        setSize( ancho, altura );
        setTitle( "Generar receta" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        initPanel();
        setLayout( null );
        add( panel );
        setVisible( true );
    }
    public void initPanel(){
        panel = new JPanel();
        panel.setLayout( null );
        panel.setSize( ancho, altura );
        id = new ArrayList<>();
        notas = new ArrayList<>();
        eliminar = new ArrayList<>();
        nom_sus = new JTextField();
        notas.add ( new JTextField() );
        id.add( new JTextField() );
        generar = new JButton("Generar receta");
        regresar = new JButton("Regresar");
        buscar_sus = new JButton("Buscar por sustancia");
        buscar_nom = new JButton("Buscar por nombre");
        agregar = new JButton("Agregar medicamento");
        eliminar.add( new JButton("Eliminar") );
        notasl = new JLabel("[Notas]:");
        dosis = new JLabel("Dosis");
        buscar_tit = new JLabel("___________BUSCAR______________");
        receta_tit = new JLabel("___________RECETA______________");
        nombrel = new JLabel("Nombre o sustancia:");
        idl = new JLabel("ID de Medicamento:");
        
        buscar_tit.setBounds( 200, 5, x*2, y );
        receta_tit.setBounds( 200, 105, x*2, y );
        nom_sus.setBounds( 30, 40, x, y );
        nombrel.setBounds( 35, 22, x, y );
        buscar_sus.setBounds( 190, 40, x+20, y );
        buscar_nom.setBounds( 190, 60, x+20, y );
        idl.setBounds( 20, 160, x, y );
        dosis.setBounds( 240, 150, x, y );
        notasl.setBounds( 235, 163, x, y );
        agregar.setBounds( 400, 130, x+50, y );
        generar.setBounds( 550, 430, 130, 70);
        id.get( 0 ).setBounds( 20, 180 + filas*20, x, y);
        notas.get( 0 ).setBounds( 200, 180 + filas*20, x*2, y);
        eliminar.get( 0 ).setBounds( 510, 180 + filas*20, 100, y );
        filas++;
        
        buscar_sus.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent ae ){
                String nom = nom_sus.getText();
                if( nom.equals( "" ) )
                    JOptionPane.showMessageDialog( null, "Por favor introduce un nombre o sustancia activa");
                else
                    new buscarSustancia( nom , "ACTIVO");
            }
        });
        buscar_nom.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent ae ){
                String nom = nom_sus.getText();
                if( nom.equals( "" ) )
                    JOptionPane.showMessageDialog( null, "Por favor introduce un nombre o sustancia activa");
                else
                    new buscarSustancia( nom , "NOMBRE");
            }
        });
        agregar.addActionListener( new eventoAgregar());
        generar.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReceta();
            }
        });
        eliminar.get( 0 ).addActionListener( new eventoEliminar());
        
        
        panel.add( receta_tit );
        panel.add( buscar_tit );
        panel.add( nom_sus );
        panel.add( notas.get( 0 ) );
        panel.add( id.get( 0 ) );
        panel.add( generar );
        panel.add( regresar );
        panel.add( buscar_sus );
        panel.add( buscar_nom );
        panel.add( agregar );
        panel.add( eliminar.get( 0 ) );
        panel.add( notasl );
        panel.add( dosis );
        panel.add( nombrel );
        panel.add( idl );
        
        
        
    }
    public void repintarElementos(){
        panel.removeAll();
        panel.add( receta_tit );
        panel.add( buscar_tit );
        panel.add( nom_sus );
        panel.add( generar );
        panel.add( regresar );
        panel.add( buscar_sus );
        panel.add( buscar_nom );
        panel.add( agregar );
        panel.add( notasl );
        panel.add( dosis );
        panel.add( nombrel );
        panel.add( idl );
        for( int i = 0 ; i < id.size() ; i++ ){
            id.get( i ).setBounds( 20, 180 + 25*i, x, 20 );
            notas.get( i ).setBounds( 200, 180 + 25*i, x*2, 20 );
            eliminar.get( i ).setBounds( 510, 180 + 25*i, 100, 20 );
            
            panel.add( id.get( i ) );
            panel.add( notas.get( i ) );
            panel.add( eliminar.get( i ) );
        }
        repaint();
    }
    
    public void generarReceta(){
        
        //Generar el documento de WORD del ticket
            XWPFDocument receta = new XWPFDocument();
            XWPFParagraph parrafo =  receta.createParagraph();
            XWPFRun run = parrafo.createRun();
            run.setFontSize( 22 );
            run.setBold( true );
            run.setFontFamily("Helvetica");
            // es para dibujar y tipo de linea en debajo de titulo
            run.setUnderline(UnderlinePatterns.THICK);
            run.setText("Receta médica" );
            parrafo.setAlignment(ParagraphAlignment.CENTER);
            // declaramos otra para el parrafo
            XWPFParagraph tit =  receta.createParagraph();
            XWPFRun runt = tit.createRun();
            runt.addBreak();
            runt.setFontSize(18);
            runt.setFontFamily("Arial");
            runt.setBold( true );
            runt.setText("  ID:                       Dosis/Notas:");
            XWPFParagraph parrafo2 =  receta.createParagraph();
            XWPFRun runs = parrafo2.createRun();
            runs.addBreak();
            runs.setFontSize(15);
            //Agrega las entradas por cada medicamento
            for( int i = 0 ; i < id.size() ; i++ ){
                runs.setText("  " + id.get( i ).getText() + "                            " + notas.get( i ).getText() );
                runs.addBreak();
            }
            parrafo2.setAlignment(ParagraphAlignment.LEFT);
            //Otro parrafo
            XWPFParagraph parrafo3 =  receta.createParagraph();
            XWPFRun run3 = parrafo3.createRun();
            run3.setFontSize(15);
            run3.addBreak();
            parrafo3.setAlignment(ParagraphAlignment.RIGHT);
            //Otro parrafo
            XWPFParagraph parrafo4 =  receta.createParagraph();
            XWPFRun run4 = parrafo4.createRun();
            run4.setFontSize( 20 );
            run4.setBold( true );
            run4.setText("SELLO O FIRMA DEL MÉDICO:");
            parrafo4.setAlignment(ParagraphAlignment.LEFT);
            
            //Guarda el documento como prueba.docx
            try{
                FileOutputStream output = new FileOutputStream("receta.docx");
                receta.write(output);
                output.close();
                
                //Ésta linea imprime directamente el documento previamente creado:
                Desktop.getDesktop().print(new File("receta.docx"));
                JOptionPane.showMessageDialog( null, "Receta generada correctamente. En breve se imprimirá");
                dispose();
                new MedicoGUI();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Cierre el archivo antes de crear una nueva receta por favor");
            }
        
    }
    
    private class eventoAgregar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
                JTextField auxT = new JTextField();
                JTextField auxC = new JTextField();
                JButton auxB = new JButton( "Eliminar" );
                
                auxT.setBounds( 20, 180 + filas*25, x, y);
                auxC.setBounds( 200, 180 + filas*25, x*2, y);
                auxB.setBounds( 510, 180 + filas*25, 100, y);
                
                auxB.addActionListener( new eventoEliminar() );
                
                id.add( auxT );
                notas.add( auxC );
                eliminar.add( auxB );
                panel.add( id.get( filas ) );
                panel.add( notas.get( filas ) );
                panel.add( eliminar.get( filas ) );

                filas++;
                repaint();
                System.out.println("PA - Contador: " + filas);
        }
    }
    
    private class eventoEliminar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            JButton source = (JButton) e.getSource();
            int indice = eliminar.indexOf( source );
            if( filas > 1 ){
                id.remove( indice );
                notas.remove( indice );
                eliminar.remove( indice );
                repintarElementos();
                filas--;
                System.out.println("Indice: " + indice +", Contador: " + filas);
            }
        }
    }
}
