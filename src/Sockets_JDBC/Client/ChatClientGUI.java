package Sockets_JDBC.Client;

import Sockets_JDBC.Server.ChatServer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.*;

/**
 * Builds the running GUI for the connecting client.
 *
 * @author https://github.com/overgara2
 */
public class ChatClientGUI {
    private static ChatClient chatClient;
    public static String userName = "Anon";

    public static JPanel mainWindow = new JPanel();

    private static JButton aboutButton = new JButton();
    private static JButton connectButton = new JButton();
    private static JButton disconnectButton = new JButton();
    private static JButton helpButton = new JButton();
    private static JButton sendButton = new JButton();

    private static JLabel messageLabel = new JLabel("Message: ");
    public static JTextField messageTF = new JTextField(20);

    private static JLabel conversationLabel = new JLabel();
    public static JTextArea conversationTA = new JTextArea();
    private static JScrollPane conversationSP = new JScrollPane();

    private static JLabel onlineLabel = new JLabel();
    public static JList onlineList = new JList();
    private static JScrollPane onlineSP = new JScrollPane();

    private static JLabel loggedInAsLabel = new JLabel();
    private static JLabel loggedInAsBox = new JLabel();

    /* GUI Globals Login Window */
    public static JFrame logInWindow = new JFrame();
    public static JTextField userNameTF = new JTextField(20);
    private static JButton enterButton = new JButton("Enter");
    private static JLabel enterUserLabel = new JLabel("Enter username: ");
    private static JPanel logInPanel = new JPanel();
    JFrame jFrame;
    public ChatClientGUI (JFrame jFrame) {
        this.jFrame = jFrame;
        buildMainWindow();
        initialize();
    }

    /**
     * Method to build our main GUI.
     */
    public void buildMainWindow() {
        this.jFrame.setSize(1080, 720);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setResizable(false);

        configureMainWindow();
        mainWindowActions();
        jFrame.getContentPane().add(mainWindow);

        mainWindow.setSize(450, 500);
        mainWindow.setLocation(220, 180);

        mainWindow.setVisible(true);
        jFrame.setVisible(true);
    }

    /**
     * Method which assigns values such as color, size and alignment
     * to our buttons, labels, text fields, text areas, and scroll panes.
     */

    // IMPORTANT! CONSIDER USING LAYOUT MANAGER!
    // FlowLayout, BorderLayout, GridLayout, BoxLayout, CardLayout and GridBagLayout
    public static void configureMainWindow() {
        mainWindow.setBackground(Color.white);
        mainWindow.setSize(500, 320);
        mainWindow.setLayout(null);

        sendButton.setBackground(Color.orange);
        sendButton.setForeground(Color.white);
        sendButton.setText("Send");
        mainWindow.add(sendButton);
        sendButton.setBounds(250, 40, 81, 25);

        disconnectButton.setBackground(Color.orange);
        disconnectButton.setForeground(Color.white);
        disconnectButton.setText("Disconnect");
        mainWindow.add(disconnectButton);
        disconnectButton.setBounds(10, 40, 110, 25);

        connectButton.setBackground(Color.orange);
        connectButton.setForeground(Color.white);
        connectButton.setText("Connect");
        mainWindow.add(connectButton);
        connectButton.setBounds(130, 40, 110, 25);

        helpButton.setBackground(Color.orange);
        helpButton.setForeground(Color.white);
        helpButton.setText("Help");
        mainWindow.add(helpButton);
        helpButton.setBounds(420, 40, 70, 25);

        aboutButton.setBackground(Color.orange);
        aboutButton.setForeground(Color.white);
        aboutButton.setText("Help");
        mainWindow.add(aboutButton);
        aboutButton.setBounds(340, 40, 75, 25);

        messageLabel.setText("Message:");
        mainWindow.add(messageLabel);
        messageLabel.setBounds(10, 10, 60, 20);

        messageTF.setForeground(Color.orange);
        messageTF.requestFocus();
        mainWindow.add(messageTF);
        messageTF.setBounds(70, 4, 260, 30);

        conversationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        conversationLabel.setText("Conversation");
        mainWindow.add(conversationLabel);
        conversationLabel.setBounds(100, 70, 140, 16);

        conversationTA.setColumns(20);
        conversationTA.setFont(new java.awt.Font("Tahoma", 0, 12));
        conversationTA.setForeground(Color.black);
        conversationTA.setLineWrap(true);
        conversationTA.setRows(5);
        conversationTA.setEditable(false);

        conversationSP.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        conversationSP.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        conversationSP.setViewportView(conversationTA);
        mainWindow.add(conversationSP);
        conversationSP.setBounds(10, 90, 330, 180);

        onlineLabel.setHorizontalAlignment(SwingConstants.CENTER);
        onlineLabel.setText("Currently Online");
        onlineLabel.setToolTipText("");
        mainWindow.add(onlineLabel);
        onlineLabel.setBounds(350, 70, 130, 16);

        onlineList.setForeground(Color.black);

        onlineSP.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        onlineSP.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        onlineSP.setViewportView(onlineList);
        mainWindow.add(onlineSP);
        onlineSP.setBounds(350, 90, 130, 180);

        loggedInAsLabel.setFont(new java.awt.Font("Tahoma", 0, 12));
        loggedInAsLabel.setText("Currently Logged in As:");
        mainWindow.add(loggedInAsLabel);
        loggedInAsLabel.setBounds(348, 0, 140, 15);


        loggedInAsBox.setHorizontalAlignment(SwingConstants.CENTER);
        loggedInAsBox.setFont(new java.awt.Font("Tahoma", 0, 12));
        loggedInAsBox.setForeground(Color.red);
        loggedInAsBox.setBorder(
                BorderFactory.createLineBorder(Color.black));
        mainWindow.add(loggedInAsBox);
        loggedInAsBox.setBounds(340, 17, 150, 20);
    }

