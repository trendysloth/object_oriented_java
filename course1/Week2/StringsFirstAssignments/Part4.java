
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part4 {
    public void printURL(String url) {
        URLResource myurl = new URLResource(url);
        for(String word : myurl.words()) {
            if(word.toLowerCase().indexOf("youtube.com") != -1) {
                int quoteIndex = word.indexOf("\"");
                int lastQouteIndex = word.indexOf("\"", quoteIndex + 1);
                System.out.println(word.substring(quoteIndex+1, lastQouteIndex));
                
            }
        }
    }
    
    public void testing() {
        printURL("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    }
}
