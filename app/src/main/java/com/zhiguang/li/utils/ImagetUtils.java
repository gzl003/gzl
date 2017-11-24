package com.zhiguang.li.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 智光 on 2016/3/31.
 */
public class ImagetUtils {

    private static final String SAVE_PIC_PATH = Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory().getAbsolutePath() : "/mnt/sdcard";//保存到SD卡
    private static final String SAVE_REAL_PATH = SAVE_PIC_PATH + "/good/savePic";//保存的确切位置

    /**
     * 放大缩小图片
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidht = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidht, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return newbmp;
    }

    /**
     * 获取圆角的图片
     *
     * @param drawable
     * @param roundPx
     * @return
     */
    public static Bitmap getRadiosImage(Drawable drawable, float roundPx) {
        Bitmap bitmap = getRoundedCornerBitmap(drawableToBitmap(drawable), roundPx);
        return bitmap;
    }

    /**
     * 将Drawable转化为Bitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;

    }

    /**
     * 获得圆角图片的方法
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    /**
     * 获得带倒影的图片方法
     */
    public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
        final int reflectionGap = 4;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap reflectionImage = Bitmap.createBitmap(bitmap,
                0, height / 2, width, height / 2, matrix, false);

        Bitmap bitmapWithReflection = Bitmap.createBitmap(width, (height + height / 2), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap(bitmap, 0, 0, null);
        Paint deafalutPaint = new Paint();
        canvas.drawRect(0, height, width, height + reflectionGap,
                deafalutPaint);

        canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0,
                bitmap.getHeight(), 0, bitmapWithReflection.getHeight()
                + reflectionGap, 0x70ffffff, 0x00ffffff, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        // Set the Transfer mode to be porter duff and destination in
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        // Draw a rectangle using the paint with our linear gradient
        canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
                + reflectionGap, paint);

        return bitmapWithReflection;
    }

    /**
     * 水印 logo 与 标题
     */
    public static Bitmap createWaterBitmap(Bitmap src, Bitmap watermark, String title) {
        Bitmap newb = null;//创建一个保存水印的位图
        if (src == null) {
            return null;
        }
        //原图片的宽
        int w = src.getWidth();
        int h = src.getHeight();//原图片的高
        int ww = watermark.getWidth();//水印图片的宽
        int wh = watermark.getHeight();//水印图片的高
        Log.v("wz", w + "," + h + "," + ww + "," + wh);//日志文件中查看位图大小

        newb = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);//创建一个新的和src一样大小的位图
        Canvas cv = new Canvas(newb);//创建一个同等 大小的画布
        cv.drawBitmap(src, 0, 0, null);//从坐标0,0开始把src画入画布

        //设置水印 logo 的大小
        watermark = Bitmap.createScaledBitmap(watermark, 80, 80, true);
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Typeface font = Typeface.create(Typeface.DEFAULT, Typeface.BOLD);

        //在src中画入水印(右下角)
        cv.drawBitmap(watermark, 10, 10, null);

        //加入文字
        if (title != null) {
            float baseX = w - 350;
            float newY = h - 30;

            textPaint.setTextSize(35);
            // textPaint.setColor( Color.RED);
            textPaint.setColor(Color.rgb(255, 127, 0));
            textPaint.setTypeface(font);
            cv.drawText(title, baseX, newY, textPaint);
        }

        cv.save();//保存
        cv.restore();//存储
        return newb;//返回带水印的位图
    }

    /**
     * 保存图片到手机
     */
    public static void saveIamge(Context mContext, Bitmap bmp) {


        if (bmp == null) {
            Log.e("saveIamge", "保存出错了...");
            return;
        }
        String fileName = System.currentTimeMillis() + "bb";
        try {
            String url = MediaStore.Images.Media.insertImage(mContext.getContentResolver(), bmp, fileName, "asfadfioudgfiasdg");
            Log.e("saveIamge", "保存成功了...");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void saveImageToGallery(Context context, Bitmap bmp) {
        if (bmp == null) {
//            ToastUtils.show(context, "保存出错了...");
            return;
        }
        // 首先保存图片
        File appDir = new File(context.getApplicationContext().getFilesDir(), "ywq");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
//            ToastUtils.show(context, "文件未发现");
            e.printStackTrace();
        } catch (IOException e) {
//            ToastUtils.show(context, "保存出错了...");
            e.printStackTrace();
        } catch (Exception e) {
//            ToastUtils.show(context, "保存出错了...");
            e.printStackTrace();
        }

        // 最后通知图库更新
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
//        ToastUtils.show(context, "保存成功了...");
    }

    public static void insertImageToAllbum(Bitmap bitmap, Context mContext) {
        String subForder = SAVE_REAL_PATH;
        File foder = new File(subForder);
        if (!foder.exists()) {
            foder.mkdirs();
        }
        File myCaptureFile = new File(subForder, "hhhhhhh.jpeg");
        try {
            if (!myCaptureFile.exists()) {
                myCaptureFile.createNewFile();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] paths = { subForder };
        String[] mimeTypes = { "image/jpeg" };
        MediaScannerConnection.scanFile(mContext, paths, mimeTypes, new MediaScannerConnection.OnScanCompletedListener(){
            @Override
            public void onScanCompleted(String path, Uri uri) {
                Log.e("onScanCompleted", "path   " + path);
            }
        });

    }
}
