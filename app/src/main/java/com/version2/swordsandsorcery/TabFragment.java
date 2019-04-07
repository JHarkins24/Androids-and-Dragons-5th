package com.version2.swordsandsorcery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TabFragment extends Fragment {
    int position;
    TextView textView;

    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        TabFragment tabFragment = new TabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("pos");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        switch (position){
            case 0:
                return inflater.inflate(R.layout.fragment_character_creation_overview, container, false);
            case 1:
                return inflater.inflate(R.layout.fragment_character_creation_class, container, false);
            case 2:
                return inflater.inflate(R.layout.fragment_character_creation_ability_scores, container, false);
            case 4:
                return inflater.inflate(R.layout.fragment_character_creation_race, container, false);
            case 5:
                return inflater.inflate(R.layout.fragment_character_creation_background, container, false);
            case 6:
                return inflater.inflate(R.layout.fragment_character_creation_items, container, false);
            case 7:
                return inflater.inflate(R.layout.fragment_character_creation_spells, container, false);
            case 8:
                return inflater.inflate(R.layout.fragment_character_creation_view, container, false);
        }

        return inflater.inflate(R.layout.fragment_character_creation_overview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView) view.findViewById(R.id.textView);


        switch (position){
            case 0:
                textView.setText(R.string.overview);
                break;
            case 1:
                textView.setText(R.string.characterClass);
                break;
            case 2:
                textView.setText(R.string.abilityScores);
                break;
            case 3:
                textView.setText(R.string.race);
                break;
            case 4:
                textView.setText(R.string.background);
                break;
            case 5:
                textView.setText(R.string.items);
                break;
            case 6:
                textView.setText(R.string.spells);
                break;
            case 7:
                textView.setText(R.string.characterView);
                break;
        }
    }
}