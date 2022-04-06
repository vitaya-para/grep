package grep;

public class SearchWordContain extends SearchWord {

    SearchWordContain(boolean ignore) {
        super(ignore);
    }

    @Override
    public boolean findWord(String line, String need) {
        String copyNeed, copyLine;
        if (caseIgnore) {
            copyNeed = need.toLowerCase();
            copyLine = line.toLowerCase();
        } else {
            copyLine = line;
            copyNeed = need;
        }
        return copyLine.contains(copyNeed);
    }
}
