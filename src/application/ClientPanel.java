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
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
    Button lancerBtn;
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
        scrollReceivedText.setPrefHeight(450);
        scrollReceivedText.setPrefWidth(1100);
        
        scrollReceivedText.setContent(receivedText);
        scrollReceivedText.vvalueProperty().bind(receivedText.heightProperty());
                
        // Bouton lancer de dés
        lancerBtn = new Button();
        lancerBtn.setLayoutX(500);
        lancerBtn.setLayoutY(525);
        lancerBtn.setPrefHeight(25);
        lancerBtn.setPrefWidth(200);
        lancerBtn.setText("Lancer les dés !");
        lancerBtn.setVisible(true);
        lancerBtn.setOnAction( e ->{
            try {
                playSound("assets/dice.wav");
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            Message mess = new Message(this.client.toString(), "lancerdedes");
            try {
                this.messageSended(mess);
                textToSend.clear();
            } catch (IOException ex) {
                Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        // Zone de saisie de texte
        textToSend = new TextArea();
        textToSend.setLayoutX(50);
        textToSend.setLayoutY(575);
        textToSend.setPrefHeight(130);
        textToSend.setPrefWidth(950);
        
        // Bouton d'envoi de message
        sendBtn = new Button();
        sendBtn.setLayoutX(1050);
        sendBtn.setLayoutY(575);
        sendBtn.setPrefHeight(25);
        sendBtn.setPrefWidth(100);
        sendBtn.setText("Envoyer");
        sendBtn.setVisible(true);
        
        sendBtn.setOnAction((ActionEvent event) -> {
            if(textToSend.getText().length()>0){
                receivedText.getChildren().add(new Label("Moi : " + textToSend.getText()));
                receivedText.getChildren().add(new Text(System.lineSeparator()));
                Message mess = new Message(this.client.toString(), textToSend.getText());
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
        clearBtn.setLayoutX(1050);
        clearBtn.setLayoutY(625);
        clearBtn.setPrefHeight(25);
        clearBtn.setPrefWidth(100);
        clearBtn.setText("Effacer");
        clearBtn.setVisible(true);
        
        clearBtn.setOnAction((ActionEvent event) -> {
            textToSend.clear();
        });
        
        // Bouton de déconnexion
        disconnectBtn = new Button();
        disconnectBtn.setLayoutX(1050);
        disconnectBtn.setLayoutY(675);
        disconnectBtn.setPrefHeight(25);
        disconnectBtn.setPrefWidth(100);
        disconnectBtn.setText("Quitter");
        disconnectBtn.setVisible(true);
        disconnectBtn.setOnAction((ActionEvent event) -> {
            Message mess = new Message(this.client.toString(), "deconnexionclient");
            try {
                this.messageSended(mess);
                System.exit(0);
            } catch (IOException ex) {
                Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });
        
        // Bouton grelotte ça picotte
        grelotteBtn = new Button();
        grelotteBtn.setLayoutX(350);
        grelotteBtn.setLayoutY(725);
        grelotteBtn.setPrefHeight(25);
        grelotteBtn.setPrefWidth(250);
        grelotteBtn.setText("Grelotte ça picotte !");
        grelotteBtn.setVisible(true);
        grelotteBtn.setOnAction( e ->{
            try {
                playSound("assets/grelotte.wav");
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            Message mess = new Message(this.client.toString(), "Grelotte ça picotte !");
            try {
                this.messageSended(mess);
                receivedText.getChildren().add(new Label("Moi : Grelotte ça picotte !"));
                receivedText.getChildren().add(new Text(System.lineSeparator()));
            } catch (IOException ex) {
                Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        // Bouton pas mou le caillou
        caillouBtn = new Button();
        caillouBtn.setLayoutX(625);
        caillouBtn.setLayoutY(725);
        caillouBtn.setPrefHeight(25);
        caillouBtn.setPrefWidth(250);
        caillouBtn.setText("Pas mou le caillou !");
        caillouBtn.setVisible(true);
        caillouBtn.setOnAction( e ->{
            Message mess = new Message(this.client.toString(), "Pas mou le caillou !");
            try {
                this.messageSended(mess);
                receivedText.getChildren().add(new Label("Moi : Pas mou le caillou !"));
                receivedText.getChildren().add(new Text(System.lineSeparator()));
            } catch (IOException ex) {
                Logger.getLogger(ClientPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.getChildren().add(scrollReceivedText);
        this.getChildren().add(textToSend);        
        this.getChildren().add(sendBtn);        
        this.getChildren().add(lancerBtn);
        this.getChildren().add(clearBtn);
        this.getChildren().add(disconnectBtn);
        this.getChildren().add(grelotteBtn);
        this.getChildren().add(caillouBtn);
    }
    
    
    public void messageReceived(Message mess) {
        
        Platform.runLater(() -> {
            receivedText.getChildren().add(new Label(mess.toString()));
            receivedText.getChildren().add(new Text(System.lineSeparator()));
        });
    }    
    
    public void messageSended(Message mess) throws IOException {
        this.client.send(mess);
    }
    
    public void playSound(String soundFile) throws MalformedURLException, UnsupportedAudioFileException, IOException, LineUnavailableException {
       File f = new File("./" + soundFile);
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());  
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }
    
}
