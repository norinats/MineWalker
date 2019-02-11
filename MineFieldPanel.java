import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *  Creates the button grid from the MineFieldButton class for playing the game and determines where the mines are based on a 
 *  randomly generated path
 * @author norinatsuhara
 *
 */
public class MineFieldPanel extends JPanel
{
	private static final int DEFAULT_DELAY = 100;
	private static final int PULSE_STEPS = 25;  
	
		public final int DEFAULT_SIZE = 10;
        private MineFieldButton[][] field, winPath;
        private MineFieldButton win, currButton;
        private MineFieldButton[][] mines;
        private RandomWalk path;
        private ArrayList<Point> points;
        private Random rand;
        private int pointCount, buttonCount;
        private double mineCount;
        private boolean adjacent, timer;
        
        private Color startColor;
    	private Color endColor;
    	private int delay;
    	private int currStep;
    	private int delta;



        /**
         * Creates the button grid that uses a random walk to create a solution to the game and randomly places mines around the
         * board that are not on the path.
         * @param listener
         * @param size
         */
        public MineFieldPanel(ActionListener listener, int size) {
            //makes sure that if the player tries to make the size too small the game resets to the Default size   
        	if(size < 5) {
                        this.setLayout(new GridLayout(DEFAULT_SIZE,DEFAULT_SIZE));

                        path = new RandomWalk(DEFAULT_SIZE);
                        path.createWalk();
                        points = path.getPath();


                        field = new MineFieldButton[DEFAULT_SIZE][DEFAULT_SIZE];
                        winPath = new MineFieldButton[DEFAULT_SIZE][DEFAULT_SIZE];
                        for(int row = 0; row < field.length; row++) {
                                for(int col = 0; col < field[row].length; col ++) {
                                        field[row][col] = new MineFieldButton();
                                        field[row][col].addActionListener(listener);
                                       // field[row][col].setEnabled(false);
                                        for(Point p: points) {
                                                if(row == p.x && col == p.y) {
                                                        winPath[row][col] = field[row][col];
                                                        win = winPath[row][col];
                                                        win.path();
                                                }
                                        }
                                        this.add(field[row][col]);
                                }
                        }
                        
                        field[0][field[0].length-1].setBackground(Color.MAGENTA);
                        field[field.length-1][0].setBackground(Color.CYAN);
                        //field[field.length-1][0].setEnabled(true);
                        currButton = field[field.length-1][0];

                        buttonCount = (DEFAULT_SIZE)*(DEFAULT_SIZE) - points.size();
                        mineCount = Math.ceil((buttonCount * 25) / 100);
                        mines = new MineFieldButton[DEFAULT_SIZE][DEFAULT_SIZE];
                        rand = new Random();
                        int i = 0;
                        while(i < mineCount) {
                                int row = rand.nextInt(DEFAULT_SIZE);
                                int col = rand.nextInt(DEFAULT_SIZE);
                                if(field[row][col].checkMine() == false && field[row][col].checkPath() == false) {
                                        mines[row][col] = field[row][col];
                                        field[row][col].mine();
                                        i ++;
                                }
                        }

                }
        	// allows the user to play the game from a 5x5 board to a 16x16 board
                else if(size >= 5 && size <= 16) {
                        this.setLayout(new GridLayout(size,size));

                        path = new RandomWalk(size);
                        path.createWalk();
                        points = path.getPath();


                        field = new MineFieldButton[size][size];
                        winPath = new MineFieldButton[size][size];
                        for(int row = 0; row < field.length; row++) {
                                for(int col = 0; col < field[row].length; col ++) {
                                        field[row][col] = new MineFieldButton();
                                        field[row][col].addActionListener(listener);
                                       // field[row][col].setEnabled(false);
                                        for(Point p: points) {
                                                if(row == p.x && col == p.y) {
                                                        winPath[row][col] = field[row][col];
                                                        win = winPath[row][col];
                                                        win.path();
                                                }
                                        }
                                        this.add(field[row][col]);
                                }
                        }
                        
                        field[0][field[0].length-1].setBackground(Color.MAGENTA);
                        field[field.length-1][0].setBackground(Color.CYAN);
                        //field[field.length-1][0].setEnabled(true);
                        currButton = field[field.length-1][0];

                        buttonCount = (size)*(size) - points.size();
                        mineCount = Math.ceil((buttonCount * 25) / 100);
                        mines = new MineFieldButton[size][size];
                        rand = new Random();
                        int i = 0;
                        while(i < mineCount) {
                                int row = rand.nextInt(size);
                                int col = rand.nextInt(size);
                                if(field[row][col].checkMine() == false && field[row][col].checkPath() == false) {
                                        mines[row][col] = field[row][col];
                                        mines[row][col].mine();
                                        i ++;
                                }
                        }

                       // this.nearbyMines(listener);
                }
        	// makes sure that if the user makes the grid too big that the grid will go to its default size
                else {
                        this.setLayout(new GridLayout(DEFAULT_SIZE,DEFAULT_SIZE));

                        path = new RandomWalk(DEFAULT_SIZE);
                        path.createWalk();
                        points = path.getPath();


                        field = new MineFieldButton[DEFAULT_SIZE][DEFAULT_SIZE];
                        winPath = new MineFieldButton[DEFAULT_SIZE][DEFAULT_SIZE];
                        for(int row = 0; row < field.length; row++) {
                                for(int col = 0; col < field[row].length; col ++) {
                                        field[row][col] = new MineFieldButton();
                                        field[row][col].addActionListener(listener);
                                      //  field[row][col].setEnabled(false);
                                        for(Point p: points) {
                                                if(row == p.x && col == p.y) {
                                                        winPath[row][col] = field[row][col];
                                                        win = winPath[row][col];
                                                        win.path();
                                                }
                                        }
                                        this.add(field[row][col]);
                                }
                        }
                        
                        field[0][field[0].length-1].setBackground(Color.MAGENTA);
                        field[field.length-1][0].setBackground(Color.CYAN);
                        //field[field.length-1][0].setEnabled(true);
                        currButton = field[field.length-1][0];

                        buttonCount = (DEFAULT_SIZE)*(DEFAULT_SIZE) - points.size();
                        mineCount = Math.ceil((buttonCount * 25) / 100);
                        mines = new MineFieldButton[DEFAULT_SIZE][DEFAULT_SIZE];
                        rand = new Random();
                        int i = 0;
                        while(i < mineCount) {
                                int row = rand.nextInt(DEFAULT_SIZE);
                                int col = rand.nextInt(DEFAULT_SIZE);
                                if(field[row][col].checkMine() == false && field[row][col].checkPath() == false) {
                                        mines[row][col] = field[row][col];
                                        field[row][col].mine();
                                        i ++;
                                }
                        }
                }
        }

