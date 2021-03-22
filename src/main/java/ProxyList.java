import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProxyList {


    public static ArrayList<String> ipList = new ArrayList<>();
    public static ArrayList<Integer> portList = new ArrayList<>();

    //There is only basic constructor here.


    //adding ip and port to list.
    public static void addToList(String ipToAdd, int portToAdd){
        ipList.add(ipToAdd);
        portList.add(portToAdd);
    }

    public static void fillProxyList(String filePathWithProxies){
        File filteredProxy = new File(filePathWithProxies);

        try {
            Scanner sc = new Scanner(filteredProxy);
            while (sc.hasNextLine()){
                String tempIp = sc.next();
                String tempPortString = sc.next();
                int tempPortInt = Integer.parseInt(tempPortString);
                addToList(tempIp,tempPortInt);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
