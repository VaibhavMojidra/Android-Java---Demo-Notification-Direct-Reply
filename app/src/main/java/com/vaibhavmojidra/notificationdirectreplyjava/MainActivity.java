package com.vaibhavmojidra.notificationdirectreplyjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;
import androidx.databinding.DataBindingUtil;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.vaibhavmojidra.notificationdirectreplyjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final String CHANNEL_ID="MESSAGE";
    private final String CHANNEL_NAME="MESSAGE";
    private NotificationManagerCompat manager;
    private final String REPLY_KEY="REPLY_KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.showNotification.setOnClickListener(v -> {
            manager=NotificationManagerCompat.from(MainActivity.this);
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                NotificationChannel channel=new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
                manager.createNotificationChannel(channel);
            }

            Intent intent=new Intent(MainActivity.this,SecondActivity.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            //Direct Reply Action
            RemoteInput remoteInput=new RemoteInput.Builder(REPLY_KEY)
                    .setLabel("Reply here")
                    .build();
            NotificationCompat.Action replyAction= new NotificationCompat.Action.Builder(0,"Reply",pendingIntent).addRemoteInput(remoteInput).build();



            Notification notification=new NotificationCompat.Builder(MainActivity.this,CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_notification_overlay)
                    .setContentTitle("Notification Title")
                    .setContentText("Notification Text")
                    .setAutoCancel(true)
                    .addAction(replyAction)
                    .setContentIntent(pendingIntent)
                    .build();
            manager.notify(2,notification);
        });
    }
}