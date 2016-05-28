/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependiente;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sismed.ConexionDB;

/**
 *
 * @author GerardoAramis
 */
public class buscarFarmacia extends JFrame{
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private JPanel panel;
    private JLabel id_label = new JLabel("Introduce un ID: ");
    private JLabel sus_activa = new JLabel("Ingresa el nombre");
    private JLabel nombre_label= new JLabel("Ingresa el nombre");
    private JLabel nombre_label2= new JLabel("del medicamento");
    private JLabel sus_activa2 = new JLabel("de una sustancia activa:");
    private JTextField id = new JTextField();
    private JTextField sus = new JTextField();
    private JTextField nom = new JTextField();
    private JButton buscar_id = new JButton("Buscar por ID");
    private JButton buscar_sus =  new JButton("Buscar por sustancia");
    private JButton buscar_nom =  new JButton("Buscar por nombre");
    private JButton cerrar =  new JButton("Cerrar ventana");
    
    public static void main( String s[]){
        new buscarFarmacia();
    }
    
    
    public buscarFarmacia(){
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Buscar...");
        setSize( 600, 300 );
        setVisible( true );
        setLocation((int) (dim.getWidth()/2) - 600/2, (int) (dim.getHeight()/2) - 300/2);
        initPanel();
        add( panel );
    }
    public void initPanel(){
        panel = new JPanel();
        
        panel.setLayout( null );
        id_label.setBounds(15, 15, 150, 20);
        sus_activa.setBounds(15, 45, 150, 20);
        sus_activa2.setBounds(15, 60, 150, 20);
        nombre_label.setBounds( 15, 85, 150, 20);
        nombre_label2.setBounds( 15, 100, 150, 20);
        id.setBounds(170, 15, 80, 20);
        sus.setBounds(170, 50, 130, 20);
        nom.setBounds(170, 95, 130, 20);
        buscar_id.setBounds(310, 15, 180, 20);
        buscar_sus.setBounds(310, 50, 180, 20);
        buscar_nom.setBounds(310, 95, 180, 20);
        cerrar.setBounds(270, 150, 150, 30);
        cerrar.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent ae){
                dispose();
            }
        });
        
        
         buscar_id.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ) {
                String idtxt = id.getText().toUpperCase();
                if( !idtxt.equals("")){
                    ConexionDB dbCon = new ConexionDB();
                    ResultSet rs = dbCon.consulta("SELECT * from medicamento WHERE idMedicamento='" + idtxt + "'");
                    try {
                        if( !rs.next() )                            
                            JOptionPane.showMessageDialog( null, "No hay ningun medicamento con el ID ingresado." );
                        else{
                            rs.previous();
                            String res = "MEDICAMENTO " + idtxt + ":\n";
                            while( rs.next() ){
                                res += ("Nombre: " + rs.getString("Nombre") + "\n");
                                res += ("Laboratorio: " + rs.getString("Laboratorio") + "\n");
                                res += ("Descripción: " + rs.getString("Descripcion") + "\n");
                                res += ("Precio: $" + rs.getString("Precio") + "\n");
                            }
                            JOptionPane.showMessageDialog( null, res );
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(buscarFarmacia.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    JOptionPane.showMessageDialog( null, "Introduzca un ID de medicamento");
                }                
            }
        
        });
         
         buscar_sus.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String sustxt = sus.getText().toUpperCase();
                if( sustxt.equals("")){
                    JOptionPane.showMessageDialog( null, "Introduzca el nombre de la sustancia activa");
                }
                else{
                    ConexionDB dbCon = new ConexionDB();
                    ResultSet rs = dbCon.consulta("SELECT * FROM medicamento WHERE sustanciaActiva like \"%" + sustxt + "%\";" );
                    try {
                        if( !rs.next() ){
                            JOptionPane.showMessageDialog( null, "No hay ningun medicamento con la sustancia activa ingresada." );
                        }
                        else{
                            buscarSustancia ventanaBS = new buscarSustancia( sustxt, "ACTIVO" );
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(buscarFarmacia.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
         });
         
         buscar_nom.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomtxt = nom.getText().toUpperCase();
                if( nomtxt.equals("")){
                    JOptionPane.showMessageDialog( null, "Introduzca un nombre por favor");
                }
                else{
                    ConexionDB dbCon = new ConexionDB();
                    ResultSet rs = dbCon.consulta("SELECT * FROM medicamento WHERE Nombre like \"%" + nomtxt + "%\";" );
                    try {
                        if( !rs.next() ){
                            JOptionPane.showMessageDialog( null, "No hay ningun medicamento con el nombre ingresado." );
                        }
                        else{
                            buscarSustancia ventanaBS = new buscarSustancia( nomtxt, "NOMBRE" );
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(buscarFarmacia.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }             
         });
        
        
        //panel.add( id_label );
        panel.add( sus_activa );
        panel.add( sus_activa2 );
        panel.add( nombre_label );
        panel.add( nombre_label2 );
        //panel.add( id );
        panel.add( sus );
        panel.add( nom );
        //panel.add( buscar_id );
        panel.add( buscar_sus );
        panel.add( buscar_nom );
        panel.add( cerrar );
    }
}
