import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ScraperFrancaisConjugation {

    static String URL = "https://la-conjugaison.nouvelobs.com/du/verbe/sampleVerb.php";
    //static String URL = "https://la-conjugaison.nouvelobs.com/rechercher/index.php?l=fr&q=sampleVerb";
    //static String URL = "https://lem.nouvelobs.com/hit.obs?s=561552&idclient=5b486fed-679a-4ec9-8671-32" +
    //       "8b258349ed&ts=1616170574116&vtag=5.18.0&ptag=js&r=1440x900x30x30&re=870x699&hl=17x16x14&lng=en-" +
    //     "GB&idp=1716147774956&p=conjugaisonFR::sampleVerb&s2=34&x1=[La_Conjugaison]&x2=7&x3=1&x4=2&x22=1";


    static ArrayList<String> verbsList = new ArrayList<>();
    static ArrayList<String> urlList = new ArrayList<>();

    public ScraperFrancaisConjugation(){}

    public static void addVerbsToList(){
        File Verbs = new File("src/main/java/Verbs.txt");
        try {
            Scanner sc = new Scanner(Verbs);
            while(sc.hasNextLine()){
                //System.out.println(sc.nextLine());
                verbsList.add(sc.nextLine());
            }
            sc.close();
            for(String s : verbsList){
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void urlMaker(){
        for (String verb : verbsList){
            String newURL = URL.replace("sampleVerb", verb);
            urlList.add(newURL);
        }
    }

    public static void urlScrapper(){
        //HttpResponse<String> response = Unirest.get(urlList.get(0)).asString();
        //System.out.println(response.getBody());
        int count =0;
        File file = new File("src/main/java/VerbsConjugated.txt");
        try {
            Writer writer = new FileWriter(file);

        for (String url : urlList) {
            HttpResponse<String> response = Unirest.get(urlList.get(count)).asString();
            Document toParse = Jsoup.parseBodyFragment(response.getBody());
            /*for (Element result : toParse.select("#contenu > div:nth-child(7) > div")){
                String s1 = result.select("b:nth-child(1)").text();
                System.out.println(s1);
            }
            count++;*/

            for (int child = 7; child <=10; child++){
                for (Element result : toParse.select("#contenu > div:nth-child("+child+")")){
                    String s1 = result.select("div").text();
                    writer.write(s1+"\n");
                    System.out.println(s1);
                }
            }

            count++;


        }
        writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
