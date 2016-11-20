package contest;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by gSlav on 20.11.2016 13:26.
 */

/*
Input:
9
6 5 3 1 2 2 3 4 6
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
                throw new NullPointerException("Размер массива A должна быть равен N");

            print(a);

            Arrays.sort(a);

            List<Integer> b = new ArrayList<>();
            for (int i : a) {
                b.add(0, i);
                if (i > 6 || i < 1)
                    throw new NullPointerException("Размер группы должно быть 1 <= A <= 6");
            }

            System.out.println(
                    "Минимальное количество рядов = " +
                    reOrganize(b));
        }
        catch (NullPointerException e){
            System.err.println(e.getMessage());
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private static int reOrganize(List<Integer> b) {
        for (int i = 0; i < b.size(); i++) {
            if (b.get(i) == 6)
                continue;
            else {
                int j = 1;
                do {
                    if (b.get(i) + b.get(b.size() - j) == 6) {
                        b.set(i, (b.get(i) + b.get(b.size() - j)));
                        b.remove(b.size() - j);
                    }
                    else {
                        j++;
                        if (i == j)
                            break;
                    }
                }
                while (true) ;
            }
        }
        print(b);
        return b.size();
    }

    private static void print(List<Integer> a) {
        for (int b:a) {
            System.out.print(b + "\t");
        }
        System.out.println();
    }


    private static void print(int[] a) {
        for (int b:a) {
            System.out.print(b + "\t");
        }
        System.out.println();
    }
}
