public class Chunk {
	private int value;
	
	/**
	 * Creates a chunk with a specific value.
	 * 
	 * @param value		the value of the chunk
	 */
	public Chunk(int value){
		this.value = value;
	}
	
	/**
	 * @return		returns the value of the chunk
	 */
	public int getValue(){return value;}
}