package game;

//A doubly-linked list node. This node class is not an inner class,
//so you will need to use getters/setters to access the fields in 
//the class. They should not be public.

/**
 * This class allows the client program to create a Josephus Node object and connect all these objects
 * in doubly linked list through Next and Prev nodes.
 *
 * @author Ruslan Bessarab
 * @version 1.0
 */
public class JosephusNode
{
    private String data;
	private JosephusNode next;
	private JosephusNode prev;

    /**
     * Allows client program to construct JosephusNode object
     * @param data player name within it
     */
    public JosephusNode(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public JosephusNode getNext() {
        return next;
    }

    public void setNext(JosephusNode next) {
        this.next = next;
    }

    public JosephusNode getPrev() {
        return prev;
    }

    public void setPrev(JosephusNode prev) {
        this.prev = prev;
    }

    /**
     * Represent the data of the JosephusNode in string format
     * @return string representation of the Node
     */
    public String toString() {
        return data.toString();
    }
}
