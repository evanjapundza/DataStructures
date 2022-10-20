import java.util.*;

public class DNAHammingDistance {

    public int hammingDistance(String firstStrand, String secondStrand) {
        int totalDistance = 0;

        for(int i = 0; i < firstStrand.length(); i++) {
            if (firstStrand.charAt(i) != secondStrand.charAt(i)){
                totalDistance++;
            }
        }

        return totalDistance;
    }
}
