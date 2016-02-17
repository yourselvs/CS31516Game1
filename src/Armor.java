public class Armor extends Item{
	private int protectionBonus;
	
	/**
	 * 
	 * @param name			the name of the item, should be pre-formatted
	 * @param description	a short description of the item to appear alongside it
	 * @param weight		the weight of the item
	 * @param speed			the speed of the item when using it , where zero is instant and higher numbers take longer
	 * @param durability	how many hits the item can take, where zero is infinite
	 * @param protection	the flat protection bonus for the item
	 */
	
	public Armor(String name, String description, int weight, int speed, int durability, int protectionBonus){
		super(name, description, weight, speed, durability);
		this.protectionBonus = protectionBonus;
	}
	
	public int getProtectionBonus(){return protectionBonus;}
}
