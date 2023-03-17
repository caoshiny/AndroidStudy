package com.shiny.androiddemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shiny.androiddemo.utils.ToastUtil;
import com.shiny.androiddemo.utils.WifiUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.tv);

        // 界面
        Button button = findViewById(R.id.btn);
        button.setTextSize(50);
        button.setOnClickListener(view -> {
            if (WifiUtil.isConnectedTo("ChinaNet-8536", MainActivity.this)) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            } else {
                ToastUtil.show(MainActivity.this, "未连接至指定wifi！");
            }
        });
    }
}