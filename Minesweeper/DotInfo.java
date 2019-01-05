// Student Name: Rithikraj Sowdermett
// Student Number: 300044941
// Course: ITI 1121 B00
// Assignment 2 





/**
 * The class <b>DotInfo</b> is a simple helper class to store 
 * the state (e.g. clicked, mined, number of neighbooring mines...) 
 * at the dot position (x,y)
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class DotInfo {

     // ADD YOUR INSTANCE VARIABLES HERE
	 int x, y, neighbooringMines;
	 boolean isCovered, isMined, wasClicked, isFlagged;
    /**
     * Constructor, used to initialize the instance variables
     * 
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */
    public DotInfo(int x, int y){

    // ADD YOU CODE HERE
	this.x = x;
	this.y = y;
	isCovered  = true;
	isMined = false;
	wasClicked = false;
	isFlagged = false;
	neighbooringMines = 0;
    }

    /**
     * Getter method for the attribute x.
     * 
     * @return the value of the attribute x
     */
    public int getX(){

    // ADD YOU CODE HERE
	return x;
    }
    
    /**
     * Getter method for the attribute y.
     * 
     * @return the value of the attribute y
     */
    public int getY(){

    // ADD YOU CODE HERE
	return y;
    }
    
 
    /**
     * Setter for mined
     */
    public void setMined() {

    // ADD YOU CODE HERE
	isMined = true;
    }

    /**
     * Getter for mined
     *
     * @return mined
     */
    public boolean isMined() {

    // ADD YOU CODE HERE
	return isMined;
    }


    /**
     * Setter for covered
     */
    public void uncover() {

    // ADD YOU CODE HERE
	isCovered = false;
    }

    /**
     * Getter for covered
     *
     * @return covered
     */
    public boolean isCovered(){

    // ADD YOU CODE HERE
	return isCovered;
    }



    /**
     * Setter for wasClicked
     */
    public void click() {

    // ADD YOU CODE HERE
	wasClicked = true;
	isCovered = false;
    }


    /**
     * Getter for wasClicked
     *
     * @return wasClicked
     */
    public boolean hasBeenClicked() {

    // ADD YOU CODE HERE
	return wasClicked;
    }


    /**
     * Setter for neighbooringMines
     *
     * @param neighbooringMines
     *          number of neighbooring mines
     */
    public void setNeighbooringMines(int neighbooringMines) {

    // ADD YOU CODE HERE
	this.neighbooringMines = neighbooringMines;
    }

    /**
     * Get for neighbooringMines
     *
     * @return neighbooringMines
     */
    public int getNeighbooringMines() {

    // ADD YOU CODE HERE
	return neighbooringMines;
    }
	
	/////////////////////// Bonus//////////////////////
	public boolean isFlagged()
	{
		return isFlagged;
	}
	
	public void flag()
	{
		isFlagged = true;
	}
	
	public void unFlag()
	{
		isFlagged = false;
	}
	/////////////////////////////////////////////////

 }
