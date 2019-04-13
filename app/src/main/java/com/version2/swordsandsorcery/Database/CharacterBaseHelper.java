package com.version2.swordsandsorcery.Database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import com.version2.swordsandsorcery.Database.CharacterDB.CharacterTable;

public class CharacterBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "characterDB.db";

    public CharacterBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + CharacterTable.TABLE_NAME + "(" + " _id integer primary key autoincrement, "
                + CharacterTable.Cols.NAME
                + "," + CharacterTable.Cols.CLASS_NAME
                + "," + CharacterTable.Cols.RACE
                + "," + CharacterTable.Cols.BACKGROUND
                + "," + CharacterTable.Cols.LVL
                + "," + CharacterTable.Cols.ABILITY_SCORES
                + "," + CharacterTable.Cols.ITEMS
                + "," + CharacterTable.Cols.SPELLS
                + "," + CharacterTable.Cols.FEATS
                + "," + CharacterTable.Cols.EXP
                + "," + CharacterTable.Cols.HITPOINTS
                + "," + CharacterTable.Cols.SKILLS_PROFICIENCIES
                + "," + CharacterTable.Cols.MONEY
                + "," + CharacterTable.Cols.ALIGNMENT
                + "," + CharacterTable.Cols.ARMOR_CLASS
                + "," + CharacterTable.Cols.SAVING_THROWS
                + "," + CharacterTable.Cols.LANGUAGES
                + "," + CharacterTable.Cols.EQUIPMENT
                + "," + CharacterTable.Cols.SPEED
                + "," + CharacterTable.Cols.INITIATIVE
                + "," + CharacterTable.Cols.HIT_DICE + ")");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
