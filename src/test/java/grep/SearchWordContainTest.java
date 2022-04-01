package grep;

import org.junit.Before;
import org.junit.Test;
import net.moznion.random.string.RandomStringGenerator;

import static org.junit.Assert.*;


public class SearchWordContainTest {
    private SearchWordContain search;
    private RandomStringGenerator generator;


    @Before
    public void before() {
        generator = new RandomStringGenerator();
        generator.setNumOfUpperLimit(10);
    }

    private void baseTest() {
        String line = generator.generateByRegex("\\w+");
        assertTrue(search.findWord(line, line));
        assertTrue(search.findWord(line + "3", line));
        assertFalse(search.findWord(line, line + "3"));
        assertFalse(search.findWord("3", "word"));
    }

    @Test
    public void testWithIgnoreCase() {
        search = new SearchWordContain(true);
        this.baseTest();
        assertTrue(search.findWord("AbCdEfG", "abcdefg"));
        assertTrue(search.findWord("WORD", "word"));
    }

    @Test
    public void testWithoutIgnoreCase() {
        search = new SearchWordContain(false);
        this.baseTest();
        assertFalse(search.findWord("AbCdEfG", "abcdefg"));
        assertFalse(search.findWord("WORD", "word"));
    }

}
