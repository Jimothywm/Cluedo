package cluedo;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import java.io.File;
import java.io.IOException;
import java.util.Random;


/**
 * The type Game cycle.
 */
public class GameCycle {

    //Changes to true after players are initially spawned so that instead of being spawned again, player positions are saved in between turns.
    private Boolean playersSpawned = false;

    private Boolean weaponsSpawned = false;

    private Boolean weaponsNeedUpdate = true;

    private Boolean displayWeapons = false;

    private Boolean customColors = false;

    private String gameTheme = "Classic";
    private List<Color> playerColors = new ArrayList<>(6);
    private List<String> playerNames = new ArrayList<>(6);
    private Color p1Color = Color.ORANGE;
    private Color p2Color = Color.BLACK;
    private Color p3Color = Color.lightGray;
    private Color p4Color = Color.magenta;
    private Color p5Color = Color.RED;
    private Color p6Color = Color.green;

    private Color colorToAdd = Color.ORANGE;

    //CHANGE
    private Boolean doorsSet = false;

    private Boolean weaponsDrawn = false;

    private int selectedPlayerId;

    /**
     * The Dice roll.
     */
    int diceRoll;

    /**
     * The R 2 dice.
     */
    Icon[] R2dice;
    /**
     * The R 3 dice.
     */
    Icon[] R3dice;
    /**
     * The R 4 dice.
     */
    Icon[] R4dice;
    /**
     * The R 5 dice.
     */
    Icon[] R5dice;
    /**
     * The R 6 dice.
     */
    Icon[] R6dice;
    /**
     * The R 7 dice.
     */
    Icon[] R7dice;
    /**
     * The R 8 dice.
     */
    Icon[] R8dice;
    /**
     * The R 9 dice.
     */
    Icon[] R9dice;
    /**
     * The R 10 dice.
     */
    Icon[] R10dice;
    /**
     * The R 11 dice.
     */
    Icon[] R11dice;
    /**
     * The R 12 dice.
     */
    Icon[] R12dice;


    //Holds the saved player positions so that cells2 can be updated back to existing positions after players have spawned and turns are being taken.
    private List<Rectangle> cells2Replace;

    //Holds the actual player Rectangles that are used in drawing the board after each turn
    private List<Rectangle> cells2;

    private List<Rectangle> weaponCells2Replace;
    private List<Rectangle> weaponCells;

    //Holds the innaccessible Rectangles
    private List<Rectangle> noAccessCells;

    private List<Rectangle> middleCells;

    private List<Rectangle> studyRoomCells;
    private List<Rectangle> libraryRoomCells;
    private List<Rectangle> billiardRoomCells;
    private List<Rectangle> conservatoryRoomCells;
    private List<Rectangle> ballroomRoomCells;
    private List<Rectangle> kitchenRoomCells;
    private List<Rectangle> diningRoomCells;
    private List<Rectangle> loungeRoomCells;
    private List<Rectangle> hallRoomCells;
    private List<Rectangle> allRoomCells;

    private List<Rectangle> studyDoorCells;
    private List<Rectangle> libraryDoorCells;
    private List<Rectangle> billiardDoorCells;
    private List<Rectangle> conservatoryDoorCells;
    private List<Rectangle> ballroomDoorCells;
    private List<Rectangle> kitchenDoorCells;
    private List<Rectangle> diningDoorCells;
    private List<Rectangle> loungeDoorCells;
    private List<Rectangle> hallDoorCells;
    private List<Rectangle> allDoorCells;

    private Room studyRoom;
    private Room libraryRoom;
    private Room billiardRoom;
    private Room conservatoryRoom;
    private Room ballroomRoom;
    private Room kitchenRoom;
    private Room diningRoom;
    private Room loungeRoom;
    private Room hallRoom;

    private List<Boolean>inRoom;

    /**
     * The Env.
     */
    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    /**
     * The Device.
     */
    GraphicsDevice device = env.getDefaultScreenDevice();
    /**
     * The Config.
     */
    GraphicsConfiguration config = device.getDefaultConfiguration();

    /**
     * The Candlestick image.
     */
    BufferedImage candlestickImage = config.createCompatibleImage(26, 26, Transparency.TRANSLUCENT);
    /**
     * The Daggerpre image.
     */
    BufferedImage daggerpreImage = config.createCompatibleImage(26, 26, Transparency.TRANSLUCENT);
    /**
     * The Pipepre image.
     */
    BufferedImage pipepreImage = config.createCompatibleImage(26, 26, Transparency.TRANSLUCENT);
    /**
     * The Revolverpre image.
     */
    BufferedImage revolverpreImage = config.createCompatibleImage(26, 26, Transparency.TRANSLUCENT);
    /**
     * The Ropepre image.
     */
    BufferedImage ropepreImage = config.createCompatibleImage(26, 26, Transparency.TRANSLUCENT);
    /**
     * The Wrenchpre image.
     */
    BufferedImage wrenchpreImage = config.createCompatibleImage(26, 26, Transparency.TRANSLUCENT);
    /**
     * The Candle image.
     */
    Image candleImage;
    /**
     * The Dagger image.
     */
    Image daggerImage;
    /**
     * The Pipe image.
     */
    Image pipeImage;
    /**
     * The Revolver image.
     */
    Image revolverImage;
    /**
     * The Rope image.
     */
    Image ropeImage;
    /**
     * The Wrench image.
     */
    Image wrenchImage;

    /**
     * The Med axe image pre.
     */
    BufferedImage medAxeImagePre = config.createCompatibleImage(26, 26, Transparency.TRANSLUCENT);
    /**
     * The Med bow image pre.
     */
    BufferedImage medBowImagePre = config.createCompatibleImage(26, 26, Transparency.TRANSLUCENT);
    /**
     * The Med cross image pre.
     */
    BufferedImage medCrossImagePre = config.createCompatibleImage(26, 26, Transparency.TRANSLUCENT);
    /**
     * The Med hammer image pre.
     */
    BufferedImage medHammerImagePre = config.createCompatibleImage(26, 26, Transparency.TRANSLUCENT);
    /**
     * The Med mace image pre.
     */
    BufferedImage medMaceImagePre  = config.createCompatibleImage(26, 26, Transparency.TRANSLUCENT);
    /**
     * The Med sword image pre.
     */
    BufferedImage medSwordImagePre = config.createCompatibleImage(26, 26, Transparency.TRANSLUCENT);
    /**
     * The Med axe image.
     */
    Image medAxeImage;
    /**
     * The Med bow image.
     */
    Image medBowImage;
    /**
     * The Med cross image.
     */
    Image medCrossImage;
    /**
     * The Med hammer image.
     */
    Image medHammerImage;
    /**
     * The Med mace image.
     */
    Image medMaceImage;
    /**
     * The Med sword image.
     */
    Image medSwordImage;

    /**
     * The Mine axe image pre.
     */
    BufferedImage mineAxeImagePre = config.createCompatibleImage(26, 26, Transparency.TRANSLUCENT);
    /**
     * The Mine bow image pre.
     */
    BufferedImage mineBowImagePre = config.createCompatibleImage(26, 26, Transparency.TRANSLUCENT);
    /**
     * The Mine cross image pre.
     */
    BufferedImage mineCrossImagePre = config.createCompatibleImage(26, 26, Transparency.TRANSLUCENT);
    /**
     * The Mine lava image pre.
     */
    BufferedImage mineLavaImagePre = config.createCompatibleImage(26, 26, Transparency.TRANSLUCENT);
    /**
     * The Mine pick image pre.
     */
    BufferedImage minePickImagePre  = config.createCompatibleImage(26, 26, Transparency.TRANSLUCENT);
    /**
     * The Mine sword image pre.
     */
    BufferedImage mineSwordImagePre = config.createCompatibleImage(26, 26, Transparency.TRANSLUCENT);
    /**
     * The Mine axe image.
     */
    Image mineAxeImage;
    /**
     * The Mine bow image.
     */
    Image mineBowImage;
    /**
     * The Mine cross image.
     */
    Image mineCrossImage;
    /**
     * The Mine lava image.
     */
    Image mineLavaImage;
    /**
     * The Mine pick image.
     */
    Image minePickImage;
    /**
     * The Mine sword image.
     */
    Image mineSwordImage;

    /**
     * The Setup complete.
     */
    Boolean setupComplete = false;

    /**
     * The Current board string.
     */
    String currentBoardString;
    /**
     * The Running game.
     */
    Game runningGame;
    /**
     * The Original board.
     */
    String originalBoard;
    /**
     * The Main f.
     */
//Main Jframe that will contain the board.
    JFrame mainF = new JFrame("Cluedo");

    private JPanel mainPanel;
    /**
     * The Main menu panel.
     */
    JPanel mainMenuPanel = new JPanel();

    /**
     * The Number of players.
     */
//Specify the number of players and the width/length of the board.
    int numberOfPlayers;
    /**
     * The Max board width.
     */
    final int maxBoardWidth = 24;
    /**
     * The Max board length.
     */
    final int maxBoardLength = 25;
    /**
     * The Board array.
     */
    String[] boardArray;
    /**
     * The Currently playing.
     */
    int currentlyPlaying = 0; //iterates up to 5 or number of players in game
    /**
     * The Popup.
     */
    JDialog popup;
    /**
     * The Accuse popup.
     */
    JDialog accusePopup;
    /**
     * The Picked character.
     */
    String pickedCharacter;
    /**
     * The Picked weapon.
     */
    String pickedWeapon;
    /**
     * The Accuse character.
     */
    String accuseCharacter;
    /**
     * The Accuse weapon.
     */
    String accuseWeapon;
    /**
     * The Accuse room.
     */
    String accuseRoom;
    /**
     * The Pick popup.
     */
    JDialog pickPopup;
    /**
     * The No cards container.
     */
    JPanel noCardsContainer;
    /**
     * The Cards container.
     */
    JPanel cardsContainer;
    /**
     * The Cards showing.
     */
    boolean cardsShowing;
    /**
     * The Die rolled.
     */
    boolean dieRolled;
    /**
     * The Accusation.
     */
    Accusation accusation;

