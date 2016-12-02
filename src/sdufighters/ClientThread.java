import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {

    private ClientThread[] clientsConnected;
    private Socket socket = null;
    private DataOutputStream out = null;
    private String clientName = null;

    ClientThread(Socket socket, ClientThread[] clientsConnected) {
        this.socket = socket;
        this.clientsConnected = clientsConnected;
    }

    public void run() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            String message;
            clientName = in.readUTF();
            while (true) {
                message = in.readUTF();
                for (ClientThread clientConnected : clientsConnected) {
                    if (clientConnected != null && !clientConnected.clientName.equals(this.clientName)) { //do not send message to your self ;)
                        clientConnected.sendMessage(message); // loops through all the list and calls the objects sendMessage method.
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Client disconnected!");
            this.clientsConnected = null;
        }
    }

    // Every instance of this class ( the client ) will have this method.
    private void sendMessage(String mess) {
        try {
            out.writeUTF(mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}