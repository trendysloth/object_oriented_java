/**
 * Print out the names for which 100 or fewer babies were born in a chosen CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                                   " Gender " + rec.get(1) +
                                   " Num Born " + rec.get(2));
            }
        }
    }
    public void totalBirths(CSVParser parser) {
        int totalboy = 0;
        int totalgirl = 0;
        int total = 0;
        int totaluniqueboy = 0;
        int totaluniquegirl = 0;
        int totalunique = 0;
        for (CSVRecord record: parser) {
            if (record.get(1).equals("F")) {
                total = total + 1;
                totalgirl = totalgirl + Integer.parseInt(record.get(2));
                totaluniquegirl = totaluniquegirl + 1;
                totalunique = totalunique + 1;
            }
            if (record.get(1).equals("M")) {
                total = total + 1;
                totalboy = totalboy + Integer.parseInt(record.get(2));
                totaluniqueboy = totaluniqueboy + 1;
                totalunique = totalunique + 1;
            }
            
        }
        System.out.println("Total number boy: "+totalboy);
        System.out.println("Total number girl: "+totalgirl);
        System.out.println("Total number: "+total);
        System.out.println("Total unique number boy: "+totaluniqueboy);
        System.out.println("Total unique number girl: "+totaluniquegirl);
        System.out.println("Total unique number: "+totalunique);
    }
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        totalBirths(parser);
    }
    public int getRank(int year, String name, String gender) {
        int rank = 0;
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob1960.csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String currName = rec.get(0);
            String currGender = rec.get(1);
            if (currGender.equals(gender)) {
                rank = rank + 1;
            }
            if (currName.equals(name) && currGender.equals(gender)) {
                return rank;
            }
        }
        return -1;
    }
    public void testGetRank() {
        System.out.println(getRank(1960, "Emily", "F"));
    }
    public String getName(int year, int rank, String gender) {
        String fileLocation = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fileLocation);
        int rankCounter = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String currGender = rec.get(1);
            if (currGender.equals(gender)) {
                rankCounter += 1;
                if (rankCounter == rank) {
                    String currName = rec.get(0);
                    return currName;
                }
            }
        }
        return "NO NAME";
    }
    public void testGetName() {
        String name = getName(1982, 450, "M");
        System.out.println(name);
    }
    public String whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = 0;
        int rankCounter = 0;
        String fileLocation = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fileLocation);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String currName = rec.get(0);
            String currGender = rec.get(1);
            if (currGender.equals(gender)) {
                rankCounter += 1;
                // System.out.println(currName + " " + rankCounter);
                if (currName.equals(name)) {
                    rank = rankCounter;
                }
            }
        }
        // System.out.println(rank);
        String fileLocationNew = "us_babynames/us_babynames_by_year/yob" + newYear + ".csv";
        FileResource frNew = new FileResource(fileLocationNew);
        rankCounter = 0;
        String newName = "";
        for (CSVRecord rec : frNew.getCSVParser(false)) {
            String currName = rec.get(0);
            String currGender = rec.get(1);
            if (currGender.equals(gender)) {
                rankCounter += 1;
                if (rankCounter == rank) {
                    newName = currName;
                }
            }
        }
        String pronoun = "";
        if (gender.equals("F")) {
            pronoun = "she";
        } else {
            pronoun = "he";
        }
        String res = name + " born in " + year + " would be " + newName + " if " +
                     pronoun + " was born in " + newYear + ".";
        return res;
    }
    public void testWhatIsNameInYear() {
        String res = whatIsNameInYear("Owen", 1974, 2014, "M");
        System.out.println(res);
    }
    public String yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestRank = -1;
        String highestRankFile = null;
        // int yearOfHigestRank = 0;
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int rankCounter = 0;
            for (CSVRecord rec : fr.getCSVParser(false)) {
                String currName = rec.get(0);
                String currGender = rec.get(1);
                if (currGender.equals(gender)) {
                    rankCounter += 1;
                }
                if (currName.equals(name)) {
                    int currRank = rankCounter;
                    if (highestRank == -1) {
                        highestRank = currRank;
                        highestRankFile = f.getName();
                    }
                    if (currRank < highestRank) {
                        highestRank = currRank;
                        highestRankFile = f.getName();
                    }
                }
            }
        }
        return highestRankFile;
    }
    public void testYearOfHighestRank() {
        System.out.println(yearOfHighestRank("Mich", "M"));
    }
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double sumRank = 0.0;
        int numOfMatches = 0;
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int rankCounter = 0;
            for (CSVRecord rec : fr.getCSVParser(false)) {
                String currName = rec.get(0);
                String currGender = rec.get(1);
                if (currGender.equals(gender)) {
                    rankCounter += 1;
                    if (currName.equals(name)) {
                        sumRank += rankCounter;
                        numOfMatches += 1;
                    }
                }
            }
        }
        return sumRank / numOfMatches;
    }
    public void testGetAverageRank() {
        // System.out.println(getAverageRank("Mason", "M"));
        System.out.println(getAverageRank("Susan", "F"));
    }
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        String fileLocation = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fileLocation);
        int totalBirthsRankedHigher = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int rankCounter = 0;
            String currGender = rec.get(1);
            if (currGender.equals(gender)) {
                rankCounter += 1;
                String currName = rec.get(0);
                int currCount = Integer.parseInt(rec.get(2));
                totalBirthsRankedHigher += currCount;
                if (currName.equals(name)) {
                    totalBirthsRankedHigher -= currCount;
                    break;
                }
            }
        }
        return totalBirthsRankedHigher;
    }
    public void testGetTotalBirthRankedHigher() {
        System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
}
