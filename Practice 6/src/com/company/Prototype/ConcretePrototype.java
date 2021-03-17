package com.company.Prototype;

public class ConcretePrototype implements Prototype {
    private final String name;
    private final int age;

    public ConcretePrototype(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public Prototype clone() {
        return new ConcretePrototype(String name, age);
    }
}
