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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author GerardoAramis
 */
public class Generar extends JFrame{
    private JPanel panel;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private int ancho = 900, altura = 600;
    private int x = 200, y = 20, x2 = 150;
    //Expediente
    private JTextField sangre, edad, peso, estatura, imc, alergias, cirugias_anteriores, fecha_act;
    private JTextArea notas;
    private JCheckBox presion_alta, diabetes, cancer, enf_cardiaca;
    //Paciente
    private JTextField curp, nombre, direccion, email, telefono, unidad_medica, fecha_nac, no_social;
    //Representante
    private JTextField nombre_rep, direccion_rep, email_rep, telefono_rep;
    //Expediente
    private JLabel sangrel, edadl, pesol, estatural, imcl, presion_altal, diabetesl, cancerl, enf_cardiacal, alergiasl, cirugias_anterioresl, fecha_actl, notasl;
    //Paciente
    private JLabel curpl, nombrel, direccionl, emaill, telefonol, unidad_medical, fecha_nacl, no_sociall;
    //Representante
    private JLabel nombre_repl, direccion_repl, email_repl, telefono_repl, paciente, representante, expediente;
    private JButton regresar, generar;
    
    
    public static void main(String s[]){
        new Generar();
    }
    public Generar(){
        setLocation( (int)dim.getWidth()/2 - (int)ancho/2, (int)dim.getHeight()/2 - (int)altura/2 );
        setSize( ancho, altura );
        setTitle( "Generar expediente" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        initPanel();
        add( panel );
        setVisible( true );
    }
    public void initPanel(){
        panel = new JPanel();
        panel.setLayout( null );
        //Campos de texto
        //Exp
        sangre = new JTextField();
        edad = new JTextField();
        peso = new JTextField();
        estatura = new JTextField();
        imc = new JTextField();
        presion_alta = new JCheckBox();
        diabetes = new JCheckBox();
        cancer = new JCheckBox();
        enf_cardiaca = new JCheckBox();
        alergias = new JTextField();
        cirugias_anteriores = new JTextField();
        fecha_act = new JTextField();
        notas = new JTextArea();
        //Paciente
        curp = new JTextField();
        nombre = new JTextField();
        direccion = new JTextField();
        email = new JTextField();
        telefono = new JTextField();
        unidad_medica = new JTextField();
        fecha_nac = new JTextField();
        no_social = new JTextField();
        //Rep
        nombre_rep = new JTextField();
        direccion_rep = new JTextField();
        email_rep = new JTextField();
        telefono_rep = new JTextField();
        
        //Labels
        //Exp
        sangrel = new JLabel("Tipo de sangre:");
        edadl = new JLabel("Edad:");
        pesol = new JLabel("Peso:");
        estatural = new JLabel("Estatura:");
        imcl = new JLabel("IMC:");
        presion_altal = new JLabel("Presion Alta:");
        diabetesl = new JLabel("Diabetes:");
        cancerl = new JLabel("Cancer:");
        enf_cardiacal = new JLabel("Enfermedad cardiaca:");
        alergiasl = new JLabel("Alergias:");
        cirugias_anterioresl = new JLabel("¿Ha sido operado antes?");
        fecha_actl = new JLabel("Ultima modificación del expediente:");
        notasl = new JLabel("Notas:");
        //Paciente
        curpl = new JLabel("CURP");
        nombrel = new JLabel("Nombre:");
        direccionl = new JLabel("Dirección:");
        emaill = new JLabel("Email:");
        telefonol = new JLabel("Teléfono:");
        unidad_medical = new JLabel("Unidad medica:");
        fecha_nacl = new JLabel("Fecha de nacimiento:");
        no_sociall = new JLabel("Número social:");
        //Rep
        nombre_repl = new JLabel("Nombre:");
        direccion_repl = new JLabel("Dirección:");
        email_repl = new JLabel("Email:");
        telefono_repl = new JLabel("Telefono:");
        
        paciente = new JLabel("DATOS DE PACIENTE");
        representante = new JLabel("DATOS DE REPRESENTANTE");
        expediente = new JLabel("DATOS MÉDICOS DEL PACIENTE");
        
        regresar = new JButton( "Regresar" );
        generar = new JButton( "Generar" );
        
        generar.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent ae ){
                //Genera el expediente
                
            }
        });
        regresar.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent ae ){
                //Regresa a la pantalla anterior
                dispose();
                new MedicoGUI();
                
            }
        });
        //Paciente
        curp.setBounds( 150, 15, x, y );
        curpl.setBounds( 150,35, x, y );
        nombrel.setBounds( 20, 80, x, y );
        nombre.setBounds( 20, 100, x, y );
        direccionl.setBounds( 240, 80, x, y );
        direccion.setBounds( 240, 100, 300, y );
        telefonol.setBounds( 560, 80, x, y );
        telefono.setBounds( 560, 100, x, y );
        unidad_medica.setBounds( 340, 140, x, y );
        unidad_medical.setBounds( 340, 120, x, y );
        emaill.setBounds( 20, 120, x, y );
        email.setBounds( 20, 140, 300, y );
        fecha_nac.setBounds( 560, 140, x, y );
        fecha_nacl.setBounds( 560, 120, x, y );
        no_social.setBounds( 20, 180, x, y );
        no_sociall.setBounds( 20, 160, x, y );
        
        paciente.setBounds(180, 65, 200, 20);
        //Rep
        nombre_rep.setBounds( 20, 240, x, y );
        nombre_repl.setBounds( 20, 220, x, y );
        telefono_repl.setBounds( 560, 220, x, y );
        telefono_rep.setBounds( 560, 240, x, y );
        direccion_repl.setBounds( 240, 220, x, y );
        direccion_rep.setBounds( 240, 240, 300, y );
        email_repl.setBounds( 20, 260, x, y );
        email_rep.setBounds( 20, 280, 300, y );
        
        representante.setBounds(180, 205, 200, 20);        
        
        //Exp
        edad.setBounds( 20, 360, x2, y );
        edadl.setBounds( 20, 340, x2, y );
        peso.setBounds( 190, 360, x2, y );
        pesol.setBounds( 190, 340, x2, y );
        estatura.setBounds( 360, 360, x2, y );
        estatural.setBounds( 360, 340, x2, y );
        sangre.setBounds( 530, 360, x2, y );
        sangrel.setBounds( 530, 340, x2, y );
        imc.setBounds( 700, 360, x2, y );
        imcl.setBounds( 700, 340, x2, y );
        presion_alta.setBounds( 20, 400, x2, y );
        presion_altal.setBounds( 20, 380, x2, y );
        diabetes.setBounds( 190, 400, x2, y );
        diabetesl.setBounds( 190, 380, x2, y );
        cancer.setBounds( 360, 400, x2, y );
        cancerl.setBounds( 360, 380, x2, y );
        enf_cardiaca.setBounds( 530, 400, x2, y );
        enf_cardiacal.setBounds( 530, 380, x2, y );
        alergias.setBounds( 700, 400, x2, y );
        alergiasl.setBounds( 700, 380, x2, y );
        fecha_act.setBounds( 550, 55, x2, y );
        fecha_actl.setBounds( 500, 35, 250, y );
        cirugias_anteriores.setBounds( 20, 440, x2, y );
        cirugias_anterioresl.setBounds( 20, 420, x2, y );
        notas.setBounds( 190, 440, 450, 100 );
        notasl.setBounds( 190, 420, x2, y );
        
        expediente.setBounds(180, 325, 200, 20);
        
        
        regresar.setBounds( 700, 490, 120, 40 );
        generar.setBounds( 700, 440, 120, 40 );
        
        
        
        
        //Campos
        panel.add( sangre );
        panel.add( edad );
        panel.add( peso );
        panel.add( estatura );
        panel.add( imc );
        panel.add( presion_alta );
        panel.add( diabetes );
        panel.add( cancer );
        panel.add( enf_cardiaca );
        panel.add( alergias );
        panel.add( cirugias_anteriores );
        panel.add( fecha_act );
        panel.add( notas );
        panel.add( curp );
        panel.add( nombre );
        panel.add( direccion );
        panel.add( email );
        panel.add( telefono );
        panel.add( unidad_medica );
        panel.add( fecha_nac );
        panel.add( no_social );
        panel.add( nombre_rep );
        panel.add( direccion_rep );
        panel.add( email_rep );
        panel.add( telefono_rep );
        
        //Labels
        panel.add( sangrel );
        panel.add( edadl );
        panel.add( pesol );
        panel.add( estatural );
        panel.add( imcl );
        panel.add( presion_altal );
        panel.add( diabetesl );
        panel.add( cancerl );
        panel.add( enf_cardiacal );
        panel.add( alergiasl );
        panel.add( cirugias_anterioresl );
        panel.add( fecha_actl );
        panel.add( notasl );
        panel.add( curpl );
        panel.add( nombrel );
        panel.add( direccionl );
        panel.add( emaill );
        panel.add( telefonol );
        panel.add( unidad_medical );
        panel.add( fecha_nacl );
        panel.add( no_sociall );
        panel.add( nombre_repl );
        panel.add( direccion_repl );
        panel.add( email_repl );
        panel.add( telefono_repl );
        panel.add( paciente );
        panel.add( representante );
        panel.add( expediente );
        
        panel.add( generar );
        panel.add( regresar );
    }
    
    public void limpiarCampos(){
        
        sangre.setText("");
        edad.setText("");
        peso.setText("");
        estatura.setText("");
        imc.setText("");
        presion_alta.setText("");
        diabetes.setText("");
        cancer.setText("");
        enf_cardiaca.setText("");
        alergias.setText("");
        cirugias_anteriores.setText("");
        fecha_act.setText("");
        notas.setText("");
        curp.setText("");
        nombre.setText("");
        direccion.setText("");
        email.setText("");
        telefono.setText("");
        unidad_medica.setText("");
        fecha_nac.setText("");
        no_social.setText("");
        nombre_rep.setText("");
        direccion_rep.setText("");
        email_rep.setText("");
        telefono_rep.setText("");
    }
}