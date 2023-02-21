package com.shiny.androiddemo.thread;

import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class RealTimeThread extends Thread {
    private final TextView timeView;

    public RealTimeThread(TextView timeView) {
        this.timeView = timeView;
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(1000);
                Message message = new Message();
                message.what = 1;
                mHandler.sendMessage(message);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }while (true);
    }

    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            try {
                switch (message.what) {
                    case 1:
                        long sysTime = System.currentTimeMillis();
                        CharSequence sysTimeStr = DateFormat.format("yyyy-MM-dd HH:mm:ss", sysTime);
                        timeView.setText(sysTimeStr);
                        break;
                    default:
                        break;
                }
            }catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    });
}
