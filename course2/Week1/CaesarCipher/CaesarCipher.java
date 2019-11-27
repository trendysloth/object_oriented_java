import edu.duke.*;
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key) {
        this.alphabet = "abcdefghijklmnopqrstuvwxyz";
        this.shiftedAlphabet = this.alphabet.substring(key) + 
                               this.alphabet.substring(0,key);
        this.mainKey = key;
    }
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = this.alphabet.indexOf(currChar);
            if (idx != -1) {
                char newChar = this.shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - this.mainKey);
        return cc.encrypt(input);
    }
}