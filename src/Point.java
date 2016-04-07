import java.util.ArrayList;

public class Point {
	private char symbol;
	private String description;
	private String hiddenDescription;
	private ArrayList<Item> items;
	private boolean searched;
	
	/**
	 * Point class contains all the information contained within a single point.
	 * 
	 * @param c				the visual representation of the point
	 * @param description			the description of the point, should be pre-formatted
	 * @param hiddenDescription 	the hidden description of the point, found upon searching the point, should be pre-formatted
	 */
	public Point(Biome biome){
		this.symbol = biome.getSymbol(); 
		this.description = Program.getPointDescription(biome);
		this.hiddenDescription = Program.getPointHiddenDescription(biome);
		this.items = Program.getPointItems(biome);
	}
	
	/**
	 * @return		returns the point's value
	 */
	public char getSymbol(){return symbol;}
	
	/**
	 * @return		returns the point's description
	 */
	public String getDescription(){return description;}
	
	/**
	 * @return		returns the point's hidden description
	 */
	public String getHiddenDescription(){return hiddenDescription;}
	
	public Point revealHiddenDescription(){if(!searched){searched = true; description = description + " " + hiddenDescription;} return this;}
	
	/**
	 * @return		returns the point's items
	 */
	public ArrayList<Item> getItems(){return items;}
	
	/**
	 * Sets the value of a point. The value determines
	 * the biome and setting of the point.
	 * 
	 * @param value		the value to be set
	 */
	public Point setSymbol(char symbol){this.symbol = symbol; return this;}
	
	/**
	 * Sets the description of a point. The description is
	 * where setting of the point is represented.
	 * 
	 * @param description	the description to be set, should be pre-formatted
	 */
	public Point setDescription(String description){this.description = description; return this;}
	
	/**
	 * Sets the hidden description of a point. The hidden
	 * description is revealed only when a point has been
	 * searched. 
	 * 
	 * @param description	the description to be set
	 */
	public Point setHiddenDescription(String description){this.hiddenDescription = description; return this;}
	
	/**
	 * Adds an item to a point. The items are what is
	 * found in a point after it has been searched.
	 *  
	 * @param items		the items to be set in the point
	 */
	public Point addItem(ArrayList<Item> items){this.items = items; return this;}
	
	public Point clearItems(){this.items = new ArrayList<Item>(); return this;}
}
