import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("serial")
public class Program extends JFrame	implements KeyListener,	ActionListener{
	// Map variables
	private static int xCoord;
	private static int yCoord;
	private static GameMap map;
	static final int numChunks = 4;
    static final int chunkSize = 5;
    static final int hiddenDescriptionChance = 20;
    static Biome oceanBiome;
    
    // Game variables
    private static List<Biome> biomes = null;
    private static ItemSet items;
    
	// Player variables
    private static Character player;

	// Menu navigation variables
	public enum Status{START, MAP, MAIN_MENU, INVENTORY_MENU, CONTROLS};
	public Status gameStatus = Status.START;
	public static int inventoryPage = 0;
	
	// General variables
	public static Scanner keyboard = new Scanner(System.in);
	public static Gson gson = new Gson();
	
	// Database variables
	public static MongoDBStorage mongoStorage;
	private static String username, password;
	public static String textUri = "mongodb://" + username + ":" + password + "@ds056288.mongolab.com:56288/minecraft";
	
	// File variables
	public static int filesRead = 0;
	public static int biomesRead = 0;
	public static int itemsRead = 0;
	public static File folder;
	public static File biomeFile;
	public static File itemFile;
	public static final String folderPath = "res/";
	public static final String biomePath = "biomes.json";
	public static final String itemPath = "items.json";
	
	// Display variables	
	private static JTextArea display;
    private static final String newLine = System.getProperty("line.separator");
    private static boolean keyPressed = false;
    private static final int width = 500;
    private static final int height = 500;
    
    // Control variables
    private static String moveNorth = "w";
    private static String moveWest = "a";
    private static String moveEast = "d";
    private static String moveSouth = "s";
    private static String openActionMenu = "e";
    
    public static void main(String[] args) {
    	
    	try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
    	
    	initFiles();
    	readFiles();

		map = new GameMap(numChunks, chunkSize);

		xCoord = map.getMap().length / 2;
    	yCoord = map.getMap().length / 2;
		
		createAndShowGUI();
		viewStart();
		}
    
    public static void initFiles() {
    	folder = new File(folderPath);
		if(!folder.exists()){
			if(folder.mkdir())
				System.out.println("File created.");
			else
				System.out.println("Failed to create file folder.");
		}
    	
    	biomeFile = new File(folderPath + biomePath);
    	itemFile = new File(folderPath + itemPath);
    	try {
    		if(!biomeFile.exists()){
    			if(biomeFile.createNewFile())
    				System.out.println("File created.");
    			else
    				System.out.println("Failed to create folder: " + folderPath);
    		}
    		if(!itemFile.exists()){
				if(itemFile.createNewFile())
					System.out.println("File created: " + folderPath + biomePath);
				else
					System.out.println("Failed to create file: " + folderPath + itemPath);
    		}
		} catch (IOException e) {e.printStackTrace();}
    }
    
    public static void readFiles() {
		File folder = new File(folderPath);
		File[] files = folder.listFiles();

	    for (int i = 0; i < files.length; i++) {
	    	String filename = "";

	    	int j = files[i].getPath().lastIndexOf('.');
	    	int k = files[i].getPath().lastIndexOf('\\');
	    	if(k > 0)
	    		filename = files[i].getPath().substring(k + 1, j);
	    	
	    	String json = "";
	    	try {
	    		BufferedReader br = new BufferedReader(new FileReader(files[i]));
		    	json = br.readLine();
			} catch (IOException e) {e.printStackTrace();}
	    	
	    	if(filename.equals("biomes")){
	    		biomes = gson.fromJson(json, new TypeToken<List<Biome>>(){}.getType());
	    		for(Biome biome : biomes){
	    			if(biome.getName().equalsIgnoreCase("ocean"))
	    				oceanBiome = biome;
	    		}
	    	}
	    	else if(filename.equals("items")){
	    		items = gson.fromJson(json, new TypeToken<ItemSet>(){}.getType());
	    	}
	    	else{
	    		return;
	    	}
	    	filesRead++;
	    }
	    System.out.println("Total files read: " + filesRead);
	    System.out.println("Bioms read: " + biomes.size());
	    //System.out.println("Items read: " + items.size());
	}
    
