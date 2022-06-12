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
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author YOUSSEF
 */
public class Fournisseur {
    
   public static LinkedList<Fournisseur> lp = new LinkedList<Fournisseur>();
    
   public int idFournisseur;
   
   public static int cmp=1;
   
   public String nomFournisseur,prenomFournisseur,typeProd;

    public Fournisseur() {
        
        this.idFournisseur=cmp;

        cmp++;
    }

    public Fournisseur(int idFournisseur, String nomFournisseur, String prenomFournisseur, String typeProd) {
        this.idFournisseur = cmp; cmp++;
        this.nomFournisseur = nomFournisseur;
        this.prenomFournisseur = prenomFournisseur;
        this.typeProd = typeProd;
    }

    @Override
    public String toString() {
        return "Fournisseur{" + "idFournisseur=" + idFournisseur + ", nomFournisseur=" + nomFournisseur + ", prenomFournisseur=" + prenomFournisseur + ", typeProd=" + typeProd + '}';
    }
    
    public static boolean CreateFournissueur(Fournisseur c){
        
        boolean res=false;
        
        int r=0;
        
        try {
            
            Connection cnx=SingletonConnection.getConnection();
            
            String sql="INSERT INTO `fournisseur`(`idFournisseur`, `nomFournisseur`, `prenomFournisseur`, `typeProd`) VALUES (null,?,?,?)";
            
            PreparedStatement ps=(PreparedStatement)cnx.prepareStatement(sql);
            
            ps.setString(1,c.nomFournisseur);
            
            ps.setString(2, c.prenomFournisseur);
            
            ps.setString(3, c.typeProd);
            
            r=ps.executeUpdate();
            
            if(r==1){
                res=true;
            }
            
        } catch (Exception e) {
        
        e.printStackTrace();
        }
        
        return res;
    }
    
     public static LinkedList<Fournisseur> getFournisseur(){
        
        LinkedList<Fournisseur>l=new LinkedList<Fournisseur>();
        
        try {
            
            Connection cnx=SingletonConnection.getConnection();
            
            String req="SELECT * FROM fournisseur";
            
            Statement st=cnx.createStatement();
            
            ResultSet rs=st.executeQuery(req);
            
            while (rs.next()) {  
                
                int idFournisseur=rs.getInt(1);
                
                String nomFournisseur=rs.getString(2);
    
                String prenomFournisseur=rs.getString(3);
                
                String typeProd=rs.getString(4);
                
                l.add(new Fournisseur(idFournisseur, nomFournisseur, prenomFournisseur, typeProd));
            }
            
            rs.close();
            
            st.close();
            
        } catch (Exception e) {
        e.printStackTrace();
        }
    
    return l;
    }
     
     public static boolean DelFournisseur(int id){
        boolean res=false;
        
        int r=0;
        
        try {
            
            Connection cnx=SingletonConnection.getConnection();
            
            String sql="Delete From fournisseur where idFournisseur=?";
            
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
     
     public  static boolean UpdateFournisseur(Fournisseur p, int id){
        
        boolean res=false;
        
        int r=0;
        
		try {
                    
                    Connection cnx=SingletonConnection.getConnection();
			
                    String sql="UPDATE fournisseur SET nomFournisseur=?, prenomFournisseur=?,typeProd=? where idFournisseur=?";
                    
                    PreparedStatement ps=(PreparedStatement)cnx.prepareStatement(sql);
                    
                                            	
                    ps.setString(1,p.nomFournisseur);
                    
                    ps.setString(2,p.prenomFournisseur);
                    
                    ps.setString(3,p.typeProd);
                    
                    ps.setInt(4, id);
                    
                    r=ps.executeUpdate();
                    
                    if(r==1){
                        
                        res=true;
                    }
                	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
                        System.out.println("sql err class");
		}
	
                return res;
    }
}
