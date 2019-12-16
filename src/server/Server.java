/*
 * Classe Server
 * Cette classe est la classe principale du serveur
 */
package server;

import client.ConnectedClient;
import common.Connection;
import common.Message;
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
    
    public Server(int port) throws IOException
    {
        this.port = port;
        this.clients = new ArrayList<>();
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
        for(ConnectedClient client : clients)
        {
            if(client.getId() != id)
            {
                client.sendMessage(mess);
            }
        }
    }
    
    public void disconnectedClient(ConnectedClient discClient) throws IOException
    {
        discClient.closeClient();
        clients.remove(discClient);
        String message = "Le client " + discClient.getId() + " s'est déconnecté.";
        System.out.println(message);
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
