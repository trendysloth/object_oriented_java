import edu.duke.*;
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipher {
    private int[] countLetters(String message) {
        //             01234567890123456789012345 
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
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
    public String breakCaesarCipher(String input) {
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        CaesarCipher cc = new CaesarCipher(dkey);
        return cc.decrypt(input);
    }
    public void simpleTests() {
        FileResource resource = new FileResource();
        CaesarCipher cc = new CaesarCipher(18);
        String message = resource.asString();
        String encryptMessage = cc.encrypt(message);
        String decryptMessage = cc.decrypt(encryptMessage);
        System.out.println(encryptMessage + "\n" + decryptMessage);
        String decryptMessage2 = breakCaesarCipher(encryptMessage);
        System.out.println("\n" + decryptMessage2);
    }
}
