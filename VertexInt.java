import java.util.*;

/**
 * An interface for a graph vertex. 
 * Assumes the adjacency list model of storing edges.
 * @author Amy Csizmar Dalal and Ethan Cassel-Mace and Teddy Willard
 * CS 201, Winter 2016
 * date: 07 March 2016
 */
public interface VertexInt {

	/**
	 * Returns whether this vertex has been visited on the latest graph traversal.
	 */
	public boolean isVisited();

	/**
	 * Sets whether this vertex has been visited (true) or resets it (false).
	 */
	public void setVisited(boolean visited);

    /**
     * Returns the predecessor (the vertex visited prior to this one) to this vertex.
     */
    public VertexInt getPredecessor();

    /**
     * Sets the predecessor for this vertex.
     */
    public void setPredecessor(VertexInt p);

	/**
	 * Returns the x-coordinate of this vertex in the maze.
	 */
	public int getRow();

	/**
	 * Returns the y-coordinate of this vertex in the maze.
	 */
	public int getCol();

	/**
	 * Returns a list of the neighbors of this node.
	 */
	public ArrayList<VertexInt> getNeighbors();
	
	/**
	 * Adds a new neighbor to the list of neighbors for this vertex
	 * @param v	The new neighbor (vertex)
	 */
	public void addNeighbor(VertexInt v);
	
	/**
	 * Compares two vertices to see if they are equal. Two vertices are equal if their
	 * row and column values are identical.
	 * @param v		The vertex to which to compare this vertex.
	 * @return 		true if the 2 vertices are equal, false otherwise
	 */
	public boolean equals(VertexInt v);
	
	/** 
	 * Returns true if the vertex v is a neighbor of this vertex, and false otherwise.
	 */
	public boolean isNeighbor(VertexInt v);
	
	/**
	 * Returns the vertex as a (row, col) string.
	 * @see java.lang.Object#toString()
	 */
	public String toString();
	
	/**
	 * Prints out a list of the neighbors of this node
	 */
	public void printNeighborList();
	
}
