package com.version2.swordsandsorcery;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.version2.swordsandsorcery.Database.CharacterBaseHelper;
import com.version2.swordsandsorcery.Database.CharacterDB;

import org.bouncycastle.util.Strings;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView cardRecyclerView;
    CardAdapter adapter;

    List<CharacterDB> characterDBList;
    public static Activity activity = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        ImageButton settingsButton = findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, settings.class));
            }
        });

        ImageButton helpButton = findViewById(R.id.help_button);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, help.class));
            }
        });


    }
    @Override
    protected void onStart(){
        super.onStart();
        final CharacterBaseHelper helper = new CharacterBaseHelper(getBaseContext());
        final SQLiteDatabase database = helper.getReadableDatabase();
        final Cursor AllCharacter = database.query(com.version2.swordsandsorcery.Database.CharacterDB.CharacterTable.CHARACTER_TABLE,null,"2",null,null,null,null);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        characterDBList = new ArrayList<>();
        cardRecyclerView = (RecyclerView) findViewById(R.id.cardRecyclerView);
        cardRecyclerView.setHasFixedSize(true);

        cardRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final Button createCharacter = findViewById(R.id.createNewCharacter);
        createCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharacterDB character = new CharacterDB();
                Intent newIntent = new Intent(MainActivity.this,characterCreationOverview.class);
                newIntent.putExtra("character", character);
                startActivity(newIntent);
            }
        });

        while (AllCharacter.moveToNext()){

            CharacterDB character = new CharacterDB();
            character.setBackground(AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.BACKGROUND)));
            character.setRace(AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.RACE)));
            character.setName(AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.NAME)));
            character.setClassName(AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.CLASS_NAME)));
            String level = AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.LVL));
            String[] abilityScoresString;
            if(Character.isDigit(level.charAt(0))){
                character.setLvl(Integer.parseInt(level));
                String stringScores = AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.ABILITY_SCORES ));
                abilityScoresString = Strings.split(stringScores.substring(1,stringScores.length()-1),',');
                for(int i = 0; i < abilityScoresString.length; i++){
                    if(i != 0) {
                        abilityScoresString[i] = abilityScoresString[i].substring(1);
                    }
                    character.setAbilityScore(i, Integer.parseInt(abilityScoresString[i]));
                }
            }
            character.setCreationTime(AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.TIME)));

            characterDBList.add(character);

        }

        //creating recyclerview adapter
        adapter = new CardAdapter(this, characterDBList);
        CardView card = findViewById(R.id.card);

        //setting adapter to recyclerview
        cardRecyclerView.setAdapter(adapter);
        AllCharacter.close();
    }
}
