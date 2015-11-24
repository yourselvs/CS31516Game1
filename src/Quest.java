import java.util.ArrayList;


public class Quest {	
	private String name;
	private String description;
	private boolean isFinished = false;
	private ArrayList<Objective> objectives;
	
	/**
	 * Creates a quest that can be interacted with
	 * on several different variables.
	 * 
	 * @param name			the name of the quest, should be pre-formatted
	 * @param description	a short sentence of the description of the quest
	 * @param objectives	a list of objectives to complete the quest
	 */
	public Quest(String name, String description, ArrayList<Objective> objectives){
		this.name = name;
		this.description = description;
		this.objectives = objectives;
	}
	
	public String getName(){return name;}	
	public String getDiscription(){return description;}
	public ArrayList<Objective> getObjectives(){return objectives;}
	public boolean isFinished(){return isFinished;}
}
