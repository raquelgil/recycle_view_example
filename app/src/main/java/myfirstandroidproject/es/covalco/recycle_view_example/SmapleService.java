package myfirstandroidproject.es.covalco.recycle_view_example;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.widget.Toast;

public class SmapleService extends Service {
    private MediaPlayer player;
    @Override
    public IBinder onBind(Intent intent) {return  null;}
    @Override
    public void onCreate(){
        Toast.makeText(this, "Service was Created", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        player.setLooping(true);
        player.start();
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public void  onDestroy(){
        super.onDestroy();
        player.stop();
        Toast.makeText(this, "Service Stoped", Toast.LENGTH_LONG).show();
    }

    public SmapleService() {
    }


}
