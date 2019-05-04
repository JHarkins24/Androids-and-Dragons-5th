package com.version2.swordsandsorcery;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                startActivity(new Intent(MainActivity.this,characterCreationOverview.class));
            }
        });

        while (AllCharacter.moveToNext()){

            CharacterDB character = new CharacterDB();
            character.setName(AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.NAME)));
            character.setClassName(AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.CLASS_NAME)));
            character.setLvl(Integer.parseInt(AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.LVL))));
            character.setCreationTime(AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.TIME)));
            String[] abilityScoresString = Strings.split(AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.ABILITY_SCORES )),',');
            for(int i = 0; i < abilityScoresString.length; i++){

                abilityScoresString[i] = abilityScoresString[i].substring(1,3);
                character.setAbilityScore(i, Integer.parseInt(abilityScoresString[i]));
            }
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
