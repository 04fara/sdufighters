import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        Client m = new Client();
        m.connect();
    }

    private void connect() throws IOException {
        //declare a scanner so we can write a message
        // localhost ip
        String ip = "0.0.0.0";
        int port = 4444;
        Socket socket = null;
        String name;
        try {
            //connect
            socket = new Socket(ip, port);
            //initialize streams
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            if (in.readUTF().equals("0")) {
                name = "player1";
            } else {
                name = "player2";
            }
            System.out.println(name);
            //start a thread which will start listening for messages
            new ReceiveMessage(in).start();
            // send the name to the server!
            out.writeUTF(name);
        } catch (IOException e) {
            e.printStackTrace();
            if (!socket.isClosed()) socket.close();
        }
    }

    class ReceiveMessage extends Thread {
        DataInputStream in;

        ReceiveMessage(DataInputStream in) {
            this.in = in;
        }

        public void run() {
            String message;
            while (true)
                try {
                    message = in.readUTF();
                    System.out.println(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}