/*import cluedo.Player;
import cluedo.Tile;*/
package cluedo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static cluedo.CluedoEnums.*;

public class Game /*extends cluedo.mainApp.BoardPane*/ {
    String[] weapons = new String[6];
    String[] rooms = new String[9];
    String[] characters = new String[6];
    ArrayList<Player> players;
    int numOfPlayers;
    int maxBoardWidth;
    int maxBoardLength;
    int ROWS = 25;
    int COLS = 24;
    Tile[][] builtBoard;
    Tile[][] boardToUse;
    boolean playing;
    boolean[][] inRange;
    ArrayList<String> weaponCards; // dont know yet if i should use card names or just an identifier
    ArrayList<String> roomCards;
    ArrayList<String> characterCards;
    ArrayList<CluedoEnums> cards;
    CluedoEnums characterEnvelope;
    CluedoEnums weaponEnvelope;
    CluedoEnums roomEnvelope;
    boolean readyToRepaint;
    boolean roomEntered;
    int playerWithFoundCards;

    /**
     * initalises the parameters for the game object
     * initalises the board as empty
     * initalises players based on the number of players that has been selected
     * then allocates cards to each of the players after remove
     *
     * @param maxWidth
     * @param maxLength
     * @param numberOfPlayers
     * @param board
     */
    public Game(int maxWidth, int maxLength, int numberOfPlayers, String[] board) {
        maxBoardWidth = maxWidth;
        maxBoardLength = maxLength;
        numOfPlayers = numberOfPlayers;
        builtBoard = new Tile[maxBoardWidth][maxBoardLength];
        roomEntered = false;
        for (int i = 0; i < maxBoardLength; i++) {
            for (int j = 0; j < maxBoardWidth; j++) {
                builtBoard[j][i] = new Tile();
            }
        }

        boardToUse = createBoard(board);

        players = PlayerList(numberOfPlayers);
        spawnPlayers(numberOfPlayers);
        allocateCards();
    }

    /**
     * @return get function for retrieving the murder envelope
     */
    public CluedoEnums[] getMurderEnvelope() { return new CluedoEnums[]{characterEnvelope, weaponEnvelope, roomEnvelope }; }

    /**
     *  //player enters a room
     *         //they pick a weapon and a character to suggest
     *         //move weapon and character tokens to room // move players if necessary
     *         //we check, in set order of players, who has these cards
     *         //display the one card that the player who has it chose ( if they have multiple)
     * @param characterChoice
     * @param weaponChoice
     * @param currentlyPlaying
     * @return which cards, 1 or more, that the first player who is found with one matching card has
     */
    public ArrayList<CluedoEnums> suggestion(String characterChoice, String weaponChoice, int currentlyPlaying) {
        int pX = players.get(currentlyPlaying).getX();
        int pY = players.get(currentlyPlaying).getY();
        boolean foundCard = false;
        CluedoEnums chosenFoundCard;
        ArrayList<CluedoEnums> foundCards = new ArrayList<>();
        int playerWithCards;
        CluedoEnums pickedCharacter = toEnum(characterChoice);
        CluedoEnums pickedWeapon = toEnum(weaponChoice);
        CluedoEnums currentRoom = toEnum(builtBoard[pX][pY].getRoomType());
        //find cards in other players decks
        for (int i = 1; i < numOfPlayers; i++) {
            for (CluedoEnums card : players.get(checkThroughPlayers(currentlyPlaying, i)).getCards()) {
                if (card.getCardName() == pickedCharacter.getCardName()) {
                    foundCard = true;
                    foundCards.add(card);
                }
                if (card.getCardName() == pickedWeapon.getCardName()) {
                    foundCard = true;
                    foundCards.add(card);
                }
                if (card.getCardName() == currentRoom.getCardName()) {
                    foundCard = true;
                    foundCards.add(card);
                }
            }
            if (foundCard) {
                playerWithFoundCards = checkThroughPlayers(currentlyPlaying, i);
                return foundCards;
                //break;
            }
        }
        if (!foundCard) {
            return null;
        }
        //give player choice to pick which card to show
        //if only one card is found in a hand auto show it
        //
        return null;
    }

    /**
     * @return returns iterator of which player has the cards that have been used in a suggestion
     */
    public int getPlayerWithFoundCards() { return playerWithFoundCards; }

    /**
     * used to convert players iterator (currently playing) into a useable number if it has gone over the
     * number of players in the game
     * @param currentlyPlaying
     * @param i
     * @return players iterator
     */
    public int checkThroughPlayers(int currentlyPlaying, int i) {
        // currently playing can never be more than or equal to the number of players as it is an iterator
        if ((currentlyPlaying + i) >= numOfPlayers) {
            return currentlyPlaying + i - numOfPlayers;
        } else {
            return currentlyPlaying + i;
        }
    }

