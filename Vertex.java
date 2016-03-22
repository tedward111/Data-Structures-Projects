import java.util.*;

/**
 * A class for a graph vertex. 
 * Assumes the adjacency list model of storing edges.
 * @author  Ethan Cassel-Mace and Teddy Willard
 * CS 201, Winter 2016
 * date: 07 March 2016
 */
public class Vertex implements VertexInt   {
    private int row; //row of vertex
    private int col;  // col of vertex
    private boolean visited; // if vertex has been visited
    private ArrayList<VertexInt> neighbors = new ArrayList<VertexInt>(); // list of neighbors or edges
    private VertexInt predecessor; // vertex that was visited prior.
    
    
    //constructor for vertex. accepts row and col. sets visited and predecissor to null.
    public Vertex(int rowc, int colc)
    {
        row = rowc;
        col = colc;
        visited = false;
        predecessor = null;
        
    }

	/**
	 * Returns whether this vertex has been visited on the latest graph traversal.
	 */
	public boolean isVisited()
        {
            return visited;
        }
	/**
	 * Sets whether this vertex has been visited (true) or resets it (false).
	 */
	public void setVisited(boolean visited)
        {
            this.visited = visited;
        }

    /**
     * Returns the predecessor (the vertex visited prior to this one) to this vertex.
     */
    public VertexInt getPredecessor()
        {
            return predecessor;
        }

    /**
     * Sets the predecessor for this vertex.
     */
    public void setPredecessor(VertexInt p)
        {
            predecessor = p;
        }

	/**
	 * Returns the x-coordinate of this vertex in the maze.
	 */
	public int getRow()
        {
            return row;
        }
	/**
	 * Returns the y-coordinate of this vertex in the maze.
	 */
	public int getCol()
        {
            return col;
        }
	/**
	 * Returns a list of the neighbors of this node.
	 */
	public ArrayList<VertexInt> getNeighbors()
        {
            return neighbors;
        }
	/**
	 * Adds a new neighbor to the list of neighbors for this vertex
	 * @param v	The new neighbor (vertex)
	 */
	public void addNeighbor(VertexInt v)
        {
            if (!neighbors.contains(v))
            {
                neighbors.add(v);
            }
        }
	
	/**
	 * Compares two vertices to see if they are equal. Two vertices are equal if their
	 * row and column values are identical.
	 * @param v		The vertex to which to compare this vertex.
	 * @return 		true if the 2 vertices are equal, false otherwise
	 */
	public boolean equals(VertexInt v)
        {
            if ((col == v.getCol())&&(row == v.getRow()))
                return true;
            else
                return false;
        }
	/** 
	 * Returns true if the vertex v is a neighbor of this vertex, and false otherwise.
	 */
	public boolean isNeighbor(VertexInt v)
        {
            for (VertexInt i: neighbors)
            {
                if (i.equals(v))
                    return true;
            }
            return false;
        }
	/**
	 * Returns the vertex as a (row, col) string.
	 * @see java.lang.Object#toString()
	 */
	public String toString()
        {
            return ("(" + row + ","+col+")");
        }
                
                
	/**
	 * Prints out a list of the neighbors of this node
	 */
	public void printNeighborList()
            {
                    for (VertexInt i: neighbors)
                {
                    System.out.println(i.toString());
                }
            }
        
	
}

    