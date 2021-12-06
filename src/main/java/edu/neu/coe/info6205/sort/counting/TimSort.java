package edu.neu.coe.info6205.sort.counting;

import java.util.Arrays;

public class TimSort {
	
	static int MIN_MERGE = 32;
	
	/**
	 * Returns the minimum run length
	 * @param n length
	 * @return
	 */
    public static int minRunLength(int n) {
        assert n >= 0;
        int r = 0;
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }
    
    /**
     * Sorts sub array from index left to right
     * @param array array in context
     * @param left from index from which sorting starts
     * @param right to index to which sorting stops
     */
    public static void insertionSort(String[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            String temp = array[i];
            int j = i - 1;
            while (j >= left && array[j].compareTo(temp) > 0) {
            	array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }

    /**
     * Merges function to sort and merge the halved arrays
     * @param array array in context
     * @param l left index
     * @param m mid index
     * @param r right index
     */
    public static void merge(String[] array, int l,int m, int r) {
        int length1 = m - l + 1, length2 = r - m;
        String[] left = new String[length1];
        String[] right = new String[length2];
        for (int x = 0; x < length1; x++) {
            left[x] = array[l + x];
        }
        for (int x = 0; x < length2; x++) {
            right[x] = array[m + 1 + x];
        }

        int i = 0;
        int j = 0;
        int k = l;
        
        while (i < length1 && j < length2) {
            if (left[i].compareTo(right[j]) <= 0) {
            	array[k] = left[i];
                i++;
            } else {
            	array[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < length1) {
        	array[k] = left[i];
            k++;
            i++;
        }

        while (j < length2) {
        	array[k] = right[j];
            k++;
            j++;
        }
    }
    
    /**
     * sorts the array using Tim sort algorithm
     * @param array
     */
    public static void sort(String[] array) {
        timSort(array, array.length);
    }
    
    /**
     * sorts the array using Tim sort algorithm
     * @param arr array in context
     * @param n length of the array
     */
    public static void timSort(String[] arr, int n) {
        int minRun = minRunLength(MIN_MERGE);

        for (int i = 0; i < n; i += minRun) {
            insertionSort(arr, i,
                    Math.min((i + MIN_MERGE - 1), (n - 1)));
        }
        
        for (int size = minRun; size < n; size = 2 * size) {

            for (int left = 0; left < n;
                 left += 2 * size) {
            	
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1),(n - 1));
                if (mid < right) {
                	merge(arr, left, mid, right);
                }   
            }
        }
    }

}
