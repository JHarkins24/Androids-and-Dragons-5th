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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.version2.swordsandsorcery.Database.CharacterBaseHelper;
import com.version2.swordsandsorcery.Database.CharacterDB;

import org.bouncycastle.util.Strings;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView cardRecyclerView;
    CardAdapter adapter;
    Toolbar toolbar;
    Button delete;
    Button help;

    List<CharacterDB> characterDBList;
    public static Activity activity = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        delete = findViewById(R.id.action_delete);
        help = findViewById(R.id.action_help);
//        action.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, help.class));
//            }
//        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        final CharacterBaseHelper helper = new CharacterBaseHelper(getBaseContext());
        final SQLiteDatabase database = helper.getReadableDatabase();
        final Cursor AllCharacter = database.query(com.version2.swordsandsorcery.Database.CharacterDB.CharacterTable.CHARACTER_TABLE,null,"2",null,null,null,null);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
//        setSupportActionBar(toolbar);

        characterDBList = new ArrayList<>();
        cardRecyclerView = (RecyclerView) findViewById(R.id.cardRecyclerView);
        cardRecyclerView.setHasFixedSize(true);

        cardRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        delete.setVisibility(View.INVISIBLE);
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
            character.setName(AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.NAME)));
            character.setClassName(AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.CLASS_NAME)));
            String level = AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.LVL));
            String[] abilityScoresString;
            character.setLvl(Integer.parseInt(level));
            abilityScoresString = Strings.split(AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.ABILITY_SCORES )),',');
            for(int i = 0; i < abilityScoresString.length; i++){
                abilityScoresString[i] = abilityScoresString[i].substring(1);
                if(i == abilityScoresString.length - 1){
                    abilityScoresString[i] = abilityScoresString[i].substring(0, abilityScoresString[i].length() - 1);
                }
                character.setAbilityScore(i, Integer.parseInt(abilityScoresString[i]));
                }
                character.setCreationTime(AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.TIME)));

            characterDBList.add(character);

        }
        AllCharacter.close();
        //creating recyclerview adapter
        adapter = new CardAdapter(this, characterDBList);
        CardView card = findViewById(R.id.card);

        //setting adapter to recyclerview
        cardRecyclerView.setAdapter(adapter);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.actionbar1, menu);
        MenuItem helpButton = menu.findItem(R.id.tlbHelp);
        MenuItem deleteButton = menu.findItem(R.id.t1bTrash);
        MenuItem saveButton = menu.findItem(R.id.tlbSave);
        saveButton.setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.tlbHelp)
        {
            startActivity(new Intent(MainActivity.this, help.class));
        }
        if(id == R.id.t1bTrash){
            for(int i = 0; i < adapter.getItemCount(); i++){
                adapter.toggleDeleteButtonVisibility();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
