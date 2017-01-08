package mishra.sripath.wecareforuclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        final Button button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(Options.this,finddoc.class);
                startActivity(activityChangeIntent);
            }
        });
        final Button button1 = (Button) findViewById(R.id.button6);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(Options.this,Adddoc.class);
                startActivity(activityChangeIntent);
            }
        });
        final Button button2 = (Button) findViewById(R.id.button8);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(Options.this,Addin.class);
                startActivity(activityChangeIntent);
            }
        });
        final Button button3 = (Button) findViewById(R.id.button7);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(Options.this,Viewinfo.class);
                startActivity(activityChangeIntent);
            }
        });
    }
}
