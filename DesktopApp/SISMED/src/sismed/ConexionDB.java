package sismed;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GerardoAramis
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ConexionDB {
    private Connection conn;
    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String DB_URL = "jdbc:mysql://db4free.net:3306/clinica_0110";
    String USER = "steven_0110";
    String PASS = "123456789";
    public ConexionDB( String db_url, String user, String psw){
        DB_URL = db_url;
        USER = user;
        PASS = psw;
        try{
            Class.forName( JDBC_DRIVER );
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ConexionDB(){
        try{
            Class.forName( JDBC_DRIVER );
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet consulta( String sql ){
        ResultSet rs = null;
        Statement stm;
        try {
            stm = conn.createStatement();
            rs = stm.executeQuery( sql );
        } catch ( SQLException ex ) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public void actualizar( String sql ){
        try {
            Statement stm =  conn.createStatement();
            stm.executeUpdate( sql );
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void insertar( String sql ){
        try {
            Statement stm =  conn.createStatement();
            stm.execute( sql );
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarConexion(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
