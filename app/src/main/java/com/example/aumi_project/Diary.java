package com.example.aumi_project;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Diary extends AppCompatActivity {

    private Button diarySave,watchHistory;
    private EditText userThoughts;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        String userEmail=getIntent().getStringExtra("userEmail");
        //System.out.println(userEmail);(recieves email success)

        userThoughts= findViewById(R.id.userThought);
        diarySave= findViewById(R.id.diary);
        watchHistory=findViewById(R.id.watchHistory);


        diarySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userThought= userThoughts.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="http://192.168.1.105/mental_health/diary.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("Success")){

                                    Toast.makeText(Diary.this, "Done", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(Diary.this,response, Toast.LENGTH_LONG).show();
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
                        paramV.put("email", userEmail);
                        paramV.put("thoughts",userThought);
                        return paramV;
                    }
                };
                queue.add(stringRequest);

            }
        });

        watchHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Diary.this,History_activity.class);
                intent.putExtra("gmail",userEmail);
               // System.out.println(userEmail);
                startActivity(intent);

            }
        });



    }

}