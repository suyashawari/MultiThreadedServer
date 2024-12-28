import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

class Server {
    public Consumer<Socket> getConsumer(){
        return (acceptedClient)->{
            try {
                PrintWriter toSocket=new PrintWriter(acceptedClient.getOutputStream());
                toSocket.println("welcome from the server..!");
                toSocket.close();
                acceptedClient.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static void main(String[] args) {
        int port=8010;
        Server server1 =new Server();
        try {
            ServerSocket socket=new ServerSocket(port);
//            socket.setSoTimeout(10000);
            while (true){
                Socket accepetConnection=socket.accept();
                System.out.println("Client running on IP "+accepetConnection.getRemoteSocketAddress()+" : "+port);
                Thread thread=new Thread(()-> server1.getConsumer().accept(accepetConnection));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}