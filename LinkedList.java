import java.util.Collection;
import java.util.List;

class LinkedList<T> {
    
    // Node class inside of linked list class to use the generic T type and prevent the node class from being accessible from outside the linked list class 
    private class Node {
        T value = null;
        Node next = null;

        Node() {}

        Node(T value) {
            this.value = value;
        }

        Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
        
        protected T getValue() {
            return value;
        }

        protected void setValue(T value) {
            this.value = value;
        }

        protected Node getNext() {
            return next;
        }

        protected void setNext(Node next) {
            this.next = next;
        }
    }

    private Node head = null;

    LinkedList() {}

    public void of(Collection<T> values) {
        values.forEach(value -> this.add(value));
    }

    public void add(T value) {
        Node node = new Node(value);
        Node pointerToHead = this.head; // make a copy of the head node because we want to keep the head node always pointing to the begining of the linked list
        // use pointerToHead (copy of the head pointer) for looping through the linked list in this way the value of head pointer will not be changed and we will
        // also be able to loop through the linked list (because pointerToHead pointer has a different memory location than head pointer but they both point to the begining of the linked list) 
        
        if (pointerToHead == null) {
            this.head = node; // if pointerToHead is null set head Node to the new Node and not pointerToHead to the new node because head node marks the begining of the linked list
            // pointerToHead is a copy of head and they both have different memory locations and pointerToHead is only for traversing through the linked list 
        }
        else {
            while (pointerToHead != null && pointerToHead.next != null) {
                pointerToHead = pointerToHead.getNext();
            }
            pointerToHead.setNext(node); // it is important to set pointerToHead.next to new node and not pointerToHead to new node because .next is the property which maintains the link
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node element = this.head;

        sb.append("[");
        while(element != null) {
            sb.append(element.getValue());
            sb.append("->");
            element = element.getNext();
        }
        sb.append("]");

        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        Collection<Integer> list = List.of(4,5,6);
        linkedList.of(list);

        System.out.println(linkedList);
    }
}