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
        }

        return inflater.inflate(R.layout.fragment_tab, container, false);
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
        }
    }
}
