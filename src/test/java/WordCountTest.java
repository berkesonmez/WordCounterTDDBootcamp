import exception.MissingCheckerException;
import exception.MissingSentenceException;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class WordCountTest {

    static WordCount sut;
    @BeforeAll
    static void initObj() {
        sut = new WordCount();
        String[] whatToLookFor = {" ", ".", ",", "\'", "\n", "\t", ""};
        sut.setChecker(whatToLookFor);
    }


    @AfterEach
    void reset() {
        sut.resetCountAndOccurrenceWords();
    }

    @Test
    void getOutput_WhenInputIsNull_ShouldThrowMissingSentenceException() {
        Exception exception = assertThrows(MissingSentenceException.class, () -> sut.countWords(null));
        assertEquals("Sentence is null", exception.getMessage());
    }
    @Test
    void getOutput_WhenInputIsEmpty_ShouldThrowMissingSentenceException() {
        Exception exception = assertThrows(MissingSentenceException.class, () -> sut.countWords(""));
        assertEquals("Sentence is empty", exception.getMessage());
    }
    @Test
    void getOutput_WhenCheckerIsEmpty_ShouldThrowMissingSentenceException() {
        Exception exception = assertThrows(MissingCheckerException.class, () -> sut.setChecker(new String[]{}));
        assertEquals("Checker is empty", exception.getMessage());
    }
    @Test
    void getOutput_WhenCheckerIsNull_ShouldThrowMissingSentenceException() {
        Exception exception = assertThrows(MissingCheckerException.class, () -> sut.setChecker(null));
        assertEquals("Checker is null", exception.getMessage());
    }
    @Test
    void getOutput_WhenInputHasDot_ShouldReturn4() {
        int result = sut.countWords("This is a test.");
        assertEquals(4, result);
    }
    @Test
    void getOutput_WhenInputHasComma_ShouldReturn4() {
        int result = sut.countWords("This, is a test.");
        assertEquals(4, result);
    }
    @Test
    void getOutput_WhenInputHasNonUniqueWords_ShouldReturn6() {
        int result = sut.countWords("This is a test and a good test.");
        assertEquals(6, result);
    }
    @Test
    void getOutput_WhenInputHasNonUniqueAndUppercaseWords_ShouldReturn6() {
        int result = sut.countWords("This is a test and a good Test.");
        assertEquals(6, result);
    }

    @Test
    void getOutput_WhenInputHasApostrophes_ShouldReturn4() {
        int result = sut.countWords("I'll be fine.");
        assertEquals(4, result);
    }

    @Test
    void getOutput_WhenInputHasMoreThanOneLinesAndUniqueWords_ShouldReturn14() {
        int result = sut.countWords("This is a test and a good test.\nI'll be fine.\nMy name is Bond, James Bond.");
        assertEquals(14, result);
    }

    @Test
    void getOutput_WhenInputHasMoreThanOneLinesAndTabAndUniqueWords_ShouldReturn14() {
        int result = sut.countWords("This is a test and a good test.\tI'll be fine.\nMy name is Bond, James Bond.");
        assertEquals(14, result);
    }

    @Test
    void getOutput_WhenInputHasMoreThanOneLinesAndNonUniqueWords_ShouldReturn10() {
        int result = sut.countWords("This is a test and a good test.\nI'll be fine.\nI'll be fine.");
        assertEquals(10, result);
    }

    @RepeatedTest(10)
    void getOutput_WhenInputIsList_ShouldReturn19() {
        List<String> sentences = new ArrayList<>();
        sentences.add("She was too short to see over the fence.\n");
        sentences.add("The white water rafting trip was suddenly halted by the unexpected brick wall.\n");
        int result = sut.countWordsFromList(sentences);
        assertEquals(19, result);
    }

}