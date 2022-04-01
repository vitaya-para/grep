package grep;

import org.junit.Test;


public class MainTest {
    @Test
    public void flagTest() {
        String[] param = {"-r", "-v", "-i", "word", "txt/test1.txt"};
        Main.main(param);
    }

    @Test
    public void emptyKeyword() {
        String[] param = {"txt/test1.txt"};
        Main.main(param);
    }
}
