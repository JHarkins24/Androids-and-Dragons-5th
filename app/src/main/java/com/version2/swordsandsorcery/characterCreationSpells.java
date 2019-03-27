package com.version2.swordsandsorcery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class characterCreationSpells extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_creation_spells);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        ImageButton homeButton = findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationSpells.this, main_menu.class));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.overviewButton:
                startActivity(new Intent(characterCreationSpells.this,characterCreationOverview.class));
                break;
            case R.id.classButton:
                startActivity(new Intent(characterCreationSpells.this,characterCreationClass.class));
                break;
            case R.id.abilityScoresButton:
                startActivity(new Intent(characterCreationSpells.this,characterCreationAbilityScores.class));
                break;
            case R.id.raceButton:
                startActivity(new Intent(characterCreationSpells.this,characterCreationRace.class));
                break;
            case R.id.backgroundButton:
                startActivity(new Intent(characterCreationSpells.this,characterCreationBackground.class));
                break;
            case R.id.itemsButton:
                startActivity(new Intent(characterCreationSpells.this,characterCreationItems.class));
                break;
            case R.id.spellsButton:
                startActivity(new Intent(characterCreationSpells.this,characterCreationSpells.class));
                break;
            case R.id.viewButton:
                startActivity(new Intent(characterCreationSpells.this,characterCreationCharacterView.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
