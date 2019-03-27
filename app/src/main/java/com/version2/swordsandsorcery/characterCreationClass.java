package com.version2.swordsandsorcery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class characterCreationClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_creation_class);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);


        final Button artificer = findViewById(R.id.artificer);
        artificer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationClass.class));
            }
        });
        final Button barbarian = findViewById(R.id.barbarian);
        barbarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationAbilityScores.class));
            }
        });
        final Button bard = findViewById(R.id.bard);
        bard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationRace.class));
            }
        });
        final Button cleric = findViewById(R.id.cleric);
        cleric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationBackground.class));
            }
        });
        final Button druid = findViewById(R.id.druid);
        druid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationItems.class));
            }
        });
        final Button fighter = findViewById(R.id.fighter);
        fighter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationSpells.class));
            }
        });
        final Button monk = findViewById(R.id.monk);
        monk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationCharacterView.class));
            }
        });

        final Button mystic = findViewById(R.id.mystic);
        mystic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationCharacterView.class));
            }
        });

        final Button paladin = findViewById(R.id.paladin);
        paladin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationCharacterView.class));
            }
        });

        final Button ranger = findViewById(R.id.ranger);
        ranger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationCharacterView.class));
            }
        });

        final Button rogue = findViewById(R.id.rogue);
        rogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationCharacterView.class));
            }
        });

        final Button sorcerer = findViewById(R.id.sorcerer);
        sorcerer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationCharacterView.class));
            }
        });

        final Button warlock = findViewById(R.id.warlock);
        warlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationCharacterView.class));
            }
        });

        final Button wizard = findViewById(R.id.wizard);
        wizard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this,characterCreationCharacterView.class));
            }
        });

        ImageButton homeButton = findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationClass.this, main_menu.class));
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
                startActivity(new Intent(characterCreationClass.this,characterCreationOverview.class));
                break;
            case R.id.classButton:
                startActivity(new Intent(characterCreationClass.this,characterCreationClass.class));
                break;
            case R.id.abilityScoresButton:
                startActivity(new Intent(characterCreationClass.this,characterCreationAbilityScores.class));
                break;
            case R.id.raceButton:
                startActivity(new Intent(characterCreationClass.this,characterCreationRace.class));
                break;
            case R.id.backgroundButton:
                startActivity(new Intent(characterCreationClass.this,characterCreationBackground.class));
                break;
            case R.id.itemsButton:
                startActivity(new Intent(characterCreationClass.this,characterCreationItems.class));
                break;
            case R.id.spellsButton:
                startActivity(new Intent(characterCreationClass.this,characterCreationSpells.class));
                break;
            case R.id.viewButton:
                startActivity(new Intent(characterCreationClass.this,characterCreationCharacterView.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
