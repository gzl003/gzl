package com.zhiguang.li.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.zhiguang.li.R;

/**
 * 测试mediaplayer
 * <p>
 */
public class MadiaPlayerActivity extends Activity {
    private final String TAG = "main";
    private SurfaceView sv;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private int currentPosition = 0;
    private boolean isPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_madia_player);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//横屏播放
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        sv = (SurfaceView) findViewById(R.id.sv);

        // 为SurfaceHolder添加回调
        sv.getHolder().addCallback(callback);

        // 4.0版本之下需要设置的属性
        // 设置Surface不维护自己的缓冲区，而是等待屏幕的渲染引擎将内容推送到界面
        // sv.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        // 为进度条添加进度更改事件
        seekBar.setOnSeekBarChangeListener(change);
    }

    private Callback callback = new Callback() {
        // SurfaceHolder被修改的时候回调
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            Log.i(TAG, "SurfaceHolder 被销毁");
            // 销毁SurfaceHolder的时候记录当前的播放位置并停止播放
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                currentPosition = mediaPlayer.getCurrentPosition();
                mediaPlayer.stop();
            }
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Log.i(TAG, "SurfaceHolder 被创建");
//            if (currentPosition > 0) {
            // 创建SurfaceHolder的时候，如果存在上次播放的位置，则按照上次播放位置进行播放
            play(currentPosition);
            currentPosition = 0;
//            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            Log.i(TAG, "SurfaceHolder 大小被改变");
        }

    };

    private SeekBar.OnSeekBarChangeListener change = new OnSeekBarChangeListener() {

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // 当进度条停止修改的时候触发
            // 取得当前进度条的刻度
            int progress = seekBar.getProgress();
//            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            // 设置当前播放的位置
            mediaPlayer.seekTo(progress);
//            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {

        }
    };

    /*
     * 停止播放
     */
    protected void stop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            isPlaying = false;
        }
    }

    /**
     * 开始播放
     *
     * @param msec 播放初始位置
     */
    protected void play(final int msec) {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            //设置重复播放
            mediaPlayer.setLooping(true);
            // 设置播放的视频源
            mediaPlayer.setDataSource(/*file.getAbsolutePath()*/"http://hls10.xiankan.com/target/hls/2016/11/14/213_982dc45f1d674b85940aaa1360cb7f7c_10_1920x1080.m3u8");
//            mediaPlayer.setDataSource(/*file.getAbsolutePath()*/"http://hls2.xiankan.com/target/hls/2015/12/09/213_2287f9efb6784107a4ef99d4eb3e0e94_27_854x480.m3u8");
            // 设置显示视频的SurfaceHolder
            mediaPlayer.setDisplay(sv.getHolder());
            Log.i(TAG, "开始装载");
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer mp) {
                    Log.i(TAG, "装载完成");
                    mediaPlayer.start();
                    // 按照初始位置播放
                    mediaPlayer.seekTo(msec);
                    // 设置进度条的最大进度为视频流的最大播放时长
                    seekBar.setMax(mediaPlayer.getDuration());
                    // 开始线程，更新进度条的刻度
                    new Thread() {

                        @Override
                        public void run() {
                            try {
                                isPlaying = true;
                                while (isPlaying) {
                                    int current = mediaPlayer
                                            .getCurrentPosition();
                                    seekBar.setProgress(current);

                                    sleep(50);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();

                }
            });
            mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    // 在播放完毕被回调
                }
            });

            mediaPlayer.setOnErrorListener(new OnErrorListener() {

                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    // 发生错误重新播放
                    play(0);
                    isPlaying = false;
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 重新开始播放
     */
    protected void replay() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(0);
            Toast.makeText(this, "重新播放", Toast.LENGTH_SHORT).show();
            return;
        }
        isPlaying = false;
        play(0);


    }

    /**
     * 暂停或继续
     */
    protected void pause() {
//        if (btn_pause.getText().toString().trim().equals("继续")) {
//            btn_pause.setText("暂停");
//            mediaPlayer.start();
//            Toast.makeText(this, "继续播放", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
//            mediaPlayer.pause();
//            btn_pause.setText("继续");
//            Toast.makeText(this, "暂停播放", Toast.LENGTH_SHORT).show();
//        }
    }
}
