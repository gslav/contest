package contest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by gSlav on 20.11.2016 13:26.
 */

public class Task4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            int N = Integer.parseInt(in.nextLine());
            if (N > 100 || N < 1)
                throw new NullPointerException("необходимо выполнение условия (1 >= N >= 100)");

            int[] a = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (N != a.length)
                throw new NullPointerException("Размер массива A должнеб быть равен N");

            print(a);

            Arrays.sort(a);

            print(a);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }

    }

    private static void print(int[] a) {
        for (int b:a) {
            System.out.print(b + "\t");
        }
        System.out.println();
    }
}
