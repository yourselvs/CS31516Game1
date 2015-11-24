public class Point {
	private int value;
	private String description;
	private String hiddenDescription;
	private Item[] items;
	
	/**
	 * Point class contains all the information contained within a single point.
	 * 
	 * @param value			the initial value of the point
	 * @param description	the description of the point, should be pre-formatted
	 */
	public Point(int value, String description, Item[] items){
		this.value = value;
		this.description = description;
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
	public String getHiddenDescription(){return hiddenDescription;}
	
	/**
	 * @return		returns the point's items
	 */
	public Item[] getItems(){return items;}
	
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
	 * Sets the items of a point. The items are what is
	 * found in a point after it has been searched.
	 *  
	 * @param items		the items to be set in the point
	 */
	public void setItems(Item[] items){this.items = items;}
}
