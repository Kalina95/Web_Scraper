import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class ProxyList {


    public static ArrayList<String> ipList = new ArrayList<>();
    public static ArrayList<Integer> portList = new ArrayList<>();
    public static boolean isUsable;
    public static int count = 0;

    public ProxyList(){}


    public static void addToList(String ipToAdd, int portToAdd){
        ipList.add(ipToAdd);
        portList.add(portToAdd);
    }

    public void removeIpFromList(int index){
        ipList.remove(index);
    }

    public static String getIpFromList(int index){
        return ipList.get(index);
    }

    public static Integer getPortFromList(int index){
        return portList.get(index);
    }

    public static void fillProxyList(String filePathWithProxies){
        File file = new File(filePathWithProxies);

        try {
            Scanner sc = new Scanner(file);

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
