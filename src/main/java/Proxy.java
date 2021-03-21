import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

public class Proxy {

    static int workingProxy;

    public Proxy() {
    }

    public Proxy(String ip, int port) {
    }

    public static void changeProxy(int index) {
        String ip = ProxyList.ipList.get(index);
        int port = ProxyList.portList.get(index);
        Unirest.config().proxy(ip, port);
    }

    public static void resetProxy() {
        Unirest.config().reset();
    }

    public static void findUsableProxy(String addressURL) {
        int proxyIndex = 0;

        for (String s : ProxyList.ipList) {
            try {
                Proxy.resetProxy();
                Proxy.changeProxy(proxyIndex);
                System.out.println("Proxy index: " + proxyIndex);
                //1.print current ip.
                System.out.println("Ip: " + ProxyList.ipList.get(proxyIndex));
                System.out.println("Port: " + ProxyList.portList.get(proxyIndex));
                //2.check if proxy works
                String URL = addressURL;
                HttpResponse<String> proxyTest = Unirest.get(URL).asString();
                //3.1 if yes then print the body of the page
                if (proxyTest.getStatusText().equals("OK")) {
                    System.out.println("Proxy status: " + proxyTest.getStatusText());
                    break;
                }
                //3.2
            } catch (UnirestException e) {
                //e.printStackTrace();
                System.out.println("Proxy status: Unavailable");
                System.out.println("\n********************\n");
                proxyIndex++;
            }
        }
    }
}
