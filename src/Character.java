import java.util.ArrayList;

public class Character {
	private String name;
	private String className;
	private ArrayList<Item> items;
	private ArrayList<Quest> quests;
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
	public ArrayList<Item> getItems(){return items;}
	public ArrayList<Quest> getQuests(){return quests;}
	public int getLevel(){return level;}
	
	public void addItem(Item item){items.add(item);}
	public void addQuest(Quest quest){quests.add(quest);}
	
	public void removeItem(String name){
		for(int i = 0; i < items.size(); i++){
			if(items.get(i).getName().equals(name))
				items.remove(i);
		}
	}
	
	public void removeQuest(String name){
		for(int i = 0; i < quests.size(); i++){
			if(quests.get(i).getName().equals(name))
				quests.remove(i);
		}
	}
}
