package com.kurnniawan.aplikasichatuts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    public static String ADD_PREF = "addPref";
    ChatAdapter chatAdapter;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO 9
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sp = getSharedPreferences(ADD_PREF, MODE_PRIVATE);
        String spString = sp.getString("data","empty");

        //TODO 10
        try {
            JSONArray jsonArray = new JSONArray(spString);

            chatAdapter = new ChatAdapter(jsonArray);
            recyclerView.setAdapter(chatAdapter);
            chatAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //TODO 11
    public void btn(View view) {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
        Toast.makeText(this, "silahkan isi konten chat", Toast.LENGTH_SHORT).show();
    }

    //TODO 12
    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    //TODO 13
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