    public static void writeFiles() {
    	System.out.println("Writing files.");
    	
    	try {    	
			BufferedWriter biomeWriter = new BufferedWriter(new FileWriter(biomeFile));
			biomeWriter.write(gson.toJson(biomes));
			biomeWriter.close();
		} catch (IOException e) {e.printStackTrace();}
    }

	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        Program frame = new Program("CS31516Game1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.addComponents();
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void addComponents() {        
        display = new JTextArea();
        display.setEditable(false);
        display.addKeyListener(this);
        
        JScrollPane scrollPane = new JScrollPane(display);
        scrollPane.setPreferredSize(new Dimension(width, height));
        
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
    
    public Program(String name) {
        super(name);
    }
    
    
    public void keyReleased(KeyEvent e) {
    	keyPressed = false;
    }
    public void keyPressed(KeyEvent e) {
    	if(!keyPressed){
    		keyPressed = true;
    		displayInfo(e);
    	}
    }
    public void keyTyped(KeyEvent e) {}
    public void actionPerformed(ActionEvent e) {}
    
    private void displayInfo(KeyEvent e){
        if(gameStatus == Status.START){
        	processStartEvent(e);
        }
        else if(gameStatus == Status.MAIN_MENU){
        	processMainMenuEvent(e);
        }
        else if(gameStatus == Status.CONTROLS){
        	processMainMenuReturnEvent();
        }
        else if(gameStatus == Status.MAP){
	        processMapEvent(e);
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
        	processMainMenuReturnEvent();
        }
        display.setCaretPosition(display.getDocument().getLength());
    }

	private void processStartEvent(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			display.setText("");
			gameStatus = Status.MAIN_MENU;
			viewMainMenu();
		}
	}

	private void processMainMenuEvent(KeyEvent e) {
		if(e.getKeyChar() == '1'){
			gameStatus = Status.MAP;
			display.setText("");
			viewMapWindow(map.getMap(), xCoord, yCoord);
			viewActionMenu();
		}
		else if(e.getKeyChar() == '2'){
			gameStatus = Status.CONTROLS;
			display.setText("");
			viewControls();
		}
		else if(e.getKeyChar() == '3'){
			this.dispose();
		}
	}

	private void processMainMenuReturnEvent() {
		gameStatus = Status.MAIN_MENU;
		display.setText("");
		viewMainMenu();
	}

	private void processMapEvent(KeyEvent e) {
		String input = (e.getKeyChar() + "").toLowerCase();
		boolean proceed = true;
		
		
		if(input.equals(moveNorth) || e.getKeyCode() == KeyEvent.VK_UP){
			if(!(xCoord == 1 || map.getMap()[xCoord - 1][yCoord].getSymbol() == oceanBiome.getSymbol()))
				xCoord--;
			else
				proceed = false;
		}
		else if(input.equals(moveWest) || e.getKeyCode() == KeyEvent.VK_LEFT){
			if(!(yCoord == 1 || map.getMap()[xCoord][yCoord - 1].getSymbol() == oceanBiome.getSymbol()))
				yCoord--;
			else
				proceed = false;
		}
		else if(input.equals(moveSouth) || e.getKeyCode() == KeyEvent.VK_DOWN){
			if(!(xCoord == map.getMap().length - 1 || map.getMap()[xCoord + 1][yCoord].getSymbol() == oceanBiome.getSymbol()))
				xCoord++;
			else
				proceed = false;
		}
		else if(input.equals(moveEast) || e.getKeyCode() == KeyEvent.VK_RIGHT){
			if(!(yCoord == map.getMap().length - 1 || map.getMap()[xCoord][yCoord + 1].getSymbol() == oceanBiome.getSymbol()))
				yCoord++;
			else
				proceed = false;
		}
		else if(e.getKeyChar() == '1')
			map.revealPoint(xCoord, yCoord);
		else if(e.getKeyChar() == '2'){
			// TODO View inventory
		//	gameStatus = Status.INVENTORY_MENU;
		//	display.setText("");
		//	viewInventory();
		}
		else if(e.getKeyChar() == '3'){}
			// TODO View quests
		else if(e.getKeyChar() == '4'){}
			// TODO View world map
		else if(e.getKeyChar() == '5'){map = new GameMap(numChunks, chunkSize);}
		
		
		display.setText("");
		viewMapWindow(map.getMap(), xCoord, yCoord);
		viewActionMenu();
		
		if(e.getKeyChar() == '1'){
		    if(map.getMap()[xCoord][yCoord].getItems().size() > 0){
				display.append(newLine + "Items found: ");
				for(int i = 0; i < map.getMap()[xCoord][yCoord].getItems().size(); i++){
					player.addItem(map.getMap()[xCoord][yCoord].getItems().get(i));
					if(i == map.getMap()[xCoord][yCoord].getItems().size() - 1)
						display.append(map.getMap()[xCoord][yCoord].getItems().get(i).getName() + ".");
					else
						display.append(map.getMap()[xCoord][yCoord].getItems().get(i).getName() + ", ");
				}
			}
			else
				display.append(newLine + "No items found.");
		}
		
		
		
		if(!proceed)
		    	display.append(newLine + "You can't go that way");
	}

	public static void viewMap(Point[][] map){
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++)
				display.append(map[i][j].getSymbol() + " ");
			display.append(newLine);
		}
	}
	
	public static void viewMapWindow(Point[][] map, int xCoord, int yCoord){
		if(xCoord < 0)
			xCoord = 0;
		else if(xCoord > map.length)
			xCoord = map.length - 1;
		else if(yCoord < 0)
			yCoord = 0;
		else if(yCoord > map[0].length)
			yCoord = map[0].length - 1;

		ArrayList<ArrayList<String>> window = new ArrayList<ArrayList<String>>();
		
		for(int x = xCoord - 5; x < xCoord + 6; x++){
			ArrayList<String> line = new ArrayList<String>();
			for(int y = yCoord - 5; y < yCoord + 6; y++){
				if(x == xCoord - 5 || x == xCoord + 5 || y == yCoord - 5 || y == yCoord + 5)
					line.add("*");
				else if(x == xCoord - 4 || x == xCoord + 4 || y == yCoord - 4 || y == yCoord + 4 
						|| x < 1 || x > map.length - 1 || y < 1 || y > map[0].length - 1)
					line.add(" ");
				else if(x == xCoord && y == yCoord)
					line.add("X");
				else
					line.add(map[x][y].getSymbol() + "");
			}
			window.add(line);
		}
		
		for(ArrayList<String> line : window){
			for(String s : line)
				display.append(s + " ");
			display.append(newLine);
		}
		display.append(map[xCoord][yCoord].getDescription());
	}
	
	public static void viewControls(){
		display.append("Use \"" + moveNorth + "\" to move north."
				+ newLine + "Use \"" + moveSouth + "\" to move south."
				+ newLine + "Use \"" + moveEast + "\" to move east."
				+ newLine + "Use \"" + moveWest + "\" to move west."
				+ newLine + "Use \"" + openActionMenu + "\" to open the action menu from in game."
				+ newLine + "Use ESCAPE to return to the main menu at any time."
				+ newLine + "Use the number keys to navigate the menu."
				+ newLine + newLine + "Press any key to continue.");
	}
	
	public static void viewStart(){
		display.append("Welcome to CS31516Game1:" + newLine + "A text-based RPG game."
				+ newLine + newLine + "Press enter to continue.");
	}
	
	public static void viewMainMenu(){
		display.append("1) Play game"
				+ newLine + "2) View controls"
				+ newLine + "3) Exit");
	}
	
	public static void viewActionMenu(){
		display.append(newLine + "1) Search the area"
				+ newLine + "2) View inventory"
				+ newLine + "3) View quests"
				+ newLine + "4) View worldmap"
				+ newLine + "5) Reload map");
	}
	
	public static void viewInventory(){
		//for(int i = inventoryPage * 9 + 1; i < (inventoryPage + 1) * 9 + 1; i++){
		//}
	}

	public static ArrayList<Item> getPointItems(Biome biome) {
		ArrayList<Item> selectItems = new ArrayList<Item>();
		//for(int i = 0; i < biome; i++){
			//selectItems.add(items.get(rand.nextInt(items.size())));
		//}
		return selectItems;
	}
	
	public static String getPointDescription(Biome biome) {
		int random = GameMap.rand.nextInt(biome.getDescriptions().size());
		return biome.getDescriptions().get(random);
	}

	public static String getPointHiddenDescription(Biome biome) {
		if(GameMap.rand.nextInt(100) < hiddenDescriptionChance){
			int random = GameMap.rand.nextInt(biome.getHiddenDescriptions().size());
			return biome.getHiddenDescriptions().get(random);
		}
		return "There is nothing special here.";
	}
	
	public static Collection<Biome> getBiomes() {return biomes;}
}
