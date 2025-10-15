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
public class RegClientCllr {

    @FXML private TextField lblRun;
    @FXML private TextField lblName;
    @FXML private TextField lblPLastName;
    @FXML private TextField lblSLastName;
    @FXML private ComboBox slctProduct;
      
    @FXML
    private void initialize() {
        
        lblRun.setOnAction(e -> lblName.requestFocus());
        lblName.setOnAction(e -> lblPLastName.requestFocus());
        lblPLastName.setOnAction(e -> lblSLastName.requestFocus());
        lblSLastName.setOnAction(e -> slctProduct.requestFocus());
    }
    
}
