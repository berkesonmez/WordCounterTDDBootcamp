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
    void getOutput_WhenInputHasNonUniqueWords_ShouldReturnSix() {
        int result = sut.countWords("This is a test and a good test.");
        assertEquals(6, result);
    }
    @Test
    void getOutput_WhenInputHasNonUniqueAndUppercaseWords_ShouldReturnSix() {
        int result = sut.countWords("This is a test and a good Test.");
        assertEquals(6, result);
    }

    @Test
    void getOutput_WhenInputHasApostrophes_ShouldReturnFour() {
        int result = sut.countWords("I'll be fine.");
        assertEquals(4, result);
    }

    @Test
    void getOutput_WhenInputHasMoreThanOneLinesAndUniqueWords_ShouldReturnFourteen() {
        int result = sut.countWords("This is a test and a good test.\nI'll be fine.\nMy name is Bond, James Bond.");
        assertEquals(14, result);
    }

    @Test
    void getOutput_WhenInputHasMoreThanOneLinesAndTabAndUniqueWords_ShouldReturnFourteen() {
        int result = sut.countWords("This is a test and a good test.\tI'll be fine.\nMy name is Bond, James Bond.");
        assertEquals(14, result);
    }

    @Test
    void getOutput_WhenInputHasMoreThanOneLinesAndNonUniqueWords_ShouldReturnTen() {
        int result = sut.countWords("This is a test and a good test.\nI'll be fine.\nI'll be fine.");
        assertEquals(10, result);
    }

    @RepeatedTest(10)
    void getOutput_WhenInputIsList_ShoudlReturn10() {
        List<String> sentences = new ArrayList<String>();
        sentences.add("She was too short to see over the fence.\n");
        sentences.add("The white water rafting trip was suddenly halted by the unexpected brick wall.\n");
        int result = sut.countWordsFromList(sentences);
        assertEquals(19, result);
    }

}