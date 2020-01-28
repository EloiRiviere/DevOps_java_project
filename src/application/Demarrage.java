/*
 * Classe de démarrage de l'interface graphique
 */
package application;

import client.Client;
import client.MainClient;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
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

    public static void main(String[] args) throws IOException {
        launch(args);
        /*try
        {
            Process process = new ProcessBuilder("../client/MainClient.java","25555").start();
        }
        catch(IOException e)
        {
            System.err.println("Erreur lors du lancement du client.");
        }*/
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        
        ClientPanel clientPanel = new ClientPanel();
        
        Client client = MainClient.customInit("localhost", 25555, clientPanel, "test");
        clientPanel.setClient(client);
        Group root = new Group();
        root.getChildren().add(clientPanel);
        Scene scene = new Scene(root, 1200, 800);
        scene.setFill(Color.BLACK);
        stage.setResizable(false);
        stage.setTitle("Application Réseau - Interface");
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        
        // Méthodes interface
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        
        
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Code à la fermeture
     * @param evt 
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {                                   
        System.out.println("Fermeture de client.");
    } 
}