    /**
     * A method which disables and enables buttons on application launch.
     */
    public static void initialize() {
        sendButton.setEnabled(false);
        disconnectButton.setEnabled(false);
        connectButton.setEnabled(true);
    }

    /**
     * A method which attaches an action to the login button.
     */
    public static void loginAction() {
        enterButton.addActionListener(
                new java.awt.event.ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        enterAction();
                    }
                });
    }

    /**
     * A method which prepares the GUI upon user login. Sets user name, adds
     * user name to current users as well as enables buttons used for chatting.
     */
    public static void enterAction() {
        if (!userNameTF.getText().equals("")) {
            userName = userNameTF.getText().trim();
            loggedInAsBox.setText(userName);
            ChatServer.currentUsers.add(userName);
//                    mainWindow.setTitle(userName + "'s Chat Box");
            logInWindow.setVisible(false);
            sendButton.setEnabled(true);
            disconnectButton.setEnabled(true);
            connectButton.setEnabled(false);
            connect();
        }
        else {
            JOptionPane.showMessageDialog(null, "Please enter a name!");
        }
    }

    /**
     * A method which attaches all action events to their respective buttons.
     */
    public static void mainWindowActions() {
        sendButton.addActionListener(
                new java.awt.event.ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        sendAction();
                    }
                });

        disconnectButton.addActionListener(
                new java.awt.event.ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        disconnectAction();
                    }
                });

        connectButton.addActionListener(
                new java.awt.event.ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        connectAction();
                    }
                });

        helpButton.addActionListener(
                new java.awt.event.ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        helpAction();
                    }
                });

        aboutButton.addActionListener(
                new java.awt.event.ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        aboutAction();
                    }
                });
    }

    /**
     * An action event which sends a message from a user.
     */
    public static void sendAction() {
        if(!messageTF.getText().equals("")) {
            chatClient.send(messageTF.getText());
            messageTF.requestFocus();
        }
    }

    /**
     * An action event which disconnects the user.
     */
    public static void disconnectAction() {
        try {
            chatClient.disconnect();
        }
        catch (Exception e) {
            System.out.println(userName + "has disconnected.");
        }
    }

    /**
     * An action event which connects the user.
     */
    public static void connectAction() {
        logInWindow.setTitle("What's your name?");
        logInWindow.setSize(400, 100);
        logInWindow.setLocation(250, 200);
        logInWindow.setResizable(false);
        logInPanel = new JPanel();
        /**
         * THIS SHOULD HANDLE THE LOGIN AND REGISTER!
         * TO-DO: THIS SHOULD HAVE CONNECTION TO THE DATABASE
         * **/
        logInPanel.add(enterUserLabel);
        logInPanel.add(userNameTF);
        logInPanel.add(enterButton);
        logInWindow.add(logInPanel);

        loginAction();
        logInWindow.setVisible(true);
    }

    /**
     * An action event which helps the user.
     */
    public static void helpAction() {
        JOptionPane.showMessageDialog(null,
                "VaqChat v1.0");
    }

    /**
     * An action event which displays information about the application.
     */
    public static void aboutAction() {
        JOptionPane.showMessageDialog(null,
                "VaqChat v1.0");
    }

    /**
     * A method which attempts to connect the client socket to the server socket
     * to begin communication.
     */
    public static void connect() {
        try {
            final int port = 1337;
            final String host = "localhost";
            Socket socket = new Socket(host, port);
            System.out.println("You connected to: " + host);

            chatClient = new ChatClient(socket);

            PrintWriter output = new PrintWriter(socket.getOutputStream());
            output.println(userName);
            output.flush();

            Thread t = new Thread(chatClient);
            t.start();

        } catch(Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Server not responding.");
            System.exit(0);
        }
    }
}
