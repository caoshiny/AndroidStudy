package com.shiny.androiddemo.videoview;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.shiny.androiddemo.R;
import com.shiny.androiddemo.utils.Mp4ParserUtil;

import java.io.File;
import java.io.IOException;

public class VideoViewActivity extends AppCompatActivity {
    private static final String  TAG = "VideoViewActivity";

    private VideoView vv;
    private Button btn_player;
    private Button convert;
    private MediaController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        Context context = getApplicationContext();
        vv = findViewById(R.id.vv);
        btn_player = findViewById(R.id.btn_play);
        convert = findViewById(R.id.convert);

        // Uri rtsp = Uri.parse("rtsp://192.168.201.1:554/live/stream0");
        // vv.setVideoURI(rtsp);

        String videoPath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + File.separatorChar + "2.mp4";
        vv.setVideoPath(videoPath);
        vv.requestFocus();

        btn_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    vv.start();
                    btn_player.setText("暂停");
                } else {
                    vv.pause();
                    btn_player.setText("播放");
                }
            }
        });

        /**
         * description: 进度条绑定到videoview控件上
         * web: http://ask.sov5.cn/q/8qc9vPcQp4
         */
        vv.setOnPreparedListener(mp -> mp.setOnVideoSizeChangedListener((mp1, width, height) -> {
            mc = new MediaController(VideoViewActivity.this);
            vv.setMediaController(mc);
            mc.setAnchorView(vv);
        }));

        String h264Path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + File.separatorChar + "2.h264";
        String mp4Path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + File.separatorChar + "3.mp4";
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + "准备开始转码!!!!");
                try {
                    Mp4ParserUtil.parser(h264Path, mp4Path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}