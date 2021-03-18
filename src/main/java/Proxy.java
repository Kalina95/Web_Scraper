import kong.unirest.Unirest;

public class Proxy {

    public Proxy (){}

    public Proxy (String ip, int port){
    }

    public static void changeProxy (int index){
        String ip = ProxyList.ipList.get(index);
        int port = ProxyList.portList.get(index);
        Unirest.config().proxy(ip, port);
    }
}