    /**
     * used to convert from Tile.roomType to CluedoEnums
     * @param choice
     * @return the Cluedo Enum version of a room type that it is initialised with (conversion function)
     */
    public CluedoEnums toEnum(Tile.roomType choice) {
        switch (choice) {
            case CONSERVATORY:
                return new CluedoEnums(RoomEnum.CONSERVATORY);
            case LIBRARY:
                return new CluedoEnums(RoomEnum.LIBRARY);
            case KITCHEN:
                return new CluedoEnums(RoomEnum.KITCHEN);
            case LOUNGE:
                return new CluedoEnums(RoomEnum.LOUNGE);
            case STUDY:
                return new CluedoEnums(RoomEnum.STUDY);
            case HALL:
                return new CluedoEnums(RoomEnum.HALL);
            case BALLROOM:
                return new CluedoEnums(RoomEnum.BALL_ROOM);
            case DININGROOM:
                return new CluedoEnums(RoomEnum.DINING_ROOM);
            case BILLIARDROOM:
                return new CluedoEnums(RoomEnum.BILLIARD_ROOM);
        }
        System.out.println("Something went wrong");
        return null;
    }

    /**
     * used to convert from String to Cluedo Enum
     * @param choice
     * @return the Cluedo Enum version of the string that it is initialised with (conversion function)
     */
    public CluedoEnums toEnum(String choice) {
        switch (choice) {
            //return character enums in place of String
            case "Mrs White":
                return new CluedoEnums(CharacterEnum.MRS_WHITE);
            case "Mrs Peacock":
                return new CluedoEnums(CharacterEnum.MRS_PEACOCK);
            case "Miss Scarlett":
                return new CluedoEnums(CharacterEnum.MISS_SCARLETT);
            case "Colonel Mustard":
                return new CluedoEnums(CharacterEnum.COLONEL_MUSTARD);
            case "Professor Plum":
                return new CluedoEnums(CharacterEnum.PROFESSOR_PLUM);
            case "Rev Green":
                return new CluedoEnums(CharacterEnum.THE_REVEREND_GREEN);
            //return weapon enums in place of String
            case "Rope":
                return new CluedoEnums(WeaponEnum.ROPE);
            case "Knife":
                return new CluedoEnums(WeaponEnum.KNIFE);
            case "Wrench":
                return new CluedoEnums(WeaponEnum.WRENCH);
            case "Revolver":
                return new CluedoEnums(WeaponEnum.REVOLVER);
            case "Lead Pipe":
                return new CluedoEnums(WeaponEnum.LEAD_PIPE);
            case "Candlestick":
                return new CluedoEnums(WeaponEnum.CANDLESTICK);
            //return room enums in place of String
            case "Hall":
                return new CluedoEnums(RoomEnum.HALL);
            case "Study":
                return new CluedoEnums(RoomEnum.STUDY);
            case "Lounge":
                return new CluedoEnums(RoomEnum.LOUNGE);
            case "Kitchen":
                return new CluedoEnums(RoomEnum.KITCHEN);
            case "Library":
                return new CluedoEnums(RoomEnum.LIBRARY);
            case "Ballroom":
                return new CluedoEnums(RoomEnum.BALL_ROOM);
            case "Dining Room":
                return new CluedoEnums(RoomEnum.DINING_ROOM);
            case "Conservatory":
                return new CluedoEnums(RoomEnum.CONSERVATORY);
            case "Billiard Room":
                return new CluedoEnums(RoomEnum.BILLIARD_ROOM);
        }
        System.out.println("Something went wrong");
        return null;
    }

