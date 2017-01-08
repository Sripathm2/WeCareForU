package mishra.sripath.wecareforuclient;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by sripa on 12/29/2016.
 */

public class ServerMain implements Runnable {
    public static ObjectOutputStream out=null;
    public static ObjectInputStream in=null;
    public static Socket socket = null;

    @Override
    public void run() {

        try {
            socket =  new Socket("10.0.0.173",13306);
            out=new ObjectOutputStream(socket.getOutputStream());
            in=new ObjectInputStream(socket.getInputStream());
            out.flush();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
    public String Control(String send)
    {
        try{
            out.writeObject(send);
            out.flush();
            String get= (String)in.readObject();
            return get;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

