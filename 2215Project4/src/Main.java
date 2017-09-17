/**
 * Devon Vlcek
 * 2215-001
 * Project 4
 * 
 * This program reads vertex and weight information
 * from a text adjacency list, creates a Matrix object (2D array)
 * from the vertex and edge information in the text file
 * and calls a Floyd-Warshall method to find the shortest
 * path from each vertex to every other vertex in the graph.
 * The final vertex/path information is then printed to the 
 * console.
 * 
 */


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException
	{
		//initialize a list of Strings to hold vertex/edge data
    	ArrayList<String> vertexEdgeData = new ArrayList<String>();
        
        Scanner input = new Scanner(new File("graph.txt"));
        
        // program reads text file and builds the ArrayList
        while(input.hasNext()){     
        	vertexEdgeData.add(input.nextLine());        
        }
        
        input.close();
        
        Collections.sort(vertexEdgeData);

        // new Matrix object created, 2D array initialized to the size of the ArrayList
        Matrix graphMatrix = new Matrix(vertexEdgeData.size());   


        //text file edge weights are placed into the Matrix object
		graphMatrix.populateCells(vertexEdgeData);
				
		
		//Shortest path to/from each matrix is found using Floyd-Warshall algorithm
		graphMatrix.floydTheMatrix();
			graphMatrix.printMatrix();
			System.out.println("\n**********\n");
			
		// Each path to and from every vertex
		// is printed to the console
		graphMatrix.printShortestPaths();
		

	}
	
}
	
