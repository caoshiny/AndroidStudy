package com.shiny.androiddemo.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConvertUtil {
    // 根据手机分辨率从 dp 转为 px
    public static int dpToPx(Context context, float dp) {
        // 获取当前手机的像素密度(1dp = ?px)
        float scale = context.getResources().getDisplayMetrics().density;
        // 四舍五入能取整
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dp(Context context,float pxValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }

    public static int sp2px(Context context,float spValue){
        float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue*fontScale+0.5f);
    }

    public static int px2sp(Context context,float pxValue){
        float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue/fontScale+0.5f);
    }

    public static void soundByteArrToMp3(String mp3Path, byte[] soundByteArr) throws IOException {
        File mp3File = new File(mp3Path);
        FileOutputStream fos = new FileOutputStream(mp3File);
        fos.write(soundByteArr);
        fos.close();
    }
}
