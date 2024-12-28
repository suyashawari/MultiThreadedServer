import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
    public static void run() throws IOException {
        int serverport = 8010;
        ServerSocket serverSocket = new ServerSocket(serverport);

        System.out.println("The server service is running on port " + serverport);
//serverSocket.setSoTimeout(10000);
        while (true) {
            try  {
                Socket acceptedConnection = serverSocket.accept();
                System.out.println("Connection accepted from client: " + acceptedConnection.getRemoteSocketAddress());

                // Set up input and output streams
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));

                // Read data from client
                String clientMessage = fromClient.readLine();
                System.out.println("Received from client: " + clientMessage);

                // Send response to client
                toClient.println("Hello from the server!");
                toClient.close();
                fromClient.close();
                acceptedConnection.close();
            } catch (IOException e) {
                System.out.println("Error occurred while handling client connection: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try {
            Server1.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
