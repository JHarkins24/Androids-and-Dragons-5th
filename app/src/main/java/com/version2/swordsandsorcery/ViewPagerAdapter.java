package com.version2.swordsandsorcery;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.version2.swordsandsorcery.Database.CharacterDB;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private String title[] = {"Cl", "AS", "Ra", "Ba", "It", "Sp", "Vi"};
    final CharacterDB character = new CharacterDB();
    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return TabFragment.getInstance(position, character);
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