       /**
        * resets all the buttons in the game back to white
        */
        public void reset() {
                for(int row = 0; row < field.length; row ++) {
                        for(int col = 0; col < field[row].length; col++) {
                                field[row][col].resetColor();
                        }
                }
        }

        /**
         * shows the randomly generated path in Blue
         */
        public void showPath() {
                for(int row = 0; row < field.length; row++) {
                        for(int col = 0; col < field[row].length; col ++) {
                                        if(field[row][col].checkPath() == true) {
                                                field[row][col].showPath();
                                        }
                        }
                }
                field[0][field[0].length-1].setBackground(Color.MAGENTA);
                field[field.length-1][0].setBackground(Color.CYAN); 
        }
        
        /**
         * hides the path and returns the buttons to white except start and destination
         */
        public void hidePath() {
                for(int row = 0; row < field.length; row++) {
                        for(int col = 0; col < field[row].length; col ++) {
                                        if(field[row][col].checkPath() == true) {
                                                field[row][col].hidePath();
                                        }
                        }
                }
                field[0][field[0].length-1].setBackground(Color.MAGENTA);
                field[field.length-1][0].setBackground(Color.CYAN);
        }

        /**
         * shows where the mines are by turning the buttons Black
         */
        public void showMines() {
                for(int row = 0; row < field.length; row++) {
                        for(int col = 0; col < field[row].length; col ++) {
                                        if(field[row][col].checkMine() == true) {
                                                field[row][col].showMine();
                                        }
                        }
                }
        }

