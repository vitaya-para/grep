package grep;


import com.mifmif.common.regex.Generex;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SearchWordContainRandomTest {
    private SearchWordContain search;
    private Generex generator;
    private Generex generatorCAPSONLY;


    @Before
    public void before() {
        generator = new Generex("\\w+");
        generatorCAPSONLY = new Generex("[A-Z]+");
    }

    private void baseTest() {
        generator = new Generex("[a-z]+");
        String line = generator.random(1, 20);
        assertTrue(search.findWord(line, line));
        assertTrue(search.findWord(line + generator.random(5,20), line));
        assertFalse(search.findWord(line, line + generator.random(5,20)));
        assertFalse(search.findWord(generator.random(5,20), generator.random(21,30)));

        generator = new Generex("[A-Z]+");
    }

    @Test
    public void testWithIgnoreCase() {
        search = new SearchWordContain(true);
        this.baseTest();
        String line = generator.random(5,25);
        assertTrue(search.findWord(line, line.toLowerCase(Locale.ROOT)));
        line = generatorCAPSONLY.random(5,25);
        assertTrue(search.findWord(line, line.toLowerCase(Locale.ROOT)));
    }

    @Test
    public void testWithoutIgnoreCase() {
        search = new SearchWordContain(false);
        this.baseTest();
        String line = generator.random(5,25);
        assertFalse(search.findWord(line, line.toLowerCase(Locale.ROOT)));
        line = generatorCAPSONLY.random(5,25);
        assertFalse(search.findWord(line, line.toLowerCase(Locale.ROOT)));
    }
}
