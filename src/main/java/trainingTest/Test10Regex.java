package trainingTest;

/**
 * Created by Ежище on 01.07.2016.
 */
public class Test10Regex {
    public static void main(String[] args) {
        String str = "rd jhkjh, khjhv. jhik.,jb      , амшлж; выро овы ghj . шир\n grkm;adc: dckmdac , ihgjh . iihb\nohoj , " +
                "\"mashka\", \"cars.MashkaCar\", \"45\", \"600\", \"0.9\"";
        // Removes whitespace between a word character and . or ,
        String pattern = "(\\w)(\\s+)([\\.,])";
        System.out.println(str.replaceAll(pattern, "$1$3"));
    }
}
