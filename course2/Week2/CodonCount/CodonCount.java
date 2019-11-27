import edu.duke.*;
import java.util.*;
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCount {
    private HashMap<String, Integer> map;
    public CodonCount() {
        map = new HashMap<String,Integer>();
    }
    private void buildCodonMap(int start, String dna) {
        map.clear();
        int i = start;
        while (i <= dna.length() - 3) {
            String currCodon = dna.substring(i, i + 3).toUpperCase();
            if (map.containsKey(currCodon)){
                map.put(currCodon, map.get(currCodon) + 1);
            } else {
                map.put(currCodon, 1);
            }
            i = i + 3;
        }
    }
    private String getMostCommonCodon() {
        int maxCount = 0;
        String mostCommonCodon = "";
        for(String w : map.keySet()){
            int value = map.get(w);
            if (value > maxCount){
                mostCommonCodon = w;
                maxCount = value;
            }
        }
        return mostCommonCodon;
    }
    private void printCodonCounts(int start, int end) {
        for(String w : map.keySet()){
            int value = map.get(w);
            if (value >= start && value <= end){
                System.out.println(w + " " + value);
            }
        }
    }
    public void tester() {
        FileResource resource = new FileResource();
        for(String s : resource.words()){
            s = s.trim();
            System.out.println(s);
            buildCodonMap(0, s);
            System.out.println("\nReading frame starting with 0 results in " + map.size() + " unique codons");
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("most common codon is " + mostCommonCodon + " with count " + map.get(mostCommonCodon));
            System.out.println("Counts of codons between 1 and 5 inclusive are: ");
            printCodonCounts(1, 5);
            buildCodonMap(1, s);
            System.out.println("\nReading frame starting with 1 results in " + map.size() + " unique codons");
            mostCommonCodon = getMostCommonCodon();
            System.out.println("most common codon is " + mostCommonCodon + " with count " + map.get(mostCommonCodon));
            System.out.println("Counts of codons between 1 and 5 inclusive are: ");
            printCodonCounts(1, 5);
            buildCodonMap(2, s);
            System.out.println("\nReading frame starting with 2 results in " + map.size() + " unique codons");
            mostCommonCodon = getMostCommonCodon();
            System.out.println("most common codon is " + mostCommonCodon + " with count " + map.get(mostCommonCodon));
            System.out.println("Counts of codons between 1 and 5 inclusive are: ");
            printCodonCounts(1, 5);
        }
    }
}
