package com.zhiguang.li.utils;

import android.content.Context;
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
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 智光 on 2016/3/31.
 */
public class ImagetUtils {

    private static final String SAVE_PIC_PATH = Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory().getAbsolutePath() : "/mnt/sdcard";//保存到SD卡
    private static final String SAVE_REAL_PATH = SAVE_PIC_PATH + "/DCIM/savePic";//保存的确切位置

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
     * 获得带倒影的图片方法 遮罩
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
        cv.drawBitmap(watermark, (w - ww) / 2, h - wh - 10, null);

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
     * 保存图片
     *
     * @param bitmap
     * @param mContext
     */
    public static void insertImageToAllbum(final Bitmap bitmap, final Context mContext, final saveImgListener listener) {
        final String SAVE_PIC_PATH = Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory().getAbsolutePath() : "/mnt/sdcard";//保存到SD卡
        final String SAVE_REAL_PATH = SAVE_PIC_PATH + "/DCIM/savePic";//保存的确切位置
        new Thread(new Runnable() {
            @Override
            public void run() {
                String subForder = SAVE_REAL_PATH;
                File foder = new File(subForder);
                if (!foder.exists()) {
                    foder.mkdirs();
                }
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                final File myCaptureFile = new File(subForder, timeStamp + ".jpeg");
                try {
                    if (!myCaptureFile.exists()) {
                        myCaptureFile.createNewFile();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int options = 100;
                    bitmap.compress(Bitmap.CompressFormat.PNG, options, baos);
                    while (baos.toByteArray().length / 1024 > 1500 && options > 0) {
                        baos.reset();
                        options -= 10;
                        bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
                    }
                    FileOutputStream fos = new FileOutputStream(myCaptureFile.getAbsolutePath());
                    fos.write(baos.toByteArray());
                    fos.flush();
                    fos.close();
                } catch (OutOfMemoryError | Exception e) {
                    e.printStackTrace();
                }


//                String[] paths = {myCaptureFile.getAbsolutePath()};
//                String[] mimeTypes = {"image/jpeg"};
//                MediaScannerConnection.scanFile(mContext, paths, mimeTypes, new MediaScannerConnection.OnScanCompletedListener() {
//                    @Override
//                    public void onScanCompleted(String path, Uri uri) {
//                        Log.e("onScanCompleted", "path   " + path);
////                        listener.onResult(path);
//                    }
//                });
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onResult(myCaptureFile.getAbsolutePath());
                    }
                });
            }

        }).start();

    }

    public interface saveImgListener {
        void onResult(String path);
    }

    /** 水平方向模糊度 */
    private static float hRadius = 5;
    /** 竖直方向模糊度 */
    private static float vRadius = 5;
    /** 模糊迭代度 */
    private static int iterations =3;

    /**
     * 图片高斯模糊处理
     * 
     */
    public static Bitmap blurImages(Bitmap bmp) {

        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] inPixels = new int[width * height];
        int[] outPixels = new int[width * height];
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bmp.getPixels(inPixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < iterations; i++) {
            blur(inPixels, outPixels, width, height, hRadius);
            blur(outPixels, inPixels, height, width, vRadius);
        }
        blurFractional(inPixels, outPixels, width, height, hRadius);
        blurFractional(outPixels, inPixels, height, width, vRadius);
        bitmap.setPixels(inPixels, 0, width, 0, 0, width, height);

        return bitmap;
    }

    /**
     * 图片高斯模糊算法
     */
    public static void blur(int[] in, int[] out, int width, int height, float radius) {
        int widthMinus1 = width - 1;
        int r = (int) radius;
        int tableSize = 2 * r + 1;
        int divide[] = new int[256 * tableSize];

        for (int i = 0; i < 256 * tableSize; i++)
            divide[i] = i / tableSize;

        int inIndex = 0;

        for (int y = 0; y < height; y++) {
            int outIndex = y;
            int ta = 0, tr = 0, tg = 0, tb = 0;

            for (int i = -r; i <= r; i++) {
                int rgb = in[inIndex + clamp(i, 0, width - 1)];
                ta += (rgb >> 24) & 0xff;
                tr += (rgb >> 16) & 0xff;
                tg += (rgb >> 8) & 0xff;
                tb += rgb & 0xff;
            }

            for (int x = 0; x < width; x++) {
                out[outIndex] = (divide[ta] << 24) | (divide[tr] << 16) | (divide[tg] << 8) | divide[tb];

                int i1 = x + r + 1;
                if (i1 > widthMinus1)
                    i1 = widthMinus1;
                int i2 = x - r;
                if (i2 < 0)
                    i2 = 0;
                int rgb1 = in[inIndex + i1];
                int rgb2 = in[inIndex + i2];

                ta += ((rgb1 >> 24) & 0xff) - ((rgb2 >> 24) & 0xff);
                tr += ((rgb1 & 0xff0000) - (rgb2 & 0xff0000)) >> 16;
                tg += ((rgb1 & 0xff00) - (rgb2 & 0xff00)) >> 8;
                tb += (rgb1 & 0xff) - (rgb2 & 0xff);
                outIndex += height;
            }
            inIndex += width;
        }
    }

    /**
     * 图片高斯模糊算法
     * 
     */
    public static void blurFractional(int[] in, int[] out, int width, int height, float radius) {
        radius -= (int) radius;
        float f = 1.0f / (1 + 2 * radius);
        int inIndex = 0;

        for (int y = 0; y < height; y++) {
            int outIndex = y;

            out[outIndex] = in[0];
            outIndex += height;
            for (int x = 1; x < width - 1; x++) {
                int i = inIndex + x;
                int rgb1 = in[i - 1];
                int rgb2 = in[i];
                int rgb3 = in[i + 1];

                int a1 = (rgb1 >> 24) & 0xff;
                int r1 = (rgb1 >> 16) & 0xff;
                int g1 = (rgb1 >> 8) & 0xff;
                int b1 = rgb1 & 0xff;
                int a2 = (rgb2 >> 24) & 0xff;
                int r2 = (rgb2 >> 16) & 0xff;
                int g2 = (rgb2 >> 8) & 0xff;
                int b2 = rgb2 & 0xff;
                int a3 = (rgb3 >> 24) & 0xff;
                int r3 = (rgb3 >> 16) & 0xff;
                int g3 = (rgb3 >> 8) & 0xff;
                int b3 = rgb3 & 0xff;
                a1 = a2 + (int) ((a1 + a3) * radius);
                r1 = r2 + (int) ((r1 + r3) * radius);
                g1 = g2 + (int) ((g1 + g3) * radius);
                b1 = b2 + (int) ((b1 + b3) * radius);
                a1 *= f;
                r1 *= f;
                g1 *= f;
                b1 *= f;
                out[outIndex] = (a1 << 24) | (r1 << 16) | (g1 << 8) | b1;
                outIndex += height;
            }
            out[outIndex] = in[width - 1];
            inIndex += width;
        }
    }
    public static int clamp(int x, int a, int b) {
        return (x < a) ? a : (x > b) ? b : x;
    }
}
