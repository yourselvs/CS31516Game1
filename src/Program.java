import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Scanner;


import com.sun.prism.paint.Color;


public class Program {
	public static Scanner keyboard = new Scanner(System.in);
	public static Random rand = new Random(System.currentTimeMillis());
	
	public static void main(String[] args){
		int numChunks = 3;
		int chunkSize = 5;
		
		Map map = new Map(numChunks, chunkSize);
		
		exploreMap(map.getMap(), map.getMap().length / 2, map.getMap().length / 2);
		
		//viewMap(map.getMap());
		
	}
	
	public static void viewMap(char[][] map){
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++)
				System.out.print(map[i][j] + " ");
			System.out.println();
		}
	}
	
	public static void exploreMap(char[][] map, int xCoord, int yCoord){
		
		if(xCoord < 0)
			xCoord = 0;
		else if(xCoord > map.length)
			xCoord = map.length - 1;
		else if(yCoord < 0)
			yCoord = 0;
		else if(yCoord > map[0].length)
			yCoord = map[0].length - 1;
		
		String input;
		do{
			for(int x = xCoord - 3; x < xCoord + 4; x++){
				for(int y = yCoord - 3; y < yCoord + 4; y++){
					if(x < 1 || x > map.length - 1 || y < 1 || y > map[0].length - 1)
						System.out.print(' ');
					else if(x == xCoord && y == yCoord)
						System.out.print('X');
					else{
						System.out.print(map[x][y]);
					}
					System.out.print(" ");
				}
				System.out.println();
			}
			
			input = keyboard.nextLine();
			for(int i = 0; i < 50; i++)
				System.out.println();
			
			if(input.equals("w")){
				if(xCoord == 1){
					System.out.println("You can't go that way");
				}
				else
					xCoord--;
			}
			else if(input.equals("a")){
				if(yCoord == 1){
					System.out.println("You can't go that way");
				}
				else
					yCoord--;
			}
			else if(input.equals("s")){
				if(xCoord == map.length - 1){
					System.out.println("You can't go that way");
				}
				else
					xCoord++;
			}
			else if(input.equals("d")){
				if(yCoord == map.length - 1){
					System.out.println("You can't go that way");
				}
				else
					yCoord++;
			}
			
		} while(!input.equals("exit"));
	}
}
