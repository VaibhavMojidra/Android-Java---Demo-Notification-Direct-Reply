package com.vaibhavmojidra.notificationdirectreplyjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;
import androidx.databinding.DataBindingUtil;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import com.vaibhavmojidra.notificationdirectreplyjava.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding;
    private final String CHANNEL_ID="MESSAGE";
    private final String CHANNEL_NAME="MESSAGE";
    private NotificationManagerCompat manager;
    private final String REPLY_KEY="REPLY_KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_second);
        receiveInput();
    }

    private void receiveInput() {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(getIntent());
        binding.textView.setText(remoteInput.getCharSequence(REPLY_KEY).toString());
        manager = NotificationManagerCompat.from(this);
        Notification notification=new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_notification_overlay)
                .setContentTitle("Notification Title")
                .setContentText("Replied")
                .setAutoCancel(true)
                .build();
        manager.notify(2,notification);
    }
}