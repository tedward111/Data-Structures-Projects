import java.io.*;
import java.util.*;

/**
 * Find the shortest path through a maze using graph traversals.
 * @author Amy Csizmar Dalal and Ethan Cassel-Mace and Teddy Willard
 * CS 201, Winter 2016
 * date: 07 March 2016
 */
public class MazeSolver {

	/**
	 * Reads in the maze from a text file, creates a graph to represent the maze, and then calls
	 * a breadth-first traversal to find the shortest path through the maze.
	 * @param args	The command line argument is the name of the maze file.
	 */
	public static void main(String[] args) {
		Graph maze = new Graph();
		Scanner infile = null;
		String line;
		String[] fields;
		
		// The line number is also the current row number in the maze.
		int lineNum = 0;
	
		// Open the file; exit if the file does not exist.
		try {
			infile = new Scanner(new File(args[0]));
		} catch (FileNotFoundException e) {
			System.err.println("Error: " + args[0] + " not found, exiting.");
			System.exit(0);
		}
		
		/*
		 * Read in each line in the file. Each line represents a row in the maze. Each row
		 * contains information for each column: a 0 in the column means there's an empty
		 * space here (a vertex), while a 1 means there's a wall here (no vertex). Create
		 * a vertex at (lineNum, col) every time you encounter a 0 in the file.
		 */
		while (infile.hasNextLine()) {
			line = infile.nextLine();
			fields = line.split("\\s"); // split on spaces 
			for (int col=0; col < fields.length; col++) {
				if (Integer.parseInt(fields[col]) == 0) {
					maze.insertVertex(lineNum, col);
				}
			}
			lineNum++;
		}
		
		infile.close();

		/*
		 * Once the vertices are determined, figure out the edges. An edge exists
		 * between two vertices if they are adjacent, i.e. if the row and column differ by 0 or 1.
		 */
        // Determines where the graph vertices are, and add to the neighbor lists of the appropriate vertices.
        ArrayList<VertexInt> vertices = maze.getVertices();
        
        for(VertexInt v1: vertices)
        {
            for (VertexInt v2: vertices)
            {
                //checks if vertex is adjacent to another one. 
                if(((Math.abs(v1.getCol()-v2.getCol())==1)&&(v1.getRow()-v2.getRow())==0)||((Math.abs(v1.getRow()-v2.getRow())==1)&&(v1.getCol()-v2.getCol())==0))
                {
                    maze.insertEdge(v1, v2);
                }
            }
        }
        
		
		// Optional: print out the graph to see if you read it in correctly.
		maze.printGraph();
		
		// Solve the maze by breadth-first traversal
		String solution = maze.breadthFirstTraversal();
		if (solution.equals("")) {
			System.out.println("No solution found.");			
		} else {
            System.out.println("Solution found: " + solution);
		}
	}
}
