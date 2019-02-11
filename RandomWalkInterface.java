import java.awt.Point;
import java.util.ArrayList;

/**
 * Interface to define the methods needed for a RandomWalk class.
 * @author CS121 Instructors
 */
public interface RandomWalkInterface
{
	/**
	 * Takes a single step towards the destination and adds the new Point to the path.
	 * Steps North or East with equal probability.
	 *
	 * If the step is the final step, sets the value of the done to true.
	 */
	public void step();

	/**
	 * Takes a single step towards the destination and adds the new Point to the path.
	 * Steps North or East with 90% probability and South or West with 10% probability.
	 *
	 * If the step is the final step, sets the value of the done to true.
	 *
	 * EXTRA CREDIT! Only implement this method if you are attempting the extra credit.
	 */
	public void stepEC();

	/**
	 * Creates the entire walk in one call by internally using the step() method.
	 */
	public void createWalk();

	/**
	 * Creates the entire walk in one call by internally using the stepEC() method.
	 *
	 * EXTRA CREDIT! Only implement this method if you are attempting the extra credit.
	 */
	public void createWalkEC();

	/**
	 * Returns the current value of the done variable.
	 * @return true if the path has reached the destination, false otherwise.
	 */
	public boolean isDone();

	/**
	 * Returns the grid size of this walk.
	 * @return The size of the grid.
	 */
	public int getGridSize();

	/**
	 * Returns the starting point of this walk.
	 * @return the starting point.
	 */
	public Point getStartPoint();

	/**
	 * Returns the ending point of this walk.
	 * @return the ending point.
	 */
	public Point getEndPoint();

	/**
	 * Returns the current point of this walk.
	 * @return the current point.
	 */
	public Point getCurrentPoint();

	/**
	 * Returns the path this walk has taken.
	 * @return a copy of the path.
	 */
	public ArrayList<Point> getPath();

}
