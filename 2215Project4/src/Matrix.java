/**
 * Devon Vlcek
 * ITCS 2215-001
 * Project 4
 * 
 * The Matrix class builds a 2D array using the 
 * size of the input file as the row and column lengths, 
 * and initializes all values to infinity or 0.  Additional methods
 * populate cells with the edge weight, implement Floyd's 
 * algorithm to find the shortest path from each vertex to 
 * every other vertex, and print both the completed matrix
 * and a list of each path.
 */


import java.util.ArrayList;


public class Matrix {
	
	private int[][] graphMatrix;			// 2d Array to hold shortest path matrix
	private final int INFINITY = Integer.MAX_VALUE;	// infinity constant
	private final int ASCII_CONSTANT = 65;			// constant value used to convert characters to integer values
	

/**
 * Constructor creates a 2D array using a size parameter
 * which will ultimately be the number of lines
 * in the text input.  initializeMatrix() is immediately 
 * called to fill cells with either infinity or 0
 * @param size - row and column length of 2D array
 */
  public Matrix(int size){
	  graphMatrix = new int[size][size];
	  this.initializeMatrix();
  }


  /**
   * This method builds the 2D array while initializing
   * cell values. Each vertex's path to itself is given
   * a weight of 0 while all other cell weights are initialized 
   * to infinity
   */
  public void initializeMatrix(){
  	for(int i = 0; i < graphMatrix.length; i++){
	  	for(int j = 0; j < graphMatrix.length; j++){
	          if(j == i){
	        	  graphMatrix[i][j] = 0;
	          }else{
	        	  graphMatrix[i][j] = INFINITY;
	          }
	  	}
	  }
  }
  
  
  /**
   * This method only populates matrix cells only
   * of neighboring vertex paths from the adjacency text
   * file. All other paths remain at infinity.
   * @param fileLines - ArrayList of lines read from text file
   */
  public void populateCells(ArrayList<String> fileLines){

	  for (int i = 0; i < fileLines.size(); i++){
      	String[] currentLine = fileLines.get(i).split(",");    

      	for(int j = 1; j < currentLine.length; j += 2){	
      		int cellValue = Integer.parseInt(currentLine[j]);
      		int columnIndex = (int)(currentLine[j+1].charAt(0) - ASCII_CONSTANT);
      	   
      		graphMatrix[i][columnIndex] = cellValue;              
         }
      }
  }

  
  /**
   * This method prints the shortest path between every
   * vertex in the graph to the console.  
   */
  public void printShortestPaths(){
	  for(int i = 0; i < graphMatrix.length; i++){
		 for(int j = 0; j < graphMatrix[i].length; j++){
			  System.out.print("[" + Character.toString((char) (i + ASCII_CONSTANT)) + 
					  Character.toString((char) (j + ASCII_CONSTANT)) + ": " + graphMatrix[i][j] + "]");
		  }
		  System.out.print("\n");
	  }
  }

/**
 * Method uses Floyd-Warshall algorithm to populate
 * the matrix with the true shortest path to/from
 * each vertex in the graph. A triple nested for loop
 * iterates through each cell and compares the current weight
 * with the proposed weight by including an intermediary
 * vertex.  Weights are updated when a shorter path is
 * found.
 */
  public void floydTheMatrix() {
	for (int h=0; h < graphMatrix.length; h++){
	   for (int i=0; i < graphMatrix[h].length; i++){
	      for (int j=0; j < graphMatrix[h].length; j++){
	        if ((graphMatrix[i][h] != INFINITY) && (graphMatrix[h][j] != INFINITY) &&
	            (graphMatrix[i][j] > (graphMatrix[i][h] + graphMatrix[h][j]))){
	        	graphMatrix[i][j] = graphMatrix[i][h] + graphMatrix[h][j];
	        }
	      }
	  	}
	  }
	}
  
  
  /**
   * This method prints the complete 2D array
   * in its current state.
   */
  public void printMatrix(){
	  System.out.print("\\");
		for(int i = 0; i < graphMatrix.length; i++){
			System.out.printf("%3s", Character.toString((char) (i + ASCII_CONSTANT)));
		}
		System.out.println();
        for (int y = 0; y < graphMatrix.length; y++) {      	
        	System.out.print(Character.toString((char) (y + ASCII_CONSTANT)));
    	    for (int x = 0; x < graphMatrix[y].length; x++) {
    	        System.out.printf("%3d", graphMatrix[y][x]);
    	    }
    	    System.out.println();
    	}
  }
}
