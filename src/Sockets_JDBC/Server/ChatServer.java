package Sockets_JDBC.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatServer {
    public static ArrayList<Socket> connectionArray = new ArrayList<>();
    public static ArrayList<String> currentUsers = new ArrayList<>();

    /**
     * Main method which must throw an IOException.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        try {
            final int port = 1337;
            ServerSocket server = new ServerSocket(port);

            System.out.println("Waiting for client...");

            while (true) {
                Socket socket = server.accept();
                connectionArray.add(socket);

                System.out.println("Client connected from: "
                        + socket.getLocalAddress().getHostName());

                addUserName(socket);

                ChatServerReturn chat = new ChatServerReturn(socket);
                Thread thread = new Thread(chat);
                thread.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Method which takes in a socket input, then creates and adds a user name
     * based off input.
     *
     * @param socket the socket of a client
     * @throws IOException
     */
    public static void addUserName(Socket socket) throws IOException {
        Scanner input = new Scanner(socket.getInputStream());
        String username = input.nextLine();
        currentUsers.add(username);

        for (int i = 0; i < connectionArray.size(); i++) {
            Socket tempSocket = connectionArray.get(i);
            PrintWriter output = new PrintWriter(tempSocket.getOutputStream());

            output.println("#?!" + currentUsers);
            output.flush();
        }
    }
}

