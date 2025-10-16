/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.ProdFamily;

/**
 * FXML Controller class
 *
 * @author Ariel
 */
public class RegFamProductsCllr {
    
    @FXML private TextField lblDescription;
    
    @FXML
    private void addFamily() {
        String descrip = lblDescription.getText().trim();
        
        if (descrip.isEmpty()) {
            MainCllr.showAlert("Campos vacios", "Por favor rellene todos los campos.");
            return;
        }
        
        ProdFamily fam = new ProdFamily(0, descrip);
        if (ProdFamily.addFamily(fam)) {
            lblDescription.setText("");
            MainCllr.showAlert("Listo!", "La familia se ha registrado con éxito.");
        }
    }
    
}
