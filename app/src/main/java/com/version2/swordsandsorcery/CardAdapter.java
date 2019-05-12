package com.version2.swordsandsorcery;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.version2.swordsandsorcery.Database.CharacterBaseHelper;
import com.version2.swordsandsorcery.Database.CharacterDB;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import static com.version2.swordsandsorcery.Database.CharacterDB.CharacterTable.CharactersColumns.TIME;

// this will hold the RecyclerView.Adapter --> for binding the information to the view itself
// this will also hold the RecyclerView.ViewHolder --> for displaying the view itself
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder>
    {

        private Context characterContext;
        private List<CharacterDB> characterList;
        private  Activity activity;
        // constructor for the adapter
        public CardAdapter(Context characterContext, List<CharacterDB> characterList, Activity activity)
            {
                this.characterContext = characterContext;
                this.characterList = characterList;
                this.activity = activity;
            }

        @NonNull
        @Override
        // this will return the instances of CardViewHolder
        public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
            {
                LayoutInflater inflater = LayoutInflater.from(characterContext);
                View view = inflater.inflate(R.layout.character_card_list, null);
                return new CardViewHolder(view);
            }

        @Override
        // this will bind the information
        public void onBindViewHolder(CardViewHolder holder, int position)
            {
                CharacterDB card = characterList.get(position);

                holder.characterName.setText(card.getName());
                holder.characterClass.setText(card.getClassName());
                holder.characterLevel.setText(String.valueOf(card.getLvl()));
                holder.characterIcon.setImageDrawable(characterContext.getResources().getDrawable(getImage(card)));
                holder.position = card.getCreationTime();
                holder.character = card;
            }

        @Override
        public int getItemCount()
            {
                return characterList.size();
            }

        class CardViewHolder extends RecyclerView.ViewHolder
            {
                ImageView characterIcon;
                TextView characterName, characterClass, characterLevel;
                Button deleteButton;
                String position;
                CardView card;
                CharacterDB character;
                final CharacterBaseHelper helper = new CharacterBaseHelper(characterContext);
                final SQLiteDatabase database = helper.getReadableDatabase();

                // constructor
                public CardViewHolder(@NonNull View itemView)
                    {
                        super(itemView);
                        card = itemView.findViewById(R.id.card);
                        characterIcon = itemView.findViewById(R.id.classIcon);
                        characterName = itemView.findViewById(R.id.characterNameText);
                        characterClass = itemView.findViewById(R.id.characterClassText);
                        characterLevel = itemView.findViewById(R.id.characterLevel);
                        deleteButton = itemView.findViewById(R.id.deleteButton);
                        deleteButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View V) {
                                PdfOpener opener = new PdfOpener( character,activity, characterContext);
                                opener.handleExceptions(0, null);
//                                MainActivity.activity.finish();
//                                characterContext.startActivity(new Intent(characterContext, MainActivity.class));
                            }
                        });
                        card.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                character.setDeleteable(true);
                                Intent newIntent = new Intent(characterContext,characterCreationOverview.class);
                                newIntent.putExtra("character", character);
                                characterContext.startActivity(newIntent);
                            }
                        });
                    }
            }

            private int getImage(CharacterDB card){
                switch (card.getClassName()){
                    case"fighter":
                        return R.drawable.fightericon;
                    case "barbarian":
                        return R.drawable.barbarianicon;
                    case "bard":
                        return R.drawable.bardicon;
                    case "cleric":
                        return R.drawable.clericicon;
                    case "druid":
                        return R.drawable.druidicon;
                    case "monk":
                        return R.drawable.monkicon;
                    case "paladin":
                        return R.drawable.paladinicon;
                    case "ranger":
                        return R.drawable.rangericon;
                    case "rogue":
                        return R.drawable.rogueicon;
                    case "sorcerer":
                        return R.drawable.sorcerericon;
                    case "warlock":
                        return R.drawable.warlockicon;
                    case "wizard":
                        return R.drawable.wizardicon;

                }
                return R.drawable.fightericon;
            }
    }
