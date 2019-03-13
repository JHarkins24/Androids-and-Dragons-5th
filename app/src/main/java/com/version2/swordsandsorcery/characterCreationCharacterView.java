package com.version2.swordsandsorcery;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class characterCreationCharacterView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_creation_character_view);

        final Button Overview = findViewById(R.id.button8);
        Overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationCharacterView.this,characterCreationOverview.class));
            }
        });
        final Button Class = findViewById(R.id.button9);
        Class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationCharacterView.this,characterCreationClass.class));
            }
        });
        final Button abilityScores = findViewById(R.id.button10);
        abilityScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationCharacterView.this,characterCreationAbilityScores.class));
            }
        });
        final Button Race = findViewById(R.id.button11);
        Race.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationCharacterView.this,characterCreationRace.class));
            }
        });
        final Button Background = findViewById(R.id.button12);
        Background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationCharacterView.this,characterCreationBackground.class));
            }
        });
        final Button Items = findViewById(R.id.button13);
        Items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationCharacterView.this,characterCreationItems.class));
            }
        });
        final Button Spells = findViewById(R.id.button14);
        Spells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationCharacterView.this,characterCreationSpells.class));
            }
        });
        final Button characterView = findViewById(R.id.button15);
        characterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationCharacterView.this,characterCreationCharacterView.class));
            }
        });
        final Button Home = findViewById(R.id.button16);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationCharacterView.this,main_menu.class));
            }
        });

        final Button Save = findViewById(R.id.button17);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("../pdf.pdf"));
                intent.setType("application/pdf");
                PackageManager pm = getPackageManager();
                List<ResolveInfo> activities = pm.queryIntentActivities(intent, 0);
                if (activities.size() > 0) {
                    startActivity(intent);
                } else {
                    startActivity(new Intent(characterCreationCharacterView.this,main_menu.class));
                }
            }
        });

    }
}
