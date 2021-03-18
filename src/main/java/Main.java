import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

import java.sql.SQLOutput;

public class Main {

    public static void proxyTest(int proxyIndex, boolean booleanTest){
        Proxy.changeProxy(proxyIndex);
        System.out.println(ProxyList.ipList.get(proxyIndex));
        HttpResponse<String> proxyTest = Unirest.get("http://httpbin.org/get").asString();
        System.out.println(proxyTest.getStatusText());
        if (proxyTest.getStatusText().equals("OK")){
            System.out.println(proxyTest.getBody());
            booleanTest=false;
        }
    }

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


        boolean proxyUsable = false;
        int proxyIndex = 0;

        for (String s : ProxyList.ipList){
            if (!proxyUsable){
                try {
                    Proxy.changeProxy(proxyIndex);
                    //1.print current ip.
                    System.out.println(ProxyList.ipList.get(proxyIndex));
                    //2.check if proxy works
                    HttpResponse<String> proxyTest = Unirest.get("http://httpbin.org/get").asString();
                    System.out.println(proxyTest.getStatusText());
                    //3.1 if yes then print the body of the page
                    if (proxyTest.getStatusText().equals("OK")){
                        System.out.println(proxyTest.getStatusText());
                        proxyUsable=true;
                    }
                    //3.2
                }catch( UnirestException e ) {
                    System.out.println("Unirest exception");
                    System.out.println(proxyIndex);
                    proxyIndex++;
                }
            }
        }



    }
}