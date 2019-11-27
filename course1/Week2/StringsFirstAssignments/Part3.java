
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        int occurences1 = stringb.indexOf(stringa);
        if (occurences1 == -1) {
            return false;
        }
        int occurences2 = stringb.indexOf(stringa, occurences1 + stringa.length() + 1);
        if (occurences2 == -1) {
            return false;
        }
        return true;
    }
    public void testing() {
        boolean hasTwoOccurences = twoOccurrences("by", "A story by Abby Long");
        System.out.println(hasTwoOccurences);
        
        hasTwoOccurences = twoOccurrences("a", "banana");
        System.out.println(hasTwoOccurences);
        
        hasTwoOccurences = twoOccurrences("atg", "ctgtatgta");
        System.out.println(hasTwoOccurences);
        
        String lastString = lastPart("an", "banana");
        System.out.println(lastString);
        
        lastString = lastPart("zoo", "forest");
        System.out.println(lastString);
    }
    public String lastPart(String stringa, String stringb) {
        int occurences1 = stringb.indexOf(stringa);
        if (occurences1 == -1) {
            return stringb;
        }
        return stringb.substring(occurences1 + stringa.length(), stringb.length());
    }
}
