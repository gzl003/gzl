package com.example.gzl.myapplication.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;

import com.example.gzl.myapplication.R;
import com.example.gzl.myapplication.widget.SparkTextView;

public class SparkActivity extends AppCompatActivity {
    private static final String TAG = "SparkActivity";
    private SparkTextView shimmerTextView;
    private int pos = 0;
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            Log.e("TAG", "pos" + pos);
            pos++;
            if (pos < SparkTextView.PROGRESS_COUNT) {
                shimmerTextView.setProgress(pos);
            } else {
                try {
                    synchronized (this) {
                        wait(800);
                    }
                    pos = 0;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mHandler.postDelayed(mRunnable, 1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spark);

        shimmerTextView = (SparkTextView) findViewById(R.id.shimmer);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seek);

        seekBar.setMax(SparkTextView.PROGRESS_COUNT);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                shimmerTextView.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//        handler.removeCallbacks(runnable);  //停止Timer
        mHandler.postDelayed(mRunnable, 1);
    }
}
