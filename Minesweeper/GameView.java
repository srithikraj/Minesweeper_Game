// Student Name: Rithikraj Sowdermett
// Student Number: 300044941
// Course: ITI 1121 B00
// Assignment 2 








import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out an instance of  <b>BoardView</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {

	// ADD YOUR INSTANCE VARIABLES HERE
	GameModel gm;
	GameController gc;
	DotButton[][] dots;
	int width, height;
	JPanel board = new JPanel();
	JPanel buttonsPanel = new JPanel();
	JButton resetButton;
	JButton quitButton;
	JButton flagButton;
	JLabel steps;
    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel gameModel, GameController gameController) {
        
    // ADD YOU CODE HERE
	gm = gameModel;
	gc = gameController;
	width = gm.getWidth();
	height = gm.getHeigth();
	dots = new DotButton[width][height];
	
	GridBagLayout grid= new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	
 	resetButton = new JButton();
 	quitButton = new JButton();
	flagButton = new JButton();
	
	
	board.setLayout(grid);
	setLayout(new BorderLayout());
	buttonsPanel.setLayout(grid);
	
	setTitle("MINESWEEPER by Rithik Sowdermett, 300044941");
	
	//filling the grid with dotButton s
	for (int x = 0; x < width; x++)
	{
		for(int y = 0; y < height; y++)
		{	
			dots[x][y] = new DotButton(x,y,getIcon(x,y));
			dots[x][y].addActionListener(gc);
			c.gridx = x;
			c.gridy = y;
			board.add(dots[x][y],c);
		}
	}
	c.gridx = 1;
	c.gridy = 1;
	add(board,BorderLayout.CENTER);
	
	
	//Reset, Quit, and Flag Mode(Bonus) buttons
	
	//Reset Button
	resetButton.setLabel("Reset");
	resetButton.addActionListener(gc);
	c.gridx = 1;
	buttonsPanel.add(resetButton, c);
	
	//Quit Button
	quitButton.setLabel("Quit");
	quitButton.addActionListener(gc);
	c.gridx = 2;
	buttonsPanel.add(quitButton, c);
	
	//Label to display Number of steps
	c.gridx = 0;
	steps = new JLabel("Number of steps: " + gm.getNumberOfSteps());
	buttonsPanel.add(steps,c);
	
	//Flag Mode Button - Bonus//
	flagButton.setLabel("Flag Mode - OFF");
	flagButton.addActionListener(gc);
	c.gridx = 3;
	buttonsPanel.add(flagButton, c);
	
	
	
	add(buttonsPanel, BorderLayout.PAGE_END);
	
	
	pack();
	setSize(width*34,height*34);
	setResizable(false);
	setVisible(true);
    }

    /**
     * update the status of the board's DotButton instances based 
     * on the current game model, then redraws the view
     */

    public void update(){
        
    // ADD YOU CODE HERE
	for (int a = 0; a < width; a++)
	{
		
		for(int b = 0; b < height; b++)
		{
			dots[a][b].setIconNumber(getIcon(a,b));
		}
	}
	//Bonus
	if (gc.flagDots == true)
	{
		flagButton.setLabel("Flag Mode - ON");
	}
	else
	{
		flagButton.setLabel("Flag Mode - OFF");
	}
	repaint();
    }

    /**
     * returns the icon value that must be used for a given dot 
     * in the game
     * 
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the icon to use for the dot at location (i,j)
     */   
    private int getIcon(int i, int j){
        
		// ADD YOU CODE HERE
		int k;
		if (gm.isCovered(i,j))
			if (gm.isFlagged(i,j))//Bonus
				k = DotButton.FLAGGED;//Bonus
			else
				k = DotButton.COVERED;//11
		else if (gm.isMined(i,j))
		{
			if (gm.hasBeenClicked(i,j))
				k = DotButton.CLICKED_MINE;//10
			else
				k = DotButton.MINED;
		}
		else
		{
			k=gm.getNeighbooringMines(i,j);
		}
		return k;
	}


}
