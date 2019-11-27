import edu.duke.*;
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarBreaker {
    public int[] countLetters(String message) {
        //             01234567890123456789012345 
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i = 0; i < message.length(); i++) {
            char ch = Character.toLowerCase(message.charAt(i));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }
    public int maxIndex(int[] values) {
        int maxIndex = 0;
        int maxValue = 0;
        for (int i = 0; i < values.length; i++) {
            int currValue = values[i];
            if (currValue > maxValue) {
                maxValue = currValue;
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    public String halfOfString(String message, int start) {
        String halfString = "";
        for (int i = start; i < message.length(); i=i+2) {
            halfString = halfString + message.charAt(i);
        }
        return halfString;
    }
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }
    public String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String messageOne = halfOfString(encrypted, 0);
        String messageTwo = halfOfString(encrypted, 1);
        //System.out.println(messageOne);
        //System.out.println(messageTwo);
        int key1 = getKey(messageOne);
        int key2 = getKey(messageTwo);
        //int key1 = 2;
        //int key2 = 20;
        System.out.println(key1 + " " + key2);
        return cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
    }
    public void testDecrypt() {
        // System.out.println(decryptTwoKeys("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu"));
        // System.out.println(decryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx"));
        // System.out.println(decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
        // FileResource resource = new FileResource();
        // System.out.println(resource.words());
        FileResource resource = new FileResource();
        String messages = "";
        for (String word: resource.words()) {
            messages += " " + word;
        }
        System.out.println(decryptTwoKeys(messages));
    }
}