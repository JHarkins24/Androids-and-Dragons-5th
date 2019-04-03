package com.version2.swordsandsorcery;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
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

import java.util.List;

public class characterCreationCharacterView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_creation_character_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        ImageButton homeButton = findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationCharacterView.this, main_menu.class));
            }
        });

        ImageView background = (ImageView) findViewById(R.id.backgroundImg);
        int imageResource = getResources().getIdentifier("@drawable/parchment", null, this.getPackageName());
        background.setImageResource(imageResource);
        background.setScaleType(ImageView.ScaleType.FIT_XY);

        final ImageButton Save = findViewById(R.id.save_button);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("5EFighterHumanCriminal.pdf"));
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
                startActivity(new Intent(characterCreationCharacterView.this,characterCreationOverview.class));
                break;
            case R.id.classButton:
                startActivity(new Intent(characterCreationCharacterView.this,characterCreationClass.class));
                break;
            case R.id.abilityScoresButton:
                startActivity(new Intent(characterCreationCharacterView.this,characterCreationAbilityScores.class));
                break;
            case R.id.raceButton:
                startActivity(new Intent(characterCreationCharacterView.this,characterCreationRace.class));
                break;
            case R.id.backgroundButton:
                startActivity(new Intent(characterCreationCharacterView.this,characterCreationBackground.class));
                break;
            case R.id.itemsButton:
                startActivity(new Intent(characterCreationCharacterView.this,characterCreationItems.class));
                break;
            case R.id.spellsButton:
                startActivity(new Intent(characterCreationCharacterView.this,characterCreationSpells.class));
                break;
            case R.id.viewButton:
                startActivity(new Intent(characterCreationCharacterView.this,characterCreationCharacterView.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
