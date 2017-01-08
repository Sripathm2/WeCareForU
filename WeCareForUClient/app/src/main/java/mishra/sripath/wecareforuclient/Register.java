package mishra.sripath.wecareforuclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    static ServerMain s;
    static String input;
    static String send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        s=new ServerMain();
        final TextView t1=(TextView)findViewById(R.id.textView7);
        final Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText Edit1= (EditText)findViewById(R.id.editText5);
                EditText Edit2= (EditText)findViewById(R.id.editText6);
                EditText Edit3= (EditText)findViewById(R.id.editText4);
                EditText Edit4= (EditText)findViewById(R.id.editText3);
                send="Reg-"+Edit1.getText()+"-"+Edit2.getText()+"-"+Edit3.getText()+"-"+Edit4.getText();
                String in=send();
                t1.setText(in);
                if(in.contains("one")) {
                    Intent activityChangeIntent = new Intent(Register.this,Picture.class);
                    activityChangeIntent.putExtra("name",""+Edit1.getText());
                    startActivity(activityChangeIntent);
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
