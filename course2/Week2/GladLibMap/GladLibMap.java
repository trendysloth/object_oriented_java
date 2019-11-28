import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    private Random myRandom;
    private HashMap<String, ArrayList> map;
    private ArrayList<String> categories;
    public GladLibMap(){
        map = new HashMap<String, ArrayList>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        categories = new ArrayList<String>();
    }
    public GladLibMap(String source){
        map = new HashMap<String, ArrayList>();
        initializeFromSource(source);
        myRandom = new Random();
        categories = new ArrayList<String>();
    }
    private void initializeFromSource(String source) {
        map.put("adjective", readIt(source+"/adjective.txt")); 
        map.put("noun", readIt(source+"/noun.txt"));
        map.put("color", readIt(source+"/color.txt"));
        map.put("country", readIt(source+"/country.txt"));
        map.put("name", readIt(source+"/name.txt"));      
        map.put("animal", readIt(source+"/animal.txt"));
        map.put("timeframe", readIt(source+"/timeframe.txt"));
    }
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        } else if (map.containsKey(label)){
            if (!categories.contains(label)) {
                categories.add(label);
            }
            return randomFrom(map.get(label));
        }
        return "**UNKNOWN**";
    }
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        return prefix + sub + suffix;
    }
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    private int totalWordsInMap() {
        int totalWords = 0;
        for(String word : map.keySet()){
            int currWords = map.get(word).size();
            totalWords += currWords;
        }
        return totalWords;
    }
    private int totalWordsConsidered() {
        int totalWordsConsidered = 0;
        for (int i = 0; i < categories.size(); i++) {
            String currCategory = categories.get(i);
            int currWordsConsidered = map.get(currCategory).size();
            totalWordsConsidered += currWordsConsidered;
        }
        return totalWordsConsidered;
    }
    public void makeStory(){
        //System.out.println("\n");
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story,60);
        int totalWords = totalWordsInMap();
        System.out.println("\nTotal number of words that were possible to pick from was " + totalWords + "."); 
        int totalWordsConsidered = totalWordsConsidered();
        System.out.println("Total number of words that were considered was " + totalWordsConsidered + "."); 
    }
}
