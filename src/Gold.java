public class Gold extends Item{
	private int quantity;

	/**
	 * Creates a set of gold pieces
	 * 
	 * @param quantity		the number of gold pieces in the set
	 */
	public Gold(int quantity) {
		super("Gold piece", "A coin of minted gold.", 0, 0, 0);
		
	}

	/**
	 * @return		returns the number of gold pieces in the set
	 */
	public int getQuantity(){return quantity;}
	
	/**
	 * Adds gold pieces to the set 
	 * 
	 * @param value		the number of gold pieces to add to the set
	 */
	public void addGold(int value){quantity += value;}
	
	/**
	 * Removes gold pieces from the set 
	 * 
	 * @param value		the number of gold pieces to remove from the set
	 */
	public void removeGold(int value){quantity -= value;}
}