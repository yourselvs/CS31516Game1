import java.util.ArrayList;

public class Biome {
	private String name;
	private ArrayList<String> descriptions;
	
	public Biome(String name, ArrayList<String> descriptions){
		this.name = name;
		this.descriptions = descriptions;
	}
	
	public String getName() {return name;}
	public ArrayList<String> getDescription() {return descriptions;}
}