    /**
     * used to allocate the cards that each play holds in their hand. after removing 3 random cards, one of each
     * type to be added to the murder envelope
     */
    public void allocateCards() {
        //21 cards 6 characters 6 weapons 9 rooms
        cards = new ArrayList<>();
        Random rnd = new Random();
        int rndCharacter;
        int rndWeapon;
        int rndRoom;
        boolean cardsLeft = true;
        int rndCard;
        ArrayList<Player> tempPlayers;

        //initalise and add to array list the characters
        CluedoEnums missScarlett = new CluedoEnums(CharacterEnum.MISS_SCARLETT);
        cards.add(missScarlett);
        CluedoEnums colonelMustard = new CluedoEnums(CharacterEnum.COLONEL_MUSTARD);
        cards.add(colonelMustard);
        CluedoEnums mrsWhite = new CluedoEnums(CharacterEnum.MRS_WHITE);
        cards.add(mrsWhite);
        CluedoEnums theReverendGreen = new CluedoEnums(CharacterEnum.THE_REVEREND_GREEN);
        cards.add(theReverendGreen);
        CluedoEnums mrsPeacock = new CluedoEnums(CharacterEnum.MRS_PEACOCK);
        cards.add(mrsPeacock);
        CluedoEnums professorPlum = new CluedoEnums(CharacterEnum.PROFESSOR_PLUM);
        cards.add(professorPlum);

        //initalise and add to array list the weapons
        CluedoEnums candlestick = new CluedoEnums(WeaponEnum.CANDLESTICK);
        cards.add(candlestick);
        CluedoEnums knife = new CluedoEnums(WeaponEnum.KNIFE);
        cards.add(knife);
        CluedoEnums leadPipe = new CluedoEnums(WeaponEnum.LEAD_PIPE);
        cards.add(leadPipe);
        CluedoEnums revolver = new CluedoEnums(WeaponEnum.REVOLVER);
        cards.add(revolver);
        CluedoEnums rope = new CluedoEnums(WeaponEnum.ROPE);
        cards.add(rope);
        CluedoEnums wrench = new CluedoEnums(WeaponEnum.WRENCH);
        cards.add(wrench);

        //initalise and add to array list rooms
        CluedoEnums kitchen = new CluedoEnums(RoomEnum.KITCHEN);
        cards.add(kitchen);
        CluedoEnums ballRoom = new CluedoEnums(RoomEnum.BALL_ROOM);
        cards.add(ballRoom);
        CluedoEnums conservatory = new CluedoEnums(RoomEnum.CONSERVATORY);
        cards.add(conservatory);
        CluedoEnums billiardRoom = new CluedoEnums(RoomEnum.BILLIARD_ROOM);
        cards.add(billiardRoom);
        CluedoEnums library = new CluedoEnums(RoomEnum.LIBRARY);
        cards.add(library);
        CluedoEnums study = new CluedoEnums(RoomEnum.STUDY);
        cards.add(study);
        CluedoEnums hall = new CluedoEnums(RoomEnum.HALL);
        cards.add(hall);
        CluedoEnums lounge = new CluedoEnums(RoomEnum.LOUNGE);
        cards.add(lounge);
        CluedoEnums diningRoom = new CluedoEnums(RoomEnum.DINING_ROOM);
        cards.add(diningRoom);
        //create "murder envolope"
        rndCharacter = rnd.nextInt(5);
        rndWeapon = rnd.nextInt(5) + 6;
        rndRoom = rnd.nextInt(8) + 12;
        characterEnvelope = cards.get(rndCharacter);
        weaponEnvelope = cards.get(rndWeapon);
        roomEnvelope = cards.get(rndRoom);
        cards.remove(rndCharacter);
        cards.remove(rndWeapon - 1);
        cards.remove(rndRoom - 2);

        //randomly allocate cards to each player // there are 18 cards
        tempPlayers = players;
        while (cardsLeft) {
            int i = 0;
            for (Player p : players) {
                rndCard = rnd.nextInt(cards.size());
                p.newCard(cards.get(rndCard));
                tempPlayers.set(i, p);
                cards.remove(rndCard);
                if (cards.isEmpty())  {
                    cardsLeft = false;
                    //set players to now have all cards evenly between them
                    players = tempPlayers;
                    break;
                }
                i++;
            }
        }
    }

    /**
     * @return all players that have been initalised for the current game
     */
    public List<Player> getPlayers() { return players; }

