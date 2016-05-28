/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrativo;

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
public class AdminGUI extends JFrame{
    
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private JPanel panel;
    private JButton agregar_usr, agregar_med;
    public static void main( String s[]){
        new AdminGUI();
    }
    public AdminGUI(){
        
        setSize(300,200);
        setVisible( true );
        setLocation((int) (dim.getWidth()/2) - 350/2, (int) (dim.getHeight()/2) - 200/2);
        
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setTitle("Menú");
        initPanel();
        add( panel );
        setVisible( true );
    }
    public void initPanel(){
        panel = new JPanel();
        panel.setSize( 300, 200);
        panel.setLayout( null );
        agregar_usr = new JButton("Agregar usuario");
        agregar_med = new JButton("Agregar medicamento");
        agregar_usr.setBounds(50, 50, 200, 30);
        agregar_med.setBounds(50, 90, 200, 30);
        agregar_usr.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent ae ){
                dispose();
                new AddUsr();
            }
        });
        agregar_med.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent ae ){
                dispose();
                new AddMed();
            }
        });
        panel.add( agregar_usr );
        panel.add( agregar_med );
        
    }
    
}
