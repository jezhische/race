package trainingTest;

/**
 * Created by Ежище on 21.06.2016.
 */
public class ExGetMessage {
    public static void main(String[] args) {
        try {
            int i = 1/0;
            String ex = String.valueOf(i);
            System.out.println(ex);
            ex = String.valueOf(0/0);
            System.out.println(ex);
            ex = String.valueOf(0*0);
            System.out.println(ex);

        }
        catch (Exception ex){
            System.out.println("error in ex: " + ex.getMessage());
        }

    }
}
