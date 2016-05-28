/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependiente;


import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import sismed.ConexionDB;
import sismed.LoginGUI;


/**
 *
 * @author ricar
 */
public class recetaFarmacia {
    public static void main(String[] args){
        ventana2 miventana=new ventana2();
        miventana.setVisible(true);
        miventana.setSize(100,100);
        miventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
