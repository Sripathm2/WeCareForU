package mishra.sripath.wecareforuclient;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Addin2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addin2);
        Bundle p = getIntent().getExtras();
        final File f1 =new File(p.getString("file1"));
        final File f2 =new File(p.getString("file2"));
        final Button button = (Button) findViewById(R.id.button11);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final EditText Edit1= (EditText)findViewById(R.id.editText9);
                final File f3=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+Edit1.getText()+"insurance.pdf");
                try {
                    f3.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Document document = new Document();
                String input = f1.getAbsolutePath();
                String input1=f2.getAbsolutePath();
                String output = f3.getAbsolutePath();
                try {
                    FileOutputStream fos = new FileOutputStream(output);
                    PdfWriter writer = PdfWriter.getInstance(document, fos);
                    writer.open();
                    document.open();
                    document.add(Image.getInstance(input));
                    document.add(Image.getInstance(input1));
                    document.close();
                    writer.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                final File f=f3;
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
                        } catch (Exception e) {
                        }
                    }
                }.start();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent activityChangeIntent = new Intent(Addin2.this,Options.class);
                startActivity(activityChangeIntent);
            }
        });
    }
}
