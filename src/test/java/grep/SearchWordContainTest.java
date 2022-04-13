package grep;

import com.mifmif.common.regex.Generex;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class SearchWordContainTest {
    private SearchWordContain search;
    private Generex generator;


    @Before
    public void before() {
        generator = new Generex("\\w+");
    }

    private void baseTest() {
        String line = generator.random(1,25);
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
