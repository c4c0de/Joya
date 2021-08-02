package com.example.joya;

public class helperCourses {

    String items , name;
    int image;

    public helperCourses(String items, String name, int image) {
        this.items = items;
        this.name = name;
        this.image = image;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
