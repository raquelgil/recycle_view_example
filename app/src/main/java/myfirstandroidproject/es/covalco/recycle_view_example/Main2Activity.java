package myfirstandroidproject.es.covalco.recycle_view_example;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView recyclerViewUser;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.w("hola mundo", "Entramos en OnCreate");
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        //editor.putBoolean("key_name", true); // Storing boolean - true/false
        editor.putString("key_name", "string value"); // Storing string
        //editor.putInt("key_name", 1); // Storing integer
        //editor.putFloat("key_name", 2.4F); // Storing float
        //editor.putLong("key_name", 3L); // Storing long

        editor.commit(); // commit changes
        String sh="";

        try {
            sh = pref.getString("key_name", null); // getting String
            //pref.getInt("key_name", -1); // getting Integer
            //pref.getFloat("key_name", null); // getting Float
            //pref.getLong("key_name", null); // getting Long
            //pref.getBoolean("key_name", null); // getting boolean
        }catch (Exception ex){
            Log.e("Covalco-MainActivity", ex.getMessage() + " " + ex.getStackTrace());
        }
        Toast toast = Toast.makeText(getApplicationContext(),
                BuildConfig.FLAVOR  + " " + sh + " " +
                BuildConfig.ENDPOINT,Toast.LENGTH_LONG);
        toast.setMargin(50,50);
        toast.show();
        recyclerViewUser = (RecyclerView) findViewById(R.id.recyclerViewUser);

        recyclerViewUser.setHasFixedSize(true);
        recyclerViewUser.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new UserAdapter(getData());
        recyclerViewUser.setAdapter(mAdapter);
    }

    public List<UserModel> getData(){
        List<UserModel> userModels = new ArrayList<>();
        userModels.add(new UserModel("Gustavo"));
        userModels.add(new UserModel("Daniel"));
        userModels.add(new UserModel("Cecilia"));
        userModels.add(new UserModel("Diego"));
        userModels.add(new UserModel("Carlos"));
        userModels.add(new UserModel("Juan"));

        for(int i = 1; i < 15; i++) {
            userModels.add(new UserModel("Name " + i));
        }
        return userModels;
    }
}


