/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.carte;
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
import services.carteservices;

/**
 * FXML Controller class
 *
 * @author zaghdoudi
 */
public class FXMLGerercarteController implements Initializable {
    
    

    @FXML
    private TableView<carte> tableview;
    @FXML
    private TableColumn<carte, Integer> colid;
    @FXML
    private TableColumn<carte, Integer> colidclient;
    @FXML
    private TableColumn<carte, Date> coldate_ex;
    @FXML
    private TableColumn<carte, String> colmp;
    @FXML
    private TableColumn<carte, String> collogin;
    @FXML
    private TableColumn<carte, String> colnum_carte;
    @FXML
    private TableColumn<carte, String> colpicture_name;
    @FXML
    private TextField search;
    carteservices ps = new carteservices();
    @FXML
    private Button ajouter;
    @FXML
    private Button Supprimercarte;
    @FXML
    private Button Modifier;
    @FXML
    private Button Exporter;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<carte> carte = ps.afficher();
             carte c =new carte();
             ObservableList<carte> listCarte = FXCollections.observableArrayList(carte); 
             System.out.println(listCarte);
             colid.setCellValueFactory(new PropertyValueFactory<>("id"));       
             colidclient.setCellValueFactory(new PropertyValueFactory<>("idclient"));
             coldate_ex.setCellValueFactory(new PropertyValueFactory<>("date_ex"));
             colmp.setCellValueFactory(new PropertyValueFactory<>("mp"));
             collogin.setCellValueFactory(new PropertyValueFactory<>("login"));
             colnum_carte.setCellValueFactory(new PropertyValueFactory<>("num_carte"));
             colpicture_name.setCellValueFactory(new PropertyValueFactory<>("picture_name"));
             
             tableview.setItems(listCarte); 
             
             search.textProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                tableview.setItems(listCarte);
            }
            String value = newValue.toLowerCase();
            ObservableList<carte> subentries = FXCollections.observableArrayList();
            for (carte entry : tableview.getItems()) {
                boolean match = true;
                
                String mp = entry.getMp();
                String login = entry.getLogin();
                String num_carte = entry.getNum_carte();
                String picture_name = entry.getPicture_name();
                

                if (!mp.toLowerCase().contains(value)
                        && !login.toLowerCase().contains(value)
                        && !num_carte.toLowerCase().contains(value)
                        && !picture_name.toLowerCase().contains(value))
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
    private void AjouterCarte(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("FXMLajoutercarte.fxml"));
            tableview.getScene().setRoot(loader);
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void supprimerCarte(ActionEvent event) {
        int selectedId = tableview.getSelectionModel().selectedItemProperty().get().getId();
        ps.supprimer(selectedId);  
        int idclient= tableview.getSelectionModel().getSelectedIndex();
        System.out.println(idclient);
        tableview.getItems().remove(idclient);
        
         Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setHeaderText (null);
            alert.setContentText ( "carte supprimée");
            alert.showAndWait () ;
    }

    @FXML
    private void Modifercarte(ActionEvent event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("FXMLmodifiercarte.fxml"));
            tableview.getScene().setRoot(loader);
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private String getPath() {
        String userHome = System.getProperty("user.home");
        String fileSeparator = System.getProperty("file.separator");
        String documentsPath = userHome + fileSeparator + "Documents";
        System.out.println("User's documents path: " + documentsPath);
        return documentsPath;
    }

    @FXML
    private void Exporter(ActionEvent event) {
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

            bw.write("id;idclient;date_ex;mp;login;num_carte;picture_name;");
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
