package com.version2.swordsandsorcery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Spinner lvlDropdown = findViewById(R.id.spinner);
        String[] lvlDefault = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
        ArrayAdapter<String> lvlDefaultAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, lvlDefault);
        lvlDropdown.setAdapter(lvlDefaultAdapter);

        ImageView background = (ImageView) findViewById(R.id.backgroundImg);
        int imageResource = getResources().getIdentifier("@drawable/parchment", null, this.getPackageName());
        background.setImageResource(imageResource);
        background.setScaleType(ImageView.ScaleType.FIT_XY);

        lvlDropdown.setOnItemSelectedListener(new OnItemSelectedListener()
            {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        Log.v("lvlDefault", (String) parent.getItemAtPosition(position));
                    }

                @Override
                public void onNothingSelected(AdapterView<?> parent)
                    {
                        // supposedly this creates a default value
                    }
            });

        ImageButton homeButton = findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(settings.this, main_menu.class));
            }
        });

        Spinner bonusDropdown = findViewById(R.id.spinner1);
        String[] bonusOrientation = new String[]{"Top", "Bottom"};
        ArrayAdapter<String> bonusOrientationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bonusOrientation);
        bonusDropdown.setAdapter(bonusOrientationAdapter);

        bonusDropdown.setOnItemSelectedListener(new OnItemSelectedListener()
            {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        Log.v("bonusOrientation", (String) parent.getItemAtPosition(position));
                    }

                @Override
                public void onNothingSelected(AdapterView<?> parent)
                    {
                        // supposedly this creates a default value
                    }
            });

        Spinner abilityScoreChoice = findViewById(R.id.spinner2);
        String[] abilityScoreChoiceOptions = new String[]{"Roll", "Manual", "Point Buy"};
        ArrayAdapter<String> abilityScoreChoiceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, abilityScoreChoiceOptions);
        abilityScoreChoice.setAdapter(abilityScoreChoiceAdapter);

        abilityScoreChoice.setOnItemSelectedListener(new OnItemSelectedListener()
            {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        Log.v("abilityScoreSelect", (String) parent.getItemAtPosition(position));
                    }

                @Override
                public void onNothingSelected(AdapterView<?> parent)
                    {
                        // supposedly this creates a default value
                    }
            });

    }



}
