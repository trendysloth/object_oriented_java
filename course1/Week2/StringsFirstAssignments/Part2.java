
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        boolean isLowerCase = (dna.toLowerCase() == dna);
        dna = dna.toUpperCase();
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1) {
            return "";
        }
        int endIndex = dna.indexOf(stopCodon, startIndex + 1) + 3;
        if (endIndex == -1) {
            return "";
        }
        int length = endIndex - startIndex;
        if (length % 3 != 0) {
            return "";
        }
        if (isLowerCase) {
            return dna.substring(startIndex, endIndex).toLowerCase();
        }
        return dna.substring(startIndex, endIndex);
    }
    public void testSimpleGene() {
        String foundDNA = findSimpleGene("TAAATGAAATAA", "ATG", "TAA");
        System.out.println("Found DNA " + foundDNA);
        
        foundDNA = findSimpleGene("ATG", "ATG", "TAA");
        System.out.println("Found DNA " + foundDNA);
        
        foundDNA = findSimpleGene("TAA", "ATG", "TAA");
        System.out.println("Found DNA " + foundDNA);
        
        foundDNA = findSimpleGene("ATGATAA", "ATG", "TAA");
        System.out.println("Found DNA " + foundDNA);
        
        foundDNA = findSimpleGene("", "ATG", "TAA");
        System.out.println("Found DNA " + foundDNA);
        
        foundDNA = findSimpleGene("gatgctataat", "ATG", "TAA");
        System.out.println("Found DNA " + foundDNA);
        
        foundDNA = findSimpleGene("gatgctataat", "ATG", "TAA");
        System.out.println("Found DNA " + foundDNA);
    }
}
