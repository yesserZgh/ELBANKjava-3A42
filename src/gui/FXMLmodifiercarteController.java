/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.carte;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import services.carteservices;

/**
 * FXML Controller class
 *
 * @author zaghdoudi
 */
public class FXMLmodifiercarteController implements Initializable {

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
    private Button modifier;
    carteservices os = new carteservices();
    int index = -1;
    @FXML
    private Button retour;
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             List<carte> carte = os.afficher();
             ObservableList<carte> listCarte = FXCollections.observableArrayList();
             listCarte.setAll(carte);
             colid.setCellValueFactory(new PropertyValueFactory<>("id"));       
             colidclient.setCellValueFactory(new PropertyValueFactory<>("idclient"));
             coldate_ex.setCellValueFactory(new PropertyValueFactory<>("date_ex"));
             colmp.setCellValueFactory(new PropertyValueFactory<>("mp"));
             collogin.setCellValueFactory(new PropertyValueFactory<>("login"));
             colnum_carte.setCellValueFactory(new PropertyValueFactory<>("num_carte"));
             colpicture_name.setCellValueFactory(new PropertyValueFactory<>("picture_name"));
             tableview.setItems(listCarte);
        // TODO
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
    }    

    @FXML
    private void modifiercarte(ActionEvent event) {
        Date startDate = Date.valueOf(date_ex.getValue().toString());

        int id = tableview.getSelectionModel().getSelectedItem().getId();
        int idclient = tableview.getSelectionModel().getSelectedItem().getIdclient();


//        os.modifier(Integer.parseInt(proprietaire.getText()), nom.getText(), secteur.getText(), description.getText(), competence.getText(), startDate, endDate, typeOffre.getSelectionModel().getSelectedItem(), id);
//        this.onStart();
        int response = JOptionPane.showConfirmDialog(null, "voulez vous confirmer les modifications de " + id + "?", "Confirmer", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            System.out.println("User clicked Yes");
            os.modifier(id, idclient, startDate,mp.getText(),login.getText(),num_carte.getText(),picture_name.getText());
        } else {
            System.out.println("User clicked No");
        }
    }
    @FXML
    private void getSelected(MouseEvent event) {
        index= tableview.getSelectionModel().getSelectedIndex();
             if (index<=-1) {
                 return;
             }
             id.setText(colid.getCellData(index).toString());
             idclient.setText(colidclient.getCellData(index).toString());
             Format formatter = new SimpleDateFormat("yyyy-MM-dd");
               String dateex= formatter.format(coldate_ex.getCellData(index));
               date_ex.setValue(LocalDate.parse(dateex));
               mp.setText(colmp.getCellData(index).toString());
               login.setText(collogin.getCellData(index).toString());
               num_carte.setText(colnum_carte.getCellData(index).toString());
               picture_name.setText(colpicture_name.getCellData(index).toString());
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

    
    
}
