package com.example.aumi_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class interpersonal extends AppCompatActivity {

    RecyclerView contactRecyclerView;
    Button contactActionButton;
    ArrayList<contactModelClass> contactList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpersonal);

        String interPersonalEmail=getIntent().getStringExtra("interPersonalEmail");
        System.out.println(interPersonalEmail);

        contactRecyclerView=findViewById(R.id.cotactRecyclerView);
        contactActionButton= findViewById(R.id.contactActionButton);

        contactRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url ="http://192.168.1.105/mental_health/contactList.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //JSONArray arrays= new JSONArray(response);
                            JSONObject object = new JSONObject(response);
                            JSONArray arrays = object.getJSONArray("contacts");
                            // System.out.println(response);
                            for (int i=0;i<arrays.length();i++){
                                JSONObject productObject= arrays.getJSONObject(i);
                                String fetchContactName= productObject.getString("contactName");
                                String fetchContactNumber= productObject.getString("contactNumber");
                                  contactList.add(new contactModelClass(fetchContactName,fetchContactNumber));
                                  contactViewAdapter adapter= new contactViewAdapter(getApplicationContext(),contactList);
                                  contactRecyclerView.setAdapter(adapter);
                                //contactViewAdapter.notifyDataSetChanged();
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
                paramV.put("email",interPersonalEmail);
                 return paramV;
            }
        };
        queue.add(stringRequest);



        contactActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog= new Dialog(interpersonal.this);
                dialog.setContentView(R.layout.add_update_contact);

                EditText edtcontactName= dialog.findViewById(R.id.contactName);
                EditText edtrcontactNumber= dialog.findViewById(R.id.contactNumber);
                Button addContact= dialog.findViewById(R.id.addContact);


                addContact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String contactNameString= edtcontactName.getText().toString();
                        String contactNumberString= edtrcontactNumber.getText().toString();
                        //System.out.println(contactNameString);
                        //System.out.println(contactNumberString);


                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        String url ="http://192.168.1.105/mental_health/contactaddition.php";

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals("Success")){

                                            Toast.makeText(interpersonal.this, "Done", Toast.LENGTH_LONG).show();

                                        }
                                        else {
                                            Toast.makeText(interpersonal.this,response, Toast.LENGTH_LONG).show();
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
                                paramV.put("name", contactNameString);
                                paramV.put("email",interPersonalEmail);
                                paramV.put("contact",contactNumberString);
                                return paramV;
                            }
                        };
                        queue.add(stringRequest);

                        dialog.dismiss();

                    }
                });


                dialog.show();

            }
        });



    }
}