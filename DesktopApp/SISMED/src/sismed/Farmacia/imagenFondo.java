/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sismed.Farmacia;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author ricar
 */
public class imagenFondo extends JPanel{
    public void paintComponents(Graphics g){
        Dimension tam=getSize();
        
        ImageIcon imagen=new ImageIcon(new ImageIcon(getClass().getResource("images/medicina.jpg")).getImage());
        g.drawImage(imagen.getImage(),0,0,tam.width,tam.height,null);
        
    }
    
}
