/*
 * Classe de l'interface
 */
package application;

import client.Client;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import common.Message;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
/**
 * @author Eloi Rivière p1925581
 * @version 1.0
 * @since 2019-12-01
 */
public class ClientPanel extends Parent {

    TextArea  textToSend;
    ScrollPane scrollReceivedText;
    TextFlow receivedText;
    Button sendBtn;
    Button clearBtn;
    Button disconnectBtn;
    Button grelotteBtn;
    Button caillouBtn;
    TextArea connected;
    Text textMembers;

    private Client client;
    
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    
    public ClientPanel()
    {
        // Zone des messages reçus
        receivedText = new TextFlow();
        scrollReceivedText = new ScrollPane();
        
        receivedText.setLayoutX(0);
        receivedText.setLayoutY(0);
        receivedText.setVisible(true);
        
        scrollReceivedText.setLayoutX(50);
        scrollReceivedText.setLayoutY(50);
        scrollReceivedText.setPrefHeight(280);
        scrollReceivedText.setPrefWidth(520);
        
        scrollReceivedText.setContent(receivedText);
        scrollReceivedText.vvalueProperty().bind(receivedText.heightProperty());
        
        // Zone de saisie de texte
        textToSend = new TextArea();
        textToSend.setLayoutX(50);
        textToSend.setLayoutY(350);
        textToSend.setPrefHeight(100);
        textToSend.setPrefWidth(400);
        
        // Bouton d'envoi de message
        sendBtn = new Button();
        sendBtn.setLayoutX(470);
        sendBtn.setLayoutY(350);
        sendBtn.setPrefHeight(25);
        sendBtn.setPrefWidth(100);
        sendBtn.setText("Envoyer");
        sendBtn.setVisible(true);
        
        sendBtn.setOnAction((ActionEvent event) -> {
            if(textToSend.getText().length()>0){
                receivedText.getChildren().add(new Label("Moi : " + textToSend.getText()));
                receivedText.getChildren().add(new Text(System.lineSeparator()));
                Message mess = new Message("test", textToSend.getText());
                try {
                    this.messageSended(mess);
                    textToSend.clear();
                } catch (IOException ex) {
                    Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // Bouton d'effacement de zone de saisie
        clearBtn = new Button();
        clearBtn.setLayoutX(470);
        clearBtn.setLayoutY(385);
        clearBtn.setPrefHeight(25);
        clearBtn.setPrefWidth(100);
        clearBtn.setText("Effacer");
        clearBtn.setVisible(true);
        
        clearBtn.setOnAction((ActionEvent event) -> {
            textToSend.clear();
        });
        
        // Bouton de déconnexion
        disconnectBtn = new Button();
        disconnectBtn.setLayoutX(470);
        disconnectBtn.setLayoutY(420);
        disconnectBtn.setPrefHeight(25);
        disconnectBtn.setPrefWidth(100);
        disconnectBtn.setText("Quitter");
        disconnectBtn.setVisible(true);
        disconnectBtn.setOnAction( e ->{
            // e.getSource().getScene().stage.close();
        });
        
        // Bouton grelotte ça picotte
        grelotteBtn = new Button();
        grelotteBtn.setLayoutX(50);
        grelotteBtn.setLayoutY(465);
        grelotteBtn.setPrefHeight(25);
        grelotteBtn.setPrefWidth(250);
        grelotteBtn.setText("Grelotte ça picotte !");
        grelotteBtn.setVisible(true);
        grelotteBtn.setOnAction( e ->{
            // e.getSource().getScene().stage.close();
        });
        
        // Bouton pas mou le caillou
        caillouBtn = new Button();
        caillouBtn.setLayoutX(320);
        caillouBtn.setLayoutY(465);
        caillouBtn.setPrefHeight(25);
        caillouBtn.setPrefWidth(250);
        caillouBtn.setText("Pas mou le caillou !");
        caillouBtn.setVisible(true);
        caillouBtn.setOnAction( e ->{
            // e.getSource().getScene().stage.close();
        });
        
        this.getChildren().add(scrollReceivedText);
        this.getChildren().add(textToSend);        
        this.getChildren().add(sendBtn);
        this.getChildren().add(clearBtn);
        this.getChildren().add(disconnectBtn);
        this.getChildren().add(grelotteBtn);
        this.getChildren().add(caillouBtn);
    }
    
    
    public void messageReceived(Message mess) {
        
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                System.out.println(mess.getSender());
                receivedText.getChildren().add(new Label(mess.toString()));
                receivedText.getChildren().add(new Text(System.lineSeparator()));
            }
        });
    }    
    
    public void messageSended(Message mess) throws IOException {
        this.client.send(mess);
    }
    
}