    /**
     * this is run when a location on the board has been selected by the player.
     * it will first check if they have selected a locations inside the reachable area based on their die rolls.
     * It must also add/remove players from rooms and move them to the correct location (door / inside the room)
     * @param cell
     * @param currentPlayer
     */
    public void clickedLocation(Rectangle cell, int currentPlayer) {
        int cellX = cell.x / 26;
        int cellY = cell.y / 26;
        Player pHolder = null;
        int[] roomDataCords;
        readyToRepaint = true;
        //find out which player is currentyl playing
        for (Player p : players) {
            if (currentPlayer == p.getUid()) pHolder = p;
        }
        //get that players current coordinates
        int pX = pHolder.getX();
        int pY = pHolder.getY();

        //check if click is in valid range or valid location
        if (inRange[cellX][cellY]) {
            //update boards locations and occupied boolean
            //update players locations in their object
            //update old room
            if (builtBoard[pHolder.getX()][pHolder.getY()].getTileType() == Tile.tileType.FLOOR || builtBoard[pHolder.getX()][pHolder.getY()].getTileType() == Tile.tileType.FLOORENTRANCE) {
                builtBoard[pHolder.getX()][pHolder.getY()].removeFromTile(pHolder);
            }
            // if entering a room a specific point must be found that holds players in rooms and data about the room
            if (builtBoard[cellX][cellY].getTileType() == Tile.tileType.ROOMENTRANCE) {
                roomDataCords = builtBoard[cellX][cellY].getRoomDataCords();
                //no need to get just send over the players and their cords should work
                builtBoard[roomDataCords[0]][roomDataCords[1]].addToRoom(pHolder);
                pHolder.setX(roomDataCords[0]);
                pHolder.setY(roomDataCords[1]);
                roomEntered = true;
            } else { // if just a normal move set new location
                pHolder.setX(cellX);
                pHolder.setY(cellY);
                roomEntered = false;
            } // put back / update the player whos go it is currently
            for (Player p : players) {
                if (currentPlayer == pHolder.getUid()) players.set(pHolder.getUid(), pHolder); //keep an eye on this line
            }
            if (builtBoard[pX][pY].getTileType() == Tile.tileType.ROOMDATA) {
                builtBoard[pX][pY].removeFromRoom(pHolder);
            }
        }
    }

    /**
     * finds all possible locations that the player can reach with the die rolls made.
     * Also sets the differnt choices that can be made leaving each room as there are multiple exists for some
     * @param currentPlayer
     * @param roll
     * @return reachableBoard -
     */
    public String showMovableRange(int currentPlayer, int roll) {
        boolean[][] reachableBoard = new boolean[maxBoardWidth][maxBoardLength];
        StringBuilder reachableBoardString = new StringBuilder();
        Player plyr = null;
        inRange = new boolean[maxBoardWidth][maxBoardLength];
        boolean[][] tempInRange = new boolean[maxBoardWidth][maxBoardLength];
        ArrayList<int[]> doorsCords;
        //find who is currently playing
        while (plyr == null) {
            for (Player p : players) {
                if (p.getUid() == currentPlayer) {
                    plyr = p;
                }
            }
        }
        //get current players locaiton
        int plyrX = plyr.getX();
        int plyrY = plyr.getY();
        // finds the correct numbers for the roomdata points that are accounting for 0 based arrays
        if (builtBoard[plyrX][plyrY].getTileType() == Tile.tileType.ROOMDATA) {
            doorsCords = builtBoard[plyrX][plyrY].getDoors();
            for (int[] d : doorsCords) {
                inRange[d[0]][d[1]] = true;
                tempInRange[d[0]][d[1]] = true;
                checkLoop(d[0], d[1], roll, tempInRange);
            }
        } else {
            inRange[plyrX][plyrY] = true;
            tempInRange[plyrX][plyrY] = true;
            checkLoop(plyrX, plyrY, roll, tempInRange);
        }
        //make a board of moveable range to combine with origenal board
        for (int i = 0; i < maxBoardLength; i++) {
            for (int j = 0; j < maxBoardWidth; j++) {
                if (tempInRange[j][i]) {
                    System.out.print("1");
                    reachableBoardString.append('a');
                } else {
                    System.out.print("0");
                    reachableBoardString.append('z');
                }
            }
            System.out.println("");
        }
        return reachableBoardString.toString();
    }

