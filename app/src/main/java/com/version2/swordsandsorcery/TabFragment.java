package com.version2.swordsandsorcery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
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

public class TabFragment extends Fragment {
    private SharedPreferences levelPreferences;
    ArrayList<String> equip;
    private SharedPreferences level2Preferences;
    private SharedPreferences abilityScorePreferences;
    private SQLiteDatabase characterDatabase;
    int position;
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
            case 0:
                // spinner is implemented dynamically in the java activity file.
                final Spinner lvlSpinner = (Spinner) view.findViewById(R.id.lvl_spinner);
                LinkedList<String> items = new LinkedList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"));
                levelPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
                String level = levelPreferences.getString("level", "");
                if(items.contains(level))
                {
                    items.remove(level);
                    items.addFirst(level);
                }
                // create arrayAdapter using the string array and a default
                ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
                level2Preferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
                String level2 = levelPreferences.getString("level2", "");
                if(level != null)
                {
                    int spinnerPosition = adapter.getPosition(level);
                    lvlSpinner.setSelection(spinnerPosition);

                }

                lvlSpinner.setAdapter(adapter);
                lvlSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                            {
                                SharedPreferences.Editor editor = level2Preferences.edit();
                                editor.putString("level",lvlSpinner.getSelectedItem().toString());
                                Log.v("level", (String) parent.getItemAtPosition(position));
                                editor.apply();
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

                        final TextView rollType = view.findViewById(R.id.rollType);
                    abilityScorePreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
                    String ability = abilityScorePreferences.getString("abilityScore", "");
                    if (ability != null) {
                        switch(ability)
                        {
                            case"Point Buy":
                                break;
                            case"Manual":
                                //Write Manual algorithm
                                break;
                            case"Roll":
                                //Write Roll algorithm that calls Roll in CharacaterDB

                                break;
                        }

                }
                break;
            case 3:
                final Button aasimar = view.findViewById(R.id.aasimar);
                aasimar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button bugbear = view.findViewById(R.id.bugbear);
                bugbear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button dragonborn = view.findViewById(R.id.dragonborn);
                dragonborn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button dwarf = view.findViewById(R.id.dwarf);
                dwarf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button elf = view.findViewById(R.id.elf);
                elf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button firbolg = view.findViewById(R.id.firbolg);
                firbolg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button genasi = view.findViewById(R.id.genasi);
                genasi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button gith = view.findViewById(R.id.gith);
                gith.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button gnome = view.findViewById(R.id.gnome);
                gnome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button goblin = view.findViewById(R.id.goblin);
                goblin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button goliath = view.findViewById(R.id.goliath);
                goliath.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button hobgoblin = view.findViewById(R.id.hobgoblin);
                hobgoblin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button halfElf = view.findViewById(R.id.halfelf);
                halfElf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button halfling = view.findViewById(R.id.halfling);
                halfling.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button halfOrc = view.findViewById(R.id.halforc);
                halfOrc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button human = view.findViewById(R.id.human);
                human.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button kenku = view.findViewById(R.id.kenku);
                kenku.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button kobold = view.findViewById(R.id.kobold);
                kobold.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button lizardfolk = view.findViewById(R.id.lizardfolk);
                lizardfolk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button orc = view.findViewById(R.id.orc);
                orc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button tabaxi = view.findViewById(R.id.tabaxi);
                tabaxi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button tiefling = view.findViewById(R.id.tiefling);
                tiefling.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button tortle = view.findViewById(R.id.tortle);
                tortle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button triton = view.findViewById(R.id.triton);
                triton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button yuantiPureblood = view.findViewById(R.id.yuantipureblood);
                yuantiPureblood.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                break;
            case 4:
                final Button acolyte = view.findViewById(R.id.acolyte);
                acolyte.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button charlatan = view.findViewById(R.id.charlatan);
                charlatan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button criminal = view.findViewById(R.id.criminal);
                criminal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button entertainer = view.findViewById(R.id.entertainer);
                entertainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button folkHero = view.findViewById(R.id.folkHero);
                folkHero.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button guildArtisan = view.findViewById(R.id.guildArtisan);
                guildArtisan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button hermit = view.findViewById(R.id.hermit);
                hermit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button noble = view.findViewById(R.id.noble);
                noble.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button outlander = view.findViewById(R.id.outlander);
                outlander.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button sage = view.findViewById(R.id.sage);
                sage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button sailor = view.findViewById(R.id.sailor);
                sailor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button soldier = view.findViewById(R.id.soldier);
                soldier.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                final Button urchin = view.findViewById(R.id.urchin);
                urchin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
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
}