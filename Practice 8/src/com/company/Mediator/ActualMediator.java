package com.company.Mediator;

public class ActualMediator implements Mediator{
    private System1 s1;
    private System1 s2;

    public ActualMediator(System1 s1, System1 s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public void setS1(System1 s1) {
        this.s1 = s1;
    }

    public void setS2(System1 s2) {
        this.s2 = s2;
    }

    @Override
    public void send_message(String message) {
        s1.receiving_logic(message);
        s2.receiving_logic(message);
    }
}