    /**
     * check each spot around a players location or a location in range.
     * each spot that a player could move to if they were there, such as up down left right not diagonals as a player
     * cannot move in these directions. this and the search functions are recursive to make sure all possible
     * reachable locations are found. such as round corners
     * @param xCheck
     * @param yCheck
     * @param movesLeft
     * @param tempInRange
     * @return returnRange boolean array locations that the player can move to that have been found so far
     */
//create a temp inRange that can be passed in then consolidate
    //create a search that goes only in one direction and run that everytime
    public boolean[][] checkLoop(int xCheck, int yCheck, int movesLeft, boolean[][] tempInRange) {
        int updteMovesLft;
        updteMovesLft = movesLeft - 1;
        boolean[][] upRanges = new boolean[maxBoardWidth][maxBoardLength];
        boolean[][] justUp = new boolean[maxBoardWidth][maxBoardLength];
        boolean[][] leftRanges = new boolean[maxBoardWidth][maxBoardLength];
        boolean[][] justLeft = new boolean[maxBoardWidth][maxBoardLength];
        boolean[][] rightRanges = new boolean[maxBoardWidth][maxBoardLength];
        boolean[][] justRight = new boolean[maxBoardWidth][maxBoardLength];
        boolean[][] downRanges = new boolean[maxBoardWidth][maxBoardLength];
        boolean[][] justDown = new boolean[maxBoardWidth][maxBoardLength];
        //boolean[][] returnRanges = new boolean[maxBoardWidth][maxBoardLength];
        for (int o = 0; o < maxBoardLength; o++) {
            for (int l = 0; l < maxBoardWidth; l++) {
                upRanges[l][o] = false;
                leftRanges[l][o] = false;
                rightRanges[l][o] = false;
                downRanges[l][o] = false;
            }
        }
        //check if its been seen // and check if there are the moves for it
        if (movesLeft > 0) {
            //checks up location and then as far as it can go up with searchUP
            int yUP = yCheck - 1;
            if ((xCheck < maxBoardWidth)&&(yUP < maxBoardLength)&&(xCheck >= 0)&&(yUP >= 0)) {
                if (builtBoard[xCheck][yUP].tileTypeHolder == Tile.tileType.FLOOR || builtBoard[xCheck][yUP].tileTypeHolder == Tile.tileType.FLOORENTRANCE || builtBoard[xCheck][yUP].tileTypeHolder == Tile.tileType.ROOMENTRANCE) { //up
                    if (!builtBoard[xCheck][yUP].occupied) {
                        inRange[xCheck][yUP] = true;
                        tempInRange[xCheck][yUP] = true;
                        tempInRange = searchUP(xCheck, yUP, updteMovesLft, tempInRange);
                        tempInRange = checkLoop(xCheck, yUP, updteMovesLft, tempInRange);
                    }
                }
            }
            //checks left location and then as far as it can go to the left with searchLEFT
            int xLEFT = xCheck - 1;
            if ((xLEFT < maxBoardWidth)&&(yCheck < maxBoardLength)&&(xLEFT >= 0)&&(yCheck >= 0)) {
                if (builtBoard[xLEFT][yCheck].tileTypeHolder == Tile.tileType.FLOOR || builtBoard[xLEFT][yCheck].tileTypeHolder == Tile.tileType.FLOORENTRANCE || builtBoard[xLEFT][yCheck].tileTypeHolder == Tile.tileType.ROOMENTRANCE) { //left
                    if (!builtBoard[xLEFT][yCheck].occupied) {
                        tempInRange[xLEFT][yCheck] = true;
                        inRange[xLEFT][yCheck] = true;
                        tempInRange = searchLeft(xLEFT, yCheck, updteMovesLft, tempInRange);
                        tempInRange = checkLoop(xLEFT, yCheck, updteMovesLft, tempInRange);
                    }
                }
            }
            //checks right location and then as far as it can go to the right with searchRIGHT
            int xRIGHT = xCheck + 1;
            if ((xRIGHT < maxBoardWidth)&&(yCheck < maxBoardLength)&&(xRIGHT >= 0)&&(yCheck >= 0)) {
                if (builtBoard[xRIGHT][yCheck].tileTypeHolder == Tile.tileType.FLOOR || builtBoard[xRIGHT][yCheck].tileTypeHolder == Tile.tileType.FLOORENTRANCE || builtBoard[xRIGHT][yCheck].tileTypeHolder == Tile.tileType.ROOMENTRANCE) { //right
                    if (!builtBoard[xRIGHT][yCheck].occupied) {
                        tempInRange[xRIGHT][yCheck] = true;
                        inRange[xRIGHT][yCheck] = true;
                        tempInRange = searchRight(xRIGHT, yCheck, updteMovesLft, tempInRange);
                        tempInRange = checkLoop(xRIGHT, yCheck, updteMovesLft, tempInRange);
                    }
                }
            }
            //checks down location and then as far as it can go down with searchDOWN
            int yDOWN = yCheck + 1;
            if ((xCheck < maxBoardWidth)&&(yDOWN < maxBoardLength)&&(xCheck >= 0)&&(yDOWN >= 0)) {
                if (builtBoard[xCheck][yDOWN].tileTypeHolder == Tile.tileType.FLOOR || builtBoard[xCheck][yDOWN].tileTypeHolder == Tile.tileType.FLOORENTRANCE || builtBoard[xCheck][yDOWN].tileTypeHolder == Tile.tileType.ROOMENTRANCE) { //down
                    if (!builtBoard[xCheck][yDOWN].occupied) {
                        tempInRange[xCheck][yDOWN] = true;
                        inRange[xCheck][yDOWN] = true;
                        tempInRange = searchDown(xCheck, yDOWN, updteMovesLft, tempInRange);
                        tempInRange = checkLoop(xCheck, yDOWN, updteMovesLft, tempInRange);
                    }
                }
            }
            //consolidate new inrange info
            for (int i = 0; i < maxBoardLength; i++) {
                for (int j = 0; j < maxBoardWidth; j++) {
                    if (upRanges[j][i] || leftRanges[j][i] || rightRanges[j][i] || downRanges[j][i] || justLeft[j][i] || justRight[j][i] || justUp[j][i] || justDown[j][i]) {
                        inRange[j][i] = true;
                    }
                }
            }
        }
        return tempInRange;
    }

