package com.version2.swordsandsorcery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemSelectedListener;
import com.version2.swordsandsorcery.Database.CharacterBaseHelper;
import com.version2.swordsandsorcery.Database.CharacterDB;
import android.database.Cursor;
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

public class TabFragment extends Fragment {
    private SharedPreferences levelPreferences;
    ArrayList<String> equip;
    private SharedPreferences level2Preferences;
    private SharedPreferences abilityScorePreferences;
    private SQLiteDatabase characterDatabase;
    int position;
    final int POINT_BUY_MAX = 15;
    final int POINT_BUY_MIN = 8;
    final int POINT_BUY_MIDDLE = 13;
    TextView textView;
    short bla = 0;
    CharacterDB character;
    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        TabFragment tabFragment = new TabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;

    }

    private InputStream getPdf()throws NullPointerException, IOException{
        AssetManager assetManager = this.getContext().getAssets();
        return assetManager.open("currentVersion");
    }

    private File getNewFile(String fileName){
        //return new File(Environment.getExternalStorageDirectory().getPath() + "/" + fileName);
        return new File(this.getContext().getFilesDir(), fileName);
    }

    private void handleExceptions(int i, ImageButton save){
        PDFParser.class.getCanonicalName();

        try {
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
                save.setVisibility(View.INVISIBLE);
        }catch (NullPointerException npe){
                save.setVisibility(View.INVISIBLE);
            }
    }

    private void printBytes(byte[] input, FileOutputStream outputStream) throws IOException{
        for (byte symbol : input) {
            outputStream.write((char)symbol);
        }
    }

    private void fillString(FileOutputStream fileOutputStream, InputStream inputStream, String string, int total, int number)throws IOException{
        int counter = 0;
        for (int i = 0; i < string.length() && counter < total - 1; i++) {
            inputStream.read();
            fileOutputStream.write((string.charAt(counter)+"").getBytes());
            counter++;
        }

        counter = total - counter - 3;
        while (counter != 0){
            inputStream.read();
            fileOutputStream.write(" ".getBytes());
            counter--;
        }
        for (int i = 0; i < 3; i++) {
            fileOutputStream.write(" ".getBytes());
        }
    }

    private void makePdf() throws IOException{

        InputStream oldFile = getPdf();
        File file = getNewFile("test2.pdf");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        int currentChar = ',';
        while (currentChar != -1){
            if((currentChar = oldFile.read()) == ','){
                if((currentChar = oldFile.read()) == ','){
                    //todo fix breaks
                    int temp;
                    switch ((temp = oldFile.read())){
                        case '1':
                            fillString(fileOutputStream, oldFile, character.getName(), 15, 1);
                            break;
                        case '2':
                            fillString(fileOutputStream, oldFile, "class", 9, 2);
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

    private File copyFile(int i)throws IOException{
        Context context = this.getContext();
        if(context == null)
            return null;
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open("currentVersion");
        File file = getNewFile(i==0?character.getName():"copy");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        int currentChar;
        while ((currentChar = inputStream.read()) != -1){
            fileOutputStream.write(currentChar);
        }
        fileOutputStream.close();
        return file;
    }

    private PDDocument getUsingPdfBox()throws IOException{
        File pdf;
        PDDocument document = null;
        if((pdf = copyFile(1)) != null)
            document = PDDocument.load(pdf);
        return document;
    }

    private void makeUsingPdfBox()throws IOException{
        PDDocument from = getUsingPdfBox();

        PDDocumentCatalog catalog = from.getDocumentCatalog();
        PDAcroForm form = catalog.getAcroForm();
        List itt = form.getFields();
        for (Object field : itt){
            System.out.println(((PDField)field).getFullyQualifiedName());
        }
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
                abilityScorePreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
                String ability = abilityScorePreferences.getString("abilityScore", "");
                switch(ability){
                    case"Point Buy":
                        return inflater.inflate(R.layout.fragment_character_creation_ability_scores_point_buy,container, false);
                    case "Roll":
                        return inflater.inflate(R.layout.fragment_character_creation_ability_scores_roll,container,false);
                    case "Manual":
                        return inflater.inflate(R.layout.fragment_character_creation_ability_scores, container, false);
                }
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
        equip = new ArrayList<>();
        character = new CharacterDB();

        switch (position){
            case 0: {
                // spinner is implemented dynamically in the java activity file.
                final Spinner lvlSpinner = (Spinner) view.findViewById(R.id.lvl_spinner);
                LinkedList<String> items = new LinkedList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"));
                levelPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
                String level = levelPreferences.getString("level", "");
                if (items.contains(level)) {
                    items.remove(level);
                    items.addFirst(level);
                }
                // create arrayAdapter using the string array and a default
                ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
                level2Preferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
                String level2 = levelPreferences.getString("level2", "");
                if (level != null) {
                    int spinnerPosition = adapter.getPosition(level);
                    lvlSpinner.setSelection(spinnerPosition);

                }

                lvlSpinner.setAdapter(adapter);
                lvlSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        SharedPreferences.Editor editor = level2Preferences.edit();
                        editor.putString("level", lvlSpinner.getSelectedItem().toString());
                        Log.v("level", (String) parent.getItemAtPosition(position));
                        editor.apply();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
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
                if (bla == 0) {
                    fighter.setVisibility(View.INVISIBLE);
                } else {
                    fighter.setVisibility(View.VISIBLE);
                }


                fighter.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.v("fighterSelect", (String) parent.getItemAtPosition(position));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
                break;

            case 1: {
                final Button artificer = view.findViewById(R.id.artificer);
                artificer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setClassName("artificier");
                    }
                });
                final Button barbarian = view.findViewById(R.id.barbarian);
                barbarian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setClassName("barbarian");
                    }
                });
                final Button bard = view.findViewById(R.id.bard);
                bard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setClassName("bard");
                    }
                });
                final Button cleric = view.findViewById(R.id.cleric);
                cleric.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setClassName("cleric");
                    }
                });
                final Button druid = view.findViewById(R.id.druid);
                druid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setClassName("druid");
                    }
                });
                final Button fighterClass = view.findViewById(R.id.fighter);
                fighterClass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setClassName("fighter");
                    }
                });
                final Button monk = view.findViewById(R.id.monk);
                monk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setClassName("monk");
                    }
                });

                final Button mystic = view.findViewById(R.id.mystic);
                mystic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setClassName("mystic");
                    }
                });

                final Button paladin = view.findViewById(R.id.paladin);
                paladin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setClassName("paladin");
                    }
                });

                final Button ranger = view.findViewById(R.id.ranger);
                ranger.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setClassName("ranger");
                    }
                });

                final Button rogue = view.findViewById(R.id.rogue);
                rogue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setClassName("rogue");
                    }
                });

                final Button sorcerer = view.findViewById(R.id.sorcerer);
                sorcerer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setClassName("sorcerer");
                    }
                });

                final Button warlock = view.findViewById(R.id.warlock);
                warlock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setClassName("warlock");
                    }
                });

                final Button wizard = view.findViewById(R.id.wizard);
                wizard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setClassName("wizard");
                    }
                });
            }
                break;
            case 2: {
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
                        case "Manual":
                            //Write Manual algorithm
                            final EditText str = view.findViewById(R.id.strength);
                            final EditText dex = view.findViewById(R.id.dexterity);
                            final EditText con = view.findViewById(R.id.constitution);
                            final EditText intelligence = view.findViewById(R.id.intelligence);
                            final EditText wis = view.findViewById(R.id.wisdom);
                            final EditText cha = view.findViewById(R.id.charisma);
                            final Button save = view.findViewById(R.id.saveManual);


                            save.setOnClickListener(new View.OnClickListener(){
                                public void onClick(View v){
                                    character.setAbilityScore(0,Integer.parseInt(str.getText().toString()));
                                    character.setAbilityScore(1,Integer.parseInt(dex.getText().toString()));
                                    character.setAbilityScore(2,Integer.parseInt(con.getText().toString()));
                                    character.setAbilityScore(3,Integer.parseInt(intelligence.getText().toString()));
                                    character.setAbilityScore(4,Integer.parseInt(wis.getText().toString()));
                                    character.setAbilityScore(5,Integer.parseInt(cha.getText().toString()));
                                }
                            });
                            break;
                        case "Roll":
                            //Write Roll algorithm that calls Roll in CharacterDB

                            break;
                    }

                }
            }
            break;
            case 3: {
                final Button aasimar = view.findViewById(R.id.aasimar);
                aasimar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("assimar");
                    }
                });

                final Button bugbear = view.findViewById(R.id.bugbear);
                bugbear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("bugbear");
                    }
                });

                final Button dragonborn = view.findViewById(R.id.dragonborn);
                dragonborn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("dragonborn");
                    }
                });

                final Button dwarf = view.findViewById(R.id.dwarf);
                dwarf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("dwarf");
                    }
                });

                final Button elf = view.findViewById(R.id.elf);
                elf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("elf");
                    }
                });

                final Button firbolg = view.findViewById(R.id.firbolg);
                firbolg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("firbolg");
                    }
                });

                final Button genasi = view.findViewById(R.id.genasi);
                genasi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("genasi");
                    }
                });

                final Button gith = view.findViewById(R.id.gith);
                gith.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("gith");
                    }
                });

                final Button gnome = view.findViewById(R.id.gnome);
                gnome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("gnome");
                    }
                });

                final Button goblin = view.findViewById(R.id.goblin);
                goblin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("goblin");
                    }
                });

                final Button goliath = view.findViewById(R.id.goliath);
                goliath.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("goliath");
                    }
                });

                final Button hobgoblin = view.findViewById(R.id.hobgoblin);
                hobgoblin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("hobgoblin");
                    }
                });

                final Button halfElf = view.findViewById(R.id.halfelf);
                halfElf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("halfelf");
                    }
                });

                final Button halfling = view.findViewById(R.id.halfling);
                halfling.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("halfling");
                    }
                });

                final Button halfOrc = view.findViewById(R.id.halforc);
                halfOrc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("halfOrc");
                    }
                });

                final Button human = view.findViewById(R.id.human);
                human.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("human");
                    }
                });

                final Button kenku = view.findViewById(R.id.kenku);
                kenku.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("kenku");
                    }
                });

                final Button kobold = view.findViewById(R.id.kobold);
                kobold.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("kobold");
                    }
                });

                final Button lizardfolk = view.findViewById(R.id.lizardfolk);
                lizardfolk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("lizardfolk");
                    }
                });

                final Button orc = view.findViewById(R.id.orc);
                orc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("orc");
                    }
                });

                final Button tabaxi = view.findViewById(R.id.tabaxi);
                tabaxi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("tabaxi");
                    }
                });

                final Button tiefling = view.findViewById(R.id.tiefling);
                tiefling.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("tiefling");
                    }
                });

                final Button tortle = view.findViewById(R.id.tortle);
                tortle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("tortle");
                    }
                });

                final Button triton = view.findViewById(R.id.triton);
                triton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("triton");
                    }
                });

                final Button yuantiPureblood = view.findViewById(R.id.yuantipureblood);
                yuantiPureblood.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setRace("yuantiPureBlood");
                    }
                });
                break;
            }
            case 4: {
                final Button acolyte = view.findViewById(R.id.acolyte);
                acolyte.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setBackground("acolyte");
                    }
                });

                final Button charlatan = view.findViewById(R.id.charlatan);
                charlatan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setBackground("charlatan");
                    }
                });

                final Button criminal = view.findViewById(R.id.criminal);
                criminal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setBackground("criminal");
                    }
                });

                final Button entertainer = view.findViewById(R.id.entertainer);
                entertainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setBackground("entertainer");
                    }
                });

                final Button folkHero = view.findViewById(R.id.folkHero);
                folkHero.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setBackground("folkHero");
                    }
                });

                final Button guildArtisan = view.findViewById(R.id.guildArtisan);
                guildArtisan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setBackground("guildArtisan");
                    }
                });

                final Button hermit = view.findViewById(R.id.hermit);
                hermit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setBackground("hermit");
                    }
                });

                final Button noble = view.findViewById(R.id.noble);
                noble.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setBackground("noble");
                    }
                });

                final Button outlander = view.findViewById(R.id.outlander);
                outlander.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setBackground("outlander");
                    }
                });

                final Button sage = view.findViewById(R.id.sage);
                sage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setBackground("sage");
                    }
                });

                final Button sailor = view.findViewById(R.id.sailor);
                sailor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setBackground("sailor");
                    }
                });

                final Button soldier = view.findViewById(R.id.soldier);
                soldier.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setBackground("soldier");
                    }
                });

                final Button urchin = view.findViewById(R.id.urchin);
                urchin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        character.setBackground("urchin");
                    }
                });
            }
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                final ImageButton save = view.findViewById(R.id.save_button);
                save.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        handleExceptions(0, save);

                        CharacterBaseHelper helper = new CharacterBaseHelper(getContext());
                        SQLiteDatabase characterDataBase = helper.getReadableDatabase();
                        ContentValues values = new ContentValues();

                        //Inserting test values into the Database

                        values.put(CharacterDB.CharacterTable.CharactersColumns.NAME, character.getName());
                        values.put(CharacterDB.CharacterTable.CharactersColumns.CLASS_NAME, character.getClassName());
                        values.put(CharacterDB.CharacterTable.CharactersColumns.RACE, character.getRace());

                        //puts all the values into a new row

                        characterDataBase.insert(CharacterDB.CharacterTable.TABLE_NAME, null , values);

                        // all rows are gotten

                        Cursor AllCharacter = characterDataBase.query(CharacterDB.CharacterTable.TABLE_NAME,null,"1",null,null,null,null);
                        while (AllCharacter.moveToNext()) {
                                AllCharacter.getString(AllCharacter.getColumnIndex(CharacterDB.CharacterTable.CharactersColumns.NAME));
                        }
                        AllCharacter.close();
                    }
                });
                break;
        }
    }
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
}