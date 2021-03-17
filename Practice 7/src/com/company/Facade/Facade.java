package com.company.Facade;

public class Facade {
    public void the_point(){
        Thing1 t1 = new Thing1();
        Thing2 t2 = new Thing2();
        Thing3 t3 = new Thing3();
        t1.action();
        t2.action();
        t3.action();
    }
}
