package utilities;

import java.util.Comparator;

/**
 * @author
 * Bilal Berhane
 * @version 1.0
 * <p>
 * Heap Sort (descending) using a max-heap over a subrange [low..high] of an array.
 * </p>
 */
public class HeapSort {

    /**
     * Convenience: sort whole array in descending order using heap sort.
     * @author Bilal Berhane 
     * @param array the array to sort
     * @param comp  comparator that defines “larger”
     * @param <T>   element type
     */
    public static <T> void sort(T[] array, Comparator<? super T> comp) {
        if (array == null || array.length < 2) return;
        sort(array, 0, array.length - 1, comp);
    }

    /**
     * Sort a subrange [low..high] in descending order using a max-heap.
     * @param array the array
     * @param low   start index (inclusive)
     * @param high  end index (inclusive)
     * @param comp  comparator to determine ordering
     * @param <T>   element type
     */
    public static <T> void sort(T[] array, int low, int high, Comparator<? super T> comp) {
        if (array == null || comp == null || low < 0 || high >= array.length || low >= high) return;

        // Build max-heap over logical heap indices [0..n-1], where n = high-low+1.
        int n = high - low + 1;

        // Heapify: sift down from the last internal node down to root.
        for (int i = (n / 2) - 1; i >= 0; i--) {
            siftDown(array, low, n, i, comp);
        }

        // Repeatedly move current max (heap root) to the end of the heap, shrink heap, and restore heap.
        for (int end = n - 1; end > 0; end--) {
        	// move max to its final position
        	swap(array, low + 0, low + end);
        	// restore heap on the reduced range [0..end-1]
            siftDown(array, low, end, 0, comp); 
        }
    }

    /**
     * Restore max-heap property by sifting the element at heap index 'i' down within heap size 'heapSize'.     
     */
    private static <T> void siftDown(T[] array, int low, int heapSize, int i, Comparator<? super T> comp) {
        while (true) {
            int left = (i * 2) + 1;
            int right = left + 1;
            int largest = i;

            // Compare children with current largest; use comparator > 0 for "is larger than".
            if (left < heapSize && comp.compare(array[low + left], array[low + largest]) > 0) {
                largest = left;
            }
            if (right < heapSize && comp.compare(array[low + right], array[low + largest]) > 0) {
                largest = right;
            }
            
            // heap holds
            if (largest == i) break; 
            
            // continue sifting down
            swap(array, low + i, low + largest);
            i = largest; 
        }
    }

    private static <T> void swap(T[] array, int a, int b) {
        T tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }
}
