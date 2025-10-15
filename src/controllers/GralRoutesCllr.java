/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ariel
 */
public class GralRoutesCllr {
    
    private static GralRoutesCllr instance;
    
    public static GralRoutesCllr getInstance() {
        return instance;
    }
    
    @FXML
    private AnchorPane contentPane;
    
    @FXML
    private void initialize() {
        FamInput();
    }
    
    @FXML
    private void FamInput() {
        loadPanel("/views/RegFamProductsVw.fxml");
    }
    
        
    @FXML
    private void ProdInput() {
        loadPanel("/views/RegProductsVw.fxml");
    }
    
    @FXML
    private void CliInput() {
        loadPanel("/views/RegClientVw.fxml");
    }
    
    @FXML
    private void ClassInput() {
        loadPanel("/views/RegClassesVw.fxml");
    }
    
    @FXML
    private void EventInput() {
        loadPanel("/views/RegEventsVw.fxml");
    }
    
    @FXML
    private void BackBtn() {
        MainCllr.getInstance().showPanel("/views/MainRoutesVw.fxml");
    }
    
    public void loadPanel(String rutaFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            AnchorPane pane = loader.load();
            contentPane.getChildren().clear();
            contentPane.getChildren().add(pane);

            AnchorPane.setTopAnchor(pane, 0.0);
            AnchorPane.setBottomAnchor(pane, 0.0);
            AnchorPane.setLeftAnchor(pane, 0.0);
            AnchorPane.setRightAnchor(pane, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
            MainCllr.mostrarAlerta("Error", "No se pudo cargar la pantalla: " + rutaFXML);
        }
    }
    
}
