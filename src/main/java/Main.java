import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args){
        String filePath1 = "src/main/java/proxyRaw.txt";
        String filePath2 = "src/main/java/proxyBaked.txt";
        boolean proxyUsable = true;
        int proxyIndex = 0;


        ProxyBaker test = new ProxyBaker(filePath1, filePath2);
        test.bakeTheProxyFile();
        ProxyList.fillProxyList(filePath2);
        //for(String s : ProxyList.ipList){


        //*************************************************************************************
        //Stworzyć pętle, która jeżeli komputer połączy się z serwerem przez proxy to przejdzie dalej
        //a jeżeli wystąpi błąd to zmieni proxyIndex na następny i spróbuje ponownie.
        //*************************************************************************************

        //while (proxyUsable){
            Proxy.changeProxy(0);
            HttpResponse<String> proxyTest = Unirest.get("http://httpbin.org/get").asString();
            System.out.println(proxyTest.getBody());
       // }


       // }



    }

}
