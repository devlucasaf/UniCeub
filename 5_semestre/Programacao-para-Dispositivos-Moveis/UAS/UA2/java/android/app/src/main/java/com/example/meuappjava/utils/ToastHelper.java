package com.example.meuappjava.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastHelper {
    public static void showShortToast(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }
}
