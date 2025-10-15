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
public class RegProductsCllr {

    @FXML private TextField lblDescription;
    @FXML private ComboBox slctFamilia;
    
    @FXML
    private void initialize() {
        // cragar familias ingresadas
        // si no hay, devolver que todavia no hay

        lblDescription.setOnAction(e -> slctFamilia.requestFocus());
    }
    
}
