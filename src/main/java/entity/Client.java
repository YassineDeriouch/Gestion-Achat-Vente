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
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yassine
 */
public class Client {
    
    public int idClient;
           
    public String login, password;
    
    public LinkedList <Client> lc = new LinkedList<Client>();
    public static int cpt=1;
    
    public Client(){
        this.idClient= cpt;
        cpt++;
    }

    public Client(String login, String password) {
        this.idClient = idClient;
        this.login = login;
        this.password = password;
    }
    
    public static boolean createClient(Client c){
        boolean res=false;
        Connection cnx = SingletonConnection.getConnection();
        
        String req= "INSERT INTO client(idClient,login,password) VALUES(null,?,?)";
        int rs;
        try {
            PreparedStatement ps=(PreparedStatement)cnx.prepareStatement(req);
            ps.setString(1,c.login);
            ps.setString(2, c.password);                      
            rs = ps.executeUpdate();
          
            if(rs==1){
                res=true;
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
             System.err.println("SQL request error Client");  
    }
        return res;
    }
    
     public static boolean VerifierUserExist(String login, String password){
        boolean resExist = false;
        
                Connection cnx = SingletonConnection.getConnection();
                String sql = "SELECT login,password FROM client WHERE login=? AND password=?";
               try{ PreparedStatement ps = cnx.prepareStatement(sql);
                ps.setString(1, login);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    resExist=true;
                }}catch(SQLException e){
                    System.out.println("ERREUR SQL");
                }
        return resExist;
    }
    
    public static boolean AuthentificationClient(String login, String password) throws SQLException{
        boolean res = false;
        
        Connection cnx = SingletonConnection.getConnection();
        
        String req ="SELECT * FROM client WHERE login =? AND password=?";
        try{
            
        PreparedStatement pstmt = (PreparedStatement) cnx.prepareStatement(req);
        pstmt.setString(1,login);
        pstmt.setString(2, password);
        
        ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                res=true;
            }
            rs.close();
            pstmt.close();
            
        }catch(SQLException ex){
            ex.printStackTrace();
            System.err.println("SQL QUERY ERROR");
        }
        
    return res;
    }
       
    public static boolean delClient(int id){
         boolean res = false;
         Connection cnx = SingletonConnection.getConnection();
         String sql = "DELETE FROM client WHERE idClient = ?";
         
        try {
            PreparedStatement ps = cnx.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            int rs = ps.executeUpdate();
            if(rs ==1){
                res = true;
            }
            
        } catch (SQLException ex) {
             ex.printStackTrace();
             System.out.println("err sql");
        }
                  
   return res; 
}

        public static LinkedList<Client> getClient(){
            LinkedList<Client> listeC = new LinkedList<Client>();
            
            Connection cnx = SingletonConnection.getConnection();
            String sql = "SELECT * FROM client";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                
                String login = rs.getString(2);
                String password = rs.getString(3);
                Client c = new Client(login,password);
                listeC.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            
        return listeC; 
        }
}
