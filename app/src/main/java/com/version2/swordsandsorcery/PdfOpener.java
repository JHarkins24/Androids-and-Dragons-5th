package com.version2.swordsandsorcery;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.version2.swordsandsorcery.Database.CharacterDB;

import org.apache.pdfbox.pdfparser.PDFParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class PdfOpener {
    File pdf = null;
    Intent pdfIntent = null;
    CharacterDB character;
    boolean permissionChecked = false;
    boolean permissionGranted = true;
    Activity activity;
    Context context;
    public PdfOpener(CharacterDB character, Activity activity, Context context){
        this.context = context;
        this.character = character;
        this.activity = activity;
    }
    private InputStream getPdf()throws NullPointerException, IOException {
        AssetManager assetManager = activity.getApplicationContext().getAssets();
        return assetManager.open("currentVersion");
    }

    private File getNewFile(String fileName){
        //return new File(Environment.getExternalStorageDirectory().getPath() + "/" + fileName);
        //return new File(this.getContext().getFilesDir(), fileName);
        pdf = new File(getPublicAlbumStorageDir(""), fileName);
        pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        pdfIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
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


   public void handleExceptions(int i, ImageButton save){
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
            context.startActivity(pdfIntent);
        }catch (IOException ioe){
            System.err.println(ioe.getMessage());
            if(save != null) {
                save.setVisibility(View.INVISIBLE);
            }
        }catch (NullPointerException npe){
            System.err.println(npe.getMessage());
            if(save != null) {
                save.setVisibility(View.INVISIBLE);
            }
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
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
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

}
