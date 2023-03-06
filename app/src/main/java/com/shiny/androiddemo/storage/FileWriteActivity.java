package com.shiny.androiddemo.storage;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shiny.androiddemo.R;

import java.io.File;

public class FileWriteActivity extends AppCompatActivity {
    private static final String TAG = "FileWriteActivity";

    private TextView showContent;
    private Button write;
    private Button read;
    private String path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        showContent = findViewById(R.id.showContent);
        write = findViewById(R.id.write);
        read = findViewById(R.id.read);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 外部存储-私有空间 卸载应用app存储文件也跟随卸载
                String directory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString();

                // 外部存储-公用空间 卸载应用app存储文件保留
                // String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();

                // 内部存储-私有空间 空间小不是存储到sd卡上
                // String directory = getFilesDir().toString();

                path = directory + File.separatorChar + "shiny.txt";
                Log.i(TAG, "onClick: " + path);
                FileUtil.saveText(path, "shiny");
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = FileUtil.readText(path);
                Log.i(TAG, "onClick: " + path);
                showContent.setText(content);
            }
        });
    }
}
