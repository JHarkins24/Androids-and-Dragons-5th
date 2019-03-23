package com.version2.swordsandsorcery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class characterSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);

        final Button Home = findViewById(R.id.button1);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterSelection.this,main_menu.class));
            }
        });

        ImageView background = (ImageView) findViewById(R.id.backgroundImg);
        int imageResource = getResources().getIdentifier("@drawable/parchment", null, this.getPackageName());
        background.setImageResource(imageResource);
        background.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}
