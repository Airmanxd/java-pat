package com.company.factory;

public class ConcreteProduct implements Product{
    @Override
    public void info(){
        System.out.println("this is a product all right");
    };
}
