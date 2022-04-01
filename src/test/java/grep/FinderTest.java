package grep;

import org.junit.Test;
import org.kohsuke.args4j.CmdLineException;

import static org.junit.Assert.assertEquals;

    /*
r ! A
0 0 0
0 0 1
0 1 0
0 1 1
1 0 0
1 0 1
1 1 0
1 1 1
     */

public class FinderTest {

    private Finder finder;

    private void generateFinder(int a) {
        finder = new Finder((a & 4) == 4, (a & 2) == 2, (a & 1) == 1);
    }

    @Test
    public void noFlags() throws CmdLineException {
        this.generateFinder(0);
        String res = "word 1\nword 2\nword 4\n";
        assertEquals(res, finder.start("word", "txt/test1.txt"));
        assertEquals("", finder.start("word", "txt/test2.txt"));
    }

    @Test
    public void caseIgnore() throws CmdLineException {
        this.generateFinder(1);
        String res = "Word 1\nWord 2\nWord 4\n";
        assertEquals(res.toLowerCase(), finder.start("word", "txt/test1.txt"));
        assertEquals(res, finder.start("word", "txt/test2.txt"));
    }

    @Test
    public void reverse() throws CmdLineException {
        this.generateFinder(2);
        String res = "3\n5\n6\n7\n";
        assertEquals(res, finder.start("word", "txt/test1.txt"));
        res = "Word 1\nWord 2\n3\nWord 4\n5\n6\n7\n";
        assertEquals(res, finder.start("word", "txt/test2.txt"));
    }

    @Test
    public void reverseCaseIgnore() throws CmdLineException {
        this.generateFinder(3);
        String res = "3\n5\n6\n7\n";
        assertEquals(res, finder.start("word", "txt/test1.txt"));
        assertEquals(res, finder.start("word", "txt/test2.txt"));
    }

    @Test
    public void regex() throws CmdLineException {
        this.generateFinder(4);
        String res = "asdfg\nlkjiop123\n";
        assertEquals(res, finder.start("^[a-z]+", "txt/test3.txt"));
        assertEquals("", finder.start("^[a-z]+", "txt/test4.txt"));
    }

    @Test
    public void regexCaseIgnore() throws CmdLineException {
        this.generateFinder(5);
        String res = "Asdfg\nLkjiop123\n";
        assertEquals(res.toLowerCase(), finder.start("^[a-z]+", "txt/test3.txt"));
        assertEquals(res, finder.start("^[a-z]+", "txt/test4.txt"));
    }

    @Test
    public void regexReverse() throws CmdLineException {
        this.generateFinder(6);
        String res = "123453\n!!!!\n";
        assertEquals(res, finder.start("^[a-z]+", "txt/test3.txt"));
        res = "Asdfg\n123453\nLkjiop123\n!!!!\n";
        assertEquals(res, finder.start("^[a-z]+", "txt/test4.txt"));
    }

    @Test
    public void regexReverseCaseIgnore() throws CmdLineException {
        this.generateFinder(7);
        String res = "123453\n!!!!\n";
        assertEquals(res, finder.start("^[a-z]+", "txt/test3.txt"));
        assertEquals(res, finder.start("^[a-z]+", "txt/test4.txt"));
    }
}
