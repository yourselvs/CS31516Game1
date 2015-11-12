import java.util.ArrayList;


public class Quest {
	class Objective{
		private String objective;
		private boolean isComplete = false;
		
		/**
		 * Creates an objective to be used as part of 
		 * a quest.
		 * 
		 * @param objective		a description of the objective in a string
		 */
		public Objective(String objective){
			this.objective = objective;
		}
		
		/**
		 * Sets the objective status to complete.
		 */
		public void complete(){isComplete = true;}
		
		/**
		 * @return		returns the description of the objective
		 */
		public String getObjective(){return objective;}
		
		/**
		 * Returns true if the objective has been 
		 * complete. Returns false otherwise.
		 * 
		 * @return		returns the completion status of the objective		
		 */
		public boolean isComplete(){return isComplete;}
	}
	
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
