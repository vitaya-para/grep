package grep;

import org.kohsuke.args4j.CmdLineException;

import java.io.*;

public class Finder {
    private final SearchWord lineFinder;
    private final boolean reverse;

    Finder(boolean isRegex, boolean reverse, boolean caseIgnore) {
        this.reverse = reverse;
        if (isRegex) {
            lineFinder = new SearchWordRegex(caseIgnore);
        } else {
            lineFinder = new SearchWordContain(caseIgnore);
        }
    }

    public String start(String word, String filename) throws CmdLineException {
        StringBuffer out = new StringBuffer();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            do {
                if (lineFinder.findWord(line, word) ^ reverse) {
                    out.append(line);
                    out.append('\n');
                }
                line = reader.readLine();
            } while (line != null);
            reader.close();
        } catch (FileNotFoundException e) {
            throw new CmdLineException("File not found");
        } catch (IOException e) {
            throw new CmdLineException("IOException");
        }
        return out.toString();
    }
}
