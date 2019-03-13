package com.version2.swordsandsorcery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Spinner lvlDefault = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> lvlDefaultAdapter = new ArrayAdapter<String>(settings.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.lvl));
        lvlDefaultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lvlDefault.setAdapter(lvlDefaultAdapter);

        Spinner orientation = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> defaultOrientationAdapter = new ArrayAdapter<String>(settings.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.modOrientation));
        defaultOrientationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orientation.setAdapter(defaultOrientationAdapter);

        Spinner abilityScoreChoice = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> abilityScoreAdapter = new ArrayAdapter<String>(settings.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.abilityScoreChoice));
        abilityScoreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        abilityScoreChoice.setAdapter(abilityScoreAdapter);

    }



}
