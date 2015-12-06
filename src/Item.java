public class Item {	
	private String name;
	private String description;
	private int weight;
	private int speed;
	private int durability;

	/**
	 * Initiates an Item object that can be accessed
	 * and interacted with within the game.
	 * 
	 * @param name			the name of the item, should be pre-formatted
	 * @param description	a short description of the item to appear alongside it
	 * @param weight		the weight of the item
	 * @param speed			the speed of the item when using it , where zero is fastest and higher numbers take longer
	 * @param durability	how many hits the item can take, where zero is infinite
	 */
	public Item(String name, String description, int weight, int speed, int durability) {
		this.name = name;
		this.description = description;
		this.weight = weight;
		this.speed = speed;
		this.durability = durability;
	}
	
	/**
	 * @return	returns the name of the item
	 */
	public String getName(){return name;}
	
	/**
	 * @return	returns the description of the item
	 */
	public String getDescription(){return description;}
	
	/**
	 * @return	returns the weight of the item
	 */
	public int getWeight(){return weight;}
	
	/**
	 * @return	returns the speed of the item
	 */
	public int getSpeed(){return speed;}
	
	/**
	 * @return	returns the hits left on the item
	 */
	public int getDurability(){return durability;}
	
	/**
	 * Uses the item based on a value of hits. 
	 * 
	 * @param value	the number of times to hit the item
	 * @return		returns true if the item breaks, otherwise returns false
	 */
	public boolean use(int value){durability -= value; if(durability == 0) return true; return false;} // Return true if it breaks
}
