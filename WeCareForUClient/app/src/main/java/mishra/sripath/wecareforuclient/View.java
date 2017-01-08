package mishra.sripath.wecareforuclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        final Button button1 = (Button) findViewById(R.id.button13);
        button1.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                Intent activityChangeIntent = new Intent(View.this,Vuser.class);
                startActivity(activityChangeIntent);
            }
        });
        final Button button2 = (Button) findViewById(R.id.button12);
        button2.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                Intent activityChangeIntent = new Intent(View.this,Vpic.class);
                startActivity(activityChangeIntent);
            }
        });

    }
}
