package mishra.sripath.wecareforuclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class Vuser extends AppCompatActivity {
    static ServerMain s;
    static String input;
    static String send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vuser);
        s=new ServerMain();
        final Button button1 = (Button) findViewById(R.id.button14);
        button1.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                EditText Edit1= (EditText)findViewById(R.id.editText10);
                EditText Edit2= (EditText)findViewById(R.id.editText11);
                send ="Get-info-"+Edit1.getText()+"-"+Edit2.getText();
                String in=send();
                Edit1.setText(input);
                while(input==null)
                {

                }
                Intent activityChangeIntent = new Intent(Vuser.this,Vlist.class);
                activityChangeIntent.putExtra("list",input);
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
