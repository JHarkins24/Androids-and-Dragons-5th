package com.version2.swordsandsorcery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class characterCreationOverview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_creation_overview);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        ImageButton homeButton = findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(characterCreationOverview.this, main_menu.class));
            }
        });

        ImageView background = (ImageView) findViewById(R.id.backgroundImg);
        int imageResource = getResources().getIdentifier("@drawable/parchment", null, this.getPackageName());
        background.setImageResource(imageResource);
        background.setScaleType(ImageView.ScaleType.FIT_XY);

        // spinner is implemented dynamically in the java activity file.
        Spinner lvlSpinner = (Spinner) findViewById(R.id.lvl_spinner);
        String[] items = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};

        // create arrayAdapter using the string array and a default
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, items);

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

//        Spinner artificer = findViewById(R.id.artificer);
//        String[] artificerOptions = new String[]{"Alchemist", "Gunsmith"};
//        ArrayAdapter<String> artificerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, artificerOptions);
//        artificer.setAdapter(artificerAdapter);
//
//        artificer.setOnItemSelectedListener(new OnItemSelectedListener()
//            {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//                    {
//                        Log.v("artificerSelect", (String) parent.getItemAtPosition(position));
//                    }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent)
//                    {
//                        // supposedly this creates a default value
//                    }
//            });
//
//        Spinner barbarian = findViewById(R.id.barbarian);
//        String[] barbarianOptions = new String[]{"Path of the Ancestral Guardian", "Path of the Battlerager", "Path of the Berserker", "Path of the Storm Herald", "Path of the Totem Warrior", "Path of the Zealot"};
//        ArrayAdapter<String> barbarianAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, barbarianOptions);
//        barbarian.setAdapter(barbarianAdapter);
//
//        barbarian.setOnItemSelectedListener(new OnItemSelectedListener()
//            {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//                    {
//                        Log.v("barbarianSelect", (String) parent.getItemAtPosition(position));
//                    }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent)
//                    {
//                        // supposedly this creates a default value
//                    }
//            });
//
//        Spinner bard = findViewById(R.id.bard);
//        String[] bardOptions = new String[]{"College of Glamour", "College of Lore", "College of Satire", "College of Swords", "College of Valor", "College of Whispers"};
//        ArrayAdapter<String> bardAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bardOptions);
//        bard.setAdapter(bardAdapter);
//
//        bard.setOnItemSelectedListener(new OnItemSelectedListener()
//            {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//                    {
//                        Log.v("bardSelect", (String) parent.getItemAtPosition(position));
//                    }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent)
//                    {
//                        // supposedly this creates a default value
//                    }
//            });
//
//        Spinner cleric = findViewById(R.id.cleric);
//        String[] clericOptions = new String[]{"Arcana Domain", "Ambition Domain", "City Domain", "Death Domain", "Forge Domain",
//                "Grave Domain", "Knowledge Domain", "Life Domain", "Light Domain", "Nature Domain",
//                "Order Domain", "Protection Domain", "Solidarity Domain", "Strength Domain",
//                "Tempest Domain", "Trickery Domain", "War Domain", "Zeal Domain"};
//        ArrayAdapter<String> clericAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, clericOptions);
//        cleric.setAdapter(clericAdapter);
//
//        cleric.setOnItemSelectedListener(new OnItemSelectedListener()
//            {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//                    {
//                        Log.v("clericSelect", (String) parent.getItemAtPosition(position));
//                    }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent)
//                    {
//                        // supposedly this creates a default value
//                    }
//            });
//
//        Spinner druid = findViewById(R.id.druid);
//        String[] druidOptions = new String[]{"Circle of Dreams", "Circle of the Land", "Circle of the Moon",
//                "Circle of the Shepherd", "Circle of Spores", "Circle of Twilight"};
//        ArrayAdapter<String> druidAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, druidOptions);
//        druid.setAdapter(druidAdapter);
//
//        druid.setOnItemSelectedListener(new OnItemSelectedListener()
//            {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//                    {
//                        Log.v("druidSelect", (String) parent.getItemAtPosition(position));
//                    }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent)
//                    {
//                        // supposedly this creates a default value
//                    }
//            });

        Spinner fighter = findViewById(R.id.fighter);
        String[] fighterOptions = new String[]{"Arcane Archer", "Battlemaster", "Brute", "Cavalier",
                "Champion", "Eldritch Knight", "Monster Hunter", "Purple Dragon Knight", "Samurai",
                "Scout", "Sharpshooter"};
        ArrayAdapter<String> fighterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, fighterOptions);
        fighter.setAdapter(fighterAdapter);

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
                        // supposedly this creates a default value
                    }
            });

