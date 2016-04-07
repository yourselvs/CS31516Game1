public class Food extends Item {
	private int heal;
	
	public Food(String name, String description, int weight, int speed, int durability, int heal) {
		super(name, description, weight, speed, durability);
		this.heal = heal;
	}
	
	public int getHeal(){return heal;}
}
