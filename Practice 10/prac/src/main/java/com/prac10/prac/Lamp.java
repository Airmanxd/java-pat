package com.prac10.prac;
import org.springframework.stereotype.Component;
@Component
public class Lamp implements Lighter{
    @Override
    public String doLight() {
        return "The lamp reveals your surroundings";
    }
}
