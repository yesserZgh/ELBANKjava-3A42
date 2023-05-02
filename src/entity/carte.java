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
public class carte {
    private int id, idclient;
    private Date date_ex ;
    private String mp , login , num_carte , picture_name;

    public carte(int id, int idclient, Date date_ex, String mp, String login, String num_carte, String picture_name) {
        this.id = id;
        this.idclient = idclient;
        this.date_ex = date_ex;
        this.mp = mp;
        this.login = login;
        this.num_carte = num_carte;
        this.picture_name = picture_name;
    }

    public carte(int idclient, Date date_ex, String mp, String login, String num_carte, String picture_name) {
        this.idclient = idclient;
        this.date_ex = date_ex;
        this.mp = mp;
        this.login = login;
        this.num_carte = num_carte;
        this.picture_name = picture_name;
    }

    public carte() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public Date getDate_ex() {
        return date_ex;
    }

    public void setDate_ex(Date date_ex) {
        this.date_ex = date_ex;
    }

    public String getMp() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNum_carte() {
        return num_carte;
    }

    public void setNum_carte(String num_carte) {
        this.num_carte = num_carte;
    }

    public String getPicture_name() {
        return picture_name;
    }

    public void setPicture_name(String picture_name) {
        this.picture_name = picture_name;
    }

    @Override
    public String toString() {
        return "carte{" + "id=" + id + ", idclient=" + idclient + ", date_ex=" + date_ex + ", mp=" + mp + ", login=" + login + ", num_carte=" + num_carte + ", picture_name=" + picture_name + '}';
    }

    
}

