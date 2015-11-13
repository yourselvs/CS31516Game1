import java.util.ArrayList;

public class Character {
	private String name;
	private String className;
	private ArrayList<Item> inventory;
	private int level;
	public int strength;
	public int dexterity;
	public int sorcery;
	public int resilience;
	public int intelligence;
	public int persona;
	public int luck;
	
	public Character(String name, String className, int strength, int dexterity, 
			int sorcery, int resilience, int intelligence, int persona, int luck){
		this.name = name;
		this.className = className;
		this.strength = strength;
		this.dexterity = dexterity;
		this.sorcery = sorcery;
		this.resilience = resilience;
		this.intelligence = intelligence;
		this.persona = persona;
		this.luck = luck;
	}
	
	public String getName(){return name;}
	public String getClassname(){return className;}
	public ArrayList<Item> getInventory(){return inventory;}
	public int getLevel(){return level;}
}
