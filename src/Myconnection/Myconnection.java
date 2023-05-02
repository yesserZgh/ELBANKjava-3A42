/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Myconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author zaghdoudi
 */
public class Myconnection {
     private Connection cnx;
    String url = "jdbc:mysql://localhost:3306/elbank";
    String user = "root";
    String pwd = "";
    public static Myconnection ct;

    private Myconnection() {
        try {
            cnx = DriverManager.getConnection(url,user,pwd);
            System.out.println("Cnx etablie ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static Myconnection getInstance(){
        if(ct ==null)
            ct= new Myconnection();
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }

    
}
