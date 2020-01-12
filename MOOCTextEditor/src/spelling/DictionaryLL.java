package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
    // TODO: Add a constructor
	public DictionaryLL() {
		this.dict = new LinkedList<String>();
	}

    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	// TODO: Implement this method
    	String word1 = word.toLowerCase();
//    	System.out.println(word);
    	if (this.dict.contains(word1) && this.dict.size() != 0) {
    		return true;
    	} else if (this.dict.size() == 0) {
    		this.dict.add(word1);
    		return true;
    	} else {
    		this.dict.add(word1);
    		return false;
    	}
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        // TODO: Implement this method
        return this.dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        //TODO: Implement this method
    	String sLowerCase = s.toLowerCase();
        return this.dict.contains(sLowerCase);
    }

    
}
