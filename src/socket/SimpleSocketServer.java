package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class SimpleSocketServer {
    private static final String OUTPUT_HEADERS = "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/plain\r\n" +
            "Content-Length: ";
    private static final String OUTPUT_END_OF_HEADERS = "\r\n\r\n";

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(5555);

        while (true) {
            System.out.println("wait tcp connection...");
            Socket socket = serverSocket.accept();

            try (
                    InputStream in = socket.getInputStream();
                    OutputStream out = socket.getOutputStream()
            ) {
                byte[] request = HttpUtil.readStream(in);
                System.out.println(new String(request, StandardCharsets.US_ASCII));

                byte[] OUTPUT = new Date().toString().getBytes(StandardCharsets.US_ASCII);
                String answer = OUTPUT_HEADERS + OUTPUT.length + OUTPUT_END_OF_HEADERS + new String(OUTPUT, StandardCharsets.US_ASCII);
                out.write(answer.getBytes(StandardCharsets.US_ASCII));
                out.flush();
            } finally {
                socket.close();
            }
        }
    }


}
