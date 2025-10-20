

import java.util.Collection;
import java.util.List;

public class LinkedListTest {
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
