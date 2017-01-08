package mishra.sripath.wecareforuclient;

import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class finddoc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finddoc);
        final Button button = (Button) findViewById(R.id.button9);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText Edit1= (EditText)findViewById(R.id.editText7);
                String url="https://www.google.com/search?q="+Edit1.getText()+"+near+me&oq="+Edit1.getText()+"+near+me+&aqs=chrome..69i57.9829j0j7&sourceid=chrome&ie=UTF-8";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(finddoc.this, Uri.parse(url));
            }
        });
    }
}
