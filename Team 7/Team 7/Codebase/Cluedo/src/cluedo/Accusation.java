package cluedo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Accusation extends JPanel {

    JDialog accusePopup;
    String pickedCharacter;
    String pickedWeapon;
    String accuseCharacter;
    String accuseWeapon;
    String accuseRoom;
    Game runningGame;
    int currentlyPlaying;
    ArrayList<Integer> lostPlayers;

    /**
     * initalizes varibles required for check accusation and who is making the accusation
     * @param setGame
     * @param setCharacter
     * @param setWeapon
     * @param setRoom
     * @param setCurrentPlayer
     */
    public Accusation(Game setGame, String setCharacter, String setWeapon, String setRoom, int setCurrentPlayer) {
        runningGame = setGame;
        accuseCharacter = setCharacter;
        accuseWeapon = setWeapon;
        accuseRoom = setRoom;
        currentlyPlaying = setCurrentPlayer;
        lostPlayers = new ArrayList<>();
    }

    /**
     * @return the players who have lost the game
     */
    public ArrayList<Integer> getLostPlayers() {
        return lostPlayers;
    }

    /**
     * opens winning screen
     */
    public void youWin() {
        JDialog winningScreen = new JDialog();
        Icon winningIcon = new ImageIcon("WIN.png");
        JLabel winningIconHolder = new JLabel(winningIcon);

        winningScreen.add(winningIconHolder);
        winningScreen.setTitle("Correct! You won. Congratulations!!");
        winningScreen.pack();
        winningScreen.setVisible(true);
    }

    /**
     * opens loosing screen and sets the player to no longer play the game
     */
    public void youDontWin() {
        JDialog youLost = new JDialog();
        Icon lossIcon = new ImageIcon("LOSE.png");
        JLabel lossIconHolder = new JLabel(lossIcon);

        youLost.add(lossIconHolder);
        youLost.add(new JLabel("You are now out of the game. \nPlease still select which cards to show."));
        youLost.setTitle("Sorry that was wrong.");
        youLost.pack();
        youLost.setVisible(true);
        lostPlayers.add(currentlyPlaying);
    }

    /**
     * checks which cards were entered into the murder envelope at the start againt the accusation that has been made
     * @param character
     * @param weapon
     * @param room
     */
    public void accusation(String character, String weapon, String room) {
        //check this againt the murder cards
        String cEnum = runningGame.toEnum(character).getCardName();
        String wEnum = runningGame.toEnum(weapon).getCardName();
        String rEnum = runningGame.toEnum(room).getCardName();
        String[] accusationCards = new String[]{cEnum, wEnum, rEnum};
        CluedoEnums[] murderEnvelope = runningGame.getMurderEnvelope();
        String[] sMurderEnvelope = {murderEnvelope[0].getCardName(), murderEnvelope[1].getCardName(), murderEnvelope[2].getCardName()};

        if (Arrays.equals(sMurderEnvelope, accusationCards)) {
            youWin();
        } else {
            youDontWin();
        }
        //return wether they got it right
        //if so end the game and say well done
        // if not keep the game going but they can no longer play only pick cards to show
    }

    /**
     * gives the player the choice of what character weapon and room to accuse
     */
    public void accusationPopup() {
        // find out who they want to accuse and with what and where
        String[] characterChoices = {"Mrs White", "Mrs Peacock", "Miss Scarlett", "Colonel Mustard", "Professor Plum", "Rev Green"};
        String[] weaponChoices = {"Rope", "Dagger", "Wrench", "Revolver", "Lead Pipe", "Candlestick"};
        String[] roomChoices = {"Hall", "Study", "Lounge", "Kitchen", "Library", "Ballroom", "Dining Room", "Conservatory", "Billiard Room"};
        JComboBox<String> characterCB = new JComboBox<>(characterChoices);
        JComboBox<String> weaponCB = new JComboBox<>(weaponChoices);
        JComboBox<String> roomCB = new JComboBox<>(roomChoices);
        JButton accuse = new JButton("Accuse!!");
        //create an object to hold the option pane
        //initialise the option pane to hold the combo box's
        Object[] holder = new Object[]{};
        JOptionPane CBPane = new JOptionPane("Please choose a Character to suggest,\n" +
                " the Weapon used and the room it was in.", JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, new ImageIcon("SMALLLOGO.png"), holder, null);
        //add the combo box's to the option pane
        CBPane.add(characterCB);
        CBPane.add(weaponCB);
        CBPane.add(roomCB);

        accuse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accuseCharacter = String.valueOf(characterCB.getSelectedItem());
                accuseWeapon = String.valueOf(weaponCB.getSelectedItem());
                accuseRoom = String.valueOf(roomCB.getSelectedItem());
                accusePopup.setVisible(false);
                accusation(accuseCharacter, accuseWeapon, accuseRoom);
            }
        });
        CBPane.add(accuse);
        //now initalise a dialog to hold all of the above
        accusePopup = new JDialog();
        accusePopup.getContentPane().add(CBPane);
        accusePopup.pack();
        accusePopup.setVisible(true);
    }

}
