package com.version2.swordsandsorcery;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import android.Manifest;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemSelectedListener;
import com.version2.swordsandsorcery.Database.CharacterBaseHelper;
import com.version2.swordsandsorcery.Database.CharacterDB;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import android.content.Context;
import android.content.res.AssetManager;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.bouncycastle.util.Strings;
import org.w3c.dom.CharacterData;

import static com.version2.swordsandsorcery.Database.CharacterDB.CharacterTable.CharactersColumns.*;


public class TabFragment extends Fragment {
    private SharedPreferences levelPreferences;
    ArrayList<String> equip;
    private SharedPreferences level2Preferences;
    private SharedPreferences abilityScorePreferences;
    private SQLiteDatabase characterDatabase;
    int position;
    static CharacterDB character;
    final int POINT_BUY_MAX = 15;
    final int POINT_BUY_MIN = 8;
    final int POINT_BUY_MIDDLE = 13;
    boolean permissionChecked = false;
    boolean permissionGranted = true;
    TextView textView;
    short bla = 0;

    public static Fragment getInstance(CharacterDB newCharacter, int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        TabFragment tabFragment = new TabFragment();
        tabFragment.setArguments(bundle);
        character = newCharacter;
        return tabFragment;

    }
    //Pdf methods//////////////////////////////////////////////////
	private InputStream getPdf()throws NullPointerException, IOException{
        AssetManager assetManager = this.getContext().getAssets();
        return assetManager.open("currentVersion");
    }

    private File getNewFile(String fileName){
        //return new File(Environment.getExternalStorageDirectory().getPath() + "/" + fileName);
        //return new File(this.getContext().getFilesDir(), fileName);
        return new File(getPublicAlbumStorageDir(""), fileName);
    }

    public boolean isExternalStorageWritable() {

         String state = Environment.getExternalStorageState();
        System.out.println(Environment.MEDIA_MOUNTED);
         return Environment.MEDIA_MOUNTED.equals(state);
     }


