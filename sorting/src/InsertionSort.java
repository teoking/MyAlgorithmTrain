import java.util.Collections;
import java.util.List;

// @See https://en.wikipedia.org/wiki/Insertion_sort
public class InsertionSort extends SortAlgorithm {

    @Override
    public List<Integer> sort(int n, List<Integer> list) {
        int i = 1;
        int j;
        while (i < n) {
            j = i;
            while (j > 0 && list.get(j - 1) > list.get(j)) {
                Collections.swap(list, j, j - 1);
                j--;
            }
            i++;
        }
        return list;
    }
}
