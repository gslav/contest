/*
 * Created by gslav
 */

package contest;

import java.util.*;


public class Task1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите число ");
        try {
            int N = in.nextInt();
            if (N < 13)
                throw new Exception("N must be > 13");

            System.out.println(is_Simple(13, N));
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
        in.close();


    }

    public static int is_Simple (int start, int end){
        int first = start, count = 0;

        outer:for(int i = start; i < end; i++) {
            for (int j = 2; j < i; j++)
                if (i % j == 0) continue outer;
            //System.out.println(i);

            if (Math.abs(i - first) == 2)
                ++count;
            first = i;
        }
        return count;
    }
}
