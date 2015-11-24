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
	private static int xCoord;
	private static int yCoord;
	
	private static Map map;
	
	public static Scanner keyboard = new Scanner(System.in);
	public static Random rand = new Random(System.currentTimeMillis());
    static JTextArea displayArea;
    JTextField typingArea;
    static final String newline = System.getProperty("line.separator");
    
    public static void main(String[] args) {
    	try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
    	
    	int numChunks = 3;
		int chunkSize = 5;
		
		map = new Map(numChunks, chunkSize);
		
		xCoord = map.getMap().length / 2;
    	yCoord = map.getMap().length / 2;
		
		createAndShowGUI();
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
        typingArea = new JTextField(20);
        typingArea.addKeyListener(this);
        typingArea.addActionListener(this);
        
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(375, 300));
        
        getContentPane().add(typingArea, BorderLayout.PAGE_START);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        displayArea.setText("Use WASD to move around.");
    }
    
    public Program(String name) {
        super(name);
    }
    
    
    public void keyReleased(KeyEvent e) {
    	typingArea.setText("");
    }
    public void keyPressed(KeyEvent e) {
    	typingArea.setText("");
    	displayInfo(e.getKeyChar(), "Text: ");
    }
    public void keyTyped(KeyEvent e) {}
    public void actionPerformed(ActionEvent e) {}
    
    private void displayInfo(char e, String keyStatus){
        displayArea.setText("");
        String input = (e + "").toLowerCase();
        boolean proceed = true;
        
        if(input.equals("w")){
			if(xCoord == 1){
				proceed = false;
			}
			else
				xCoord--;
		}
		else if(input.equals("a")){
			if(yCoord == 1){
				proceed = false;
			}
			else
				yCoord--;
		}
		else if(input.equals("s")){
			if(xCoord == map.getMap().length - 1){
				proceed = false;
			}
			else
				xCoord++;
		}
		else if(input.equals("d")){
			if(yCoord == map.getMap().length - 1){
				proceed = false;
			}
			else
				yCoord++;
		}
        
		viewMapWindow(map.getMap(), xCoord, yCoord);
		
		if(!proceed)
	        	displayArea.append(newline + "You can't go that way");
		
        displayArea.setCaretPosition(displayArea.getDocument().getLength());
    }

	public static void viewMap(Point[][] map){
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++)
				displayArea.append(map[i][j].getValue() + " ");
			displayArea.append(newline);
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
				displayArea.append(s + " ");
			displayArea.append(newline);
		}
	}
}
