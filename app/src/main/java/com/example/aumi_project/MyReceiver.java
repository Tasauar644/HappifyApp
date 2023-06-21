package com.example.aumi_project;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;

public class MyReceiver extends BroadcastReceiver {

    MediaPlayer mp;


    @Override
    public void onReceive(Context context, Intent intent) {


        mp=MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);



        NotificationCompat.Builder builder= new NotificationCompat.Builder(context,"Reminder")
                .setSmallIcon(R.drawable.img)
                .setContentTitle("Mental health App")
                .setContentText("reminder about something!!")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123,builder.build());

        mp.setLooping(true);
        mp.start();




    }
}
