package com.version2.swordsandsorcery;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

import java.util.List;


public class settings extends AppCompatActivity {

    private SharedPreferences levelPreferences;
    private SharedPreferences abilityScorePreferences;
    private int currentAbilityPoint;
    private int currentBuyPoint;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);



        ImageView background = (ImageView) findViewById(R.id.backgroundImg);
        int imageResource = getResources().getIdentifier("@drawable/parchment", null, this.getPackageName());
        background.setImageResource(imageResource);
        background.setScaleType(ImageView.ScaleType.FIT_XY);

//        final Spinner lvlDropdown = findViewById(R.id.spinner);
//        String[] lvlDefault = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
//        ArrayAdapter<String> lvlDefaultAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text_work_bitch, lvlDefault );
//        lvlDropdown.setAdapter(lvlDefaultAdapter);
//
//        levelPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        String level = levelPreferences.getString("level", "");
//        if(level != null)
//        {
//            int spinnerPosition = lvlDefaultAdapter.getPosition(level);
//            lvlDropdown.setSelection(spinnerPosition);
//
//        }
//
//
//        lvlDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//        {
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//            {
//                SharedPreferences.Editor editor = levelPreferences.edit();
//                editor.putString("level",lvlDropdown.getSelectedItem().toString());
//                Log.v("lvlDefault", (String) parent.getItemAtPosition(position));
//                editor.apply();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent)
//            {
//                // supposedly this creates a default value
//            }
//
//        });

//        final Spinner abilityScoreChoice = findViewById(R.id.spinner2);
//        String[] abilityScoreChoiceOptions = new String[]{"Roll", "Manual", "Point Buy"};
//        ArrayAdapter<String> abilityScoreChoiceAdapter = new ArrayAdapter<>(this, R.layout.spinner_text_work_bitch, abilityScoreChoiceOptions);
//        abilityScoreChoice.setAdapter(abilityScoreChoiceAdapter);
//
//        abilityScorePreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        String ability = abilityScorePreferences.getString("abilityScore", "");
//        if(ability != null)
//        {
//            int spinnerPosition = abilityScoreChoiceAdapter.getPosition(ability);
//            abilityScoreChoice.setSelection(spinnerPosition);
//
//        }
//
//        abilityScoreChoice.setOnItemSelectedListener(new OnItemSelectedListener()
//        {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//            {
//                SharedPreferences.Editor abilityEditor = abilityScorePreferences.edit();
//                abilityEditor.putString("abilityScore",abilityScoreChoice.getSelectedItem().toString());
//                Log.v("abilityScoreSelect", (String) parent.getItemAtPosition(position));
//                abilityEditor.apply();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent)
//            {
//                // supposedly this creates a default value
//            }
//        });


    }



}
