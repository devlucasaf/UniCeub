package com.unitask.app.utils;

import com.unitask.app.R;
import com.unitask.app.activities.MainActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {

    public static final String CHANNEL_ID = "unitask_notifications";
    public static final String EXTRA_TITULO = "titulo";
    public static final String EXTRA_DESCRICAO = "descricao";
    public static final String EXTRA_NOTIF_ID = "notifId";

    @Override
    public void onReceive(Context context, Intent intent) {
        String titulo = intent.getStringExtra(EXTRA_TITULO);
        String descricao = intent.getStringExtra(EXTRA_DESCRICAO);
        int notifId = intent.getIntExtra(EXTRA_NOTIF_ID, 0);

        createNotificationChannel(context);
        showNotification(context, notifId, titulo, descricao);
    }

    private void createNotificationChannel(Context context) {
        NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                "UniTask - Lembretes de Atividades",
                NotificationManager.IMPORTANCE_HIGH
        );
        channel.setDescription("Notificações de prazo de entrega de atividades acadêmicas");

        NotificationManager manager = context.getSystemService(NotificationManager.class);
        if (manager != null) {
            manager.createNotificationChannel(channel);
        }
    }

    private void showNotification(Context context, int notifId, String titulo, String descricao) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                context, notifId, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("⏰ " + titulo)
                .setContentText(descricao)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(descricao))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify(notifId, builder.build());
        }
    }
}
