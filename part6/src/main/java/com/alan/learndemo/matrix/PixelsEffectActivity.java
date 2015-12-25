package com.alan.learndemo.matrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

import com.alan.learndemo.R;

public class PixelsEffectActivity extends AppCompatActivity {
    private ImageView mImageOne;
    private ImageView mImageTwo;
    private ImageView mImageThree;
    private ImageView mImageFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixels_effect);
        initView();
    }
    private void initView(){
        mImageOne = (ImageView) findViewById(R.id.imageViewOne);
        mImageTwo = (ImageView) findViewById(R.id.imageViewTwo);
        mImageThree = (ImageView) findViewById(R.id.imageViewThree);
        mImageFour = (ImageView) findViewById(R.id.imageViewFour);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources() , R.mipmap.test2);
        mImageOne.setImageBitmap(bitmap);
        mImageTwo.setImageBitmap(ImageHelper.handleImageNegative(bitmap));
        mImageThree.setImageBitmap(ImageHelper.handleImagePixelsOldPhoto(bitmap));
        mImageFour.setImageBitmap(ImageHelper.handleImagePixelsRelief(bitmap));
    }
}
