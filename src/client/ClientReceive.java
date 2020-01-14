package client;

import common.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Eloi Rivière p1925581
 * @version 1.0
 * @since 2019-11-18
 */
public class ClientReceive extends Thread
{
    private final Client client;
    private final Socket socket;
    private ObjectInputStream in;
    
    public ClientReceive(Client client, Socket socket)
    {
        this.client = client;
        this.socket = socket;
    }
    
    @Override
    public void run()
    {
        try
        {
            in = new ObjectInputStream(socket.getInputStream());
        }
        catch (IOException ex)
        {
            System.out.println("Erreur de récupération d'InputStream lors de l'ouverture du client.");
        }
        
        boolean isActive = true;
        Message mess;
        while(isActive)
        {
            try
            {
                mess = (Message) in.readObject();
                if (mess != null)
                {
                    System.out.println("\nMessage reçu  >> " + mess);
                    //this.client.messageReceived(mess);
                    System.out.print("Votre message >> ");
                }
                else
                {
                    isActive = false;
                }
            }
            catch (IOException | ClassNotFoundException ex)
            {
                Logger.getLogger(ClientReceive.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        client.disconnectedServer();
    }
}
