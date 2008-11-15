package popstuff;

import grid.*;
import java.util.*;

/**
 * <<Class summary>>
 *
 * @author Brandon Titus &lt;&gt;
 * @version $Rev$
 */
public class Plant extends Organism {

    //TODO: Implement more fleshed out SUN & energy exchange system.

    public Plant(String name, int x, int y, Grid<Structure> gr) {
		setName(name);
		double size = getDistNumber(10);
		setSize(size);
		setLife(200);
		setTemp(20.0, 50.0);
		Location loc = new Location(x, y);
		putSelfInGrid(gr, loc);
    }

	/**
	 * Checks to see if the plant is dead yet (<code>life</code> is 0) and removes from the grid if it is.
	 */
	public void act()
	{
		if(getLife() == 0)
		{
			removeSelfFromGrid();
		}
		else
		{
			setLife(getLife()-1);
			Random rand = new Random();
			if(rand.nextInt() > 0.5)			// primitive SUN implementation. Will flesh this out later.
			{
				setLife(getLife()+(1/3));
			}
		}
	}

}
