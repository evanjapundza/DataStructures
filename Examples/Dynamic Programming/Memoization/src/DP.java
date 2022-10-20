import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class DP {

    /**
     * We create a hash table called coinChangeMemo.
     * Each subproblem is determined by the list of coins and by the desired sum.
     * That combination should be the key.
     */
    static final Map<Pair<List<Coin>,Integer>,Integer> coinChangeMemo = new HashMap<>();

    /**
     * Problem Description
     * -------------------
     * Given a list of possible coins that must include a penny,
     * and a number of pennies 'n', in how many ways (permutations) can we use the
     * coins to make change for 'n'? You should make use of memoization in your solution!
     *
     * @param coins The list of coins you can use to make change.
     * @param n The amount of change to be made.
     * @return The total number of combinations possible to make change for n
     */
    static int mcoinChange (List<Coin> coins, int n) {
        // Check if the solution has been previously computed?
        if(coinChangeMemo.containsKey(new Pair<>(coins, n))) {
            return coinChangeMemo.get(new Pair<>(coins, n));
        }

        int answer = 0;

        try {
            // How many cases are there?
            if (n == 0) answer=1;
            else if (n<0) answer=0;
            else {
                answer+=mcoinChange(coins, n-coins.getFirst().getValue());
                answer+=mcoinChange(coins.getRest(), n);
            }
        }
        catch (EmptyListE e) {
            // Since an EmptyListE was thrown, can you make change?
            return 0;
        }

        coinChangeMemo.put(new Pair<>(coins, n), answer);

        /**
         * use answer here!
         */
        return answer;
    }

    /**
     * Problem Description
     * -------------------
     *
     * You are going on a long trip. You start on the road at mile post 0. Along the way there are n
     * hotels, at mile posts a1 < a2 < ... < an, where each ai is the distance measured from the
     * starting point. The only places you are allowed to stop are at these hotels, but you can
     * choose which of the hotels you stop at. You must stop at the final hotel (at distance an),
     * which is your destination. You would ideally like to travel 200 miles a day, but this may
     * not be possible. If you travel x miles during a day, the penalty for that day is (200 − x)^2.
     * You want to plan your trip so as to minimize the total penalty, which is the sum of
     * penalties over all travel days.
     */

    /**
     * Computes the optimal trip given the distances from the starting point using the penalty
     * equation (200 - x)^2.
     *
     * @param distances The distances from the starting point for potential hotel stops.
     * @return The optimal indices hotels to stop at and the total penalty for the trip contained
     * in a Pair obj.
     */
    public Pair findHotels(int[] distances) {

        int[] penalties = new int[distances.length];
        int[] pathIndexes = new int[distances.length];

        for (int i = 0; i < distances.length; i++) {
            penalties[i] = (int) (Math.pow((200 - distances[i]), 2));
            pathIndexes[i] = 0;

            for (int j = 0; j < i; j++) {
                if (penalties[j] + Math.pow((200 - (distances[i] - distances[j])), 2) < penalties[i]) {
                    penalties[i] = (int)(penalties[j] + Math.pow((200 - (distances[i] - distances[j])), 2));
                    pathIndexes[i] = j + 1;
                }
            }
        }

        ArrayList<Integer> finalPath = new ArrayList<>();

        int index = pathIndexes.length-1;
        while(index>=0){
            finalPath.add((index));
            index = pathIndexes[index]-1;
        }

        Collections.reverse(finalPath);
        Pair<ArrayList<Integer>, Integer> finalTotal = new Pair<ArrayList<Integer>, Integer>(finalPath, penalties[distances.length-1]);

        return finalTotal;

    }

    public static void main(String[] args) {

        List<Coin> coins4 = new Node<>(new Coin(1), new Node<>(new Coin(5), new Node<>(new Coin(10),
                new Node<>(new Coin(25), new Empty<>()))));
        System.out.printf("Coins: %-20s Change: %-4d Permutations: %d\n", coins4, 100,
                mcoinChange(coins4, 100));

        System.out.println();
        System.out.println();
        int[] distances3 = new int[6];
        distances3[0] = 200;
        distances3[1] = 250;
        distances3[2] = 400;
        distances3[3] = 405;
        distances3[4] = 605;
        distances3[5] = 800;
        Pair answer3 = new DP().findHotels(distances3);
        System.out.println("answer3 Hotels:  " + answer3.getFirst());
        System.out.println("answer3 Penalty: " + answer3.getSecond());


        System.out.println();
        System.out.println();

        int[] distances4 = new int[4];
        distances4[0] = 100;
        distances4[1] = 200;
        distances4[2] = 400;
        distances4[3] = 700;


        Pair answer4 = new DP().findHotels(distances4);
        System.out.println("answer4 Hotels:  " + answer4.getFirst());
        System.out.println("answer4 Penalty: " + answer4.getSecond());
    }

}