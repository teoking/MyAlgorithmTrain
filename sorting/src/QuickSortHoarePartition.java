import java.util.Collections;
import java.util.List;

// @See https://en.wikipedia.org/wiki/Quicksort
public class QuickSortHoarePartition extends SortAlgorithm {

    @Override
    public List<Integer> sort(int n, List<Integer> list) {
        quickSort(list, 0, n - 1);
        return list;
    }

    private void quickSort(List<Integer> list, int lo, int hi) {
        if (lo < hi) {
            int p = partition(list, lo, hi);
            quickSort(list, lo, p);
            quickSort(list, p + 1, hi);
        }
    }

    private int partition(List<Integer> list, int lo, int hi) {
        int pivot = list.get(lo);
        int i = lo - 1;
        int j = hi + 1;
        while (true) {
            do {
                i++;
            } while (list.get(i) < pivot);

            do {
                j--;
            } while (list.get(j) > pivot);

            if (i >= j)
                return j;

            Collections.swap(list, i, j);
        }
    }
}