    /**
     * Instantiates a new Game cycle.
     *
     * @param customBoard the custom board
     */
    public GameCycle(String customBoard) {
        Toolkit.getDefaultToolkit().sync();
        try {
            //Loads all the required assets for drawing images on the board.
            candlestickImage = ImageIO.read(new File("CANDLESTICK.png"));
            candleImage = candlestickImage.getScaledInstance(26, 26, Image.SCALE_FAST);


            daggerpreImage = ImageIO.read(new File("DAGGER.png"));
            daggerImage = daggerpreImage.getScaledInstance(26, 26, Image.SCALE_FAST);

            pipepreImage = ImageIO.read(new File("PIPE.png"));
            pipeImage = pipepreImage.getScaledInstance(26, 26, Image.SCALE_FAST);

            revolverpreImage = ImageIO.read(new File("REVOLVER.png"));
            revolverImage = revolverpreImage.getScaledInstance(26, 26, Image.SCALE_FAST);

            ropepreImage = ImageIO.read(new File("ROPE.png"));
            ropeImage = ropepreImage.getScaledInstance(26, 26, Image.SCALE_FAST);

            wrenchpreImage = ImageIO.read(new File("WRENCH.png"));
            wrenchImage = wrenchpreImage.getScaledInstance(26, 26, Image.SCALE_FAST);

            medAxeImagePre = ImageIO.read(new File("MEDIEVALaxe.png"));
            medBowImagePre = ImageIO.read(new File("MEDIEVALbow.png"));
            medCrossImagePre = ImageIO.read(new File("MEDIEVALcrossbow.png"));
            medHammerImagePre = ImageIO.read(new File("MEDIEVALhammer.png"));
            medMaceImagePre  = ImageIO.read(new File("MEDIEVALmace.png"));
            medSwordImagePre = ImageIO.read(new File("MEDIEVALsaber.png"));
            medAxeImage = medAxeImagePre.getScaledInstance(26, 26, Image.SCALE_FAST);
            medBowImage = medBowImagePre.getScaledInstance(26, 26, Image.SCALE_FAST);
            medCrossImage = medCrossImagePre.getScaledInstance(26, 26, Image.SCALE_FAST);
            medHammerImage = medHammerImagePre.getScaledInstance(26, 26, Image.SCALE_FAST);
            medMaceImage = medMaceImagePre.getScaledInstance(26, 26, Image.SCALE_FAST);
            medSwordImage = medSwordImagePre.getScaledInstance(26, 26, Image.SCALE_FAST);

            mineAxeImagePre = ImageIO.read(new File("MINECRAFTaxe.png"));
            mineBowImagePre = ImageIO.read(new File("MINECRAFTbow.png"));
            mineCrossImagePre = ImageIO.read(new File("MINECRAFTcrossbow.png"));
            mineLavaImagePre = ImageIO.read(new File("MINECRAFTlava.png"));
            minePickImagePre  = ImageIO.read(new File("MINECRAFTpickaxe.png"));
            mineSwordImagePre = ImageIO.read(new File("MINECRAFTsword.png"));
            mineAxeImage = mineAxeImagePre.getScaledInstance(26, 26, Image.SCALE_FAST);
            mineBowImage = mineBowImagePre.getScaledInstance(26, 26, Image.SCALE_FAST);
            mineCrossImage = mineCrossImagePre.getScaledInstance(26, 26, Image.SCALE_FAST);
            mineLavaImage = mineLavaImagePre.getScaledInstance(26, 26, Image.SCALE_FAST);
            minePickImage = minePickImagePre.getScaledInstance(26, 26, Image.SCALE_FAST);
            mineSwordImage = mineSwordImagePre.getScaledInstance(26, 26, Image.SCALE_FAST);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        currentBoardString = customBoard;
        originalBoard = customBoard;
        boardArray = customBoard.split("");

        createGUI();
    }

    /**
     * Refresh gui.
     * used to hide the cards in a players hand when a suggestion is being made and the other player must pick a card
     */
    public void refreshGUI() {
        mainF.setVisible(false);
        if (cardsShowing) {
            mainF.remove(cardsContainer);
        } else {
            mainF.remove(noCardsContainer);
        }
        mainF.add(noCardsContainer);
        mainF.pack();
        mainF.setVisible(true);
    }

    /**
     * Next player.
     * is used between rounds to iterate the current player
     */
    public void nextPlayer() {
        String playing = "Cluedo - Player 1";
        if (currentlyPlaying + 1 < numberOfPlayers) {
            currentlyPlaying++;
        } else {
            currentlyPlaying = 0;
        }
        playing = playing.substring(0, 16) + Integer.toString(currentlyPlaying + 1);
        mainF.setTitle(playing);
    }

    /**
     * Roll and update string.
     * retives all possible locations the player can reach based on their roll
     *      * and returns a string that can be used to update the board with highlighted reachable lcaotions
     *
     * @param roll the roll
     * @return board string with possible movements on it
     */
    public String rollAndUpdate(int roll) {

        String movementBoard;
        String boardString;

        movementBoard = runningGame.showMovableRange(currentlyPlaying, roll);
        boardString = updateCustomBoard(movementBoard);
        currentBoardString = boardString;
        System.out.println("You rolled a: " + roll);

        return boardString;
    }

    /**
     * Update custom board string.
     * converts string of reachable locations into a full board that can be displayed
     *
     * @param movement the movement
     * @return the string
     */
    public String updateCustomBoard(String movement) {
        String newBoard = null;
        StringBuilder newString = new StringBuilder(originalBoard);

        for (int i = 0; i < newString.length(); i++) {
            if (movement.charAt(i) == 'a') {
                newString.setCharAt(i, 'z');
            } else {

            }
        }
        return newString.toString();
    }

    /**
     * Display cards.
     * displays the cards that are in the current players hand
     *
     * @param oldContainer the old container
     */
    public void displayCards(JPanel oldContainer) {
        JPanel container;
        if (dieRolled) {
            container = defaultGUIState(originalBoard, 13);
        } else {
            container = defaultGUIState(originalBoard, 0);
        }
        JPanel cardHolder = new JPanel();
        GridBagConstraints cardHConstraints = new GridBagConstraints();
        Player pHolder = null;
        cardsShowing = true;
        ArrayList<CluedoEnums> playersCards;


        mainF.setVisible(false);
        mainF.remove(oldContainer);

        cardHConstraints.gridx = 1;
        cardHConstraints.gridy = 2;
        cardHConstraints.gridwidth = 2;
        cardHConstraints.weightx = 1;


        for (Player p : runningGame.getPlayers()) {
            if (currentlyPlaying == p.getUid()) pHolder = p;
        }
        playersCards = pHolder.getCards();
        cardHolder.setLayout(new GridLayout(1, playersCards.size()));



        int i = 0;
        for (CluedoEnums card : playersCards) {
            Icon newCard = card.getCardIcon();
            JLabel addCard = new JLabel(newCard);
            cardHolder.add(addCard);

            i++;
        }

        container.add(cardHolder, cardHConstraints);
        cardsContainer = container;
        mainF.add(container);
        mainF.pack();
        mainF.setVisible(true);
    }

    /**
     * Default gui state j panel.
     * is the default state of the GUI and is made up of all possible buttons and uses teh boardString to show where the
     *      * player can move or default board
     *
     * @param boardString the board string
     * @param dieRoll     the die roll
     * @return the j panel
     */
    public JPanel defaultGUIState(String boardString, int dieRoll) {
        BoardPane BoardPane = new BoardPane(boardString);
        Dice dice = new Dice();
        JPanel container = new JPanel();
        JPanel buttonsContainer = new JPanel();
        buttonsContainer.setLayout(new BorderLayout());
        DicePane DicePane = new DicePane(dice, container, BoardPane);
        container.setLayout(new GridBagLayout());
        GridBagConstraints BPConstraints = new GridBagConstraints();
        GridBagConstraints DPConstraints = new GridBagConstraints();
        GridBagConstraints ETConstraints = new GridBagConstraints();
        GridBagConstraints DConstraints = new GridBagConstraints();
        GridBagConstraints SCConstraints = new GridBagConstraints();
        GridBagConstraints AConstraints = new GridBagConstraints();
        JButton endTurn = new JButton("End Turn");
        JLabel displayLabel = new JLabel();
        JButton showCards = new JButton("Show Cards");
        JButton accuse = new JButton("Accusation!");

        BPConstraints.gridx = 1;
        BPConstraints.gridy = 0;
        BPConstraints.weightx = 1;
        container.add(BoardPane, BPConstraints);

        DPConstraints.gridx = 2;
        DPConstraints.gridy = 0;
        DPConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        DPConstraints.ipadx = 20;
        DPConstraints.ipady = 20;
        DPConstraints.insets = new Insets(0, 0, 0, 0);
        DPConstraints.weightx = 1;
        boolean dontShowRollButton = false;

        accuse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accusation = new Accusation(runningGame, accuseCharacter, accuseWeapon, accuseRoom, currentlyPlaying);
                accusation.accusationPopup();
            }
        });
        accuse.setPreferredSize(new Dimension(115, 50));

        if (accusation != null) {
            for (int i : accusation.getLostPlayers()) {
                if (i == currentlyPlaying) dontShowRollButton = true;
            }
        }
        if (!dontShowRollButton) {
            if (dieRoll == 0) {
                container.add(DicePane, DPConstraints);
            }
            buttonsContainer.add(accuse, BorderLayout.NORTH);
        }

        SCConstraints.gridx = 2;
        SCConstraints.gridy = 0;
        SCConstraints.anchor = GridBagConstraints.LAST_LINE_START;
        SCConstraints.fill = GridBagConstraints.HORIZONTAL;
        SCConstraints.ipady = 20;

        SCConstraints.weightx = 1;
        showCards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayCards(container);
            }
        });
        showCards.setPreferredSize(new Dimension(115, 50));
        buttonsContainer.add(showCards, BorderLayout.CENTER);

        ETConstraints.gridx = 2;
        ETConstraints.gridy = 0;
        ETConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        ETConstraints.weightx = 1;

        endTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endTurn(container, dice);
            }
        });
        endTurn.setPreferredSize(new Dimension(115, 50));
        buttonsContainer.add(endTurn, BorderLayout.SOUTH);

        if (dieRoll != 0 && dieRoll < 13) {
            DConstraints.gridx = 2;
            DConstraints.gridy = 0;
            DConstraints.anchor = GridBagConstraints.CENTER;
            DConstraints.insets = new Insets(0, 0, 0, 0);
            displayLabel.setText("You rolled a: " + dieRoll);
            buttonsContainer.add(displayLabel, BorderLayout.PAGE_START);
        }

        container.add(buttonsContainer, ETConstraints);
        noCardsContainer = container;
        return container;
    }

    /**
     * Create gui.
     * creates JFrame that will hold all other that come after and retives customisation from the user
     */
    public void createGUI() {

        Runnable runr = new Runnable() {

            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }


                mainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainF.setIconImage(new ImageIcon("SMALLLOGO.png").getImage());

                JLabel welcomeLabel = new JLabel("Welcome to Cluedo. The not a board game, game. Fun for the whole family!!");

                Icon welcImg = new ImageIcon("src/cluedo/cluedocover.jpeg");
                JLabel welcCover = new JLabel(welcImg);

                JButton optionsButton = new JButton("Start");
                JButton optionsButton2 = new JButton("Custom Game from ClueConfig");
                JButton exitButton = new JButton("Exit");
                ActionListener optionsAL = new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        Component createOptions = (Component) actionEvent.getSource();
                        while(!setupComplete) {
                            Object gmChoice = JOptionPane.showInputDialog(createOptions,
                                    "How many players?", "Options Screen 1",
                                    JOptionPane.QUESTION_MESSAGE, new ImageIcon("SMALLLOGO.png"), new String[]{"2", "3", "4", "5", "6"},
                                    "2");

                            System.out.println("Number of players: " + gmChoice);
                            if (gmChoice != null) {
                                numberOfPlayers = Integer.valueOf((String) gmChoice);
                                //setupComplete = true;
                            } else {
                                System.exit(0);
                            }

                            Object gmChoice2 = JOptionPane.showInputDialog(createOptions,
                                    "Choose game theme", "Game theme",
                                    JOptionPane.QUESTION_MESSAGE, new ImageIcon("SMALLLOGO.png"), new String[]{"Classic", "Medieval", "Minecraft"},
                                    "Classic");
                            gameTheme = gmChoice2.toString();

                            String[] playerColours = {"Yellow", "Red", "White", "Green", "Pink", "Black"};
                            List<String> pColours = new ArrayList<String>(Arrays.asList(playerColours));
                            for (int i = 0; i < 6; i++){
                                playerColors.add(Color.ORANGE);
                                playerNames.add("Mustard");
                            }
                            playerColors.set(0, p1Color);
                            playerColors.set(1, p2Color);
                            playerColors.set(2, p3Color);
                            playerColors.set(3, p4Color);
                            playerColors.set(4, p5Color);
                            playerColors.set(5, p6Color);
                            playerNames.set(0, "Mustard");
                            playerNames.set(1, "Peacock");
                            playerNames.set(2, "White");
                            playerNames.set(3, "Plum");
                            playerNames.set(4, "Scarlett");
                            playerNames.set(5, "Green");
                            for ( int i = 0; i < numberOfPlayers; i ++){
                                String playerName = (String) JOptionPane.showInputDialog(createOptions, "Enter Player " + (i + 1) + " name:", "What will it be?", JOptionPane.DEFAULT_OPTION, new ImageIcon("SMALLLOGO.png"), null, null);
                                if(playerName.length() > 1) {
                                    playerNames.set(i, playerName);
                                }
                            }

                            for( int i = 0; i < numberOfPlayers; i ++) {
                                Object gmChoice3 = JOptionPane.showInputDialog(createOptions,
                                        "Choose player colour", "Player " + (i + 1),
                                        JOptionPane.QUESTION_MESSAGE, new ImageIcon("SMALLLOGO.png"), playerColours,
                                        "Yellow");

                                if(gmChoice3 == "Yellow"){
                                    colorToAdd = Color.ORANGE;
                                }
                                else if (gmChoice3 == "Red"){
                                    colorToAdd = Color.RED;
                                }
                                else if (gmChoice3 == "White"){
                                    colorToAdd = Color.lightGray;
                                }
                                else if (gmChoice3 == "Green"){
                                    colorToAdd = Color.green;
                                }
                                else if (gmChoice3 == "Pink"){
                                    colorToAdd = Color.magenta;
                                }
                                else if (gmChoice3 == "Black"){
                                    colorToAdd = Color.BLACK;
                                }

                                pColours.remove(gmChoice3);
                                playerColors.set(i, colorToAdd);
                                System.out.println(colorToAdd);
                                playerColours = pColours.toArray(new String[pColours.size()]);
                                //playerColours.get(0));
                            }
                            customColors = true;
                            setupComplete = true;


                        }

                        mainF.setVisible(false);
                        mainF.remove(mainMenuPanel);
                        mainF.setTitle("Cluedo - Player 1");
                        System.out.println("Added boardpane remove mainMenuPanel");

                        runningGame = new Game(maxBoardWidth, maxBoardLength, numberOfPlayers, boardArray);
                        JPanel container = defaultGUIState(originalBoard, 0);

                        mainF.add(container);
                        mainF.pack();
                        mainF.setVisible(true);
                        mainF.setResizable(false);
                    }
                };

                ActionListener optionsAL2 = new ActionListener() {
                    String[] props;
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < 6; i++){
                            playerColors.add(Color.ORANGE);
                            playerNames.add("Mustard");
                        }
                        playerColors.set(0, p1Color);
                        playerColors.set(1, p2Color);
                        playerColors.set(2, p3Color);
                        playerColors.set(3, p4Color);
                        playerColors.set(4, p5Color);
                        playerColors.set(5, p6Color);
                        GetPropertyValues properties = new GetPropertyValues();
                        try {
                            props = properties.getPropValues();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        //numberOfPlayers = Integer.parseInt(props[0]);
                        if(props[0] == null){
                            System.out.println("Error with reading the config file");
                            System.exit(0);
                        }
                        System.out.println(props[0]);
                        gameTheme = props[1];
                        System.out.println(props[1]);
                        for (int i = 2; i < props.length; i++){
                            if(props[i] == "Yellow"){
                                colorToAdd = Color.ORANGE;
                            }
                            else if (props[i] == "Red"){
                                colorToAdd = Color.RED;
                            }
                            else if (props[i] == "White"){
                                colorToAdd = Color.lightGray;
                            }
                            else if (props[i] == "Green"){
                                colorToAdd = Color.green;
                            }
                            else if (props[i] == "Pink"){
                                colorToAdd = Color.magenta;
                            }
                            else if (props[i] == "Black"){
                                colorToAdd = Color.BLACK;
                            }

                            playerColors.set((i - 2), colorToAdd);

                        }
                        customColors = true;
                        setupComplete = true;

                        mainF.setVisible(false);
                        mainF.remove(mainMenuPanel);
                        mainF.setTitle("Cluedo - Player 1");
                        System.out.println("Added boardpane remove mainMenuPanel");

                        runningGame = new Game(maxBoardWidth, maxBoardLength, numberOfPlayers, boardArray);
                        JPanel container = defaultGUIState(originalBoard, 0);

                        mainF.add(container);
                        mainF.pack();
                        mainF.setVisible(true);
                        mainF.setResizable(false);

                    }
                };

                ActionListener optionsAL3 = new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        System.exit(0);
                    }
                };
                optionsButton.addActionListener(optionsAL);
                optionsButton2.addActionListener(optionsAL2);
                exitButton.addActionListener(optionsAL3);

                mainMenuPanel.add(welcomeLabel, BorderLayout.PAGE_START);
                mainMenuPanel.add(optionsButton, BorderLayout.SOUTH);
                mainMenuPanel.add(optionsButton2, BorderLayout.SOUTH);
                mainMenuPanel.add(exitButton, BorderLayout.EAST);
                mainMenuPanel.add(welcCover, BorderLayout.SOUTH);
                mainF.add(mainMenuPanel);
                mainF.setSize(605,415);
                mainF.setVisible(true);
            }
        };
        runr.run();
    }

    /**
     * End turn.
     * iterates to the next player and resets the screen for the new player
     *
     * @param container the container
     * @param dice      the dice
     */
    public void endTurn(JPanel container, Dice dice) {
        nextPlayer();
        cardsShowing = false;
        dieRolled = false;
        mainF.setVisible(false);
        mainF.remove(container);

        //Player positions being saved to be restored to cells2 on next turn instead of re-spawning the players.
        cells2Replace = new ArrayList<>(cells2);

        //CHANGE

        //playersSpawned is set to true so that players are not spawned again.
        playersSpawned = true;
        weaponsSpawned = true; // was false??

        container = defaultGUIState(originalBoard, 0);

        mainF.add(container);

        mainF.pack();
        mainF.setVisible(true);
    }

    /**
     * The type Dice pane.
     */
    public class DicePane extends JPanel {
        private final Dice dice;

        private JButton rollButton;
        private JLabel displayLabel;
        private JButton endTurn;

        /**
         * Instantiates a new Dice pane.
         *
         * @param dice      the dice
         * @param container the container
         * @param BoardPane the board pane
         */
        public DicePane(Dice dice, JPanel container, BoardPane BoardPane) {
            this.dice = dice;

            //dice icons
            Icon diceR2 = new ImageIcon("src/cluedo/dicepngs/dice2.png");
            Icon diceR3 = new ImageIcon("src/cluedo/dicepngs/dice3.png");
            Icon diceR32 = new ImageIcon("src/cluedo/dicepngs/dice32.png");
            Icon diceR4 = new ImageIcon("src/cluedo/dicepngs/dice4.png");
            Icon diceR42 = new ImageIcon("src/cluedo/dicepngs/dice42.png");
            Icon diceR5 = new ImageIcon("src/cluedo/dicepngs/dice5.png");
            Icon diceR52 = new ImageIcon("src/cluedo/dicepngs/dice52.png");
            Icon diceR53 = new ImageIcon("src/cluedo/dicepngs/dice53.png");
            Icon diceR6 = new ImageIcon("src/cluedo/dicepngs/dice6.png");
            Icon diceR62 = new ImageIcon("src/cluedo/dicepngs/dice62.png");
            Icon diceR63 = new ImageIcon("src/cluedo/dicepngs/dice63.png");
            Icon diceR7 = new ImageIcon("src/cluedo/dicepngs/dice7.png");
            Icon diceR72 = new ImageIcon("src/cluedo/dicepngs/dice72.png");
            Icon diceR73 = new ImageIcon("src/cluedo/dicepngs/dice73.png");
            Icon diceR74 = new ImageIcon("src/cluedo/dicepngs/dice74.png");
            Icon diceR8 = new ImageIcon("src/cluedo/dicepngs/dice8.png");
            Icon diceR82 = new ImageIcon("src/cluedo/dicepngs/dice82.png");
            Icon diceR83 = new ImageIcon("src/cluedo/dicepngs/dice83.png");
            Icon diceR84 = new ImageIcon("src/cluedo/dicepngs/dice84.png");
            Icon diceR9 = new ImageIcon("src/cluedo/dicepngs/dice9.png");
            Icon diceR92 = new ImageIcon("src/cluedo/dicepngs/dice92.png");
            Icon diceR93 = new ImageIcon("src/cluedo/dicepngs/dice93.png");
            Icon diceR10 = new ImageIcon("src/cluedo/dicepngs/dice10.png");
            Icon diceR102 = new ImageIcon("src/cluedo/dicepngs/dice102.png");
            Icon diceR11 = new ImageIcon("src/cluedo/dicepngs/dice11.png");
            Icon diceR112 = new ImageIcon("src/cluedo/dicepngs/dice112.png");
            Icon diceR12 = new ImageIcon("src/cluedo/dicepngs/dice12.png");

            Icon[] R2dice = {diceR2};
            Icon[] R3dice = {diceR3, diceR32};
            Icon[] R4dice = {diceR4, diceR42};
            Icon[] R5dice = {diceR5,diceR52,diceR53};
            Icon[] R6dice = {diceR6,diceR62,diceR63};
            Icon[] R7dice = {diceR7,diceR72,diceR73,diceR74};
            Icon[] R8dice = {diceR8,diceR82,diceR83,diceR84};
            Icon[] R9dice = {diceR9,diceR92,diceR93};
            Icon[] R10dice = {diceR10,diceR102};
            Icon[] R11dice = {diceR11,diceR112};
            Icon[] R12dice = {diceR12};

            Icon basicRoll = new ImageIcon("src/cluedo/dicepngs/dice2.png");

            Icon rollResult = basicRoll;
            if(diceRoll == 2){
                rollResult = R2dice[0];
            }
            if(diceRoll == 3){
                rollResult = R3dice[(int)Math.round(Math.random()*(R3dice.length-1))];
            }
            if(diceRoll == 4){
                rollResult = R4dice[(int)Math.round(Math.random()*(R4dice.length-1))];

            }
            if(diceRoll == 5){
                rollResult = R5dice[(int)Math.round(Math.random()*(R5dice.length-1))];

            }
            if(diceRoll == 6){
                rollResult = R6dice[(int)Math.round(Math.random()*(R6dice.length-1))];

            }
            if(diceRoll == 7){
                rollResult = R7dice[(int)Math.round(Math.random()*(R7dice.length-1))];

            }
            if(diceRoll == 8){
                rollResult = R8dice[(int)Math.round(Math.random()*(R8dice.length-1))];

            }
            if(diceRoll == 9){
                rollResult = R9dice[(int)Math.round(Math.random()*(R9dice.length-1))];

            }
            if(diceRoll == 10){
                rollResult = R10dice[(int)Math.round(Math.random()*(R10dice.length-1))];

            }
            if(diceRoll == 11){
                rollResult = R11dice[(int)Math.round(Math.random()*(R11dice.length-1))];

            }
            if(diceRoll == 12){
                rollResult = R12dice[0];

            }
            diceRoll = dice.roll();

            JLabel rollLabel = new JLabel(rollResult);

            Icon diceJPEG = new ImageIcon("src/cluedo/DiceGif1.jpeg");
            rollButton = new JButton("Roll Die");
            displayLabel = new JLabel();
            JLabel gifLabelStill = new JLabel(diceJPEG);
            add(gifLabelStill);
            rollButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gifLabelStill.show();
                    rollLabel.hide();
                    int delayTime = 670; // for 500 msecs
                    new Timer(delayTime, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // TODO: code to be delayed goes here

                            //Player positions being saved to be restored to cells2 on next turn instead of re-spawning the players.


                            //CHANGE
                            weaponCells2Replace = new ArrayList<>(weaponCells);
                            cardsShowing = false;
                            dieRolled = true;

                            gifLabelStill.hide();
                            remove(gifLabelStill);
                            // stop the timer from repeating
                            ((Timer) e.getSource()).stop();

                            //show player reachable locations
                            String boardString = rollAndUpdate(diceRoll);
                            mainF.setVisible(false);
                            mainF.remove(container);


                            //playersSpawned is set to true so that players are not spawned again.
                            playersSpawned = true;
                            weaponsSpawned = true;

                            JPanel container = defaultGUIState(boardString, diceRoll);

                            mainF.add(container);

                            mainF.pack();
                            mainF.setVisible(true);
                        }
                    }).start();

                }
            });
            setLayout(new GridLayout(3, 1));

            add(rollLabel);
            gifLabelStill.hide();
            add(rollButton);

        }
    }

    /**
     * The type Board pane.
     */
    //Handles the GUI board data, refreshing and redrawing the board visually,
    public class BoardPane extends JPanel {

        /**
         * The constant ROWS.
         */
        protected static final int ROWS = 25;
        /**
         * The constant COLS.
         */
        protected static final int COLS = 24;
        /**
         * The constant BOX_SIZE.
         */
        protected static final int BOX_SIZE = 26;

        private List<Rectangle> cells;

        private List<Integer> playersPieces;
        private Point selectedCell;
        private Point lastSelectedCell;
        private boolean newPositionAvailable;

        //Holder for the actual Rectangle of a player (i.e. Selected Player: java.awt.Rectangle[x=234,y=364,width=26,height=26])
        private Rectangle selectedPlayer = null;

        private Rectangle newPosition;

        //Represents the Id of the selected player: ranges from 0 to 6 and is -1 when no player is selected.
        private int selectedPlayerId = -1;
        private Point cellClick;
        private java.util.List<Color> colors;
        private Tile[][] finalBoard;


        /**
         * The Numbers representing red for Color generation.
         */
        //Integer lists that represent R G B for drawing the colour board.
        java.util.List<Integer> numbers = new ArrayList<>();
        /**
         * The Numbers representing green for Color generation.
         */
        java.util.List<Integer> numbers2 = new ArrayList<>();
        /**
         * The Numbers representing blue for Color generation.
         */
        List<Integer> numbers3 = new ArrayList<>();

        /**
         * The Suggestion.
         */
        //initalise Suggestion class for making a suggestion when going in a room
        Suggestion suggestion;

        /**
         * Instantiates a new Board pane.
         *
         * @param boardString the board string
         */
        public BoardPane(String boardString) {

            int length = ROWS * COLS;
            String[] board = boardString.split("");

            //Colors that will be utilised by Rectangle objects of the cells array.
            colors = new ArrayList<>(length);

            //Cells will contain Rectangle objects.
            cells = new ArrayList<>(length);

            //Cells which do not allow regular player movement
            noAccessCells = new ArrayList<>(length);

            //Cells of the middle section
            middleCells = new ArrayList<>(length);

            //Stores weapon Rectangle cells.
            weaponCells = new ArrayList<>();

            //Sets all arraylists to contain door cells for the respective door.
            if(doorsSet == false) {
                studyDoorCells = new ArrayList<>();
                libraryDoorCells = new ArrayList<>();
                billiardDoorCells = new ArrayList<>();
                ballroomDoorCells = new ArrayList<>();
                kitchenDoorCells = new ArrayList<>();
                diningDoorCells = new ArrayList<>();
                loungeDoorCells = new ArrayList<>();
                hallDoorCells = new ArrayList<>();
                conservatoryDoorCells = new ArrayList<>();
                allDoorCells = new ArrayList<>();
                doorsSet = true;
            }


            //Will hold the cells belonging to each room.
            studyRoomCells = new ArrayList<>();
            libraryRoomCells = new ArrayList<>();
            billiardRoomCells = new ArrayList<>();
            ballroomRoomCells = new ArrayList<>();
            kitchenRoomCells = new ArrayList<>();
            diningRoomCells = new ArrayList<>();
            loungeRoomCells = new ArrayList<>();
            hallRoomCells = new ArrayList<>();
            conservatoryRoomCells = new ArrayList<>();
            allRoomCells = new ArrayList<>();


            //Initalising ArrayLists that will contain:
            //cells2: Player rectangle cells
            //inRoom: Booleans for each player to mark if they're in a room or not.
            //cells2Replace: Used as storage for cells2 in between board redraw's so that player positions are restored.
            if(playersSpawned == false) {
                cells2 = new ArrayList<>(length);
                inRoom = new ArrayList<>(6);
                cells2Replace = new ArrayList<>(cells2);
            }

            //Initialises ArrayLists that will contain weapon Rectangle cells
            //weaponCells2Replace is being used as storage for weaponCells in between board refresh/redraw.
            if(weaponsSpawned == false){
                weaponCells = new ArrayList<>();
                weaponCells2Replace = new ArrayList<>(weaponCells);
            }
            else{ weaponCells = new ArrayList<>(weaponCells2Replace);

            }

            //Represents players as numbers 1-6 to represent their location on the board, with 0 representing no player in that location.
            playersPieces = new ArrayList<>();
            int playerCounter = 0;
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    if(playerCounter < numberOfPlayers) {
                        if (row == 8) {
                            if (col == 9) {
                                playersPieces.add(1);
                                playerCounter++;

                            }
                            if (col == 13) {
                                playersPieces.add(2);
                                playerCounter++;
                            }
                        }
                        if (row == 11) {
                            if (col == 9) {
                                playersPieces.add(3);
                                playerCounter++;
                            }
                            if (col == 13) {
                                playersPieces.add(4);
                                playerCounter++;
                            }
                        }
                        if (row == 14) {
                            if (col == 9) {
                                playersPieces.add(5);
                                playerCounter++;
                            }
                            if (col == 13) {
                                playersPieces.add(6);
                                playerCounter++;
                            }
                        }
                        else {
                            playersPieces.add(0);
                        }
                    }
                    else{
                        playersPieces.add(0);
                    }


                }

            }
            System.out.println(playersPieces);

            //-------All mouse functions,------
            /*
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
             */
            // ---------------------------------
            // currently using mouseReleased (clicking) and mouseMoved (selecting) for hovering over Rectangles and
            // turning them white..
            // Mouse events also trigger location updates, board redraws, and various game logic that needs to be considered upon a mouse event.
            MouseAdapter mouseHandler;
            mouseHandler = new MouseAdapter() {

                //Overrides the function of mouse being moved.
                @Override
                public void mouseMoved(MouseEvent e) {
                    //Weapons only displayed when mouse is hovered over the middle portion of the board due to performance issues of keeping them permanently on the board.
                    displayWeapons = false;
                    Point point = e.getPoint();

                    //Width and Height of a Rectangle cell.
                    int width = getWidth();
                    int height = getHeight();

                    //Splits the width & height of the 2dgraphics into Rectangles.
                    int cellWidth = width / COLS;
                    int cellHeight = height / ROWS;

                    //Specifies the whole clickable / hoverable area of each Rectangle.
                    int xOffset = (width - (COLS * cellWidth)) / 2;
                    int yOffset = (height - (ROWS * cellHeight)) / 2;

                    //Selecting a cell to be painted white.
                    lastSelectedCell = selectedCell;
                    selectedCell = null;
                    //Creation of a Point object that can be used to check if a Rectangle contains it.
                    if (e.getX() >= xOffset && e.getY() >= yOffset) {

                        int column = (e.getX() - xOffset) / cellWidth;
                        int row = (e.getY() - yOffset) / cellHeight;

                        if (column >= 0 && row >= 0 && column < COLS && row < ROWS) {

                            selectedCell = new Point(column, row);

                        }

                    }

                    //Redrawing weapons when the mouse is hovered over the middle portion of the board.
                    for(int i = 0; i < middleCells.size(); i++){
                        Rectangle cell = middleCells.get(i);
                        if (cell.contains(point)){
                            displayWeapons = true;
                            //repaint();
                        }
                    }

                    //Repaints when a new cell is selected and when some necessary Game logic finishes.
                    if(selectedCell != lastSelectedCell) {
                        repaint();
                    }

                }
                @Override
                //Mouse handler for mouse clicks.
                public void mouseReleased(MouseEvent e)
                {
                    Point point = e.getPoint();


                    int width = getWidth();
                    int height = getHeight();

                    int cellWidth = width / COLS;
                    int cellHeight = height / ROWS;

                    int xOffset = (width - (COLS * cellWidth)) / 2;
                    int yOffset = (height - (ROWS * cellHeight)) / 2;

                    cellClick = null;
                    //Generation of a cellClick object to be used in checking if a Rectangle contains a mouse click.
                    if (e.getX() >= xOffset && e.getY() >= yOffset) {

                        int column = (e.getX() - xOffset) / cellWidth;
                        int row = (e.getY() - yOffset) / cellHeight;

                        if (column >= 0 && row >= 0 && column < COLS && row < ROWS) {

                            cellClick = new Point(column, row);

                        }

                    }

                    int thisSelectedIndex = -1;
                    int thisClickedIndex = -1;

                    //If clicked, index is selected
                    if (selectedCell != null) {
                        thisSelectedIndex = selectedCell.x + (selectedCell.y * COLS);
                    }

                    if (cellClick != null) {
                        thisClickedIndex = cellClick.x + (cellClick.y * COLS);
                    }

                    if (selectedPlayer != null){
                        //cells2.add(selectedPlayer);
                    }

                    //Gets the currently selected player from running game.
                    Player selectedP = runningGame.players.get(currentlyPlaying);
                    int selectedPX = selectedP.getX();
                    int selectedPY = selectedP.getY();
                    selectedPlayerId = selectedP.getUid();
                    System.out.println("Selected player: " + (selectedPlayerId + 1));


                    for (int row = 0; row < ROWS; row++) {
                        for (int col = 0; col < COLS; col++) {
                            int index = (row * COLS) + col;
                            if (index == thisSelectedIndex) {

                                //Selects rectangle cell using index. Cells.get returns the rectangle that would have the coordinates.
                                //The coordinates are the top left point of each Rectangle.
                                //For square [x=338,y=251,width=26,height=24]
                                //The square below is [x=338,y=275,width=26,height=24]
                                //Difference of 24 as that is the height of each Rectangle.

                                Rectangle cell = cells.get(index);

                                if (cell.contains(point))  {
                                    System.out.println("Clicked square: " + cell);
                                    Player currentPlayer = runningGame.players.get(currentlyPlaying);
                                    //updates player location based on runninggame.players arraylist players.get(currentyplaying).getX .getY * 26
                                }
                                else System.out.println("Error");

                                if (selectedPlayerId >= 0){
                                    boolean canMove = true;

                                    boolean inaccessible = false;
                                    //Prevents players from being able to move on top of other players.
                                    for (int i = 0; i < cells2.size(); i++){
                                        Rectangle cell2 = cells2.get(i);
                                        if (cell2.contains(point)){
                                            canMove = false;
                                            System.out.println("Can't move on to a tile already occupied by another player!");
                                        }
                                    }

                                    //Prevents players from moving into rooms without using doors.
                                    for (int i = 0; i < noAccessCells.size(); i++){
                                        Rectangle noAccessCell = noAccessCells.get(i);
                                        if (noAccessCell.contains(point)){
                                            canMove = false;
                                            System.out.println("Unable to move - to access a room you must use a room door!");
                                        }
                                    }

                                    //Prevents players from moving outside of their highlight area.
                                    if(colors.get(index).getRGB() != -11513776){
                                        canMove = false;
                                        inaccessible = true;
                                        System.out.println("Unable to move - You can only move in the highlighted area!");
                                    }


                                    //All of these for loops are responsible for allocating players to a location inside the room
                                    //when they move on to a room door cell.
                                    for (int i = 0; i < studyDoorCells.size(); i++){
                                        Rectangle doorCell = studyDoorCells.get(i);
                                        if (doorCell.contains(point)){
                                            canMove = false;
                                            Rectangle rect = doorCell;
                                            if(selectedPlayerId == 0){
                                                rect = new Rectangle(52,26,26,26);
                                            }
                                            if(selectedPlayerId == 1){
                                                rect = new Rectangle(52,52,26,26);
                                            }
                                            if(selectedPlayerId == 2){
                                                rect = new Rectangle(78,26,26,26);
                                            }
                                            if(selectedPlayerId == 3){
                                                rect = new Rectangle(78,52,26,26);
                                            }
                                            if(selectedPlayerId == 4){
                                                rect = new Rectangle(104,26,26,26);
                                            }
                                            if(selectedPlayerId == 5){
                                                rect = new Rectangle(104,52,26,26);
                                            }
                                            //Handles players entering and leaving the study.
                                            if(!inaccessible) {
                                                if(!inRoom.get(selectedPlayerId)) {
                                                    cells2.set(selectedPlayerId, rect);
                                                    runningGame.clickedLocation(cells.get(index), currentlyPlaying);
                                                    System.out.println("Player " + (selectedPlayerId + 1) + " enters the Study.");
                                                    inRoom.set(selectedPlayerId, true);
                                                    refreshGUI();
                                                    suggestion = new Suggestion(runningGame, pickedCharacter, pickedWeapon, currentlyPlaying);
                                                    suggestion.openSugestionPopup();
                                                }
                                                //Handles players leaving a room if they're in a room.
                                                else{
                                                    cells2.set(selectedPlayerId, cells.get(index));
                                                    runningGame.clickedLocation(cells.get(index), currentlyPlaying);
                                                    System.out.println("Player " + (selectedPlayerId + 1) + " leaves the Study.");
                                                    inRoom.set(selectedPlayerId, false);
                                                }
                                                selectedPlayer = null;
                                                selectedPlayerId = -1;
                                            }
                                        }
                                    }

                                    //Same as above but for library.
                                    for (int i = 0; i < libraryDoorCells.size(); i++){
                                        Rectangle doorCell = libraryDoorCells.get(i);
                                        if (doorCell.contains(point)){
                                            canMove = false;
                                            Rectangle rect = doorCell;
                                            if(selectedPlayerId == 0){
                                                rect = new Rectangle(52,182,26,26);
                                            }
                                            if(selectedPlayerId == 1){
                                                rect = new Rectangle(52,208,26,26);
                                            }
                                            if(selectedPlayerId == 2){
                                                rect = new Rectangle(78,208,26,26);
                                            }
                                            if(selectedPlayerId == 3){
                                                rect = new Rectangle(78,182,26,26);
                                            }
                                            if(selectedPlayerId == 4){
                                                rect = new Rectangle(104,182,26,26);
                                            }
                                            if(selectedPlayerId == 5){
                                                rect = new Rectangle(104,208,26,26);
                                            }

                                            if(!inaccessible) {
                                                if(!inRoom.get(selectedPlayerId)) {
                                                    cells2.set(selectedPlayerId, rect);
                                                    runningGame.clickedLocation(cells.get(index), currentlyPlaying);
                                                    System.out.println("Player " + (selectedPlayerId + 1) + " enters the Library.");
                                                    inRoom.set(selectedPlayerId, true);
                                                    suggestion = new Suggestion(runningGame, pickedCharacter, pickedWeapon, currentlyPlaying);
                                                    suggestion.openSugestionPopup();
                                                }
                                                else{
                                                    cells2.set(selectedPlayerId, cells.get(index));
                                                    runningGame.clickedLocation(cells.get(index), currentlyPlaying);
                                                    System.out.println("Player " + (selectedPlayerId + 1) + " leaves the Library.");
                                                    inRoom.set(selectedPlayerId, false);
                                                }
                                                selectedPlayer = null;
                                                selectedPlayerId = -1;
                                            }
                                        }
                                    }

                                    for (int i = 0; i < billiardDoorCells.size(); i++){
                                        Rectangle doorCell = billiardDoorCells.get(i);
                                        if (doorCell.contains(point)){
                                            canMove = false;
                                            Rectangle rect = doorCell;
                                            if(selectedPlayerId == 0){
                                                rect = new Rectangle(26,364,26,26);
                                            }
                                            if(selectedPlayerId == 1){
                                                rect = new Rectangle(26,390,26,26);
                                            }
                                            if(selectedPlayerId == 2){
                                                rect = new Rectangle(52,364,26,26);
                                            }
                                            if(selectedPlayerId == 3){
                                                rect = new Rectangle(52,390,26,26);
                                            }
                                            if(selectedPlayerId == 4){
                                                rect = new Rectangle(78,364,26,26);
                                            }
                                            if(selectedPlayerId == 5){
                                                rect = new Rectangle(78,390,26,26);
                                            }

                                            if(!inaccessible) {
                                                if(!inRoom.get(selectedPlayerId)) {
                                                    cells2.set(selectedPlayerId, rect);
                                                    runningGame.clickedLocation(cells.get(index), currentlyPlaying);
                                                    System.out.println("Player " + (selectedPlayerId + 1) + " enters the Billiard Room.");
                                                    inRoom.set(selectedPlayerId, true);
                                                    suggestion = new Suggestion(runningGame, pickedCharacter, pickedWeapon, currentlyPlaying);
                                                    suggestion.openSugestionPopup();
                                                }
                                                else{
                                                    cells2.set(selectedPlayerId, cells.get(index));
                                                    runningGame.clickedLocation(cells.get(index), currentlyPlaying);
                                                    System.out.println("Player " + (selectedPlayerId + 1) + " leaves the Billiard Room.");
                                                    inRoom.set(selectedPlayerId, false);
                                                }
                                                selectedPlayer = null;
                                                selectedPlayerId = -1;
                                            }
                                        }
                                    }

                                    for (int i = 0; i < conservatoryDoorCells.size(); i++){
                                        Rectangle doorCell = conservatoryDoorCells.get(i);
                                        if (doorCell.contains(point)){
                                            canMove = false;
                                            Rectangle rect = doorCell;
                                            if(selectedPlayerId == 0){
                                                rect = new Rectangle(52,546,26,26);
                                            }
                                            if(selectedPlayerId == 1){
                                                rect = new Rectangle(78,546,26,26);
                                            }
                                            if(selectedPlayerId == 2){
                                                rect = new Rectangle(52,572,26,26);
                                            }
                                            if(selectedPlayerId == 3){
                                                rect = new Rectangle(78,572,26,26);
                                            }
                                            if(selectedPlayerId == 4){
                                                rect = new Rectangle(52,598,26,26);
                                            }
                                            if(selectedPlayerId == 5){
                                                rect = new Rectangle(78,598,26,26);
                                            }

                                            if(!inaccessible) {
                                                if(!inRoom.get(selectedPlayerId)) {
                                                    cells2.set(selectedPlayerId, rect);
                                                    runningGame.clickedLocation(cells.get(index), currentlyPlaying);
                                                    System.out.println("Player " + (selectedPlayerId + 1) + " enters the Conservatory.");
                                                    inRoom.set(selectedPlayerId, true);
                                                    suggestion = new Suggestion(runningGame, pickedCharacter, pickedWeapon, currentlyPlaying);
                                                    suggestion.openSugestionPopup();
                                                }
                                                else{
                                                    cells2.set(selectedPlayerId, cells.get(index));
                                                    runningGame.clickedLocation(cells.get(index), currentlyPlaying);
                                                    System.out.println("Player " + (selectedPlayerId + 1) + " leaves the Conservatory.");
                                                    inRoom.set(selectedPlayerId, false);
                                                }
                                                selectedPlayer = null;
                                                selectedPlayerId = -1;
                                            }
                                        }
                                    }

                                    for (int i = 0; i < ballroomDoorCells.size(); i++){
                                        Rectangle doorCell = ballroomDoorCells.get(i);
                                        if (doorCell.contains(point)){
                                            canMove = false;
                                            Rectangle rect = doorCell;
                                            if(selectedPlayerId == 0){
                                                rect = new Rectangle(286,494,26,26);
                                            }
                                            if(selectedPlayerId == 1){
                                                rect = new Rectangle(312,494,26,26);
                                            }
                                            if(selectedPlayerId == 2){
                                                rect = new Rectangle(286,520,26,26);
                                            }
                                            if(selectedPlayerId == 3){
                                                rect = new Rectangle(312,520,26,26);
                                            }
                                            if(selectedPlayerId == 4){
                                                rect = new Rectangle(286,546,26,26);
                                            }
                                            if(selectedPlayerId == 5){
                                                rect = new Rectangle(312,546,26,26);
                                            }
                                            if(!inaccessible) {
                                                if(!inRoom.get(selectedPlayerId)) {
                                                    cells2.set(selectedPlayerId, rect);
                                                    runningGame.clickedLocation(cells.get(index), currentlyPlaying);
                                                    System.out.println("Player " + (selectedPlayerId + 1) + " enters the Ballroom.");
                                                    inRoom.set(selectedPlayerId, true);
                                                    suggestion = new Suggestion(runningGame, pickedCharacter, pickedWeapon, currentlyPlaying);
                                                    suggestion.openSugestionPopup();
                                                }
                                                else{
                                                    cells2.set(selectedPlayerId, cells.get(index));
                                                    runningGame.clickedLocation(cells.get(index), currentlyPlaying);
                                                    System.out.println("Player " + (selectedPlayerId + 1) + " leaves the Ballroom.");
                                                    inRoom.set(selectedPlayerId, false);
                                                }
                                                selectedPlayer = null;
                                                selectedPlayerId = -1;
                                            }
                                        }
                                    }

                                    for (int i = 0; i < kitchenDoorCells.size(); i++){
                                        Rectangle doorCell = kitchenDoorCells.get(i);
                                        if (doorCell.contains(point)){
                                            canMove = false;
                                            Rectangle rect = doorCell;
                                            if(selectedPlayerId == 0){
                                                rect = new Rectangle(520,520,26,26);
                                            }
                                            if(selectedPlayerId == 1){
                                                rect = new Rectangle(546,520,26,26);
                                            }
                                            if(selectedPlayerId == 2){
                                                rect = new Rectangle(520,546,26,26);
                                            }
                                            if(selectedPlayerId == 3){
                                                rect = new Rectangle(546,546,26,26);
                                            }
                                            if(selectedPlayerId == 4){
                                                rect = new Rectangle(520,572,26,26);
                                            }
                                            if(selectedPlayerId == 5){
                                                rect = new Rectangle(546,572,26,26);
                                            }
                                            if(!inaccessible) {
                                                if(!inRoom.get(selectedPlayerId)) {
                                                    cells2.set(selectedPlayerId, rect);
                                                    runningGame.clickedLocation(cells.get(index), currentlyPlaying);
                                                    System.out.println("Player " + (selectedPlayerId + 1) + " enters the Kitchen.");
                                                    inRoom.set(selectedPlayerId, true);
                                                    suggestion = new Suggestion(runningGame, pickedCharacter, pickedWeapon, currentlyPlaying);
                                                    suggestion.openSugestionPopup();
                                                }
                                                else{
                                                    cells2.set(selectedPlayerId, cells.get(index));
                                                    runningGame.clickedLocation(cells.get(index), currentlyPlaying);
                                                    System.out.println("Player " + (selectedPlayerId + 1) + " leaves the Kitchen.");
                                                    inRoom.set(selectedPlayerId, false);
                                                }
                                                selectedPlayer = null;
                                                selectedPlayerId = -1;
                                            }
                                        }
                                    }

                                    for (int i = 0; i < diningDoorCells.size(); i++){
                                        Rectangle doorCell = diningDoorCells.get(i);
                                        if (doorCell.contains(point)){
                                            canMove = false;
                                            Rectangle rect = doorCell;
                                            if(selectedPlayerId == 0){
                                                rect = new Rectangle(494,286,26,26);
                                            }
                                            if(selectedPlayerId == 1){
                                                rect = new Rectangle(494,312,26,26);
                                            }
                                            if(selectedPlayerId == 2){
                                                rect = new Rectangle(520,286,26,26);
                                            }
                                            if(selectedPlayerId == 3){
                                                rect = new Rectangle(520,312,26,26);
                                            }
                                            if(selectedPlayerId == 4){
                                                rect = new Rectangle(546,286,26,26);
                                            }
                                            if(selectedPlayerId == 5){
                                                rect = new Rectangle(546,312,26,26);
                                            }
                                            if(!inaccessible) {
                                                if(!inRoom.get(selectedPlayerId)) {
                                                    cells2.set(selectedPlayerId, rect);
                                                    runningGame.clickedLocation(cells.get(index), currentlyPlaying);
                                                    System.out.println("Player " + (selectedPlayerId + 1) + " enters the Dining Room.");
                                                    inRoom.set(selectedPlayerId, true);
                                                    suggestion = new Suggestion(runningGame, pickedCharacter, pickedWeapon, currentlyPlaying);
                                                    suggestion.openSugestionPopup();
                                                }
                                                else{
                                                    cells2.set(selectedPlayerId, cells.get(index));
                                                    runningGame.clickedLocation(cells.get(index), currentlyPlaying);
                                                    System.out.println("Player " + (selectedPlayerId + 1) + " leaves the Dining Room.");
                                                    inRoom.set(selectedPlayerId, false);
                                                }
                                                selectedPlayer = null;
                                                selectedPlayerId = -1;
                                            }
                                        }
                                    }

                                    for (int i = 0; i < loungeDoorCells.size(); i++){
                                        Rectangle doorCell = loungeDoorCells.get(i);
                                        if (doorCell.contains(point)){
                                            canMove = false;
                                            Rectangle rect = doorCell;
                                            if(selectedPlayerId == 0){
                                                rect = new Rectangle(494,78,26,26);
                                            }
                                            if(selectedPlayerId == 1){
                                                rect = new Rectangle(494,52,26,26);
                                            }
                                            if(selectedPlayerId == 2){
                                                rect = new Rectangle(520,52,26,26);
                                            }
                                            if(selectedPlayerId == 3){
                                                rect = new Rectangle(520,78,26,26);
                                            }
                                            if(selectedPlayerId == 4){
                                                rect = new Rectangle(546,78,26,26);
                                            }
                                            if(selectedPlayerId == 5){
                                                rect = new Rectangle(546,52,26,26);
                                            }
                                            if(!inaccessible) {
                                                if(!inRoom.get(selectedPlayerId)) {
                                                    cells2.set(selectedPlayerId, rect);
                                                    runningGame.clickedLocation(cells.get(index), currentlyPlaying);
                                                    System.out.println("Player " + (selectedPlayerId + 1) + " enters the Lounge.");
                                                    inRoom.set(selectedPlayerId, true);
                                                    suggestion = new Suggestion(runningGame, pickedCharacter, pickedWeapon, currentlyPlaying);
                                                    suggestion.openSugestionPopup();
                                                }
                                                else{
                                                    cells2.set(selectedPlayerId, cells.get(index));
                                                    runningGame.clickedLocation(cells.get(index), currentlyPlaying);
                                                    System.out.println("Player " + (selectedPlayerId + 1) + " leaves the Lounge.");
                                                    inRoom.set(selectedPlayerId, false);
                                                }
                                                selectedPlayer = null;
                                                selectedPlayerId = -1;
                                            }
                                        }
                                    }

                                    for (int i = 0; i < hallDoorCells.size(); i++){
                                        Rectangle doorCell = hallDoorCells.get(i);
                                        if (doorCell.contains(point)){
                                            canMove = false;
                                            Rectangle rect = doorCell;
                                            if(selectedPlayerId == 0){
                                                rect = new Rectangle(286,104,26,26);
                                            }
                                            if(selectedPlayerId == 1){
                                                rect = new Rectangle(312,104,26,26);
                                            }
                                            if(selectedPlayerId == 2){
                                                rect = new Rectangle(286,78,26,26);
                                            }
                                            if(selectedPlayerId == 3){
                                                rect = new Rectangle(312,78,26,26);
                                            }
                                            if(selectedPlayerId == 4){
                                                rect = new Rectangle(286,52,26,26);
                                            }
                                            if(selectedPlayerId == 5){
                                                rect = new Rectangle(312,52,26,26);
                                            }
                                            if(!inaccessible) {
                                                if(!inRoom.get(selectedPlayerId)) {
                                                    cells2.set(selectedPlayerId, rect);
                                                    runningGame.clickedLocation(cells.get(index), currentlyPlaying);
                                                    System.out.println("Player " + (selectedPlayerId + 1) + " enters the Hall.");
                                                    inRoom.set(selectedPlayerId, true);
                                                    suggestion = new Suggestion(runningGame, pickedCharacter, pickedWeapon, currentlyPlaying);
                                                    suggestion.openSugestionPopup();
                                                }
                                                else{
                                                    cells2.set(selectedPlayerId, cells.get(index));
                                                    runningGame.clickedLocation(cells.get(index), currentlyPlaying);
                                                    System.out.println("Player " + (selectedPlayerId + 1) + " leaves the Hall.");
                                                    inRoom.set(selectedPlayerId, false);
                                                }
                                                selectedPlayer = null;
                                                selectedPlayerId = -1;
                                            }
                                        }
                                    }

                                    //If no player is occupying the clicked tile, canMove remains as true.
                                    //This is for all 'normal' movements.
                                    if(canMove) {
                                        cells2.set(selectedPlayerId, cells.get(index));
                                        //CHANGE
                                        runningGame.clickedLocation(cells.get(index), currentlyPlaying);
                                        inRoom.set(selectedPlayerId,false);

                                        selectedPlayer = null;
                                        selectedPlayerId = -1;
                                    }
                                }

                                //If a player isn't already selected, this selects a clicked player instead.
                                else{
                                    for (int i = 0; i < cells2.size(); i++){
                                        Rectangle cell2 = cells2.get(i);

                                    }
                                }

                            }

                        }}

                    repaint();


                }

            };
            addMouseMotionListener(mouseHandler);
            addMouseListener(mouseHandler);



            //Reads the gameboard text file and sets arrays for setting colour for the board.
            for (int i = 0; i < length; i++){
                String pip = board[i];

                //Room space (inaccessible)
                if("x".equals(pip)){
                    numbers.add(0);
                    numbers2.add(50);
                    numbers3.add(200);
                }

                //Board walkway
                else if ("a".equals(pip)){
                    numbers.add(50);
                    numbers2.add(0);
                    numbers3.add(150);
                }

                //Player spawns represented as p.
                else if ("p".equals(pip)){
                    numbers.add(70);
                    numbers2.add(50);
                    numbers3.add(70);
                }


                //Move area after dice rolls set as z
                else if ("z".equals(pip)){
                    numbers.add(80);
                    numbers2.add(80);
                    numbers3.add(80);
                }

                //Room tile for doorway enterance.
                else if ("y".equals(pip)){
                    numbers.add(100);
                    numbers2.add(50);
                    numbers3.add(100);

                }

                //Middle space
                else if ("m".equals(pip)){
                    numbers.add(200);
                    numbers2.add(150);
                    numbers3.add(50);
                }

                //Room tile for lounge.
                else if ("f".equals(pip)){
                    numbers.add(150);
                    numbers2.add(0);
                    numbers3.add(150);

                }

                //Room tile for study.
                else if ("s".equals(pip)){
                    numbers.add(100);
                    numbers2.add(150);
                    numbers3.add(100);

                }

                //Room tile for hall.
                else if ("h".equals(pip)){
                    numbers.add(0);
                    numbers2.add(0);
                    numbers3.add(150);

                }

                //Room tile for library.
                else if ("l".equals(pip)){
                    numbers.add(150);
                    numbers2.add(0);
                    numbers3.add(0);

                }

                //Room tile for dining room.
                else if ("d".equals(pip)){
                    numbers.add(0);
                    numbers2.add(150);
                    numbers3.add(0);

                }

                //Room tile for billiard room.
                else if ("b".equals(pip)){
                    numbers.add(0);
                    numbers2.add(0);
                    numbers3.add(100);

                }

                //Room tile for ballroom.
                else if ("e".equals(pip)){
                    numbers.add(0);
                    numbers2.add(100);
                    numbers3.add(0);

                }

                //Room tile for kitchen.
                else if ("k".equals(pip)){
                    numbers.add(100);
                    numbers2.add(0);
                    numbers3.add(0);

                }

                //Room tile for conservatory.
                else if ("c".equals(pip)){
                    numbers.add(50);
                    numbers2.add(0);
                    numbers3.add(50);

                }
            }


            //Color generation for the board.
            for (int index = 0; index < length; index++) {

                int c1 = numbers.get(index);

                int c2 = numbers2.get(index);

                int c3 = numbers3.get(index);

                colors.add(new Color(c1, c2, c3));
            }
        }

        @Override
        //
        public Dimension getPreferredSize() {
            return new Dimension(COLS * BOX_SIZE, ROWS * BOX_SIZE);
        }

        //Invalidation for clearing the cells for the board.
        @Override
        public void invalidate() {
            if(playersSpawned == true){
                cells2Replace = new ArrayList<>(cells2);
            }
            if(weaponsSpawned == true){
                weaponCells2Replace = new ArrayList<>(weaponCells);
            }
            weaponCells.clear();
            cells.clear();
            cells2.clear();
            cells2 = new ArrayList<>(cells2Replace);
            weaponCells = new ArrayList<>(weaponCells2Replace);
            playersPieces.clear();
            selectedCell = null;
            super.invalidate();
        }

        private void updateWeaponLocs(){
            if (weaponsNeedUpdate == true){

            }
        }


        @Override
        //Responsible for updating and repainting the board and it's graphics. Handles everything being displayed on the board.
        protected void paintComponent(Graphics g) {
            //CHANGE
            Font myFont = new Font("Lucida Grande", Font.PLAIN, 13);
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setFont(myFont);

            int width = getWidth();
            int height = getHeight();

            int cellWidth = width / COLS;
            int cellHeight = height / ROWS;

            int xOffset = (width - (COLS * cellWidth)) / 2;
            int yOffset = (height - (ROWS * cellHeight)) / 2;


            if (cells.isEmpty()) {
                for (int row = 0; row < ROWS; row++) {
                    for (int col = 0; col < COLS; col++) {
                        Rectangle cell = new Rectangle(
                                xOffset + (col * cellWidth),
                                yOffset + (row * cellHeight),
                                cellWidth,
                                cellHeight);
                        cells.add(cell);


                    }
                }
            }


            //Player pieces are represented on the board as ovals.
            //int playerCounter2 = 0;
            if (cells2.isEmpty()) {
                int playerCounter2 = 0;

                for (int i=0; i < numberOfPlayers; i++ ){
                    Rectangle cell = new Rectangle(0,0,26,26);
                    cells2.add(cell);
                    inRoom.add(false);
                }
                //Initial spawning of players.
                if (playersSpawned == false) {

                    while (playerCounter2 < numberOfPlayers) {

                        if (playerCounter2 < numberOfPlayers) {
                            Rectangle cell = new Rectangle(
                                    xOffset + (9 * cellWidth),
                                    yOffset + (24 * cellHeight),
                                    cellWidth,
                                    cellHeight);
                            cells2.set(0, cell);
                            System.out.println("Player 1 added");
                            playerCounter2++;
                            inRoom.set(0, false);
                        }

                        if (playerCounter2 < numberOfPlayers) {
                            Rectangle cell = new Rectangle(
                                    xOffset + (16 * cellWidth),
                                    yOffset + (0 * cellHeight),
                                    cellWidth,
                                    cellHeight);
                            cells2.set(1, cell);
                            System.out.println("Player 2 added");
                            playerCounter2++;
                            inRoom.set(1, false);
                        }

                        if (playerCounter2 < numberOfPlayers) {
                            Rectangle cell = new Rectangle(
                                    xOffset + (23 * cellWidth),
                                    yOffset + (7 * cellHeight),
                                    cellWidth,
                                    cellHeight);
                            cells2.set(2, cell);
                            System.out.println("Player 3 added");
                            playerCounter2++;
                            inRoom.set(2, false);
                        }

                        if (playerCounter2 < numberOfPlayers) {
                            Rectangle cell = new Rectangle(
                                    xOffset + (14 * cellWidth),
                                    yOffset + (24 * cellHeight),
                                    cellWidth,
                                    cellHeight);
                            cells2.set(3, cell);
                            ;
                            System.out.println("Player 4 added");
                            playerCounter2++;
                            inRoom.set(3, false);
                        }

                        if (playerCounter2 < numberOfPlayers) {
                            Rectangle cell = new Rectangle(
                                    xOffset + (0 * cellWidth),
                                    yOffset + (5 * cellHeight),
                                    cellWidth,
                                    cellHeight);
                            cells2.set(4, cell);
                            System.out.println("Player 5 added");
                            playerCounter2++;
                            inRoom.set(4, false);
                        }

                        if (playerCounter2 < numberOfPlayers) {
                            Rectangle cell = new Rectangle(
                                    xOffset + (0 * cellWidth),
                                    yOffset + (18 * cellHeight),
                                    cellWidth,
                                    cellHeight);
                            cells2.set(5, cell);
                            System.out.println("Player 6 added");
                            playerCounter2++;
                            inRoom.set(5, false);
                        }

                    }


                    playersSpawned = true;
                } else {
                    List<Rectangle> cells2 = new ArrayList<>(cells2Replace);

                }
            }

            int thisSelectedIndex = -1;
            int clickedIndex = -1;
            int thisSelctedPlayerIndex = -1;

            if (selectedCell != null) {
                thisSelectedIndex = selectedCell.x + (selectedCell.y * COLS);

            }

            //Adds all the room cells to the no access cell list to prevent players from going on these.
            if (noAccessCells.isEmpty()) {
                for (int row = 0; row < ROWS; row++) {
                    for (int col = 0; col < COLS; col++) {
                        int index = (row * COLS) + col;
                        Rectangle cell = cells.get(index);
                        if(row <= 3 && col <= 5){
                            studyRoomCells.add(cell);
                        }
                        if(row >= 6 && row <= 9 && col <= 5){
                            libraryRoomCells.add(cell);
                        }
                        if(row >= 13 && row <= 16 && col <= 4){
                            billiardRoomCells.add(cell);
                        }
                        if(row >= 20 && row <= 24 && col <= 5){
                            conservatoryRoomCells.add(cell);
                        }
                        if(row >= 18 && row <= 22 && col >= 9 && col <= 14){
                            ballroomRoomCells.add(cell);
                        }
                        if(row >= 19 && row <= 24 && col >= 18 && col <= 23){
                            kitchenRoomCells.add(cell);
                        }
                        if(row >= 10 && row <= 14 && col >= 17 && col <= 23){
                            diningRoomCells.add(cell);
                        }
                        if(row <= 4 && col >= 17 && col <= 23){
                            loungeRoomCells.add(cell);
                        }
                        if(row <=5 && col >= 10 && col <= 14){
                            hallRoomCells.add(cell);
                        }
                        if (colors.get(index).getRGB() == -16764216) {
                            noAccessCells.add(cell);
                        }
                        if (colors.get(index).getRGB() == -3631566){
                            middleCells.add(cell);
                        }
                    }
                }
                studyRoom = new Room("Study", studyRoomCells);
                libraryRoom = new Room("Library", libraryRoomCells);
                billiardRoom = new Room("Billiard Room", billiardRoomCells);
                conservatoryRoom = new Room("Conservatory", conservatoryRoomCells);
                ballroomRoom = new Room("Ballroom", ballroomRoomCells);
                kitchenRoom = new Room("Kitchen", kitchenRoomCells);
                diningRoom = new Room("Dining Room",diningRoomCells);
                loungeRoom = new Room("Lounge", loungeRoomCells);
                hallRoom = new Room("Hall", hallRoomCells);
                for (int i = 0; i < weaponCells.size(); i++) {
                    Rectangle cell = weaponCells.get(i);
                }
            }

            //Defaults colors if custom colors are not being used.
            if(customColors == false){
                for (int i = 0; i < 6; i++){
                    playerColors.add(Color.ORANGE);
                    playerNames.add("Mustard");
                }
                playerColors.set(0, p1Color);
                playerColors.set(1, p2Color);
                playerColors.set(2, p3Color);
                playerColors.set(3, p4Color);
                playerColors.set(4, p5Color);
                playerColors.set(5, p6Color);
                playerNames.set(0, "Mustard");
                playerNames.set(1, "Peacock");
                playerNames.set(2, "White");
                playerNames.set(3, "Plum");
                playerNames.set(4, "Scarlett");
                playerNames.set(5, "Green");
            }

            //Spawns weapons and their possible spawns.
            if(weaponsSpawned == false){
                ArrayList<Room> possibleSpawns = new ArrayList<>();
                possibleSpawns.add(studyRoom);
                possibleSpawns.add(libraryRoom);
                possibleSpawns.add(billiardRoom);
                possibleSpawns.add(conservatoryRoom);
                possibleSpawns.add(ballroomRoom);
                possibleSpawns.add(kitchenRoom);
                possibleSpawns.add(diningRoom);
                possibleSpawns.add(loungeRoom);
                possibleSpawns.add(hallRoom);
                Random generator = new Random();
                for( int wepid=0; wepid < 6; wepid++){
                    int randomIndex = generator.nextInt(possibleSpawns.size());
                    Room chosenRoom = possibleSpawns.get(randomIndex);
                    possibleSpawns.remove(randomIndex);
                    ArrayList<Rectangle> roomCells = chosenRoom.getRoomRectangles();
                    weaponCells.add(roomCells.get(0));
                    Weapon w = new Weapon(wepid, roomCells.get(0), gameTheme);
                    chosenRoom.addWeapon(w);
                    System.out.println("Added weapon " + w.getName() + " to " + chosenRoom.getNameOfRoom());
                }
                weaponsSpawned = true;

            }
            else{
                List<Rectangle> weaponCells = new ArrayList<>(weaponCells2Replace);
            }

            //Adds door cells to their respective arraylist.
            if (allDoorCells.isEmpty()) {
                for (int row = 0; row < ROWS; row++) {
                    for (int col = 0; col < COLS; col++) {
                        int index = (row * COLS) + col;
                        Rectangle cell = cells.get(index);
                        if (colors.get(index).getRGB() == -10185116) {
                            studyDoorCells.add(cell);
                            allDoorCells.add(cell);
                        }
                        else if (colors.get(index).getRGB() == -16777066) {
                            hallDoorCells.add(cell);
                            allDoorCells.add(cell);
                        }
                        else if (colors.get(index).getRGB() == -6946666) {
                            loungeDoorCells.add(cell);
                            allDoorCells.add(cell);
                        }
                        else if (colors.get(index).getRGB() == -6946816) {
                            libraryDoorCells.add(cell);
                            allDoorCells.add(cell);
                        }
                        else if (colors.get(index).getRGB() == -16738816) {
                            diningDoorCells.add(cell);
                            allDoorCells.add(cell);
                        }
                        else if (colors.get(index).getRGB() == -16777116) {
                            billiardDoorCells.add(cell);
                            allDoorCells.add(cell);
                        }
                        else if (colors.get(index).getRGB() == -16751616) {
                            ballroomDoorCells.add(cell);
                            allDoorCells.add(cell);
                        }
                        else if (colors.get(index).getRGB() == -10223616) {
                            kitchenDoorCells.add(cell);
                            allDoorCells.add(cell);
                        }
                        else if (colors.get(index).getRGB() == -13500366) {
                            conservatoryDoorCells.add(cell);
                            allDoorCells.add(cell);
                        }

                    }
                }

            }


            //Loop for redrawing the whole board and the majority of it's contents, including selecting squares.
            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    int index = (row * COLS) + col;
                    if (index == thisSelectedIndex) {
                        Rectangle cell = cells.get(index);
                        g2d.setColor(Color.WHITE);
                        g2d.draw(cell);
                    } else {
                        Rectangle cell = cells.get(index);
                        //CHANGE
                        if(allDoorCells.contains(cell) && colors.get(index).getRGB() == -11513776){
                            g2d.setColor(colors.get(index).brighter().brighter());
                        }
                        else {
                            g2d.setColor(colors.get(index));
                        }
                        g2d.fill(cell);

                    }

                    //Draws the player rectangles on the board.
                    for (int i = 0; i < cells2.size(); i++) {
                        if (i == 0) {
                            Rectangle cell = cells2.get(i);

                            g2d.setColor(playerColors.get(i));
                            g2d.fillOval((int) cell.getX(), (int) cell.getY(), (int) cell.getWidth(), (int) cell.getHeight());
                            g2d.drawString(playerNames.get(i),(int) cell.getX() - 12,(int) cell.getY() - 5);
                            if(playerNames.get(i) != "Mustard") {
                                g2d.drawString("Mustard", (int) cell.getX() - 25, (int) cell.getY() - 18);
                            }


                        }
                        if (i == 1) {

                            Rectangle cell = cells2.get(i);
                            g2d.setColor(playerColors.get(i));
                            g2d.fillOval((int) cell.getX(), (int) cell.getY(), (int) cell.getWidth(), (int) cell.getHeight());
                            g2d.drawString(playerNames.get(i),(int) cell.getX() - 12,(int) cell.getY() - 5);
                            if(playerNames.get(i) != "Peacock") {
                                g2d.drawString("Mrs. Peacock", (int) cell.getX() - 25, (int) cell.getY() - 18);
                            }

                        }
                        if (i == 2) {
                            Rectangle cell = cells2.get(i);

                            g2d.setColor(playerColors.get(i));
                            g2d.fillOval((int) cell.getX(), (int) cell.getY(), (int) cell.getWidth(), (int) cell.getHeight());
                            g2d.drawString(playerNames.get(i),(int) cell.getX() - 7,(int) cell.getY() - 5);
                            if(playerNames.get(i) != "White") {
                                g2d.drawString("Mrs. White", (int) cell.getX() - 25, (int) cell.getY() - 18);
                            }

                        }
                        if (i == 3) {
                            Rectangle cell = cells2.get(i);

                            g2d.setColor(playerColors.get(i));
                            g2d.fillOval((int) cell.getX(), (int) cell.getY(), (int) cell.getWidth(), (int) cell.getHeight());
                            g2d.drawString(playerNames.get(i),(int) cell.getX() - 5,(int) cell.getY() - 5);
                            if(playerNames.get(i) != "Plum") {
                                g2d.drawString("Prof. Plum", (int) cell.getX() - 25, (int) cell.getY() - 18);
                            }

                        }
                        if (i == 4) {
                            Rectangle cell = cells2.get(i);

                            g2d.setColor(playerColors.get(i));
                            g2d.fillOval((int) cell.getX(), (int) cell.getY(), (int) cell.getWidth(), (int) cell.getHeight());
                            g2d.drawString(playerNames.get(i),(int) cell.getX() - 12,(int) cell.getY() - 5);
                            if(playerNames.get(i) != "Scarlett") {
                                g2d.drawString("Miss Scarlett", (int) cell.getX() - 25, (int) cell.getY() - 18);
                            }

                        }
                        if (i == 5) {
                            Rectangle cell = cells2.get(i);

                            g2d.setColor(playerColors.get(i));
                            g2d.fillOval((int) cell.getX(), (int) cell.getY(), (int) cell.getWidth(), (int) cell.getHeight());
                            g2d.drawString(playerNames.get(i),(int) cell.getX() - 7,(int) cell.getY() - 5);
                            if(playerNames.get(i) != "Green") {
                                g2d.drawString("Mr. Green", (int) cell.getX() - 25, (int) cell.getY() - 18);
                            }

                        }
                    }

                    g2d.setColor(Color.YELLOW);

                    //Draws weapon Rectangles on the board when displayWeapons is true.
                    //Checks what theme is being used.

                    if (displayWeapons == true){
                        for (int i = 0; i < weaponCells.size(); i++) {
                            if (gameTheme == "Classic") {
                                if (i == 0) {
                                    Rectangle cell = weaponCells.get(i);

                                    g2d.drawImage(candleImage, (int) cell.getX(), (int) cell.getY(), Color.BLUE, null);
                                    g2d.drawString("Candlestick", (int) cell.getX() - 17, (int) cell.getY() + 34);
                                } else if (i == 1) {
                                    Rectangle cell = weaponCells.get(i);

                                    g2d.drawImage(daggerImage, (int) cell.getX(), (int) cell.getY(), Color.BLUE, null);
                                    g2d.drawString("Dagger", (int) cell.getX() - 12, (int) cell.getY() + 34);
                                } else if (i == 2) {
                                    Rectangle cell = weaponCells.get(i);

                                    g2d.drawImage(pipeImage, (int) cell.getX(), (int) cell.getY(), Color.BLUE, null);
                                    g2d.drawString("Pipe", (int) cell.getX() - 5, (int) cell.getY() + 34);
                                } else if (i == 3) {
                                    Rectangle cell = weaponCells.get(i);

                                    g2d.drawImage(revolverImage, (int) cell.getX(), (int) cell.getY(), Color.BLUE, null);
                                    g2d.drawString("Revolver", (int) cell.getX() - 12, (int) cell.getY() + 34);
                                } else if (i == 4) {
                                    Rectangle cell = weaponCells.get(i);

                                    g2d.drawImage(ropeImage, (int) cell.getX(), (int) cell.getY(), Color.BLUE, null);
                                    g2d.drawString("Rope", (int) cell.getX() - 5, (int) cell.getY() + 34);
                                } else if (i == 5) {
                                    Rectangle cell = weaponCells.get(i);

                                    g2d.drawImage(wrenchImage, (int) cell.getX(), (int) cell.getY(), Color.BLUE, null);
                                    g2d.drawString("Wrench", (int) cell.getX() - 12, (int) cell.getY() + 34);

                                }
                            }
                            else if (gameTheme == "Medieval"){
                                if (i == 0) {
                                    Rectangle cell = weaponCells.get(i);

                                    g2d.drawImage(medAxeImage, (int) cell.getX(), (int) cell.getY(), Color.BLUE, null);
                                    g2d.drawString("Axe(Candlestick)", (int) cell.getX() - 17, (int) cell.getY() + 34);
                                } else if (i == 1) {
                                    Rectangle cell = weaponCells.get(i);

                                    g2d.drawImage(medBowImage, (int) cell.getX(), (int) cell.getY(), Color.BLUE, null);
                                    g2d.drawString("Bow(Dagger)", (int) cell.getX() - 12, (int) cell.getY() + 34);
                                } else if (i == 2) {
                                    Rectangle cell = weaponCells.get(i);

                                    g2d.drawImage(medCrossImage, (int) cell.getX(), (int) cell.getY(), Color.BLUE, null);
                                    g2d.drawString("Crossbow(Pipe)", (int) cell.getX() - 5, (int) cell.getY() + 34);
                                } else if (i == 3) {
                                    Rectangle cell = weaponCells.get(i);

                                    g2d.drawImage(medHammerImage, (int) cell.getX(), (int) cell.getY(), Color.BLUE, null);
                                    g2d.drawString("Hammer(Revolver)", (int) cell.getX() - 12, (int) cell.getY() + 34);
                                } else if (i == 4) {
                                    Rectangle cell = weaponCells.get(i);

                                    g2d.drawImage(medMaceImage, (int) cell.getX(), (int) cell.getY(), Color.BLUE, null);
                                    g2d.drawString("Mace(Rope)", (int) cell.getX() - 5, (int) cell.getY() + 34);
                                } else if (i == 5) {
                                    Rectangle cell = weaponCells.get(i);

                                    g2d.drawImage(medSwordImage, (int) cell.getX(), (int) cell.getY(), Color.BLUE, null);
                                    g2d.drawString("Sword(Wrench)", (int) cell.getX() - 12, (int) cell.getY() + 34);

                                }
                            }
                            else if (gameTheme == "Minecraft"){
                                if (i == 0) {
                                    Rectangle cell = weaponCells.get(i);

                                    g2d.drawImage(mineAxeImage, (int) cell.getX(), (int) cell.getY(), Color.BLUE, null);
                                    g2d.drawString("Axe(Candlestick)", (int) cell.getX() - 17, (int) cell.getY() + 34);
                                } else if (i == 1) {
                                    Rectangle cell = weaponCells.get(i);

                                    g2d.drawImage(mineBowImage, (int) cell.getX(), (int) cell.getY(), Color.BLUE, null);
                                    g2d.drawString("Bow(Dagger)", (int) cell.getX() - 12, (int) cell.getY() + 34);
                                } else if (i == 2) {
                                    Rectangle cell = weaponCells.get(i);

                                    g2d.drawImage(mineCrossImage, (int) cell.getX(), (int) cell.getY(), Color.BLUE, null);
                                    g2d.drawString("Crossbow(Pipe)", (int) cell.getX() - 5, (int) cell.getY() + 34);
                                } else if (i == 3) {
                                    Rectangle cell = weaponCells.get(i);

                                    g2d.drawImage(mineLavaImage, (int) cell.getX(), (int) cell.getY(), Color.BLUE, null);
                                    g2d.drawString("Lava(Revolver)", (int) cell.getX() - 12, (int) cell.getY() + 34);
                                } else if (i == 4) {
                                    Rectangle cell = weaponCells.get(i);

                                    g2d.drawImage(minePickImage, (int) cell.getX(), (int) cell.getY(), Color.BLUE, null);
                                    g2d.drawString("Pickaxe(Rope)", (int) cell.getX() - 5, (int) cell.getY() + 34);
                                } else if (i == 5) {
                                    Rectangle cell = weaponCells.get(i);

                                    g2d.drawImage(mineSwordImage, (int) cell.getX(), (int) cell.getY(), Color.BLUE, null);
                                    g2d.drawString("Sword(Wrench)", (int) cell.getX() - 12, (int) cell.getY() + 34);

                                }
                            }
                        }
                        weaponsDrawn = true;

                    }
                    //Draws text on the board.

                    g2d.setColor(Color.PINK);

                    g2d.drawString("Study",52,28);
                    g2d.drawString("Hall",286,28);
                    g2d.drawString("Lounge",520,28);
                    g2d.drawString("Dining room",494,254);
                    g2d.drawString("Kitchen",520,483);
                    g2d.drawString("Ballroom",260,467);
                    g2d.drawString("Conservatory",40,514);
                    g2d.drawString("Biliard Room",52,334);
                    g2d.drawString("Library",52,184);

                    g2d.setColor(Color.BLACK);
                    g2d.drawString("CLUEDO", 275, 286);
                    g2d.drawString("Hover Mouse over ", 248, 310);
                    g2d.drawString("here to view ", 257, 324);
                    g2d.drawString("weapons.", 269, 338);


                }
            }


            g2d.dispose();
        }

    }
}