package com.zhiguang.li.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

import com.zhiguang.li.R;
import com.zhiguang.li.widget.VerticalSeekBar;

public class VerticalSeekbarActivity extends AppCompatActivity {

    private VerticalSeekBar verticalSeekBar = null;
    private TextView verticalText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_seekbar);

        verticalSeekBar = (VerticalSeekBar) findViewById(R.id.verticalSeekBar);
        verticalText = (TextView) findViewById(R.id.verticalText);
        verticalSeekBar.setOnSeekBarChangeListener(verticalSeekBarChangeListener);
    }


    private SeekBar.OnSeekBarChangeListener verticalSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
//            Log.d("", "Vertical SeekBar --> onProgressChanged");
            verticalText.setText(Integer.toString(progress));

        }
    };
}
