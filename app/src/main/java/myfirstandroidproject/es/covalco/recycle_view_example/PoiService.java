package myfirstandroidproject.es.covalco.recycle_view_example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PoiService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poi_service);
    }

    public void startService(View view) { startService(new Intent(this, SmapleService.class ));}
    public void stopService(View view) { stopService(new Intent(this, SmapleService.class ));}
}
