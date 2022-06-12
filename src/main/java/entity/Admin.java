/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import Singleton.SingletonConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yassine
 */
public class Admin {
    public int idAdmin;
    public String login, password ;
    public static int cpt =1;
    
    public Admin(){
        this.idAdmin = cpt;
        cpt++;
    }

    public Admin(int idAdmin, String login, String password) {
        this.idAdmin = idAdmin;
        this.login = login;
        this.password = password;
    }
    
    public static boolean createAdmin(Admin a){
        boolean res=false;
        Connection cnx = SingletonConnection.getConnection();
        
        String req= "INSERT INTO admin(idAdmin,login,password) VALUES(null,?,?)";
        int rs;
        try {
            PreparedStatement ps=(PreparedStatement)cnx.prepareStatement(req);
            ps.setString(1,a.login);
            ps.setString(2, a.password);                      
            rs = ps.executeUpdate();
          
            if(rs==1){
                res=true;
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
             System.err.println("SQL query error ");  
    }
        
        return res;
    }
   
    public static boolean Authentification(String login, String password) throws SQLException{
        boolean res = false;
        Connection cnx = SingletonConnection.getConnection();
        String req = "SELECT * FROM admin WHERE login = ? AND password = ?";
        try{
        PreparedStatement pstmt = (PreparedStatement) cnx.prepareStatement(req);
        pstmt.setString(1,login);
        pstmt.setString(2, password);
        
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            res = true;
        }
            rs.close();
            pstmt.close();
            
        }catch(SQLException ex){
            ex.printStackTrace();
            System.err.println("SQL QUERY ERROR !");
        }
        
    return res;
    }
    
}
