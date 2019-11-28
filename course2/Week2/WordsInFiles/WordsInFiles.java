import edu.duke.*;
import java.util.*;
import java.io.*; 
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    private HashMap<String, ArrayList> map;
    public WordsInFiles() {
        map = new HashMap<String, ArrayList>();
    }
    private void addWordsFromFile(File f) {
        FileResource r = new FileResource(f);
        for(String word : r.words()){
            if (map.containsKey(word)) {
                ArrayList<String> fileNameList = map.get(word);
                if (!fileNameList.contains(f.getName())){
                    fileNameList.add(f.getName());
                }
                map.put(word, fileNameList);
            } else {
                ArrayList<String> fileNameList = new ArrayList<String>();
                fileNameList.add(f.getName());
                map.put(word, fileNameList);
            }
        }
    }
    private void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    private int maxNumber() {
        int maxCount = 0;
        for(String word : map.keySet()){
            int value = map.get(word).size();
            if (value > maxCount){
                maxCount = value;
            }
        }
        return maxCount;
    }
    private ArrayList wordsInNumFiles(int number) {
        ArrayList<String> wordsInNumFiles = new ArrayList<String>();
        for(String word : map.keySet()){
            int value = map.get(word).size();
            if (value == number) {
                wordsInNumFiles.add(word);
            }
        }
        return wordsInNumFiles;
    }
    private void printFilesIn(String word) {
        ArrayList<String> wordInFiles = map.get(word);
        for (int i = 0; i < wordInFiles.size(); i++) {
            System.out.println(wordInFiles.get(i));
        }
    }
    public void tester() {
        buildWordFileMap();
        //printFilesIn("tree");
        //int maxCount = maxNumber();
        // System.out.println("max number of files is " + maxCount);
        ArrayList<String> words = wordsInNumFiles(4);
        //for (int i = 0; i < words.size(); i ++) {
            //System.out.println(words.get(i));
            // printFilesIn(words.get(i));
        //}
        System.out.println(words.size());
    }
}
