package com.shiny.androiddemo.videoview;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.shiny.androiddemo.R;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class VideoViewActivity extends AppCompatActivity {
    private static final String  TAG = "VideoViewActivity";

    private VideoView vv;
    private MediaPlayer mp;
    private AudioTrack at;
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

        String downloadDir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString();
        String h264Path = downloadDir + File.separatorChar + "2.h264";
        String audioPath = downloadDir + File.separatorChar + "1.aud";
        String mp4Path = downloadDir + File.separatorChar + "3.mp4";
        String mp3Path = downloadDir + File.separatorChar + "temp.mp3";
        String videoPath = downloadDir + File.separatorChar + "2.mp4";


        vv.setVideoPath(videoPath);
        vv.requestFocus();

        at = new AudioTrack(AudioManager.STREAM_MUSIC, 24000, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT, 24000, AudioTrack.MODE_STREAM);
        byte[] sounds = null;
        try {
            sounds = FileUtils.readFileToByteArray(new File(audioPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] finalSounds = sounds;
        btn_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vv.isPlaying()) {
                    vv.start();
                    at.play();
                    at.write(finalSounds, 0 , finalSounds.length);
                    btn_player.setText("暂停");
                } else {
                    vv.pause();
                    at.pause();
                    btn_player.setText("播放");
                }
            }
        });

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + "准备开始转码!!!!");
                try {
                    // ConvertUtil.soundByteArrToMp3(downloadDir + File.separatorChar + "temp.mp3", FileUtils.readFileToByteArray(new File(audioPath)));
                    String[] command = {"-y", "-i", h264Path, "-c:v", "copy", "-c:a", "copy", mp4Path};
                    FFmpeg ffmpeg = FFmpeg.getInstance(context);
                    ffmpeg.execute(command, new ExecuteBinaryResponseHandler() {
                        @Override
                        public void onSuccess(String message) {
                            Log.d("FFmpeg", "Success: " + message);
                        }
                        @Override
                        public void onFailure(String message) {
                            Log.d("FFmpeg", "Failure: " + message);
                        }
                        @Override
                        public void onProgress(String message) {
                            Log.d("FFmpeg", "Progress: " + message);
                        }
                        @Override
                        public void onStart() {
                            Log.d("FFmpeg", "Started");
                        }
                        @Override
                        public void onFinish() {
                            Log.d("FFmpeg", "Finished");
                        }
                    });
                } catch (FFmpegCommandAlreadyRunningException e) {
                    e.printStackTrace();
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
    }
}