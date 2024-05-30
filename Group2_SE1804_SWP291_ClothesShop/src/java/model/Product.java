/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author KieuTuanPC
 */
public class Product {
    private int pid; // Product ID
    private String name; // Product Name
    private int quantity; // Quantity of Product
    private double price; // Price of Product
    private String describe; // Description of Product
    private String img; // Image URL of Product
    private Date releaseDate; // Release Date of Product
    private Category category; // Category
    private Brand brand; // Brand
    private Gender gender; // Gender
    private String size; // Size of Product

    // Constructor
    public Product(int pid, String name, int quantity, double price, String describe, String img, Date releaseDate, Category category, Brand brand, Gender gender, String size) {
        this.pid = pid;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.describe = describe;
        this.img = img;
        this.releaseDate = releaseDate;
        this.category = category;
        this.brand = brand;
        this.gender = gender;
        this.size = size;
    }

    // Getters and Setters
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    // Override toString() method
    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", describe='" + describe + '\'' +
                ", img='" + img + '\'' +
                ", releaseDate=" + releaseDate +
                ", category=" + category +
                ", brand=" + brand +
                ", gender=" + gender +
                ", size='" + size + '\'' +
                '}';
    }
}
