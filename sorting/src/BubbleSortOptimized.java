import java.util.Collections;
import java.util.List;

// @See https://en.wikipedia.org/wiki/Bubble_sort
public class BubbleSortOptimized extends SortAlgorithm {

    @Override
    public List<Integer> sort(int n, List<Integer> list) {
        int newN = 0;
        int size = n;
        while (true) {
            newN = 0;
            for (int i = 1; i <= size - 1; i++) {
                if (list.get(i - 1) > list.get(i)) {
                    Collections.swap(list, i - 1, i);
                    newN = i;
                }
            }
            size = newN;
            if (size == 0) {
                break;
            }
        }
        return list;
    }
}
