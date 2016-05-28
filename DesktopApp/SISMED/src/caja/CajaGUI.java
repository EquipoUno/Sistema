/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caja;

/**
 *
 * @author GerardoAramis
 */
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import sismed.ConexionDB;
import sismed.LoginGUI;
public class CajaGUI extends JFrame{
    public static void main( String s[]){
        new CajaGUI();
    }
    
    private JButton pagar = new JButton("Pagar");
    private JButton cerrar_sesion = new JButton("Cerrar sesión");
    private JLabel id_label = new JLabel("ID de transacción:");
    private JLabel monto_label = new JLabel("Monto recibido:");
    private JTextField id = new JTextField();
    private JTextField monto = new JTextField();
    private JPanel panel = new JPanel();
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public CajaGUI(){
        setSize(350,200);
        setVisible( true );
        setLocation((int) (dim.getWidth()/2) - 350/2, (int) (dim.getHeight()/2) - 200/2);
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setTitle("Caja");
        initPanel();
        add( panel );
    }
    public void initPanel(){
        panel.setSize( 400, 400);
        panel.setLayout( null );
        
        id_label.setBounds( 20, 20, 150, 20 );
        id.setBounds( 160, 20, 150, 20 );
        monto_label.setBounds( 20, 50, 150, 20 );
        monto.setBounds( 160, 50, 150, 20 );
        pagar.setBounds( 80, 80, 150, 20 );
        pagar.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent ae ){
                if( id.getText().equals("") )
                    JOptionPane.showMessageDialog( null, "Introduzca un ID de transaccion");
                else if( monto.getText().equals("") )
                    JOptionPane.showMessageDialog( null, "Introduzca el monto recibido del cliente");
                else if( !esNumero ( monto.getText() ) )
                    JOptionPane.showMessageDialog( null, "Introduzca un número como monto");
                else{
                    ConexionDB dbCon = new ConexionDB();
                    int dinero_recibido = Integer.parseInt( monto.getText() );
                    try {
                        //Revisa la existencia de la transaccion
                        if( dbCon.consulta( "SELECT * FROM transacciones WHERE id=" + id.getText() ).next()){
                            ResultSet rs;
                            rs = dbCon.consulta("SELECT status FROM transacciones WHERE id=" + id.getText() );
                            rs.next();
                            if( rs.getString("status").equals("Pagado") )
                                JOptionPane.showMessageDialog( null, "La transaccion con ID: " + id.getText() + " ya está pagada");
                            else{
                                rs = dbCon.consulta( "SELECT saldo FROM caja WHERE id=666" );
                                int dinero_actual = 0, precio_trans = 0, cambio;
                                if( rs.next() )
                                    dinero_actual = rs.getInt("saldo"); //QUERY OK
                                rs = dbCon.consulta( "SELECT monto FROM transacciones WHERE id=" + id.getText() );
                                if( rs.next() )
                                    precio_trans = rs.getInt("monto"); //QUERY OK
                                cambio = dinero_recibido - precio_trans;
                                //Revisa si es posible dar cambio
                                if( dinero_actual < cambio )
                                    JOptionPane.showMessageDialog( null, "No hay suficiente dinero en caja para dar cambio. \nFavor de pagar con cambio.");
                                else{
                                    dbCon.actualizar("UPDATE caja SET saldo=" + (dinero_actual + precio_trans) +  " WHERE id=666");
                                    dbCon.actualizar("UPDATE transacciones SET status='Pagado' WHERE id=" + id.getText());
                                    JOptionPane.showMessageDialog( null, "Pago exitoso. \nDevolver $" + cambio + " al cliente");
                                    //Vacia los campos anteriores
                                    id.setText("");
                                    monto.setText("");
                                }
                            }
                        }
                        else
                            JOptionPane.showMessageDialog( null, "No existe ninguna transaccion pendiente con el ID ingresado");
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(CajaGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }
            }
        });
        
        cerrar_sesion.setBounds( 80, 120, 150, 20);
        cerrar_sesion.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent ae ){
                LoginGUI l = new LoginGUI();
                dispose();
            }
        });
        
        panel.add( id_label );
        panel.add( id );
        panel.add( monto_label );
        panel.add( monto );
        panel.add( pagar );
        panel.add(cerrar_sesion);
    }
    public boolean esNumero( String text ) {
            boolean es_numero = true;
            for( int i = 0 ; i < text.length() ; i++ )
                if( !Character.isDigit( text.charAt( i ) ) )
                    es_numero = false;
            return es_numero;
        }
}
