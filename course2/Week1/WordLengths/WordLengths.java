import java.util.*;
import edu.duke.*;
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    public int[] countWordLengths(FileResource resource, int[] counts) {
        for (String word: resource.words()) {
            int wordLength = 0;
            int startIndex = 0;
            while (startIndex < word.length()) {
                char currChar = word.charAt(startIndex);
                if (Character.isLetter(currChar)) {
                    break;
                } else {
                    startIndex += 1;
                }
            }
            for (int i = startIndex; i < word.length(); i ++) {
                char currChar = word.charAt(i);
                if (Character.isLetter(currChar) || currChar == '-' || 
                    currChar == '\'') {
                    wordLength += 1;
                }
            }
            // System.out.println(word + " " + wordLength);
            if (wordLength > counts.length) {
                counts[counts.length] += 1;
            } else {
                counts[wordLength] += 1;
            }
        }
        return counts;
    }
    public int indexOfMax(int[] values) {
        int indexOfMax = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > indexOfMax) {
                indexOfMax = values[i];
            }
        }
        return indexOfMax;
    }
    public void testCountWordLengths() {
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        int[] countWordLengths = countWordLengths(resource, counts);
        for (int i = 0; i < countWordLengths.length; i ++) {
            if (countWordLengths[i] != 0) {
                System.out.println(countWordLengths[i] + " words of length " + i);
            }
        }
        System.out.println(indexOfMax(countWordLengths));
    }
}