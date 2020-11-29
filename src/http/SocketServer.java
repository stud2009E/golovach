package http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;

public class SocketServer {
    private static final String OUTPUT_HEADERS = "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/plain\r\n" +
            "Content-Length: ";
    private static final String OUTPUT_END_OF_HEADERS = "\r\n\r\n";

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(5555);

        while (true){
            System.out.println("wait tcp connection...");
            Socket socket = serverSocket.accept();

            try (
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream()
            ){
                byte[] request = readStream(in);
                System.out.println(new String(request, StandardCharsets.US_ASCII));

                byte[] OUTPUT = new Date().toString().getBytes(StandardCharsets.US_ASCII);
                String answer = OUTPUT_HEADERS + OUTPUT.length + OUTPUT_END_OF_HEADERS + new String(OUTPUT, StandardCharsets.US_ASCII);
                out.write(answer.getBytes(StandardCharsets.US_ASCII));
                out.flush();
            }finally {
                socket.close();
            }
        }
    }

    private static byte[] readStream(InputStream in) throws IOException {
        byte[] buffer = new byte[1024];
        int headerLen = 0;
        while (true){
            int cnt = in.read(buffer);

            if(cnt < 0){
                return new byte[]{};
            }else {
                headerLen += cnt;
                if(isRequestEnd(headerLen, buffer)){
                    return Arrays.copyOfRange(buffer, 0, headerLen);
                }

                if(headerLen > buffer.length){
                    throw new RuntimeException("Too big HTTP header");
                }
            }
        }

    }

    private static boolean isRequestEnd(int length, byte[] buffer) {
        if(length < 4){
            return false;
        }

        return buffer[length - 4] == '\r' &&
                buffer[length - 3] == '\n' &&
                buffer[length - 2] == '\r' &&
                buffer[length - 1] == '\n';
    }
}
