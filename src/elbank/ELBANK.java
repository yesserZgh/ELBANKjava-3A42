/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbank;

import entity.carte;
import entity.categoriecarte;
import java.sql.Date;
import services.carteservices;
import services.categoriecarteservices;

/**
 *
 * @author zaghdoudi
 */
public class ELBANK {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
           carte c1 = new carte(11, 2, new Date(2020,12,3), "ghghg", "yessssser", "yeeeeeser", "nnnnnn");
           categoriecarte cc2 = new categoriecarte(12, new Date(2021,12,3), "yerrt", "tyeh", 69, 126);
       //    carte c3 = new Offre(user, "Dr", "santé", "llll", "REA", new Date(2020,12,3), new Date(2020,12,3), "Stage");
          carteservices  cs = new carteservices();
          categoriecarteservices cc = new categoriecarteservices();
          cc.ajouter(cc2);
          cc.afficher();
          //cs.supprimer(0);
         // cs.modifier(0, 0, date_ex, mp, login, num_carte, picture_name);
          
    }
    
}
