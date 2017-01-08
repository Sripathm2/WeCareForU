package mishra.sripath.wecareforuclient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

public class Adddoc extends AppCompatActivity {
    TextView ti;
    EditText Edit1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddoc);
        final Button button = (Button) findViewById(R.id.button10);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Edit1= (EditText)findViewById(R.id.editText8);
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(intent,1);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData)
    {
        Uri uri =resultData.getData();
        ti=(TextView)findViewById(R.id.textView10);
        String path=resultData.getDataString();
        path=path.substring(path.indexOf(':')+1);
        final File f=new File(path);
        final ServerMain s=new ServerMain();
        new Thread() {
            public void run() {
                try {
                    String input=s.Control("File-Pdf-"+Edit1.getText()+"-"+f.getName());
                    FileSenderPdf fr=new FileSenderPdf();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    fr.sendFile(f);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    fr.sendFile(f);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ti.setText("done");
                } catch (Exception e) {
                }
            }
        }.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            Intent activityChangeIntent = new Intent(Adddoc.this,Options.class);
            startActivity(activityChangeIntent);
    }

}
