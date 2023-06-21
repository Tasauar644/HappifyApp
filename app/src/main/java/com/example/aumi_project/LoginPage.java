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

public class LoginPage extends AppCompatActivity {

    TextView signUp;
    private EditText signInEmail,signInPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

    }

    public  void  signUpPage(View view){
        signUp=findViewById(R.id.signUp);
        Intent intent= new Intent(LoginPage.this,SignUpPage.class);
        startActivity(intent);
        finish();
    }

    public  void  signIn(View view){
        signInEmail= findViewById(R.id.signInEmail);
        signInPass= findViewById(R.id.signInPass);


        String email= signInEmail.getText().toString();
        String password= signInPass.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url ="http://192.168.1.105/mental_health/signin.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Success")){
                            Intent intent= new Intent(LoginPage.this,HomePage.class);
                            intent.putExtra("email",email);
                            startActivity(intent);
                            finish();

                        }
                        else {
                            Toast.makeText(LoginPage.this,"Wrong Email or Password", Toast.LENGTH_LONG).show();


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
                paramV.put("email",email);
                paramV.put("password", password);
                return paramV;
            }
        };
        queue.add(stringRequest);



    }


}