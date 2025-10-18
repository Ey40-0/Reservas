/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.Client;
import models.Product;

/**
 * FXML Controller class
 *
 * @author Ariel
 */
public class RegClientCllr {

    @FXML private TextField lblRun;
    @FXML private TextField lblName;
    @FXML private TextField lblLastName;
    
    @FXML private DatePicker fldStarted;
    @FXML private DatePicker fldEnded;
    
    @FXML private ComboBox<Product> slctProduct;
      
    @FXML
    private void initialize() {
        
        if (Product.getItems().isEmpty()) {
            MainCllr.showAlert("Sin existencias", "Por favor primero añada una familia.");
        } else {
            slctProduct.getItems().addAll(Product.getItems());
        }
        
        lblRun.setOnAction(e -> lblName.requestFocus());
        lblName.setOnAction(e -> lblLastName.requestFocus());
        lblLastName.setOnAction(e -> slctProduct.requestFocus());
    }
    
    @FXML
    private void addClient() {
        String run = lblRun.getText();
        String name = lblName.getText();
        String lastName = lblLastName.getText();
        
        Product product = slctProduct.getValue();
        
        LocalDate started = fldStarted.getValue();
        LocalDate ended = fldEnded.getValue();
        
        if (run.isEmpty() || name.isEmpty() || lastName.isEmpty() || product == null || started == null || ended == null)  {
            MainCllr.showAlert("Campos vacios", "Por favor rellene todos los campos.");
            return;
        }
        
        if (!MainCllr.validateRun(run)) {
            MainCllr.showAlert("Run invalido", "Por favor ingrese un run valido.");
            return;
        }
        
        Client cli = new Client(0, MainCllr.formatRun(run), name, lastName, product.getId(), started, ended);
        if (Client.addClient(cli)) {
            lblRun.setText("");
            lblName.setText("");
            lblLastName.setText("");
            slctProduct.setValue(null);
            fldStarted.setValue(null);
            fldEnded.setValue(null);
            MainCllr.showAlert("Listo!", "El cliente se ha registrado con éxito.");
        }
    }
    
    @FXML
    private void listClients() {
        GralRoutesCllr.getInstance().listClients();
    }
}
