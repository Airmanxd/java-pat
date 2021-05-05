package com.example.Practice14;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class DepartureController {
    private ArrayList<Departure> departures;
    public void add_departure(Departure departure){
        departures.add(departure);
    }
    public Departure create_departure(String type, String date){
        Departure temp =  new Departure(type, date);
        this.add_departure(temp);
        return temp;
    }

    public ArrayList<Departure> getDepartures() {
        return departures;
    }
    public void deleteDepartureByName(String type){
        int i = 0;

        while(i < departures.size()) {

            Departure temp = departures.get(i);

            if(temp.getType() == type) {
                departures.remove(temp);
                temp = null;
            }
            i++;
        }
    }
}
