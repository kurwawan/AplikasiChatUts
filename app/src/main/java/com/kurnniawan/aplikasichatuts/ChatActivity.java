package com.kurnniawan.aplikasichatuts;

import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChatActivity extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor spEdit;
    EditText isinama, isichat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sp = getSharedPreferences(MainActivity.ADD_PREF, MODE_PRIVATE);
        spEdit = sp.edit();

        isinama = (EditText) findViewById(R.id.namaPengguna);
        isichat = (EditText) findViewById(R.id.isiChat);



    }

    public void btnSend(View view) {

        String IsiNama = isinama.getText().toString();
        String IsiChat = isichat.getText().toString();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
        String tgl = sdf.format(calendar.getTime());

        JSONObject jsonObject = new JSONObject();

        //TODO 1
        if (IsiNama.isEmpty() || IsiChat.isEmpty()) {
            Toast.makeText(this, "Isi Konten Terlebih Dahulu", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Chat Konten Terkirim", Toast.LENGTH_SHORT).show();
            //TODO 2
            try {
                //No Image
                jsonObject.put("Nama",IsiNama);
                jsonObject.put("Chat",IsiChat);
                jsonObject.put("Waktu",tgl);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //TODO 3
            if (sp.contains("data")) {
                String spString = sp.getString("data","empty");
                try {
                    //jsonObject to jsonArray
                    JSONArray jsonArray = new JSONArray(spString);
                    jsonArray.put(jsonObject);
                    spEdit.putString("data", jsonArray.toString());
                    spEdit.apply();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(jsonObject);
                spEdit.putString("data",jsonArray.toString());
                spEdit.apply();
            }
            finish();
        }
    }
}
