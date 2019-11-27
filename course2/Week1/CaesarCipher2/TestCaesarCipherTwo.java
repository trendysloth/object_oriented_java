import edu.duke.*;
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipherTwo {
    private int[] countLetters(String message) {
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
    private int maxIndex(int[] values) {
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
    private String halfOfString(String message, int start) {
        String halfString = "";
        for (int i = start; i < message.length(); i=i+2) {
            halfString = halfString + message.charAt(i);
        }
        return halfString;
    }
    public String breakCaesarCipher(String input) {
        String messageOne = halfOfString(input, 0);
        int[] freqs1 = countLetters(messageOne);
        int maxDex1 = maxIndex(freqs1);
        int dkey1 = maxDex1 - 4;
        if (maxDex1 < 4) {
            dkey1 = 26 - (4 - maxDex1);
        }
        
        String messageTwo = halfOfString(input, 1);
        int[] freqs2 = countLetters(messageTwo);
        int maxDex2 = maxIndex(freqs2);
        int dkey2 = maxDex2 - 4;
        if (maxDex2 < 4) {
            dkey2 = 26 - (4 - maxDex2);
        }
        CaesarCipherTwo cc = new CaesarCipherTwo(dkey1, dkey2);
        return cc.decrypt(input);
    }
    public void simpleTests() {
        FileResource resource = new FileResource();
        String message = resource.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(17, 3);
        System.out.println(cc.encrypt(message) + "\n" + cc.decrypt(cc.encrypt(message)));
        String decryptMessage = this.breakCaesarCipher(cc.encrypt(message));
        System.out.println(decryptMessage);
    }
}
