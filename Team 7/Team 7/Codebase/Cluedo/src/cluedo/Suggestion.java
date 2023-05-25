package cluedo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Suggestion extends JPanel{

    Game runningGame;
    String pickedCharacter;
    String pickedWeapon;
    int currentlyPlaying;
    JDialog popup;
    JDialog pickPopup;

    /**
     * initalises varibles that are needed to tell what cards are being suggested
     * @param setGame
     * @param setCharacter
     * @param setWeapon
     * @param setCurrentP
     */
    public Suggestion(Game setGame, String setCharacter, String setWeapon, int setCurrentP) {
        runningGame = setGame;
        pickedCharacter = setCharacter;
        pickedWeapon = setWeapon;
        currentlyPlaying = setCurrentP;
    }

    /**
     * when cards that are in play have been found it is then displayed
     * @param cardName
     */
    public void cardPicked(String cardName) {
        int playerWithCard = runningGame.getPlayerWithFoundCards() + 1;
        JDialog cardHolder = new JDialog();
        Icon cardIcon = runningGame.toEnum(cardName).getCardIcon();
        JLabel cardIconHolder = new JLabel(cardIcon);

        cardHolder.add(cardIconHolder);
        cardHolder.setTitle("Player " + playerWithCard + "'s card");
        cardHolder.pack();
        cardHolder.setVisible(true);
    }

    /**
     * when the suggestion is made the other player must deicded what card to show if there are multiple in their hand
     * @param foundCards
     * @param playerOfCards
     */
    public void suggestionCardPlayerChoicePopup(ArrayList<CluedoEnums> foundCards, int playerOfCards) {
        JPanel buttonsPane = new JPanel();
        JButton imageButton;
        JButton showCards = new JButton("Show Cards");
        for (CluedoEnums c : foundCards) {
            imageButton = new JButton(c.getCardName(), c.getCardIcon());
            imageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardPicked(e.getActionCommand());
                    pickPopup.setVisible(false);
                }
            });
            buttonsPane.add(imageButton);
        }
        showCards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pickPopup.setVisible(false);
                pickPopup.remove(showCards);
                pickPopup.setContentPane(buttonsPane);
                pickPopup.pack();
                pickPopup.setVisible(true);
            }
        });
        pickPopup = new JDialog((Dialog) null, "Which card would you \n" +
                "like to show?", true);
        pickPopup.add(showCards);
        pickPopup.setTitle("Player " + String.valueOf(playerOfCards + 1));
        pickPopup.pack();
        pickPopup.setVisible(true);
    }

    /**
     * gives the player the choice of wha character to suggest and what weapon but the room is based on where they are currently
     */
    public void openSugestionPopup() {
        //initialise the content of the combo boxs then the combo boxs themselves
        String[] characterChoices = {"Mrs White", "Mrs Peacock", "Miss Scarlett", "Colonel Mustard", "Professor Plum", "Rev Green"};
        String[] weaponChoices = {"Rope", "Dagger", "Wrench", "Revolver", "Lead Pipe", "Candlestick"};
        JComboBox<String> characterCB = new JComboBox<>(characterChoices);
        JComboBox<String> weaponCB = new JComboBox<>(weaponChoices);
        JButton suggest = new JButton("Suggest!!");
        //create an object to hold the option pane
        //initialise the option pane to hold the combo box's
        Object[] holder = new Object[]{};
        JOptionPane CBPane = new JOptionPane("Please choose a Character to suggest\n" +
                "and the Weapon used.", JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, new ImageIcon("SMALLLOGO.png"), holder, null);
        //add the combo box's to the option pane
        CBPane.add(characterCB);
        CBPane.add(weaponCB);
        suggest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pickedCharacter = String.valueOf(characterCB.getSelectedItem());
                pickedWeapon = String.valueOf(weaponCB.getSelectedItem());
                popup.setVisible(false);
                suggestionPT2();
            }
        });
        CBPane.add(suggest);
        //now initalise a dialog to hold all of the above
        popup = new JDialog();
        popup.getContentPane().add(CBPane);
        popup.pack();
        popup.setVisible(true);
    }

    /**
     * find which player has the cards that have been suggested and displays based on that
     */
    public void suggestionPT2() {
        ArrayList<CluedoEnums> foundCards;
        CluedoEnums cardToShow;
        // retrive cards that have been found
        foundCards = runningGame.suggestion(pickedCharacter, pickedWeapon, currentlyPlaying);
        // show cards to other player if there is more than one so they can choose
        if (foundCards != null) {
            if (foundCards.size() > 1) {
                suggestionCardPlayerChoicePopup(foundCards, runningGame.getPlayerWithFoundCards());
            } else {
                cardPicked(foundCards.get(0).getCardName());
            }

        }
        // other player chooses which card to show
        // original player is shown card
    }
}
