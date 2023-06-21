package com.example.aumi_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignUpPage extends AppCompatActivity {

    EditText userName,userEmail,userPassword,userContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

    }

    public  void signUp(View view){

        userName = findViewById(R.id.userName);
        userEmail= findViewById(R.id.userEmail);
        userPassword= findViewById(R.id.userPassword);
        userContact= findViewById(R.id.userContact);

        String name = userName.getText().toString();
        String email= userEmail.getText().toString();
        String password= userPassword.getText().toString();
        String contact = userContact.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url ="http://192.168.1.105/mental_health/signup.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Success")){

                            Toast.makeText(SignUpPage.this, "Done", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignUpPage.this,LoginPage.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(SignUpPage.this,response, Toast.LENGTH_LONG).show();
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
                paramV.put("name", name);
                paramV.put("email",email);
                paramV.put("password", password);
                paramV.put("contact",contact);
                return paramV;
            }
        };
        queue.add(stringRequest);

    }

}