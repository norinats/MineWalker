import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * This runs the Mine Walker game to be played by users.
 * @author norinatsuhara
 *
 */
public class MineWalker {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Mine Walker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(950,800));
		
		frame.setContentPane(new MineWalkerPanel());
		
		frame.pack();
		frame.setVisible(true);

	}

}
