package Sockets_JDBC.Client;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame jFrame = new JFrame();
            ChatClientGUI chatClientGUI = new ChatClientGUI(jFrame);
        });
    }
}
/**
 * The Player class represents a game player with both GUI-related and non-GUI-related logic.
 * When performing GUI-related operations such as painting or repainting, use SwingUtilities.invokeLater
 * to ensure thread safety and execute the operations on the Event Dispatch Thread (EDT).
 * Example structure:
 * public class Player extends JPanel implements Runnable {
 *     // Other fields and methods...
 *     @Override
 *     public void run() {
 *         // GUI-related operations should be invoked on the EDT
 *         SwingUtilities.invokeLater(() -> {
 *             // Perform GUI-related updates, painting, or repainting here
 *             repaint();
 *         });
 *         // Other non-GUI logic...
 *     }
 *     // Other methods...
 * }
 */
