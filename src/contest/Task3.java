package contest;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by gSlav on 19.11.2016 22:36.
 */

class AirLines implements Comparable<AirLines>{
    /** место прибытия */
    private String city;
    /** номер рейса */
    private int number;
    /** тип самолета */
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

    /**
     * Компаратор (сравнитель) экземпляров класса.
     * Сравнение происходит по полю number (номер рейса)
     * @param o - экземпляр класс AirLines
     * @return -1 (если < ), 0 (если == ), 1 (если > )
     */
    @Override
    public int compareTo(AirLines o) {
        return this.number - o.getNumber();
    }
}

public class Task3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<AirLines> myAirLines = new ArrayList<>();
        Path myDir = OpenCreateFile("resourses//airlines.db").toPath();

        /**
         * чтение из файла
         */
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
        int key;
        do {
            System.out.println("Введите" +
                    "\n\t1 для поиска по номеру рейса" +
                    "\n\t2 для сортировки по номеру рейса" +
                    "\n\t3 для записи в файл" +
                    "\n\t0 для выхода");
            key = in.nextInt();
            switch (key) {
                case 1:
                    System.out.println("Введите номер искомого рейса ");
                    search(myAirLines, in.nextInt());
                    break;
                case 2:
                    Collections.sort(myAirLines);
                    printDataToConsole(myAirLines);
                    System.out.println();
                    break;
                case 3:
                    printToFile(myAirLines,
                            "resourses//airlines2.db");
                    break;
                case 0:
                    System.exit(0);
            }
        }while (true);
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
        System.out.println("Данные успешно записаны \n" +
                myFile.getPath());
    }

    public static boolean search(List<AirLines> myAirLines, int number) {
        for (AirLines myAirLine: myAirLines) {
            if (number == myAirLine.getNumber()) {
                printDataToConsole(myAirLine);
                return true;
            }
        }
        System.out.print("Рейс номер " + number + " не найден.");
        return false;
    }

    public static void printDataToConsole(AirLines myAirLine) {
        System.out.println(
                "Рейс № " + myAirLine.getNumber() +
                "\tприбытие в: " +
                myAirLine.getCity() +
                "\t тип: " +
                myAirLine.getType());
    }

    public static void printDataToConsole(List<AirLines> myAirLines) {
        myAirLines.forEach(Task3::printDataToConsole);
    }

    /**
     * Открыть файл, если файл существует, иначе создать новый.
     * @param myPath - путь к каталогу с базой рейсов самолетов (от каталога запуска программы)
     * @return открытый файл
     */
    private static File OpenCreateFile(String myPath){
        String[] str = myPath.trim().split("//");
        File myDir = new File(System.getProperty("user.dir") + "//" + str[0]);
        File myFile = new File(myDir, str[1]);
        try {
            if (!myDir.exists())
                myDir.mkdir();

            if (!myFile.exists())
                myFile.createNewFile();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        return myFile;
    }
}
