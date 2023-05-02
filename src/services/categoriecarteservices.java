/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Myconnection.Myconnection;
import entity.carte;
import entity.categoriecarte;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zaghdoudi
 */
public class categoriecarteservices {
    String sql = "";
        Connection cnx;

    public categoriecarteservices() {
        cnx = Myconnection.getInstance().getCnx();
    }
    public void ajouter(categoriecarte cc) {
        System.out.println(cc);
        try {
            sql = "insert into categoriecarte(id , type , description , prix , montant_max , date_categorie)values (?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, cc.getId());
            ste.setString(2, cc.getType());
            ste.setString(3, cc.getDescription());
            ste.setDouble(4, cc.getPrix());
            ste.setDouble(5, cc.getMontant_max());
            ste.setDate(6, cc.getDate_categorie());
            ste.executeUpdate();
            System.out.println("categorie carte ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<categoriecarte> afficher() {
        List<categoriecarte> categoriecarte = new ArrayList<>();
        try {
            sql = "select * from categoriecarte";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                categoriecarte cc = new categoriecarte();
                cc.setId(rs.getInt("id"));
                cc.setType(rs.getString("type"));
                cc.setDescription(rs.getString("description"));
                cc.setDate_categorie(rs.getDate("date_categorie"));
                cc.setPrix(rs.getDouble("prix"));
                cc.setMontant_max(rs.getDouble("montant_max"));
                categoriecarte.add(cc);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categoriecarte;
    }
    public void supprimer(int id) {
        try {
            sql = "delete from categoriecarte where id=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();
            System.out.println("categorie carte supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void modifier(int id, String type, String description,double prix, double montant_max,Date date_categorie) {
        System.out.println(id);
        String sql = "UPDATE categoriecarte SET  type=?, description=?, prix=?, montant_max=?, num_carte=? , date_categorie=? WHERE id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ste.setString(2, type);
            ste.setString(3, description);
            ste.setDouble(4, prix);
            ste.setDouble(5, montant_max);
            ste.setDate(6, date_categorie);
            ste.executeUpdate();
            System.out.println("categorie carte a éte modifié avec succées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    
    }
    
}
