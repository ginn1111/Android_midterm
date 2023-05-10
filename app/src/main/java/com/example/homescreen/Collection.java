package com.example.homescreen;

public class Collection {
    private int imgResource;
    private String name;
    private String author;
    private double price;

    public Collection(int imageURL, String name, String author, double price) {
        this.imgResource = imageURL;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public int getImageURL() {
        return imgResource;
    }

    public void setImageURL(int imgResource) {
        this.imgResource = imgResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
