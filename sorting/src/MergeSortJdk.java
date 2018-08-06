import java.util.Collections;
import java.util.List;

// Merge sort impl in the JDK.
public class MergeSortJdk extends SortAlgorithm {

    @Override
    public List<Integer> sort(int n, List<Integer> list) {
        Collections.sort(list);
        return list;
    }
}
