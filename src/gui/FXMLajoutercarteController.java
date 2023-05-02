/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.carte;
import java.io.File;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.carteservices;

/**
 * FXML Controller class
 *
 * @author zaghdoudi
 */
public class FXMLajoutercarteController implements Initializable {
    
    @FXML
    private TextField id;
    @FXML
    private TextField idclient;
    @FXML
    private DatePicker date_ex;
    @FXML
    private TextField mp;
    @FXML
    private TextField login;
    @FXML
    private TextField num_carte;
    @FXML
    private TextField picture_name;
    @FXML
    private Button btnajouter;
    @FXML
    private Button retour;
    @FXML
    private Button ajouterimg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterCARTE(ActionEvent event) {
        if (id.getText().isEmpty()
                || idclient.getText().isEmpty()
                || date_ex.getValue().format(DateTimeFormatter.BASIC_ISO_DATE.ISO_DATE).isEmpty() || mp.getText().isEmpty()|| login.getText().isEmpty()|| num_carte.getText().isEmpty()|| picture_name.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plaît , veuillez remplir le formulaire");
            alert.showAndWait();
        } else {
            carteservices cs = new carteservices();
            carte c = new carte();

//LocalDate localDate = DatePicker.getValue();
//java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
//         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date dateDebut = dateFormat.parse(dateDebut.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        Date dateFin = dateFormat.parse(dateFin.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//    Offre o = new Offre(user, nom.getText(), secteur.getText(), description.getText(),dateDebut , dateFin, typeOffer.getSelectionModel(), competence.getText());
            c.setId(Integer.parseInt(id.getText()));
            c.setIdclient(Integer.parseInt(idclient.getText()));
            Date startDate = Date.valueOf(date_ex.getValue().toString());
            c.setDate_ex(startDate);
            c.setMp(mp.getText());
            c.setLogin(login.getText());
            c.setNum_carte(num_carte.getText());
            c.setPicture_name(picture_name.getText());

            cs.ajouter(c);
            JOptionPane.showMessageDialog(null, "carte Ajoutée avec succés ");

//        Alert alert = new Alert (Alert.AlertType.INFORMATION);
//            alert.setHeaderText (null);
//            alert.setContentText ( "ajoutée");
//            alert.showAndWait () ;
        }
    }
    

    @FXML
    private void retour(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("FXMLGerercarte.fxml"));
            retour.getScene().setRoot(loader);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void addimg(ActionEvent event) {
                 FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une image");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Fichiers image", "*.png", "*.jpg", "*.gif"),
        new FileChooser.ExtensionFilter("Tous les fichiers", ".")
    );
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
        String imagePath = selectedFile.getName();
        picture_name.setText(imagePath);
    }
    }
    
}
