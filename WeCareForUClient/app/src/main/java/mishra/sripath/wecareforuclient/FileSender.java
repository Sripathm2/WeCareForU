package mishra.sripath.wecareforuclient;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

public class FileSender {
    public static File myFile=null;
    public static void sendFile() throws Exception {
        Socket sock = new Socket("10.0.0.173", 13311);
        int i;
        FileInputStream fis = new FileInputStream (myFile);
        DataOutputStream os = new DataOutputStream(sock.getOutputStream());
        while ((i = fis.read()) > -1)
            os.write(i);
        fis.close();
        os.close();
        sock.close();
    }
}
