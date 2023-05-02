/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.carte;
import entity.categoriecarte;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class FXMLGerercategoriecarteController implements Initializable {
    
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
    @FXML
    private TextField search;
    categoriecarteservices cc = new categoriecarteservices();
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button Exporter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<categoriecarte> categoriecarte = cc.afficher();
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
        // TODO
    }    

    @FXML
    private void Ajoutercategorie(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("FXMLajoutercategoriecarte.fxml"));
            tableview.getScene().setRoot(loader);
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifiercategorie(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("FXMLModifiercategoriecarte.fxml"));
            tableview.getScene().setRoot(loader);
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supprimercategorie(ActionEvent event) {
        int selectedId = tableview.getSelectionModel().selectedItemProperty().get().getId();
        cc.supprimer(selectedId);  
        int idclient= tableview.getSelectionModel().getSelectedIndex();
        System.out.println(idclient);
        tableview.getItems().remove(idclient);
        
         Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setHeaderText (null);
            alert.setContentText ( "carte supprimée");
            alert.showAndWait () ;
    }
    
    private String getPath() {
        String userHome = System.getProperty("user.home");
        String fileSeparator = System.getProperty("file.separator");
        String documentsPath = userHome + fileSeparator + "Documents";
        System.out.println("User's documents path: " + documentsPath);
        return documentsPath;
    }

    @FXML
    private void exportercategorie(ActionEvent event) {
        try {
            //the file path
//            File file = new File("C:\\Users\\user\\Desktop\\image\\file.csv");
                        File file = new File(getPath() + "\\file.csv");

            //if the file not exist create one
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("id;date_categorie;type;description;prix;mantant_max;");
            bw.newLine();
            //loop for jtable rows
            for (int i = 0; i < tableview.getItems().size(); i++) {
                //loop for jtable column
                for (int j = 0; j < tableview.getColumns().size(); j++) {
                    bw.write(tableview.getColumns().get(j).getCellData(i) + ";");
                }
                bw.newLine();
            }
            //close BufferedWriter
            bw.close();
            //close FileWriter 
            fw.close();
            JOptionPane.showMessageDialog(null, "Data Exported");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
