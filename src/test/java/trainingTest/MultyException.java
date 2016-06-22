package trainingTest;

/**
 * Created by Ежище on 21.06.2016.
 */
public class MultyException {
    public String helloMessage(String name) throws Exception {
        if ("FIRST".equals(name)) {
            throw new Exception("FirstException occured");
        }
        if ("SECOND".equals(name)) {
            throw new Exception("SecondException occured");
        }
        return "Hello, " + name;
        //недописано - какие-то тут нестыковки...
    }
}
