public class Chunk {
	private Biome biome;
	
	/**
	 * Creates a chunk with a specific value.
	 * 
	 * @param value		the value of the chunk
	 */
	public Chunk(Biome c){
		this.biome = c;
	}
	
	/**
	 * @return		returns the biome
	 */
	public Biome getBiome() {return biome;}
}