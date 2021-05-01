/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author CristhianFriasJaldin
 */
public class ConexionDB {
    static String url ="jdbc:mysql://localhost:3306/bd_biblio";
    static String usuario ="root";
    static String password = "";
    
    protected Connection conn = null;
    
    public ConexionDB() {
    try {
    Class.forName("com.mysql.jdbc.Driver");
    conn = DriverManager.getConnection(url,usuario,password);
    }catch(ClassNotFoundException ex)
            {
                System.out.println("Falta driver "+ex.getMessage());
            
            } 
    catch(SQLException ex)
    {
        System.out.println("Error de sql"+ex.getMessage());
    }
    
    
}
public Connection conectar()
{
return conn;
}

    /**
     *
     */
    public void desconectar()
{}}
