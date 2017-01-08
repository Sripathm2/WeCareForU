import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileRecieverPdf {
    Socket socket;
    InputStream is;
    FileOutputStream fos;
    BufferedOutputStream bos;
    int bufferSize;


    FileRecieverPdf() throws IOException {
        try (ServerSocket sock = new ServerSocket(13322)) {
            try {
                socket = sock.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        is = null;
        fos = null;
        bos = null;
        bufferSize = 0;

    }

    void receiveFile(String fileName) {
        try {
            is = socket.getInputStream();
            bufferSize = socket.getReceiveBufferSize();
            System.out.println("Buffer size: " + bufferSize);
            fos = new FileOutputStream(fileName);
            bos = new BufferedOutputStream(fos);
            byte[] bytes = new byte[bufferSize];
            int count;
            while ((count = is.read(bytes)) >= 0) {
                bos.write(bytes, 0, count);
            }
            bos.close();
            is.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendFile(File file) {

        FileInputStream fis;
        BufferedInputStream bis;
        BufferedOutputStream out;
        byte[] buffer = new byte[8192];
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            out = new BufferedOutputStream(socket.getOutputStream());
            int count;
            while ((count = bis.read(buffer)) > 0) {
                out.write(buffer, 0, count);

            }
            out.close();
            fis.close();
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}