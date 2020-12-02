package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class MultiThreadSocketServer {

    public static void main(String[] args) throws IOException {

        ExecutorService service = new ThreadPoolExecutor(
            4, 256,
            60, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(256)
        );

        ServerSocket serverSocket = new ServerSocket(7777);

        while (true){
            Socket socket = serverSocket.accept();
            service.submit(new HttpHandler(socket));
        }

    }
}
