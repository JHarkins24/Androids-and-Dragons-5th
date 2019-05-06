package com.version2.swordsandsorcery;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.version2.swordsandsorcery.Database.CharacterBaseHelper;
import com.version2.swordsandsorcery.Database.CharacterDB;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView cardRecyclerView;
    CardAdapter adapter;

    List<CharacterCard> characterCardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button action = findViewById(R.id.action_help);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, help.class));
            }
        });



        final CharacterBaseHelper helper = new CharacterBaseHelper(getBaseContext());
        final SQLiteDatabase database = helper.getReadableDatabase();
        final ContentValues values = new ContentValues();
        final Cursor AllCharacter = database.query(CharacterDB.CharacterTable.TABLE_NAME,null,"2",null,null,null,null);
        String characterName;
        String className;
        int level;

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);



        characterCardList = new ArrayList<>();
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

        if (AllCharacter.moveToNext())
            {
                characterName = AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.NAME));
                className = AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.CLASS_NAME));
                level = Integer.parseInt(AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.LVL)));

                characterCardList.add(
                        new CharacterCard(
                                1,
                                level,
                                characterName,
                                className,
                                R.drawable.fightericon));

                characterCardList.add(
                        new CharacterCard(
                                1,
                                level,
                                characterName,
                                className,
                                R.drawable.wizardicon));

                characterCardList.add(
                        new CharacterCard(
                                1,
                                level,
                                characterName,
                                className,
                                R.drawable.bardicon));
            }

        //creating recyclerview adapter
        adapter = new CardAdapter(this, characterCardList);

        //setting adapter to recyclerview
        cardRecyclerView.setAdapter(adapter);
        AllCharacter.close();
    }
}
