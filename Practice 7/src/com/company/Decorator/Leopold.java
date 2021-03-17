package com.company.Decorator;

public class Leopold extends Decorator {
    public Leopold(keyboard wrap){
        super(wrap);
    }

    public void click(){
        System.out.print("Clickity ");
        super.click();
    }
}
