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
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import services.categoriecarteservices;

/**
 * FXML Controller class
 *
 * @author zaghdoudi
 */
public class FXMLModifiercategoriecarteController implements Initializable {

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
    private DatePicker date_categorie;
    @FXML
    private Button modifier;
    @FXML
    private TableView<categoriecarte> tableview;
    @FXML
    private TableColumn<categoriecarte, Integer> colid;
    @FXML
    private TableColumn<categoriecarte, Date> coldate_categorie;
    @FXML
    private TableColumn<categoriecarte, String> coltype;
    @FXML
    private TableColumn<categoriecarte, String> coldescription;
    @FXML
    private TableColumn<categoriecarte, Double> colprix;
    @FXML
    private TableColumn<categoriecarte, Double> colmontant_max;
    categoriecarteservices os = new categoriecarteservices();
    int index = -1;
    @FXML
    private TextField search;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<categoriecarte> categoriecarte = os.afficher();
             categoriecarte cc =new categoriecarte();
             ObservableList<categoriecarte> listCategorie = FXCollections.observableArrayList(categoriecarte); 
             System.out.println(listCategorie);
             colid.setCellValueFactory(new PropertyValueFactory<>("id"));       
             coldate_categorie.setCellValueFactory(new PropertyValueFactory<>("date_categorie"));
             coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
             coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
             colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
             colmontant_max.setCellValueFactory(new PropertyValueFactory<>("montant_max"));
             
             tableview.setItems(listCategorie); 
        // TODO
         search.textProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                tableview.setItems(listCategorie);
            }
            String value = newValue.toLowerCase();
            ObservableList<categoriecarte> subentries = FXCollections.observableArrayList();
            for (categoriecarte entry : tableview.getItems()) {
                boolean match = true;
                
                String type = entry.getType();
                String description = entry.getDescription();
                
                

                if (!type.toLowerCase().contains(value)
                        && !description.toLowerCase().contains(value))
                         {
                    match = false;
                }
                if (match) {
                    subentries.add(entry);
                }
            }
            tableview.setItems(subentries);
        });
    }    

    @FXML
    private void modifiercategorie(ActionEvent event) {
        Date startDate = Date.valueOf(date_categorie.getValue().toString());

        int id = tableview.getSelectionModel().getSelectedItem().getId();
//        os.modifier(Integer.parseInt(proprietaire.getText()), nom.getText(), secteur.getText(), description.getText(), competence.getText(), startDate, endDate, typeOffre.getSelectionModel().getSelectedItem(), id);
//        this.onStart();
        int response = JOptionPane.showConfirmDialog(null, "voulez vous confirmer les modifications de " + id + "?", "Confirmer", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            System.out.println("User clicked Yes");
            os.modifier(id, type.getText(),description.getText(),Double.parseDouble(prix.getText()),Double.parseDouble(montant.getText()),startDate);
        } else {
            System.out.println("User clicked No");
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
