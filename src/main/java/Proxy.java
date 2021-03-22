import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.jsoup.select.Evaluator;

public class Proxy {

    //Creating constructor
    public Proxy(String ip, int port) {}

    //Methods:
    //Changing current proxy
    public static void changeProxy(int index) {
        String ip = ProxyList.ipList.get(index);
        int port = ProxyList.portList.get(index);
        Unirest.config().proxy(ip, port);
    }

    //Resetting proxy to default
    public static void resetProxy() {
        Unirest.config().reset();
    }


    //finding first usable proxy.
    public static void findUsableProxy(String addressURL) {
        int proxyIndex = 0;

        for (String ip : ProxyList.ipList) {
            try {
                Proxy.resetProxy();
                Proxy.changeProxy(proxyIndex);
                System.out.println("Proxy index: " + proxyIndex);
                System.out.println("Ip: " + ProxyList.ipList.get(proxyIndex));
                System.out.println("Port: " + ProxyList.portList.get(proxyIndex));
                String URL = addressURL;
                //I don't know why but if I put addressURL directly
                //to "Unirest.get(URL)" instead of "URL", then it's not working.
                HttpResponse<String> proxyTest = Unirest.get(URL).asString();
                if (proxyTest.getStatusText().equals("OK")) {
                    System.out.println("Proxy status: " + proxyTest.getStatusText());
                    break;
                }
            } catch (UnirestException e) {
                /* e.printStackTrace(); */
                System.out.println("Proxy status: Unavailable");
                System.out.println("\n********************\n");
                proxyIndex++;
            }
        }
    }
}
