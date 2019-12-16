/*
 * Classe Connection
 * Cette classe est la classe de la connection
 */
package common;

import client.ConnectedClient;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import server.Server;

/**
 * @author Eloi Rivière p1925581
 * @version 1.0
 * @since 2019-11-18
 */
public class Connection implements Runnable {
    
    private final Server server;
    private final ServerSocket serverSocket;
    
    public Connection(Server server) throws IOException
    {
        this.server = server;
        this.serverSocket = new ServerSocket(server.getPort());
    }
    
    @Override
    public void run()
    {
        Socket sockNewClient;
        while(true)
        {
            try
            {
                // attente de connexion d'un nouveau client
                sockNewClient = serverSocket.accept();
                // récupération du client et ajout à la liste des clients connectés
                ConnectedClient newClient = new ConnectedClient(server, sockNewClient);
                server.addClient(newClient);
                // démarrage du thread du client
                Thread threadNewClient = new Thread(newClient);
                threadNewClient.start();
            }
            catch(IOException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
}
