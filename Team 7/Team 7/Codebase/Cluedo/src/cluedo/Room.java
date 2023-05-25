package cluedo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Room {

    public String nameOfRoom;
    public ArrayList<Rectangle> dimensionsOfRoom;
    public ArrayList<Weapon> weaponList;
    public ArrayList<Player> playerList;

    public Room(String roomName, List<Rectangle> roomDimensions) {
        this.nameOfRoom = roomName;
        this.dimensionsOfRoom = new ArrayList<>(roomDimensions);
        this.weaponList = new ArrayList<>();
        this.playerList = new ArrayList<>();
    }

    public String getNameOfRoom() {
        return nameOfRoom;
    }

    public ArrayList<Rectangle> getRoomRectangles(){
        return dimensionsOfRoom;
    }

    public void addPlayer(Player player){
        Player p = player;
        playerList.add(p);
    }

    public Player removePlayer(Player player){
        Player p = player;
        playerList.remove(p);
        return p;
    }

    public void addWeapon(Weapon weapon){
        Weapon w = weapon;
        weaponList.add(w);
        System.out.println("Added weapon: " + w.getName());
    }

    public Weapon removeWeapon(Weapon weapon){
        Weapon w = weapon;
        weaponList.remove(w);
        return w;
    }

    public ArrayList<Player> getPlayerList(){
        return playerList;
    }
}
