package com.version2.swordsandsorcery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class characterCreationClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_creation_class);

        final Button Overview = findViewById(R.id.button8);
        Overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationOverview.class));
            }
        });
        final Button Class = findViewById(R.id.button9);
        Class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationClass.class));
            }
        });
        final Button abilityScores = findViewById(R.id.button10);
        abilityScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationAbilityScores.class));
            }
        });
        final Button Race = findViewById(R.id.button11);
        Race.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationRace.class));
            }
        });
        final Button Background = findViewById(R.id.button12);
        Background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationBackground.class));
            }
        });
        final Button Items = findViewById(R.id.button13);
        Items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationItems.class));
            }
        });
        final Button Spells = findViewById(R.id.button14);
        Spells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationSpells.class));
            }
        });
        final Button characterView = findViewById(R.id.button15);
        characterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationCharacterView.class));
            }
        });
        final Button Home = findViewById(R.id.button16);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,main_menu.class));
            }
        });
    }
}
