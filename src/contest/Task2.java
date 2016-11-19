package contest;

import java.util.Scanner;

/**
 * Created by gSlav on 19.11.2016 21:07.
 */
public class Task2 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите символ заполнения ");
        char symbol = in.next().charAt(0);
        System.out.print("Введите размерность ");
        int size = in.nextInt();

        System.out.println();
        Square(size, symbol);
        System.out.println();
        TriangleSW(size, symbol);
        System.out.println();
        TriangleNW(size, symbol);
        System.out.println();
        TriangleSE(size, symbol);
        System.out.println();
        TriangleNE(size, symbol);

    }

    public static void Square(int n, char symbol) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(symbol);
            }
            System.out.println();
        }
    }

    public static void TriangleSW(int n, char symbol) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print(symbol);
            }
            System.out.println();
        }
    }

    public static void TriangleNW(int n, char symbol) {
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                System.out.print(symbol);
            }
            System.out.println();
        }
    }

    public static void TriangleSE(int n, char symbol) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (n - i <= j + 1)
                    System.out.print(symbol);
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void TriangleNE(int n, char symbol) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i > j)
                    System.out.print(" ");
                else
                    System.out.print(symbol);
            }
            System.out.println();
        }
    }
}
