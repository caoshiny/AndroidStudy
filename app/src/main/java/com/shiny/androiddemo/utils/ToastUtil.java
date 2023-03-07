package com.shiny.androiddemo.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void show(Context context, String desc) {
        Toast.makeText(context, desc, Toast.LENGTH_LONG).show();
    }
}
