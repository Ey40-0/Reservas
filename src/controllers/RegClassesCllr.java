/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ariel
 */
public class RegClassesCllr {

    @FXML private TextField fldName;
    @FXML private ComboBox slctFamily;
    
    @FXML
    private void initialize() {
        
        fldName.setOnAction(e -> slctFamily.requestFocus());
        
    }
}
