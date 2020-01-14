/*
 * Classe de démarrage de l'interface graphique
 */
package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * @author Eloi Rivière p1925581
 * @version 1.0
 * @since 2019-12-01
 */
public class Demarrage extends Application {

    public static void main(String[] args) {
        launch(args);
        try
        {
            Process process = new ProcessBuilder("../client/MainClient.java","25555").start();
        }
        catch(IOException e)
        {
            System.err.println("Erreur lors du lancement du client.");
        }
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        ClientPanel clientPanel = new ClientPanel();
        Group root = new Group();
        root.getChildren().add(clientPanel);
        Scene scene = new Scene(root, 600, 500);
        scene.setFill(Color.BLACK);
        stage.setResizable(false);
        stage.setTitle("Application Réseau - Interface");
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
