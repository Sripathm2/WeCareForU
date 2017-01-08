package mishra.sripath.wecareforuclient;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Vlist extends AppCompatActivity {
    private static String send;
    private static String input;
    static ServerMain s;
    static File f;
    final ArrayList<String> List = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlist);
        Bundle p = getIntent().getExtras();
        final String list[]=p.getString("list").split("-");
        s=new ServerMain();
        f=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/temp.pdf");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=1;i<list.length;i++)
            List.add(list[i]);
        final ListView mainListView = (ListView) findViewById(R.id.mainListView);
        final ArrayAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow,List);
        mainListView.setAdapter(listAdapter);
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            send="Get-File-"+list[0]+"-"+List.get(position);
                new Thread() {
                    public void run() {
                        try {
                            String in=send();
                            FileSenderPdf fr=new FileSenderPdf();
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            fr.receiveFile(f.getAbsolutePath());
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            fr.receiveFile(f.getAbsolutePath());
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } catch (Exception e) {
                        }
                    }
                }.start();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent target = new Intent(Intent.ACTION_VIEW);
                target.setDataAndType(Uri.fromFile(f),"application/pdf");
                target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                Intent intent = Intent.createChooser(target, "Open File");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public static String send()
    {
        new Thread() {
            public void run() {
                try {
                    input=s.Control(send);
                } catch (Exception e) {
                }
            }
        }.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return input;
    }
}
