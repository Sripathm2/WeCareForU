package mishra.sripath.wecareforuclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public TextView t1;
    public static ServerMain s;
    public static String input ;
    public static String send ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(TextView)findViewById(R.id.textView1);
        s= new ServerMain();
        Thread t= new Thread(s);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText Edit1= (EditText)findViewById(R.id.editText);
                EditText Edit2= (EditText)findViewById(R.id.editText2);
                send="login-"+Edit1.getText()+"-"+Edit2.getText();
                String in=send();
                t1.setText(in);
                if(in.contains("Cor")) {
                    Intent activityChangeIntent = new Intent(MainActivity.this,Options.class);
                    startActivity(activityChangeIntent);
                }
            }
        });
        final Button button1 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(MainActivity.this,Register.class);
                startActivity(activityChangeIntent);
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
