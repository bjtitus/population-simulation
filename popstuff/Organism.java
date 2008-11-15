package popstuff;

import grid.*;
import java.util.Random;
import java.util.ArrayList;
import java.awt.*;

/**
 * <<Class summary>>
 *
 * @author Brandon Titus &lt;&gt;
 * @version $Rev$
 */

public class Organism extends Structure{
	
	//TODO: Organize Animals better.
	
	private String name;
	private double speed, size, life, maxTemp, minTemp;
	private boolean meat, plants;
	private Image image;
   
    public void Organism(double speed, double size, boolean meat, boolean plants, double life, Image image) {
		this.speed = speed;
		this.size = size;
		this.meat = meat;
		this.plants = plants;
		this.life = life;
		this.image = image;
    }

	//Accessors
	
	/**
	 * Gets the speed of this organism.
	 * @return the speed of this organism.
	 */
	public double getSpeed()
	{
		return speed;
	}
	/**
	 * Gets the size of this organism.
	 * @return the size of this organism.
	 */
	public double getSize()
	{
		return size;
	}
	/**
	 * Gets the boolean value of <code>meat</code> for this organism.
	 * @return the boolean value of <code>meat</code>.
	 */
	public boolean eatsMeat()
	{
		return meat;
	}
	/**
	 * Gets the boolean value of <code>plants</code> for this organism.
	 * @return the boolean value of <code>plants</code>.
	 */
	public boolean eatsPlants()
	{
		return plants;
	}
	/**
	 * Gets the current life value of this organism.
	 * @return the life value for this organism.
	 */
	public double getLife()
	{
		return life;
	}
	/**
	 * Gets the current location of this organism.
	 * @return the current location of this organism.
	 */
	
	/**
	 * Gets the name of this organism.
	 * @return the name of this organism.
	 */
	public String getName()
	{
		return name;
	}
	
	//Mutators
	
	/**
	 * Sets the speed of the organism.
	 * @param speed the speed value for this organism.
	 */
	public void setSpeed(double speed)
	{
		this.speed = speed;
	}
	/**
	 * Sets the size of the organism.
	 * @param size the size of the organism.
	 */
	public void setSize(double size)
	{
		this.size = size;
	}
	/**
	 * Sets whether this organism can eat meat.
	 * @param meat the boolean value for whether the organism can eat meat.
	 */
	public void eatsMeat(boolean meat)
	{
		this.meat = meat;
	}
	/**
	 * Sets whether this organism can eat plants.
	 * @param plants the boolean value for whether the organism can eat plants.
	 */
	public void eatsPlants(boolean plants)
	{
		this.plants = plants;
	}
	/**
	 * Sets the life value of this organism.
	 * @param life the new life value.
	 */
	public void setLife(double life)
	{
		this.life = life;
	}
	/**
	 * Sets the maximum and minimum temperature values of this organism.
	 * @param minTemp the minimum temperature value.
	 * @param maxTemp the maximum temperature value.
	 */
	public void setTemp(double minTemp, double maxTemp)
	{
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
	}
	/**
	 * Sets the name of this organism.
	 * @param name the string value of the organism's name.
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setImage(Image image)
	{
		this.image = image;
	}

	// Function Methods

	/**
	 * The basic Act method for every organism.
	 */
	public void act()
	{
		
	}
	
	/**
	 * Looks at surrounding organisms and removes them from the grid if this mammal can eat them.
	 * @param organisms the list of organisms surrounding the mammal.
	*/
	public void processOrganisms(ArrayList<Organism> organisms)
    {
        for (Organism a : organisms)
        {
            if (this.getSize() > a.getSize())
			{
				if(this.meat && (a instanceof Mammal))
				{ a.removeSelfFromGrid();
				  this.life = this.life + a.getSize()/4;
				}
				if(this.plants && (a instanceof Plant))
				{ a.removeSelfFromGrid();
				  this.life = this.life + a.getSize()/3;
				}
			}
        }
    }
	
	public int getDistNumber(int factor)
	{
		Random rand = new Random();
		int finalVal = 0;
		while(finalVal <= 0)
		{
			finalVal = (int)(rand.nextGaussian()*10)+factor;
		}
		return finalVal;
	}
	public int getRandDirection()
	{
		Random rand = new Random();
		double thing= rand.nextDouble();
		if(thing <= 0.20)
		{
			return Location.NORTH;
		}
		else if(thing <= 0.40)
		{
			return Location.SOUTH;
		}
		else if(thing <= 0.60)
		{
			return Location.EAST;
		}
		else if(thing <= 1)
		{
			return Location.WEST;
		}
		else
		{
			return getDirection();
		}
	}
	
	public String toString()
	{
		return getClass().getName() + ": " + name + " [location=" + getLocation() + ",direction="
                + getDirection() + ",temps=" + minTemp + "," + maxTemp + ",size=" + size +
 				",meat=" + meat + ",life=" + life + ",speed=" + speed + "]";
	}
	
	public Image getImage()
	{
		return image;
	}
}
