import org.apache.commons.validator.routines.InetAddressValidator;

import java.io.*;
import java.util.Scanner;

public class ProxyBaker {

    String file1 = "";
    String file2 = "";

    public ProxyBaker(){}

    public ProxyBaker(String s1, String s2){
        file1 = s1;
        file2 = s2;
    }

    public void bakeTheProxyFile(){
        File proxyToBeBake = new File(file1);
        File bakedProxyList = new File(file2);

        try {
            Scanner sc = new Scanner(proxyToBeBake);
            InetAddressValidator checkIsIpValid = new InetAddressValidator();
            BufferedWriter printer = new BufferedWriter(new FileWriter(bakedProxyList));

            while (sc.hasNext()){
                String ip = sc.next();
                if ( checkIsIpValid.isValid(ip) ){
                    printer.write(ip + " " + sc.next());
                    printer.newLine();
                }
            }
            sc.close();
            printer.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
