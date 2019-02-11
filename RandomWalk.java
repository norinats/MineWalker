import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * RandomWalk sets up the the class and methods used in the RandomWalkDriver program
 * to create a random walk and map out the path.
 * @author Nori Natsuhara
 *
 */
public class RandomWalk implements RandomWalkInterface
{
	private int gridSize;
	private Random rand;
	private boolean done;
	private ArrayList<Point> path;
	private Point start;
	private Point end;
	private Point current;
	
	
	/**
	 * This sets up the variables used in the RandomWalk class only needing
	 * one parameter
	 * @param gridSize
	 */
	public RandomWalk(int gridSize)
	{
		this.gridSize = gridSize;
		rand = new Random();
		start = new Point(0,gridSize-1);
		end = new Point(gridSize-1,0);
		current = new Point(0,gridSize-1);
		path = new ArrayList<Point>();
		path.add(start);
		done = false;
	}
	
	/**
	 * This sets up the variables used in the RandomWalk class needing both
	 * the gridSize and seed parameters
	 * @param gridSize
	 * @param seed
	 */
	public RandomWalk(int gridSize, long seed)
	{
		this.gridSize = gridSize;
		rand = new Random(seed);
		start = new Point(0,gridSize-1);
		end = new Point(gridSize-1,0);
		current = new Point(0,gridSize-1);
		path = new ArrayList<Point>();
		path.add(start);
		done = false;
	}
	
	/**
	 *This creates a random step only allowing the step to be taken in the
	 *north or east direction and one point at a time.
	 */
	@Override
	public void step() {
		int north = rand.nextInt(2);
		if(current.x <(gridSize-1) && current.y > 0)
		{
			if(north == 1)
				current.setLocation(current.x, current.y-1);
			else
				current.setLocation(current.x+1, current.y);
			path.add(new Point(current));
		}
		else if(current.y > 0)
		{
			current.setLocation(current.x, current.y-1);
			path.add(new Point(current));
		}
		else if(current.x<(gridSize-1))
		{
			current.setLocation(current.x+1, current.y);
			path.add(new Point(current));
		}
		else
			done = true;
			
	}
	
	@Override
	public void stepEC() {
		// TODO
		
	}
	/**
	 * This takes all of the steps and creates a walk continuously creates
	 * a walk until the done variable becomes true as advertised in
	 * the step() method.
	 */
	@Override
	public void createWalk() {
		while(!done) {
			step();
		}
	}
	@Override
	public void createWalkEC() {
		//TODO
	}
	/**
	 * This returns whether or not the walk is finished
	 */
	@Override
	public boolean isDone() {
		return done;
	}
	/**
	 * this returns the size of the grid
	 */
	@Override
	public int getGridSize() {
		return gridSize;
	}
	/**
	 * this returns the start location of the point
	 */
	@Override
	public Point getStartPoint() {
		return start;
	}
	/**
	 * this returns the ending location of the point
	 */
	@Override
	public Point getEndPoint() {
		return end;
	}
	/**
	 * this returns the current location of the point
	 */
	@Override
	public Point getCurrentPoint() {
		return current;
	}
	/**
	 * this creates a copy of the ArrayList<Point> and returns the copied ArrayList
	 */
	@Override
	public ArrayList<Point> getPath() {
		ArrayList<Point> pointCopy = new ArrayList<Point>();
		for(Point p : path)
			pointCopy.add(p);
		return pointCopy;
	}
	
	/**
	 * this takes all of the points in the ArrayList and prints them out in a string
	 */
	public String toString()
	{
		String coor = "";
		for(Point p : path) {
			coor += "["+p.x+","+p.y+"] ";
		}
		return coor;
	}
	
	
	

}
