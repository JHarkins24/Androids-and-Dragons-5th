package com.version2.swordsandsorcery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final Button characterCreation = findViewById(R.id.button2);
        characterCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main_menu.this, characterCreationOverview.class));
            }
        });

        final Button characterSelection = findViewById(R.id.button3);
        characterSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main_menu.this, com.version2.swordsandsorcery.characterSelection.class));
            }
        });

        final Button settings = findViewById(R.id.button6);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main_menu.this, com.version2.swordsandsorcery.settings.class));
            }
        });

        final Button help = findViewById(R.id.button7);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(main_menu.this, com.version2.swordsandsorcery.help.class));
            }
        });
    }


}
