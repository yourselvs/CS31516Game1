public class MeleeItem extends Item{
	private int damageMulti;
	private int damageBonus;
	
	/**
	 * 
	 * @param name			the name of the item, should be pre-formatted
	 * @param description	a short description of the item to appear alongside it
	 * @param weight		the weight of the item
	 * @param speed			the speed of the item when using it , where zero is instant and higher numbers take longer
	 * @param durability	how many hits the item can take, where zero is infinite
	 * @param damageMulti	the damage multiplier for the item
	 */
	
	public MeleeItem(String name, String description, int weight, int speed, int durability, int damageMulti, int damageBonus){
		super(name, description, weight, speed, durability);
		this.damageMulti = damageMulti;
		this.damageBonus = damageBonus;
	}
	
	public int getDamageMulti(){return damageMulti;}
	public int getDamageBonus(){return damageBonus;}
}
