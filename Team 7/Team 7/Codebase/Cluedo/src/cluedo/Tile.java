package cluedo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tile { // should be separated into tiles with a purpose for each that can be referenced when needed
    //separation so that rooms have a specific tile in the room that holds in information rather than many as this would be inefficient
    tileType tileTypeHolder; //"room","floor","floorEntrance","spawn", "secretPassage", "unreachable"
    roomType roomTypeHolder;
    boolean occupied; //for normal tiles // not really needed now we have onTile in room probably the same
    // if there is a player on onTile there can only be one if it is a floor tile
    ArrayList<Player> inRoom = new ArrayList<>(); //holds characters and weapons that are currently in the room
    //switch in room to in tile so each tile can hold a player in it??
    ArrayList<Player> onTile = new ArrayList<>();

    /**
     * holds all differnt possible types of Tile
     */
    enum tileType {
        ROOM,
        ROOMDATA,
        ROOMENTRANCE,
        FLOOR,
        FLOORENTRANCE,
        SPAWN,
        SECRETPASSAGE,
        STAIRS
    }

    /**
     * holds all possible differnt types of rooms
     */
    enum roomType {
        STUDY,
        HALL,
        LIBRARY,
        DININGROOM,
        BILLIARDROOM,
        BALLROOM,
        KITCHEN,
        CONSERVATORY,
        LOUNGE
    }

    /**
     * @return all doors to a players current room
     */
    public ArrayList<int[]> getDoors() {
        ArrayList<int[]> doors = new ArrayList<>();
        switch (roomTypeHolder) {
            case STUDY: // 1
                doors.add(new int[]{6, 3});
                return doors;
            case HALL: // 2
                doors.add(new int[]{9, 4});
                doors.add(new int[]{11, 7});
                doors.add(new int[]{12, 7});
                return doors;
            case LIBRARY: // 3
                doors.add(new int[]{6, 8});
                doors.add(new int[]{3, 10});
                return doors;
            case DININGROOM: // 4
                doors.add(new int[]{17, 9});
                doors.add(new int[]{16, 12});
                return doors;
            case BILLIARDROOM: // 5
                doors.add(new int[]{1, 11});
                doors.add(new int[]{5, 14});
                return doors;
            case BALLROOM: // 6
                doors.add(new int[]{8, 19});
                doors.add(new int[]{9, 17});
                doors.add(new int[]{14, 17});
                doors.add(new int[]{15, 19});
                return doors;
            case KITCHEN: // 7
                doors.add(new int[]{19, 18});
                return doors;
            case CONSERVATORY: // 8
                doors.add(new int[]{4, 19});
                return doors;
            case LOUNGE: // 9
                doors.add(new int[]{17, 5});
                return doors;
        }
        return new ArrayList<>(Collections.singleton(new int[]{0, 0}));
    }

    /**
     * @return specific room central points that hold the rooms data
     */
    public int[] getRoomDataCords() {
        switch (roomTypeHolder) { // all must be array ready
            case STUDY: // 1
                return new int[]{5, 2};
            case HALL: // 2
                return new int[]{11, 4};
            case LIBRARY: // 3
                return new int[]{3, 8};
            case DININGROOM: // 4
                return new int[]{18, 10};
            case BILLIARDROOM: // 5
                return new int[]{2, 14};
            case BALLROOM: // 6
                return new int[]{12, 20};
            case KITCHEN: // 7
                return new int[]{20, 20};
            case CONSERVATORY: // 8
                return new int[]{2, 21};
            case LOUNGE: // 9
                return new int[]{19, 4};
        }
        return new int[]{0, 0};
    }

    /**
     * adds a player to the tile so that only one player can be there at once
     * @param plyr
     */
    public void setOnTile(Player plyr) {onTile.add(plyr);}

    /**
     * removes a player from the tile as only one player can be there at once
     * @param plyr
     */
    public void removeFromTile(Player plyr) {
        int i = 0;
        for (Player p : onTile) {
            if (p == plyr) onTile.remove(i);
            i++;
        }
    }

    /**
     * adds a player to the tile so that only one player can be there at once
     */
    public void setOccupied() {occupied = true;}

    /**
     * sets the room type of the tile based on possible room types in enum
     * @param setRoom
     */
    public void setRoomType(roomType setRoom) {roomTypeHolder = setRoom;}

    /**
     * @return the room type of the tile
     */
    public roomType getRoomType() { return roomTypeHolder;}

    /**
     * @return gets the tile type of the tile
     */
    public tileType getTileType() {return tileTypeHolder;}

    /**
     * sets the tile type of the tile
     * @param setType
     */
    public void setTileType(tileType setType) {tileTypeHolder = setType;}

    /**
     * is used for only room data tiles not floor tiles that can only hold one player
     * @param toAdd
     */
    public void addToRoom(Player toAdd) {inRoom.add(toAdd);}

    /**
     * takes a player out of a room
     * @param toRemove
     */
    public void removeFromRoom(Player toRemove) {
        List<Player> playersToRemove = new ArrayList<>();
        int i = 0;
        for (Player p : inRoom) {
            if (p == toRemove) playersToRemove.add(p);
            i++;
        }
        inRoom.removeAll(playersToRemove);
    }
}