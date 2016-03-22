/**
 * A partial class representing (and solving) a maze. Your task is to fill in the rest of
 * this class definition.
 *
 * @author Amy Csizmar Dalal, Teddy Willard, and Alex Schneider
 * CS 201, Fall 2015
 * date: 13 October 2015
 */

import java.util.*;
import java.io.*;

public class Maze {
	/** The dimensions of the maze */
	private int numRows, numCols;

	/** A 2-D array that stores the maze squares */
	private MazeSquare[][] maze;

	/**
	 * Constructor: initializes a maze given a maze file
	 * @param f   The name of the file storing the maze
	 */
	public Maze(String f) {
		loadFile(f);
	}

	/**
	 * Reads in a maze from a file and stores it.
	 * @param f  The name of the file storing the maze
	 */
	public void loadFile(String f) {
		Scanner mazeFile = null;
        /* These next variables temporarily store the current line, and the current
           contents of this line.
        */
		String currLine;
		String[] fields;

        /* We use line count to keep track of whether we're reading in left/right walls,
           top/bottom walls, or the dimensions of the maze.
        */
		int lineCount = 0;

        // Open the file for processing, deal with any errors that arise
		try {
			mazeFile = new Scanner(new File(f));
		} catch (FileNotFoundException e) {
			System.err.println("Error: file " + f + " not found. Exiting...");
			System.exit(0);
		}

        // Read in and process the contents of the maze file
		while (mazeFile.hasNextLine()) {
			currLine = mazeFile.nextLine();
			lineCount++;

			if (lineCount == 1) {
				// extract the number of rows and columns and initialize the matrix of maze squares
				fields = currLine.split("\\s");
				numRows = Integer.parseInt(fields[0]);
				numCols = Integer.parseInt(fields[1]);
				maze = new MazeSquare[numRows][numCols];
				for (int i=0; i<numRows; i++) {
					for (int j=0; j<numCols; j++) {
						maze[i][j] = new MazeSquare();
					}
				}

			} else {
                // the line contains either left/right or top/bottom wall data

                /* Split the current line so that each value is stored in a list.
                   Note that at this point, they are still String values.
                */
				fields = currLine.split("\\s");
				if (lineCount <= numRows + 1) {
					// read and set the correct values for left/right walls
					for (int i=0; i<fields.length; i++) {
						/* Since all walls are set to true, sets left and right walls that should be deleted to false
                        */
                        if(fields[i].equals("0")){
                            maze[lineCount - 2][i + 1].setHasLeftWall(false);
                            maze[lineCount - 2][i].setHasRightWall(false);
                        }
					}
				} else {
					// read and set the correct values for top/bottom walls
					for (int j=0; j<fields.length; j++) {
						/* Since all walls are set to true, sets top and bottom walls that should be deleted to false
                        */
                        if(fields[j].equals("0")){
                            maze[j + 1][lineCount - numRows - 2].setHasTopWall(false);
                            maze[j][lineCount - numRows - 2].setHasBottomWall(false);
                        }
					}

				}
			}
		}

		/* Remove the top wall from the top left square and the right wall from the bottom right square, to "open" the maze.
		*/
		maze[0][0].setHasTopWall(false);
		maze[numRows - 1][numCols - 1].setHasRightWall(false);

	}

	/**
	 * Prints the maze out to the screen.
	 */
	public void print() {
		for (int i=0; i<numRows; i++) {
			// Draw the top walls
			System.out.print("+");
			for (int j=0; j<numCols; j++) {
				if (maze[i][j].getHasTopWall()) {
					System.out.print("---+");
				} else {
					System.out.print("   +");
				}
			}
			System.out.println();

			// Draw the left walls
			for (int j=0; j<numCols; j++) {
				if (maze[i][j].getHasLeftWall()) {
					if (maze[i][j].getMark()) {
						System.out.print("| X ");
					} else {
						System.out.print("|   ");
					}
				} else {
					if (maze[i][j].getMark()) {
						System.out.print("  X ");
					} else {
						System.out.print("    ");
					}
				}
			}

			// Draw the rightmost wall for this row, if it exists
			if (maze[i][numCols - 1].getHasRightWall()) {
				System.out.print("|");
			} else {
				System.out.print(" ");
			}
			System.out.println();
		}

		// Draw the bottom walls of the bottom row of squares
		if (numRows > 0) {
			System.out.print("+");
			for (int j=0; j<numCols; j++) {
				if (maze[numRows - 1][j].getHasBottomWall()) {
					System.out.print("---+");
				} else {
					System.out.print("   +");
				}
			}
			System.out.println();
		}

	}

