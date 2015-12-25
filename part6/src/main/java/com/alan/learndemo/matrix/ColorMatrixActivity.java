package com.alan.learndemo.matrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.alan.learndemo.R;

public class ColorMatrixActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView mImageView;
    private Button mChange;
    private Button mReset;
    private GridLayout gridLayout;
    private int mEtWidth;
    private int mEtHeight;
    private Bitmap mBitmap;

    private EditText[] mEts = new EditText[20];
    private float[] mColorMatrix = new float[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix);

        initView();
    }

    private void initView(){
        mImageView = (ImageView) findViewById(R.id.imageView);
        mChange = (Button) findViewById(R.id.change);
        mReset = (Button) findViewById(R.id.reset);

        mChange.setOnClickListener(this);
        mReset.setOnClickListener(this);
        mBitmap = BitmapFactory.decodeResource(getResources() , R.mipmap.test3);
        mImageView.setImageBitmap(mBitmap);

        gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        gridLayout.post(new Runnable() {
            @Override
            public void run() {
                // 获取宽高信息
                mEtWidth = gridLayout.getWidth() / 5;
                mEtHeight = gridLayout.getHeight() / 4;

                for (int i = 0; i < 20; i++) {
                    EditText editText = new EditText(ColorMatrixActivity.this);
                    //editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    mEts[i] = editText;
                    gridLayout.addView(editText, mEtWidth, mEtHeight);
                }
                initMatrix();
            }
        });


    }

    // 初始化颜色矩阵为初始状态
    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                mEts[i].setText(String.valueOf(1));
            } else {
                mEts[i].setText(String.valueOf(0));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change:
                getMatrix();
                setImageBitmap();
                break;
            case R.id.reset:
                break;
        }
    }

    private void getMatrix(){
        for (int i = 0 ; i < 20 ; i++){
            mColorMatrix[i] = Float.parseFloat(mEts[i].getText().toString());
        }
    }

    private void setImageBitmap(){
        Bitmap bitmap = Bitmap.createBitmap(mBitmap.getWidth() , mBitmap.getHeight() , Bitmap.Config.ARGB_8888);
        // 画布
        Canvas canvas = new Canvas(bitmap);
        // 画笔
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(mColorMatrix);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        mImageView.setImageBitmap(bitmap);
    }
}
