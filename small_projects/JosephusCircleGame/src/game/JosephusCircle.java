package game;

/**
 * This class allows a client program to create circle doubly linked list with Josephus Nodes and manipulate this list
 * with adding, removing methods, and traverse through list.
 *
 * @author ruslan
 * @version 1.0
 */
public class JosephusCircle
{
	//fields go here...
	private JosephusNode head; //first element
	private JosephusNode tail; //keep track a last element
	private int size; //size of the circle
	private JosephusNode cursor;

	/**
	 * Creates a new JosephusCircle object with the maximum players given.
	 *
	 * @param playerCount the maximum number of players that can play in this circle
	 */
	public JosephusCircle(int playerCount)
	{
		//Creates a new JosephusCircle object with the maximum players given.
		this.size = playerCount;
	}

	/**
	 * Adds a new player to the game from left-to-right in the internal linked list.
	 *
	 * @param player the new player name
	 */
	public void addPlayer(String player)
	{
		//Adds a new player to the game from left-to-right in the 
		//internal linked list.
		if(head == null) {
			head = tail = cursor = new JosephusNode(player);
		}
		else {
			tail.setNext(new JosephusNode(player));
			tail.getNext().setPrev(tail);
			tail = tail.getNext();
			tail.setNext(head);
			head.setPrev(tail);
		}
	}

	/**
	 * Moves the current player (the cursor) from the current position the given number of steps to the left or right
	 *
	 * @param direction which direction to move the cursor
	 * @param steps how many steps to move the cursor
	 * @return the player the cursor ends up on after moving
	 */
	public String moveCursor(Direction direction, int steps)
	{
		//Moves the current player (the cursor) from the current 
		//position the given number of steps to the left or right
		JosephusNode current = cursor;
		int helper = 1;
		if(direction.equals(direction.RIGHT)) {
			while(helper <= steps) {
				current = current.getNext();
				helper++;
			}
			cursor = current;
			return current.getData();
		}
		else {
			while(helper <= steps) {
				current = current.getPrev();
				helper++;
			}
			cursor = current;
			return current.getData();
		}
	}

	/**
	 * Removes the player at the cursor position from the circle.
	 *
	 * @param direction moves the cursor one position to the left or right depending on the direction
	 */
	public void removeAtCursor(Direction direction)
	{
		//Removes the player at the cursor position from the circle.
		if(direction.equals(direction.LEFT)) {
			JosephusNode prevNode = cursor.getPrev();
			cursor.getNext().setPrev(cursor.getPrev());
			cursor.getPrev().setNext(cursor.getNext());
			cursor = prevNode;
		}
		else {
			JosephusNode nextNode = cursor.getNext();
			cursor.getNext().setPrev(cursor.getPrev());
			cursor.getPrev().setNext(cursor.getNext());
			cursor = nextNode;
		}
		size--;
	}

	/**
	 * Returns a string representing the internal state of the circle, with the following format:
	 * <-- Player A <--> Player B <--> Player C <--> Player D -->
	 *
	 * @return a string representation of the circle of players
	 */
	public String getCircle()
	{
		//Returns a string representing the internal state of the circle, 
		//with the following format:
		//<-- Player A <--> Player B <--> Player C <--> Player D -->
		JosephusNode current = head;
		String result = "<-- ";
		if(head != null) {
			do {
				result += current.getData() + " <--> ";
				current = current.getNext();
			} while (current != head);
		}
		return result;
	}

	/**
	 * Returns true if the game is over. That is, this method will return true if only one player remains in the circle.
	 *
	 * @return true if game over, otherwise false
	 */
	public boolean isGameOver()
	{
		//Returns true if the game is over. That is, this method will 
		//return true if only one player remains in the circle.
		if(size == 1)
			return true;
		else
			return false;
	}
}
