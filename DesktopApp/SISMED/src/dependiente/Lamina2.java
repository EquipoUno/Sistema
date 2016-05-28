/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependiente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class Lamina2 extends JPanel{
    int y = 18, altura = 18,ancho = 100;
    int renglon = 0;
    Lamina2 l = this;
    JFrame invocador;
    ArrayList<JLabel> label_id = new ArrayList<>();
    ArrayList<JLabel> label_cantidad = new ArrayList<>();
    ArrayList<JTextField> id= new ArrayList<>();
    ArrayList<JTextField> cantidadA= new ArrayList<>();
    ArrayList<JButton> agregarb = new ArrayList<>();
    ArrayList<JButton> eliminarb = new ArrayList<>();
    JTextField usuario=new JTextField(20);
    JTextField cantidad=new JTextField(20);
    //informacion de la base de datos
    final String DB_URL = "jdbc:mysql://db4free.net:3306/clinica_0110";
    final String USER = "steven_0110";
    final String PASS = "123456789";
   //Constructor Lamina
    public Lamina2( JFrame frame ){
        invocador = frame;
        setLayout(null);
        int y, altura,ancho;
        y=18;
        ancho=100;
        altura=18;
        label_id.add( new JLabel("ID Farmaco: ") );
        label_cantidad.add( new JLabel("Cantidad: ") );
        id.add( new JTextField() );
        cantidadA.add( new JTextField() );
        agregarb.add( new JButton("Agregar") );
        eliminarb.add( new JButton("Eliminar") );
        
        label_id.get( 0 ).setBounds(20,y*2,ancho,altura);
        id.get( 0 ).setBounds(100,y*2,ancho,altura);
        label_cantidad.get( 0 ).setBounds(210,y*2,ancho,altura);
        cantidadA.get( 0 ).setBounds(290,y*2,ancho,altura);
        //Botones
        agregarb.get( 0 ).setBounds(300,y/2,ancho*2,altura);
        agregarb.get( 0 ).addActionListener( new eventoAgregar( this ) );
        eliminarb.get( 0 ).setBounds(530,y*2,ancho,altura);
        eliminarb.get( 0 ).addActionListener( new eventoEliminar( ) );
        
        pintarElementos();

        //Botones  confirmar
        
    }
    public void pintarElementos(){
        //Quita todos los elementos del panel
        l.removeAll();
        //Agrega los elementos por defecto
        JButton comprar=new JButton("Comprar");
        JButton limpiar=new JButton("Limpiar todo");
        accionComprar evento=new accionComprar();
        comprar.setBounds(175,300,100,20);
        comprar.addActionListener(evento);
        limpiar.setBounds(465,300,110,20);
        limpiar.addActionListener( new limpiarCampos() );
        l.add(comprar);
        l.add(limpiar);
        l.add(agregarb.get( 0 ) );
        System.out.println("ARRAYS A PINTAR: " + label_id.size());
        
        //Recalcular ubicaciones        
        for( int i = 0 ; i < label_id.size() ; i++ ){
            label_id.get( i ).setBounds(20, altura * ( i + 1 ) * 2 , ancho, altura);
            id.get( i ).setBounds(100, altura * ( i + 1 ) * 2, ancho, altura);
            label_cantidad.get( i ).setBounds(210, altura * ( i + 1 ) * 2, ancho, altura);
            cantidadA.get( i ).setBounds(290, altura * ( i + 1 )* 2, ancho, altura);
            eliminarb.get( i ).setBounds(530, altura * ( i + 1 )* 2, ancho, altura);
        }
        //Agregar de nuevo todos los elementos restantes
        for( int i = 0 ; i < label_id.size() ; i++ ){
            l.add(label_id.get( i ));
            l.add(id.get( i ) );
            l.add(label_cantidad.get( i ) );
            l.add(cantidadA.get( i ) );
            l.add(eliminarb.get( i ) ); 
        }
        //Repinta el panel
        l.repaint();
    }
    public void limpiarCampos(){
        for( int i = 0 ; i < label_id.size() ; i++ ){
                id.get( i ).setText("");
                cantidadA.get( i ).setText("");
            }
    }
    
    class accionComprar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){ 
            //Verifica que no haya ningun campo vacio
            boolean info_valida = true;
            boolean es_numero = true;
            boolean medic_exist = true;
            ConexionDB dbCon = new ConexionDB();
                
            for( int i = 0 ; i < label_id.size() ; i++ ){
                if( id.get( i ).getText().equals("" ) || cantidadA.get( i ).getText().equals("" ) )
                    info_valida = false;
                if( !esNumero( cantidadA.get( i ).getText() ) )
                    es_numero = false;
                try {
                    if( !dbCon.consulta("SELECT * FROM medicamento WHERE idMedicamento='" + id.get( i ).getText().toUpperCase() + "'").next() )
                        medic_exist = false;
                } catch (SQLException ex) {
                    Logger.getLogger(Lamina2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Consulta de precios y calculo de subtotal
            if( info_valida == false)
                JOptionPane.showMessageDialog( null, "Ningún campo debe estar vacío. \nFavor de revisar la información ingresada");
            else if( es_numero == false)
                JOptionPane.showMessageDialog( null, "La cantidad debe ser un número.\nFavor de revisar la información ingresada");
            else if( medic_exist == false )
                JOptionPane.showMessageDialog( null, "Uno o más medicamentos no existen.\nFavor de revisar la información ingresada");
            else{
                ResultSet rs;
                int monto = 0;
                ArrayList<String> nombres = new ArrayList<>();
                ArrayList<Integer> cantidades = new ArrayList<>();
                ArrayList<Integer> precios = new ArrayList<>();
                for( int i = 0 ; i < label_id.size() ; i++ ){
                    rs = dbCon.consulta("SELECT precio FROM medicamento WHERE idMedicamento='" + id.get( i ).getText() + "'");
                    try {
                        if( rs.next() ){
                            
                            nombres.add(id.get( i ).getText().toUpperCase());
                            cantidades.add( Integer.parseInt( cantidadA.get( i ).getText() ));
                            precios.add( rs.getInt("precio") );
                            monto += (rs.getInt("precio") * Integer.parseInt( cantidadA.get( i ).getText()) );
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Lamina2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                ventana4 pantalla2 = new ventana4( monto, precios, nombres, cantidades, invocador );
            }
        }

        private boolean esNumero( String text ) {
            boolean es_numero = true;
            for( int i = 0 ; i < text.length() ; i++ )
                if( !Character.isDigit( text.charAt( i ) ) )
                    es_numero = false;
            return es_numero;
        }
    }
    class limpiarCampos implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){ 
            limpiarCampos();
        }
    }
    private class eventoAgregar implements ActionListener{
        private Lamina2 lamina;
        public eventoAgregar(Lamina2 lam) {
            lamina = lam;
        }
        @Override
        public void actionPerformed(ActionEvent e){
                renglon++;
                int alt_elem_anterior = label_cantidad.get( renglon - 1 ).getLocation().y;
                label_id.add( new JLabel("ID Farmaco: ") );
                label_cantidad.add( new JLabel("Cantidad: ") );
                id.add( new JTextField() );
                cantidadA.add( new JTextField());
                eliminarb.add( new JButton("Eliminar") );   
                
                //Posiciona el nuevo elemento, de acuerdo al elemento anterior
                label_id.get( renglon ).setBounds(20, alt_elem_anterior + altura*2 , ancho, altura);
                label_cantidad.get( renglon ).setBounds( 210, alt_elem_anterior + altura*2, ancho, altura );
                id.get( renglon ).setBounds( 100, alt_elem_anterior + altura*2, ancho, altura );
                cantidadA.get( renglon ).setBounds( 290, alt_elem_anterior + altura*2, ancho, altura );
                eliminarb.get( renglon ).setBounds( 530, alt_elem_anterior + altura*2, ancho, altura );
                eliminarb.get( renglon ).addActionListener(  new eventoEliminar( ));
                
                //Agrega los nuevos elementos al Lamina2 principal               
                l.add( label_cantidad.get( renglon ));
                l.add( id.get( renglon ));
                l.add( cantidadA.get( renglon ));
                l.add( eliminarb.get( renglon ));
                l.add( label_id.get( renglon ));
                l.repaint();
        }
    }
    
    private class eventoEliminar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            JButton source = (JButton) e.getSource();
            int indice = eliminarb.indexOf( source );
            if( label_id.size() > 1 ){
                renglon--;
                System.out.println("INDICE HALLADO: " + indice );
                label_id.remove(indice);
                label_cantidad.remove(indice);
                cantidadA.remove(indice);
                eliminarb.remove(indice);
                id.remove(indice);
                l.pintarElementos();
            }
        }
    }

}
  
