// Student Name: Rithikraj Sowdermett
// Student Number: 300044941
// Course: ITI 1121 B00
// Assignment 2 




import java.util.Random;

/**
 * The class <b>GameModel</b> holds the model, the state of the systems. 
 * It stores the following information:
 * - the state of all the ``dots'' on the board (mined or not, clicked
 * or not, number of neighbooring mines...)
 * - the size of the board
 * - the number of steps since the last reset
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class GameModel {


     // ADD YOUR INSTANCE VARIABLES HERE
	 int widthOfGame, heightOfGame, numberOfMines, steps, numberUncovered;
	 DotInfo[][] dots;
	 
	 Random rand = new Random();
	 
    /**
     * Constructor to initialize the model to a given size of board.
     * 
     * @param width
     *            the width of the board
     * 
     * @param heigth
     *            the heigth of the board
     * 
     * @param numberOfMines
     *            the number of mines to hide in the board
     */
    public GameModel(int width, int heigth, int numberOfMines) {
        
    // ADD YOU CODE HERE
	widthOfGame = width;
	heightOfGame = heigth;
	this.numberOfMines = numberOfMines;
	reset();
    }


 
    /**
     * Resets the model to (re)start a game. The previous game (if there is one)
     * is cleared up . 
     */
    public void reset(){

        
    // ADD YOU CODE HERE
	dots = new DotInfo[widthOfGame][heightOfGame];
	steps = 0;
	numberUncovered = 0;
	for (int i = 0; i < widthOfGame; i++)
	{
		for (int j = 0; j < heightOfGame; j++)
		{
			dots[i][j] = new DotInfo(i,j);
		}
	}
	// Set Mines
	int r,c;
	int count = 0;
	boolean mine=false;
	while(count<numberOfMines)
	{
		r = rand.nextInt(widthOfGame);
		c = rand.nextInt(heightOfGame);
		while(isMined(r,c) == true)
		{
			r = rand.nextInt(widthOfGame);
			c = rand.nextInt(heightOfGame);
		}
		dots[r][c].setMined();
		for(int i = -1; i < 2; i++)
		{
			for(int j = -1; j < 2; j++)
			{
				try {
					dots[r+i][c+j].setNeighbooringMines(dots[r+i][c+j].getNeighbooringMines()+1);
				} 
				catch (IndexOutOfBoundsException e) {}
			}
		}
		count++;
		
	}

    }


    /**
     * Getter method for the heigth of the game
     * 
     * @return the value of the attribute heightOfGame
     */   
    public int getHeigth(){
        
    // ADD YOU CODE HERE
	return heightOfGame;
    }

    /**
     * Getter method for the widthOfGame of the game
     * 
     * @return the value of the attribute widthOfGameOfGame
     */   
    public int getWidth(){
        
    // ADD YOU CODE HERE
	return widthOfGame;
    }



    /**
     * returns true if the dot at location (i,j) is mined, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isMined(int i, int j){
        
    // ADD YOU CODE HERE
	return dots[i][j].isMined();
    }

    /**
     * returns true if the dot  at location (i,j) has 
     * been clicked, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean hasBeenClicked(int i, int j){
        
    // ADD YOU CODE HERE
	return dots[i][j].hasBeenClicked();
    }

  /**
     * returns true if the dot  at location (i,j) has zero mined 
     * neighboor, false otherwise
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isBlank(int i, int j){
        
    // ADD YOU CODE HERE
	if(dots[i][j].getNeighbooringMines() == 0)
		return true;
	else
		return false;
    }
    /**
     * returns true if the dot is covered, false otherwise
    * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */   
    public boolean isCovered(int i, int j){
        
    // ADD YOU CODE HERE
	return dots[i][j].isCovered();
    }

    /**
     * returns the number of neighbooring mines os the dot  
     * at location (i,j)
     *
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the number of neighbooring mines at location (i,j)
     */   
    public int getNeighbooringMines(int i, int j){
        
    // ADD YOU CODE HERE
	return dots[i][j].getNeighbooringMines();
    }


    /**
     * Sets the status of the dot at location (i,j) to uncovered
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void uncover(int i, int j){
        
    // ADD YOU CODE HERE
	dots[i][j].uncover();
	numberUncovered++;
    }

    /**
     * Sets the status of the dot at location (i,j) to clicked
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */   
    public void click(int i, int j){
        
    // ADD YOU CODE HERE
	dots[i][j].click();
    }
     /**
     * Uncover all remaining covered dot
     */   
    public void uncoverAll(){
        
    // ADD YOU CODE HERE
	for (int i = 0; i < widthOfGame; i++)
	{
		for (int j = 0; j < heightOfGame; j++)
		{
			if (isCovered(i,j)==true)
			{
				uncover(i,j);
			}
		}
	}
    }

 

    /**
     * Getter method for the current number of steps
     * 
     * @return the current number of steps
     */   
    public int getNumberOfSteps(){
        
    // ADD YOU CODE HERE
	return steps;
    }

  

    /**
     * Getter method for the model's dotInfo reference
     * at location (i,j)
     *
      * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     *
     * @return model[i][j]
     */   
    public DotInfo get(int i, int j) {
        
    // ADD YOU CODE HERE
	return dots[i][j];
    }


   /**
     * The metod <b>step</b> updates the number of steps. It must be called 
     * once the model has been updated after the player selected a new square.
     */
     public void step(){
        
    // ADD YOU CODE HERE
	steps++;
    }
 
   /**
     * The metod <b>isFinished</b> returns true iff the game is finished, that
     * is, all the nonmined dots are uncovered.
     *
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished(){
        
    // ADD YOU CODE HERE
	
	
	boolean finished = true;
	for (int i = 0; i < widthOfGame; i++)
	{
		for (int j = 0; j < heightOfGame; j++)
		{
			if ((isMined(i,j)==false) && (isCovered(i,j) == true))
			{
				finished = false;
			}
		}
	}
	return finished;
	
		
    }


   /**
     * Builds a String representation of the model
     *
     * @return String representation of the model
     */
    public String toString(){
        
    // ADD YOU CODE HERE
	String r = "";
	for (int j = 0; j < heightOfGame+1; j++)
	{
		if (j < heightOfGame)
		{
			r+=j+"    ";
			for(int i = 0; i < widthOfGame; i++)
			{
				if (get(i,j).isCovered()==true)
				{

					r+= " * ";
				}
				else
				{
					if (get(i,j).isMined()== true)
						r+=" M ";
					else
						r+=" " + get(i,j).getNeighbooringMines() + " ";
				}
			}
		}
		else
		{
			r+="\n";
			r+= "     ";
			for (int k = 0; k < widthOfGame; k++)
			{
				r+=" " + k + " ";
			}
		}
		r+="\n";
	}
	return r;
    }
	
	////////////////BONUS////////////////
	public boolean isFlagged(int x, int y)
	{
		return dots[x][y].isFlagged();
	}
	
	public void flag(int x, int y)
	{
		dots[x][y].flag();
	}
	
	public void unFlag(int x, int y)
	{
		dots[x][y].unFlag();
	}
	//////////////////////////////////////
	

}
