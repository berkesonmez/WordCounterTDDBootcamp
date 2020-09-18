import exception.MissingCheckerException;
import exception.MissingSentenceException;

import java.util.*;

public class WordCount {
    private HashSet<String> occurrenceWords = new HashSet<String>();
    private HashSet<String> checker;

    public HashSet<String> getChecker() {
        return checker;
    }

    public void setChecker(String[] array) {
        if (array == null) {
            throw new MissingCheckerException("Checker is null");
        }
        if (array.length == 0) {
            throw new MissingCheckerException("Checker is empty");
        }
        this.checker = new HashSet<String>(Arrays.asList(array));
    }


    public void resetCountAndOccurrenceWords() {
        this.occurrenceWords.clear();
    }


    public int countWords(String s) {

        if(s == null) {
            throw new MissingSentenceException("Sentence is null");
        }
        if(s.length() == 0) {
            throw new MissingSentenceException("Sentence is empty");
        }

        String word = "";

        for(int i = 0; i < s.length(); i++) {
            char checkChar = Character.toLowerCase(s.charAt(i));

            if (getChecker().contains(Character.toString(checkChar))) {
                if(!WordCount.this.occurrenceWords.contains(word) && !word.equals("")) {
                    WordCount.this.occurrenceWords.add(word);
                }
                word = "";
            } else {
                word += checkChar;
            }

        }
        System.out.println(occurrenceWords);
        return this.occurrenceWords.size();
    }

    public int countWordsFromList(List<String> listOfWords) {
        for (String word: listOfWords) {
            this.countWords(word);
        }
        return this.occurrenceWords.size();

    }

}
