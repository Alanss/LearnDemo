package com.alan.learndemo.matrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.alan.learndemo.R;

public class PrimaryColorActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{
    private static final String TAG = "xjw";

    private static int MAX_VALUE = 255;
    private static int MIDDLE_VALUE = 127;
    private ImageView mImageView;
    private SeekBar mTonalitySeekBar;
    private SeekBar mSaturabilitySeekBar;
    private SeekBar mBrightnessSeekBar;
    private Bitmap mBitmap;

    float hue = 0;
    float saturation = 0;
    float brightness = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_color);

        init();
    }
    private void init(){
        mBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.test3);

        mImageView = (ImageView) findViewById(R.id.imageView);
        mTonalitySeekBar = (SeekBar) findViewById(R.id.tonalitySeekBar);
        mSaturabilitySeekBar = (SeekBar) findViewById(R.id.saturabilitySeekBar);
        mBrightnessSeekBar = (SeekBar) findViewById(R.id.brightnessSeekBar);
        mTonalitySeekBar.setOnSeekBarChangeListener(this);
        mSaturabilitySeekBar.setOnSeekBarChangeListener(this);
        mBrightnessSeekBar.setOnSeekBarChangeListener(this);

        mTonalitySeekBar.setMax(MAX_VALUE);
        mSaturabilitySeekBar.setMax(MAX_VALUE);
        mBrightnessSeekBar.setMax(MAX_VALUE);
        mTonalitySeekBar.setProgress(MIDDLE_VALUE);
        mSaturabilitySeekBar.setProgress(MIDDLE_VALUE);
        mBrightnessSeekBar.setProgress(MIDDLE_VALUE);

        mImageView.setImageBitmap(mBitmap);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        Log.e(TAG , "onProgressChanged");

        switch (seekBar.getId()){
            case R.id.tonalitySeekBar:
                hue = (progress - MIDDLE_VALUE) * 1.0f / MIDDLE_VALUE * 180;
                break;
            case R.id.saturabilitySeekBar:
                saturation = progress * 1.0f / MIDDLE_VALUE;
                break;
            case R.id.brightnessSeekBar:
                brightness = progress * 1.0f / MIDDLE_VALUE;
                break;
        }
        mImageView.setImageBitmap(ImageHelper.handleImageEffect(mBitmap , hue , saturation , brightness));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
//        Log.e(TAG , "onStartTrackingTouch");
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
//        Log.e(TAG , "onStopTrackingTouch");
    }
}
