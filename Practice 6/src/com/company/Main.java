package com.company;

import com.company.AbstractFactory.AbstractProduct;
import com.company.AbstractFactory.ConcreteFactory;
import com.company.AbstractFactory.ConcreteProduct;
import com.company.AbstractFactory.ConcreteProductAbs;
import com.company.factory.ConcreteCreator;
import com.company.factory.Product;

public class Main {
    public static void main(String[] args) {
        ConcreteCreator cnc = new ConcreteCreator();
        Product test = cnc.create();
        test.info();

        ConcreteFactory cnf = new ConcreteFactory();
        AbstractProduct test2 = cnf.create();
        test2.info();

        com.company.Builder.Product.Builder builder = new com.company.Builder.Product.Builder();
        builder.setName("New");
        builder.setCost(12);
        com.company.Builder.Product test3 = builder.build();
        System.out.println("Cost: " + test3.getCost() + ", Name: " + test3.getName());
    }
}
