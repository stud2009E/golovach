package socket;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class HttpUtil {

    /**
     * read header of request
     * @param {InputStream} in socket input stream
     * @return {byte[]} header
     * @throws IOException
     */
    public static byte[] readStream(InputStream in) throws IOException {
        byte[] buffer = new byte[1024];
        int headerLen = 0;
        while (true) {
            int cnt = in.read(buffer);

            if (cnt < 0) {
                return new byte[]{};
            } else {
                headerLen += cnt;
                if (isRequestEnd(headerLen, buffer)) {
                    return Arrays.copyOfRange(buffer, 0, headerLen);
                }

                if (headerLen > buffer.length) {
                    throw new RuntimeException("Too big HTTP header");
                }
            }
        }
    }

    /**
     * find http request end
     * @param {int} length edge of bytes
     * @param {byte[]} buffer array of butes
     * @return boolean
     */
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
