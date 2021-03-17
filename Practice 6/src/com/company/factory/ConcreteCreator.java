package com.company.factory;

public class ConcreteCreator extends Creator {
    @Override
    public Product create() {
        return new ConcreteProduct();
    }
}
