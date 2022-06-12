/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import Singleton.SingletonConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author YOUSSEF
 */
public class Commande {
    
   public static LinkedList<Commande> lp = new LinkedList<Commande>();
    
    public int idCommande, idClient, idProd ;
    
    public static int cmp=1;
    
    public String nomCommande;
    
    public Date dateCommande;
          
    public int QuantiteProdCommande;
    
    public String nomClient;
    
    public int prixTotal;

    public Commande() {
    
    this.idCommande=cmp;
    
    cmp++;
            
    }

    public Commande(int idClient , int idProd, String nomCommande, Date dateCommande, int QuantiteProdCommande, String nomClient,int prixTotal) {
        this.idClient = idClient;
        this.idProd = idProd;
        this.idCommande = cmp;
        cmp++;
        this.nomCommande = nomCommande;
        this.dateCommande = dateCommande;
        this.QuantiteProdCommande = QuantiteProdCommande;
        this.nomClient = nomClient;
        this.prixTotal = prixTotal;
    }

    @Override
    public String toString() {
        return "Commande{" + "idCommande=" + idCommande + ", nomCommande=" + nomCommande + ", dateCommande=" + dateCommande + ", QuantiteProdCommande=" + QuantiteProdCommande + ", nomClient=" + nomClient + ", prixTotal=" + prixTotal + '}';
    }
    
//     public static LinkedList<Commande> getCommande(){
//        
//        LinkedList<Commande> l = new LinkedList<Commande>();
//        
//        try {
//            
//            Connection cnx=SingletonConnection.getConnection();
//            
//            String req="SELECT * FROM commande";
//            
//            Statement st=cnx.createStatement();
//            
//            ResultSet rs=st.executeQuery(req);
//            
//            while (rs.next()) {                
//                
//                String nomCommande=rs.getString(2);
//    
//                Date dateCommande=rs.getDate(3);
//                
//                int QuantiteProdCommande=rs.getInt(4);
//    
//                String nomClient=rs.getString(5);
//                
//                int prixTotal=rs.getInt(6);
//    
//                l.add(new Commande(nomCommande, dateCommande, QuantiteProdCommande, nomClient, prixTotal));
//            }
//            
//            rs.close();
//            
//            st.close();
//            
//        } catch (Exception e) {
//        e.printStackTrace();
//        }
//    
//    return l;
//    }
    
    public static boolean commander_prod(Commande c) {
        
        boolean res=false;
        
        try {
            
            Connection cnx=SingletonConnection.getConnection();
            
            String sql="INSERT INTO `commande`(`idProd`, `idClient`, `idCommande`, `nomCommande`, `dateCommande`, `quantitteProdCommande`, `nomClient`, `prixTotal`) "
            + " VALUES (?,?,null,?,?,?,?,?)";
            PreparedStatement ps= cnx.prepareStatement(sql);
            Produit p = new Produit();
            Client c1 = new Client();
            ps.setInt(1, p.idProd);
            ps.setInt(2,c1.idClient);
            ps.setString(3,c.nomCommande);
            ps.setDate(4, c.dateCommande);
            ps.setInt(5, c.QuantiteProdCommande);
            ps.setString(6, c.nomClient);
            ps.setInt(7, c.prixTotal);
            
            
            int i = ps.executeUpdate();
            if (i==1) {
               res=true; 
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
        
          return res;
    }
    
    public static int getIDClient(String login, String password){
        
        int idClient = 0;
        try {
            Connection cnx=SingletonConnection.getConnection();
            String sql =" SELECT idClient FROM Client where login = ? AND password = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               idClient = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idClient;
    }
    
    public static int getIDProd(String nomProd){
        
        int idProd = 0;
        try {
            Connection cnx=SingletonConnection.getConnection();
            String sql =" SELECT idProd FROM produit where nomProd= ?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setString(1, nomProd);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               idProd = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idProd;
    }
}
