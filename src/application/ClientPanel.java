/*
 * Classe de l'interface
 */
package application;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * @author Eloi Rivière p1925581
 * @version 1.0
 * @since 2019-12-01
 */
public class ClientPanel extends Parent {
    TextArea textToSend;
    ScrollPane scrollReceivedText;
    TextFlow receivedText;
    Button sendBtn;
    Button clearBtn;
    Button disconnectBtn;
    TextArea connected;
    Text textMembers;
    
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
        scrollReceivedText.setPrefWidth(400);
        
        scrollReceivedText.setContent(receivedText);
        scrollReceivedText.vvalueProperty().bind(receivedText.heightProperty());
        
        // Label des clients connectés
        textMembers = new Text("Connectés:");
        textMembers.setLayoutX(470);
        textMembers.setLayoutY(0);
        
        // Zone des clients connectés
        connected = new TextArea();
        connected.setLayoutX(470);
        connected.setLayoutY(50);
        connected.setPrefHeight(280);
        connected.setPrefWidth(100);
        connected.setEditable(false);
        
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
            receivedText.getChildren().add(new Label(textToSend.getText()));
            textToSend.clear();
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
        
        this.getChildren().add(connected);
        this.getChildren().add(textMembers);
        this.getChildren().add(scrollReceivedText);
        this.getChildren().add(textToSend);        
        this.getChildren().add(sendBtn);
        this.getChildren().add(clearBtn);
        this.getChildren().add(disconnectBtn);
    }
    
}
