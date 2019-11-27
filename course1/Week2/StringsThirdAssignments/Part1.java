
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part1 {
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
        // System.out.println(dna);
        // System.out.println(startIndex);
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
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        // System.out.println(minIndex);
        if (minIndex == -1) {
            return "";
        }
        // System.out.println(minIndex);
        return dna.substring(startIndex, minIndex + 3);
    }
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            // System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    public StorageResource getAllGenes(String dna) {
        int startIndex = 0;
        StorageResource sr = new StorageResource();
        while (true) {
            String currentGene = findGene(dna, startIndex);
            // System.out.println("Current Gene is " + currentGene);
            if (currentGene.isEmpty()) {
                break;
            }
            sr.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        System.out.println(sr.size());
        return sr;
    }
    public float cgRatio(String dna) {
        int numOfC = 0;
        int numOfG = 0;
        int startIndex = 0;
        while (true) {
            startIndex = dna.indexOf("C", startIndex);
            if (startIndex == -1) {
                break;
            }
            startIndex = startIndex + 1;
            numOfC = numOfC + 1;
        }
        // System.out.println(numOfC);
        startIndex = 0;
        while (true) {
            startIndex = dna.indexOf("G", startIndex);
            if (startIndex == -1) {
                break;
            }
            startIndex = startIndex + 1;
            numOfG = numOfG + 1;
        }
        float totalCG = Float.valueOf(numOfC + numOfG);
        return totalCG / dna.length();
    }
    public int numOfCTG(String dna) {
        int numOfCTG = 0;
        int startIndex = 0;
        while (true) {
            startIndex = dna.indexOf("CTG", startIndex);
            if (startIndex == -1) {
                break;
            }
            startIndex = startIndex + 1;
            numOfCTG = numOfCTG + 1;
        }
        return numOfCTG;
    }
    public void processGenes(StorageResource sr) {
        int counter = 0;
        int cgRatioCounter = 0;
        int longestGeneLength = 0;
        String longestGene = "";
        float cgRatio = 0;
        System.out.println("There are " + sr.size() + " gene(s)");
        for (String s: sr.data()) {
            if (s.length() > longestGeneLength) {
                longestGeneLength = s.length();
                longestGene = s;
            }
            if (s.length() > 9) {
                System.out.println(s);
                counter = counter + 1;
            }
            cgRatio = cgRatio(s);
            if (cgRatio > 0.35) {
                System.out.println(s + "'s C-G-ratio is higher than 0.35");
                cgRatioCounter = cgRatioCounter + 1;
            }
        }
        System.out.println(counter + " of strings in sr is/are longer than 9 characters.");
        System.out.println(cgRatioCounter + " of strings in sr has a C-G-ratio higher than 0.35.");
        System.out.println("longest gene in sr is " + longestGene + ".");
        System.out.println("The length of the longest gene is " + longestGene.length() + ".");
        
    }
    public void testProcessGenes() {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        StorageResource sr = getAllGenes(dna);
        processGenes(sr);
        System.out.println("CTG appeared " + numOfCTG(dna));
    }
    public void testcgRatio() {
        System.out.println(cgRatio("ATGCCATAG"));
    }
    
    public void testGetAllGenes() {
        StorageResource sr = getAllGenes("AATGCTAACTAGCTGACTAAT");
        System.out.println("Testing printAllGenes on AATGCTAACTAGCTGACTAAT");
        for (String s: sr.data()) {
            System.out.println(s);
        }
        System.out.println("Size is " + sr.size());
        
        sr = getAllGenes("TAAATGAAATAAATGTATTAGATGGGGTTTTAG");
        System.out.println("Testing printAllGenes on TAAATGAAATAAATGTATTAGATGGGGTTTTAG");
        for (String s: sr.data()) {
            System.out.println(s);
        }
        System.out.println("Size is " + sr.size());
        
        
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        System.out.println("Testing printAllGenes on " + dna);
        sr = getAllGenes(dna);
        for (String s: sr.data()) {
            System.out.println(s);
        }
        System.out.println("Size is " + sr.size());
        
    }
    public void testOn(String dna) {
        System.out.println("Testing printAllGenes on " + dna);
        printAllGenes(dna);
    }
    public void test() {
        //testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        //testOn("");
        //testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
        testOn("AATGCTAACTAGCTGACTAAT");
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
        String dna1 = "AATGCTAACTAGCTGACTAAT";
        System.out.println(findGene(dna1, 0));
        //String dna2 = "TAAATGAAATAA";
        //System.out.println(findGene(dna2));
        //String dna3 = "TAAATGAAATAAATGTATTAG";
        //System.out.println(findGene(dna3));
        //String dna4 = "TAAATGAAATAAATGTATTAGATGGGGTTTTAG";
        //System.out.println(findGene(dna4));
        //String dna5 = "ATGTTT";
        //System.out.println(findGene(dna5));
        //String dna = "ATGCCCGGGAAATAACC";
        //String gene = findGene(dna, 0);
        //if (!gene.equals("ATGCCCGGGAAATAA")) {
        //    System.out.println("error");
        //}
        //System.out.println("tests finished");
    }
    public String mystery(String dna) {
      int pos = dna.indexOf("T");
      int count = 0;
      int startPos = 0;
      String newDna = "";
      if (pos == -1) {
        return dna;
      }
      while (count < 3) {
        count += 1;
        newDna = newDna + dna.substring(startPos,pos);
        startPos = pos+1;
        pos = dna.indexOf("T", startPos);
        if (pos == -1) {
          break;
        }
      }
      newDna = newDna + dna.substring(startPos);
      return newDna;
    }
    public void testMystery() {
        System.out.println(mystery("ATGCCCGGGAAATAATAATAA"));
    }
}
