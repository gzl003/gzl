package com.zhiguang.li.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhiguang.li.R;
import com.zhiguang.li.utils.TestObservable;
import com.zhiguang.li.utils.TestObserver;

public class ObserverActivity extends AppCompatActivity {

    private Button add;
    private Button remove;
    private TextView result;
    private int number = 100;
    private TestObservable observable = new TestObservable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer);
        add = findViewById(R.id.btn_add);
        remove = findViewById(R.id.btn_remove);
        result = findViewById(R.id.tv_result);
        initObserver();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = number + 1;
                observable.addone(number);
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = number - 1;
                observable.removeone(number);
            }
        });


    }

    private void initObserver() {
        TestObserver testObserver = new TestObserver() {
            @Override
            public void onDataChanged(String message, Object object) {
                result.setText(object.toString());
            }
        };
        observable.addObserver(testObserver);
    }
}
