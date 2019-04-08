package com.version2.swordsandsorcery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemSelectedListener;

public class TabFragment extends Fragment {
    int position;
    TextView textView;
    short bla = 0;

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
            case 3:
                return inflater.inflate(R.layout.fragment_character_creation_race, container, false);
            case 4:
                return inflater.inflate(R.layout.fragment_character_creation_background, container, false);
            case 5:
                return inflater.inflate(R.layout.fragment_character_creation_items, container, false);
            case 6:
                return inflater.inflate(R.layout.fragment_character_creation_spells, container, false);
            case 7:
                return inflater.inflate(R.layout.fragment_character_creation_view, container, false);
        }

        return inflater.inflate(R.layout.fragment_character_creation_overview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        switch (position){
            case 0:
                // spinner is implemented dynamically in the java activity file.
                Spinner lvlSpinner = (Spinner) view.findViewById(R.id.lvl_spinner);
                String[] items = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};

                // create arrayAdapter using the string array and a default
                ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, items);

                lvlSpinner.setAdapter(adapter);
                lvlSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                            {
                                Log.v("level", (String) parent.getItemAtPosition(position));
                            }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent)
                            {
                                // auto generated program stub will set the initial to the object at index 0,
                                // could we make it so that there is some kind of interface between the settings
                                // screen and the drop down interface here? Boolean?
                            }
                    });

                Spinner fighter = view.findViewById(R.id.fighter);
                String[] fighterOptions = new String[]{"Arcane Archer", "Battlemaster", "Brute", "Cavalier",
                        "Champion", "Eldritch Knight", "Monster Hunter", "Purple Dragon Knight", "Samurai",
                        "Scout", "Sharpshooter"};

                ArrayAdapter<String> fighterAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, fighterOptions);
                fighter.setAdapter(fighterAdapter);
                if (bla == 0){
                    fighter.setVisibility(View.INVISIBLE);
                }else {
                    fighter.setVisibility(View.VISIBLE);
                }


                fighter.setOnItemSelectedListener(new OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                            {
                                Log.v("fighterSelect", (String) parent.getItemAtPosition(position));
                            }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent)
                            {

                            }
                    });
                break;
            case 1:
                final Button artificer = view.findViewById(R.id.artificer);
                artificer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                final Button barbarian = view.findViewById(R.id.barbarian);
                barbarian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                final Button bard = view.findViewById(R.id.bard);
                bard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                final Button cleric = view.findViewById(R.id.cleric);
                cleric.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                final Button druid = view.findViewById(R.id.druid);
                druid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                final Button fighterClass = view.findViewById(R.id.fighter);
                fighterClass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bla = 1;
                    }
                });
                final Button monk = view.findViewById(R.id.monk);
                monk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button mystic = view.findViewById(R.id.mystic);
                mystic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button paladin = view.findViewById(R.id.paladin);
                paladin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button ranger = view.findViewById(R.id.ranger);
                ranger.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button rogue = view.findViewById(R.id.rogue);
                rogue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button sorcerer = view.findViewById(R.id.sorcerer);
                sorcerer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button warlock = view.findViewById(R.id.warlock);
                warlock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button wizard = view.findViewById(R.id.wizard);
                wizard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
        }
    }
}