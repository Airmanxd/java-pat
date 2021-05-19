package com.practice10;
import org.springframework.stereotype.Component;

@Component
public class Flashlight implements Lighter{
    @Override
    public String doLight() {
        return "The flashligh illuminates what's right before you";
    }
}
