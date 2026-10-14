package com.unitask.app.utils;

import com.unitask.app.models.Atividade;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class NotificationHelper {

    private static final long UN_DIA_MS = 24 * 60 * 60 * 1000L;
    private static final long DUAS_HORAS_MS = 2 * 60 * 60 * 1000L;

    public static void agendarNotificacoes(Context context, Atividade atividade) {
        long agora = System.currentTimeMillis();
        long dataEntrega = atividade.getDataEntrega();

        long umDiaAntes = dataEntrega - UN_DIA_MS;
        if (umDiaAntes > agora) {
            agendarNotificacao(
                    context,
                    atividade.getId() * 10,
                    "Atividade amanhã: " + atividade.getTitulo(),
                    "Sua atividade vence amanhã! Não esqueça de entregá-la.",
                    umDiaAntes
            );
        }

        long duasHorasAntes = dataEntrega - DUAS_HORAS_MS;
        if (duasHorasAntes > agora) {
            agendarNotificacao(
                    context,
                    atividade.getId() * 10 + 1,
                    "Atividade em breve: " + atividade.getTitulo(),
                    "Sua atividade vence em 2 horas! Finalize agora.",
                    duasHorasAntes
            );
        }
    }

    private static void agendarNotificacao(Context context, int notifId, String titulo,
                                            String descricao, long horario) {
        Intent intent = new Intent(context, NotificationReceiver.class);
        intent.putExtra(NotificationReceiver.EXTRA_TITULO, titulo);
        intent.putExtra(NotificationReceiver.EXTRA_DESCRICAO, descricao);
        intent.putExtra(NotificationReceiver.EXTRA_NOTIF_ID, notifId);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, notifId, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (alarmManager.canScheduleExactAlarms()) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, horario, pendingIntent);
                } else {
                    alarmManager.set(AlarmManager.RTC_WAKEUP, horario, pendingIntent);
                }
            } else {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, horario, pendingIntent);
            }
        }
    }

    public static void cancelarNotificacoes(Context context, int atividadeId) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager == null) {
            return;
        }

        int[] ids = {atividadeId * 10, atividadeId * 10 + 1};
        for (int notifId : ids) {
            Intent intent = new Intent(context, NotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    context, notifId, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );
            alarmManager.cancel(pendingIntent);
        }
    }
}
