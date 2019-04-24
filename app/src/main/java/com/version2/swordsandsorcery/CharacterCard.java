package com.version2.swordsandsorcery;

public class CharacterCard
    {
        private int id, level;
        private String characterName;
        private String characterClass;
        private int characterIcon;

        public CharacterCard(int id, int level, String characterName, String characterClass, int image)
            {
                this.id = id;
                this.level = level;
                this.characterName = characterName;
                this.characterIcon = image;
                this.characterClass = characterClass;
            }

        public int getId()
            {
                return id;
            }

        public int getLevel()
            {
                return level;
            }

        public String getCharacterName()
            {
                return characterName;
            }

        public String getCharacterClass()
            {
                return characterClass;
            }

        public int getImage()
            {
                return characterIcon;
            }
    }
