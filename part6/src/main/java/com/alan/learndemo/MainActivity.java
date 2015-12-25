package com.alan.learndemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alan.learndemo.matrix.ColorMatrixActivity;
import com.alan.learndemo.matrix.PixelsEffectActivity;
import com.alan.learndemo.matrix.PrimaryColorActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "xjw";
    private Button primaryColor;
    private Button colorMatrix;
    private Button pixelsEffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        primaryColor = (Button) findViewById(R.id.primaryColor);
        colorMatrix = (Button) findViewById(R.id.colorMatrix);
        pixelsEffect = (Button) findViewById(R.id.pixelsEffect);

        primaryColor.setOnClickListener(this);
        colorMatrix.setOnClickListener(this);
        pixelsEffect.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.primaryColor:
                startActivity(new Intent(MainActivity.this , PrimaryColorActivity.class));
                break;
            case R.id.colorMatrix:
                startActivity(new Intent(MainActivity.this, ColorMatrixActivity.class));
                break;
            case R.id.pixelsEffect:
                startActivity(new Intent(MainActivity.this , PixelsEffectActivity.class));
                break;
        }
    }
}