    /**
     * searches all the way up from a point that has been defined as reachable by the player.
     * all the way till there are no moves left
     * @param xCheck
     * @param yCheck
     * @param movesLeft
     * @param returnRange
     * @return returnRange boolean array locations that the player can move to that have been found so far
     */
    public boolean[][] searchUP(int xCheck, int yCheck, int movesLeft, boolean[][] returnRange) {
        int changeY;
        int updteMovesLft;

        for (int i = 1; i < movesLeft; i++) {
            changeY = yCheck - i;
            updteMovesLft = movesLeft - i;
            if ((xCheck < maxBoardWidth)&&(changeY < maxBoardLength)&&(xCheck >= 0)&&(changeY >= 0)) {
                if (builtBoard[xCheck][changeY].tileTypeHolder == Tile.tileType.FLOOR || builtBoard[xCheck][changeY].tileTypeHolder == Tile.tileType.FLOORENTRANCE || builtBoard[xCheck][changeY].tileTypeHolder == Tile.tileType.ROOMENTRANCE) { //down
                    returnRange[xCheck][changeY] = true;
                    returnRange = checkLoop(xCheck, changeY, updteMovesLft, returnRange);
                } else {
                    return returnRange;
                }
            }
        }
        return returnRange;
    }

    /**
     * searches all the way Left from a point that has been defined as reachable by the player.
     * all the way till there are no moves left
     * @param xCheck
     * @param yCheck
     * @param movesLeft
     * @param returnRange
     * @return returnRange boolean array locations that the player can move to that have been found so far
     */
    public boolean[][] searchLeft(int xCheck, int yCheck, int movesLeft, boolean[][] returnRange) {
        //boolean[][] returnRange = new boolean[maxBoardWidth][maxBoardLength];
        int changeX;
        int updteMovesLft;

        for (int i = 1; i < movesLeft; i++) {
            changeX = xCheck - i;
            updteMovesLft = movesLeft - i;
            if ((changeX < maxBoardWidth)&&(yCheck < maxBoardLength)&&(changeX >= 0)&&(yCheck >= 0)) {
                if (builtBoard[changeX][yCheck].tileTypeHolder == Tile.tileType.FLOOR || builtBoard[changeX][yCheck].tileTypeHolder == Tile.tileType.FLOORENTRANCE || builtBoard[changeX][yCheck].tileTypeHolder == Tile.tileType.ROOMENTRANCE) { //down
                    returnRange[changeX][yCheck] = true;
                    returnRange = checkLoop(changeX, yCheck, updteMovesLft, returnRange);
                } else {
                    return returnRange;
                }
            }
        }
        return returnRange;
    }

    /**
     * searches all the way Right from a point that has been defined as reachable by the player.
     * all the way till there are no moves left
     * @param xCheck
     * @param yCheck
     * @param movesLeft
     * @param returnRange
     * @return returnRange boolean array locations that the player can move to that have been found so far
     */
    public boolean[][] searchRight(int xCheck, int yCheck, int movesLeft, boolean[][] returnRange) {
        //boolean[][] returnRange = new boolean[maxBoardWidth][maxBoardLength];
        int changeX;
        int updteMovesLft;

        for (int i = 1; i < movesLeft; i++) {
            changeX = xCheck + i;
            updteMovesLft = movesLeft - i;
            if ((changeX < maxBoardWidth)&&(yCheck < maxBoardLength)&&(changeX >= 0)&&(yCheck >= 0)) {
                if (builtBoard[changeX][yCheck].tileTypeHolder == Tile.tileType.FLOOR || builtBoard[changeX][yCheck].tileTypeHolder == Tile.tileType.FLOORENTRANCE || builtBoard[changeX][yCheck].tileTypeHolder == Tile.tileType.ROOMENTRANCE) { //down
                    returnRange[changeX][yCheck] = true;
                    returnRange = checkLoop(changeX, yCheck, updteMovesLft, returnRange);
                } else {
                    return returnRange;
                }
            }
        }
        return returnRange;
    }

