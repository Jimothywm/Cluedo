package cluedo;

import java.util.ArrayList;

public class Player {
    String character;
    /**
     * Represents name of a Character
     */
    private String characterName;
    /**
     * Unique player identifier
     */
    private int uid;
    /**
     * Unique character identifier
     */
    private int characterUID;
    /**
     * Represents name of a Player
     */
    private String playerName;
    /**
     * Represent the x position of the player.
     */
    private int posX;
    /**
     * Represent the y position of the player.
     */
    private int posY;

    /**
     * Represent if the player has already made a suggestion after the last
     * move.
     */
    private boolean suggested;
    /**
     * Represnet if the player has already rolled the die this turn
     */
    private boolean rolled;
    /**
     * Represent the corresponding Image of the Player
     */
    //private final Image image;


    int[] location; //spawn location can be stairs or specific locations for each character ** <REPLACED BY POSX POSY>
    int movePoints;
    boolean failedAccusation;

    ArrayList<CluedoEnums> cards;

    public Player(int uid, String characterName, String playerName) {
        this.uid = uid;
        this.characterName = characterName;
        initCharacterUID(characterName);
        this.playerName = playerName;

        cards = new ArrayList<>();
        location = new int[2];
        suggested = false;
        rolled = false;
        failedAccusation = false;
    }

    public ArrayList<CluedoEnums> getCards() {return cards;}

    public void newCard(CluedoEnums card) {cards.add(card);}

    public int getUid() {return uid;}

    public Card getCard() {
        switch (characterName) {
            case "MISS SCARLETT":
                return CluedoEnums.CharacterEnum.MISS_SCARLETT;
            case "COLONEL MUSTARD":
                return CluedoEnums.CharacterEnum.COLONEL_MUSTARD;
            case "MRS WHITE":
                return CluedoEnums.CharacterEnum.MRS_WHITE;
            case "THE REVEREND GREEN":
                return CluedoEnums.CharacterEnum.THE_REVEREND_GREEN;
            case "MRS PEACOCK":
                return CluedoEnums.CharacterEnum.MRS_PEACOCK;
            case "PROFESSOR PLUM":
                return CluedoEnums.CharacterEnum.PROFESSOR_PLUM;
            default:
                throw new RuntimeException("getCard error");
        }
    }


    public void setX(int x) {
        this.posX = x;
    }


    public void setY(int y) {
        this.posY = y;
    }


    public int getX() {
        return posX;
    }


    public int getY() {
        return posY;
    }


    public String getName() {
        return playerName;
    }

    private void initCharacterUID(String characterName) {
        switch (characterName) {
            case "MISS SCARLETT":
                characterUID = 1;
                break;
            case "COLONEL MUSTARD":
                characterUID = 2;
                break;
            case "MRS WHITE":
                characterUID = 3;
                break;
            case "THE REVEREND GREEN":
                characterUID = 4;
                break;
            case "MRS PEACOCK":
                characterUID = 5;
                break;
            case "PROFESSOR PLUM":
                characterUID = 6;
                break;
            default:
                return;
        }
    }



    @Override
    public String toString() {
        return "Character name: " + characterName + "\n" + "Player name: "
                + playerName + "\n" + "Player UID: " + uid;
    }


}