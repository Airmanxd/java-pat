package com.company;

import java.util.Map;
import java.util.Set;

public class Main {

    public static void settest(Set<Integer> set) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++)
                set.add(i);
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 20; i < 30; i++)
                set.add(i);
        });
        thread1.start();
        thread2.start();
        Thread.sleep(1000);

        set.stream().forEach(System.out::println);
    }

    public static void maptest(Map<Integer, Integer> map) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 11; i++)
                map.put(i, i);
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 11; i < 21; i++)
                map.put(i, i);
        });
        thread1.start();
        thread2.start();
        Thread.sleep(1000);

        map.entrySet().stream().forEach(System.out::println);
    }

    public static void main(String[] args) throws InterruptedException {
        Set<Integer> set = new SynchronizedSet<>();
        System.out.println("Set");
        settest(set);

        Map<Integer, Integer> map = new LockedMap<>();
        System.out.println("Map");
        maptest(map);
    }
}
