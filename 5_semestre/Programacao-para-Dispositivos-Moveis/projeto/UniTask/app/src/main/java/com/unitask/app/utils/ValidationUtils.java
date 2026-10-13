package com.unitask.app.utils;

import android.graphics.Color;
import android.text.TextUtils;

import java.util.regex.Pattern;

public class ValidationUtils {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"
    );

    public static boolean isEmailValido(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isSenhaValida(String senha) {
        return !TextUtils.isEmpty(senha) && senha.length() >= 6;
    }

    public static boolean isNomeValido(String nome) {
        return !TextUtils.isEmpty(nome) && nome.trim().length() >= 2;
    }

    public static boolean isCorHexValida(String cor) {
        if (TextUtils.isEmpty(cor)) {
            return false;
        }

        try {
            Color.parseColor(cor);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static String[] CORES_PADRAO = {
            "#6C5CE7",
            "#00B894",
            "#E17055",
            "#0984E3",
            "#FDCB6E",
            "#E84393",
            "#00CEC9",
            "#A29BFE",
            "#FD79A8",
            "#55EFC4"
    };
}
