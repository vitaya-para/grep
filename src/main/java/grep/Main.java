package grep;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Вариант 3 -- grep
Вывод на консоль указанного (в аргументе командной строки) текстового файла с
фильтрацией:
● word задаёт слово для поиска (на консоль выводятся только содержащие его
строки)
● -r (regex) вместо слова задаёт регулярное выражение для поиска (на консоль
выводятся только строки, содержащие данное выражение)
● -v инвертирует условие фильтрации (выводится только то, что ему НЕ
соответствует)
● -i игнорировать регистр слов
Command Line: grep [-v] [-i] [-r] word inputname.txt
Кроме самой программы, следует написать автоматические тесты к ней.
 */


public class Main {
    @Argument(metaVar = "word", required = true, index = 0, usage = "Pattern or word for finding it")
    private String word;

    @Argument(metaVar = "filename", required = true, index = 1, usage = "Way to the file")
    private String filename;

    @Option(name = "-r", usage = "regex find")
    private boolean isRegex;

    @Option(name = "-v", usage = "inverts the filter condition")
    private boolean reverse;

    @Option(name = "-i", usage = "ignore word case")
    private boolean caseIgnore;

    public static void main(String[] args) {
        new Main().parseArgs(args);
    }

    private void parseArgs(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            checkIllegalArgs(args);
            Finder finder = new Finder(isRegex, reverse, caseIgnore);
            System.out.print(String.join("\n", finder.start(word, filename)));
        } catch (CmdLineException ex) {
            System.err.println(ex.getMessage());
            parser.printUsage(System.err);
            System.err.println("Does not match the signature: grep [-v] [-i] [-r] word inputname.txt\n[<flag>] is optional");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void checkIllegalArgs(String[] args) throws CmdLineException {
        if (word == null || filename == null)
            throw new CmdLineException("invalid set of arguments");
    }
}
