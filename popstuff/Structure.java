package popstuff;

import grid.Grid;
import grid.Location;

import java.util.ArrayList;

public class Structure{

    private Grid<Structure> grid;
	private Location location;
	private int direction;

    public Structure() {
        direction = Location.NORTH;
		grid = null;
		location = null;
    }

	// Accessors

	/**
	 * Gets the current direction of this structure.
	 * @return the direction of this structure, an angle between 0 and 359 degrees.
	 */
	public int getDirection()
	{
		return direction;
	}
	/**
	 * Gets the grid of this structure.
	 * @return the grid of this structure.
	 */
	public Grid<Structure> getGrid()
	{
		return grid;
	}	
	/**
	 * Gets the location of this structure.
	 * @return the location of this structure.
	 */
	public Location getLocation()
	{
		return location;
	}
	
	// Mutators
	
	/**
	 * Sets the current direction of this structure.
	 * @param newDirection the new direction. The direction of this structure is set
	 * to the angle between 0 and 359 degrees that is equivalent to
	 * <code>newDirection</code>.
	 */
	public void setDirection(int newDirection)
    {
        direction = newDirection % Location.FULL_CIRCLE;
        if (direction < 0)
            direction += Location.FULL_CIRCLE;
    }

	// Function Methods

	/**
	 * Puts this structure into a grid. If there is another structure at the given
	 * location, it is removed. <br />
	 * Precondition: (1) This structure is not contained in a grid (2) <code>loc</code>
	 * is valid in <code>gr</code>
	 * @param gr the grid into which this structure should be placed
	 * @param loc the location into which the structure shold be placed
	 */
	public void putSelfInGrid(Grid<Structure> gr, Location loc)
    {
        if (grid != null)
            throw new IllegalStateException(
                    "This structure is already contained in a grid.");

        Structure structure = gr.get(loc);
        if (structure != null)
            structure.removeSelfFromGrid();
        gr.put(loc, this);
        grid = gr;
        location = loc;
    }

	/**
	 * Moves this structure to a new location. If there's another structure at
	 * the new location, that structure is removed. <br />
	 * Precondition: (1) This structure is contained in a grid
	 * (2) <code>newLocation</code> is valid in the grid of this structure
	 * @param newLocation the new location
	 */
	public void moveTo(Location newLocation)
    {
        if (grid == null)
            throw new IllegalStateException("This structure is not in a grid.");
        if (grid.get(location) != this)
            throw new IllegalStateException(
                    "The grid contains a different structure at location "
                            + location + ".");
        if (!grid.isValid(newLocation))
            throw new IllegalArgumentException("Location " + newLocation
                    + " is not valid.");

        if (newLocation.equals(location))
            return;
        grid.remove(location);
        Structure other = grid.get(newLocation);
        if (other != null)
            other.removeSelfFromGrid();
        location = newLocation;
        grid.put(location, this);
    }

	/**
	 * Removes this structure from its grid. <br />
	 * Precondition: This structure is contained in a grid
	 */
	public void removeSelfFromGrid()
    {
        if (grid == null)
            throw new IllegalStateException(
                    "This structure is not contained in a grid.");
        if (grid.get(location) != this)
            throw new IllegalStateException(
                    "The grid contains a different structure at location "
                            + location + ".");

        grid.remove(location);
        grid = null;
        location = null;
    }
	
	/**
	* Creates a string that describes an structure
	* @return String with the location, direction, min and max temps, size, meat value, life, and speed of this structure.
	*/
	public String toString()
	{
		return getClass().getName() + "[location=" + location + ",direction="
                + direction + "]";
	}

	public void add(Location loc, Structure occupant)
    {
        occupant.putSelfInGrid(getGrid(), loc);
    }
}
