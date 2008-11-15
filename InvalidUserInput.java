public class InvalidUserInput extends Exception
{
	public InvalidUserInput()
	{
		super("Invalid User Input Exception!");
	}
	
	public InvalidUserInput(String userInput)
	{
		super(userInput);
	}
}