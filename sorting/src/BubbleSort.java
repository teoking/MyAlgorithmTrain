import java.util.Collections;
import java.util.List;

// @See https://en.wikipedia.org/wiki/Bubble_sort
public class BubbleSort extends SortAlgorithm {

    @Override
    public List<Integer> sort(int n, List<Integer> list) {
        boolean swapped;
        while (true) {
            swapped = false;
            for (int i = 1; i <= n - 1; i++) {
                if (list.get(i - 1) > list.get(i)) {
                    Collections.swap(list, i - 1, i);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
        return list;
    }
}
