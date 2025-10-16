/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import models.Class;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import models.ProdFamily;

/**
 * FXML Controller class
 *
 * @author Ariel
 */
public class RegEventsCllr {
    
    @FXML private ComboBox slctClass;
    
    @FXML
    private void initialize() {
        if (ProdFamily.getItems().isEmpty()) {
            MainCllr.showAlert("Sin existencias", "Por favor primero añada una clase.");
        } else {
            slctClass.getItems().setAll(Class.getItems());
        }
    }
    
}
