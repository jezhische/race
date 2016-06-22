package trainingTest;

/**
 * Created by Ежище on 21.06.2016.
 */
public class ExceptionGetMessage {
    public static void main(String[] args) {
        try {
            System.out.println(String.valueOf(1/0));
//            System.out.println(0/0);
//            System.out.println(0*0);
//            System.out.println(Math.pow(-6,0.5));
//            System.out.println(String.valueOf(null));

        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
}
