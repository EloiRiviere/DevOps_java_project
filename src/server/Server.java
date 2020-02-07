/*
 * Classe Server
 * Cette classe est la classe principale du serveur
 */
package server;

import client.ConnectedClient;
import common.Connection;
import common.Message;
import engine.Triplon;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eloi Rivière p1925581
 * @version 1.0
 * @since 2019-11-18
 */
public class Server {
    private final int port;
    private final List<ConnectedClient> clients;
    private int pj1;
    private int pj2;
    private String lastPlayer;
    private int specialMoveValue;
    private String specialMoveName;
    
    public Server(int port) throws IOException
    {
        this.port = port;
        this.clients = new ArrayList<>();
        this.pj1 = 0;
        this.pj2 = 0;
        this.lastPlayer = "";
        this.specialMoveValue = 0;
        this.specialMoveName = "";
        Thread threadConnection = new Thread(new Connection(this));
        threadConnection.start();
    }
    
    public void addClient(ConnectedClient newClient) throws IOException
    {
        for(ConnectedClient client : clients)
        {
            client.sendMessage(new Message("server","Le client " + newClient.getId() + " vient de se connecter."));
        }
        this.clients.add(newClient);
    }
    
    public void broadcastMessage(Message mess, int id) throws IOException
    {
        if("Pas mou le caillou !".equals(mess.getContent())){
            Message mess1 = new Message(mess.getSender(), "Pas mou le caillou !");
            for(ConnectedClient client : clients)
            {
                if(client.getId() != id)
                {
                    client.sendMessage(mess1);
                }
            }
            if("CV".equals(this.specialMoveName)){
                if("0".equals(mess.getSender())){
                    this.pj1 += this.specialMoveValue;
                }else{
                    this.pj2 += this.specialMoveValue;
                }
                this.specialMoveName="";
                this.specialMoveValue=0;
                Message mess2 = new Message("Score", this.pj1 + " - " + this.pj2);
                for(ConnectedClient client : clients)
                {
                    client.sendMessage(mess2);
                    if(this.pj1>=343 || this.pj2>=343){
                        client.sendMessage(new Message("Serveur", "Partie terminée !!!"));
                    }
                }
            } else {
                if("0".equals(mess.getSender())){
                    this.pj1 -= 5;
                }else{
                    this.pj2 -= 5;
                }
                Message mess3 = new Message(mess.getSender(), "Bévue ! Tu perds 5 points");
                Message mess2 = new Message("Score", this.pj1 + " - " + this.pj2);
                for(ConnectedClient client : clients)
                {
                    client.sendMessage(mess3);
                    client.sendMessage(mess2);
                }
            }
        } else if("Grelotte ça picotte !".equals(mess.getContent())){
            Message mess1 = new Message(mess.getSender(), "Grelotte ça picotte !");
            for(ConnectedClient client : clients)
            {
                if(client.getId() != id)
                {
                    client.sendMessage(mess1);
                }
            }
            if("S".equals(this.specialMoveName)){
                if("0".equals(mess.getSender())){
                    this.pj1 += this.specialMoveValue;
                }else{
                    this.pj2 += this.specialMoveValue;
                }
                this.specialMoveName="";
                this.specialMoveValue=0;
                Message mess2 = new Message("Score", this.pj1 + " - " + this.pj2);
                for(ConnectedClient client : clients)
                {
                    client.sendMessage(mess2);
                    if(this.pj1>=343 || this.pj2>=343){
                        client.sendMessage(new Message("Serveur", "Partie terminée !!!"));
                    }
                }
            } else {
                if("0".equals(mess.getSender())){
                    this.pj1 -= 5;
                }else{
                    this.pj2 -= 5;
                }
                Message mess3 = new Message(mess.getSender(), "Bévue ! Tu perds 5 points");
                Message mess2 = new Message("Score", this.pj1 + " - " + this.pj2);
                for(ConnectedClient client : clients)
                {
                    client.sendMessage(mess3);
                    client.sendMessage(mess2);
                }
            }
        } else if("lancerdedes".equals(mess.getContent())){
            if(this.pj1<343 && this.pj2<343 && "".equals(this.specialMoveName)){
                if(!this.lastPlayer.equals(mess.getSender())){
                    this.lastPlayer=mess.getSender();
                    Triplon t = new Triplon();
                    t.process();
                    Message mess1 = new Message(mess.getSender(), t.toString());
                    for(ConnectedClient client : clients)
                    {
                        client.sendMessage(mess1);
                    }
                    if("Chouette velute".equals(t.getType())){
                        this.specialMoveName="CV";
                        this.specialMoveValue=t.getPoints();
                    } else if("Suite".equals(t.getType())){
                        this.specialMoveName="S";
                        this.specialMoveValue=t.getPoints();
                    } else{
                        if("0".equals(mess.getSender())){
                            this.pj1 += t.getPoints();
                        }else{
                            this.pj2 += t.getPoints();
                        }

                        Message mess2 = new Message("Score", this.pj1 + " - " + this.pj2);

                        for(ConnectedClient client : clients)
                        {
                            client.sendMessage(mess2);
                            if(this.pj1>=343 || this.pj2>=343){
                                client.sendMessage(new Message("Serveur", "Partie terminée !!!"));
                            }
                        }
                    }
                }
            }
        } else {
            for(ConnectedClient client : clients)
            {
                if(client.getId() != id)
                {
                    client.sendMessage(mess);
                }
            }
        }
    }
    
    public void disconnectedClient(ConnectedClient discClient) throws IOException
    {
        discClient.closeClient();
        clients.remove(discClient);
        for(ConnectedClient client : clients)
        {
            client.sendMessage(new Message("server","Le client " + discClient.getId() + " s'est déconnecté."));
        }
    }
    
    public int getPort()
    {
        return port;
    }
}
