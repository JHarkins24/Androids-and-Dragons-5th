package com.version2.swordsandsorcery;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.version2.swordsandsorcery.Database.CharacterBaseHelper;
import com.version2.swordsandsorcery.Database.CharacterDB;

import java.util.List;

import static com.version2.swordsandsorcery.Database.CharacterDB.CharacterTable.CharactersColumns._ID;

// this will hold the RecyclerView.Adapter --> for binding the information to the view itself
// this will also hold the RecyclerView.ViewHolder --> for displaying the view itself
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder>
    {

        private Context characterContext;
        private List<CharacterDB> characterList;

        // constructor for the adapter
        public CardAdapter(Context characterContext, List<CharacterDB> characterList)
            {
                this.characterContext = characterContext;
                this.characterList = characterList;
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
                holder.position = card.getDataBaseIndex();
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
                int position;
                final CharacterBaseHelper helper = new CharacterBaseHelper(characterContext);
                final SQLiteDatabase database = helper.getReadableDatabase();

                // constructor
                public CardViewHolder(@NonNull View itemView)
                    {
                        super(itemView);
                        characterIcon = itemView.findViewById(R.id.classIcon);
                        characterName = itemView.findViewById(R.id.characterNameText);
                        characterClass = itemView.findViewById(R.id.characterClassText);
                        characterLevel = itemView.findViewById(R.id.characterLevel);
                        deleteButton = itemView.findViewById(R.id.deleteButton);
                        deleteButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View V) {
                                String[] positions = {Integer.toString(position)};
                                database.delete(CharacterDB.CharacterTable.TABLE_NAME, _ID +" = ?", positions);
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
