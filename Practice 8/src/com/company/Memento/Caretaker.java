package com.company.Memento;

public class Caretaker {
    private Memento saved_instance;
    public void save_inst(Originator origin){
        this.saved_instance = origin.save();
    }
    public void undo(Originator origin){
        origin.restore(this.saved_instance);
    }
}
