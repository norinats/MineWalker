import java.awt.Color;

import javax.swing.JButton;

/**
 * this creates buttons to be used in the mine field panel
 * @author norinatsuhara
 *
 */
public class MineFieldButton extends JButton
{
        private final Color[] COLORS = {Color.WHITE, Color.GREEN, Color.YELLOW,
                        Color.ORANGE, Color.RED, Color.BLACK, Color.CYAN, Color.MAGENTA};
        private int colorIndex;
        private boolean path, adjacent, currButton;
        private boolean mine, destination;
        private Color currColor;

        /**
         * Creates mine field buttons
         */
        public MineFieldButton() {
                colorIndex = 0;
                setBackground(COLORS[colorIndex]);
                
                currColor = COLORS[0];

                adjacent = false;
                currButton = false;
                path = false;
                mine = false;
                destination = false;
        }

       /**
        * resets the color of the button back to white
        */
        public void resetColor() {
                colorIndex = 0;
                setBackground(COLORS[0]);
        }

       /**
        * changes a button to be on the path
        */
        public void path() {
                path = true;
        }

       /**
        * checks to see whether or not a button is on the path
        * @return
        */
        public boolean checkPath() {
                return path;
        }

        /**
         * changes the color of the buttons that are on the path
         */
        public void showPath() {
                setBackground(Color.BLUE);
        }

       /**
        * returns the color of the buttons on the path back to normal
        */
        public void hidePath() {
                setBackground(COLORS[0]);
        }

       /**
        * checks to see whether or not a button is a mine
        * @return
        */
        public boolean checkMine() {
                return mine;
        }

       /**
        * changes a button to be a mine
        */
        public void mine() {
                mine = true;
        }

       /**
        * changes the color of a button if it is a mine
        */
        public void showMine() {
                colorIndex = 5;
                setBackground(COLORS[colorIndex]);
        }

        /**
         * returns the color of the buttons that are mines back to normal
         */
        public void hideMine() {
                setBackground(COLORS[0]);
        }
        
        /**
         * returns the current color of the button
         * @return
         */
        public Color currColor() {
        	return currColor;
        }
        
        /**
         * updates the current color depending on the parameter entered
         * @param index
         */
        public void numMines(int index) {
        	if(index == 0)
        		currColor = COLORS[1];
        	else if(index == 1)
        		currColor = COLORS[2];
        	else if(index == 2)
        		currColor = COLORS[3];
        	else if(index == 3)
        		currColor = COLORS[4];
        	else if(index == 4)
        		currColor = COLORS[5];
        }
        
        /**
         * updates the color of the button to the current color
         */
        public void updateColor() {
        	setBackground(currColor);
        }
        
        /**
         * sets the button destination to be true
         */
        public void destination() {
        	destination = true;
        }
        
        /**
         * returns whether or not the button is the destination
         * @return
         */
        public boolean destinationReached() {
        	return destination;
        }
        
        /**
         * makes the button adjacency true
         */
        public void isAdjacent() {
        	adjacent = true;
        }
        
        /**
         * returns whether or not the button is adjacent
         * @return
         */
        public boolean adjacent() {
        	return adjacent;
        }
        
        /**
         * updates the button to be the current button
         */
        public void updateCurrButton() {
        	currButton = true;
        }
        
        /**
         * returns whether or not the button is the current button
         * @return
         */
        public boolean currButton() {
        	return currButton;
        }
}

