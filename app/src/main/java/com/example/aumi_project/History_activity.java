package com.example.aumi_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class History_activity extends AppCompatActivity {

    ArrayList<HistoryModelClass> arrayList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        RecyclerView recyclerView= findViewById(R.id.recyclerView);
        String diaryUserEmail=getIntent().getStringExtra("gmail");
        //System.out.println(diaryUserEmail);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url ="http://192.168.1.105/mental_health/history.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //JSONArray arrays= new JSONArray(response);
                            JSONObject object = new JSONObject(response);
                            JSONArray arrays = object.getJSONArray("userthoughts");
                           // System.out.println(response);
                            for (int i=0;i<arrays.length();i++){
                                JSONObject productObject= arrays.getJSONObject(i);
                                String fetchThoughts= productObject.getString("thoughts");
                                String fetchTime= productObject.getString("time");
                                arrayList.add(new HistoryModelClass(fetchThoughts,fetchTime));
                                HistoryViewAdapter historyViewAdapter= new HistoryViewAdapter(getApplicationContext(),arrayList);
                                recyclerView.setAdapter(historyViewAdapter);
                                historyViewAdapter.notifyDataSetChanged();
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.getLocalizedMessage());

            }
        }){
            protected Map<String, String> getParams(){
                       Map<String, String> paramV = new HashMap<>();
                       paramV.put("email",diaryUserEmail);
                    return paramV;
            }
        };
        queue.add(stringRequest);



    }

}



