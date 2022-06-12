/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Yassine
 */
public class SingletonConnection {
    
    public static Connection connection;
    
    static{
        try{
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_achat2","root","");
            System.out.println("CONNECTED");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("DB Connection failed !");
        }
    }
    public static Connection getConnection(){
        return connection;
    }
    
}
