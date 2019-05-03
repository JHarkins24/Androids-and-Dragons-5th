package com.version2.swordsandsorcery.Database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import com.version2.swordsandsorcery.Database.CharacterDB.CharacterTable;
import static com.version2.swordsandsorcery.Database.CharacterDB.CharacterTable.*;

public class CharacterBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "characterDB.db";

    public CharacterBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + CharacterTable.CHARACTER_TABLE + "(" + " _id integer primary key autoincrement, "

                 + CharacterTable.CharactersColumns.NAME
                + "," + CharacterTable.CharactersColumns.CLASS_NAME
                + "," + CharacterTable.CharactersColumns.RACE
                + "," + CharacterTable.CharactersColumns.BACKGROUND
                + "," + CharacterTable.CharactersColumns.LVL
                + "," + CharacterTable.CharactersColumns.ABILITY_SCORES
                + "," + CharacterTable.CharactersColumns.ITEMS
                + "," + CharacterTable.CharactersColumns.SPELLS
                + "," + CharacterTable.CharactersColumns.FEATS
                + "," + CharacterTable.CharactersColumns.EXP
                + "," + CharacterTable.CharactersColumns.HITPOINTS
                + "," + CharacterTable.CharactersColumns.SKILLS_PROFICIENCIES
                + "," + CharacterTable.CharactersColumns.MONEY
                + "," + CharacterTable.CharactersColumns.ALIGNMENT
                + "," + CharacterTable.CharactersColumns.ARMOR_CLASS
                + "," + CharacterTable.CharactersColumns.SAVING_THROWS
                + "," + CharacterTable.CharactersColumns.LANGUAGES
                + "," + CharacterTable.CharactersColumns.EQUIPMENT
                + "," + CharacterTable.CharactersColumns.SPEED
                + "," + CharacterTable.CharactersColumns.INITIATIVE
                + "," + CharacterTable.CharactersColumns.HIT_DICE
                + "," +CharacterTable.CharactersColumns.TIME + ")");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("Drop Table if Exists " + CHARACTER_TABLE);
        onCreate(db);
    }

}
