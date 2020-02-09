/*
 * Classe de démarrage de l'interface graphique
 */
package application;

import client.Client;
import client.MainClient;
import common.Message;
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
    
    private ClientPanel clientPanel;

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        
        this.clientPanel = new ClientPanel();
        
        Client client = MainClient.customInit("localhost", 25555, this.clientPanel, "test");
        this.clientPanel.setClient(client);
        Group root = new Group();
        root.getChildren().add(this.clientPanel);
        Scene scene = new Scene(root, 1200, 800);
        scene.setFill(Color.BLACK);
        stage.setResizable(false);
        stage.setTitle("Application Réseau - Interface");
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());        
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void stop() throws IOException{
        this.clientPanel.messageSended(new Message(this.clientPanel.getClient().toString(), "deconnexionclient"));
        System.exit(0);
    }
}
