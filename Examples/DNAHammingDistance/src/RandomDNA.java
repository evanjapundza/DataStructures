import java.util.Random;

public class RandomDNA {

    public String generateDNAstring(int n) {
        String lDNA = "";
        Random lRandomizer = new Random();
        char dnaList[] = {'A', 'T', 'C', 'G'};

        for (int i = 0; i < n; i++) {
            lDNA += dnaList[lRandomizer.nextInt(4)];
        }
        return lDNA;
    }

    // a test client
    public static void main(String[] argv) {
        RandomDNA myRandomDNA = new RandomDNA();
        DNAHammingDistance myHammingDistance = new DNAHammingDistance();
        System.out.println();
        System.out.println("Test Case 1:");
        String myDNAstring1 = myRandomDNA.generateDNAstring(100);
        String myDNAstring2 = myRandomDNA.generateDNAstring(100);
        int hammingDistance1 = myHammingDistance.hammingDistance(myDNAstring1, myDNAstring2);

        System.out.println("Strand 1: "+myDNAstring1+" | Strand 2: "+myDNAstring2);
        System.out.println("Hamming Distance: "+hammingDistance1);

        System.out.println();
        System.out.println("Test Case 2:");
        myDNAstring1 = myRandomDNA.generateDNAstring(100);
        myDNAstring2 = myRandomDNA.generateDNAstring(100);
        hammingDistance1 = myHammingDistance.hammingDistance(myDNAstring1, myDNAstring2);

        System.out.println("Strand 1: "+myDNAstring1+" | Strand 2: "+myDNAstring2);
        System.out.println("Hamming Distance: "+hammingDistance1);

        System.out.println();
        System.out.println("Test Case 3:");
        myDNAstring1 = myRandomDNA.generateDNAstring(100);
        myDNAstring2 = myRandomDNA.generateDNAstring(100);
        hammingDistance1 = myHammingDistance.hammingDistance(myDNAstring1, myDNAstring2);

        System.out.println("Strand 1: "+myDNAstring1+" | Strand 2: "+myDNAstring2);
        System.out.println("Hamming Distance: "+hammingDistance1);



    }

} // end of class RandomDNA