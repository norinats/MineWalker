import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * This arranges the layout of the game that is seen and uses ActionListeners to make the buttons work and the game be able
 * to be played
 * @author norinatsuhara
 *
 */
public class MineWalkerPanel extends JPanel
{   
	
		private JPanel colorKey, scoreBoard, options;
        private JButton showMines, showPath, giveUp;
        private JTextField input;
        private JButton noMines, oneMine, twoMines, threeMines, exploded, start, destination;
        private JLabel lives, score;
        private MineFieldPanel mineField;
        private int pathShow, mineShow, progress, livesLeft, currScore, again;

        /**
         * This arranges the layout of the game that is seen.
         */
        public MineWalkerPanel() {
                this.setLayout(new BorderLayout());

                mineField = new MineFieldPanel(new MineWalkerListener(), 10);
                this.add(mineField, BorderLayout.CENTER);

                colorKey = new JPanel();
                colorKey.setLayout(new GridLayout(8,1));
                colorKey.setBorder(BorderFactory.createTitledBorder("Color Key"));
                noMines = new JButton("0 nearby mines");
                        noMines.setBackground(Color.GREEN);
                        colorKey.add(noMines);
                oneMine = new JButton("1 nearby mine");
                        oneMine.setBackground(Color.YELLOW);
                        colorKey.add(oneMine);
                twoMines = new JButton("2 nearby mines");
                        twoMines.setBackground(Color.ORANGE);
                        colorKey.add(twoMines);
                threeMines = new JButton("3 nearby mines");
                        threeMines.setBackground(Color.RED);
                        colorKey.add(threeMines);
                exploded = new JButton("exploded mines");
                        exploded.setBackground(Color.BLACK);
                        exploded.setForeground(Color.WHITE);
                        colorKey.add(exploded);
                start = new JButton("start");
                        start.setBackground(Color.CYAN);
                        colorKey.add(start);
                destination = new JButton("destination");
                        destination.setBackground(Color.MAGENTA);
                        colorKey.add(destination);
                this.add(colorKey, BorderLayout.WEST);

                scoreBoard = new JPanel();
                scoreBoard.setLayout(new GridLayout(2,1));
                scoreBoard.setPreferredSize(new Dimension(125,200));
                scoreBoard.setBorder(BorderFactory.createTitledBorder("Score Board"));
                livesLeft = 5;
                lives = new JLabel("Lives: "+livesLeft);
                        //lives.setVerticalAlignment(JLabel.BOTTOM);
                        scoreBoard.add(lives);
                currScore = 500;
                score = new JLabel("Score: "+currScore);
                        //lives.setVerticalAlignment(JLabel.TOP);
                        scoreBoard.add(score);
                this.add(scoreBoard, BorderLayout.EAST);

               //South border
                options = new JPanel();
                //show/hide mines
                showMines = new JButton("Show Mines");
                showMines.addActionListener(new ShowMineListener());
                //show/hide path
                showPath = new JButton("Show Path");
                showPath.addActionListener(new ShowPathListener());
                //give up/start new games
                giveUp = new JButton("Give Up?");
                giveUp.addActionListener(new ResetButtonListener());
                //change size of 
                input = new JTextField(4);
                input.setText("10");
                options.add(showMines);
                options.add(showPath);
                options.add(giveUp);
                options.add(input);
                this.add(options, BorderLayout.SOUTH);

        }

        /**
         * This updates the buttons in the mineField that are allowed to be clicked and changes the color of the buttons that are
         * clicked. This listener also keeps track of the score and whether or not the user has won the game.
         * @author norinatsuhara
         *
         */
        private class MineWalkerListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				//updates the color of each button
				MineFieldButton button = (MineFieldButton) e.getSource();
				
				mineField.nearbyMines();
				mineField.checkAdjacent();
				mineField.startAnimation();
				
