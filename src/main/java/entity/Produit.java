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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YOUSSEF
 */
public class Produit {
    public int idProd;
    
    public static int cmp=1;
    
    public static LinkedList<Produit> lp = new LinkedList<Produit>();
    
    public String nomProd;
    
    public Double PU;
    
    public int quantiteProd;
    
    public Date dateEntre;
    
    public Date dateExp;
    
    public String Description;

    public Produit() {
    
        this.idProd=cmp;
        
        cmp++;
    
    }

    public Produit(int idProd, String nomProd, Double PU, int quantiteProd, Date dateEntre, Date dateExp, String Description) {
        this.idProd = idProd;
        this.nomProd = nomProd;
        this.PU = PU;
        this.quantiteProd = quantiteProd;
        this.dateEntre = dateEntre;
        this.dateExp = dateExp;
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "Produit{" + "idProd=" + idProd + ", nomProd=" + nomProd + ", PU=" + PU + ", quantiteProd=" + quantiteProd + ", dateEntre=" + dateEntre + ", dateExp=" + dateExp + ", Description=" + Description + '}';
    }
            
    public static boolean CreateProduit(Produit c){
        
        boolean res=false;
        
        int r=0;
        
        try {
            
            Connection cnx=SingletonConnection.getConnection();
            
            String sql="INSERT INTO produit(`idProd`, `nomProd`, `PU`, `quantiteProd`, `dateEntre`, `dateExp`, `Description`) VALUES (null,?,?,?,?,?,?)";
            
            PreparedStatement ps=(PreparedStatement)cnx.prepareStatement(sql);
            
            ps.setString(1,c.nomProd);
            
            ps.setDouble(2, c.PU);
            
            ps.setDouble(3, c.quantiteProd);
            
            ps.setDate(4,c.dateEntre);
            
            ps.setDate(5,c.dateExp);
            
            ps.setString(6,c.Description);
            
            r=ps.executeUpdate();
            
            if(r==1){
                res=true;
            }
            
        } catch (Exception e) {
        
        e.printStackTrace();
        }
        
        return res;
    }
    
    public static LinkedList<Produit> getProduit(){
        
        LinkedList<Produit>l=new LinkedList<Produit>();
        
        try {
            
            Connection cnx=SingletonConnection.getConnection();
            
            String req="SELECT * FROM produit";
            
            Statement st=cnx.createStatement();
            
            ResultSet rs=st.executeQuery(req);
            
            while (rs.next()) {  
                
                int idProd=rs.getInt(1);
                
                String nomProd=rs.getString(2);
    
                Double PU=rs.getDouble(3);
    
                int quantiteProd=rs.getInt(4);
        
                Date dateEntre=rs.getDate(5);
    
                Date dateExp=rs.getDate(6);
    
                String Description=rs.getString(7);
                
                
                l.add(new Produit(idProd, nomProd, PU, quantiteProd, dateEntre, dateExp, Description));
            }
            
            rs.close();
            
            st.close();
            
        } catch (Exception e) {
        e.printStackTrace();
        }
    
    return l;
    }
    
    public static boolean DelProd(int id){
        boolean res=false;
        
        int r=0;
        
        try {
            
            Connection cnx=SingletonConnection.getConnection();
            
            String sql="Delete From produit where idProd=?";
            
            PreparedStatement ps=(PreparedStatement)cnx.prepareStatement(sql);
            
            ps.setInt(1,id);
            
            r=ps.executeUpdate();
            
            if(r==1){
                
                res=true;
            }
            
        } catch (Exception e) {
        
        e.printStackTrace();
        }
        
        
        return res;
    }
    
    public  static boolean UpdateProduit(Produit p, int id){
        
        boolean res=false;
        
        int r=0;
        
		try {
                    
                    Connection cnx=SingletonConnection.getConnection();
			
                    String sql="UPDATE produit SET idProd=?, nomProd=?,PU=?,quantiteProd=?,dateEntre=?,dateExp=?,Description=? where idProd=?";
                    
                    PreparedStatement ps=(PreparedStatement)cnx.prepareStatement(sql);
                    
                    ps.setInt(1, p.idProd);
                        	
                    ps.setString(2,p.nomProd);
			
                    ps.setDouble(3,p.PU);
                    
                    ps.setDouble(4,p.quantiteProd);
                    
                    ps.setDate(5,p.dateEntre);
                    
                    ps.setDate(6,p.dateExp);
                    
                    ps.setString(7,p.Description);
                    
                    ps.setInt(8, id);
                    
                    r=ps.executeUpdate();
                    
                    if(r==1){
                        
                        res=true;
                    }
                	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
                return res;
    }
    
    public static double VisEtatProd(String nomProd){
        
        boolean res=false;
        
        double qteprod=0;
        
        try {
              Connection cnx=SingletonConnection.getConnection();
			
                    String sql="SELECT quantiteProd FROM produit WHERE nomProd=?";
                    
                    PreparedStatement ps=(PreparedStatement)cnx.prepareStatement(sql);
                    
                    ps.setString(1,nomProd);
                    
                    ResultSet rs=ps.executeQuery();
                    
                    while (rs.next()) {                
                
                   qteprod=rs.getDouble(1);
                        
                    res=true;
            }
                    
                    rs.close();
                    
                    ps.close();
            
        } catch (Exception e) {
        
        e.printStackTrace();
        }
        return qteprod;
    }
    
    
    public static boolean updateQteProd(String nomProd,int qteprod){
        boolean res=false;
        int r=0;
        
        try {
            
            Connection cnx=SingletonConnection.getConnection();
            
            String sql="update produit set quantiteProd=? where nomProd=?";
            
            PreparedStatement ps=(PreparedStatement)cnx.prepareStatement(sql);
            
            ps.setInt(1,qteprod);
            
            ps.setString(2,nomProd);
            
            r=ps.executeUpdate();
            
            if(r==1){
                res=true;
            }
            
        } catch (Exception e) {
        e.printStackTrace();
        }
        
        return res;
    }
    
    public static int GetPU(String nomProd){
        
        boolean res=false;
        
        int pu=0;
        
        try {
              Connection cnx=SingletonConnection.getConnection();
			
                    String sql="SELECT PU FROM produit WHERE nomProd=?";
                    
                    PreparedStatement ps=(PreparedStatement)cnx.prepareStatement(sql);
                    
                    ps.setString(1,nomProd);
                    
                    ResultSet rs=ps.executeQuery();
                    
                    while (rs.next()) {                
                
                   pu=rs.getInt(1);
                        
                    res=true;
            }
                    
                    rs.close();
                    
                    ps.close();
            
        } catch (Exception e) {
        
        e.printStackTrace();
        }
        return pu;
    }
    
}
