package exceptions;

/**
 * This class throws the GameException when the maximum number of players has already been reached
 */
public class GameException extends RuntimeException
{
	public GameException(String message)
	{
		super(message);
	}
	
	public GameException()
	{
		super("Error running game...");
	}
}