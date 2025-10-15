/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author Ariel
 */
public class MainRoutesCllr {

    @FXML
    private void btnReservas() {
        MainCllr.getInstance().showPanel("/views/ReservationsVw.fxml");
    }
    
    @FXML
    private void btnReportes() {
        MainCllr.getInstance().showPanel("/views/.fxml");
    }
    
    @FXML
    private void btnDependencias() {
        MainCllr.getInstance().showPanel("/views/DependencesRoutesVw.fxml");
    }
    
}
