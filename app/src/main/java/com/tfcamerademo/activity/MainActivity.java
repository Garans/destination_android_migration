package com.tfcamerademo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tfcamerademo.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button faceBtn = findViewById(R.id.faceBtn);
        Button roadBtn = findViewById(R.id.roadBtn);
        Button bodyBtn = findViewById(R.id.bodyBtn);
        Button carAndLineBtn = findViewById(R.id.carAndLineBtn);

        faceBtn.setOnClickListener(this::onClick);
        roadBtn.setOnClickListener(this::onClick);
        bodyBtn.setOnClickListener(this::onClick);
        carAndLineBtn.setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, CameraActivity.class);
        switch (v.getId()) {
            case R.id.faceBtn:
                intent.putExtra("Flag", 1);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.roadBtn:
                intent.putExtra("Flag", 2);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.bodyBtn:
                intent.putExtra("Flag", 3);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.carAndLineBtn:
                intent.putExtra("Flag", 4);
                MainActivity.this.startActivity(intent);
                break;
        }
    }
}