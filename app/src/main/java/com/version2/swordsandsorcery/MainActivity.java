package com.version2.swordsandsorcery;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.version2.swordsandsorcery.Database.CharacterBaseHelper;
import com.version2.swordsandsorcery.Database.CharacterDB;

import org.bouncycastle.util.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView cardRecyclerView;
    CardAdapter adapter;
    Toolbar toolbar;
    Button delete;
    Button help;
    private SharedPreferences selectionAbilityScorePreference;

    ArrayList<String> equip;

    List<CharacterDB> characterDBList;
    public static Activity activity = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions();
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

        /////////////SPINNER FOR ABILITY SCORE TYPE SELECTION///////////////////////////////////////////////

        final Spinner abilityScoreType = findViewById(R.id.selection_type);
        LinkedList<String> scoreType = new LinkedList<>(Arrays.asList("Manual", "Roll", "Point Buy"));


        ArrayAdapter<String> adaptery = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_dropdown_item, scoreType);
        selectionAbilityScorePreference = PreferenceManager.getDefaultSharedPreferences(this);
        String abilitySelect = selectionAbilityScorePreference.getString("abilityScore", "");
        if (scoreType.contains(abilitySelect)) {
            scoreType.remove(abilitySelect);
            scoreType.addFirst(abilitySelect);
        }
        if (abilitySelect != null) {

            int spinnerPosition = adaptery.getPosition(abilitySelect);
            abilityScoreType.setSelection(spinnerPosition);

        }
        abilityScoreType.setAdapter(adaptery);
        abilityScoreType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = selectionAbilityScorePreference.edit();
                editor.putString("abilityScore", abilityScoreType.getSelectedItem().toString());
                Log.v("abilityScore", (String) parent.getItemAtPosition(position));
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // auto generated program stub will set the initial to the object at index 0,
                // could we make it so that there is some kind of interface between the settings
                // screen and the drop down interface here? Boolean?
            }
        });

        /////////////SPINNER FOR ABILITY SCORE TYPE SELECTION///////////////////////////////////////////////

        while (AllCharacter.moveToNext()){

            CharacterDB character = new CharacterDB();
            character.setName(AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.NAME)));
            character.setClassName(AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.CLASS_NAME)));
            character.setRace(AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.RACE)));
            character.setBackground(AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.BACKGROUND)));
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
        MenuItem pdfButton = menu.findItem(R.id.t1bPDF);
        pdfButton.setVisible(false);
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
                adapter.getHolder().get(i).toggleDeleteButtonVisibility();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestPermissions() throws NullPointerException{
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            System.out.println("Hi");
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults){
        System.out.println(requestCode);
    }
}
