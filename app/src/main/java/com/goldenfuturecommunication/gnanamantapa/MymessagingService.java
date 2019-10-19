package com.goldenfuturecommunication.gnanamantapa;


import android.app.PendingIntent;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MymessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
    }

    public void showNotification(String title, String message){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"MyNotifications")
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_notifications_none_black_24dp)
                .setAutoCancel(true);
        Intent i=new Intent(this,notification.class);
        PendingIntent p=PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(p);

        NotificationManagerCompat manager= NotificationManagerCompat.from(this);
        manager.notify(999,builder.build());
    }
}

