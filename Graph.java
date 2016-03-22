import java.util.*;
import java.util.concurrent.*;
import java.io.*;

/**
 * A class for a graph, using the adjacency list approach.
 * @author Amy Csizmar Dalal and Ethan Cassel-Mace and Teddy Willard
 * CS 201, Winter 2016
 * date: 07 March 2016
 */
// we used an array blocking queue to implement BFT


public class Graph implements GraphInt   {
    private ArrayList<VertexInt> vertices = new ArrayList<VertexInt>();
    public Graph()
    {
        
    }
    /**
	 * Returns the current list of vertices
	 */
	public ArrayList<VertexInt> getVertices()
    {
        return vertices;
    }

	/**
	 * Adds a vertex to the list of vertices
	 * @param row	The x-position of this vertex in the maze
	 * @param col	The y-position of this vertex in the maze	
	 */
	public void insertVertex(int row, int col)
    {
        vertices.add(new Vertex(row, col));
    }
	
	/**
	 * Adds an edge between two vertices
	 * @param v1 	The first vertex
	 * @param v2	The second vertex
	 */
	public void insertEdge(VertexInt v1, VertexInt v2)
    {
        v1.addNeighbor(v2);
        v2.addNeighbor(v1);
    }
 
	/**
	 * Displays the graph contents by printing out each vertex and its neighbor list.
	 */
	public void printGraph()
    {
        for(VertexInt v : vertices) 
        {
            System.out.println("vertex "+v.toString()+" has vertices: ");
            v.printNeighborList();
        }
        
    }
	
	/**
	 * Conducts a breadth-first search of a graph, starting at the upper left corner of
	 * the maze (0, 0) and continuing to the bottom right corner of the maze. Prunes all 
     * of the visited nodes that are NOT on the shortest path from (0, 0) to the bottom
     * right corner of the maze.
	 * @return	A string listing the vertices visited, in order, on the path from (0, 0) 
     *          to the end of the maze, or an empty string if no path is found.
	 */
	public String breadthFirstTraversal()
    {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(getVertices().size()); //queue used for BFT
        VertexInt currentNode = null;
        int maxCol = 0;
        int maxRow = 0;
        for(VertexInt v : vertices) 
        {
            if (v.getCol() > maxCol)
            {
                maxCol = v.getCol();//max col is used to find the end of the maze
            }
            if (v.getRow() > maxRow)
            {
                maxRow = v.getRow(); //max row is used to find the end of the maze
            }
            // finds the node to start at. when row and col are 0.
            if ((v.getCol() == 0)&&(v.getRow() == 0))
            {
                currentNode = v;
            }
            
        }
        Vertex endNode = new Vertex(maxRow, maxCol); //node to look for at end.
        currentNode.setVisited(true);
        queue.offer(currentNode);
        //this while loop is the basis of the BFT. it enques the connections from a vertex and dequeues to check, over and over again. 
        while ((queue.isEmpty() == false) && (!currentNode.equals(endNode)))
        {
            currentNode = (VertexInt)queue.poll();
            ArrayList<VertexInt> nodes = currentNode.getNeighbors();
            for (VertexInt v: nodes)
            {
                if(v.isVisited() == false)
                {
                    v.setPredecessor(currentNode);
                    v.setVisited(true);
                    queue.offer(v);
                    
                }
            }
        }
        //if maze is impossible
        if (!currentNode.equals(endNode))
        {
                return "";
        }
        //else maze was solved. creates a stack to store the path, then empties the stack to have path in right order.
        else
        {
            Stack path = new Stack();
            path.push(currentNode);
            while (currentNode.getPredecessor()!=null)
            {
                path.push(currentNode.getPredecessor());
                currentNode = currentNode.getPredecessor();
            }
            String result = "";
            while(!path.empty())
            {
                result += path.pop().toString();
                result += ", ";
                result = result.substring(0, result.length()-2);
            }
            
            return result;
        }
                
            
                
    }
}