package popstuff;

import grid.*;
import java.util.*;

/**
 * <<Class summary>>
 *
 * @author Brandon Titus &lt;&gt;
 * @version $Rev$
 */
public class Reptile extends Organism{
    
    public Reptile(String name, boolean meat, boolean plants, int x, int y, Grid<Structure> gr) {
		setName(name);
		double size = getDistNumber(20);
        setSize(size);
		setSpeed(getDistNumber(25));
		eatsMeat(meat);
		eatsPlants(plants);
		setLife(30);
		setTemp(0, 0);
		Location loc = new Location(x, y);
		putSelfInGrid(gr, loc);
    }

	/**
	 * Checks to see if reptile is dead yet (<code>life</code> is 0) and removes from the grid if it is.
	 * If it isn't, the reptile is moved and <code>life</code> is decreased by 1.
	 */
	public void act()
	{
		if(getLife() == 0)
		{
			removeSelfFromGrid();
		}
		else
		{
			move();
			setLife(getLife()-1);
		}
	}

	public boolean attack()
	{
		return true;
	}
	
	/**
	 * Moves the mammal down by one.
	 */
	public void move()
    {
        Grid<Structure> gr = getGrid();
        if (gr == null)
            return;
        Location loc = super.getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next))
            moveTo(next);
        else
            removeSelfFromGrid();
    }
}
