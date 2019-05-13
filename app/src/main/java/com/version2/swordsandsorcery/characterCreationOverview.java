
package com.version2.swordsandsorcery;

import android.content.Intent;
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

import com.version2.swordsandsorcery.Database.CharacterDB;

public class characterCreationOverview extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_character_creation_overview);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        Intent intent = getIntent();
        CharacterDB character = (CharacterDB)intent.getSerializableExtra("character");
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

        return super.onOptionsItemSelected(item);
    }
}