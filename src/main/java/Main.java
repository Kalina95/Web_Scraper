import kong.unirest.*;
import org.apache.http.conn.ConnectTimeoutException;

import java.net.SocketTimeoutException;
import java.sql.SQLOutput;

public class Main {


    public static void main(String[] args){
        String filePath1 = "src/main/java/proxyRaw.txt";
        String filePath2 = "src/main/java/proxyBaked.txt";




        ProxyBaker test = new ProxyBaker(filePath1, filePath2);
        test.bakeTheProxyFile();
        ProxyList.fillProxyList(filePath2);
        //for(String s : ProxyList.ipList){


        //*************************************************************************************
        //Stworzyć pętle, która jeżeli komputer połączy się z serwerem przez proxy to przejdzie dalej
        //a jeżeli wystąpi błąd to zmieni proxyIndex na następny i spróbuje ponownie.
        //*************************************************************************************

        //while (proxyUsable){

       // }

        
        int proxyIndex = 0;

        for (String s : ProxyList.ipList){
            try {
                Proxy.resetProxy();
                Proxy.changeProxy(proxyIndex);
                System.out.println("Proxy index: " + proxyIndex);
                //1.print current ip.
                System.out.println("Ip: " + ProxyList.ipList.get(proxyIndex));
                System.out.println("Port: " + ProxyList.portList.get(proxyIndex));
                //2.check if proxy works
                HttpResponse<String> proxyTest = Unirest.get("http://httpbin.org/get").asString();
                //3.1 if yes then print the body of the page
                if (proxyTest.getStatusText().equals("OK")){
                    System.out.println("Proxy status: " + proxyTest.getStatusText());
                    break;
                }
                //3.2
            } catch( UnirestException e ) {
                //e.printStackTrace();
                System.out.println("Proxy status: Unavailable");
                System.out.println("\n********************\n");
                proxyIndex++;
            }
        }



    }
}