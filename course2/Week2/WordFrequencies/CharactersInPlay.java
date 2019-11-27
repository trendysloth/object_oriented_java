import edu.duke.*;
import java.util.ArrayList;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> characterNames;
    private ArrayList<Integer> counts;
    public CharactersInPlay() {
        characterNames = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    private void update(String person) {
        person = person.toLowerCase();
        person = person.replaceAll("\\s", "");
        int indexOfPerson = characterNames.indexOf(person);
        if (indexOfPerson == -1){
            characterNames.add(person);
            counts.add(1);
        } else {
            int currCount = counts.get(indexOfPerson);
            counts.set(indexOfPerson, currCount + 1);
        }
    }
    private void findAllCharacter() {
        characterNames.clear();
        counts.clear();
        FileResource resource = new FileResource();
        for (String line: resource.lines()) {
            int periodIndex = line.indexOf(".");
            if (periodIndex != -1) {
                String possibleName = line.substring(0, periodIndex);
                update(possibleName);
            } 
        }
    }
    private void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < characterNames.size(); i ++) {
            int numOfCounts = counts.get(i);
            if (numOfCounts >= num1 && numOfCounts <= num2) {
                System.out.println(characterNames.get(i) + ": " + counts.get(i));
            }
        }
    }
    private int findMaxIndex() {
        int maxIndex = 0;
        int maxValue = 0;
        for (int i = 0; i < characterNames.size(); i ++) {
            int numOfCounts = counts.get(i);
            if (numOfCounts > maxValue) {
                maxIndex = i;
                maxValue = numOfCounts;
            }
        }
        return maxIndex;
    }
    public void tester() {
        findAllCharacter();
        //for (int i = 0; i < characterNames.size(); i ++) {
        //    System.out.println(characterNames.get(i) + ": " + counts.get(i));
        //}
        charactersWithNumParts(10, 15);
        int maxIndex = findMaxIndex();
        System.out.println("name that appeared the most is " + characterNames.get(maxIndex) + ": " + counts.get(maxIndex));
    }
}