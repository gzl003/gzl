package com.zhiguang.li.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.zhiguang.li.R;
import com.zhiguang.li.adapter.GreenDaoListAdapter;
import com.zhiguang.li.been.Dog;
import com.zhiguang.li.been.User;
import com.zhiguang.li.db.DBManager;
import com.zhiguang.li.utils.PreferenceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库测试 GreenDao 使用方法
 * 1 编写对象User.class  对应数据库的表
 * 2 点击 Make progect 生成数据了的操作类 UserDao.class
 * 3 UserDao.class  用来增删改查的操作
 */
public class GreenDaoActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lvUser;
    private DBManager greenDaoHelper;
    private List<User> users;
    private List<Dog> dogs;
    private TextView tvTime;
    private Button btnAdd, btnDelete;
    private int maxCount = 0;
    private EditText ediMaxCount;
    private PreferenceUtil preferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);

        initView();
        getGreenDaoHelper();
        initData();
        long seleteTime = System.currentTimeMillis();
        refreshData();
        tvTime.setText("GreenDao查询" + preferenceUtil.getGreenDaoCount() + "条数据花了" + (System.currentTimeMillis() - seleteTime) + "毫秒");
    }


    private void refreshData() {
//        lvUser.setAdapter(new GreenDaoListAdapter(this, greenDaoHelper.getUserAll()));
        lvUser.setAdapter(new GreenDaoListAdapter(this, greenDaoHelper.getDogAll()));
    }

    private void initData() {
        users = new ArrayList<User>();
        users.clear();
        dogs = new ArrayList<Dog>();
        dogs.clear();
        for (int i = 0; i < maxCount; i++) {
            User user = new User();
            user.setId(i);
            user.setName("我是不是 王毛" + i);
            user.setAge(i);
            users.add(user);
        }

        for (int i = 0; i < maxCount; i++) {
            Dog dog = new Dog();
            dog.setId(i);
            dog.setName("我是不是 王毛" + i);
            dog.setCretry("");
            dogs.add(dog);
        }
    }

    private void initView() {
        preferenceUtil = PreferenceUtil.getIntance(this);
        lvUser = (ListView) findViewById(R.id.lv_user);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        tvTime = (TextView) findViewById(R.id.tv_time);
        ediMaxCount = (EditText) findViewById(R.id.edi_maxCount);
    }

    public void getGreenDaoHelper() {
        greenDaoHelper = new DBManager(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                greenDaoHelper.deleteAll();

                if (ediMaxCount.getText() != null && !ediMaxCount.getText().toString().isEmpty()) {
                    try {
                        maxCount = Integer.parseInt(ediMaxCount.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        tvTime.setText(e.getMessage());
                    }
                }

                preferenceUtil.saveGreenDaoCount(maxCount);
                initData();
                long addTime = System.currentTimeMillis();
//                for (int i= 0;i<maxCount;i++){
//                    greenDaoHelper.insertUser(users.get(i));
//                }
                for (int i = 0; i < maxCount; i++) {
                    greenDaoHelper.insertDog(dogs.get(i));
                }
                tvTime.setText("GreenDao添加" + preferenceUtil.getGreenDaoCount() + "条数据花了" + (System.currentTimeMillis() - addTime) + "毫秒");
                refreshData();

                break;
            case R.id.btn_delete:
                long deleteTime = System.currentTimeMillis();
                int count = preferenceUtil.getGreenDaoCount();
                greenDaoHelper.deleteAll();
                tvTime.setText("GreenDao删除" + count + "条数据花了" + (System.currentTimeMillis() - deleteTime) + "毫秒");
                refreshData();
                preferenceUtil.saveGreenDaoCount(0);
                break;
        }
    }
}
