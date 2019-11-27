
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon) {
        int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
                return currIndex;
            }
            else {
                currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
            }
        }        
        return -1;
    }
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = 0;
        
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        }
        else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 || (tgaIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        if (minIndex == -1) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    public void testOn(String dna) {
        System.out.println("Testing printAllGenes on " + dna);
        printAllGenes(dna);
    }
    public void test() {
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
    public void testFindStopCodon() {
        //            01234567890123456789012345
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna, 0, "TAA");
        if (dex != 9) System.out.println("error on 9");
        dex = findStopCodon(dna, 9, "TAA");
        if (dex != 21) System.out.println("error on 21");
        dex = findStopCodon(dna, 1, "TAA");
        if (dex != -1) System.out.println("error on 26");
        dex = findStopCodon(dna, 1, "TAG");
        if (dex != -1) System.out.println("error on 26 TAG");
        System.out.println("tests finished");
    }
    public void testFindGene() {
        //String dna1 = "TAAATAAATAA";
        //System.out.println(findGene(dna1));
        //String dna2 = "TAAATGAAATAA";
        //System.out.println(findGene(dna2));
        //String dna3 = "TAAATGAAATAAATGTATTAG";
        //System.out.println(findGene(dna3));
        //String dna4 = "TAAATGAAATAAATGTATTAGATGGGGTTTTAG";
        //System.out.println(findGene(dna4));
        //String dna5 = "ATGTTT";
        //System.out.println(findGene(dna5));
        String dna = "ATGCCCGGGAAATAACC";
        String gene = findGene(dna, 0);
        if (!gene.equals("ATGCCCGGGAAATAA")) {
            System.out.println("error");
        }
        System.out.println("tests finished");
    }
    public int countGenes(String dna) {
        int startIndex = 0;
        int counter = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            counter = counter + 1;
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return counter;
    }
    public void testCountGenes() {
        System.out.println(countGenes("TAAATGAAATAAATGTATTAG"));
        System.out.println(countGenes("TAAATGAAATAAATGTATTAGATGGGGTTTTAG"));
    }
}
