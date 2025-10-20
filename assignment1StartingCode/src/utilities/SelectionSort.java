package utilities;

import java.util.Comparator;

/**
 * @author
 * @version 1.0
 * <p>
 * This class implements Selection Sort (descending) for any array of objects using a provided Comparator.
 * </p>
 */
public class SelectionSort {

    /**
     * Convenience method to sort the entire array in descending order using selection sort.
     *
     * @param array the array to sort
     * @param comp  the comparator to determine order (larger according to this comparator comes first)
     * @param <T>   the element type
     */
    public static <T> void sort(T[] array, Comparator<? super T> comp) {
        if (array == null || array.length < 2) return;
        sort(array, 0, array.length - 1, comp);
    }

    /**
     * Sorts a subrange of the array in descending order using selection sort.
     *
     * @param array the array to sort
     * @param low   the starting index (inclusive)
     * @param high  the ending index (inclusive)
     * @param comp  the comparator to determine order (larger according to this comparator comes first)
     * @param <T>   the element type
     */
    public static <T> void sort(T[] array, int low, int high, Comparator<? super T> comp) {
        // Guard against bad ranges or trivial cases
        if (array == null || comp == null || low < 0 || high >= array.length || low >= high) {
            return;
        }

        // For each position i, find the maximum element in [i..high] and swap it into position i.
        for (int i = low; i < high; i++) {
            int maxIndex = i;

            // Scan the unsorted tail to find the index of the largest element according to comp
            for (int j = i + 1; j <= high; j++) {
                // Since we want descending order, "larger" (comp > 0) should come earlier
                if (comp.compare(array[j], array[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }

            // Swap only if a new maximum was found
            if (maxIndex != i) {
                T tmp = array[i];
                array[i] = array[maxIndex];
                array[maxIndex] = tmp;
            }
        }
    }
}
