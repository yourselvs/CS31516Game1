public class Projectile  extends Item{
	private int range;
	private String ammo;
	private int magazineCount;
	private int magSize;
	
	/**
	 * Initiates a Projectile Item object that can be accessed
	 * and interacted with within the game. Item takes a specific
	 * type of ammo. Item can be reloaded based on a magazine size.
	 * 
	 * @param name			the name of the item, should be pre-formatted
	 * @param description	a short description of the item to appear alongside it
	 * @param weight		the weight of the item
	 * @param speed			the speed of the item when using it , where zero is instant and higher numbers take longer
	 * @param durability	how many hits the item can take, where zero is infinite
	 * @param range			range multiplier to increase the range of ammo used
	 * @param ammo			type of ammo to be used with the item
	 * @param magazine		amount of ammo left in current reload
	 * @param reloadSize	amount of ammo held in a single reload
	 */
	public Projectile(String name, String description, int weight, int speed, int durability, int range, String ammo, int magSize){
		super(name, description, weight, speed, durability);
		this.range = range;
		this.ammo = ammo;
		this.magazineCount = magSize;
	}
	
	/**
	 * @return	returns the range boost of the item
	 */
	public int getRange(){return range;}
	
	/**
	 * @return	returns the ammo type used by the item
	 */
	public String getAmmoType(){return ammo;}
	
	/**
	 * @return	returns the ammo in the current magazine
	 */
	public int getMagCount(){return magazineCount;}
	
	/**
	 * Reloads the item with all missing bullets. If there
	 * are more missing bullets than there is ammo, the ammo
	 * is loaded into the magazine.
	 * 
	 * @param totalAmmo		the stock of ammo from which to reload the gun
	 * @return				returns the amount of ammo used to reload the gun
	 */
	public int reload(int totalAmmo){
		if(totalAmmo > this.magSize - this.magazineCount){
			int ammoUsed = this.magSize - this.magazineCount;
			this.magazineCount = this.magSize;
			return ammoUsed;
		}
		else{
			this.magazineCount += totalAmmo;
			return totalAmmo;
		}
	}
	
	public boolean fire(int value){
		if(this.magazineCount > 0)
			this.magazineCount -= value;
			
		return super.use(value);
	}
}
