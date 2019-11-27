import edu.duke.*;
import java.util.ArrayList;
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    private void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        for(String s : resource.words()) {
            s = s.toLowerCase();
            int indexOfWord = myWords.indexOf(s);
            if (indexOfWord != -1) {
                myFreqs.set(indexOfWord, myFreqs.get(indexOfWord) + 1);
            } else {
                myWords.add(s);
                myFreqs.add(1);
            }
        }
    }
    private int findIndexOfMax() {
        int indexOfMax = 0;
        int maxVal = myFreqs.get(0);
        
        for (int i = 0; i < myWords.size(); i ++) {
            int currVal = myFreqs.get(i);
            if (currVal > maxVal) {
                indexOfMax = i;
                maxVal = currVal;
            }
        }
        return indexOfMax;
    }
    public void tester() {
        findUnique();
        System.out.println("Number of unique words: " + myFreqs.size());
        //for (int i = 0; i < myWords.size(); i ++) {
        //    System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        //}
        int indexOfMax = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " + 
                           myWords.get(indexOfMax) + " " + myFreqs.get(indexOfMax));
    }
}
