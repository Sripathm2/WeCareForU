package mishra.sripath.wecareforuclient;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class Addin1 extends AppCompatActivity {

    private static File f;
    private static File f1;
    private String info;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        final TextView t1=(TextView)findViewById(R.id.textView8);
        t1.setText("We will now take back image of the insurance card to complete the process.");
        Bundle p = getIntent().getExtras();
        f1 =new File(p.getString("file"));
        Button photoButton = (Button) this.findViewById(R.id.button4);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info="back";
                f = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+info+".jpg");
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f)),2);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent activityChangeIntent = new Intent(Addin1.this,Addin2.class);
                activityChangeIntent.putExtra("file1",f1.getAbsolutePath());
                activityChangeIntent.putExtra("file2",f.getAbsolutePath());
                startActivity(activityChangeIntent);
            }
        });
    }
}
