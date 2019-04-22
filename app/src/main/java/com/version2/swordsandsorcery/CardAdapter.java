package com.version2.swordsandsorcery;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

// this will hold the RecyclerView.Adapter --> for binding the information to the view itself
// this will also hold the RecyclerView.ViewHolder --> for displaying the view itself
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder>
    {

        private Context characterContext;
        private List<CharacterCard> characterList;

        // constructor for the adapter
        public CardAdapter(Context characterContext, List<CharacterCard> characterList)
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
                CharacterCard fighterCard = characterList.get(position);

                holder.characterName.setText(fighterCard.getCharacterName());
                holder.characterClass.setText(fighterCard.getCharacterClass());
                holder.characterLevel.setText(String.valueOf(fighterCard.getLevel()));
                holder.characterIcon.setImageDrawable(characterContext.getResources().getDrawable(fighterCard.getImage()));
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

                // constructor
                public CardViewHolder(@NonNull View itemView)
                    {
                        super(itemView);

                        characterIcon = itemView.findViewById(R.id.classIcon);
                        characterName = itemView.findViewById(R.id.characterNameText);
                        characterClass = itemView.findViewById(R.id.characterClassText);
                        characterLevel = itemView.findViewById(R.id.characterLevel);
                    }
            }
    }
