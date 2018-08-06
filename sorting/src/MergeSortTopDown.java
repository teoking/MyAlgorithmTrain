import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// @See https://en.wikipedia.org/wiki/Merge_sort
public class MergeSortTopDown extends SortAlgorithm {

    @Override
    public List<Integer> sort(int n, List<Integer> list) {
        Collections.sort(list);
        return list;
    }

    private List<Integer> mergeSort(List<Integer> m) {
        // Base case. A list of zero or one elements is sorted, by definition.
        if (m.size() <= 1) {
            return m;
        }

        // Recursive case. First, device the list into equal-sized sublists
        // consisting of the first half and second half of the list.
        // This assumes lists start at index 0.
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int i = 0;
        for (int x : m) {
            if (i < m.size() / 2)
                left.add(x);
            else
                right.add(x);
            i++;
        }

        // Recursively sort both sublists.
        left = mergeSort(left);
        right = mergeSort(right);

        // Then merge the now-sorted sublists.
        return merge(left, right);
    }

    private List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> result = new ArrayList<>();

        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.get(0) <= right.get(0)) {
                result.add(left.remove(0));
            } else {
                result.add(right.remove(0));
            }
        }

        // Either left or right may have elements left; consume them.
        // (Only one of the following loops will actually be entered.)
        while (!left.isEmpty())
            result.addAll(left);
        while (!right.isEmpty())
            result.addAll(right);

        return result;
    }
}
