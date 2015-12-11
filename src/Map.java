import java.util.Random;


public class Map {	
	private static Chunk[][] chunks;
	private static Point[][] map;
	
	public int numChunks;
	public int chunkSize;
	public int edgeChance = 2;
	
	private static Random rand = new Random(System.currentTimeMillis());

	/**
	 * Creates a square map with a random seed. Dimensions
	 * are determined by numChunks, while the size of each
	 * chunk is determined by chunkSize.
	 * 
	 * @param numChunks		the number of chunks on each side of the map
	 * @param chunkSize		the square dimension of each chunk
	 */
	public Map(int numChunks, int chunkSize){
		this.numChunks = numChunks;
		this.chunkSize = chunkSize;
		chunks = new Chunk[numChunks][numChunks];
		map = new Point[numChunks * chunkSize][numChunks * chunkSize];
		
		this.genMap();
	}
	
	/**
	 * Creates a square map with a set seed. Dimensions
	 * are determined by numChunks, while the size of each
	 * chunk is determined by chunkSize. The same seed will
	 * turn out the same map every time.
	 * 
	 * @param numChunks		the number of chunks on each side of the map
	 * @param chunkSize		the square dimension of each chunk
	 * @param seed			the value in which to seed the random generation
	 */
	public Map(int numChunks, int chunkSize, int seed){
		this.numChunks = numChunks;
		this.chunkSize = chunkSize;
		chunks = new Chunk[numChunks][numChunks];
		map = new Point[numChunks * chunkSize][numChunks * chunkSize];
		
		
		this.genMap();
	}
	
	/**
	 * Generates a new map based off of a random seed. If 
	 * the map was seeded when initialized a new seed will 
	 * be generated.
	 */
	public void genMap(){
		rand = new Random(rand.nextInt());
		genChunks();
		genEdges();
	}
	
	/**
	 * @return		returns the map in an int[][] array
	 */
	public Point[][] getMap(){return map;}
	
	public void revealPoint(int x, int y){map[x][y].revealHiddenDescription();}
	
	private void genChunks(){
		for(int xChunk = 0; xChunk < numChunks; xChunk++){
			for(int yChunk = 0; yChunk < numChunks; yChunk++){
				int value = rand.nextInt(Program.numBiomes);
				chunks[xChunk][yChunk] = new Chunk(value);
				
				for(int x = xChunk * chunkSize; x < (xChunk * chunkSize) + chunkSize; x++){
					for(int y = yChunk * chunkSize; y < (yChunk * chunkSize) + chunkSize; y++){
						map[x][y] = new Point(value, Program.getRandomPointDescription(value), Program.getRandomPointHiddenDescription(value), Program.getRandomPointItems(value));
					}
				}
			}
		}
	}

	private void genEdges(){
		for(int xChunk = 0; xChunk < chunks.length; xChunk++){
			for(int yChunk = 0; yChunk < chunks[0].length; yChunk++){
				if(xChunk != 0 && chunks[xChunk-1][yChunk].getValue() != chunks[xChunk][yChunk].getValue()){
					int x = xChunk * chunkSize;
					for(int y = yChunk * chunkSize; y < (yChunk * chunkSize) + chunkSize; y++){
						if(rand.nextInt() % edgeChance == 0)
							map[x][y].setValue(chunks[xChunk - 1][yChunk].getValue());
					}
				}
				else if(xChunk != numChunks - 1 && chunks[xChunk + 1][yChunk].getValue() != chunks[xChunk][yChunk].getValue()){
					int x = (xChunk * chunkSize) + chunkSize - 1;
					for(int y = yChunk * chunkSize; y < (yChunk * chunkSize) + chunkSize; y++){
						if(rand.nextInt() % edgeChance == 0)
							map[x][y].setValue(chunks[xChunk + 1][yChunk].getValue());
					}
				}
				else if(yChunk != 0 && chunks[xChunk][yChunk - 1].getValue() != chunks[xChunk][yChunk].getValue()){
					int y = yChunk * chunkSize;
					for(int x = xChunk * chunkSize; x < (xChunk * chunkSize) + chunkSize; x++){
						if(rand.nextInt() % edgeChance == 0)
							map[x][y].setValue(chunks[xChunk][yChunk - 1].getValue());
					}
				}
				else if(yChunk != numChunks - 1 && chunks[xChunk][yChunk + 1].getValue() != chunks[xChunk][yChunk].getValue()){ 
					int y = (yChunk * chunkSize) + chunkSize - 1;
					for(int x = xChunk * chunkSize; x < (xChunk * chunkSize) + chunkSize; x++){
						if(rand.nextInt() % edgeChance == 0)
							map[x][y].setValue(chunks[xChunk][yChunk + 1].getValue());
					}
				}
			}
		}
	}
}
