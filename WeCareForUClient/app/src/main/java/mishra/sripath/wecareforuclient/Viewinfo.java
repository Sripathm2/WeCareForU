package mishra.sripath.wecareforuclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Viewinfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewinfo);
        final Button button1 = (Button) findViewById(R.id.button16);
        button1.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                Intent activityChangeIntent = new Intent(Viewinfo.this,Vuser.class);
                startActivity(activityChangeIntent);
            }
        });
        final Button button2 = (Button) findViewById(R.id.button15);
        button2.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View v) {
                Intent activityChangeIntent = new Intent(Viewinfo.this,Vpic.class);
                startActivity(activityChangeIntent);
            }
        });
    }
}
