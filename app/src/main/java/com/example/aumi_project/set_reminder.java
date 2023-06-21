package com.example.aumi_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.aumi_project.databinding.ActivityMainBinding;

import java.util.Calendar;

public class set_reminder extends AppCompatActivity {


    private Button btn_date,btn_time,reminder_button;
    private TextView text_date,text_time;
     static final int ALARM_REQ_CODE=100;
     private static final int NOTIFICATION_ID=100;

    private EditText reminder_editText;
    int cyear,cmonth,cday;
    int chour,cminute;

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_set_reminder);
       createNotificationChannel();


        btn_date=findViewById(R.id.btn_date);
        btn_time=findViewById(R.id.btn_time);
        reminder_button=findViewById(R.id.reminder_button);

        text_date=findViewById(R.id.date_textview);
        text_time=findViewById(R.id.time_textview);
        reminder_editText=findViewById(R.id.reminder_editText);

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar= Calendar.getInstance();
                cyear=calendar.get(Calendar.YEAR);
                cmonth= calendar.get(Calendar.MONTH);
                cday=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog= new DatePickerDialog(set_reminder.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        text_date.setText(dayOfMonth+"/"+(month+1)+"/"+year);

                    }
                },cyear,cmonth,cday);
                datePickerDialog.show();
            }
        });

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final  Calendar calendar= Calendar.getInstance();
                chour= calendar.get(Calendar.HOUR_OF_DAY);
                cminute= calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog= new TimePickerDialog(set_reminder.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        text_time.setText(hourOfDay+":"+minute);
                        chour=hourOfDay;
                        cminute=minute;
                    }
                },chour,cminute,true);

                timePickerDialog.show();
            }
        });

        reminder_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
                if (cyear==0){

                    Toast.makeText(set_reminder.this, "Fill up date!", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(set_reminder.this, "Your reminder has been saved!!", Toast.LENGTH_SHORT).show();

                    String reminderText = reminder_editText.getText().toString();
                    Calendar c = Calendar.getInstance();
                    c.set(Calendar.YEAR, cyear);
                    c.set(Calendar.MONTH, cmonth);
                    c.set(Calendar.DAY_OF_MONTH, cday);
                    c.set(Calendar.HOUR_OF_DAY, chour);
                    c.set(Calendar.MINUTE, cminute);
                    c.set(Calendar.SECOND, 0);

                    System.out.println(cyear);
                    System.out.println(cmonth);
                    System.out.println(cday);
                    System.out.println(chour);
                    System.out.println(cminute);


                    Intent intent = new Intent(set_reminder.this, MyReceiver.class);
                    PendingIntent pi = PendingIntent.getBroadcast(set_reminder.this, ALARM_REQ_CODE, intent, PendingIntent.FLAG_MUTABLE);



                    alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);




                }

            }
        });

    }
    public void createNotificationChannel(){

        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)){
            CharSequence name= "ReminderChannel";
            String description= "Just a normal reminder";
            int important= NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel= new NotificationChannel("Reminder",name,important);
            channel.setDescription(description);


            NotificationManager notificationManager= getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }

}