package trainingTest;

import java.util.Arrays;

/**
 * Created by Ежище on 21.06.2016.
 */
public class ToString {
    public static void main(String[] args) {
        // Test 1
        Integer a = 1;

        // Test 2, Test 3, Test 4
        Integer [] b = new Integer [3];
        b[0] = 1;   b[1] = 1;   b[2] = 1;

        // Test 5, Test 6
        Integer [][] c = new Integer [2][3];
        c[0][0] = 1;    c[0][1] = 2;    c[0][2] = 3;
        c[1][0] = 4;    c[1][1] = 5;    c[1][2] = 6;

        // Test 7
        String [][] d = new String [2][3];
        d[0][0] = "1";  d[0][1] = "2";  d[0][2] = "3";
        d[1][0] = "4";  d[1][1] = "5";  d[1][2] = "6";

        // Test 8
        String[][] e = { {"X", "O", "O"},
                {"O", "X", "X"},
                {"X", "O", "X"}};

        System.out.println("Test 1 | a");
        System.out.println(a);

        System.out.println("\nTest 2 | b[]");
        System.out.println(b);

        System.out.println("\nTest 3 | b[].toString");
        System.out.println(b.toString()); //NOTE: object.toString()

        System.out.println("\nTest 4 | Arrays.deepToString(b[])");
        System.out.println(Arrays.deepToString(b));

        System.out.println("\nTest 5 | c[][]");
        System.out.println(c);

        System.out.println("\nTest 6 | Arrays.deepTostring(c[][])");
        System.out.println(Arrays.deepToString(c));

        System.out.println("\nTest 7 | Arrays.deepTostring(d[][])");
        System.out.println(Arrays.deepToString(d));

        System.out.println("\nTest 8 | Arrays.deepTostring(e[][])");
        System.out.println(Arrays.deepToString(e));

    }
}
