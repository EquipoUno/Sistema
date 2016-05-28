
package dependiente;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sismed.ConexionDB;


public class consultaFarmacia {
    public static void main(String[] args){
    ventana3 ventana=new ventana3();
    ventana.setVisible(true);
    ventana.setDefaultCloseOperation(EXIT_ON_CLOSE); 
    }
}

    class ventana3 extends JFrame{
        JMenuBar barra=new JMenuBar();
        JMenu menu=new JMenu("Menu");
        JMenuItem comprar=new JMenuItem("Comprar");
        JMenuItem consultar=new JMenuItem("Consultar");
        JMenuItem salir=new JMenuItem("Salir");
    public ventana3(){
        Toolkit pantalla= Toolkit.getDefaultToolkit();
        Dimension tamanioPantalla= pantalla.getScreenSize();
        setBounds(400,200,400,400);
        setSize(400,150);
        setResizable(false);
         
        //Barra de menus
        setJMenuBar(barra);
        barra.add(menu);
        comprarAccion evento1=new comprarAccion();
        comprar.addActionListener(evento1);
        consultarAccion evento2=new consultarAccion();
        consultar.addActionListener(evento2);
        salirAccion evento3=new salirAccion();
        salir.addActionListener(evento3);
        menu.add(comprar);
        menu.add(consultar);
        menu.add(salir);
        
        //Tamaño de pantalla, imagen & titulo
        
        setTitle("Farmacia-Consulta");
        Image miIcono=pantalla.getImage("src/images/icono.png");
        setIconImage(miIcono);
        Lamina3 pantalla3=new Lamina3( this );
        add(pantalla3);
}
    private class comprarAccion implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            ventana2 pantalla1=new ventana2();
            pantalla1.setVisible(true);
            pantalla1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dispose();
        }
    }
    
    private class consultarAccion implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            ventana3 pantalla2=new ventana3();
            pantalla2.setVisible(true);
            pantalla2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dispose();
        }
    }
    
    private class salirAccion implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }

    }




    class Lamina3 extends JPanel{
        ventana3 v3;
        JTextField idFarmaco;
        public Lamina3( ventana3 v3 ){
            this.v3 = v3;
            JLabel texto1=new JLabel("ID Farmaco: ");
            idFarmaco=new JTextField(20);
            JButton consultar=new JButton("Consultar");
            consultar.addActionListener( new consultarAccion() );
            add(texto1);
            add(idFarmaco);
            add(consultar);
            
        }
        private class consultarAccion implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
                //CONSULTAR EL STOCK DEL ID DE MEDICAMENTO INGRESADO:
                ConexionDB dbCon = new ConexionDB();
                if(idFarmaco.getText().equals("") ){
                    JOptionPane.showMessageDialog( null, "Favor de introducir un ID de medicamento");
                }
                else{
                    ResultSet rs = dbCon.consulta("SELECT * FROM almacen WHERE idMedicamento='" + idFarmaco.getText().toUpperCase() + "'");
                    try {
                        if( rs.next() )
                            JOptionPane.showMessageDialog( null, "Hay " + rs.getInt("stock") + " unidades de " + idFarmaco.getText().toUpperCase());
                    } catch (SQLException ex) {
                        Logger.getLogger(Lamina3.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ventana2 pantalla1 = new ventana2();
                    pantalla1.setVisible(true);
                    pantalla1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    v3.dispose();
                }
            }
        }
    }