package grep;

import org.kohsuke.args4j.CmdLineException;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Finder {
    private final SearchWord lineFinder;
    private final boolean reverse;

    Finder(boolean isRegex, boolean reverse, boolean caseIgnore) {
        this.reverse = reverse;
        if (isRegex)
            lineFinder = new SearchWordRegex(caseIgnore);
        else
            lineFinder = new SearchWordContain(caseIgnore);
    }

    //for old test only
    public String start(String word, String filename)  throws IOException {
        return String.join("\n", this.start(new String[]{word, filename}));
    }

    public List<String> start(String[] params) throws IOException {
        List<String> out = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(params[1]));
            String line = reader.readLine();
            do {
                if (lineFinder.findWord(line, params[0]) ^ reverse)
                    out.add(line);
                line = reader.readLine();
            } while (line != null);
            reader.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        } catch (IOException e) {
            throw new IOException("IOException");
        }
        return out;
    }
}
