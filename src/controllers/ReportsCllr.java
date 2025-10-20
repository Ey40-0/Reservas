/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Register;

/**
 * FXML Controller class
 *
 * @author Ariel
 */
public class ReportsCllr {
    
    @FXML private TableView<Register> tblRegs;
    @FXML private TableColumn<Register, String> colClass;
    @FXML private TableColumn<Register, String> colRun;
    @FXML private TableColumn<Register, String> colDate;
    @FXML private TableColumn<Register, String> colTime;
    @FXML private TableColumn<Register, String> colTimestamp;

    @FXML
    private void initialize() {
        
        colClass.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getClassName()));
        colRun.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getRun()));
        colDate.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDate()));
        colTime.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTime()));
        colTimestamp.setCellValueFactory(cell -> new SimpleStringProperty(
            cell.getValue().getRegisteredAt().toString()
        ));

        loadRegisters();
    }
    
    private void loadRegisters() {
        ObservableList<Register> regs = FXCollections.observableArrayList(Register.getItems());
        tblRegs.setItems(regs);
    }
    
    @FXML
    private void backBtn() {
        MainCllr.getInstance().showPanel("/views/MainRoutesVw.fxml");
    }
}
