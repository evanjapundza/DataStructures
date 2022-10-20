import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortDist {
    static int increment(int n) {
        // From https://oeis.org/search?q=shell+sort
        // a(n) = 9*2^n - 9*2^(n/2) + 1 if n is even;
        // a(n) = 8*2^n - 6*2^((n+1)/2) + 1 if n is odd.
        if (n % 2 == 0)
            return (int) (9 * Math.pow(2, n) - 9 * Math.pow(2, n / 2) + 1);
        else
            return (int) (8 * Math.pow(2,n) - 6 * Math.pow(2,(n + 1) / 2) + 1);
    }
    /**
     * Steps:
     * 1. create an array list gapSequence that calls increment above until the
     *    gap is more than half of the size of the array (increase i by 1 each time you call increment)
     * 2. Start from the largest gap and iterate down the list of gaps
     * 3. For each gap, do an insertion sort for the elements separated
     *    by the given gap
     * 4. Return your list of sorted elements at the end of all gaps
     */
    static List<Integer> shellSort (List<Integer> ns) {
        ArrayList<Integer> gapSequence = new ArrayList<>();
        List<Integer> result = ns;
        int i = 0;
        int counter = 0;
        //TODO: Implement Task B
        for(i = 0; counter<ns.size()/2; i++) {
            gapSequence.add(increment(i));
            counter = increment(i);
        }

        for(i = gapSequence.size()-1; i > -1; i--) {
            for(int j = 0; j < result.size(); j++) {
                if(j+gapSequence.get(i) < result.size() && result.get(j) > result.get(j+gapSequence.get(i))) {
                    Collections.swap(result, j, j+gapSequence.get(i));
                    j=0;
                }
            }
        }
        return result;
    }

    static int partition(List<Integer> ns, int begin, int end)
    {
        //TODO: Task C Helper Function
        int pivot = ns.get(end);
        int i = (begin - 1);

        for(int j = begin; j <= end - 1; j++) {
            if (ns.get(j) < pivot) {
                i++;
                Collections.swap(ns,i,j);
            }
        }
        Collections.swap(ns, i+1, end);
        return (i+1);
    }

    static List<Integer>quickSort(List<Integer> ns, int begin, int end)
    {
        //TODO: Implement Task C
        if (begin < end)
        {
            int thePartition = partition(ns, begin, end);
            quickSort(ns, begin, thePartition - 1);
            quickSort(ns, thePartition + 1, end);
        }
        return ns;
    }

    public static void main(String args[]){
        List<Integer> list1 = Arrays.asList(1,9,81,32,4,94,7,2);
        Instant start = Instant.now();
        System.out.println(shellSort(list1));
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toNanos());

        List<Integer> list2 = Arrays.asList(6,10,5,29,39,43,24,16,92);
        start = Instant.now();
        quickSort(list2,0,8);
        System.out.println(list2);
        end = Instant.now();
        System.out.println(Duration.between(start,end).toNanos());
    }
}