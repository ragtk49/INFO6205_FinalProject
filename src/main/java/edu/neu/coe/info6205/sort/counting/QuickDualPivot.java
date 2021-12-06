package edu.neu.coe.info6205.sort.counting;

/******************************************************************************
 *  Compilation:  javac QuickDualPivot.java
 *  Execution:    java QuickDualPivot < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   https://algs4.cs.princeton.edu/23quicksort/tiny.txt
 *                https://algs4.cs.princeton.edu/23quicksort/words3.txt
 *
 *  Sorts a sequence of strings from standard input using dual-pivot
 *  quicksort.
 *
 *  [Warning: not thoroughly tested.]
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java QuickDualPivot < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *
 *  % java QuickDualPivot < words3.txt
 *  all bad bed bug dad ... yes yet zoo    [ one string per line ]
 *
 ******************************************************************************/

public class QuickDualPivot {
	
	private static final int CUTOFF = 100000; 

	/**
	 * quicksort the array a[] using dual-pivot quicksort
	 * @param a
	 */
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    /**
     * quicksort the subarray a[lo .. hi] using dual-pivot quicksort 
     * @param a array in context
     * @param lo is the pointer that points to lo index
     * @param hi is the pointer that points to hi index
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;

        if (less(a[hi], a[lo])) exch(a, lo, hi);

        int lt = lo + 1, gt = hi - 1;
        int i = lo + 1;
        while (i <= gt) {
            if       (less(a[i], a[lo])) exch(a, lt++, i++);
            else if  (less(a[hi], a[i])) exch(a, i, gt--);
            else                         i++;
        }
        exch(a, lo, --lt);
        exch(a, hi, ++gt);

        /**
         * recursively sort three subarrays
         */
        sort(a, lo, lt-1);
        if (less(a[lt], a[gt])) sort(a, lt+1, gt-1);
        sort(a, gt+1, hi);

        assert isSorted(a, lo, hi);
    }

    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    /**
     * is v < w ? 
     * @param v
     * @param w
     * @return
     */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * exchange a[i] and a[j]
     * @param a array in context
     * @param i ith element's index to be exchanged
     * @param j jth element's index to be exchanged
     */
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    /**
     * print array to standard output
     * @param a array in context
     */
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            //StdOut.println(a[i]);
        }
    }

}