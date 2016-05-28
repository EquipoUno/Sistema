/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependiente;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dependiente.Lamina2;
import dependiente.buscarFarmacia;
import dependiente.ventana3;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import sismed.LoginGUI;

/**
 *
 * @author GerardoAramis
 */
 public class ventana2 extends JFrame{
 
        JMenuBar barra=new JMenuBar();
        JMenu menu=new JMenu("Opciones");
        JMenu menuUsuario=new JMenu("Usuario");
        JMenuItem comprar=new JMenuItem("Comprar");
        JMenuItem consultar=new JMenuItem("Consultar stock");
        JMenuItem buscar=new JMenuItem("Buscar");
        JMenuItem salir=new JMenuItem("Salir");
        JMenuItem sesionClose=new JMenuItem("Cerrar Sesión");
        
        
      
        
                
    public ventana2(){
        setBounds(400,200,700,400);
        setResizable(false);
        Toolkit pantalla= Toolkit.getDefaultToolkit();
        //Dimension tamanioPantalla= pantalla.getScreenSize();
        /*
        int alturaP=tamanioPantalla.height;
        int anchoP=tamanioPantalla.width;
        setSize(anchoP/2,alturaP/2);
        setLocation(anchoP/4,alturaP/4);
        setResizable(true);
        */
        
        setJMenuBar(barra);
        barra.add(menuUsuario);
        barra.add(menu);
        comprarAccion evento1=new comprarAccion();
        comprar.addActionListener(evento1);
        consultarAccion evento2=new consultarAccion();
        consultar.addActionListener(evento2);
        salirAccion evento3=new salirAccion();
        salir.addActionListener(evento3);
        cerrarAccion evento4=new cerrarAccion();
        sesionClose.addActionListener(evento4);
        buscar.addActionListener( new buscarAccion() );
        menu.add(comprar);
        menu.add(consultar);
        menu.add(buscar);
        menuUsuario.add(sesionClose);
        menuUsuario.add(salir);
        
 
        
        //Tamaño de pantalla, imagen & titulo

        setTitle("Farmacia");
        Image miIcono=pantalla.getImage("src/images/icono.png");
        setIconImage(miIcono);
        
    
        Lamina2 recetaLamina=new Lamina2( this );
        add(recetaLamina); 
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
    
    private class cerrarAccion implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            LoginGUI login=new LoginGUI();
            login.setVisible(rootPaneCheckingEnabled);
            dispose();
        }
    }
    private class buscarAccion implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            buscarFarmacia buscar = new buscarFarmacia();
        }
    }
    
    private class salirAccion implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }
    private class agregar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            
        }
    }

    
    
    }