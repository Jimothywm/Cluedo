package cluedo;

public interface Token {

	/**
	 * Set x position
	 * @param x
	 */
	public void setX(int x);

	/**
	 * Set y position
	 * @param y
	 */
	public void setY(int y);

	/**
	 * Return x position
	 * @return
	 */
	public int getX();

	/**
	 * Return y position
	 * @return
	 */
	public int getY();

	/**
	 * Return Card of Token
	 * @return
	 */
	public Card getCard();

	/**
	 * Return the full name of the Token
	 * @return
	 */
	public String getName();

	/**
	 * Return the corresponding image of the token
	 */

}
