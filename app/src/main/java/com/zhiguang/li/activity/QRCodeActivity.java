package com.zhiguang.li.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;
import com.zhiguang.li.R;

/**
 * 二维码
 */
public class QRCodeActivity extends AppCompatActivity {

    private Button makeBut, saoBut;
    private TextView coutontext;
    private EditText imputEdit;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        initView();
    }

    private void initView() {
        makeBut = (Button) findViewById(R.id.makebutton);
        saoBut = (Button) findViewById(R.id.saomiaobutton);
        imputEdit = (EditText) findViewById(R.id.imputeditText);
        coutontext = (TextView) findViewById(R.id.coutontextView);
        imageView = (ImageView) findViewById(R.id.imageView);

        makeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeQR();
            }
        });
        saoBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saoQR();
            }
        });
    }

    private void makeQR() {
        String contentString = imputEdit.getText().toString();
        if (contentString != null && !contentString.isEmpty()) {

            if (!contentString.equals("")) {
                //根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
                Bitmap qrCodeBitmap = EncodingUtils.createQRCode(contentString, 350, 350, BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
                imageView.setImageBitmap(qrCodeBitmap);
            } else {
                Toast.makeText(this, "Text can not be empty", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saoQR() {
//打开扫描界面扫描条形码或二维码
        Intent openCameraIntent = new Intent(this, CaptureActivity.class);
        startActivityForResult(openCameraIntent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            coutontext.setText(scanResult);
        }
    }
}
