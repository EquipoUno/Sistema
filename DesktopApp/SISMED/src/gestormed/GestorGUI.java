/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestormed;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import sismed.*;

/**
 *
 * @author GerardoAramis
 */
public class GestorGUI extends JFrame{
    
    private final JPanel panel = new JPanel();
    private final JButton agregar = new JButton("Agregar");
    private final JButton regresar = new JButton("Cerrar sesíón");
    private final JSpinner cantidad = new JSpinner();
    private final JTextField id = new JTextField();
    private final JLabel label = new JLabel("Cantidad");
    private final JLabel titulo = new JLabel("Introduce el ID del medicamento y la cantidad que desees agregar:");
    private final JLabel label_id = new JLabel("ID Medicamento");
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    
    private ConexionDB dbCon;
    public GestorGUI(){
        setSize( 550, 170 );
        setTitle("Agregar al stock");
        setVisible( true );
        setLocation((int) (dim.getWidth()/2) - 550/2, (int) (dim.getHeight()/2) - 170/2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add( panel );
        initPanel();
        initConnection();
    }
    public void initPanel(){
        panel.setSize( 550, 200 );
        panel.setLayout( null );
        
        titulo.setBounds( 20, 10, 400, 30 );
        id.setBounds( 70, 50, 100, 30 );
        label_id.setBounds( 70, 75, 100, 30 );
        cantidad.setBounds( 210, 50, 100, 30 );
        label.setBounds( 230, 75, 100, 30 );
        agregar.setBounds( 350, 50, 150, 25 );
        regresar.setBounds( 350, 80, 150, 25 );
        regresar.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrar();
                dbCon.cerrarConexion();
                LoginGUI login = new LoginGUI();
            }
        });
        agregar.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String idMed = id.getText().toUpperCase();
                ResultSet rs = dbCon.consulta("SELECT * FROM almacen WHERE idMedicamento='" + idMed + "'");
                try {
                    if( rs.next() ){
                        int stock = rs.getInt("stock");
                        int newstock = stock + (int)cantidad.getValue();
                        dbCon.actualizar("UPDATE almacen SET stock=" + newstock + " WHERE idMedicamento='" + idMed + "'");
                        JOptionPane.showMessageDialog( null, "Ahora hay " + newstock + " unidades de " + idMed );
                    }
                    else{
                        JOptionPane.showMessageDialog( null, "No existe medicamento con ese ID. Favor de revisar los datos.");                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(GestorGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                id.setText("");
                cantidad.setValue( 0 );
            }
        });
        
        panel.add( titulo );
        panel.add( label );
        panel.add( label_id );
        panel.add( id );
        panel.add( agregar );
        panel.add( regresar );
        panel.add( cantidad );         
    }
    public void initConnection(){
        dbCon = new ConexionDB();
    }
    public void cerrar(){
        dispose();
    }
}
