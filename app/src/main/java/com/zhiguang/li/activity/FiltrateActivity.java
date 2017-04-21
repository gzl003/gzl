package com.zhiguang.li.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.zhiguang.li.R;
import com.zhiguang.li.widget.FiltrateDialog;

/**
 * Created by 智光 on 2016/4/6.
 */
public class FiltrateActivity extends FragmentActivity {
    private Button show_dialog;
    public FiltrateDialog filtrateDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtrate_layout);
        show_dialog = (Button) findViewById(R.id.show_dialog);
        show_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtrateDialog = new FiltrateDialog(FiltrateActivity.this, getSupportFragmentManager());
//                filtrateDialog.showAtLocation(show_dialog, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                filtrateDialog.show();
            }

        });


    }
}
