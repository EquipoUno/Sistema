/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sismed;

import administrativo.AdminGUI;
import dependiente.ventana2;
import caja.CajaGUI;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import gestormed.*;
import medico.MedicoGUI;
/**
 *
 * @author GerardoAramis
 */

public class LoginGUI extends JFrame{
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private JPanel panel = new JPanel();
    private JTextField user = new JTextField();
    private JPasswordField psw = new JPasswordField();
    private JLabel label_user = new JLabel("Usuario");
    private JLabel label_psw = new JLabel("Contraseña");
    private JButton validar = new JButton("Iniciar sesión");
    public static void main( String s[] ){
        new LoginGUI();
    }
    public LoginGUI(){
        setSize( 250, 200 );
        setVisible( true );
        setTitle("Login");
        setLocation((int) (dim.getWidth()/2) - 250/2, (int) (dim.getHeight()/2) - 200/2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initPanel();
        add( panel );
    }
    public void initPanel(){
        panel.setSize( 250, 200 );
        panel.setLayout( null );
        
        user.setBounds( 50, 20, 150, 30 );
        label_user.setBounds( 50, 45, 150, 30 );
        psw.setBounds( 50, 80, 150, 30 );
        label_psw.setBounds( 50, 105, 150, 30 );
        validar.setBounds( 70, 135, 150, 20);
        validar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent ae ){
                if( !psw.getText().equals("") && !user.getText().equals("") ){
                ConexionDB dbCon = new ConexionDB();
                ResultSet rs = dbCon.consulta("SELECT rol FROM usuarios WHERE usuario='" + user.getText() + "' AND pass='" + psw.getText() + "';");
                try {
                    if( rs.next() ){
                        //Abre la ventana correspondiente de acuerdo al rol de la cuenta
                        String rol = rs.getString("rol");
                        if( rol.equals("Dependiente") ){
                            ventana2 pantalla2 = new ventana2();
                            pantalla2.setVisible(true);
                            pantalla2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        }
                        else if( rol.equals("Proveedor")){
                            GestorGUI gestor = new GestorGUI();
                        }
                        else if( rol.equals("Cajero")){
                            CajaGUI c = new CajaGUI();
                        }
                        else if( rol.equals("Doctor")){
                            MedicoGUI c = new MedicoGUI();
                        }
                        else if( rol.equals("Admin")){
                            AdminGUI c = new AdminGUI();
                        }
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog( null, "Datos de usuario incorrectos. \nFavor de contactar con un administrativo");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                else{
                    JOptionPane.showMessageDialog( null, "Ambos campos no deben estar vacíos");
                }
            }});
        
        panel.add( label_user );
        panel.add( label_psw );
        panel.add( psw );
        panel.add( user );       
        panel.add( validar );     
           
    }
    public void cerrar(){
        dispose();
    }
}
