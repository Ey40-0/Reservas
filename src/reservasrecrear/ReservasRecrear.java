/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package reservasrecrear;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author PROG
 */
public class ReservasRecrear extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/MainVw.fxml"));
        Scene scene = new Scene(root);
        scene.getRoot().setStyle("-fx-background-radius: 10; -fx-background-color: #2b2b2b;");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setWidth(840);
        primaryStage.setHeight(520);
        primaryStage.show();
        primaryStage.getScene().setFill(Color.TRANSPARENT);
    }

    /**
     * Inicializa la app
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO application logic here
        launch(args);
    }
    
}
