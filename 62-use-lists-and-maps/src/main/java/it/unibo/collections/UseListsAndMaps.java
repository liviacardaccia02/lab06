package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private static final int FIRST = 1000;
    private static final int LAST = 2000;
    private static final int ELEMS = 100_000;
    private static final int READ = 1000;
    private static final long AFRICA = 1_110_635_000L;
    private static final long AMERICAS = 972_005_000L;
    private static final long ASIA = 4_298_723_000L;
    private static final long EUROPE = 742_452_000L;
    private static final long OCEANIA = 38_304_000L;
    private static final long ANTARCTICA = 0L;




    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {

        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */

        ArrayList<Integer> arraylist = new ArrayList<>();

        for (int i = FIRST; i < LAST; i++) {
            arraylist.add(i);
        }

        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */

         LinkedList<Integer> linkedlist = new LinkedList<>(arraylist);

        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */

         final int tmp = arraylist.get(arraylist.size() - 1);
         arraylist.set(arraylist.size() - 1, arraylist.get(0));
         arraylist.set(0, tmp);

        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */

        for (Integer integer : arraylist) {
            if (integer != FIRST) { 
                System.out.print(integer.toString() + ", ");
            } else {
                System.out.println(integer.toString());
            }
        }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        long timearrlist = System.nanoTime();
        for (int i = 1; i < ELEMS; i++) {
            arraylist.add(0, i);
        }
        timearrlist = System.nanoTime() - timearrlist;
        var millis = TimeUnit.NANOSECONDS.toMillis(timearrlist);
        System.out.println(// NOPMD
            "Inserting "
                + ELEMS
                + " elements in an Array List took "
                + timearrlist
                + "ns ("
                + millis
                + "ms)"
        );

        long timelinklist = System.nanoTime();
        for (int i = 1; i < ELEMS; i++) {
            linkedlist.add(0, i);
        }
        timelinklist = System.nanoTime() - timelinklist;
        millis = TimeUnit.NANOSECONDS.toMillis(timelinklist);
        System.out.println(// NOPMD
            "Inserting "
                + ELEMS
                + " elements in a Linked List took "
                + timelinklist
                + "ns ("
                + millis
                + "ms)"
        );

        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example PerfTest.java.
         */

        long timeread1 = System.nanoTime();
        for (int i = 0; i < READ; i++) {
            arraylist.get(arraylist.size() / 2);
        }
        timeread1 = System.nanoTime() - timeread1;
        millis = TimeUnit.NANOSECONDS.toMillis(timeread1);
        System.out.println("Reading " + READ 
                         + " times an element in the middle of the collection "
                         + "took " + timeread1
                         + " ns (" + millis + "ms)");

        long timeread2 = System.nanoTime();
        for (int i = 0; i < READ; i++) {
             linkedlist.get(linkedlist.size() / 2);
        }
        timeread2 = System.nanoTime() - timeread2;
        millis = TimeUnit.NANOSECONDS.toMillis(timeread2);
        System.out.println("Reading " + READ 
                         + " times an element in the middle of the collection "
                         + "took " + timeread2
                         + " ns (" + millis + "ms)");


        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        
        final Map<String, Long> world = new HashMap<>();
        world.put("Africa",AFRICA);
        world.put("Americas", AMERICAS);
        world.put("Antartica", ANTARCTICA);
        world.put("Asia", ASIA);
        world.put("Europe", EUROPE);
        world.put("Oceania", OCEANIA);
        
        /*
         * 8) Compute the population of the world
         */
        long totalpeople = 0L;
        for (final long inhabitants : world.values()) {
            totalpeople += inhabitants; 
        }
        System.out.println("There are " + totalpeople + " inhabitants in the world.");
    }
}