	/* Solves maze recursively using a completed maze as the base case and using the framework
	presented in the textbook.
    */
   public boolean solveMaze(int row, int column){
        boolean atEnd = false;
        int choiceNumber = 0;
        while(atEnd == false && choiceNumber < 4) {
            if(tryNext(choiceNumber, row, column)){
                //Checks if maze has been completed (i.e. checks for base case)
                if(maze[numRows - 1][numCols - 1].getMark()) {
                    return true;
                }
                else {
                    //Checks each possible maze direction recursively (i.e. reducing the problem)
                    if(choiceNumber == 0){
                        row++;
                        atEnd = solveMaze(row, column);
                    }
                    else if(choiceNumber == 1){
                        column++;
                        atEnd = solveMaze(row, column);
                    }
                    else if(choiceNumber == 2){
                        row--;
                        atEnd = solveMaze(row, column);
                    }
                    else if(choiceNumber == 3){
                        column--;
                        atEnd = solveMaze(row, column);
                    }
                }
                //Removes mark if maze direction did not work (i.e Backtracks)
                if(atEnd == false){
                    maze[row][column].setMark(false);
                    if(choiceNumber == 0){
                        row--;
                    }
                    if(choiceNumber == 1){
                        column--;
                    }
                    if(choiceNumber == 2){
                        row++;
                    }
                    if(choiceNumber == 3){
                        column++;
                    }
                }
            }
            choiceNumber++;
        }
         return atEnd;   
   } 
   
   /* Checks if entering the desired maze square is a possibility based on whether there is
   a wall or a mark present in the desired maze square.
   */
   public boolean tryNext(int choiceNumber, int row, int column){
        if(choiceNumber == 0){
            row++;
            if(row >= 0 && row < numRows){
                if(!(maze[row][column].getHasTopWall()) && !(maze[row][column].getMark())){
                maze[row][column].setMark(true);
                return true;
                }
            }
        }
        else if(choiceNumber == 1){
            column++;
            if(column >= 0 && column < numCols){
                if(!(maze[row][column].getHasLeftWall()) && !(maze[row][column].getMark())){
                maze[row][column].setMark(true);
                return true;
                }
            }
        }
        else if(choiceNumber == 2){
            row--;
            if(row >= 0 && row < numRows){
                if(!(maze[row][column].getHasBottomWall()) && !(maze[row][column].getMark())){
                maze[row][column].setMark(true);
                return true;
                }
            }
        }
        else if(choiceNumber == 3){
            column--;
            if(column >= 0 && column < numCols){
                if(!(maze[row][column].getHasRightWall()) && !(maze[row][column].getMark())){
                maze[row][column].setMark(true);
                return true;
                }
            }
        }
        return false;
   }
   

	/**
	 * Generates a maze from a file, then finds a path through the maze if one exists.
	 * @param args	The name of the file containing the maze (Specify this on the command line!)
	 */
	public static void main(String[] args) {
	    Maze m = new Maze("maze4.txt");
        // Puts a mark on the first mazesquare
        m.maze[0][0].setMark(true);
        // Calls recursive function to solve maze
        boolean answer = m.solveMaze(0,0);
        // If maze is solved, prints that it has been solved.
        if(answer) System.out.println("The maze has been solved!");
        // If maze is not solved, prints that it has no solution and deletes mark from first mazesquare
        else {
            System.out.println("The maze has no solution.");
            m.maze[0][0].setMark(false);
            }

        // Display the maze (if unsolved, none of the squares should be marked as visited)
		m.print();

	}

}
