package trainingTest;

/**
 * Created by Ежище on 01.07.2016.
 */
public class Test10ARegex {
    public static void main(String[] args) {
        String str = "rd jhkjh, khjhv. jhik.,WWWjb      , амшлж; выро овы ghj . шир \n grkm;adc: WWWdckmdac , ihgjh . iihb \nohoj , " +
                "\"mashka\", \"cars.MashkaCar\", \"45\", \"600\", \"0.9\"";
        System.out.println(str + "\n");
        // Removes whitespace between a word character and . or ,
        String pattern = "(\\w)(\\s+)([\\.,])";
        System.out.println(str.replaceAll(pattern, "$1$3"));
        System.out.println("");

        // Extract the text between the two title elements
        pattern = "(?i)(<title.*?>)(.+?)(</title>)";
        String updated = str.replaceAll(pattern, "$2");
        System.out.println(updated); // ну - непонятно, что тут происходит
        System.out.println("");

        // a(?!b)  =  "a" if "a" is not followed by "b"
        pattern = "(W(?!d))";
        System.out.println(str.replaceAll(pattern, ""));
        System.out.println("");
        pattern = "(\\s+(?![вW.]))";
        System.out.println(str.replaceAll(pattern, ""));
        System.out.println("");


    }
}
