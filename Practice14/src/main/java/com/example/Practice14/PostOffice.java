package com.example.Practice14;

public class PostOffice {
    private String name;
    private String cityName;

    public PostOffice(String name, String cityName) {
        this.name = name;
        this.cityName = cityName;
    }

    public String getName() {
        return name;
    }

    public String getCityName() {
        return cityName;
    }
}
