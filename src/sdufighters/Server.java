import java.io.*;
import java.net.*;

public class Server {

    private static int co = 0;
    private static ClientThread[] clientsConnected = new ClientThread[2];

    public static void main(String[] args) throws IOException {
        int port = 4444;
        ServerSocket server = null;
        Socket clientSocket = null;
        // An array of clientsConnected instances
        try {
            server = new ServerSocket(port);
            System.out.println("listening on port: " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                clientSocket = server.accept();
                DataOutputStream oos = new DataOutputStream(clientSocket.getOutputStream());
                oos.writeUTF(co + "");
            } catch (IOException e) {
                e.printStackTrace();
                if (!server.isClosed()) {
                    server.close();
                }
                if (!clientSocket.isClosed()) {
                    clientSocket.close();
                }
            }

            System.out.println("Client connected!");

            for (int c = 0; c < clientsConnected.length; c++) {
                if (clientsConnected[c] == null) {
                    // if it is empty ( null) then start a new Thread, and pass the socket and the object of itself as parameter
                    (clientsConnected[c] = new ClientThread(clientSocket, clientsConnected)).start();
                    ++co;
                    System.out.println("num of players " + co);
                    break; // have to break, else it will start 20 threads when the first client connects :P
                }
            }
            if (co == 2) {
                System.out.println("start");
            }
        }
    }
}