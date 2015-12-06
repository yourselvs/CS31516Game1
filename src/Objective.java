public class Objective{
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