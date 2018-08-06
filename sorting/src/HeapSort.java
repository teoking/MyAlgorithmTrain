import java.util.Collections;
import java.util.List;

// @See https://en.wikipedia.org/wiki/Heapsort
public class HeapSort extends SortAlgorithm {

    @Override
    public List<Integer> sort(int n, List<Integer> list) {
        heapSort(list, n);
        return list;
    }

    // map a real number to the smallest leading integer
    private int iParent(int i) {
        return (int) Math.floor((i - 1) >> 1);
    }

    private int iLeftChild(int i) {
        return 2 * i + 1;
    }

    private int iRightChild(int i) {
        return 2 * i + 2;
    }

    private void heapSort(List<Integer> list, int n) {
        heapify(list, n);

        int end = n - 1;
        while (end > 0) {
            // list[0] is the root and largest value. The swap moves it in front of the sorted elements.
            Collections.swap(list, end, 0);
            // the heap size is reduced by one
            end--;
            // the swap ruined the heap property, so restore it
            siftDown(list, 0, end);
        }
    }

    // Put elements of 'list' in heap order, in-place
    private void heapify(List<Integer> list, int count) {
        // start is assigned the index in 'list' of the last parent node
        // the last element in a 0-based array is at count-1; find the parent of that element
        int start = iParent(count - 1);

        while (start >= 0) {
            // sift down the node at index 'start' to the proper place such that all nodes below
            siftDown(list, start, count - 1);
            // go to the next parent node
            start--;
        }
        // after sifting down the root all nodes/elements are in heap order
    }

    // Repair the heap whose root element is at index 'start', assuming the heaps rooted at its children are valid.
    private void siftDown(List<Integer> list, int start, int end) {
        int root = start;

        // while the root has at least one child
        while (iLeftChild(root) <= end) {
            // Left child of root
            int child = iLeftChild(root);
            // keeps track of child to swap with
            int swap = root;

            if (list.get(swap) < list.get(child)) {
                swap = child;
            }
            // If there is a right child and that child is greater
            if (child + 1 <= end && list.get(swap) < list.get(child + 1)) {
                swap = child + 1;
            }
            if (swap == root) {
                // The root holds the largest element. Since we assume the heaps rooted at the children are valid,
                // this means that we are done.
                return;
            } else {
                Collections.swap(list, root, swap);
                root = swap;
            }
        }
    }
}
