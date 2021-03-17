package com.company.Decorator;

public class Decorator implements keyboard {
    private final keyboard wrap;
    public Decorator(keyboard wrap){
        this.wrap = wrap;
    }

    @Override
    public void click() {
        wrap.click();
    }
}
