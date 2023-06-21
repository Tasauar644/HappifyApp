package com.example.aumi_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    private Button myDiary,setReminder;
    private ImageButton interPersonal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        String data = getIntent().getStringExtra("email");//needs to be transferred to every activity
        System.out.println(data);

        myDiary= findViewById(R.id.myDiary);
        setReminder=findViewById(R.id.setReminder);
        interPersonal= findViewById(R.id.interPersonal);


        myDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HomePage.this,Diary.class);
                intent.putExtra("userEmail",data);
                startActivity(intent);
                finish();

            }
        });
        setReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HomePage.this,set_reminder.class);
                startActivity(intent);
            }
        });

        interPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomePage.this,interpersonal.class);
                intent.putExtra("interPersonalEmail",data);
                startActivity(intent);
            }
        });


    }
}