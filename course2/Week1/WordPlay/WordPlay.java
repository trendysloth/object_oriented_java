import edu.duke.*;
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            return true;
        }
        return false;
    }
    public void testIsVowel() {
        System.out.println(isVowel('F'));
        System.out.println(isVowel('a'));
        System.out.println(isVowel('E'));
    }
    public String replaceVowels(String phrase, char ch) {
        StringBuilder input = new StringBuilder(phrase);
        for (int i = 0; i < input.length(); i ++) {
            char currChar = input.charAt(i);
            if (isVowel(currChar)) {
                input.setCharAt(i, ch);
            }
        }
        return input.toString();
    }
    public void testReplaceVowels() {
        System.out.println(replaceVowels("Hello World", '*'));
    }
    public String emphasize(String phrase, char ch) {
        StringBuilder input = new StringBuilder(phrase);
        for (int i = 0; i < input.length(); i ++) {
            char currChar = input.charAt(i);
            if (currChar == ch || currChar == Character.toUpperCase(ch)) {
                if (i % 2 == 0) {
                    input.setCharAt(i, '*');
                } else {
                    input.setCharAt(i, '+');
                }
            }
        }
        return input.toString();
    }
    public void testEmphasize() {
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}
