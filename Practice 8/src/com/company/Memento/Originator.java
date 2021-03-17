package com.company.Memento;

public class Originator {
    private String important_text;
    public void nice_function(String text){
        important_text += text;
    }
    public Memento save(){
        return new Memento(important_text);
    }
    public void restore(Memento save){
        this.important_text = save.getSave();
    }
}