    /**
     * searches all the way down from a point that has been defined as reachable by the player.
     * all the way till there are no moves left
     * @param xCheck
     * @param yCheck
     * @param movesLeft
     * @param returnRange
     * @return returnRange boolean array locations that the player can move to that have been found so far
     */
    public boolean[][] searchDown(int xCheck, int yCheck, int movesLeft, boolean[][] returnRange) {
        //boolean[][] returnRange = new boolean[maxBoardWidth][maxBoardLength];
        int changeY;
        int updteMovesLft;

        for (int i = 1; i < movesLeft; i++) {
            changeY = yCheck + i;
            updteMovesLft = movesLeft - i;
            if ((xCheck < maxBoardWidth)&&(changeY < maxBoardLength)&&(xCheck >= 0)&&(changeY >= 0)) {
                if (builtBoard[xCheck][changeY].tileTypeHolder == Tile.tileType.FLOOR || builtBoard[xCheck][changeY].tileTypeHolder == Tile.tileType.FLOORENTRANCE || builtBoard[xCheck][changeY].tileTypeHolder == Tile.tileType.ROOMENTRANCE) { //down
                    returnRange[xCheck][changeY] = true;
                    returnRange = checkLoop(xCheck, changeY, updteMovesLft, returnRange);
                } else {
                    return returnRange;
                }
            }
        }
        return returnRange;
    }

    /**
     * sets the spawn locations of players based on locations in the file but are set for each character specifically
     * @param numOfPlayers
     */
    public void spawnPlayers(int numOfPlayers) {
        //should be done when players are alloction (or picked) so that
        // players can pick specific characters rather than be allocated each inorder

        for (Player plyr : players) {
            switch (plyr.getName()) {// temporary to set locations. will be done by random for real by auto finding spawnpoints
                case "mustard": //could also be set locations to start from as some versions of the game do it this way
                    plyr.setX(16); // the other choice is to start everyone at the stairs one by one int eh same spot as this  can be more fair
                    plyr.setY(0);
                    builtBoard[16][0].setOnTile(plyr);
                    builtBoard[16][0].setOccupied();
                    break;
                case "plum":
                    //CHANGE
                    plyr.setX(0);
                    plyr.setY(18);
                    builtBoard[0][18].setOnTile(plyr);
                    builtBoard[0][18].setOccupied();
                    break;
                //CHANGE
                case "white":
                    plyr.setX(23);
                    plyr.setY(7);
                    builtBoard[23][7].setOnTile(plyr);
                    builtBoard[23][7].setOccupied();
                    break;
                case "peacock":
                    //CHANGE
                    plyr.setX(0);
                    plyr.setY(5);
                    builtBoard[0][5].setOnTile(plyr);
                    builtBoard[0][5].setOccupied();
                    break;
                case "scarlett":
                    plyr.setX(9);
                    plyr.setY(24);
                    builtBoard[9][24].setOnTile(plyr);
                    builtBoard[9][24].setOccupied();
                    break;
                //CHANGE
                case "green":
                    plyr.setX(14);
                    plyr.setY(24);
                    builtBoard[14][24].setOnTile(plyr);
                    builtBoard[14][24].setOccupied();
                    break;
            }
        }
    }

    /**
     * initlaises each of the playable characters and assigns them based on the required amount
     * @param numberOfPlayers
     * @return ArrayList<Player> of now initalised players based on the number of players that has been picked
     */
    private static ArrayList<Player> PlayerList(int numberOfPlayers) {
        //initlises all possible playable characters
        Player scarlett = new Player(0, "MISS SCARLETT", "scarlett");
        Player mustard = new Player(1, "COLONEL MUSTARD", "mustard");
        Player white = new Player(2, "MRS WHITE", "white");
        Player green = new Player(3, "THE REVEREND GREEN", "green");
        Player peacock = new Player(4, "MRS PEACOCK", "peacock");
        Player plum = new Player(5, "PROFESSOR PLUM", "plum");

        Player[] playerArray = new Player[]{scarlett, mustard, white, green, peacock, plum};

        ArrayList<Player> playerList = new ArrayList<Player>();
        //only addes the characters that are needed
        for (int i = 0; i < numberOfPlayers; i++) {
            playerList.add(playerArray[i]);
        }

        return playerList;
    }

