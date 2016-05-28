/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medico;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import sismed.LoginGUI;

/**
 *
 * @author GerardoAramis
 */
public class MedicoGUI extends JFrame{
    private JPanel panel;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private int ancho = 300, altura = 300;
    private JButton receta;
    private JButton gen_exp;
    private JButton mod_exp;
    private JButton cons_exp;
    private JButton cerrar_sesion;
    
    public static void main( String s[]){
        new MedicoGUI();
    }
    public MedicoGUI(){
        setLocation( (int)dim.getWidth()/2 - (int)ancho/2, (int)dim.getHeight()/2 - (int)altura/2 );
        setSize( ancho, altura );
        setTitle( "Menú" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        //setLayout( null );
        initPanel();
        add( panel );
        setVisible( true );
    }
    public void initPanel(){
        panel = new JPanel();
        panel.setLayout( null );
        panel.setSize( ancho, altura );
        
        receta = new JButton( "Generar receta" );
        gen_exp= new JButton( "Generar expediente médico" );
        mod_exp = new JButton( "Modificar expediente médico" );
        cons_exp = new JButton( "Consultar expediente médico" );
        cerrar_sesion = new JButton( "Cerrar sesión" );
        
        receta.setBounds( 50, 30 , 200, 30 );
        cons_exp.setBounds( 50, 70 , 200, 30 );
        gen_exp.setBounds( 50, 110 , 200, 30 );
        mod_exp.setBounds( 50, 150 , 200, 30 );
        cerrar_sesion.setBounds( 120, 220 , 130, 20 );
        
        receta.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent ae ){
                new Receta();
            }
        });
        
        gen_exp.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent ae ){
                dispose();
                new Generar();
            }
        });
        
        mod_exp.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent ae ){
                dispose();
                new Modificar();
            }
        });
        
        cons_exp.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent ae ){
                dispose();
                new Consultar();
            }
        });
        
        cerrar_sesion.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent ae ){
                new LoginGUI();
                dispose();
            }
        });
        
        panel.add( receta );
        panel.add( gen_exp );
        panel.add( mod_exp );
        panel.add( cons_exp );
        panel.add( cerrar_sesion );
    }
}