//        Spinner monk = findViewById(R.id.monk);
//        String[] monkOptions = new String[]{"Way of the Drunken Master", "Way of the Four Elements",
//                "Way of the Kensei", "Way of the Long Death", "Way of the Open Hand", "Way of Shadow",
//                "Way of the Sun Soul", "Way of Tranquility"};
//        ArrayAdapter<String> monkAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, monkOptions);
//        monk.setAdapter(monkAdapter);
//
//        monk.setOnItemSelectedListener(new OnItemSelectedListener()
//            {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//                    {
//                        Log.v("monkSelect", (String) parent.getItemAtPosition(position));
//                    }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent)
//                    {
//                        // supposedly this creates a default value
//                    }
//            });
//
//        Spinner mystic = findViewById(R.id.mystic);
//        String[] mysticOptions = new String[]{"Order of the Avatar", "Order of the Awakened", "Order of the Immortal",
//                "Order of the Nomad", "Order of the Soul Knife", "Order of the Wu Jen"};
//        ArrayAdapter<String> mysticAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mysticOptions);
//        mystic.setAdapter(mysticAdapter);
//
//        mystic.setOnItemSelectedListener(new OnItemSelectedListener()
//            {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//                    {
//                        Log.v("mysticSelect", (String) parent.getItemAtPosition(position));
//                    }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent)
//                    {
//                        // supposedly this creates a default value
//                    }
//            });
//
//        Spinner paladin = findViewById(R.id.paladin);
//        String[] paladinOptions = new String[]{"Oath of the Ancients", "Oath of Conquest", "Oath of the Crown",
//                "Oath of Devotion", "Oath of Redemption", "Oath of Vengeance", "Oathbreaker", "Oath of Treachery"};
//        ArrayAdapter<String> paladinAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, paladinOptions);
//        paladin.setAdapter(paladinAdapter);
//
//        paladin.setOnItemSelectedListener(new OnItemSelectedListener()
//            {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//                    {
//                        Log.v("paladinSelect", (String) parent.getItemAtPosition(position));
//                    }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent)
//                    {
//                        // supposedly this creates a default value
//                    }
//            });
//
//        Spinner ranger = findViewById(R.id.ranger);
//        String[] rangerOptions = new String[]{"Beast Master", "Gloom Stalker",
//                "Horizon Walker", "Hunter", "Monster Slayer", "Primeval Guardian"};
//        ArrayAdapter<String> rangerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, rangerOptions);
//        ranger.setAdapter(rangerAdapter);
//
//        ranger.setOnItemSelectedListener(new OnItemSelectedListener()
//            {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//                    {
//                        Log.v("rangerSelect", (String) parent.getItemAtPosition(position));
//                    }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent)
//                    {
//                        // supposedly this creates a default value
//                    }
//            });
//
//        Spinner rogue = findViewById(R.id.rogue);
//        String[] rogueOptions = new String[]{"Arcane Trickster", "Assassin",
//                "Inquisitive", "Mastermind", "Scout", "Swashbuckler", "Thief"};
//        ArrayAdapter<String> rogueAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, rogueOptions);
//        rogue.setAdapter(rogueAdapter);
//
//        rogue.setOnItemSelectedListener(new OnItemSelectedListener()
//            {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//                    {
//                        Log.v("rogueSelect", (String) parent.getItemAtPosition(position));
//                    }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent)
//                    {
//                        // supposedly this creates a default value
//                    }
//            });
//
//        Spinner sorcerer = findViewById(R.id.sorcerer);
//        String[] sorcererOptions = new String[]{"Divine Soul", "Draconic Bloodline", "Giant Soul",
//                "Phoenix Sorcery", "Pyromancer", "Sea Sorcery", "Shadow Magic",
//                "Stone Sorcery", "Storm Sorcery", "Wild Magic"};
//        ArrayAdapter<String> sorcererAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sorcererOptions);
//        sorcerer.setAdapter(sorcererAdapter);
//
//        sorcerer.setOnItemSelectedListener(new OnItemSelectedListener()
//            {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//                    {
//                        Log.v("sorcererSelect", (String) parent.getItemAtPosition(position));
//                    }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent)
//                    {
//                        // supposedly this creates a default value
//                    }
//            });
//
//        Spinner warlock = findViewById(R.id.warlock);
//        String[] warlockOptions = new String[]{"The Archfey", "The Celestial", "The Fiend", "The Ghost in the Machine",
//                "The Great Old One", "The Hexblade", "The Raven Queen", "The Seeker", "The Undying"};
//        ArrayAdapter<String> warlockAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, warlockOptions);
//        warlock.setAdapter(warlockAdapter);
//
//        warlock.setOnItemSelectedListener(new OnItemSelectedListener()
//            {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//                    {
//                        Log.v("warlockSelect", (String) parent.getItemAtPosition(position));
//                    }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent)
//                    {
//                        // supposedly this creates a default value
//                    }
//            });
//
//        Spinner wizard = findViewById(R.id.wizard);
//        String[] wizardOptions = new String[]{"Bladesinger", "Lore Mastery", "School of Abjuration",
//                "School of Conjuration", "School of Divination", "School of Enchantment", "School of Evocation",
//                "School of Illusion", "School of Invention", "School of Necromancy", "School of Transmutation",
//                "Technomancy", "Theurgy", "War Magic"};
//        ArrayAdapter<String> wizardAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, wizardOptions);
//        wizard.setAdapter(wizardAdapter);
//
//        wizard.setOnItemSelectedListener(new OnItemSelectedListener()
//            {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//                    {
//                        Log.v("wizardSelect", (String) parent.getItemAtPosition(position));
//                    }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent)
//                    {
//                        // supposedly this creates a default value
//                    }
//            });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.overviewButton:
                startActivity(new Intent(characterCreationOverview.this,characterCreationOverview.class));
                break;
            case R.id.classButton:
                startActivity(new Intent(characterCreationOverview.this,characterCreationClass.class));
                break;
            case R.id.abilityScoresButton:
                startActivity(new Intent(characterCreationOverview.this,characterCreationAbilityScores.class));
                break;
            case R.id.raceButton:
                startActivity(new Intent(characterCreationOverview.this,characterCreationRace.class));
                break;
            case R.id.backgroundButton:
                startActivity(new Intent(characterCreationOverview.this,characterCreationBackground.class));
                break;
            case R.id.itemsButton:
                startActivity(new Intent(characterCreationOverview.this,characterCreationItems.class));
                break;
            case R.id.spellsButton:
                startActivity(new Intent(characterCreationOverview.this,characterCreationSpells.class));
                break;
            case R.id.viewButton:
                startActivity(new Intent(characterCreationOverview.this,characterCreationCharacterView.class));
        }
        return super.onOptionsItemSelected(item);
    }



}