    /**
     * initialises the board tiles according to the custom file
     * @param String board
     * @return Tile[][] builtBoard
     */
    //start up procedures
    public Tile[][] createBoard(String[] board) {
        int o = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                String pip = board[o];
                builtBoard[j][i] = new Tile();
                switch (pip) {
                    case "x":
                        builtBoard[j][i].setTileType(Tile.tileType.ROOM);
                        break;
                    case "a":
                        builtBoard[j][i].setTileType(Tile.tileType.FLOOR);
                        break;
                    case "y":
                        builtBoard[j][i].setTileType(Tile.tileType.FLOORENTRANCE);
                        break;
                    case "m":
                        builtBoard[j][i].setTileType(Tile.tileType.STAIRS);
                        break;
                    case "z":
                        builtBoard[j][i].setTileType(Tile.tileType.SPAWN);
                        break;
                    case "s":// here onwards are the entrances to rooms but not the place by a door players enter by
                        builtBoard[j][i].setTileType(Tile.tileType.ROOMENTRANCE);
                        builtBoard[j][i].setRoomType(Tile.roomType.STUDY);
                        break;
                    case "h":
                        builtBoard[j][i].setTileType(Tile.tileType.ROOMENTRANCE);
                        builtBoard[j][i].setRoomType(Tile.roomType.HALL);
                        break;
                    case "l":
                        builtBoard[j][i].setTileType(Tile.tileType.ROOMENTRANCE);
                        builtBoard[j][i].setRoomType(Tile.roomType.LIBRARY);
                        break;
                    case "d":
                        builtBoard[j][i].setTileType(Tile.tileType.ROOMENTRANCE);
                        builtBoard[j][i].setRoomType(Tile.roomType.DININGROOM);
                        break;
                    case "b":
                        builtBoard[j][i].setTileType(Tile.tileType.ROOMENTRANCE);
                        builtBoard[j][i].setRoomType(Tile.roomType.BILLIARDROOM);

                        break;
                    case "e":
                        builtBoard[j][i].setTileType(Tile.tileType.ROOMENTRANCE);
                        builtBoard[j][i].setRoomType(Tile.roomType.BALLROOM);
                        break;
                    case "k":
                        builtBoard[j][i].setTileType(Tile.tileType.ROOMENTRANCE);
                        builtBoard[j][i].setRoomType(Tile.roomType.KITCHEN);
                        break;
                    case "c":
                        builtBoard[j][i].setTileType(Tile.tileType.ROOMENTRANCE);
                        builtBoard[j][i].setRoomType(Tile.roomType.CONSERVATORY);
                        break;
                    case "f":
                        builtBoard[j][i].setTileType(Tile.tileType.ROOMENTRANCE);
                        builtBoard[j][i].setRoomType(Tile.roomType.LOUNGE);
                }
                o++;
                if (i == 3 && j == 5) { // room data point setters // must be set as loops run otherwise will be overwritten
                    builtBoard[j][i].setTileType(Tile.tileType.ROOMDATA);
                    builtBoard[j][i].setRoomType(Tile.roomType.STUDY);
                } if (i == 4 && j == 11) {
                    builtBoard[j][i].setTileType(Tile.tileType.ROOMDATA);
                    builtBoard[j][i].setRoomType(Tile.roomType.HALL);
                } if (i == 8 && j == 3) {
                    builtBoard[j][i].setTileType(Tile.tileType.ROOMDATA);
                    builtBoard[j][i].setRoomType(Tile.roomType.LIBRARY);
                } if (i == 10 && j == 18) {
                    builtBoard[j][i].setTileType(Tile.tileType.ROOMDATA);
                    builtBoard[j][i].setRoomType(Tile.roomType.DININGROOM);
                } if (i == 14 && j == 2) {
                    builtBoard[j][i].setTileType(Tile.tileType.ROOMDATA);
                    builtBoard[j][i].setRoomType(Tile.roomType.BILLIARDROOM);
                } if (i == 20 && j == 12) {
                    builtBoard[j][i].setTileType(Tile.tileType.ROOMDATA);
                    builtBoard[j][i].setRoomType(Tile.roomType.BALLROOM);
                } if (i == 20 && j == 20) {
                    builtBoard[j][i].setTileType(Tile.tileType.ROOMDATA);
                    builtBoard[j][i].setRoomType(Tile.roomType.KITCHEN);
                } if (i == 21 && j == 2) {
                    builtBoard[j][i].setTileType(Tile.tileType.ROOMDATA);
                    builtBoard[j][i].setRoomType(Tile.roomType.CONSERVATORY);
                } if (i == 4 && j == 19) {
                    builtBoard[j][i].setTileType(Tile.tileType.ROOMDATA);
                    builtBoard[j][i].setRoomType(Tile.roomType.LOUNGE);
                }
            }
        }
        return builtBoard;
    }
}
