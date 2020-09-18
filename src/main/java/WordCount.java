import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class WordCount {
    private HashSet<String> occurrenceWords = new HashSet<String>();

    public HashSet<String> getChecker() {
        return checker;
    }

    public void setChecker(String[] array) {
        this.checker = new HashSet<String>(Arrays.asList(array));
    }

    private HashSet<String> checker;

    public void resetCountAndOccurrenceWords() {
        this.occurrenceWords.clear();
    }


    public int countWords(String s) {
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


//            if(!(Character.isWhitespace(checkChar) || checkChar == '.' || checkChar == ',' || checkChar == '\'' || checkChar == '\n' || checkChar == '\t')) {
//                word += checkChar;
//            } else {
//                if(!WordCount.this.occurrenceWords.contains(word) && !word.equals("")) {
//                    WordCount.this.occurrenceWords.add(word);
//                }
//                word = "";
//            }
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
