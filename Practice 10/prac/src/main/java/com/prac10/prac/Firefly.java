package com.prac10.prac;
import org.springframework.stereotype.Component;
@Component
public class Firefly implements Lighter{

    @Override
    public String doLight() {
        return "The firefly shines, but doesn't illuminate anything around it";
    }
}
