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
//        try {
//            directSegmentTime = spacing / initialSpeed;
//            terminalSpeed = initialSpeed;
//            if (initialSpeed == 0) {
//                throw new Exception("Автомобиль остался на старте. Разбудите водителя!");
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        // вот так, через throw new Exception и e.getMessage() проще всего получить распечатку нужного сообщения.
    }
}
