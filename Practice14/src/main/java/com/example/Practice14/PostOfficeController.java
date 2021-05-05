package com.example.Practice14;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
@Controller
public class PostOfficeController {
    private ArrayList<PostOffice> postOffices;
    public PostOffice createPostOffice(String name, String cityName){
        PostOffice temp = new PostOffice(name, cityName);
        this.addPostOffice(temp);
        return temp;
    }
    public void addPostOffice(PostOffice postOffice){
        postOffices.add(postOffice);
    }

    public ArrayList<PostOffice> getPostOffices() {
        return postOffices;
    }

    public void deletePostOfficeByName(String name){
        int i = 0;

        while(i < postOffices.size()) {

            PostOffice temp = postOffices.get(i);

            if(temp.getName() == name) {
                postOffices.remove(temp);
                temp = null;
            }
            i++;
        }
    }
}
