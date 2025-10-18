/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import models.Client;
import models.Class;

/**
 * FXML Controller class
 *
 * @author Ariel
 */
public class ReservationsCllr {
    
    @FXML private ComboBox<Client> slctRun;
    @FXML private ComboBox<Class> slctClass;
    
    @FXML
    private void backBtn() {
        MainCllr.getInstance().showPanel("/views/MainRoutesVw.fxml");
    }
}
