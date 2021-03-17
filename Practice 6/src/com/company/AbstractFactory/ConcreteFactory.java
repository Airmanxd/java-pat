package com.company.AbstractFactory;

public class ConcreteFactory implements AbstractFactory {
    @Override
    public AbstractProduct create() {
        return new ConcreteProduct();
    }
}
