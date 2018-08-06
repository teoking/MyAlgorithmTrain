import java.util.Collections;
import java.util.List;

public class InsertionSortR extends SortAlgorithm {

    @Override
    public List<Integer> sort(int n, List<Integer> list) {
        return insertionSortRecursive(n - 1, list);
    }

    private List<Integer> insertionSortRecursive(int n, List<Integer> list) {
        if (n > 0) {
            insertionSortRecursive(n - 1, list);
            int x = list.get(n);
            int j = n - 1;
            while (j >= 0 && list.get(j) > x) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, x);
        }
        return list;
    }
}
