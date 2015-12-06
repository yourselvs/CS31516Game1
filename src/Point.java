import java.util.ArrayList;

public class Point {
	private int value;
	private String description;
	private String hiddenDescription;
	private ArrayList<Item> items;
	private boolean searched;
	
	/**
	 * Point class contains all the information contained within a single point.
	 * 
	 * @param value					the initial value of the point
	 * @param description			the description of the point, should be pre-formatted
	 * @param hiddenDescription 	the hidden description of the point, found upon searching the point, should be pre-formatted
	 */
	public Point(int value, String description, String hiddenDescription, ArrayList<Item> items){
		this.value = value;
		this.description = description;
		this.hiddenDescription = hiddenDescription;
		this.items = items;
	}
	
	/**
	 * @return		returns the point's value
	 */
	public int getValue(){return value;}
	
	/**
	 * @return		returns the point's description
	 */
	public String getDescription(){return description;}
	
	/**
	 * @return		returns the point's hidden description
	 */
	public void revealHiddenDescription(){if(!searched){searched = true; description = description + " " + hiddenDescription;}}
	
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
	public void setValue(int value){this.value = value;}
	
	/**
	 * Sets the description of a point. The description is
	 * where setting of the point is represented.
	 * 
	 * @param description	the description to be set, should be pre-formatted
	 */
	public void setDescription(String description){this.description = description;}
	
	/**
	 * Sets the hidden description of a point. The hidden
	 * description is revealed only when a point has been
	 * searched. 
	 * 
	 * @param description	the description to be set
	 */
	public void setHiddenDescription(String description){this.hiddenDescription = description;}
	
	/**
	 * Adds an item to a point. The items are what is
	 * found in a point after it has been searched.
	 *  
	 * @param items		the items to be set in the point
	 */
	public void addItem(ArrayList<Item> items){this.items = items;}
	
	public void clearItems(){this.items = new ArrayList<Item>();}
}
