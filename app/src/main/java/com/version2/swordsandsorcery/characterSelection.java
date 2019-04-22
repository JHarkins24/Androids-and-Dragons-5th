package com.version2.swordsandsorcery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class characterSelection extends AppCompatActivity {

    RecyclerView cardRecyclerView;
    CardAdapter adapter;

    List<CharacterCard> characterCardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

//        ImageView background = (ImageView) findViewById(R.id.backgroundImg);
//        int imageResource = getResources().getIdentifier("@drawable/parchment", null, this.getPackageName());
//        background.setImageResource(imageResource);
//        background.setScaleType(ImageView.ScaleType.FIT_XY);

        characterCardList = new ArrayList<>();
        cardRecyclerView = (RecyclerView) findViewById(R.id.cardRecyclerView);
        cardRecyclerView.setHasFixedSize(true);

        cardRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        characterCardList.add(
                new CharacterCard(
                        1,
                        3,
                        "Nick The Fighter",
                        "Fighter",
                        R.drawable.fightericon));

        characterCardList.add(
                new CharacterCard(
                        1,
                        3,
                        "AJ The Mighty Wizard",
                        "Wizard",
                        R.drawable.wizardicon));

        characterCardList.add(
                new CharacterCard(
                        1,
                        3,
                        "Danny The Red Bull Bard",
                        "Bard",
                        R.drawable.bardicon));

        //creating recyclerview adapter
        adapter = new CardAdapter(this, characterCardList);

        //setting adapter to recyclerview
        cardRecyclerView.setAdapter(adapter);
    }

}
