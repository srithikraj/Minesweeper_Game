// Student Name: Rithikraj Sowdermett
// Student Number: 300044941
// Course: ITI 1121 B00
// Assignment 2 




import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;
import java.awt.*;

import java.util.*;


/**
 * The class <b>GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener {

    // ADD YOUR INSTANCE VARIABLES HERE
	int widthOfGame, heightOfGame, numberOfMines;
	boolean flagDots;
	GameModel gm;
	GameView gv;
    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     * @param numberOfMines
     *            the number of mines hidden in the board
     */
	public GameController(int width, int height, int numberOfMines) {

		// ADD YOU CODE HERE
		widthOfGame = width;
		heightOfGame = height;
		gm = new GameModel(width,height, numberOfMines);
		gv = new GameView(gm,this);
		flagDots = false;

	}


	/**
		* Callback used when the user clicks a button (reset or quit)
		*
		* @param e
		*            the ActionEvent
		*/

	public void actionPerformed(ActionEvent e) {
        
		// ADD YOU CODE HERE
	
		try
		{
			if (e.getSource() == gv.resetButton)
			{
				reset();
				gv.steps.setText("Number of steps: " + gm.getNumberOfSteps());
			}
			if (e.getSource() == gv.quitButton)
			{
				System.exit(0);
			}
			if(e.getSource() instanceof DotButton)
			{
				int tempX = ((DotButton)(e.getSource())).getColumn();
				int tempY = ((DotButton)(e.getSource())).getRow();
				//////////////Bonus///////////////////
				if (flagDots == true && gm.isCovered(tempX,tempY)==true)
				{
					if (gm.isFlagged(tempX,tempY) == false)
						gm.flag(tempX,tempY);
					else
						gm.unFlag(tempX,tempY);
					gv.update();
				}
				//////////////////////////////////////
				else
				{
					if (gm.isFlagged(tempX,tempY)==false)//Bonus
					{
						if (gm.hasBeenClicked(tempX,tempY)==false)
							gm.step();
						play(tempX,tempY);
						gv.steps.setText("Number of steps: " + gm.getNumberOfSteps());
						gv.update();
						if (gm.isMined(tempX,tempY) == true)
						{
							Object[] options = {"Quit",
							                    "Play Again"};
							int n = JOptionPane.showOptionDialog(gv,
							    "Aouch, you lost in "+gm.getNumberOfSteps()+" steps!\nWould you like to play again?",
							    "Boom!",
							    JOptionPane.YES_NO_OPTION,
							    JOptionPane.INFORMATION_MESSAGE,
							    null,     //do not use a custom Icon
							    options,  //the titles of buttons
							    options[0]);
							if (n==0)
								gv.quitButton.doClick();
							else if (n==1){
								reset();
								gv.steps.setText("Number of steps: " + gm.getNumberOfSteps());
							}
						}
						else if (gm.isFinished() == true)
						{			
							Object[] options = {"Quit",
							                    "Play Again"};
							int n = JOptionPane.showOptionDialog(gv,
							    "Congratulations! You Won in "+gm.getNumberOfSteps()+" steps!\nWould you like to play again?",
							    "Won",
							    JOptionPane.YES_NO_OPTION,
							    JOptionPane.INFORMATION_MESSAGE,
							    null,     //do not use a custom Icon
							    options,  //the titles of buttons
							    options[0]);
							if (n==0)
								gv.quitButton.doClick();
							else if (n==1){
								reset();
								gv.steps.setText("Number of steps: " + gm.getNumberOfSteps());
							}
						}
					}
				}
			
			}
			if(e.getSource() == gv.flagButton)//Bonus
			{
				flagDots = !flagDots;
				gv.flagButton.setBackground(Color.GREEN);
				gv.update();
			
			}
		}
		catch (Exception ee){}
		}

    /**
     * resets the game
     */
    private void reset(){

    // ADD YOU CODE HERE
	gm.reset();
	gv.update();
    }

    /**
     * <b>play</b> is the method called when the user clicks on a square.
     * If that square is not already clicked, then it applies the logic
     * of the game to uncover that square, and possibly end the game if
     * that square was mined, or possibly uncover some other squares. 
     * It then checks if the game
     * is finished, and if so, congratulates the player, showing the number of
     * moves, and gives to options: start a new game, or exit
     * @param width
     *            the selected column
     * @param heigth
     *            the selected line
     */
    private void play(int width, int height){

    // ADD YOU CODE HERE
	if(gm.isCovered(width,height)==true)
	{
		gm.click(width,height);
		if (gm.isMined(width,height) == true)
		{
			gm.uncoverAll();
		}
		else if (gm.isFinished() == true)
		{
			gm.uncoverAll();			
		}
		else if (gm.isBlank(width,height))
		{
			clearZone(gm.get(width,height));
		}
		//System.out.println(gm.toString());
	}
	//gv.update();
    }

   /**
     * <b>clearZone</b> is the method that computes which new dots should be ``uncovered'' 
     * when a new square with no mine in its neighborood has been selected
     * @param initialDot
     *      the DotInfo object corresponding to the selected DotButton that
     * had zero neighbouring mines
     */
    private void clearZone(DotInfo initialDot) {


    // ADD YOU CODE HERE
	int x = initialDot.getX();
	int y = initialDot.getY();
	for(int i = -1; i < 2; i++)
	{
		for(int j = -1; j < 2; j++)
		{
			try {
				play(x+i,y+j);
			} 
			catch( IndexOutOfBoundsException a){}
		}
	}
    }
	
}
