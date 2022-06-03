package com.example.hasma.sightsofminsk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class VisionImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision_image);

        ImageView img = (ImageView) findViewById(R.id.FullImage);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if (b!=null) {
            img.setImageResource(b.getInt("IMG"));
        }
    }
}
