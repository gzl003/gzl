package com.example.gzl.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.gzl.myapplication.R;
import com.example.gzl.myapplication.utils.MapToJson;

import java.util.HashMap;

/**
 * Created by 智光 on 2016/6/16
 */
public class HasMapJsonActivity extends Activity {
    private TextView mytextview;
    HashMap<String, String> stringHashMap;

    public static Long startPlay;
    public static Long endPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapjson_layout);
        mytextview = (TextView) findViewById(R.id.mytext);
        startPlay = System.currentTimeMillis();
        stringHashMap = new HashMap<String,String>();
        stringHashMap.put("key1", "value8");
        stringHashMap.put("key2", "value76");
        stringHashMap.put("key3", "value65");
        stringHashMap.put("key4", "value54");
        stringHashMap.put("key5", "value3");
        stringHashMap.put("key6", "value1");
        stringHashMap.put("key7", "value34");
        stringHashMap.put("key8", "value8");
        mytextview.setText(MapToJson.hashMapToJson(stringHashMap));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        endPlay = System.currentTimeMillis();
        long jiange = (endPlay - startPlay) / 1000;
        Log.e("jiange", jiange + "");
    }
}
