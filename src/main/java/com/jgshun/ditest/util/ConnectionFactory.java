/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jgshun.ditest.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jgshun
 */
public class ConnectionFactory {
    private static final ThreadLocal<Connection> tc = new ThreadLocal<Connection>();
    private static final Properties prop = new Properties();
    private static String driver;
    private static String username;
    private static String password;
    private static String url;
    
    static{
        try {
            prop.load(ConnectionFactory.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            driver = prop.getProperty("driver");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            url = prop.getProperty("url");
            
        } catch (IOException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConn(){
         Connection conn = null;
        try {
            if(tc.get() == null || tc.get().isClosed()){
                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);
                tc.set(conn);
            }else{
                conn = tc.get();
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
             Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
         return conn;
    }
    
    public static void closeConn(Connection conn){
        try {
            if(conn !=null && !conn.isClosed()) conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeState(Statement state){
        try {
            if(state !=null && state.isClosed()) state.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeRs(ResultSet rs){
        try {
            if(rs !=null && rs.isClosed()) rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
