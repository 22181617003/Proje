package com.example.proje.Model;

public class Car {
    private int id;
    private String brand;
    private String model;
    private int year;
    private double price;
    private String name;

    public Car() {
    }

    public Car(String brand, String model, int year, double price, String name) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.name = name;
    }

    public Car(int id, String brand, String model, int year, double price, String name) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.name = name;
    }

    public Car(String brand, String model, int year, double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
