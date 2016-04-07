import java.util.List;
import java.util.Map;

public class ItemSet {
	private Map<String, List<Item>> items;
	private Map<String, List<Armor>> armorItems;
	private Map<String, List<Food>> foodItems;
	private Map<String, List<Melee>> meleeItems;
	private Map<String, List<Projectile>> projectileItems;
	
	public ItemSet(Map<String, List<Item>> items, 
			Map<String, List<Armor>> armorItems, 
			Map<String, List<Food>> foodItems, 
			Map<String, List<Melee>> meleeItems, 
			Map<String, List<Projectile>> projectileItems){
		this.items = items;
		this.armorItems = armorItems;
		this.foodItems = foodItems;
		this.meleeItems = meleeItems;
		this.projectileItems = projectileItems;
	}
	
	
	public Map<String, List<Item>> getItems(){return items;}
	public Map<String, List<Armor>> getArmorItems(){return armorItems;}
	public Map<String, List<Food>> getFoodItems(){return foodItems;}
	public Map<String, List<Melee>> getMeleeItems(){return meleeItems;}
	public Map<String, List<Projectile>> getProjectileItems(){return projectileItems;}
	
	public int size(){
		return items.size() + 
				armorItems.size() + 
				foodItems.size() + 
				meleeItems.size() +
				projectileItems.size();
	}
}
