package com.version2.swordsandsorcery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class characterCreationOverview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_creation_overview);

        final Button Overview = findViewById(R.id.button8);
        Overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationOverview.this,characterCreationOverview.class));
            }
        });
        final Button Class = findViewById(R.id.button9);
        Class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationOverview.this,characterCreationClass.class));
            }
        });
        final Button abilityScores = findViewById(R.id.button10);
        abilityScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationOverview.this,characterCreationAbilityScores.class));
            }
        });
        final Button Race = findViewById(R.id.button11);
        Race.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationOverview.this,characterCreationRace.class));
            }
        });
        final Button Background = findViewById(R.id.button12);
        Background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationOverview.this,characterCreationBackground.class));
            }
        });
        final Button Items = findViewById(R.id.button13);
        Items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationOverview.this,characterCreationItems.class));
            }
        });
        final Button Spells = findViewById(R.id.button14);
        Spells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationOverview.this,characterCreationSpells.class));
            }
        });
        final Button characterView = findViewById(R.id.button15);
        characterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationOverview.this,characterCreationCharacterView.class));
            }
        });
        final Button Home = findViewById(R.id.button16);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationOverview.this,main_menu.class));

        Spinner lvlSpinner = (Spinner) findViewById(R.id.lvl_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(characterCreationOverview.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.lvl));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lvlSpinner.setAdapter(adapter);
            }
        });



    }


}
