import java.util.ArrayList;


public class Quest {


	private static String name;
	private static String description;
	private static boolean isFinished;
	private static ArrayList objectives;
	
	//is finished
	public Quest(String name, String description, ArrayList objectives, boolean status){
		
		
		this.name = name;
		
		this.description = description;
		
		this.objectives = objectives;
		
		this.isFinished = status;
		
		
	}
	
	
	
	public void setName(String name){
		
		this.name = name;
	}
	
	public static String getName(){
		
		return name;
		
	}
	
	
	
	public void setDescription(String description){
		
		this.description = description;
	}
	
	public static String getDiscription(){
		
		return description;
		
	}
	
	
	
	
	public void setStatus(boolean status){
		
		this.isFinished = status;
	}
	
	public static boolean getStatus(){
		
		return isFinished;
		
	}
	
	
	
	public void setObjectives(ArrayList objectives){
		
		this.objectives = objectives;
	}
	
	
	public static ArrayList getObjectives(){
		
		return objectives;
		
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
}