    public File getPublicAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("makeStorageTag", "Directory not created");
        }
        return file;
    }


    private void handleExceptions(int i, ImageButton save){
        PDFParser.class.getCanonicalName();
        if(!isExternalStorageWritable()){
            System.err.println("External storage is not writable");
            return;
        }

        try {
            if(permissionChecked && !permissionGranted){
                throw new IOException("Permissions not granted");
            }else if (!permissionChecked){
                requestPermissions();
            }

            switch (i) {
                case 0:
                    makePdf();
                    break;
                case 1:
                    makeUsingPdfBox();
                    break;
                default:
                    System.out.println("Bye");
            }
        }catch (IOException ioe){
            System.err.println(ioe.getMessage());
            save.setVisibility(View.INVISIBLE);
        }catch (NullPointerException npe){
            System.err.println(npe.getMessage());
            save.setVisibility(View.INVISIBLE);
            }
    }

    private void fillString(FileOutputStream fileOutputStream, InputStream inputStream, String string)throws IOException{
        int current = 0;
        for (int i = 0;i < 3; i++) {
            if(i < string.length()) {
                fileOutputStream.write((string.charAt(i) + "").getBytes());
            }else {
                fileOutputStream.write(' ');
            }
        }
        for (int i = 3; i < string.length() && current != '~'; i++) {
            current = inputStream.read();
            fileOutputStream.write((string.charAt(i)+"").getBytes());
        }

        while (current != '~'){
            current = inputStream.read();
            fileOutputStream.write(' ');
        }
    }

    private void requestPermissions() throws NullPointerException{
        if (ContextCompat.checkSelfPermission(this.getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.getActivity(),
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            System.out.println("Hi");
        }
    }

    public void onRequestPermissionsResult(int requestCode,
        String[] permissions, int[] grantResults){

        if(requestCode == 1 && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            permissionGranted = true;
            permissionChecked = true;
        }
        else {
            permissionGranted = false;
            permissionChecked = true;
        }
    }

    private void makePdf() throws IOException{

        InputStream oldFile = getPdf();
        File file = getNewFile("test2.pdf");
        requestPermissions();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        int currentChar = ',';
        while (currentChar != -1){
            if((currentChar = oldFile.read()) == ','){
                if((currentChar = oldFile.read()) == ','){
                    //todo fix breaks
                    int temp;
                    switch ((temp = oldFile.read())){
                        case '1':
                            fillString(fileOutputStream, oldFile, character.getName());
                            break;
                        case '2':
                            fillString(fileOutputStream, oldFile, Integer.toString(character.getAbilityScore(0)));
                            break;
                        case '3':
                            fillString(fileOutputStream, oldFile, Integer.toString(character.getAbilityScore(1)));
                            break;
                        case '4':
                            fillString(fileOutputStream, oldFile, Integer.toString(character.getAbilityScore(2)));
                            break;
                        case '5':
                            fillString(fileOutputStream, oldFile, Integer.toString(character.getAbilityScore(3)));
                            break;
                        case '6':
                            fillString(fileOutputStream, oldFile, Integer.toString(character.getAbilityScore(4)));
                            break;
                        case '7':
                            fillString(fileOutputStream, oldFile, Integer.toString(character.getAbilityScore(5)));
                            break;
                        case '8':
                            fillString(fileOutputStream, oldFile, Integer.toString(character.abilityModifier(0)));
                            break;
                        case '9':
                            fillString(fileOutputStream, oldFile, Integer.toString(character.abilityModifier(1)));
                            break;
                        case 'a':
                            fillString(fileOutputStream, oldFile, Integer.toString(character.abilityModifier(2)));
                            break;
                        case 'b':
                            fillString(fileOutputStream, oldFile, Integer.toString(character.abilityModifier(3)));
                            break;
                        case 'c':
                            fillString(fileOutputStream, oldFile, Integer.toString(character.abilityModifier(4)));
                            break;
                        case 'd':
                            fillString(fileOutputStream, oldFile, Integer.toString(character.abilityModifier(5)));
                            break;
                            default:
                                System.out.println("hi");
                                fileOutputStream.write((",," + (char)temp).getBytes());
                    }
                }else {
                    fileOutputStream.write(',');
                    fileOutputStream.write(currentChar);
                }
            }else {
                fileOutputStream.write(currentChar);
            }
        }
        fileOutputStream.close();
    }

    private void makeUsingPdfBox()throws IOException{

    }

    //Pdf methods//////////////////////////////////////////////////

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
                abilityScorePreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
                String ability = abilityScorePreferences.getString("abilityScore", "");
                switch(ability){
                    case"Point Buy":
                        return inflater.inflate(R.layout.fragment_character_creation_ability_scores_point_buy,container, false);
                    case "Roll":
                        return inflater.inflate(R.layout.fragment_character_creation_ability_scores_roll,container,false);
                    case "Manual":
                        return inflater.inflate(R.layout.fragment_character_creation_ability_scores_manual, container, false);
                }
                return inflater.inflate(R.layout.fragment_character_creation_ability_scores_manual, container, false);
            case 2:
                return inflater.inflate(R.layout.fragment_character_creation_items, container, false);
            case 3:
                return inflater.inflate(R.layout.fragment_character_creation_spells, container, false);
            case 4:
                return inflater.inflate(R.layout.fragment_character_creation_view, container, false);
        }
        return inflater.inflate(R.layout.fragment_character_creation_overview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        switch (position){
            case 0: {
                // spinner is implemented dynamically in the java activity file.
                final EditText name = view.findViewById(R.id.characterName);
                if(!character.getName().equals("")){
                    name.setText(character.getName());
                }
                name.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        character.setName(name.getText().toString());
                    }
                });
                final Spinner lvlSpinner = (Spinner) view.findViewById(R.id.lvl_spinner);
                LinkedList<String> levels = new LinkedList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"));
                // create arrayAdapter using the string array and a default
                ArrayAdapter<String> levelAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, levels);
                lvlSpinner.setAdapter(levelAdapter);
                int lvlSpinnerPosition = 0;
                if (character.getLvl() == 0) {
                    lvlSpinnerPosition = levelAdapter.getPosition("1");
                    lvlSpinner.setSelection(lvlSpinnerPosition);
                } else {
                    lvlSpinnerPosition = levelAdapter.getPosition(Integer.toString(character.getLvl()));
                    lvlSpinner.setSelection(lvlSpinnerPosition);
                }


                lvlSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.v("lvl", (String) parent.getItemAtPosition(position));
                        character.setLvl(Integer.parseInt((String) lvlSpinner.getSelectedItem()));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // auto generated program stub will set the initial to the object at index 0,
                        // could we make it so that there is some kind of interface between the settings
                        // screen and the drop down interface here? Boolean?
                    }
                });

                final Spinner classSpinner = (Spinner) view.findViewById(R.id.class_spinner);
                LinkedList<String> classes = new LinkedList<>(Arrays.asList("barbarian", "bard", "cleric", "druid", "fighter", "monk", "paladin", "ranger", "rogue", "sorcerer", "warlock", "wizard"));
                ArrayAdapter<String> classAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, classes);
                classSpinner.setAdapter(classAdapter);
                int classSpinnerPosition = 0;
                String s;
                if (character.getClassName().equals("")) {
                    classSpinnerPosition = classAdapter.getPosition("barbarian");
                    classSpinner.setSelection(classSpinnerPosition);
                } else {
                    classSpinnerPosition = classAdapter.getPosition(character.getClassName());
                    classSpinner.setSelection(classSpinnerPosition);
                    s = (String)classSpinner.getItemAtPosition(classSpinnerPosition);
                }

                classSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.v("class", (String) parent.getItemAtPosition(position));
                        character.setClassName((String) classSpinner.getSelectedItem());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // auto generated program stub will set the initial to the object at index 0,
                        // could we make it so that there is some kind of interface between the settings
                        // screen and the drop down interface here? Boolean?
                    }
                });
                final Spinner raceSpinner = (Spinner) view.findViewById(R.id.raceSpinner);
                LinkedList<String> racees = new LinkedList<>(Arrays.asList("dragonborn", "dwarf", "elf", "gnome", "half-elf", "halfing", "half-orc", "human", "tiefling"));
                ArrayAdapter<String> raceAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, racees);
                raceSpinner.setAdapter(raceAdapter);
                int raceSpinnerPosition = 0;
                if (character.getRace().equals("")) {
                    raceSpinnerPosition = raceAdapter.getPosition("dragonborn");
                    raceSpinner.setSelection(raceSpinnerPosition);
                } else {
                    raceSpinnerPosition = raceAdapter.getPosition(character.getRace());
                    raceSpinner.setSelection(raceSpinnerPosition);
                }


                raceSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.v("race", (String) parent.getItemAtPosition(position));
                        character.setRace((String) raceSpinner.getSelectedItem());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // auto generated program stub will set the initial to the object at index 0,
                        // could we make it so that there is some kind of interface between the settings
                        // screen and the drop down interface here? Boolean?
                    }
                });

                final Spinner backgroundSpinner = (Spinner) view.findViewById(R.id.backgroundSpinner);
                LinkedList<String> backgrounds = new LinkedList<>(Arrays.asList("acolyte", "charlatan", "criminal", "entertainer", "folk hero", "guild artisan", "hermit", "noble", "outlander", "sage", "sailor", "soldier", "urchin"));
                ArrayAdapter<String> backgroundAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, backgrounds);
                backgroundSpinner.setAdapter(backgroundAdapter);
                int bckgrndSpinnerPosition = 0;
                if (character.getBackground().equals("")){
                    bckgrndSpinnerPosition = backgroundAdapter.getPosition("acolyte");
                    backgroundSpinner.setSelection(bckgrndSpinnerPosition);
                }else{
                    bckgrndSpinnerPosition = backgroundAdapter.getPosition(character.getBackground());
                    backgroundSpinner.setSelection(bckgrndSpinnerPosition);
                }



                backgroundSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.v("background", (String) parent.getItemAtPosition(position));
                        character.setBackground((String)backgroundSpinner.getSelectedItem());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // auto generated program stub will set the initial to the object at index 0,
                        // could we make it so that there is some kind of interface between the settings
                        // screen and the drop down interface here? Boolean?
                    }
                });

            }
                break;
            case 1: {
                final TextView rollType = view.findViewById(R.id.rollType);
                abilityScorePreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
                String ability = abilityScorePreferences.getString("abilityScore", "");
                if (ability != null) {
                    switch (ability) {

                        case "Point Buy": {

                            final TextView pointBuy = view.findViewById(R.id.pointsRemaining);//27
                            final Button strPlus = view.findViewById(R.id.strPlus);
                            final Button strMin = view.findViewById(R.id.strMin);
                            final TextView str = view.findViewById(R.id.strValue);//8

                            strPlus.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pointBuyAdd(str, pointBuy);
                                }
                            });
                            strMin.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pointBuyMin(str, pointBuy);
                                }
                            });
                            final Button wisPlus = view.findViewById(R.id.wisPlus);
                            final Button wisMin = view.findViewById(R.id.wisMin);
                            final TextView wis = view.findViewById(R.id.wisValue);//8

                            wisPlus.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pointBuyAdd(wis, pointBuy);
                                }
                            });
                            wisMin.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pointBuyMin(wis, pointBuy);
                                }
                            });
                            final Button intPlus = view.findViewById(R.id.intPlus);
                            final Button intMin = view.findViewById(R.id.intMin);
                            final TextView intelligence = view.findViewById(R.id.intValue);//8

                            intPlus.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pointBuyAdd(intelligence, pointBuy);

                                }
                            });
                            intMin.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pointBuyMin(intelligence, pointBuy);
                                }
                            });
                            final Button chaPlus = view.findViewById(R.id.chaPlus);
                            final Button chaMin = view.findViewById(R.id.chaMin);
                            final TextView cha = view.findViewById(R.id.chaValue);//8

                            chaPlus.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pointBuyAdd(cha, pointBuy);

                                }
                            });
                            chaMin.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pointBuyMin(cha, pointBuy);
                                }
                            });
                            final Button dexPlus = view.findViewById(R.id.dexPlus);
                            final Button dexMin = view.findViewById(R.id.dexMin);
                            final TextView dex = view.findViewById(R.id.dexValue);//8

                            dexPlus.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pointBuyAdd(dex, pointBuy);
                                }
                            });
                            dexMin.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pointBuyMin(dex, pointBuy);

                                }
                            });

                            final Button conPlus = view.findViewById(R.id.conPlus);
                            final Button conMin = view.findViewById(R.id.conMin);
                            final TextView con = view.findViewById(R.id.conValue);//8

                            conPlus.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pointBuyAdd(con, pointBuy);
                                }
                            });
                            conMin.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pointBuyMin(con, pointBuy);
                                }
                            });
                            final Button save = view.findViewById(R.id.saveButtonPointBuy);
                            save.setOnClickListener(new View.OnClickListener(){
                                public void onClick(View v){
                                    character.setAbilityScore(0,Integer.parseInt((String)str.getText()));
                                    character.setAbilityScore(0,Integer.parseInt((String)dex.getText()));
                                    character.setAbilityScore(0,Integer.parseInt((String)con.getText()));
                                    character.setAbilityScore(0,Integer.parseInt((String)intelligence.getText()));
                                    character.setAbilityScore(0,Integer.parseInt((String)wis.getText()));
                                    character.setAbilityScore(0,Integer.parseInt((String)cha.getText()));
                                }
                            });
                        }
                        break;
                            case "Roll": {
                                //Write Roll algorithm that calls Roll in CharacterDB
                                final int[] lastClicked = {-1};
                                final Button[] abilityScores = {
                                        view.findViewById(R.id.strength), view.findViewById(R.id.dexterity), view.findViewById(R.id.constitution),
                                        view.findViewById(R.id.intelligence), view.findViewById(R.id.wisdom), view.findViewById(R.id.charisma)};
                                final Button[] scoreTable = {
                                        view.findViewById(R.id.score0), view.findViewById(R.id.score1), view.findViewById(R.id.score2),
                                        view.findViewById(R.id.score3), view.findViewById(R.id.score4), view.findViewById(R.id.score5), view.findViewById(R.id.score6)};
                                {

                                    scoreTable[0].setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            lastClicked[0] = 0;
                                        }
                                    });
                                    scoreTable[1].setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            lastClicked[0] = 1;
                                        }
                                    });
                                    scoreTable[2].setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            lastClicked[0] = 2;
                                        }
                                    });

                                    scoreTable[3].setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            lastClicked[0] = 3;
                                        }
                                    });
                                    scoreTable[4].setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            lastClicked[0] = 4;
                                        }
                                    });
                                    scoreTable[5].setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            lastClicked[0] = 5;
                                        }
                                    });
                                    scoreTable[6].setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            lastClicked[0] = 6;
                                        }
                                    });
                                }
                                int[] scores = character.rollAbilityScores();
                                for (int i = 0; i < scoreTable.length; i++) {
                                    scoreTable[i].setText(Integer.toString(scores[i]));
                                }
                                {
                                    abilityScores[0].setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (!abilityScores[0].getText().equals("")) {
                                                rollSetAbility(abilityScores[0], scoreTable[findFirstEmpty(scoreTable)]);
                                            } else {
                                                rollSetAbility(abilityScores[0], scoreTable[lastClicked[0]]);
                                                lastClicked[0] = -1;
                                            }

                                        }
                                    });
                                    abilityScores[1].setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (!abilityScores[1].getText().equals("")) {
                                                rollSetAbility(abilityScores[1], scoreTable[findFirstEmpty(scoreTable)]);
                                            } else {
                                                rollSetAbility(abilityScores[1], scoreTable[lastClicked[0]]);
                                                lastClicked[0] = -1;
                                            }

                                        }
                                    });
                                    abilityScores[2].setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (!abilityScores[2].getText().equals("")) {
                                                rollSetAbility(abilityScores[2], scoreTable[findFirstEmpty(scoreTable)]);
                                            } else {
                                                rollSetAbility(abilityScores[2], scoreTable[lastClicked[0]]);
                                                lastClicked[0] = -1;
                                            }

                                        }
                                    });
                                    abilityScores[3].setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (!abilityScores[3].getText().equals("")) {
                                                rollSetAbility(abilityScores[3], scoreTable[findFirstEmpty(scoreTable)]);
                                            } else {
                                                rollSetAbility(abilityScores[3], scoreTable[lastClicked[0]]);
                                                lastClicked[0] = -1;
                                            }

                                        }
                                    });
                                    abilityScores[4].setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (!abilityScores[4].getText().equals("")) {
                                                rollSetAbility(abilityScores[4], scoreTable[findFirstEmpty(scoreTable)]);
                                            } else {
                                                rollSetAbility(abilityScores[4], scoreTable[lastClicked[0]]);
                                                lastClicked[0] = 0;
                                            }

                                        }
                                    });
                                    abilityScores[5].setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (!abilityScores[5].getText().equals("")) {
                                                rollSetAbility(abilityScores[5], scoreTable[findFirstEmpty(scoreTable)]);
                                            } else {
                                                rollSetAbility(abilityScores[5], scoreTable[lastClicked[0]]);
                                                lastClicked[0] = 0;
                                            }

                                        }
                                    });


                                }
                                final Button saveRoll = view.findViewById(R.id.saveRoll);
                                saveRoll.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        int[] abilityScoresForCharacter = new int[abilityScores.length];
                                        for (int i = 0; i < abilityScores.length; i++) {
                                            abilityScoresForCharacter[i] = Integer.parseInt((String) abilityScores[i].getText());
                                        }
                                        character.setAbilityScores(abilityScoresForCharacter);
                                    }
                                });
                            }
                        break;
                        default: {
                            //Write Manual algorithm
                            final EditText str = view.findViewById(R.id.strength);
                            final EditText dex = view.findViewById(R.id.dexterity);
                            final EditText con = view.findViewById(R.id.constitution);
                            final EditText intelligence = view.findViewById(R.id.intelligence);
                            final EditText wis = view.findViewById(R.id.wisdom);
                            final EditText cha = view.findViewById(R.id.charisma);
                            if(character.getAbilityScore(0) != 0){
                                str.setText(Integer.toString(character.getAbilityScore(0)));
                                dex.setText(Integer.toString(character.getAbilityScore(1)));
                                con.setText(Integer.toString(character.getAbilityScore(2)));
                                intelligence.setText(Integer.toString(character.getAbilityScore(3)));
                                wis.setText(Integer.toString(character.getAbilityScore(4)));
                                cha.setText(Integer.toString(character.getAbilityScore(5)));

                            }
                            str.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    if(checkForChars(str.getText().toString())){
                                        return;
                                    }
                                    if (!str.getText().toString().equals("")) {
                                        character.setAbilityScore(0, Integer.parseInt(str.getText().toString()));
                                    }
                                }
                            });
                            dex.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    if(checkForChars(dex.getText().toString())){
                                        return;
                                    }
                                    if (!dex.getText().toString().equals("")) {
                                        character.setAbilityScore(1, Integer.parseInt(dex.getText().toString()));
                                    }
                                }
                            });
                            con.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    if(checkForChars(con.getText().toString())){
                                        return;
                                    }
                                    if (!con.getText().toString().equals("")) {
                                        character.setAbilityScore(2, Integer.parseInt(con.getText().toString()));
                                    }
                                }
                            });
                            intelligence.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    if(checkForChars(intelligence.getText().toString())){
                                        return;
                                    }
                                    if (!intelligence.getText().toString().equals("")) {
                                        character.setAbilityScore(3, Integer.parseInt(intelligence.getText().toString()));
                                    }
                                }
                            });
                            wis.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    if(checkForChars(wis.getText().toString())){
                                        return;
                                    }
                                    if (!wis.getText().toString().equals("")) {
                                        character.setAbilityScore(4, Integer.parseInt(wis.getText().toString()));
                                    }
                                }
                            });
                            cha.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    if(checkForChars(cha.getText().toString())){
                                        return;
                                    }
                                    if (!cha.getText().toString().equals("")) {
                                        character.setAbilityScore(5, Integer.parseInt(cha.getText().toString()));
                                    }
                                }
                            });
                        }
                        break;

                    }

                }
            }
            break;
            case 2:
                break;
            case 3:
                break;
            case 4: {
                final ContentValues values = new ContentValues();
                CharacterBaseHelper helper = new CharacterBaseHelper(getContext());
                final SQLiteDatabase characterDataBase = helper.getReadableDatabase();
                final ImageButton save = view.findViewById(R.id.save_button);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleExceptions(0, save);

                        //Inserting values into the Database

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


                        //puts all the values into a new row


                    }
                });
                final Button delete = view.findViewById(R.id.deleteButtonView);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        characterDataBase.delete(CharacterDB.CharacterTable.CHARACTER_TABLE, TIME + " = " + character.getCreationTime(), null);
                        character.setCreationTime("");
                    }
                });
                if(!character.isDeleteable()){
                    delete.setVisibility(View.INVISIBLE);
                }
                break;
            }
        }
    }
    //Ability Score algorithms//////////////////////////////////////////////////////
    private void pointBuyAdd(final TextView stat, final TextView pointBuy){

        int currentAbilityPoint = Integer.parseInt(stat.getText().toString());
        int currentBuyPoint = Integer.parseInt(pointBuy.getText().toString());

        if (currentAbilityPoint >= POINT_BUY_MIDDLE && !(currentAbilityPoint >= POINT_BUY_MAX || currentBuyPoint < 2)) {
            currentAbilityPoint++;
            stat.setText(Integer.toString(currentAbilityPoint));
            currentBuyPoint = currentBuyPoint - 2;
            pointBuy.setText(Integer.toString(currentBuyPoint));

        } else if (!(currentBuyPoint < 1) && (currentAbilityPoint < POINT_BUY_MAX)) {
            currentAbilityPoint++;
            stat.setText(Integer.toString(currentAbilityPoint));
            currentBuyPoint--;
            pointBuy.setText(Integer.toString(currentBuyPoint));
        }
    }
    private void pointBuyMin(final TextView stat, final TextView pointBuy){
        int currentAbilityPoint = Integer.parseInt(stat.getText().toString());
        int currentBuyPoint = Integer.parseInt(pointBuy.getText().toString());

        if (currentAbilityPoint > POINT_BUY_MIDDLE ) {
            currentAbilityPoint--;
            stat.setText(Integer.toString(currentAbilityPoint));
            currentBuyPoint = currentBuyPoint + 2;
            pointBuy.setText(Integer.toString(currentBuyPoint));

        } else if (!(currentAbilityPoint <= POINT_BUY_MIN)) {
            currentAbilityPoint--;
            stat.setText(Integer.toString(currentAbilityPoint));
            currentBuyPoint++;
            pointBuy.setText(Integer.toString(currentBuyPoint));
        }
    }

    private void rollSetAbility(final Button ability, final Button score) {
        if (score.getText().equals("")) {
            score.setText(ability.getText());
            ability.setText("");
        } else {
            ability.setText(score.getText());
            score.setText("");
        }
    }

    private int findFirstEmpty(final Button[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getText() == null || buttons[i].getText().equals("")) {
                return i;
            }
        }
        return -1;
    }
    private boolean checkForChars(String check){
        for(int i = 0; i < check.length(); i++){
            if(!Character.isDigit(check.charAt(i))){
                return true;
            }
        }
        return false;
    }


    //Ability Score algorithms//////////////////////////////////////////////////////
}