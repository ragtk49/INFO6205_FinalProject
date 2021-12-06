package edu.neu.coe.info6205.sort.counting;

public class MSD {
    private static final int BITS_PER_BYTE =   8;
    private static final int BITS_PER_INT  =  32;   
    private static final int R             = 2560;   
    private static final int CUTOFF        =  15;   

    public MSD() { }

    /**
     * Rearranges the array of extended ASCII strings in ascending order.
     * @param a the array to be sorted
     */
    public static void sort(String[] a) {
        int n = a.length;
        String[] aux = new String[n];
        sort(a, 0, n-1, 0, aux);
    }

    /**
     * return dth character of s, -1 if d = length of string
     * @param s string that is referred
     * @param d length of the string
     * @return
     */
    public static int charAt(String s, int d) {
        assert d >= 0 && d <= s.length();
        if (d == s.length()) return -1;
        return s.charAt(d);
    }
    
    /**
     * sort from a[lo] to a[hi], starting at the dth character
     * @param a array to be sorted
     * @param lo starting index from which we have to sort
     * @param hi ending index to which we have to sort
     * @param d length of the string
     * @param aux auxilary array
     */
    private static void sort(String[] a, int lo, int hi, int d, String[] aux) {

    	/**
    	 * cutoff to insertion sort for small subarrays
    	 */
        if (hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d);
            return;
        }

        /**
         * compute frequency counts
         */
        int[] count = new int[R+2];
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            count[c+2]++;
        }

        /**
         * transform counts to indicies
         */
        for (int r = 0; r < R+1; r++)
            count[r+1] += count[r];

        /**
         * distribute
         */
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            aux[count[c+1]++] = a[i];
        }

        /**
         * copy back
         */
        for (int i = lo; i <= hi; i++)
            a[i] = aux[i - lo];


        /**
         * recursively sort for each character (excludes sentinel -1)
         */
        for (int r = 0; r < R; r++)
            sort(a, lo + count[r], lo + count[r+1] - 1, d+1, aux);
    }

    /**
     * insertion sort a[lo..hi], starting at dth character
     * @param a array in context
     * @param lo starting index from which we have to sort
     * @param hi ending index to which we have to sort
     * @param d length of the array 
     */
    private static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--)
                exch(a, j, j-1);
    }

    /**
     * exchange a[i] and a[j]
     * @param a array in context
     * @param i ith index of the element to be exchanged
     * @param j jth index of the element to be exchanged 
     */
    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * is v less than w, starting at character d
     * @param v
     * @param w
     * @param d
     * @return
     */
    private static boolean less(String v, String w, int d) {
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) return true;
            if (v.charAt(i) > w.charAt(i)) return false;
        }
        return v.length() < w.length();
    }


    /**
     * Rearranges the array of 32-bit integers in ascending order.
     * Currently assumes that the integers are nonnegative.
     * @param a the array to be sorted
     */
    private static void sort(int[] a) {
        int n = a.length;
        int[] aux = new int[n];
        sort(a, 0, n-1, 0, aux);
    }

    /**
     * MSD sort from a[lo] to a[hi], starting at the dth byte
     * @param a the array to be sorted
     * @param lo starting index from which we have to sort
     * @param hi ending index to which we have to sort
     * @param aux
     */
    private static void sort(int[] a, int lo, int hi, int d, int[] aux) {
    	
    	/**
    	 * cutoff to insertion sort for small subarrays
    	 */
        if (hi <= lo + CUTOFF) {
            insertion(a, lo, hi);
            return;
        }

        /**
         * compute frequency counts (need R = 256)
         */
        int[] count = new int[R+1];
        int mask = R - 1;   // 0xFF;
        int shift = BITS_PER_INT - BITS_PER_BYTE*d - BITS_PER_BYTE;
        for (int i = lo; i <= hi; i++) {
            int c = (a[i] >> shift) & mask;
            count[c + 1]++;
        }

        /**
         * transform counts to indicies 
         */
        for (int r = 0; r < R; r++)
            count[r+1] += count[r];

        /**
         * for most significant byte, 0x80-0xFF comes before 0x00-0x7F
         */
        if (d == 0) {
            int shift1 = count[R] - count[R/2];
            int shift2 = count[R/2];
            count[R] = shift1 + count[1];   
            for (int r = 0; r < R/2; r++)
                count[r] += shift1;
            for (int r = R/2; r < R; r++)
                count[r] -= shift2;
        }

        /**
         * distribute
         */
        for (int i = lo; i <= hi; i++) {
            int c = (a[i] >> shift) & mask;
            aux[count[c]++] = a[i];
        }

        /**
         * copy back 
         */
        for (int i = lo; i <= hi; i++)
            a[i] = aux[i - lo];

        /**
         * no more bits
         */
        if (d == 3) return;

        /**
         * special case for most significant byte
         */
        if (d == 0 && count[R/2] > 0)
            sort(a, lo, lo + count[R/2] - 1, d+1, aux);

        /**
         * special case for other bytes
         */
        if (d != 0 && count[0] > 0)
            sort(a, lo, lo + count[0] - 1, d+1, aux);

        /**
         * recursively sort for each character
         * (could skip r = R/2 for d = 0 and skip r = R for d > 0)
         */
        for (int r = 0; r < R; r++)
            if (count[r+1] > count[r])
                sort(a, lo + count[r], lo + count[r+1] - 1, d+1, aux);
    }

    /**
     * insertion sort of integer array
     * @param a integer array
     * @param lo starting index from which we have to sort
     * @param hi ending index to which we have to sort
     */
    private static void insertion(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && a[j] < a[j-1]; j--)
                exch(a, j, j-1);
    }

    /**
     * exchange of elements a[i] and a[j]
     * @param a integer array 
     * @param i index of ith element
     * @param j index of jth element
     */
    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}