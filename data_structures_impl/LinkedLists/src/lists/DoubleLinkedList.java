package lists;

public class DoubleLinkedList {
    private Node head; //first element
    private Node tail; //keep tracking the last element

    //adding
    public void add(Object element) {
        //if list is empty
        if(head == null) {
            head = tail = new Node(element);
            head.previous = null;
        }

        //if list is not empty
        else {
            //creating new node
            Node newNode = new Node(element);
            //new node will be added after tail
            tail.next = newNode;
            //tail will be addressed to the previous node of new node
            newNode.previous = tail;
            tail = newNode;
        }
        tail.next = null;
    }

    //removing (didnt finish, confusing with some cases)
    public boolean remove(Object element) {
        //if list is empty
        if(head == null)
            return false;
        //if the searched element is first
        else if(head.data.equals(element)) {
            head.next.previous = null;
            head = head.next;
            return true;
        }
        else {
            Node current = head;
            while(current.next != null && current.next.data.equals(element)) {
                current = current.next;
            }
            if(current.next == null) {
                return false;
            }
            else {
                if(current.next == tail) {
                    tail = current;
                }
            }
        }
        return false;
    }

    //helper for future operations
    private Node nodeAt(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    public String toString() {
        Node current = head;
        String result = "";
        if(head == null) {
            return "[]";
        }
        else {
            while(current != null) {
                result += current.data + " <-> ";
                current = current.next;
            }
            return result;
        }
    }

    class Node {
        private Object data;
        private Node next;
        private Node previous;


        public Node(Object data) {
            this.data = data;
        }

        public Node(Object data, Node next, Node previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }

        public String toString() {
            return data.toString();
        }
    }
}
