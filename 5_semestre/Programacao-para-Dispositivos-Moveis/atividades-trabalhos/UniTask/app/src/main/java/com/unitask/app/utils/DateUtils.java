package com.unitask.app.utils;

import android.graphics.Color;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static final String FORMAT_DATA = "dd/MM/yyyy";
    public static final String FORMAT_DATA_HORA = "dd/MM/yyyy HH:mm";

    public static String formatarData(long timestamp) {
        if (timestamp <= 0) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATA, new Locale("pt", "BR"));
        return sdf.format(new Date(timestamp));
    }

    public static String formatarDataHora(long timestamp) {
        if (timestamp <= 0) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATA_HORA, new Locale("pt", "BR"));
        return sdf.format(new Date(timestamp));
    }

    public static boolean isAtrasada(long dataEntrega, String situacao) {
        return !"CONCLUIDA".equals(situacao) && dataEntrega < System.currentTimeMillis();
    }

    public static String diasRestantes(long dataEntrega) {
        long agora = System.currentTimeMillis();
        long diff = dataEntrega - agora;

        if (diff < 0) {
            long diasAtrasados = Math.abs(diff) / (24 * 60 * 60 * 1000L);
            if (diasAtrasados == 0) {
                return "Atrasada (hoje)";
            }
            return "Atrasada há " + diasAtrasados + " dia(s)";
        } else {
            long diasRestantes = diff / (24 * 60 * 60 * 1000L);
            if (diasRestantes == 0) {
                return "Vence hoje!";
            }

            if (diasRestantes == 1) {
                return "Vence amanhã";
            }
            return "Vence em " + diasRestantes + " dia(s)";
        }
    }

    public static int corPrazo(long dataEntrega, String situacao) {
        if ("CONCLUIDA".equals(situacao)) {
            return Color.parseColor("#4CAF50");
        }

        long agora = System.currentTimeMillis();
        long diff = dataEntrega - agora;
        long diasRestantes = diff / (24 * 60 * 60 * 1000L);

        if (diff < 0) {
            return Color.parseColor("#F44336");
        }

        if (diasRestantes <= 1) {
            return Color.parseColor("#FF9800");
        }

        if (diasRestantes <= 3) {
            return Color.parseColor("#FFC107");
        }
        return Color.parseColor("#4CAF50");
    }
}
