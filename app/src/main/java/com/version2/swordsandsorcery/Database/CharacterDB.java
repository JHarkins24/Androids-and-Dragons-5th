package com.version2.swordsandsorcery.Database;
import java.util.Scanner;
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
import java.util.ArrayList;
import java.lang.Object;


public class CharacterDB {

    private String name;
    private String className;
    private String race;
    private String background;
    private int lvl;
    private int[] abilityScores;
    private ArrayList<String> items;
    private ArrayList<String> spells;
    private ArrayList<String> feats;
    private int exp;
    private int[] hitpoints;
    private int[] skillProficiencies;
    private int[] money;
    private String alignment;
    private int armorClass;
    private int[] savingThrows;
    private ArrayList<String> languages;
    private ArrayList<String> equipment;
    private int speed;
    private int initiative;
    private int hitDice;

    public CharacterDB() {
        this.name = "";
        this.className = "";
        this.race = "";
        this.background = "";
        this.lvl = 0;
        this.abilityScores = new int[6];
        this.items = new ArrayList<String>();
        this.spells = new ArrayList<String>();
        this.feats = new ArrayList<String>();
        this.exp = 0;
        this.hitpoints = new int[3];
        this.skillProficiencies = new int[18];
        this.money = new int[5];
        this.alignment = "NN";
        this.armorClass = 0;
        this.savingThrows = new int[6];
        this.languages = new ArrayList<String>();
        this.equipment = new ArrayList<String>();
        this.speed = 0;
        this.initiative = 0;
        this.hitDice = 0;
    }
    public int abilityModifier(int index)
    {
        return  (abilityScores[index] - 10) / 2;
    }
    public int[] rollAbilityScores(){
        int[] abilityScores = new int[7];
        for(int i = 0; i < 7; i++){
            ArrayList<Integer> rolls = new ArrayList<Integer>(4);
            for(int j = 0; j < 4; j++){
                rolls.add(rollD6());
            }
            for(int j = 0; j < 3;j++){
                abilityScores[i] += Math.max(rolls.get(0),rolls.get(1));
                if(rolls.get(0) < rolls.get(1)){
                    rolls.remove(1);
                }
                else{rolls.remove(0);}
            }
        }
        return abilityScores;
    }
    public int rollD6(){
        return (int)(Math.random()*6)+1;
    }
    public int rollD8(){
        return (int)(Math.random()*8)+1;
    }
    public int rollD10(){
        return (int)(Math.random()*10)+1;
    }
    public int rollD12(){
        return (int)(Math.random()*12)+1;
    }
    public int getExp() {
        return exp;
    }
    public void setExp(int exp) {
        this.exp = exp;
    }
    public int[] getHitpoints() {
        return hitpoints;
    }
    public void setHitpoints(int[] hitpoints) {
        this.hitpoints = hitpoints;
    }
    public void setBaseHP(int HP){
        hitpoints[0] = HP;
    }
    public int getBaseHP(){
        return hitpoints[0];
    }
    public void setCurrentHP(int HP){
        hitpoints[1] = HP;
    }
    public int getCurrentHP(){
        return hitpoints[1];
    }
    public void setBonusHP(int HP){
        hitpoints[2] = HP;
    }
    public int getBonusHP(){
        return hitpoints[2];
    }
    public int[] getSkillProficiencies() {
        return skillProficiencies;
    }
    public void setSkillProficiencies(int[] skillProficiencies) {
        this.skillProficiencies = skillProficiencies;
    }
    public int[] getMoney() {
        return money;
    }
    public int getMoney(int index){
        return money[index];
    }
    public void setMoney(int[] money) {
        this.money = money;
    }
    public void setMoney(int index, int amount) {
        this.money[index] = amount;
    }
    public String getAlignment() {
        return alignment;
    }
    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }
    public int getArmorClass() {
        return armorClass;
    }
    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }
    public int[] getSavingThrows() {
        return savingThrows;
    }
    public int getSavingThrow(int index){
        return savingThrows[index];
    }
    public void setSavingThrows(int[] savingThrows) {
        this.savingThrows = savingThrows;
    }
    public void setSavingThrow(int index){
        savingThrows[index] = 1;
    }
    public ArrayList<String> getLanguages() {
        return languages;
    }
    public boolean knowsLanguage(String language){
        return languages.contains(language);
    }
    public void addLanguage(String language){
        languages.add(language);
    }
    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }
    public boolean removeLanguage(String language){
        return languages.remove(language);
    }
    public ArrayList<String> getEquipment() {
        return equipment;
    }
    public void addEquipment(String equip){
        this.equipment.add(equip);
    }
    public boolean removeEquipment(String equip){
        return equipment.remove(equip);
    }
    public void setEquipment(ArrayList<String> equipment) {
        this.equipment = equipment;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getInitiative() {
        return initiative;
    }
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }
    public int getHitDice() {
        return hitDice;
    }
    public void setHitDice(int hitDice) {
        this.hitDice = hitDice;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getRace() {
        return race;
    }
    public void setRace(String race) {
        this.race = race;
    }
    public String getBackground() {
        return background;
    }
    public void setBackground(String background) {
        this.background = background;
    }
    public int getLvl() {
        return lvl;
    }
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
    public int[] getAbilityScores() {
        return abilityScores;
    }
    public void setAbilityScores(int[] abilityScores) {
        this.abilityScores = abilityScores;
    }
    public void setAbilityScore(int index, int score){
        abilityScores[index] = score;
    }
    public int getAbilityScore(int index){
        return abilityScores[index];
    }
    public ArrayList<String> getItems() {
        return items;
    }
    public void setItems(ArrayList<String> items) {
        this.items = items;
    }
    public void addItem(String item){
        items.add(item);
    }
    public boolean hasItem(String item){
        return items.contains(item);
    }
    public boolean removeItem(String item){
        return items.remove(item);
    }
    public ArrayList<String> getSpells() {
        return spells;
    }
    public void addSpell(String spell){
        spells.add(spell);
    }
    public boolean hasSpell(String spell){
        return spells.contains(spell);
    }
    public void setSpells(ArrayList<String> spells) {
        this.spells = spells;
    }
    public boolean removeSpell(String spell){
        return spells.remove(spell);
    }
    public ArrayList<String> getFeats() {
        return feats;
    }
    public void setFeats(ArrayList<String> feats) {
        this.feats = feats;
    }
    public void addFeat(String feat){
        feats.add(feat);
    }
    public boolean hasFeat(String feat){
        return feats.contains(feat);
    }
    public boolean removeFeat(String feat){
        return feats.remove(feat);
    }
    public int proficiency()
    {
        int profBonus;

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
    public static final class CharacterTable
    {
        public static final String TABLE_NAME = "character";

        public static final class Cols
        {

            public static final String CLASS_NAME = "className";
            public static final String RACE = "race";
            public static final String BACKGROUND = "background";
            public static final String LVL = "lvl";
            public static final String  ABILITY_SCORES = "abilityScores";
            public static final String ITEMS = "items";
            public static final String SPELLS = "spells";
            public static final String FEATS = "feats";
            public static final String EXP = "exp";
            public static final String HITPOINTS = "hitpoints";
            public static final String SKILLS_PROFICIENCIES = "skillProficiencies";
            public static final String MONEY = "money";
            public static final String ALIGNMENT = "alignment";
            public static final String ARMOR_CLASS = "armorClass";
            public static final String SAVING_THROWS = "savingThrows";
            public static final String LANGUAGES = "languages";
            public static final String  EQUIPMENT = "equipment";
            public static final String SPEED = "speed";
            public static final String INITIATIVE = "initiative";
            public static final String HIT_DICE = "hitDice";
            public static final String NAME = "name";

        }
    }
}
