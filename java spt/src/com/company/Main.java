package com.company;

import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        Function<Integer[], Integer> common = arr ->
        {
            Integer a = arr[0];
            Integer b = arr[1];
            while(!a.equals(b))
            {
                if (a>b)
                    a %= b;
                else
                    b %= a;
            }
            return a;
        };
        Integer[] data = new Integer[]{18, 24};
        System.out.println(common.apply(data));
    }
}
