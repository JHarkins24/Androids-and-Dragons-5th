
package com.version2.swordsandsorcery;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.version2.swordsandsorcery.Database.CharacterBaseHelper;
import com.version2.swordsandsorcery.Database.CharacterDB;

import org.apache.pdfbox.pdfparser.PDFParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Calendar;

import static com.version2.swordsandsorcery.Database.CharacterDB.CharacterTable.CharactersColumns.NAME;
import static com.version2.swordsandsorcery.Database.CharacterDB.CharacterTable.CharactersColumns.TIME;

public class characterCreationOverview extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    CharacterDB character;
    Intent pdfIntent = null;
    boolean permissionChecked = false;
    boolean permissionGranted = true;
    File pdf = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_character_creation_overview);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        Intent intent = getIntent();
        character = (CharacterDB)intent.getSerializableExtra("character");
        viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter adapter2 = new ViewPagerAdapter(character, getSupportFragmentManager());
        viewPager.setAdapter(adapter2);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.actionbar1, menu);
        MenuItem helpButton = menu.findItem(R.id.tlbHelp);
        MenuItem deleteButton = menu.findItem(R.id.t1bTrash);
        deleteButton.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        requestPermissions();
        if (id == R.id.tlbHelp)
        {
            startActivity(new Intent(characterCreationOverview.this, help.class));
        }
        if(id == R.id.tlbSave){
            final ContentValues values = new ContentValues();
            CharacterBaseHelper helper = new CharacterBaseHelper(this);
            final SQLiteDatabase characterDataBase = helper.getReadableDatabase();
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
            finish();

        }
        if(id == R.id.t1bPDF){
            //todo: SPENCER CODE HERE
            handleExceptions(0);
        }

        return super.onOptionsItemSelected(item);
    }
    //Pdf methods//////////////////////////////////////////////////
    private InputStream getPdf()throws NullPointerException, IOException{
        AssetManager assetManager = getAssets();
        return assetManager.open("currentVersion");
    }

    private File getNewFile(String fileName){
        //return new File(Environment.getExternalStorageDirectory().getPath() + "/" + fileName);
        //return new File(this.getContext().getFilesDir(), fileName);
        pdf = new File(getPublicAlbumStorageDir(""), fileName);
        pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(Uri.fromFile(pdf), "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        return pdf;
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


    private void handleExceptions(int i){
        PDFParser.class.getCanonicalName();
        if(!isExternalStorageWritable()){
            System.err.println("External storage is not writable");
            return;
        }

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
            startActivity(pdfIntent);
        }catch (IOException ioe){
            System.err.println(ioe.getMessage());
        }catch (NullPointerException npe){
            System.err.println(npe.getMessage());
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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
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
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        int currentChar = ',';
        while (currentChar != -1){
            if((currentChar = oldFile.read()) == ','){
                if((currentChar = oldFile.read()) == ','){
                    //todo fix breaks
                    int temp;
                    switch ((temp = oldFile.read())){
                        case '*':
                            makeSwitch(oldFile, fileOutputStream, character);
                            break;
                        case '1':
                            fillString(fileOutputStream, oldFile, Integer.toString(character.getAbilityScore(0)));
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
                            fillString(fileOutputStream, oldFile, "");
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

    private void makeSwitch(InputStream file, FileOutputStream fileOutputStream, CharacterDB character) throws IOException{
        int symbol = file.read();
        fileOutputStream.write(' ');
        switch (symbol){
            case '1':
                fillString(fileOutputStream, file, character.getName());
                break;
            case '2':
                fillString(fileOutputStream, file, character.getClassName() + " " + character.getLvl());
                break;
            case '3':
                fillString(fileOutputStream, file, character.getBackground());
                break;
            case '4':
                fillString(fileOutputStream, file, "");
                break;
            case '5':
                fillString(fileOutputStream, file, character.getRace());
                break;
            case '6':
                fillString(fileOutputStream, file, "");
                break;
                default:
                    fillString(fileOutputStream, file, "");
        }
    }



    private void makeUsingPdfBox()throws IOException {

    }

    //Pdf methods//////////////////////////////////////////////////
}