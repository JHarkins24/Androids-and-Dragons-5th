package com.version2.swordsandsorcery;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.version2.swordsandsorcery.Database.CharacterDB;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private String[] title = {"Ov", "As", "It", "Sp", "Vi"};
    private CharacterDB character;
    public ViewPagerAdapter(CharacterDB newCharacter, FragmentManager manager) {
        super(manager);
        this.character = newCharacter;
    }

    @Override
    public Fragment getItem(int position) {
        return TabFragment.getInstance(character, position);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}