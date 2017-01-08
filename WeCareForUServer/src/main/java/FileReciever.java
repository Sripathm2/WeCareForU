import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileReciever {
    public static String FILE_TO_RECEIVED = null;
    public static void getfile() throws Exception {
        ServerSocket socket = new ServerSocket(13311);
        while (true) {
            Socket clientSocket = socket.accept();
            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            FileOutputStream fout = new FileOutputStream(FILE_TO_RECEIVED);
            int i;
            while ( (i = dis.read()) > -1) {
                fout.write(i);
            }

            fout.flush();
            fout.close();
            dis.close();
            clientSocket.close();
        }
    }
}