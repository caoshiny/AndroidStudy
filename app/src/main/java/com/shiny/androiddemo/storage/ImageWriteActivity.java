package com.shiny.androiddemo.storage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.shiny.androiddemo.R;

import java.io.File;

public class ImageWriteActivity extends AppCompatActivity {
    private static final String TAG = "ImageWriteActivity";

    private ImageView showImage;
    private Button save;
    private Button read;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_write);

        showImage = findViewById(R.id.showImage);
        save = findViewById(R.id.save);
        read = findViewById(R.id.readImage);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filename = System.currentTimeMillis() + ".jpeg";
                // 获取当前app 外部存储私有空间下载目录
                path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + File.separatorChar + filename;
                // 从指定资源文件中获取位图对象
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flower);

                Log.i(TAG, "onClick: " + path);
                FileUtil.saveImage(path, bitmap);
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = FileUtil.readImage(path);
                Log.i(TAG, "onClick: " + bitmap);
                showImage.setImageBitmap(bitmap);
            }
        });
    }
}