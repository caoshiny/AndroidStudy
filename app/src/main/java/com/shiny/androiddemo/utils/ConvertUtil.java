package com.shiny.androiddemo.utils;

import android.content.Context;

public class ConvertUtil {
    // 根据手机分辨率从 dp 转为 px
    public static int dpToPx(Context context, float dp) {
        // 获取当前手机的像素密度(1dp = ?px)
        float scale = context.getResources().getDisplayMetrics().density;
        // 四舍五入能取整
        return (int) (dp * scale + 0.5f);
    }
}
