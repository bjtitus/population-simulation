import java.awt.*;
import javax.swing.JApplet;
import java.awt.event.*;
import popstuff.*;
import grid.*;
import java.io.*;
import java.util.*;

public class AppletTester extends JApplet implements ActionListener , Runnable{
	
	static ArrayList<Organism> someStuff = new ArrayList<Organism>();
	Grid<Structure> gr = new BoundedGrid<Structure>(300, 400);
	Button testButton, anotherButton, playButton, updateButton;
	Checkbox animal1, animal2, animal3, animal4;
	ButtonPanel buttonPanel;
	private Graphics canvas;
	boolean continueT = false;
	private Thread t = null;
	
	public void run()
	{
		boolean continueTL = true;
		synchronized(this)
		{
			continueTL = continueT;
		}
		while(continueTL == true)
		{
			synchronized(this)
			{
				runSim();
			}
			repaint();
			try{
				Thread.sleep((150));
			}
			catch (Exception e) {};
			synchronized(this)
			{
				continueTL = continueT;
			}
		}
	}
	
	public void init()
	{
		//Menu Options
		setLayout(new FlowLayout());
		buttonPanel = new ButtonPanel();
		add(buttonPanel);
		testButton = new Button("Move");
		testButton.addActionListener(this);
		anotherButton = new Button("Pause");
		anotherButton.addActionListener(this);
		playButton = new Button("Play");
		playButton.addActionListener(this);
		updateButton = new Button("Update");
		updateButton.addActionListener(this);
		animal1 = new Checkbox("Tiger", false);
		animal2 = new Checkbox("Swan", false);
		animal3 = new Checkbox("Kangaroo", false);
		animal4 = new Checkbox("Beetle", false);
		
		buttonPanel.add(testButton);
		buttonPanel.add(playButton);
		buttonPanel.add(anotherButton);
		buttonPanel.add(animal1);
		buttonPanel.add(animal2);
		buttonPanel.add(animal3);
		buttonPanel.add(animal4);
		buttonPanel.add(updateButton);
		
		synchronized(this)
		{
		//parseFile(gr, "animalfile.txt");
		Random rand = new Random();		
		System.out.println((rand.nextGaussian()*10)+20);
        Plant fern = new Plant("Coconut Tree", randomLocation(), randomLocation(), gr);
Plant fern1 = new Plant("Coconut Tree", randomLocation(), randomLocation(), gr);
Plant fern2 = new Plant("Coconut Tree", randomLocation(), randomLocation(), gr);
Plant fern3 = new Plant("Coconut Tree", randomLocation(), randomLocation(), gr);
Plant fern4 = new Plant("Coconut Tree", randomLocation(), randomLocation(), gr);
		//fern.setImage(getImage(getDocumentBase(), "/images/dld.png", 20, 30));
		someStuff.add(fern);
		someStuff.add(fern1);
				someStuff.add(fern2);
						someStuff.add(fern3);
								someStuff.add(fern4);
		System.out.println(fern);
		repaint();
		}
	}
	
	class ButtonPanel extends Panel
	{
	    // constructor
	    ButtonPanel()
	    {
	        // set the background color for this panel
	        setBackground (new Color(180,255,0));
	    }
	}
	
	public void paint(Graphics can)
	{
		canvas = can;
		//final int iHeight = getHeight(this);
		//final int iWidth = image.getWidth(this)
				
		//canvas.drawImage(image, xPos, yPos, this);
		
		synchronized(this)
		{
		canvas.setColor(Color.WHITE);
		canvas.fillRect(0, 0, this.getSize().width, this.getSize().height);
		for(int j=0; j<someStuff.size(); j++)
		{
			if(someStuff.get(j) != null)
			{
				if(someStuff.get(j).getGrid() != null)
				{
					if(someStuff.get(j) instanceof Organism)
					{
						if(someStuff.get(j) instanceof Plant)
							canvas.setColor(Color.GREEN);
						else if(someStuff.get(j) instanceof Mammal)
							canvas.setColor(Color.RED);
						else if(someStuff.get(j) instanceof Marsupial)
							canvas.setColor(Color.ORANGE);
						else if(someStuff.get(j) instanceof Bird)
							canvas.setColor(Color.GRAY);
						else if(someStuff.get(j) instanceof Reptile)
							canvas.setColor(Color.YELLOW);
						else if(someStuff.get(j) instanceof Insect)
							canvas.setColor(Color.PINK);
						canvas.fillRect((this.getSize().width/2)-someStuff.get(j).getLocation().getRow(), (this.getSize().height/2)-someStuff.get(j).getLocation().getCol(), 3, 3);
					}
				}
			}
		}
		}
	}
	
	public void checkBoxes()
	{
			if(animal1.getState() == true)
			{
				Mammal tiger = new Mammal("Tiger", false, false, randomLocation(), randomLocation(), gr);
				tiger.setImage(getImage(getDocumentBase(), "images/images/dld_05.gif"));
				someStuff.add(tiger);
			}
			if(animal2.getState() == true)
			{
				Bird swan = new Bird("Swan", true, false, randomLocation(), randomLocation(), gr);
				swan.setDirection(Location.SOUTH);
				someStuff.add(swan);		
			}
			if(animal3.getState() == true)
			{
				Marsupial kangaroo = new Marsupial("Kangaroo", true, false, randomLocation(), randomLocation(), gr);
				kangaroo.setDirection(Location.EAST);
				someStuff.add(kangaroo);
			}
			if(animal4.getState() == true)
			{
				Insect beetle = new Insect("Beetle", true, false, randomLocation(), randomLocation(), gr);
				beetle.setDirection(Location.WEST);
				someStuff.add(beetle);
			}
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource() == testButton)
		{
			synchronized(this)
			{
				canvas.setColor(Color.WHITE);
				canvas.fillRect(0, this.getSize().width, this.getSize().width, this.getSize().height);
				runSim();
			}
			repaint();
		}
		else if(evt.getSource() == anotherButton)
		{
			synchronized(this)
			{
				continueT = false;
				//animal1.setState(true);
				//animal3.setState(true);
			}
			//super.update();
		}
		else if(evt.getSource() == playButton)
		{
			synchronized(this)
			{
				continueT = true;
				t = new Thread(this);
				t.start();
			}
		}
		else if(evt.getSource() == updateButton)
		{
			checkBoxes();
			repaint();
		}
		//evt.consume();
	}
	
	public static void runSim()
	{
		for(int j=0; j<someStuff.size(); j++)
		{
			if(someStuff.get(j) != null)
			{
				if(someStuff.get(j).getGrid() != null)
				{
					if(someStuff.get(j) instanceof Organism)
					{
						someStuff.get(j).act();
						System.out.println("Is organism");
					}
					System.out.println(someStuff.get(j));
				}
			}
		}
	}
	
	public static void parseFile(Grid gr, String fileName)
	{
		try{
			File file = new File(fileName);
			if(!file.exists())
			{
				throw new InvalidUserInput(file.toString());
			}	
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
		catch (InvalidUserInput e)
		{
			System.err.println("ERROR: invalid user input: " + e.getMessage());
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
		int loc = (int)(Math.random()*100) + 1;
		System.out.println(loc);
		return loc;
	}
}