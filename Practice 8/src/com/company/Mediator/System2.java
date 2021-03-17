package com.company.Mediator;

public class System2 {
    private Mediator mediator;

    public void setMediator(Mediator mediator){
        this.mediator = mediator;
    }
    public void send_message(String message){
        this.mediator.send_message(message);
    }
}
