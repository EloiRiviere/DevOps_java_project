package client;
import application.ClientPanel;
import java.io.IOException;

/**
 * starts a client. Reads the address and port from the command line argument
 * @authorRemi Watrigant
 **/

public class MainClient {
    /**
     * construct a new client
     * @param args
     * @throws java.io.IOException
     * @paramargs
     */
    
    /*public static void main(String[] args) throws IOException {
        if (args.length != 2)
        {
            printUsage();
        } 
        else
        {
            String address = args[0];
            Integer port = new Integer(args[1]);
            Client c = new Client(address, port);
        }
    }*/
    
    public static Client customInit(String add, int p, ClientPanel cp, String name) throws IOException{
        String address = add;
        int port = p;
        Client c = new Client(address, port, cp, name);
        return c;
    }
    
    private static void printUsage() {
        System.out.println("java client.Client <address> <port>");
        System.out.println("\t<address>: server's ip address");
        System.out.println("\t<port>: server's port");
    }
}