package mishra.sripath.wecareforuclient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class Vpic extends AppCompatActivity {
    private static File f;
    private String input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        final TextView t1=(TextView)findViewById(R.id.textView8);
        t1.setText("We will now take your image to proceed.");
        Button photoButton = (Button) this.findViewById(R.id.button4);
        photoButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                f = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/temp.jpg");
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f)),2);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final ServerMain s=new ServerMain();
                new Thread() {
                    public void run() {
                        try {
                            input=s.Control("Imginfo");
                            FileSender.myFile=f;
                            FileSender.sendFile();
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            FileSender.sendFile();
                        } catch (Exception e) {
                        }
                    }
                }.start();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent activityChangeIntent = new Intent(Vpic.this,Vlist.class);
                activityChangeIntent.putExtra("list",input);
                startActivity(activityChangeIntent);
            }
        });
    }
}
