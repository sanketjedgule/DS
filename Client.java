import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 12345;

        try {
            // Create a socket and connect to the server
            Socket socket = new Socket(serverAddress, serverPort);
            System.out.println("Connected to server: " + socket.getInetAddress());

            // Create input/output streams for server communication
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputWriter = new PrintWriter(socket.getOutputStream(), true);

            try (// Read user input from the console
            Scanner scanner = new Scanner(System.in)) {
                String userInput;
                do {
                    System.out.print("Enter a message (or 'quit' to exit): ");
                    userInput = scanner.nextLine();

                    // Send user input to the server
                    outputWriter.println(userInput);

                    // Receive and display server response
                    String serverResponse = inputReader.readLine();
                    System.out.println("Server response: " + serverResponse);
                } while (!userInput.equalsIgnoreCase("quit"));
            }
            // Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
