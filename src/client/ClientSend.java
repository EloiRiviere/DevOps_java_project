package client;

import common.Message;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Eloi Rivière p1925581
 * @version 1.0
 * @since 2019-11-18
 */
public class ClientSend extends Thread
{
    private final Socket socket;
    private final ObjectOutputStream out;
    
    public ClientSend(Socket socket, ObjectOutputStream out)
    {
        this.socket = socket;
        this.out = out;
    }
    
    @Override
    public void run()
    {
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            System.out.print("Votre message >> ");
            String m = sc.nextLine();
            Message mess = new Message("client", m);
            try
            {
                out.writeObject(mess);
                out.flush();
            }
            catch (IOException ex)
            {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
