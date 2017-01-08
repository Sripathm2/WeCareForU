import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;

/**
 * Created by sripa on 12/29/2016.
 */
public class Server implements Runnable {
    public Socket socket;
    public ObjectOutputStream out=null;
    public ObjectInputStream in=null;

    public Server(Socket socket)
    {
        this.socket=socket;
    }

    public static void main(String args[])
    {
        try{
            ServerSocket s=new ServerSocket(13306)  ;
            while(true)
            {
                Socket sock=s.accept();
                Server server=new Server(sock);
                Thread t=new Thread(server);
                t.start();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void run() {
        try {
            this.out = new ObjectOutputStream(this.socket.getOutputStream());
            this.in= new ObjectInputStream(this.socket.getInputStream());
            this.out.flush();
            this.control();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void control()
    {
        try {
            System.out.println("connected");

            while(true)
            {
                String input = (String) this.in.readObject();
                System.out.println("got this:"+input);
                String output=input(input);
                System.out.println("sending "+output);
                out.writeObject(output);
                out.flush();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public String input(String input) {
        if (input.contains("login")) {
            String str[] = input.split("-");
            String info = getinfo(str[1]);
            if (info.length() > 3) {
                String str1[] = info.split("-");
                if (str[2].equals(str1[1]))
                    return "Correct";
                else
                    return "Please check the details";
            } else
                return "Please check the details";
        }
        if (input.contains("Reg")) {
            String str[] = input.split("-");
            String info = getinfo(str[1]);
            if (info.length() > 3)
                return "Username is taken please choose another";
            else {
                File f = new File("Users/" + str[1] + "/");
                f.mkdir();
                writeinfo(input.substring(input.indexOf('-') + 1));
                return "Done";
            }
        }
        if (input.contains("File-Img")) {
            String str[] = input.split("-");
            File f = new File("Users/" + str[2] + "/" + str[3]);
            try {
                f.createNewFile();
                FileReciever.FILE_TO_RECEIVED = f.getAbsolutePath();
                FileReciever.getfile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (input.contains("File-Pdf")) {
            String str[] = input.split("-");
            File f = new File("Users/" + str[2] + "/" + str[3]);
            try {
                f.createNewFile();
                FileRecieverPdf fr = new FileRecieverPdf();
                fr.receiveFile(f.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(input.contains("Get-File"))
        {
            String str[]=input.split("-");
            File f=new File("Users/" + str[2] + "/" + str[3]);
            try{
                FileRecieverPdf fr = new FileRecieverPdf();
                fr.sendFile(f);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        if(input.contains("Get-info"))
        {
            String str[]=input.split("-");
            File f=new File("Users/" + str[2]+"/");
            File f1[]=f.listFiles();
            String out=str[2]+"-";
            for(int i=0;i<f1.length;i++)
                out+=f1[i].getName()+"-";
            out=out.substring(0,out.lastIndexOf('-'));
            return out;
        }
        if(input.contains("Img"))
        {
            final File f1=new File("temp.jpg");
            try {
                f1.createNewFile();
                FileReciever.FILE_TO_RECEIVED = f1.getAbsolutePath();
                FileReciever.getfile();
            } catch (Exception e) {
                e.printStackTrace();
            }
            File f[]=new File("Users/").listFiles();
            String tempfaceid=GetFaceId(f1);
            int i;
            for(i=0;i<f.length;i++)
            {
                File f2=new File(f[i].getAbsolutePath()+"/"+f[i].getAbsolutePath().substring(f[i].getAbsolutePath().lastIndexOf('/')+1)+".jpg");
                String facdeId=GetFaceId(f2);
                Boolean same=Verify(tempfaceid,facdeId);
                if(same==true)
                    return input(f[i].getAbsolutePath().substring(f[i].getAbsolutePath().lastIndexOf('/')+1));
            }
        }
        return "nope";
    }
    public String getinfo(String s)
    {
        String out="";
        File f=new File("Login.txt");
        try {
            FileReader isr = new FileReader(f);
            BufferedReader in = new BufferedReader(isr);
            String line;
            while((line=in.readLine())!=null)
            {
                String inpu=line;
                inpu=inpu.substring(0,inpu.indexOf('-'));
                if(inpu.equalsIgnoreCase(s))
                    out=line;
            }
            in.close();
            isr.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return out;
    }
    public void writeinfo(String n)
    {
        File f=new File("Login.txt");
        try {
            FileOutputStream out2 = new FileOutputStream(f, true);
            PrintWriter p = new PrintWriter(out2);
            p.println(n);
            p.close();
            out2.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static String GetFaceId(File f)
    {
        String input="";
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://api.projectoxford.ai/face/v1.0/detect");

            builder.setParameter("returnFaceId", "true");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", "2a586164d38e4698a10e796822aac3f0");


            FileReader fr=new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer json = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                json.append(line);
            }
            StringEntity reqEntity = new StringEntity(json.toString());
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                String getdsata[]=EntityUtils.toString(entity).split("\"faceId\": \"");
                input="\""+getdsata[1].substring(0,getdsata[1].indexOf('"')+1);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return input;
    }
    public static boolean Verify(String s1,String s2)
    {
        boolean input=false;
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://api.projectoxford.ai/face/v1.0/verify");


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", "2a586164d38e4698a10e796822aac3f0");


            String send="\"faceId1\":\""+s1+",\"faceId1\":\""+s2;
            StringEntity reqEntity = new StringEntity(send);
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                String output=EntityUtils.toString(entity);
                if(output.contains("true"))
                    input=true;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return input;
    }
}
