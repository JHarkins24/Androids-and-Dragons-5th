package com.version2.swordsandsorcery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class rulesHelp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules_help);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        ImageButton backhelpButton = findViewById(R.id.back_help_button);
        backhelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(rulesHelp.this,help.class));
            }
        });

        ImageView background = (ImageView) findViewById(R.id.backgroundImg);
        int imageResource = getResources().getIdentifier("@drawable/parchment", null, this.getPackageName());
        background.setImageResource(imageResource);
        background.setScaleType(ImageView.ScaleType.FIT_XY);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
