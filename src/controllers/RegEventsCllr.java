/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import models.Class;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.Event;
import models.ProdFamily;

/**
 * FXML Controller class
 *
 * @author Ariel
 */
public class RegEventsCllr {
    
    @FXML private ComboBox<Class> slctClass;
    @FXML private DatePicker fldDate;
    @FXML private TextField dtStart;
    @FXML private TextField dtEnd;
    
    @FXML
    private void initialize() {
        if (ProdFamily.getItems().isEmpty()) {
            MainCllr.showAlert("Sin existencias", "Por favor primero añada una clase.");
        } else {
            slctClass.getItems().setAll(Class.getItems());
        }
    }
    
    @FXML
    private void addEvent() {
        Class clas = slctClass.getValue();
        LocalDate date = fldDate.getValue();
        String start = dtStart.getText().trim();
        String end = dtEnd.getText().trim();
        
        if (clas == null || date == null || start.isEmpty() || end.isEmpty()) {
            MainCllr.showAlert("Campos vacios", "Por favor rellene todos los campos.");
            return;
        }
        
        if (!MainCllr.validateTime(start) || !MainCllr.validateTime(end)) {
            MainCllr.showAlert("Formato incorrecto", "Por favor ingrese una hora válida.");
            return;
        }
        
        // Convertir las horas a LocalTime
        LocalTime startTime = MainCllr.formatTime(start);
        LocalTime endTime = MainCllr.formatTime(end);
        
        // Combinar fecha y hora en LocalDateTime
        LocalDateTime startAt = LocalDateTime.of(date, startTime);
        LocalDateTime endAt = LocalDateTime.of(date, endTime);

        Event eve = new Event(0, clas.getId(), startAt, endAt);
        // Guardar en la base de datos
        if (Event.addEvent(eve)) {
            slctClass.setValue(null);
            fldDate.setValue(null);
            dtStart.setText("");
            dtEnd.setText("");
            MainCllr.showAlert("Listo!", "El evento se ha registrado con éxito.");
        }
    }
    
}
