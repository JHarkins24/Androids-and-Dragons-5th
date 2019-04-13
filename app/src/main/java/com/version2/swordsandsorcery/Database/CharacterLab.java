package com.version2.swordsandsorcery.Database;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
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
public class CharacterLab {
    //Database Variables
    private List<CharacterDB> mCharacters;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    //Database Variables

    public CharacterLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new CharacterBaseHelper(mContext).getWritableDatabase();
        mCharacters = new ArrayList<>();
    }

}
