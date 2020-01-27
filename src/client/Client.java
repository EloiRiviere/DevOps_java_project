
package client;

import application.ClientPanel;
import application.Demarrage;
import common.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Eloi Rivière p1925581
 * @version 1.0
 * @since 2019-11-18
 */
public class Client
{
    private final String address;
    private final int port;
    private final Socket socket;
    private ObjectInputStream in;
    private final ObjectOutputStream out;
    private Thread clientSend;
    
    public static void main(String[] args)
    {
        new application.Demarrage();
    }
    
    public Client(String address, int port, ClientPanel cp) throws IOException
    {
        this.address = address;
        this.port = port;
        this.socket = new Socket(address, port);
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.clientSend = new ClientSend(socket,out);
        clientSend.start();
        Thread clientReceive = new ClientReceive(this, socket, cp);
        clientReceive.start();
        
    }

    public void send(Message mess) throws IOException{
        this.out.writeObject(mess);
        this.out.flush();
    }

    void disconnectedServer() {
        System.out.println("Un client s'est déconnecté.");
    }
}
