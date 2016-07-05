package trainingTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ежище on 03.07.2016.
 */
public class Test10BRegex {
    public static final String EXAMPLE_TEST = "This is my small example "
            + "string which I'm going to " + "use for pattern matching.";
    public static final String myExample = "\"ferrfurr\", \"cars.FerraryCar\", \"22\", \"300\", \"0.3\"";

    public static void main(String[] args) {
        System.out.println(EXAMPLE_TEST.matches("\\w.*"));
        String[] splitString = (EXAMPLE_TEST.split("\\s+"));
        System.out.println(splitString.length);// should be 14
        for (String string : splitString) {
            System.out.println(string);
        }
        // replace all whitespace with tabs
        System.out.println(EXAMPLE_TEST.replaceAll("\\s+", "\t"));

        System.out.println(myExample);
//        Pattern wordsPattern = Pattern.compile("\\w+");
//        Pattern digitsPattern = Pattern.compile("\\d+(\\.\\d+)?");
//        Matcher wordsMatcher = wordsPattern.matcher(myExample);
//        Matcher digitsMatcher = digitsPattern.matcher(myExample);
//        while (wordsMatcher.find()) {
//            System.out.println(wordsMatcher.group());
//        }
//        while (digitsMatcher.find()) {
//            System.out.println(digitsMatcher.group());
//        }

//        Pattern insideQuotesPattern = Pattern.compile("(\")(\\w+(.\\w+)?)(\")");
//        Matcher insideQuotesMatcher = insideQuotesPattern.matcher(myExample);
//        while (insideQuotesMatcher.find()) {
//            System.out.println(insideQuotesMatcher.group());
//        }
//  П О Л Е З Н Ы Е     И З Д Е Л И Я :
//        // regex для того, чтобы убрать кавычки и оставить только слова или группы "слово.слово":
//        String insideQuotesPattern = "(\")(\\w+(.\\w+)?)(\")";
//        System.out.println(myExample.replaceAll(insideQuotesPattern, "$2"));

//        // regex для того, чтобы выцепить числа double и удобно друг за другом поместить их в коллекцию:
//        Pattern digitsPattern = Pattern.compile("\\d+(\\.\\d+)?");
//        Matcher digitsMatcher = digitsPattern.matcher(myExample);
//        while (digitsMatcher.find()) {
//            System.out.println(digitsMatcher.group());
//        }

        // regex для того, чтобы выцепить только слова или числа или группы "слово.слово" или группы "число.число",
        // чтобы удобно друг за другом поместить их в коллекцию:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        -поместить их в коллекцию:
        Pattern groupWordsPattern = Pattern.compile("\\w+(.\\w+)?");
        Matcher groupWordsMatcher = groupWordsPattern.matcher(myExample);
        while (groupWordsMatcher.find()) {
            System.out.println(groupWordsMatcher.group());
        }



//        // check all occurance
//        Pattern pattern = Pattern.compile("\\w+");
//        // in case you would like to ignore case sensitivity,
//        // you could use this statement:
//        // Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(myExample);
//        while (matcher.find()) {
//            System.out.print("Start index: " + matcher.start());
//            System.out.print(" End index: " + matcher.end() + " ");
//            System.out.println(matcher.group());
//            //For a matcher m with input sequence s, the expressions m.group() and s.substring(m.start(), m.end()) are equivalent.
//            //Returns:
//           // The (possibly empty) subsequence matched by the previous match, in string form
//           // Throws:
//            //.IllegalStateException - If no match has yet been attempted, or if the previous match operation failed
//        }
//        // now create a new pattern and matcher to replace whitespace with tabs
//        Pattern replace = Pattern.compile("\\s+");
//        Matcher matcher2 = replace.matcher(myExample);
//        System.out.println(matcher2.replaceAll("\t"));
    }
}