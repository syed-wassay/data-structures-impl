

import java.util.Collection;
import java.util.List;

public class LinkedList<T> {

    // Node class inside of linked list class to use the generic T type and prevent
    // the node class from being accessible from outside the linked list class
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

    // head pointer for storing the starting point of the linked list
    private Node head = null;

    LinkedList() {
    }

    LinkedList(Collection<T> values) {
        this.addAll(values);
    }

    /*
     * method for adding a single value to the linked list
     * {params} T value
     * returns void (updates the linked list)
     */
    public void add(T value) {
        Node node = new Node(value);
        Node pointerToHead = this.head; // make a copy of the head node because we want to keep the head node always
                                        // pointing to the begining of the linked list
        // use pointerToHead (copy of the head pointer) for looping through the linked
        // list in this way the value of head pointer will not be changed and we will
        // also be able to loop through the linked list (because pointerToHead pointer
        // has a different memory location than head pointer but they both point to the
        // begining of the linked list)

        if (pointerToHead == null) {
            this.head = node; // if pointerToHead is null set head Node to the new Node and not pointerToHead
                              // to the new node because head node marks the begining of the linked list
            // pointerToHead is a copy of head and they both have different memory locations
            // and pointerToHead is only for traversing through the linked list
        } else {
            while (pointerToHead != null && pointerToHead.next != null) {
                pointerToHead = pointerToHead.getNext();
            }
            pointerToHead.setNext(node); // it is important to set pointerToHead.next to new node and not pointerToHead
                                         // to new node because .next is the property which maintains the link
        }
    }

    /*
     * method for adding multiple values to the linked list
     * {params} collection of values to be added
     * returns void (updates linked list)
     */
    public void addAll(Collection<T> values) {
        values.forEach(value -> this.add(value));
    }

    /*
     * remove method for the linked list
     * {params} takes a value to be removed from linked list
     * returns void (updates the linked list)
     */
    public void remove(T value) {
        // element pointer node to traverse through the linked list without changing the
        // value of head
        Node element = this.head;

        // if the value is the first value in the linked list then update the head to
        // point to the next node
        if (this.head.getValue() == value) {
            this.head.setValue(null);
            this.head = this.head.getNext();
        }
        // if the value is value is somewhere in between or in the end of the
        // linked list traverse the linked list using element node using 
        // element and element.next as two pointers
        // on every iteration check that both pointers are non null
        // compare the element.next value for the value match
        // if it is a match update the element.next to point to
        // element.next.next
        else {
            while (element != null && element.getNext() != null) {
                if (element.getNext().getValue() == value) {
                    element.getNext().setValue(null);
                    element.setNext(element.getNext().getNext());
                    break; // break through the loop when value is removed on first occurance
                }
                // if the value is not matched update the pointer
                element = element.getNext();
            }
        }
    }

    /*
     * method for removing all values for a given collection
     * {params} collection of values to be removed
     * returns void
     */
    public void removeAll(Collection<T> values) {
        values.forEach(value -> this.remove(value));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node element = this.head;

        sb.append("[");
        while (element != null) {
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

        Collection<Integer> c = List.of(1,4,5,6,3,7,8,9);

        linkedList.addAll(c);

        System.out.println(linkedList);
        
        linkedList.remove(2);

        System.out.println(linkedList);

        Collection<Integer> list = List.of(1, 3, 9, 6);
        linkedList.removeAll(list);

        System.out.println(linkedList);

    }
}