import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ScraperFrancaisConjugation {
    //how many tables from website do you want to copy
    static int numberOfTables = 4;

    //direct
    static String URL = "https://la-conjugaison.nouvelobs.com/du/verbe/sampleVerb.php";

    static ArrayList<String> verbsList = new ArrayList<>();
    static ArrayList<String> urlList = new ArrayList<>();

    public ScraperFrancaisConjugation() {
    }

    public static void addVerbsToList(String pathWithPlainVerbs) {
        File Verbs = new File(pathWithPlainVerbs);
        try {
            Scanner sc = new Scanner(Verbs);
            while (sc.hasNextLine()) {
                verbsList.add(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void urlMaker(String URLtoScrap) {
        for (String verb : verbsList) {
            String newURL = URLtoScrap.replace("sampleVerb", verb);
            urlList.add(newURL);
        }
    }

    public static void urlScrapper(String pathToVerbs, String pathToScrappedVerbs) {
        int count = 0;
        File scrappedVerbs = new File(pathToScrappedVerbs);
        File verbs = new File(pathToVerbs);
        try {
            Writer writer = new FileWriter(scrappedVerbs);
            Scanner scanner = new Scanner(verbs);

            for (String url : urlList) {
                HttpResponse<String> response = Unirest.get(urlList.get(count)).asString();
                Document toParse = Jsoup.parseBodyFragment(response.getBody());

                writer.write(scanner.nextLine() + "\n");

                for (int child = 7; child <= (7 + numberOfTables - 1); child++) {
                    for (Element result : toParse.select("#contenu > div:nth-child(" + child + ")")) {
                        String s1 = result.select("div").text();
                        writer.write(s1 + "\n");
                        //System.out.println(s1);
                    }
                }
                count++;
            }
            writer.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}