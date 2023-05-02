/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author zaghdoudi
 */
public class categoriecarte {
    private int id;
    private Date date_categorie ;
    private String type, description ;
    private double prix,montant_max;

    public categoriecarte(int id, Date date_categorie, String type, String description, double prix, double montant_max) {
        this.id = id;
        this.date_categorie = date_categorie;
        this.type = type;
        this.description = description;
        this.prix = prix;
        this.montant_max = montant_max;
    }

    public categoriecarte(Date date_categorie, String type, String description, double prix, double montant_max) {
        this.date_categorie = date_categorie;
        this.type = type;
        this.description = description;
        this.prix = prix;
        this.montant_max = montant_max;
    }

    public categoriecarte() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_categorie() {
        return date_categorie;
    }

    public void setDate_categorie(Date date_categorie) {
        this.date_categorie = date_categorie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getMontant_max() {
        return montant_max;
    }

    public void setMontant_max(double montant_max) {
        this.montant_max = montant_max;
    }

    @Override
    public String toString() {
        return "categoriecarte{" + "id=" + id + ", date_categorie=" + date_categorie + ", type=" + type + ", description=" + description + ", prix=" + prix + ", montant_max=" + montant_max + '}';
    }
    
    
}
