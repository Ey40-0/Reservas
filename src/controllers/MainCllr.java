/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainCllr implements Initializable {

    @FXML
    private Pane contentPane;
    @FXML
    private Pane navBar;
    
    private static MainCllr instance;
    
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        showPanel("/views/MainRoutesVw.fxml");
        
        navBar.setOnMousePressed((MouseEvent event) -> {
            Stage stage = (Stage) navBar.getScene().getWindow();
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        navBar.setOnMouseDragged((MouseEvent event) -> {
            Stage stage = (Stage) navBar.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

    }

  
    public static MainCllr getInstance() {
        return instance;
    }


    public void showPanel(String rutaFXML) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(rutaFXML));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(pane);
            AnchorPane.setTopAnchor(pane, 0.0);
            AnchorPane.setBottomAnchor(pane, 0.0);
            AnchorPane.setLeftAnchor(pane, 0.0);
            AnchorPane.setRightAnchor(pane, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public void close() {
        System.exit(0);
    }
    
    public void minimize() {
        Stage stage = (Stage) contentPane.getScene().getWindow();
        stage.setIconified(true);
    }

    public static void showAlert(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static boolean validateRun(String run) {
        run = run.replace(".", "").replace("-", "").replace(" ", "").toUpperCase();


        if (!run.matches("\\d{8}[0-9K]")) {
            return false;
        }

        String numero = run.substring(0, run.length() - 1);
        char dv = run.charAt(run.length() - 1);

        return dv == calculateDV(numero);
    }

    private static char calculateDV(String rut) {
        int suma = 0;
        int multiplicador = 2;

        for (int i = rut.length() - 1; i >= 0; i--) {
            suma += Character.getNumericValue(rut.charAt(i)) * multiplicador;
            multiplicador = (multiplicador == 7) ? 2 : multiplicador + 1;
        }

        int resto = 11 - (suma % 11);

        if (resto == 11) return '0';
        if (resto == 10) return 'K';
        return (char) (resto + '0');
    }

    public static String formatRun(String run) {
        if (run == null || run.isEmpty()) return "";

        run = run.replace(".", "").replace("-", "").replace(" ", "").toUpperCase();

        if (!validateRun(run)) {
            return "";
        }

        String numero = run.substring(0, run.length() - 1);
        char dv = run.charAt(run.length() - 1);

        return numero + "-" + dv;
    }

}