        /**
         * hides the mines that are shown by turning them back to white
         */
        public void hideMines() {
                for(int row = 0; row < field.length; row++) {
                        for(int col = 0; col < field[row].length; col ++) {
                                        if(field[row][col].checkMine() == true) {
                                                field[row][col].hideMine();
                                        }
                        }
                }
        }
        
        /**
         * This checks all the buttons for nearby mines
         */
        public void nearbyMines() {
        	for(int row = 0; row < field.length; row++) {
                for(int col = 0; col < field[row].length; col ++) {
                	int nearby = 0;
                	//check for mine above
                	if((col)>0 && field[row][col-1].checkMine() == true)
                		nearby ++;
                	//check for mine below
                	if(col < (field[row].length-1) && field[row][col+1].checkMine() == true)
                		nearby ++;
                	//check for mine to the left
                	if(row > 0 && field[row-1][col].checkMine() == true)
                		nearby ++;
                	//check for mine to the right
                	if(row < (field.length-1) && field[row+1][col].checkMine() == true)
                		nearby ++;
                	//check if button is a mine
                	if(field[row][col].checkMine() == true)
                		nearby = 4;
                	
                	field[row][col].numMines(nearby);                	
                }
        	}
        }
        
        /**
         * Gives the destination point of the game
         */
        public void destinationReach() {
        	field[0][field[0].length-1].destination();
        }
        
        /**
         * updates the current button to the parameter
         * @param b
         * @return
         */
        public MineFieldButton updateCurrent(MineFieldButton b) {
        	currButton = b;
        	return currButton;
        }
        
        /**
         * checks the buttons surrounding the current button to see if they are adjacent
         */
        public void checkAdjacent() {
        	for(int row = 0; row < field.length; row++) {
                for(int col = 0; col < field[row].length; col ++) {
                	//check if button is adjacent to current button from above
                	if(col < (field[row].length -1) && currButton == field[row][col+1]) {
                		field[row][col].isAdjacent();
                	}
                	//check if button is adjacent to current button from below
                	if(col > 0 && currButton == field[row][col-1]) {
                		field[row][col].isAdjacent();
                	}
                	//check if button is adjacent to current button from left
                	if(row > 0 && currButton == field[row-1][col]) {
                		field[row][col].isAdjacent();
                	}
                	//check for mine to the right
                	if(row < (field.length - 1) && currButton == field[row+1][col]) {
                		field[row][col].isAdjacent();
                	}
                	
                }
        	}
        }
        /**
         * creates the time interval and color to toggle between for the timer
         * @author norinatsuhara
         *
         */
        private class TimerListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				currStep += delta;
				if (currStep < 0)
				{
					delta = 1;
					currStep = 0;
				}
				if (currStep >= PULSE_STEPS)
				{
					delta = -1;
					currStep = PULSE_STEPS-1;
				}
				
				float ratio = (float)currStep/PULSE_STEPS;
				int rPart = startColor.getRed() + (int)(ratio*(endColor.getRed()-startColor.getRed()));
				int gPart = startColor.getGreen() + (int)(ratio*(endColor.getGreen()-startColor.getGreen()));
				int bPart = startColor.getBlue() + (int)(ratio*(endColor.getBlue()-startColor.getBlue()));
				
				//((MineFieldButton)arg0.getSource())
				currButton.setBackground(new Color(rPart, gPart, bPart));
			}
        }
        
        /**
         * creates the timer to start the animation and toggle between colors
         */
        public void startAnimation() {
        	nearbyMines();
        	startColor = currButton.currColor();
    		endColor = currButton.currColor().darker().darker();
    		delay = DEFAULT_DELAY;
    		currStep = 0;
    		delta = 1;
    		
        	TimerListener taskPerformer = new TimerListener();
        	new Timer(DEFAULT_DELAY, taskPerformer).start();
        }  
       
}
