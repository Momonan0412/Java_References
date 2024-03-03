package Sockets_JDBC.Server;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Console prompt for the server which states all actions any connected client
 * has taken. This includes connections, disconnections, and messages sent.
 */
public class ChatServerReturn implements Runnable {

    Socket socket;
    private Scanner input;
    private PrintWriter output;
    String message = "";

    /**
     * Simple constructor which takes a socket and assigns the value to a local
     * variable socket.
     *
     * @param socket Socket taken from the server.
     */
    public ChatServerReturn(Socket socket) {
        this.socket = socket;
    }

    /**
     * A method which checks to see if connections are still connected, or have
     * disconnected. Prints this information to the console.
     *
     * @throws IOException
     */
    public void checkConnection() throws IOException {
        if (!socket.isConnected()) {
            for (int i = 0; i < ChatServer.connectionArray.size(); i++) {
                if (ChatServer.connectionArray.get(i) == socket) {
                    ChatServer.connectionArray.remove(i);
                }
            }

            for (int i = 0; i < ChatServer.connectionArray.size(); i++) {
                Socket tempSocket = ChatServer.connectionArray.get(i);
                PrintWriter tempOutput = new PrintWriter(tempSocket.getOutputStream());

                tempOutput.println(tempSocket.getLocalAddress().getHostName()
                        + " disconnected.");
                tempOutput.flush();

                System.out.println(tempSocket.getLocalAddress().getHostName()
                        + " disconnected!");
            }
        }
    }

    /**
     * Run method which creates an infinite loop to track connections as well as
     * any messages sent among clients.
     */
    @Override
    public void run() {
        try {
            try {
                input = new Scanner(socket.getInputStream());
                output = new PrintWriter(socket.getOutputStream());

                while (true) {
                    checkConnection();

                    if (!input.hasNext()) {
                        return;
                    }

                    message = input.nextLine();

                    System.out.println("Client said: " + message);

                    for (int i = 0; i < ChatServer.connectionArray.size(); i++) {
                        Socket tempSocket = ChatServer.connectionArray.get(i);
                        PrintWriter tempOutput = new PrintWriter(tempSocket.getOutputStream());

                        tempOutput.println(message);
                        tempOutput.flush();

                        System.out.println("sent to: "
                                + tempSocket.getLocalAddress().getHostName());
                    }
                }
            } finally {
                socket.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
