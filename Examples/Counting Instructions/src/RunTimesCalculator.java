public class RunTimesCalculator {

    void instrCount(int n) {
        int instrCount = 0;

        int i,j;
        int sum2 = 0;
        instrCount+=3;
        for (i = 1; i <= n; i++, instrCount++) {
            for (j = 1; j <= n; j++, instrCount++) {
                sum2++;
                instrCount++;
            }
        }
        System.out.println("In Example 1, for size n = "+n+", instrCounter = "+instrCount+", instrCounter/n = "+ (instrCount/n)+"." );
        instrCount = 0;

        int sum5 = 0;
        int k,c;
        instrCount+=3;
        for(k = 1; k <= n; k *= 2, instrCount++) {
            for(c = 1; c <= n; c++, instrCount++) {
                sum5++;
                instrCount++;
            }
        }

        System.out.println("In Example 4, for size n = "+n+", instrCounter = "+instrCount+", instrCounter/n = "+ (instrCount/n)+".\n" );



    }

}
