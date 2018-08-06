import java.util.Collections;
import java.util.List;

// @See https://en.wikipedia.org/wiki/Quicksort
public class QuickSortLomutoPartition extends SortAlgorithm {

    @Override
    public List<Integer> sort(int n, List<Integer> list) {
        quickSort(list, 0, n - 1);
        return list;
    }

    private void quickSort(List<Integer> list, int lo, int hi) {
        if (lo < hi) {
            int p = partition(list, lo, hi);
            quickSort(list, lo, p - 1);
            quickSort(list, p + 1, hi);
        }
    }

    private int partition(List<Integer> list, int lo, int hi) {
        int pivot = list.get(hi);
        int i = lo - 1;
        for (int j = lo; j <= hi - 1; j++) {
            if (list.get(j) < pivot) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, hi);
        return i + 1;
    }
}
