import org.apache.commons.validator.routines.InetAddressValidator;

import java.io.*;
import java.util.Scanner;

public class ProxyFilter {

    public String file1 = "";
    public String file2 = "";

    public ProxyFilter(){}

    public ProxyFilter(String s1, String s2){
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
            int count = 0;
            while (sc.hasNext()){
                String ip = sc.next();
                if ( checkIsIpValid.isValid(ip) ){
                    if(count==0){
                        printer.write(ip + " " + sc.next());
                        count++;
                    }else{
                        printer.write("\n" + ip + " " + sc.next());
                        count++;
                    }
                }
            }
            sc.close();
            printer.close();
            //System.out.println(count);

        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
