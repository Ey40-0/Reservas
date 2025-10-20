/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import models.Client;
import models.Event;
import models.Register;

/**
 * FXML Controller class
 *
 * @author Ariel
 */
public class ReservationsCllr {
    
    @FXML private ComboBox<Client> slctRun;
    @FXML private ComboBox<Event> slctEvent;
    
    private Client selected;
    
    @FXML
    private void initialize() {
        if (Client.getItems().isEmpty()) {
            MainCllr.showAlert("Sin existencias", "Por favor primero registre al menos un cliente y un evento.");
            return;
        }
        
        slctRun.getItems().setAll(Client.getItems());

        // Listener cuando se selecciona un cliente
        slctRun.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            selected = newSel;

            slctEvent.getItems().clear(); 
            if (selected != null) {
                slctEvent.getItems().setAll(Event.getItemsByFamily(selected.getId()));
            }
        });
    }
    
    @FXML
    private void addRegister() {
        Client cli = slctRun.getValue();
        Event eve = slctEvent.getValue();
        
        if (cli == null || eve == null) {
            MainCllr.showAlert("Campos vacios", "Por favor rellene todos los campos.");
            return;
        }
        
        Register reg = new Register(0, eve, cli);
        if (Register.addRegister(reg)) {
            slctRun.setValue(null);
            slctEvent.setValue(null);
            MainCllr.showAlert("Listo!", "El cliente se ha registrado en el evento con éxito.");
        }
    }
    
    @FXML
    private void backBtn() {
        MainCllr.getInstance().showPanel("/views/MainRoutesVw.fxml");
    }
}
