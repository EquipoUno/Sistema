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
public class AddMed extends JFrame{
    private JPanel panel;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    //Precio
    private JTextField id, nombre, lab, desc, sus;
    private JCheckBox controlado;
    private JLabel idl, nombrel, labl, descl, susl, controladol, inst;
    private JButton registrar, regresar;
    public static void main( String s[]){
        new AddMed();
    }
    public AddMed(){
        setSize(400,400);
        setVisible( true );
        setLocation((int) (dim.getWidth()/2) - 350/2, (int) (dim.getHeight()/2) - 200/2);
        
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setTitle("Agregar medicamento");
        initPanel();
        add( panel );
        setVisible( true );
    }
    public void initPanel(){
        panel = new JPanel();
        panel.setLayout( null );
        panel.setSize(400, 400);
        id = new JTextField();
        nombre = new JTextField();
        lab = new JTextField();
        desc = new JTextField();
        sus = new JTextField();
        controlado = new JCheckBox();
        idl = new JLabel("ID:");
        nombrel = new JLabel("Nombre:");
        labl = new JLabel("Laboratorio:");
        descl = new JLabel("Descripción:");
        susl = new JLabel("Sustancia activa:");
        controladol = new JLabel("¿Es controlado?");
        inst = new JLabel("INGRESA LA INFORMACIÓN SOLICITADA");
        registrar = new JButton("Registrar");
        regresar = new JButton("Regresar");
        inst.setBounds( 50, 5, 300, 20 );
        idl.setBounds( 20, 20, 200, 20);
        id.setBounds( 20, 40, 200, 20);
        nombrel.setBounds( 20, 60, 200, 20);
        nombre.setBounds( 20, 80, 200, 20);
        labl.setBounds( 20, 100, 200, 20);
        lab.setBounds( 20, 120, 200, 20);
        descl.setBounds( 20, 140, 200, 20);
        desc.setBounds( 20, 160, 200, 20);
        susl.setBounds( 20, 180, 200, 20);
        sus.setBounds( 20, 200, 200, 20);
        controladol.setBounds( 20, 220, 100, 20);
        controlado.setBounds( 120, 220, 200, 20);
        regresar.setBounds( 20, 280, 100, 30);
        registrar.setBounds( 130, 280, 100, 30);
        
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
        
        panel.add( id );
        panel.add( nombre );
        panel.add( lab );
        panel.add( desc );
        panel.add( sus );
        panel.add( controlado );
        panel.add( idl );
        panel.add( nombrel );
        panel.add( labl );
        panel.add( descl );
        panel.add( susl );
        panel.add( inst );
        panel.add( controladol );
        panel.add( registrar );
        panel.add( regresar );
        
    }
    
}