				//updates the score if the buttons clicked are adjacent
				if(button.checkMine() == true && button.adjacent() == true) {
					button.updateColor();
					button.updateCurrButton();
					mineField.updateCurrent(button);
					livesLeft --;
					currScore = currScore - 100;
					lives.setText("Lives:" + livesLeft);
					score.setText("Score:" + currScore);
				}
				else if(button.adjacent() == true){
					button.updateColor();
					button.updateCurrButton();
					mineField.updateCurrent(button);
					currScore = currScore - 1;
					score.setText("Score:" + currScore);
				}
				
				
				
				//ends games and asks to start a new game when user runs out of lives
				if(livesLeft == 0) {
					do {
						//JOptionPane.showMessageDialog(null, "Out of Lives.");
						again = JOptionPane.showConfirmDialog(null, "Out of Lives. New Game?");
						livesLeft = 5;
                    	currScore = 500;
                    	lives.setText("Lives:" + livesLeft);
    					score.setText("Score:" + currScore);
						remove(mineField);
                        mineField = new MineFieldPanel(new MineWalkerListener(),10);
                        add(mineField, BorderLayout.CENTER);
                        updateUI();
					} while (again == JOptionPane.YES_OPTION);
				}
				
				//ends game when user wins and prompts user to start a new game
				mineField.destinationReach();
				if(((MineFieldButton) e.getSource()).destinationReached() == true) {
					do {
						//JOptionPane.showMessageDialog(null, "Out of Lives.");
						again = JOptionPane.showConfirmDialog(null, "Congratulations! You Won!"+
								"\n\nYou finished with a score of: "+currScore + "\n\nNew Game?");
						livesLeft = 5;
                    	currScore = 500;
                    	lives.setText("Lives:" + livesLeft);
    					score.setText("Score:" + currScore);
						remove(mineField);
                        mineField = new MineFieldPanel(new MineWalkerListener(),10);
                        add(mineField, BorderLayout.CENTER);
                        updateUI();
					} while (again == JOptionPane.YES_OPTION);
				}
			}
        }
        


        /**
         * This is an action listener for the reset button to show the mines and reset the game to any sized board.
         * the button switches from giving up to show the mines and restarting the game.
         * @author norinatsuhara
         *
         */
        private class ResetButtonListener implements ActionListener{

                @Override
                public void actionPerformed(ActionEvent e) {
                        progress ++;
                        if(progress % 3 == 1) {
                                mineField.showMines();
                                giveUp.setText("New Game");
                        }
                        else {
                        	livesLeft = 5;
                        	currScore = 500;
                        	lives.setText("Lives:" + livesLeft);
        					score.setText("Score:" + currScore);
                            giveUp.setText("Give Up?");
                            mineField.hideMines();
                            int size;
                            try {
                                  size = Integer.parseInt(input.getText());
                            }
                            catch(IllegalArgumentException ce) {
                                   return;
                            }

                            remove(mineField);
                            mineField = new MineFieldPanel(new MineWalkerListener(),size);
                            add(mineField, BorderLayout.CENTER);
                            updateUI();
                        }
                }
        }

       /**
        * This action listener allows the button to toggle between showing and hiding the path and perform the action
        * @author Nori Natsuhara
        *
        */
        private class ShowPathListener implements ActionListener{

                @Override
                public void actionPerformed(ActionEvent e) {

                        pathShow ++;
                        if(pathShow % 2 == 1) {
                                mineField.showPath();
                                showPath.setText("Hide Path");
                        }
                        else {
                                mineField.hidePath();
                                showPath.setText("Show Path");
                        }
                }
        }

        /**
         * this action listener toggles between showing and hiding the mine and performs the action
         * @author norinatsuhara
         *
         */
        private class ShowMineListener implements ActionListener{

                @Override
                public void actionPerformed(ActionEvent e) {
                        mineShow ++;
                        if(mineShow % 2 == 1) {
                                mineField.showMines();
                                showMines.setText("Hide Mines");
                        }
                        else {
                                mineField.hideMines();
                                showMines.setText("Show Mines");
                        }
                }
        }
        

}

