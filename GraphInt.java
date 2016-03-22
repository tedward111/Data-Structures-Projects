import java.util.*;
import java.util.concurrent.*;
import java.io.*;


/**
 * An interface for a graph, using the adjacency list approach.
 * Your Graph class should implement this interface.
 * @author Amy Csizmar Dalal and Ethan Cassel-Mace and Teddy Willard
 * CS 201, Winter 2016
 * date: 07 March 2016
 */
public interface GraphInt {
	/**
	 * Returns the current list of vertices
	 */
	public ArrayList<VertexInt> getVertices();

	/**
	 * Adds a vertex to the list of vertices
	 * @param row	The x-position of this vertex in the maze
	 * @param col	The y-position of this vertex in the maze	
	 */
	public void insertVertex(int row, int col);
	
	/**
	 * Adds an edge between two vertices
	 * @param v1 	The first vertex
	 * @param v2	The second vertex
	 */
	public void insertEdge(VertexInt v1, VertexInt v2);
 
	/**
	 * Displays the graph contents by printing out each vertex and its neighbor list.
	 */
	public void printGraph();
	
	/**
	 * Conducts a breadth-first search of a graph, starting at the upper left corner of
	 * the maze (0, 0) and continuing to the bottom right corner of the maze. Prunes all 
     * of the visited nodes that are NOT on the shortest path from (0, 0) to the bottom
     * right corner of the maze.
	 * @return	A string listing the vertices visited, in order, on the path from (0, 0) 
     *          to the end of the maze, or an empty string if no path is found.
	 */
	public String breadthFirstTraversal();
}
