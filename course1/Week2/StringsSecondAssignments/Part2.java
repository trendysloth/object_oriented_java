
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int counter = 0;
        int startIndex = 0;
        while (true) {
            startIndex = stringb.indexOf(stringa, startIndex);
            // System.out.println(startIndex);
            if (startIndex == -1 || startIndex >= stringb.length()) {
                break;
            }
            startIndex = startIndex + stringa.length();
            counter = counter + 1;
        } 
        return counter;
    }
    public void testHowMany() {
        int numOfStringb = howMany("AA", "ATAAAA");
        System.out.println(numOfStringb);
        
        numOfStringb = howMany("GAA", "ATGAACGAATTGAATC");
        System.out.println(numOfStringb);
        
        numOfStringb = howMany("", "");
        System.out.println(numOfStringb);
    }
}
