package contest;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gSlav on 19.11.2016 22:36.
 */

class AirLines {
    private String city;
    private int number;
    private String type;

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

    public AirLines(String[] line) {
        String city = line[0];
        int number = Integer.parseInt(line[1]);
        String type = line[2];
        this.setCity(city);
        this.setNumber(number);
        this.setType(type);
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
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        return myFile;
    }
}
