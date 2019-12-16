
package client;

import common.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Server;

/**
 * @author Eloi Rivière p1925581
 * @version 1.0
 * @since 2019-11-18
 */
public class ConnectedClient implements Runnable
{
    private static int idCounter;
    private int id;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    protected Server server;
    
    public ConnectedClient(Server server, Socket socket)
    {
        this.server = server;
        this.socket = socket;
        this.id = idCounter;
        idCounter ++;
        try
        {
            out = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (IOException ex)
        {
            Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Nouvelle connexion, id = " + id);
    }
    
    public void sendMessage(Message mess) throws IOException
    {
        this.out.writeObject(mess);
        this.out.flush();
    }
    
    @Override
    public void run()
    {
       
        boolean isActive = true;
        try
        {
            in = new ObjectInputStream(socket.getInputStream());
            while(isActive)
            {
                // attente de réception de message
                Message mess = (Message) in.readObject();
                if(Objects.isNull(mess) || mess.getContent().equals("Bye."))
                {
                    // le client s'est déconnecté si le message est null
                    server.disconnectedClient(this);
                    isActive = false;
                }
                else
                {
                    // on diffuse le message
                    mess.setId(String.valueOf(id));
                    server.broadcastMessage(mess, id);
                }
            }
        }
        catch(IOException | ClassNotFoundException ex)
        {
            Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int getId()
    {
        return id;
    }
    
    public void closeClient()
    {
        
    }
}
