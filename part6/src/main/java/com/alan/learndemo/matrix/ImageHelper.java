package com.alan.learndemo.matrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by jiaowei on 12/25/2015.
 */
public class ImageHelper {

    /**
     * 根据色调 亮度 饱和度 画出具体的图片
     * @param bitmap
     * @param hue 色调
     * @param saturation 饱和度
     * @param lum 亮度
     * @return
     */
    public static Bitmap handleImageEffect(Bitmap bitmap , float hue , float saturation , float lum){
        Bitmap bm = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        // 画布
        Canvas canvas = new Canvas(bm);
        // 画笔
        Paint paint = new Paint();

        //色调
        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0 , hue);
        hueMatrix.setRotate(1 , hue);
        hueMatrix.setRotate(2 , hue);

        // 饱和度
        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        // 亮度
        ColorMatrix scaleMatrix = new ColorMatrix();
        scaleMatrix.setScale(lum , lum , lum , 1);

        // 将矩阵的作用效果叠加
        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(scaleMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bitmap ,0,0, paint);
        return bm;
    }

    /**
     * 底片效果 具体的算法
     * @param bitmap
     * @return
     */
    public static Bitmap handleImageNegative(Bitmap bitmap){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int r,g,b,a;

        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth() , bitmap.getHeight() , Bitmap.Config.ARGB_8888);
        int oldPx[] = new int[width * height];
        int newPx[] = new int[width * height];

        // 提取整个Bitmap的像素点，并保存到一个数组
        // 第一个参数 接受位图颜色值得数组
        // 第二个参数 写入到pixels[]中的第一个像素的索引值
        // 第三个参数 pixels[]中的行间距
        // 第四个参数 从位图中读取的第一个像素的x坐标值
        // 第五个参数 从位图中读取的第一个像素的y坐标值
        // 第六个参数 从每一行中读取的像素宽度
        // 第七个参数 读取的行数
        bitmap.getPixels(oldPx, 0, width, 0, 0, width, height);

        for (int i = 0 ; i < width * height ; i++){
            int color = oldPx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            r = 255 - r;
            g = 255 - g;
            b = 255 - b;

            if(r > 255){
                r = 255;
            }else if(r < 0){
                r = 0;
            }

            if(g > 255){
                g = 255;
            }else if(g < 0){
                g = 0;
            }
            if(b > 255){
                b = 255;
            }else if(b < 0){
                b = 0;
            }
            newPx[i] = Color.argb(a , r ,g , b);

        }
        bmp.setPixels(newPx, 0, width, 0, 0, width, height);

        return  bmp;
    }

    /**
     * 老照片效果
     * @param bitmap
     * @return
     */
    public static Bitmap handleImagePixelsOldPhoto(Bitmap bitmap){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int r,g,b,a;
        int r1 , g1, b1;
        int oldPx[] = new int[width * height];
        int newPx[] = new int[width * height];

        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth() , bitmap.getHeight() , Bitmap.Config.ARGB_8888);
        bitmap.getPixels(oldPx , 0 , width , 0 ,0 ,width , height);
        for (int i = 0 ; i < width * height ; i++){
            int color = oldPx[i];

            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            // 核心算法
            r1 = (int) (0.393 * r + 0.769 * g + 0.189 * b);
            g1 = (int) (0.349 * r + 0.686 * g + 0.168 * b);
            b1 = (int) (0.272 * r + 0.534 + g + 0.131 * b);

            if(r1 > 255){
                r1 = 255;
            }
            if(g1 > 255){
                g1 = 255;
            }

            if(b1 > 255){
                b1 = 255;
            }
            newPx[i] = Color.argb(a , r1 , g1 , b1);
        }
        bmp.setPixels(newPx , 0 , width , 0 , 0 , width , height);
        return bmp;
    }

    /**
     * 浮雕效果
     * @param bitmap
     * @return
     */
    public static Bitmap handleImagePixelsRelief(Bitmap bitmap){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int r , g ,b , a;
        int r1 , g1 , b1;
        int colorBefore , color = 0;
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth() , bitmap.getHeight() , Bitmap.Config.ARGB_8888);
        int newPx[] = new int[width * height];
        int oldPx[] = new int[width * height];

        bitmap.getPixels(oldPx , 0 , width , 0 ,0 , width , height);
        for (int i = 1 ; i < width * height ; i++){
            // 核心算法
            colorBefore = oldPx[ i - 1];
            r = Color.red(colorBefore);
            g = Color.green(colorBefore);
            b = Color.blue(colorBefore);
            a = Color.alpha(colorBefore);

            color = oldPx[i];
            r1 = Color.red(color);
            g1 = Color.green(color);
            b1 = Color.blue(color);

            r = (r - r1 + 127);
            g = (g - g1 + 127);
            b = (b - b1 + 127);

            if(r > 255){
                r = 255;
            }
            if(g > 255){
                g = 255;
            }
            if(b> 255){
                b = 255;
            }

            newPx[i] = Color.argb(a , r , g , b);
        }
        bmp.setPixels(newPx , 0 , width , 0 ,0 , width , height);
        return bmp;
    }
}
