import java.util.ArrayList;

public class Biome {
	private String name;
	private String symbol;
	private ArrayList<String> descriptions;
	private ArrayList<String> hiddenDescriptions;
	
	public Biome(){}
	
	public String getName() {return name;}
	public char getSymbol() {return symbol.toCharArray()[0];}
	public ArrayList<String> getDescriptions() {return descriptions;}
	public ArrayList<String> getHiddenDescriptions() {return hiddenDescriptions;}
}
