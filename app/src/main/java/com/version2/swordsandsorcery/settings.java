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

        final Spinner lvlDropdown = findViewById(R.id.spinner);
        String[] lvlDefault = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
        ArrayAdapter<String> lvlDefaultAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text_work_bitch, lvlDefault );
        lvlDropdown.setAdapter(lvlDefaultAdapter);

        levelPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String level = levelPreferences.getString("level", "");
        if(level != null)
        {
            int spinnerPosition = lvlDefaultAdapter.getPosition(level);
            lvlDropdown.setSelection(spinnerPosition);

        }


        lvlDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                SharedPreferences.Editor editor = levelPreferences.edit();
                editor.putString("level",lvlDropdown.getSelectedItem().toString());
                Log.v("lvlDefault", (String) parent.getItemAtPosition(position));
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // supposedly this creates a default value
            }

        });

        final Spinner abilityScoreChoice = findViewById(R.id.spinner2);
        String[] abilityScoreChoiceOptions = new String[]{"Roll", "Manual", "Point Buy"};
        ArrayAdapter<String> abilityScoreChoiceAdapter = new ArrayAdapter<>(this, R.layout.spinner_text_work_bitch, abilityScoreChoiceOptions);
        abilityScoreChoice.setAdapter(abilityScoreChoiceAdapter);

        abilityScorePreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String ability = abilityScorePreferences.getString("abilityScore", "");
        if(ability != null)
        {
            int spinnerPosition = abilityScoreChoiceAdapter.getPosition(ability);
            abilityScoreChoice.setSelection(spinnerPosition);

        }

        abilityScoreChoice.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                SharedPreferences.Editor abilityEditor = abilityScorePreferences.edit();
                abilityEditor.putString("abilityScore",abilityScoreChoice.getSelectedItem().toString());
                Log.v("abilityScoreSelect", (String) parent.getItemAtPosition(position));
                abilityEditor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // supposedly this creates a default value
            }
        });

//         <TextView
//        android:id="@+id/textView2"
//        android:layout_width="290dp"
//        android:layout_height="37dp"
//        android:layout_marginStart="8dp"
//        android:layout_marginTop="8dp"
//        android:layout_marginEnd="8dp"
//        android:layout_marginBottom="8dp"
//        android:text="Starting Character Level"
//        android:textSize="24sp"
//        app:layout_constraintBottom_toTopOf="@+id/imageView"
//        app:layout_constraintEnd_toEndOf="parent"
//        app:layout_constraintHorizontal_bias="0.495"
//        app:layout_constraintStart_toStartOf="parent"
//        app:layout_constraintTop_toBottomOf="@+id/app_bar"
//        app:layout_constraintVertical_bias="1.0" />











//        final TextView pointBuy = findViewById(R.id.textView3);//27
//        final Button numberButton = findViewById(R.id.number_button);
//        final TextView abilityPoint = findViewById(R.id.textView4);//8
//
//      //  .setText(integer + "")
//        numberButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//               currentAbilityPoint = Integer.parseInt(abilityPoint.getText().toString());
//             currentBuyPoint = Integer.parseInt(pointBuy.getText().toString());
//
//                if(currentAbilityPoint >= 13)
//                {
//                    if(currentBuyPoint < 2 )
//                    {
//                        //throw error
//
//                    }
//                    currentAbilityPoint++;
//                    abilityPoint.setText(Integer.toString(currentAbilityPoint));
//                    currentBuyPoint = currentBuyPoint - 2;
//                    pointBuy.setText(Integer.toString(currentBuyPoint));
//
//                }
//                else if(currentAbilityPoint == 15 || currentBuyPoint == 0  )
//                {
//
//                }
//                else
//                {
//                    currentAbilityPoint++;
//                    abilityPoint.setText(Integer.toString(currentAbilityPoint));
//                    currentBuyPoint--;
//                    pointBuy.setText(Integer.toString(currentBuyPoint));
//                }
//
//
//
//            }
//        });



//     private void checkSharedPreferences(){
//            String
//        }



    }



}
