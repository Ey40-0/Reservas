/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import models.Class;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.ProdFamily;

/**
 * FXML Controller class
 *
    * @author Ariel
 */
public class RegClassesCllr {

    @FXML private TextField fldName;
    @FXML private ComboBox<ProdFamily> slctFamily;

    @FXML
    private void initialize() {   
        fldName.setOnAction(e -> slctFamily.requestFocus());
        slctFamily.getItems().addAll(ProdFamily.getItems());
    }
    
    @FXML
    private void addClass() {
        String name = fldName.getText().trim();
        ProdFamily family = slctFamily.getValue();
        
        if (name.isEmpty() || family == null) {
            MainCllr.showAlert("Campos vacios", "Por favor rellene todos los campos.");
            return;
        }
        
        Class clas = new Class(0, name, family.getId());
        if (Class.addClass(clas)) {
            fldName.setText("");
            slctFamily.setValue(null);
            MainCllr.showAlert("Listo!", "La clase se ha registrado con éxito.");
        }
    }
    
}
