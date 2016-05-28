/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependiente;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import sismed.ConexionDB;

/**
 *
 * @author GerardoAramis
 */
public class buscarSustancia extends JFrame{
    public static void main( String s[] ){
        new buscarSustancia("aceta", "ACTIVO");
    }
    
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private JPanel panel = new JPanel();
    private JTable table = new JTable(){
       @Override
       public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
           Component component = super.prepareRenderer(renderer, row, column);
           int rendererWidth = component.getPreferredSize().width;
           TableColumn tableColumn = getColumnModel().getColumn(column);
           tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
           return component;
        }
    };
    private JScrollPane spTable = new JScrollPane(table);
    private DefaultTableModel modelo = new DefaultTableModel();
    private JLabel titulo = new JLabel("");
    private JButton salir = new JButton("Cerrar ventana");
    private String nombre;
    private String tipo;
    public buscarSustancia( String nombre, String tipo ){
        this.nombre = nombre;
        this.tipo = tipo;
        setSize(1000, 600);
        setVisible( true );
        setTitle("Búsqueda");
        setLocation( (int) (dim.getWidth()/2) - 1000/2, (int) (dim.getHeight()/2) - 600/2);
        titulo.setText("Medicamentos hallados con '" + nombre + "':");
        initPanel();
        spTable.setBounds( 30, 40, 880, 500);
        salir.setBounds( 400, 500, 150, 30);
        salir.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
        add(panel);
        table.setModel( modelo );
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        mostrarRegistros();
    }
    public void initPanel(){
        panel.setLayout( null );
        titulo.setBounds( 200, 20, 400, 20);
        panel.add( titulo );
        panel.add( spTable );
        panel.add( salir );
    }
    public void mostrarRegistros(){
        ConexionDB dbCon = new ConexionDB();
        if( tipo.equals("ACTIVO") ){
            ResultSet rs = dbCon.consulta( "SELECT m.*, a.stock FROM medicamento m, almacen a WHERE sustanciaActiva like \"%" + nombre + "%\"  AND m.idMedicamento=a.idMedicamento;" );

            try {
                ResultSetMetaData meta = rs.getMetaData();
                int columnas = meta.getColumnCount();
                Object[] etiquetas = new Object[ columnas ];
                for( int i = 0 ; i < columnas ; i++ )
                    etiquetas[ i ] = meta.getColumnLabel( i + 1 );
                modelo.setColumnIdentifiers( etiquetas );

                while ( rs.next() ) {
                    Object[] fila = new Object[columnas];
                    for ( int i = 0 ; i < columnas ; i++ ) {
                        fila[ i ] = rs.getObject( i + 1 );
                    System.out.println( rs.getObject( i + 1 ).toString() );
                    }
                    modelo.addRow( fila );
                    repaint();
               }

            } catch (SQLException ex) {
                Logger.getLogger(buscarSustancia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            ResultSet rs = dbCon.consulta( "SELECT m.*, a.stock FROM medicamento m, almacen a WHERE m.Nombre like \"%" + nombre + "%\" AND m.idMedicamento=a.idMedicamento;" );

            try {
                ResultSetMetaData meta = rs.getMetaData();
                int columnas = meta.getColumnCount();
                Object[] etiquetas = new Object[ columnas ];
                for( int i = 0 ; i < columnas ; i++ )
                    etiquetas[ i ] = meta.getColumnLabel( i + 1 );
                modelo.setColumnIdentifiers( etiquetas );

                while ( rs.next() ) {
                    Object[] fila = new Object[columnas];
                    for ( int i = 0 ; i < columnas ; i++ ) {
                        fila[ i ] = rs.getObject( i + 1 );
                    System.out.println( rs.getObject( i + 1 ).toString() );
                    }
                    modelo.addRow( fila );
                    repaint();
               }

            } catch (SQLException ex) {
                Logger.getLogger(buscarSustancia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
