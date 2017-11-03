package com.kurnniawan.aplikasichatuts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 03/11/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    JSONArray jsonArray;
    //TODO 4
    public ChatAdapter(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }


    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myOwnView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat,parent,false);
        return new ChatViewHolder(myOwnView);
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ChatViewHolder holder, int position) {
        //TODO 8 : get data
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);

            holder.nm.setText(jsonObject.getString("Nama"));
            holder.cht.setText(jsonObject.getString("Chat"));
            holder.wkt.setText(jsonObject.getString("Waktu"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        //TODO 7
        return jsonArray.length();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        //TODO 6
        TextView nm,cht,wkt;
        ImageView img;
        public ChatViewHolder(View itemView) {
            super(itemView);
            nm = itemView.findViewById(R.id.name);
            cht = itemView.findViewById(R.id.komen);
            wkt = itemView.findViewById(R.id.wkt);
            img = itemView.findViewById(R.id.img);
        }
    }
}
