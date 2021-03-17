package com.company.AbstractFactory;

public class ConcreteProduct implements AbstractProduct {
    @Override
    public void info() {
        System.out.println("New abstract factory product");
    }
}
