import java.util.*;
import java.io.*;

public class Lab09Hashing {

    public static void main(String[] args) throws Exception {

        File file = new File("/Users/evanjapundza/C343evjapund/labs/lab09/src/lab09text.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        HashMap<String, Set<Integer>> words = new HashMap<>();

        String currentLine;
        String tempLine;

        while((currentLine = br.readLine()) != null) {
            String[] splitLine = currentLine.split(" ");
            for(int i =0; i <splitLine.length;i++) {
                int lineNum2 = 1;
                if(!words.containsKey(splitLine[i])) {
                    BufferedReader tempReader = new BufferedReader(new FileReader(file));
                    Set<Integer> lines = new HashSet<>();
                    while((tempLine = tempReader.readLine()) != null) {
                        String[] theTempLine = tempLine.split(" ");
                        for (int j=0; j<theTempLine.length;j++) {
                            if(theTempLine[j].equals(splitLine[i])) {
                                lines.add(lineNum2);
                            }
                        }
                        lineNum2++;
                    }
                    //System.out.println(lines);
                    words.put(splitLine[i], lines);
                }
            }
        }

        System.out.println(words);
        System.out.println("Data = "+words.get("data"));
        System.out.println("Crazy = "+words.get("Crazy"));
        System.out.println("to = "+words.get("to"));
        System.out.println("methods = "+words.get("methods"));

    }
}
