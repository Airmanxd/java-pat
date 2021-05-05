package com.company;

import com.company.Mediator.ActualMediator;
import com.company.Mediator.System1;
import com.company.Mediator.System2;
import com.company.Memento.Caretaker;
import com.company.Memento.Originator;

public class Main {

    public static void main(String[] args) {
        System1 worker1 = new System1();
        System1 worker2 = new System1();
        System2 boss = new System2();
        boss.setMediator(new ActualMediator(worker1, worker2));
        boss.send_message("test");

        Caretaker caretaker = new Caretaker();
        Originator originator = new Originator();
        originator.edit_text("First commit \n");
        originator.edit_text("Second commit \n");
        caretaker.save_inst(originator);
        System.out.println(originator.getImportant_text());
        originator.edit_text("Error!");
        System.out.println(originator.getImportant_text());
        caretaker.undo(originator);
        System.out.println(originator.getImportant_text());

    }
}
