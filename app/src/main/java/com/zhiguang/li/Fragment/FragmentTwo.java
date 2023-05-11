package com.zhiguang.li.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zhiguang.li.R;

/**
 * Created by 智光 on 2016/4/6.
 */
public class FragmentTwo extends Fragment implements View.OnClickListener {
    private Button fenlei_one;
    private View rootview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootview == null){
            rootview = inflater.inflate(R.layout.dialog_layput, null);
            init(rootview);
        }
        return rootview;
    }

    private void init(View view) {
        fenlei_one = (Button) view.findViewById(R.id.fenlei_one);
        fenlei_one.setText("我是三级分类");
        fenlei_one.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        ((FiltrateActivity)(getActivity())).filtrateDialog.dismiss();
    }
}
