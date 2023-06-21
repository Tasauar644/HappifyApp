package com.example.aumi_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcomePage2 extends AppCompatActivity {

     private Button happifyButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page2);

        happifyButton2= findViewById(R.id.happifyButton2);
        happifyButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(welcomePage2.this,LoginPage.class);
                startActivity(intent);
                finish();
            }
        });
    }



}