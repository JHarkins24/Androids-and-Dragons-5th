
package com.version2.swordsandsorcery;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.version2.swordsandsorcery.Database.CharacterBaseHelper;
import com.version2.swordsandsorcery.Database.CharacterDB;

import java.util.Arrays;
import java.util.Calendar;

import static com.version2.swordsandsorcery.Database.CharacterDB.CharacterTable.CharactersColumns.NAME;
import static com.version2.swordsandsorcery.Database.CharacterDB.CharacterTable.CharactersColumns.TIME;

public class characterCreationOverview extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    CharacterDB character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_character_creation_overview);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        Intent intent = getIntent();
        character = (CharacterDB)intent.getSerializableExtra("character");
        viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter adapter2 = new ViewPagerAdapter(character, getSupportFragmentManager());
        viewPager.setAdapter(adapter2);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.actionbar1, menu);
        MenuItem helpButton = menu.findItem(R.id.tlbHelp);
        MenuItem deleteButton = menu.findItem(R.id.t1bTrash);
        deleteButton.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.tlbHelp)
        {
            startActivity(new Intent(characterCreationOverview.this, help.class));
        }
        if(id == R.id.tlbSave){
            final ContentValues values = new ContentValues();
            CharacterBaseHelper helper = new CharacterBaseHelper(this);
            final SQLiteDatabase characterDataBase = helper.getReadableDatabase();
            values.put(NAME, character.getName());
            values.put(CharacterDB.CharacterTable.CharactersColumns.CLASS_NAME, character.getClassName());
            values.put(CharacterDB.CharacterTable.CharactersColumns.LVL, character.getLvl());
            values.put(CharacterDB.CharacterTable.CharactersColumns.BACKGROUND, character.getBackground());
            values.put(CharacterDB.CharacterTable.CharactersColumns.RACE, character.getRace());
            values.put(CharacterDB.CharacterTable.CharactersColumns.ABILITY_SCORES, Arrays.toString(character.getAbilityScores()));

            if(!character.getCreationTime().equals("")){
                characterDataBase.update(CharacterDB.CharacterTable.CHARACTER_TABLE,values, TIME + " = " + character.getCreationTime(), null);
            }else{
                values.put(TIME, Calendar.getInstance().getTimeInMillis());
                characterDataBase.insert(CharacterDB.CharacterTable.CHARACTER_TABLE, null, values);
            }
            finish();

        }
        if(id == R.id.t1bPDF){
            //todo: SPENCER CODE HERE
        }

        return super.onOptionsItemSelected(item);
    }
}