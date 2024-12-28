

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Server3{
    private ExecutorService threadPool;
    public Server3(int pools)
    {
        this.threadPool= Executors.newFixedThreadPool(pools);
    }
    public void handleClient(Socket clientSocket){
        String path="readerOperation";
        try(PrintWriter toSocket=new PrintWriter(clientSocket.getOutputStream(),true)
        ) {
            List<String> lines= Files.readAllLines(Paths.get(path));
            toSocket.println(lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int Pools=100;
        int port=8010;
        Server3 server=new Server3(Pools);
        try {
            ServerSocket socket=new ServerSocket(port);
            socket.setSoTimeout(70000);
            while (true){
                Socket acceptClient=socket.accept();
                server.threadPool.execute(()->server.handleClient(acceptClient));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}