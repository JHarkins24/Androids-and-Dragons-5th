package com.version2.swordsandsorcery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.Scanner;
import java.lang.Object;

public class formulaProcessor
    {
        public int getLvl()
            {
                Scanner input = new Scanner(System.in);
                int lvl = input.nextInt();
                return lvl;
            }

        public int[] abilityModifier(int[] abilityScore)
            {
                int[] abilityModifier;
                abilityModifier = new int[6];

                for (int i = 0; i < abilityScore.length; i++)
                    {
                        abilityModifier[i] = (abilityScore[i] - 10) / 2;
                    }

                return abilityModifier;
            }

        public int playerHP(int abilityMod, int hpRoll, int lvl, int otherBonus)
            {
                int playerHP = (abilityMod * lvl) + hpRoll + otherBonus;
                return playerHP;
            }

        public int proficiency(int lvl)
            {
                int profBonus;
                lvl = getLvl();

                switch (lvl)
                    {
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            profBonus = 3;
                            break;
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                            profBonus = 4;
                            break;
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                            profBonus = 5;
                            break;
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                            profBonus = 6;
                            break;
                        default:
                            profBonus = 2;
                            break;
                    }
                return profBonus;
            }
    }
