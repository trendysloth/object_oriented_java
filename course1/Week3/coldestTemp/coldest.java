/**
 * Write a description of coldest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class coldest {
    public CSVRecord getSmallestOfTwo (CSVRecord currentRow, 
                                       CSVRecord smallestSoFar,
                                       String param) {
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        else {
            if (! currentRow.get(param).equals("N/A")) {
                double currentTemp = Double.parseDouble(currentRow.get(param));
                double smallestTemp = Double.parseDouble(smallestSoFar.get(param));
                if (currentTemp < smallestTemp) {
                    smallestSoFar = currentRow;
                }
            }
        }
        return smallestSoFar;
    }
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow : parser) {
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar, "TemperatureF");
        }
        return smallestSoFar;
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumiditySoFar = null;
        for (CSVRecord currentRow : parser) {
            lowestHumiditySoFar = getSmallestOfTwo(currentRow, lowestHumiditySoFar, "Humidity");
        }
        return lowestHumiditySoFar;
    }
    public CSVRecord lowestHumidityInManyDays() {
        CSVRecord lowestHumiditySoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestHumiditySoFar = getSmallestOfTwo(currentRow, lowestHumiditySoFar, "Humidity");
        }
        return lowestHumiditySoFar;
    }
    public CSVRecord coldestInManyDays() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar, "TemperatureF");
        }
        return smallestSoFar;
    }
    public String fileWithColdestTemperature() {
        CSVRecord coldestSoFar = null;
        String coldestFileName = null;
        String coldestMessage = null;
        FileResource coldestFR = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f:dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            if (coldestSoFar == null) {
                coldestSoFar = current;
            }
            else {
                double currentTem = Double.parseDouble(current.get("TemperatureF"));
                double coldestTem = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                
                if (currentTem != -9999.0 && currentTem < coldestTem) {
                    coldestSoFar = current;
                    coldestFileName = f.getName();
                    coldestFR = fr;
                }
            }
        }
        coldestMessage = "Coldest day was in file " + coldestFileName + "\r\n";
        coldestMessage = coldestMessage + "Coldest temperature on that day was " + 
                         coldestSoFar.get("TemperatureF") + "\r\n";
        coldestMessage = coldestMessage + "All the Temperatures on the coldest day were:" +
                         "\r\n";
        for (CSVRecord record : coldestFR.getCSVParser()) {
            String currentDate = record.get("DateUTC");
            String currentTemp = record.get("TemperatureF");
            coldestMessage = coldestMessage + currentDate + " " + currentTemp + "\r\n";
        }
        return coldestMessage;
    }
    public double averageTemperatureInFile(CSVParser parser) {
        double averageTemperature = 0.0;
        double totalTemperature = 0.0;
        int temperatureCount = 0;
        for (CSVRecord currentRow : parser) {
            totalTemperature = totalTemperature + 
                               Double.parseDouble(currentRow.get("TemperatureF"));
            temperatureCount = temperatureCount + 1;
        }
        averageTemperature = totalTemperature / temperatureCount;
        return averageTemperature;
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double averageTemperature = 0.0;
        double temperatureSum = 0.0;
        int temperatureCount = 0;
        double currentHumidity = 0;
        for (CSVRecord currentRow : parser) {
            if (currentRow.get("Humidity").equals("N/A")) {
                currentHumidity = -999;
            }
            currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            // System.out.println(currentHumidity);
            if (currentHumidity >= value) {
                temperatureSum = temperatureSum + Double.parseDouble(currentRow.get("TemperatureF"));
                temperatureCount = temperatureCount + 1;
            }
        }
        //System.out.println(temperatureSum);
        //System.out.println(temperatureCount);
        if (temperatureSum == 0.0) {
            return 0.0;
        }
        averageTemperature = temperatureSum / temperatureCount;
        return averageTemperature;
    }
    public void testColdestHourInFile() {
        FileResource fr = new FileResource("../nc_weather/2014/weather-2014-05-01.csv");
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + lowest.get("TemperatureF") +
                           " at " + lowest.get("DateUTC"));
    }
    public void testFileWithColdestTemperature() {
        String coldestMessage = fileWithColdestTemperature();
        System.out.println(coldestMessage);
    }
    public void testColdestHourInManyFile() {
        CSVRecord smallest = coldestInManyDays();
        System.out.println("Coldest temperature was " + smallest.get("TemperatureF") +
                           " at " + smallest.get("DateUTC"));
    }
    public void testlowestHumidityInFile() {
        FileResource fr = new FileResource("../nc_weather/2014/weather-2014-07-22.csv");
        CSVRecord lowest = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest humidity was " + lowest.get("Humidity") +
                           " at " + lowest.get("DateUTC"));
    }
    public void testlowestHumidityInManyFile() {
        CSVRecord lowest = lowestHumidityInManyDays();
        System.out.println("Lowest humidity was " + lowest.get("Humidity") +
                           " at " + lowest.get("DateUTC"));
    }
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource("../nc_weather/2013/weather-2013-08-10.csv");
        double averageTemperature = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + averageTemperature);
    }
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource("../nc_weather/2013/weather-2013-09-02.csv");
        double averageTemperature = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (averageTemperature == 0.0) {
            System.out.println("No temperatures with that humidity");
        }
        else {
            System.out.println("Average Temp when high Humidity is " + averageTemperature);
        }
    }
}
