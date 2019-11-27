import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet_UpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabet_LowerCase = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet_UpperCase = alphabet_UpperCase.substring(key) + alphabet_UpperCase.substring(0,key);
        String shiftedAlphabet_LowerCase = alphabet_LowerCase.substring(key) + alphabet_LowerCase.substring(0,key);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (currChar != Character.toLowerCase(currChar)) {
                int idx = alphabet_UpperCase.indexOf(currChar);
                if (idx != -1) {
                    char newChar = shiftedAlphabet_UpperCase.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            } else {
                int idx = alphabet_LowerCase.indexOf(currChar);
                if (idx != -1) {
                    char newChar = shiftedAlphabet_LowerCase.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
    public void testCaesar() {
        int key = 17;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        //String encrypted = encrypt(message, key);
        //System.out.println(encrypted);
        //String decrypted = encrypt(encrypted, 26-key);
        //System.out.println(decrypted);
        //System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
        // FileResource fr = new FileResource();
        // String message = fr.asString();
        // String encrypted = encrypt(message, key);
        // System.out.println("key is " + key + "\n" + encrypted);
        // System.out.println(encrypt("First Legion", 23));
        System.out.println(encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15));
    }
    public String encryptTwoKeys(String input, int key1, int key2) {
        String doubleEncrypted = "";
        for(int i = 0; i < input.length(); i++) {
            String curr = Character.toString(input.charAt(i));
            if (i % 2 == 0) {
                doubleEncrypted += encrypt(curr, key1);
            } else {
                doubleEncrypted += encrypt(curr, key2);
            }
        }
        return doubleEncrypted;
    }
    
    public void testEncryptTwoKeys() {
        System.out.println(encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.", 26-14, 26-24));
    }
}

