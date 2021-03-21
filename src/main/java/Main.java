import kong.unirest.*;

public class Main {


    public static void main(String[] args){
        String filePath1 = "src/main/java/proxyRaw.txt";
        String filePath2 = "src/main/java/proxyBaked.txt";

        ProxyBaker test = new ProxyBaker(filePath1, filePath2);
        test.bakeTheProxyFile();
        ProxyList.fillProxyList(filePath2);

        String URL = "https://la-conjugaison.nouvelobs.com";
        Proxy.findUsableProxy(URL);

        ScraperFrancaisConjugation.addVerbsToList();
        ScraperFrancaisConjugation.urlMaker();
        ScraperFrancaisConjugation.urlScrapper();





    }
}