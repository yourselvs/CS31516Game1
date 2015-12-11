import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class Program extends JFrame	implements KeyListener,	ActionListener{
	// Map variables
	private static int xCoord;
	private static int yCoord;
	private static Map map;
	static final int numChunks = 3;
    static final int chunkSize = 5;
    static final int numBiomes = 10;
    static final int hiddenDescriptionChance = 100;
    
    // Game variables
    public enum Biome{FOREST, MOUNTAIN, PLAINS, TUNDRA, DESERT, JUNGLE, BEACH, SWAMP, PRAIRIE, OCEAN};
    //private static final ArrayList<Item> items = new ArrayList<Item>();
    private static final ArrayList<ArrayList<String>> chunkDescription = new ArrayList<ArrayList<String>>();
    private static final ArrayList<ArrayList<String>> chunkHiddenDescription = new ArrayList<ArrayList<String>>();
    
	// Player variables
    private static Character player;

	// Menu navigation variables
	public enum Status{START, MAP, MAIN_MENU, INVENTORY_MENU, CONTROLS};
	public Status gameStatus = Status.START;
	public static int inventoryPage = 0;
	
	// General variables
	public static Scanner keyboard = new Scanner(System.in);
	public static Random rand = new Random(System.currentTimeMillis());
	
	// Display variables
    static JTextArea display;
    JTextField input;
    static final String newLine = System.getProperty("line.separator");
    static boolean keyPressed = false;
    static final int width = 500;
    static final int height = 500;
    
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
    	
    	readPointDescriptions();
    	readPointHiddenDescriptions();
    	
		
		map = new Map(numChunks, chunkSize);
		
		xCoord = map.getMap().length / 2;
    	yCoord = map.getMap().length / 2;
		
		createAndShowGUI();
		
		viewStart();
	}
    
    private static void readPointHiddenDescriptions() {
    	for(int i = 0; i < numBiomes; i++){
    		ArrayList<String> descriptions = new ArrayList<String>();
    		descriptions.add("hidden");
    		chunkHiddenDescription.add(descriptions);
    	}
	}

	private static void readPointDescriptions() {
		for(int i = 0; i < numBiomes; i++){
    		ArrayList<String> descriptions = new ArrayList<String>();
    		descriptions.add("1");
    		descriptions.add("2");
    		descriptions.add("3");
    		descriptions.add("4");
    		descriptions.add("5");
    		descriptions.add("6");
    		descriptions.add("7");
    		descriptions.add("8");
    		descriptions.add("9");
    		chunkDescription.add(descriptions);
    	}
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
        input = new JTextField(20);
        input.setEditable(false);
        input.addKeyListener(this);
        
        display = new JTextArea();
        display.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(display);
        scrollPane.setPreferredSize(new Dimension(width, height));
        
        getContentPane().add(input, BorderLayout.BEFORE_FIRST_LINE);
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
        	if(e.getKeyCode() == KeyEvent.VK_ENTER){
        		display.setText("");
        		gameStatus = Status.MAIN_MENU;
        		viewMainMenu();
        	}
        }
        else if(gameStatus == Status.MAIN_MENU){
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
        else if(gameStatus == Status.CONTROLS){
        	gameStatus = Status.MAIN_MENU;
        	display.setText("");
        	viewMainMenu();
        }
        else if(gameStatus == Status.MAP){
	        String input = (e.getKeyChar() + "").toLowerCase();
	        boolean proceed = true;
	        
	        
	        if(input.equals(moveNorth)){
				if(xCoord == 1){
					proceed = false;
				}
				else
					xCoord--;
			}
			else if(input.equals(moveWest)){
				if(yCoord == 1){
					proceed = false;
				}
				else
					yCoord--;
			}
			else if(input.equals(moveSouth)){
				if(xCoord == map.getMap().length - 1){
					proceed = false;
				}
				else
					xCoord++;
			}
			else if(input.equals(moveEast)){
				if(yCoord == map.getMap().length - 1){
					proceed = false;
				}
				else
					yCoord++;
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
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
        	gameStatus = Status.MAIN_MENU;
        	display.setText("");
        	viewMainMenu();
        }
        display.setCaretPosition(display.getDocument().getLength());
    }

	public static void viewMap(Point[][] map){
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++)
				display.append(map[i][j].getValue() + " ");
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
					line.add(map[x][y].getValue() + "");
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
				+ newLine + "4) View worldmap");
	}
	
	public static void viewInventory(){
		//for(int i = inventoryPage * 9 + 1; i < (inventoryPage + 1) * 9 + 1; i++){
		//}
	}

	public static ArrayList<Item> getRandomPointItems(int numItem) {
		ArrayList<Item> selectItems = new ArrayList<Item>();
		for(int i = 0; i < numItem; i++){
			//selectItems.add(items.get(rand.nextInt(items.size())));
		}
		return selectItems;
	}

	public static String getRandomPointDescription(int chunk) {
		int descriptionNum = rand.nextInt(chunkDescription.get(chunk).size());
		return chunkDescription.get(chunk).get(descriptionNum);
	}
	
	public static String getRandomPointHiddenDescription(int chunk) {
		if(rand.nextInt(100) < hiddenDescriptionChance)
			return chunkHiddenDescription.get(chunk).get(rand.nextInt(chunkHiddenDescription.get(chunk).size()));
		return "";
	}
}
