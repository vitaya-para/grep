package grep;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchWordRegex extends SearchWord {

    SearchWordRegex(boolean ignore) {
        super(ignore);
    }

    @Override
    public boolean findWord(String line, String need) {
        Pattern pattern;
        if (caseIgnore) {
            pattern = Pattern.compile(need, Pattern.CASE_INSENSITIVE);
        } else {
            pattern = Pattern.compile(need);
        }
        Matcher out = pattern.matcher(line);
        return out.find();
    }
}
