/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Myconnection.Myconnection;
import entity.carte;
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
public class carteservices {
        String sql = "";
        Connection cnx;

    public carteservices() {
        cnx = Myconnection.getInstance().getCnx();
    }
    
    
    public void ajouter(carte c) {
        System.out.println(c);
        try {
            sql = "insert into carte(id , idclient , date_ex , mp , login , num_carte , picture_name)values (?,?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, c.getId());
            ste.setInt(2, c.getIdclient());
            ste.setDate(3, c.getDate_ex());
            ste.setString(4, c.getMp());
            ste.setString(5, c.getLogin());
            ste.setString(6, c.getNum_carte());
            ste.setString(7, c.getPicture_name());
            ste.executeUpdate();
            System.out.println("carte ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<carte> afficher() {
        List<carte> carte = new ArrayList<>();
        try {
            sql = "select * from carte";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                carte c = new carte();
                c.setId(rs.getInt("id"));
                c.setIdclient(rs.getInt("idclient"));
                c.setDate_ex(rs.getDate("date_ex"));
                c.setMp(rs.getString("date_ex"));
                c.setLogin(rs.getString("login"));
                c.setNum_carte(rs.getString("num_carte"));
                c.setPicture_name(rs.getString("picture_name"));
                carte.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return carte;
    }
    
    public void supprimer(int id) {
        try {
            sql = "delete from offre where id=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();
            System.out.println("carte supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void modifier(int id, int idclient, Date date_ex, String mp, String login, String num_carte, String picture_name) {
        System.out.println(id);
        String sql = "UPDATE carte SET  idclient=?, date_ex=?, mp=?, login=?, num_carte=? , picture_name=? WHERE id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ste.setInt(2, idclient);
            ste.setDate(3, date_ex);
            ste.setString(4, mp);
            ste.setString(5, login);
            ste.setString(6, num_carte);
            ste.setString(7, picture_name);
            ste.executeUpdate();
            System.out.println("carte a éte modifié avec succées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    
    }
}
