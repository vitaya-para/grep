package grep;

abstract public class SearchWord {
    protected boolean caseIgnore;

    SearchWord(boolean ignore) {
        this.caseIgnore = ignore;
    }

    abstract public boolean findWord(String line, String need);
}
