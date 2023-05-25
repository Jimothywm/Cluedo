package cluedo;

import javax.swing.*;

public class CluedoEnums {
    CharacterEnum characterCard;
    WeaponEnum weaponCard;
    RoomEnum roomCard;
    CardsEnum cardType;
    Icon cardIcon; //= new ImageIcon("MRSWHITE.png");
    String cardName;

    /*public Icon getCardPicture(CharacterEnum character) {
        switch (character) {
            case MRS_WHITE:
                return mrsWhite;
        }
        return null;
    }

    public Icon getCardPicture(WeaponEnum weapon) {
        switch (weapon) {

        }
        return null;
    }

    public Icon getCardPicture(RoomEnum room) {
        switch (room) {
            case
        }
        return null;
    }*/

    //public CardsEnum getCardType() { return cardType; }

    //public CharacterEnum getCard(CardsEnum any) { return characterCard; }

    //public WeaponEnum getCard(CardsEnum any) { return weaponCard; }

    //public RoomEnum getCard(CardsEnum any) { return roomCard; }

    public Icon getCardIcon() { return cardIcon; }

    public String getCardName() { return cardName; }

    public CluedoEnums(CharacterEnum setCharacter) {
        characterCard = setCharacter;
        cardType = CardsEnum.CHARACTER;
        switch (characterCard) {
            case MRS_WHITE:
                cardIcon = new ImageIcon("MRSWHITE.png");
                cardName = "Mrs White";
                break;
            case MRS_PEACOCK:
                cardIcon = new ImageIcon("MRSPEACOCK.png");
                cardName = "Mrs Peacock";
                break;
            case MISS_SCARLETT:
                cardIcon = new ImageIcon("MRSSCARLETT.png");
                cardName = "Miss Scarlett";
                break;
            case COLONEL_MUSTARD:
                cardIcon = new ImageIcon("COLONELMUSTARD.png");
                cardName = "Colonel Mustard";
                break;
            case PROFESSOR_PLUM:
                cardIcon = new ImageIcon("PROFESSORPLUM.png");
                cardName = "Professor Plum";
                break;
            case THE_REVEREND_GREEN:
                cardIcon = new ImageIcon("THEREVERENDGREEN.png");
                cardName = "Rev Green";
                break;
        }
    }

    public CluedoEnums(WeaponEnum setWeapon) {
        weaponCard = setWeapon;
        cardType = CardsEnum.WEAPON;
        switch (weaponCard) {
            case ROPE:
                cardIcon = new ImageIcon("ROPECARD.png");
                cardName = "Rope";
                break;
            case KNIFE:
                cardIcon = new ImageIcon("DAGGERCARD.png");
                cardName = "Knife";
                break;
            case WRENCH:
                cardIcon = new ImageIcon("WRENCHCARD.png");
                cardName = "Wrench";
                break;
            case REVOLVER:
                cardIcon = new ImageIcon("REVOLVERCARD.png");
                cardName = "Revolver";
                break;
            case LEAD_PIPE:
                cardIcon = new ImageIcon("PIPECARD.png");
                cardName = "Lead Pipe";
                break;
            case CANDLESTICK:
                cardIcon = new ImageIcon("CANDLESTICKCARD.png");
                cardName = "Candlestick";
                break;
        }
    }

    public CluedoEnums(RoomEnum setRoom) {
        roomCard = setRoom;
        cardType = CardsEnum.ROOM;
        switch (roomCard) {
            case HALL:
                cardIcon = new ImageIcon("HALL.png");
                cardName = "Hall";
                break;
            case STUDY:
                cardIcon = new ImageIcon("STUDY.png");
                cardName = "Study";
                break;
            case LOUNGE:
                cardIcon = new ImageIcon("LOUNGE.png");
                cardName = "Lounge";
                break;
            case KITCHEN:
                cardIcon = new ImageIcon("KITCHEN.png");
                cardName = "Kitchen";
                break;
            case LIBRARY:
                cardIcon = new ImageIcon("LIBRARY.png");
                cardName = "Library";
                break;
            case BALL_ROOM:
                cardIcon = new ImageIcon("BALLROOM.png");
                cardName = "Ballroom";
                break;
            case DINING_ROOM:
                cardIcon = new ImageIcon("DININGROOM.png");
                cardName = "Dining Room";
                break;
            case CONSERVATORY:
                cardIcon = new ImageIcon("CONSERVATORY.png");
                cardName = "Conservatory";
                break;
            case BILLIARD_ROOM:
                cardIcon = new ImageIcon("BILLIARDROOM.png");
                cardName = "Billiard Room";
                break;
        }
    }

    public enum CardsEnum implements Card {
        CHARACTER,
        WEAPON,
        ROOM
    }

    /**
     * Represents one of the six characters in the game
     */
    public enum CharacterEnum implements Card {
        MISS_SCARLETT,
        COLONEL_MUSTARD,
        MRS_WHITE,
        THE_REVEREND_GREEN,
        MRS_PEACOCK,
        PROFESSOR_PLUM;

        public static Card getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }

        @Override
        public String toString() {
            return this.name().replaceAll("_", " ");
        }
    }

    /**
     * Represents one of the six weapons in the game
     */
    public enum WeaponEnum implements Card {
        CANDLESTICK,
        KNIFE,
        LEAD_PIPE,
        REVOLVER,
        ROPE,
        WRENCH;

        public static Card getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }

        @Override
        public String toString() {
            return this.name().replaceAll("_", " ");
        }
    }

    /**
     * Represents one of the nine rooms in the game
     */
    public enum RoomEnum implements Card {
        KITCHEN,
        BALL_ROOM,
        CONSERVATORY,
        BILLIARD_ROOM,
        LIBRARY,
        STUDY,
        HALL,
        LOUNGE,
        DINING_ROOM;

        public static Card getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }

        @Override
        public String toString() {
            return this.name().replaceAll("_", " ");
        }
    }
}
