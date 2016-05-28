/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrativo;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author GerardoAramis
 */
public class AddUsr extends JFrame{
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private JPanel panel;
    private JTextField usr, psw1, psw2;
    private JLabel usrl, psw1l, psw2l;
    private JButton regresar, registrar;
    public static void main( String s[]){
        new AddUsr();
    }
    public AddUsr(){
        setSize(300,300);
        setVisible( true );
        setLocation((int) (dim.getWidth()/2) - 350/2, (int) (dim.getHeight()/2) - 200/2);
        
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setTitle("Agregar usuario");
        initPanel();
        add( panel );
        setVisible( true );
    }

    private void initPanel() {
        panel = new JPanel();
        panel.setSize( 300, 300 );
        panel.setLayout( null );
        
        usr = new JTextField();
        psw1 = new JTextField();
        psw2 = new JTextField();
        usrl = new JLabel("Nombre de usuario: ");
        psw1l = new JLabel("Contraseña: ");
        psw2l = new JLabel("Confirma contraseña:");
        regresar = new JButton("Regresar");
        registrar = new JButton("Registrar");
        usrl.setBounds( 20, 30, 150, 20);
        usr.setBounds( 20, 50, 150, 20);
        psw1l.setBounds( 20, 80, 150, 20);
        psw1.setBounds( 20, 100, 150, 20);
        psw2l.setBounds( 20, 130, 150, 20);
        psw2.setBounds( 20, 150, 150, 20);
        regresar.setBounds( 20, 180, 100, 30);
        registrar.setBounds( 130, 180, 100, 30);
        
        regresar.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent ae ){
                dispose();
                new AdminGUI();
            }
        });
        registrar.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent ae ){
                //
            }
        });
        
        panel.add( usr );
        panel.add( usrl );
        panel.add( psw1 );
        panel.add( psw1l );
        panel.add( psw2 );
        panel.add( psw2l );
        panel.add( regresar );
        panel.add( registrar );
        
    }
    
}
