package cluedo;

import java.awt.*;

//import cluedo.viewer.CluedoCanvas;

public class Weapon implements Token {

	/**
	 * Represent the x position of the Weapon.
	 */
	private int posX;

	/**
	 * Represent the y position of the Weapon.
	 */
	private int posY;

	/**
	 * Represent the full name of the Weapon.
	 */
	private String name;

	/**
	 * Represent the short name of the Weapon.
	 */
	private char shortName;

	/**
	 * Represent the corresponding Card of the Weapon.
	 */
	private Card correspondingCard;

	/**
	 * Represent the corresponding Image of the Weapon
	 */
	private final Image image = null;

	private Rectangle position;

	private String[] weapons = {"Candlestick", "Dagger", "Pipe", "Revolver", "Rope", "Wrench"};
	private String[] weapons2 = {"Axe", "Bow", "Crossbow", "Hammer", "Mace", "Sword"};
	private String[] weapons3 = {"Axe", "Bow", "Crossbow", "Lava", "Pickaxe", "Sword"};

	public Weapon(Integer wepid, Rectangle pos, String theme) {
		if(theme == "Classic") {
			this.name = weapons[wepid];
			this.position = pos;
		}
		else if(theme == "Medieval"){
			this.name = weapons2[wepid];
			this.position = pos;
		}
		else if(theme == "Minecraft"){
			this.name = weapons3[wepid];
			this.position = pos;
		}

	}

	private void initPositions() {
		switch (name) {
		case "CANDLESTICK":
			posX = 0;
			posY = 0;
			break;
		case "DAGGER":
			posX = 0;
			posY = 1;
			break;
		case "LEAD PIPE":
			posX = 0;
			posY = 2;
			break;
		case "REVOLVER":
			posX = 0;
			posY = 3;
			break;
		case "ROPE":
			posX = 0;
			posY = 4;
			break;
		case "SPANNER":
			posX = 0;
			posY = 5;
			break;
		default:
			//throw new GameError("No such a weapon in the game: " + name);
		}
	}

	/**
	 * Return the short name of the Weapon.
	 *
	 * @return
	 */
	public char getShortName() {
		return shortName;
	}

	/**
	 * Set the corresponding Card of the Weapon.
	 *
	 * @param card
	 */
	public void setCard(Card card) {
		this.correspondingCard = card;
	}

	public Rectangle getPosition(){
		return position;
	}

	public void setPosition(Rectangle newPos){
		this.position = newPos;
	}

	@Override
	public void setX(int x) {
		this.posX = x;
	}

	@Override
	public void setY(int y) {
		this.posY = y;
	}

	@Override
	public int getX() {
		return posX;
	}

	@Override
	public int getY() {
		return posY;
	}

	@Override
	public Card getCard() {
		return correspondingCard;
	}

	@Override
	public String getName() {
		return name;
	}


}
