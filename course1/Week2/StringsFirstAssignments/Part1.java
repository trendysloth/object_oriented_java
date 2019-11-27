
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int endIndex = dna.indexOf("TAA", startIndex + 1) + 3;
        if (endIndex == -1) {
            return "";
        }
        int length = endIndex - startIndex;
        if (length % 3 != 0) {
            return "";
        }
        return dna.substring(startIndex, endIndex);
    }
    public void testSimpleGene() {
        String foundDNA = findSimpleGene("TAAATGAAATAA");
        System.out.println("Found DNA " + foundDNA);
        
        foundDNA = findSimpleGene("ATG");
        System.out.println("Found DNA " + foundDNA);
        
        foundDNA = findSimpleGene("TAA");
        System.out.println("Found DNA " + foundDNA);
        
        foundDNA = findSimpleGene("ATGATAA");
        System.out.println("Found DNA " + foundDNA);
        
        foundDNA = findSimpleGene("");
        System.out.println("Found DNA " + foundDNA);
    }
}
