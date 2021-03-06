
package client;

import application.ClientPanel;
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
    private final String name;
    private ObjectInputStream in;
    private final ObjectOutputStream out;
    private Thread clientSend;
    
    public Client(String address, int port, ClientPanel cp, String name) throws IOException
    {
        this.address = address;
        this.port = port;
        this.socket = new Socket(address, port);
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.clientSend = new ClientSend(socket,out);
        clientSend.start();
        Thread clientReceive = new ClientReceive(this, socket, cp);
        clientReceive.start();
        this.name = name;
    }

    public void send(Message mess) throws IOException{
        this.out.writeObject(mess);
        this.out.flush();
    }

    void disconnectedServer() {
        System.out.println("Un client s'est déconnecté.");
    }
}
