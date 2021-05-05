package com.company.Memento;

public class Originator {
    private String important_text = "";
    public void edit_text(String text){
        important_text += text;
    }
    public Memento save(){
        return new Memento(important_text);
    }

    public String getImportant_text() {
        return important_text;
    }

    public void restore(Memento save){
        this.important_text = save.getSave();
    }
}
