
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SortAlgorithmTest {

    private List<Integer> mList1;
    private List<Integer> mList2;
    private List<Integer> mHugeList;

    @Before
    public void init() throws FileNotFoundException {
        mList1 = readSampleInputAsList("./data/sample1_input.txt");
        mList2 = readSampleInputAsList("./data/sample2_input.txt");
        mHugeList = randomIntList(30000);
    }

    private List<Integer> readSampleInputAsList(String filePath) throws FileNotFoundException {
        Scanner s = new Scanner(new File(filePath));
        int n = s.nextInt();
        List<Integer> list = new ArrayList<>(n);
        while (s.hasNextInt()) {
            list.add(s.nextInt());
        }
        s.close();
        return list;
    }

    private List<Integer> randomIntList(int n) {
        List<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(-10, 11);
            list.add(randomNum);
        }
        return list;
    }

    @After
    public void release() {
        mHugeList = null;
    }

    @Test
    public void testBubbleSort() {
        sortingThat(new BubbleSort(), "BubbleSort", true);
    }

    @Test
    public void testBubbleSort2() {
        sortingThat(new BubbleSortOptimized(), "BubbleSortOptimized", true);
    }

    @Test
    public void testMergeSortJdk() {
        sortingThat(new MergeSortJdk(), "MergeSortJdk", true);
    }

    @Test
    public void testInsertionSort() {
        sortingThat(new InsertionSort(), "InsertionSort", true);
    }

    @Test
    public void testInsertionSortR() {
        // 递归版本排序会StackOverflowError
        sortingThat(new InsertionSortR(), "InsertionSortR", false);
    }

    @Test
    public void testQuickSortLomuto() {
        sortingThat(new QuickSortLomutoPartition(), "QuickSortLomuto", true);
    }

    @Test
    public void testQuickSortHoare() {
        sortingThat(new QuickSortHoarePartition(), "QuickSortHoare", true);
    }

    @Test
    public void testMergeSortTopDown() {
        sortingThat(new MergeSortTopDown(), "MergeSortTopDown", true);
    }

    @Test
    public void testHeapSort() {
        sortingThat(new HeapSort(), "HeapSort", true);
    }

    private void sortingThat(SortAlgorithm sort, String tag, boolean forHugeList) {
        sort.sort(mList1.size(), mList1);
        sort.sort(mList2.size(), mList2);
        if (forHugeList)
            sort.sort(mHugeList.size(), mHugeList);

        dumpList(tag, mList1);
        dumpList(tag, mList2);
    }

    private static void dumpList(String tag, List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        sb.append(tag).append(" ").append(list.size()).append(": ");
        for (int i : list) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
