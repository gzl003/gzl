package com.zhiguang.li.activity;

import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.zhiguang.li.R;
import com.zhiguang.li.utils.OrientationManager;
import com.zhiguang.li.utils.ScreenUtils;

public class ScreenSwitchingActivity extends BaseActivity {
    private final String TAG = "main";
    private OrientationManager orientationManager;

    private SurfaceView sv;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;
    private ImageView imagebody;

    private int currentPosition = 0;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            currentPosition = mediaPlayer.getCurrentPosition();
            seekBar.setProgress(currentPosition);
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_switching);
        imagebody = findViewById(R.id.body);
        sv = findViewById(R.id.sv);
        seekBar = findViewById(R.id.seekBar);
        // 为SurfaceHolder添加回调
        sv.getHolder().addCallback(callback);
        // 为进度条添加进度更改事件
        seekBar.setOnSeekBarChangeListener(change);

        initOrientationManager();
    }

    private SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
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
            mediaPlayer.setDataSource("https://agmjjzyi.ixibeiren.com/20181022/7MVaVwY9/index.m3u8");
            // 设置显示视频的SurfaceHolder
            mediaPlayer.setDisplay(sv.getHolder());
            Log.i(TAG, "开始装载");
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer mp) {
                    Log.i(TAG, "装载完成");
                    mediaPlayer.start();
                    // 按照初始位置播放
                    mediaPlayer.seekTo(msec);
                    // 设置进度条的最大进度为视频流的最大播放时长
                    seekBar.setMax(mediaPlayer.getDuration());
                    // 开始线程，更新进度条的刻度
                    handler.post(runnable);
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    // 在播放完毕被回调
                }
            });

            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {

                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    // 发生错误重新播放
                    play(0);
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private SeekBar.OnSeekBarChangeListener change = new SeekBar.OnSeekBarChangeListener() {

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

    /**
     * 初始化屏幕方向变化监听器
     */
    public void initOrientationManager() {
        if (orientationManager == null) {
            orientationManager = new OrientationManager(this);
            orientationManager.setOrientationListener(new OrientationManager.IOrientationListener() {
                @Override
                public void onOrientationChanged(int orientation) {
                    if (orientation == OrientationManager.ORIENTATION_90 || orientation == OrientationManager.ORIENTATION_270) {
                        toFullScreen();
                    } else if (orientation == OrientationManager.ORIENTATION_0 || orientation == OrientationManager.ORIENTATION_180) {
                        toSmallScreen();
                    }
                }
            });
            enableOrientation(true);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//保持屏幕不变黑
        }
    }

    /**
     * 全屏幕
     */
    private void toFullScreen() {
        imagebody.setVisibility(View.GONE);
        ViewGroup.LayoutParams params = sv.getLayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        sv.setLayoutParams(params);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

    }

    /**
     * 小屏幕
     */
    private void toSmallScreen() {
        imagebody.setVisibility(View.VISIBLE);
        ViewGroup.LayoutParams params = sv.getLayoutParams();
        params.height = ScreenUtils.dip2px(this, 220);
        sv.setLayoutParams(params);
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    /**
     * 是否启动监听屏幕方向变化
     *
     * @param enable true：启动； false：停止；
     */
    public void enableOrientation(boolean enable) {
        if (orientationManager != null) {
            if (enable) {
                orientationManager.enable();
            } else {
                orientationManager.disable();
            }
        }
    }
}
