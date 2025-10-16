/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.ProdFamily;
import models.Product;

/**
 * FXML Controller class
 *
 * @author Ariel
 */
public class RegProductsCllr {

    @FXML private TextField lblDescription;
    @FXML private ComboBox<ProdFamily> slctFamilia;
    
    @FXML
    private void initialize() {
        lblDescription.setOnAction(e -> slctFamilia.requestFocus());
        if (ProdFamily.getItems().isEmpty()) {
            MainCllr.showAlert("Sin existencias", "Por favor primero añada una familia.");
        } else {
            slctFamilia.getItems().setAll(ProdFamily.getItems());
        }
    }
    
    @FXML
    private void addProduct() {
        String descrip = lblDescription.getText().trim();
        ProdFamily family = slctFamilia.getValue();
        
        if (descrip.isEmpty() || family == null) {
            MainCllr.showAlert("Campos vacios", "Por favor rellene todos los campos.");
            return;
        }
        
        Product prod = new Product(0, descrip, family.getId());
        if (Product.addProduct(prod)) {
            lblDescription.setText("");
            slctFamilia.setValue(null);
            MainCllr.showAlert("Listo!", "El producto se ha registrado con éxito.");
        }
    }
    
}
