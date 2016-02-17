public class Chunk {
	private char symbol;
	
	/**
	 * Creates a chunk with a specific value.
	 * 
	 * @param value		the value of the chunk
	 */
	public Chunk(char c){
		this.symbol = c;
	}
	
	/**
	 * @return		returns the value of the chunk
	 */
	public char getSymbol(){return symbol;}
}
