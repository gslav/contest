package contest;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gSlav on 19.11.2016 22:36.
 */

class AirLines implements Comparable<AirLines>{
    private String city;
    private int number;
    private String type;

    public AirLines(){

    }

    public AirLines(String[] line) {
        this.set(line);
    }

    public void set(String[] line) {
        String city = line[0];
        int number = Integer.parseInt(line[1]);
        String type = line[2];
        this.setCity(city);
        this.setNumber(number);
        this.setType(type);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int compareTo(AirLines o) {
        return this.number - o.getNumber();
    }
}

public class Task3 {
    public static void main(String[] args) {
        List<AirLines> myAirLines = new ArrayList<>();
        Path myDir = OpenCreateFile("resourses//airlines.db").toPath();

        try {
            List<String> myData = Files.readAllLines(myDir, Charset.defaultCharset());

            for (String line:myData) {
                AirLines a = new AirLines(line.trim().split("::"));
                myAirLines.add(a);
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        printDataToConsole(myAirLines);

        /*
        if (!search(myAirLines, 635))
            System.out.println("Рейс не найден");
        if (!search(myAirLines, 636))
            System.out.println("Рейс не найден");
        */

        List<AirLines> myAirLines2 = myAirLines;
        Collections.sort(myAirLines2);

        System.out.println();
        printDataToConsole(myAirLines2);
        printToFile(myAirLines2,
                "resourses//airlines2.db");
    }

    public static void printToFile(List<AirLines> myAirLines, String myPath) {
        File myFile = new File(
                String.valueOf(OpenCreateFile(myPath)));
        if (!myFile.canWrite())
            myFile.setWritable(true);

        PrintWriter out = null;
        try {
            out = new PrintWriter(
                    myFile.getAbsoluteFile());
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        for (AirLines tmp : myAirLines) {
            out.println(tmp.getNumber() + "::" + tmp.getCity() + "::" + tmp.getType());
        }
        out.close();
    }

    public static boolean search(List<AirLines> myAirLines, int number) {
        for (AirLines myAirLine: myAirLines) {
            if (number == myAirLine.getNumber()) {
                printDataToConsole(myAirLine);
                return true;
            }
        }
        return false;
    }

    public static void printDataToConsole(AirLines myAirLine) {
        System.out.println(myAirLine.getNumber() + " + " +
                myAirLine.getCity() + " + " +
                myAirLine.getType());
    }

    public static void printDataToConsole(List<AirLines> myAirLines) {
        for (AirLines line:myAirLines) {
            System.out.println(line.getNumber() + " + " +
                    line.getCity() + " + " +
                    line.getType());
        }
    }

    private static File OpenCreateFile(String myPath){
        String[] str = myPath.trim().split("//");
        File myDir = new File(System.getProperty("user.dir") + "//" + str[0]);
        File myFile = new File(myDir, str[1]);
        try {
            if (!myDir.exists())
                myDir.mkdir();

            if (!myFile.exists())
                myFile.createNewFile();

            /*
            if (myFile.length() == 0) {
                String[] temp = {
                        "Москва::635::1",
                        "Самара::21::3",
                        "Минск::344::2"};

                if (!myFile.canWrite())
                    myFile.setWritable(true);

                PrintWriter out = new PrintWriter(myFile.getAbsoluteFile());
                for (String tmp : temp) {
                    out.println(tmp);
                }
                out.close();
            }
            */
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        return myFile;
    }
}
