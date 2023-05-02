/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.carte;
import entity.categoriecarte;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import services.carteservices;
import services.categoriecarteservices;

/**
 * FXML Controller class
 *
 * @author zaghdoudi
 */
public class FXMLajoutercategoriecarteController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField type;
    @FXML
    private TextField description;
    @FXML
    private TextField prix;
    @FXML
    private TextField montant;
    @FXML
    private Button ajouter;
    @FXML
    private Button retour;
    @FXML
    private DatePicker date_categorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutercategorie(ActionEvent event) {
        if (id.getText().isEmpty()
                || type.getText().isEmpty()
                || date_categorie.getValue().format(DateTimeFormatter.BASIC_ISO_DATE.ISO_DATE).isEmpty() || description.getText().isEmpty()|| prix.getText().isEmpty()|| montant.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plaît , veuillez remplir le formulaire");
            alert.showAndWait();
        } else {
            categoriecarteservices cs = new categoriecarteservices();
            categoriecarte cc = new categoriecarte();

//LocalDate localDate = DatePicker.getValue();
//java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
//         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date dateDebut = dateFormat.parse(dateDebut.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        Date dateFin = dateFormat.parse(dateFin.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//    Offre o = new Offre(user, nom.getText(), secteur.getText(), description.getText(),dateDebut , dateFin, typeOffer.getSelectionModel(), competence.getText());
            cc.setId(Integer.parseInt(id.getText()));
            cc.setType(type.getText());
            cc.setDescription(description.getText());
            cc.setPrix(Double.parseDouble(prix.getText()));
            cc.setMontant_max(Double.parseDouble(montant.getText()));
            Date startDate = Date.valueOf(date_categorie.getValue().toString());
            cc.setDate_categorie(startDate);
            cs.ajouter(cc);
            JOptionPane.showMessageDialog(null, "categorie carte Ajoutée avec succés ");

//        Alert alert = new Alert (Alert.AlertType.INFORMATION);
//            alert.setHeaderText (null);
//            alert.setContentText ( "ajoutée");
//            alert.showAndWait () ;
        }
    }

    @FXML
    private void retour(ActionEvent event) {
         try {
            Parent loader = FXMLLoader.load(getClass().getResource("FXMLGerercategoriecarte.fxml"));
            retour.getScene().setRoot(loader);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
