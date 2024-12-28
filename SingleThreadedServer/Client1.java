import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client1 {
    public static void run() throws UnknownHostException, IOException {
        int port=8010;
        InetAddress ip=InetAddress.getByName("localhost");
        Socket socket=new Socket(ip,port);
        PrintWriter toSocket=new PrintWriter(socket.getOutputStream(),true);
        BufferedReader fromSocket=new BufferedReader(new InputStreamReader(socket.getInputStream()));
       toSocket.println("hello from client");
        String data=fromSocket.readLine();
        System.out.println("the responce from the server is  "+data);
        toSocket.close();
        fromSocket.close();
socket.close();
    }

    public static void main(String[] args) {
try{
Client.run();
}catch (Exception e){
    e.printStackTrace();
}
    }
}
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.InetAddress;
//import java.net.Socket;
//
//public class Client {
//    public static void run() throws Exception {
//        int port = 8010;
//        InetAddress ip = InetAddress.getByName("localhost");
//        try (Socket socket = new Socket(ip, port)) {
//            PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
//            BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            // Send message to server
//            toServer.println("Hello from client");
//
//            // Read response from server
//            String serverMessage = fromServer.readLine();
//            System.out.println("Response from server: " + serverMessage);
//        }
//    }
//
//    public static void main(String[] args) {
//        try {
//            Client.run();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
