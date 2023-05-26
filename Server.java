import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 12345;

        try {
            // Create a ServerSocket
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            // Listen for client connections
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            // Create input/output streams for client communication
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outputWriter = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read client messages and send responses
            String clientMessage;
            while ((clientMessage = inputReader.readLine()) != null) {
                System.out.println("Received from client: " + clientMessage);

                // Process client message (e.g., perform some operations)
                String serverResponse = "Server response: " + clientMessage.toUpperCase();

                // Send response back to the client
                outputWriter.println(serverResponse);
            }

            // Close client connection and server socket
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
