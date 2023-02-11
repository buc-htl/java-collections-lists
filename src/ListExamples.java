import java.util.*;

/* This class shows a lot of methods of the interface list and its implementations.
 * Don't worry, you won't need all of them immediately.
 */
public class ListExamples {

    @SuppressWarnings("all")
    public void test() {

        /* ArrayList, LinkedList, Vector and Stack (a subclass of Vector) all implement the List interface */
        //use a list implementation of your choice
        List<String> list = new ArrayList<>(20); // initialCapacity (default is 10)
        //List<String> list = new LinkedList<>();
        //List<String> list = new Vector<>();
        //List<String> list = new Stack<>();

        //add elements
        list.add("one");
        list.add("two");
        list.add("three");

        System.out.println(list);  // [one, two, three]

        System.out.println("Size: "+list.size());  // 3

        //Access items by index
        System.out.println(list.get(1));         // two
        System.out.println(list.get(2));         // three

        System.out.println(list.indexOf("two"));  // 1

        list.remove(1);     // removes two
        list.remove("one");    // removes one
        System.out.println(list.add("three"));   // true - duplicates alowed
        System.out.println(list);                // [three, three]

        System.out.println(list.isEmpty());      // false
        list.clear();
        System.out.println(list.isEmpty());      // true

        list.add("10");
        list.add("20");
        list.add("30");

        //iterate over all elements using a for-loop
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i)+" "); // 10 20 30
        }
        System.out.println();

        //generate a list form an array
        List<String> v2 = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));

        list.addAll(v2);
        System.out.println(list);   // [10, 20, 30, A, B, C, D]

        System.out.println(list.contains("A")); // true

        List<String> v3 = new ArrayList<>();
        v3.add("20");
        v3.add("C");

        System.out.println(list.containsAll(v3)); // true

        list.removeAll(v3);
        System.out.println(list);              // [10, 30, A, B, D]

        v2.remove("C");
        System.out.println(v2);                //v2: [A, B, D]

        list.retainAll(v2);
        System.out.println(list);              // [A, B, D]

        list.add(1, "X");
        System.out.println(list);              // [A, X, B, D]

        list.addAll(2, v3);
        System.out.println(list);              // [A, X, 20, C, B, D]

        String o = list.set(1, "Y");    //replaces the item at index 1 with "y" and returns the old item
        System.out.println(o);                 // X
        System.out.println(list);              // [A, Y, 20, C, B, D]

        /* ======================== Iteration ========================= */
        System.out.println("*");
        for (String s : list) {
            System.out.print(s + " ");               // A Y 20 C B D
        }

        System.out.println("\n-");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");       // A Y 20 C B D
        }

        System.out.println("\n-");
        Object[] items = list.toArray();
        for (Object obj : items) {
            System.out.print(obj + " ");               // A Y 20 C B D
        }

        System.out.println("\n--");
        for (int i = 0; i < items.length; i++) {
            System.out.print(items[i] + " ");          // A Y 20 C B D
        }

        System.out.println("\n--");
        ArrayList<String> listOfStrings = new ArrayList<String>(Arrays.asList("x", "y", "z"));
        //String[] strings = (String[])listOfStrings.toArray();              // ClassCastException
        String[] strings = (String[]) listOfStrings.toArray(new String[0]); // with type info
        for (String s : strings) {
            System.out.print(s + " ");
        }


        //You need to use an Iterator if you want to remove items from a collection while iterating over it
        System.out.println("\n--");
        for (Iterator it = list.iterator(); it.hasNext(); /**/) {
            Object o2 = it.next();
            System.out.print(o2 + " ");                // A Y 20 C B D
            if (o2 instanceof String && ((String) o2).equals("B"))
                it.remove();   // remove 20
        }
        System.out.println("\n"+list);

        System.out.println("\n---");
        for (ListIterator it = list.listIterator(); it.hasNext(); /**/) {
            System.out.print(it.next() + " ");         // A Y C B D
        }

        System.out.println("\n---");
        for (ListIterator it = list.listIterator(list.indexOf("C")); it.hasNext(); /**/) {
            System.out.print(it.next() + " ");         // C B D
        }

        /* ==================== Iteration inverted ==================== */

        System.out.println("\n---");
        for (ListIterator it = list.listIterator(list.indexOf("C")); it.hasPrevious(); /**/) {
            System.out.print(it.previous() + " ");     // Y A
        }

        System.out.println("\n---");
        for (ListIterator it = list.listIterator(list.size()); it.hasPrevious(); /**/) { // no mistake
            System.out.print(it.previous() + " ");     // D B C Y A
        }

        System.out.println("\n---");
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.print(list.get(i) + " ");      // D B C Y A
        }

        System.out.println("\n----");

        if (list instanceof Vector) {
            methodsSpecificToVectorAndStack((Vector) list);
        }

        if (list instanceof Stack) {
            methodsSpecificToStack((Stack) list);
        }



        /* ============================================================= */
        /* Sorting elements in a Stack, Vector, ArrayList and LinkedList */
        /* ============================================================= */
        list.add("Evi");
        list.add("EVI");
        list.add("evi");
        System.out.println(list);              // [A, Y, C, B, D, Evi, EVI, evi]
        Collections.sort(list, new MyStringComparator(true)); // descending
        System.out.println(list);              // [A, B, C, D, evi, Evi, EVI, Y]
    }


    public void methodsSpecificToVectorAndStack(Vector v) {
        /* ============================================================= */
        /* "Element"-methods are valid only for Vector and Stack         */
        /* comment them for other collections                            */
        /* ============================================================= */
        for (Enumeration en = v.elements(); en.hasMoreElements(); /**/) {
            System.out.print(en.nextElement() + " "); // A Y C B D
        }
        System.out.println();

        System.out.println(v.firstElement()); // A
        System.out.println(v.lastElement());  // D
    }


    private void methodsSpecificToStack(Stack<String> list) {
        /* ============================================================= */
        /* Stack methods - comment them for other collections            */
        /* ============================================================= */
        list.push("TOP");
        System.out.println(list);             // [A, Y, C, B, D, TOP]
        System.out.println(list.peek());      // TOP
        System.out.println(list);             // [A, Y, C, B, D, TOP]

        String o3 = list.pop();
        System.out.println(o3);               // TOP
        System.out.println(list);              // [A, Y, C, B, D]

        System.out.println(list.search("B")); // 2
    }


    /* ============================================================================ */
    /* A inner class implementing the Comparator interface to sort Strings by case  */
    /* ============================================================================ */
    class MyStringComparator implements Comparator<String> {

        private boolean descending = true;

        public MyStringComparator(boolean direction) {
            descending = direction;
        }

        @Override
        public int compare(String s1, String s2) {
            if (s1.compareToIgnoreCase(s2) == 0)
                return (descending) ? s2.compareTo(s1) : s1.compareTo(s2);
            else
                return (descending) ? s1.compareToIgnoreCase(s2) : s2.compareToIgnoreCase(s1);
        }
    }


    public static void main(String[] args) {
        new ListExamples().test();
    }
}

