public class Main {


    public static void main(String[] args){

        /*
        *******************************************************************************************************
        Proxy Crawler START
        *******************************************************************************************************
         */

        System.out.println("Proxy crawler - start" + "\n");
        //filepath to the file with non-filtered proxy list (copied page content with proxy list for example)
        String proxyToFilter = "src/main/resources/proxyToFilter.txt";
        //filepath to the list with filtered proxies from previous file(proxyToFilter.txt)
        String filteredProxy = "src/main/resources/filteredProxy.txt";

        //URL to scrap(checking ip)
        String URL = "https://la-conjugaison.nouvelobs.com";

        //Filtering proxy list from proxyToFilter.txt to filteredProxy.txt
        ProxyFilter filter = new ProxyFilter(proxyToFilter, filteredProxy);
        filter.bakeTheProxyFile();
        //Filling an Arraylist with proxies from filteredProxy.txt
        ProxyList.fillProxyList(filteredProxy);

        Proxy.findUsableProxy(URL); //Checking which first proxy is usable

        System.out.println("\nProxy crawler - end\n********************");
                /*
        *******************************************************************************************************
        Proxy Crawler END
        *******************************************************************************************************
         */



        /*
        *******************************************************************************************************
        Scraper START
        *******************************************************************************************************
         */

        //scraping example
        String verbsToScrap = "src/main/resources/Verbs.txt"; //list of verbs to scrap in txt file
        String scrappedVerbs = "src/main/resources/VerbsConjugated.txt"; //list of scrapped verbs
        String URLtoScrap = "https://la-conjugaison.nouvelobs.com/du/verbe/sampleVerb.php"; //"sampleVerb" in the end
        // is replacing by urlMaker with verbs from the list.

        ScraperFrancaisConjugation.addVerbsToList(verbsToScrap);
        ScraperFrancaisConjugation.urlMaker(URLtoScrap);
        ScraperFrancaisConjugation.urlScrapper(verbsToScrap,scrappedVerbs);

        /*
        *******************************************************************************************************
        Scraper END
        *******************************************************************************************************
         */
    }
}