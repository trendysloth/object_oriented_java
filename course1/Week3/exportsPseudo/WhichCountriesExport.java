/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public String countryInfo(CSVParser parser, String country) {
        String countryInformation = "";
        for (CSVRecord record : parser) {
            String countryName = record.get("Country");
            if (countryName.equals(country)) {
                countryInformation = countryInformation + country + ": ";
                String exports = record.get("Exports");
                countryInformation = countryInformation + exports + ": ";
                String value = record.get("Value (dollars)");
                countryInformation = countryInformation + value;
            }
        }
        if (countryInformation.length() != 0) {
            return countryInformation;
        } else {
            return "NOT FOUND";
        }
    }
    public void listExportersTwoProducts(CSVParser parser, 
                                         String exportItem1, 
                                         String exportItem2) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int numOfExporters = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                numOfExporters = numOfExporters + 1;
            }
        }
        return numOfExporters;
    }
    public void bigExporters(CSVParser parser, String value) {
        for (CSVRecord record : parser) {
            String export = record.get("Value (dollars)");
            if (export.length() > value.length()) {
                String country = record.get("Country");
                System.out.print(country + " ");
                System.out.println(export);
            }
        }
    }
    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //listExporters(parser, "coffee");
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //String countryInformation = countryInfo(parser, "Nauru");
        //System.out.println(countryInformation);
        //int numOfExporters = numberOfExporters(parser, "cocoa");
        //System.out.println(numOfExporters);
        bigExporters(parser, "$999,999,999,999");
    }
    
}
