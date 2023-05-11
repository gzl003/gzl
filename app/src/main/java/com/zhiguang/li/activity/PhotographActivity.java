package com.zhiguang.li.activity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.zhiguang.li.R;

import java.io.File;
import java.io.IOException;

/**
 * @author :智光
 * @date :2021/9/17 15:16
 * @desc :掉起系统相机拍照获取照片
 */
public class PhotographActivity extends AppCompatActivity {

    private final static int REQUEST_CODE_TAKE_PHOTO = 10;
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;

    private final static String AUTH_FILE_PROVIDER = "com.zhiguang.li.fileprovider";
    private AppCompatButton button;
    private ImageView ciemaImage;
    private File imageFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photograp);
        ciemaImage = findViewById(R.id.ciema_image);
        button = findViewById(R.id.photo_grap_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getPermission();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_TAKE_PHOTO) {
            if (imageFile != null && imageFile.exists()) {
                Uri uri = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    uri = FileProvider.getUriForFile(this,AUTH_FILE_PROVIDER, imageFile);
                } else {
                    uri = Uri.fromFile(imageFile);
                }
//                Uri uri = getImageContentUri(this, imageFile.getAbsolutePath());
                ciemaImage.setImageURI(uri);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (CAMERA_PERMISSIONS_REQUEST_CODE == requestCode) {
            callSysCameraAppToTakePhoto();
        }
    }

    public static Uri getImageContentUri(Context context, String path) {
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=? ",
                new String[]{path}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            // 如果图片不在手机的共享图片数据库，就先把它插入。
            if (new File(path).exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, path);
                return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    private void getPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                Toast.makeText(this, "您已经拒绝过一次", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_PERMISSIONS_REQUEST_CODE);
        } else {
            callSysCameraAppToTakePhoto();
        }
    }

    private void callSysCameraAppToTakePhoto() {
        // 拍照
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/aDemoCamera/testSys.jpg";
        imageFile = createImageFile(filePath);
        Uri imageUri;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  // 表示跳转至相机的拍照界面
        if (intent.resolveActivity(getPackageManager()) != null) {//这句作用是如果没有相机则该应用不会闪退，要是不加这句则当系统没有相机应用的时候该应用会闪退

            if (null != imageFile) {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    /* Android7及以上需要使用FileProvider */
                    imageUri = FileProvider.getUriForFile(this, AUTH_FILE_PROVIDER, imageFile);

                } else {
                    /*7.0以下则直接使用Uri的fromFile方法将File转化为Uri*/
                    imageUri = Uri.fromFile(imageFile);
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);    // 表示录制完后保存的录制，如果不写，则会保存到默认的路径，在onActivityResult()的回调，通过intent.getData中返回保存的路径

                startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);//启动相机
            } else {
                Log.e(getClass().getName(), "callSysCameraAppToTakePhoto() -- error: imageFile == null");
            }
        } else {
            Log.e(getClass().getName(), "callSysCameraAppToTakePhoto() -- error: open camera App failed");
        }
    }

    private File createImageFile(String filePath) {

        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        File file = new File(filePath);
        File dirFile = file.getParentFile();
        if (null != dirFile) {
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            file = null;
        }

        return file;
    }
}
