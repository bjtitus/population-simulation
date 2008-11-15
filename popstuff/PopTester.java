package popstuff;

import grid.*;
import java.io.*;
import java.util.*;

/**
 * <<Class summary>>
 *
 * @author Brandon Titus &lt;&gt;
 * @version $Rev$
 */
public class PopTester{
	
	//TODO: Possibly add more parameters for text file interpretation.
	
	private static ArrayList<Organism> someStuff = new ArrayList<Organism>();
	
    public static void main(String[] args){
		Grid<Structure> gr = new BoundedGrid<Structure>(40, 60);
		//parseFile(gr, "animalfile.txt");
		Random rand = new Random();		
		System.out.println((rand.nextGaussian()*10)+20);
        Plant fern = new Plant("Coconut Tree", 10, 15, gr);
		System.out.println(fern);
		Mammal leopard = new Mammal("Tiger", true, false, 20, 5, gr);
		System.out.println(leopard);
		
		for(int i=0; i<30; i++)
		{
			for(int j=0; j<someStuff.size(); j++)
			{
				if(someStuff.get(j) != null)
				{
					if(someStuff.get(j).getGrid() != null)
					{
						if(someStuff.get(j) instanceof Organism)
						{
							//someStuff.get(j).act();
							System.out.println("Is organism");
						}
						System.out.println(someStuff.get(j));
					}
				}
			}
			//System.out.println(fern);
			//System.out.println(leopard);
    	}
	}
	
	public static void parseFile(Grid gr, String fileName)
	{
		try{
			File file = new File(fileName);
			Scanner fileInput = new Scanner(file);
			int cnt = 0;
			while(fileInput.hasNext())
			{
				String token = fileInput.nextLine();
				System.out.println((++cnt) + ": " + token);
				processLine(gr, token);
			}
			fileInput.close();
		}
		catch (FileNotFoundException e)
		{
			System.err.println("ERROR: file not found: " + e.getMessage());
		}
	}
	
	public static void processLine(Grid gr, String line)
	{
		String[] stuff = line.split(" ");
		ArrayList<String> items = new ArrayList<String>(Arrays.asList(stuff));
		int num = someStuff.size();
		
		if(items.get(0).length() > 1)
		{
			if(items.get(0).equalsIgnoreCase("mam"))  //Mammal
			{
				Mammal name;
				if(items.size() > 4)
				{
					name = new Mammal(items.get(2), true, true, Integer.valueOf(items.get(3)), Integer.valueOf(items.get(4)), gr);
				}
				else
				{
					name = new Mammal(items.get(2), true, true, randomLocation(), randomLocation(), gr);
				}
				someStuff.add(name);
			}
			else if(items.get(0).equalsIgnoreCase("rep"))  //Reptile
			{
				Reptile thing;
				if(items.size() > 4)
				{
					thing = new Reptile(items.get(2), true, true, Integer.valueOf(items.get(3)), Integer.valueOf(items.get(4)), gr);
				}
				else
				{
					thing = new Reptile(items.get(2), true, true, randomLocation(), randomLocation(), gr);
				}
				someStuff.add(thing);
			}
			else if(items.get(0).equalsIgnoreCase("mar")) // Marsupial
			{
				Marsupial thing;
				if(items.size() > 4)
				{
					thing = new Marsupial(items.get(2), true, true, Integer.valueOf(items.get(3)), Integer.valueOf(items.get(4)), gr);
				}
				else
				{
					thing = new Marsupial(items.get(2), true, true, randomLocation(), randomLocation(), gr);
				}
				someStuff.add(thing);
			}
			else if(items.get(0).equalsIgnoreCase("bir")) // Bird
			{
				Bird thing;
				if(items.size() > 4)
				{
					thing = new Bird(items.get(2), true, true, Integer.valueOf(items.get(3)), Integer.valueOf(items.get(4)), gr);
				}
				else
				{
					thing = new Bird(items.get(2), true, true, randomLocation(), randomLocation(), gr);
				}
				someStuff.add(thing);
			}
			else if(items.get(0).equalsIgnoreCase("ins")) // Insect
			{
				Insect thing;
				if(items.size() > 4)
				{
					thing = new Insect(items.get(2), true, true, Integer.valueOf(items.get(3)), Integer.valueOf(items.get(4)), gr);
				}
				else
				{
					thing = new Insect(items.get(2), true, true, randomLocation(), randomLocation(), gr);
				}
				someStuff.add(thing);
			}
			else
			{
				System.out.println("ERROR: This line's type is incorrect.");
			}
			
			if(items.get(1).equalsIgnoreCase("H"))  //Herbivore
			{
				someStuff.get(num).eatsMeat(false);
				someStuff.get(num).eatsPlants(true);
			}
			else if(items.get(1).equalsIgnoreCase("C"))  //Carnivore
			{
				someStuff.get(num).eatsMeat(true);
				someStuff.get(num).eatsPlants(false);
			}
			else if(items.get(1).equalsIgnoreCase("O"))  //Omnivore
			{
				someStuff.get(num).eatsMeat(true);
				someStuff.get(num).eatsPlants(true);
			}
			else
			{
				System.out.println("ERROR: This line's type is incorrect.");
			}
		}
		else
		{
			if(items.get(0).equalsIgnoreCase("P"))  //Plant
			{
				Plant thing;
				if(items.size() > 4)
				{
					thing = new Plant(items.get(1), Integer.valueOf(items.get(1)), Integer.valueOf(items.get(2)), gr);
				}
				else
				{
					thing = new Plant(items.get(1), randomLocation(), randomLocation(), gr);
				}
			}
			else if(items.get(0).equalsIgnoreCase("T")) //Thing
			{

			}
			else
			{
				System.out.println("ERROR: This line's type is incorrect.");
			}
		}
	}
	public static int randomLocation()
	{
		int loc = (int)(Math.random()*10) + 1;
		System.out.println(loc);
		return loc;
	}
}
