package com.company.AbstractFactory;

public class ConcreteProductAbs implements AbstractProduct{
    @Override
    public void info() {
        System.out.println("New abstract factory product");
    }
}
