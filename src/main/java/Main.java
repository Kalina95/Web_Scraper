import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args){
        String filePath1 = "src/main/java/proxyRaw.txt";
        String filePath2 = "src/main/java/proxyBaked.txt";


        ProxyBaker test = new ProxyBaker(filePath1, filePath2);
        test.bakeTheProxyFile();

        System.out.println("test");
        System.out.println("test2");
        System.out.println("test3");
    }

}
