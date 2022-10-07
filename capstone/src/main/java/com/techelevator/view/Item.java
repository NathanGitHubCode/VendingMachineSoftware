package com.techelevator.view;

import javax.sound.sampled.Line;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class Item {


    private String itemName;
    private static final int MAX_QUANTITY = 5;
    private int quantityInStock;
    private double itemPrice;
    private String itemSlot;
    private String itemSound;
    private String itemType;

    public Item() {
    }

    public Item(String itemName, double itemPrice, String itemType, int quantityInStock) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemType = itemType;
        this.quantityInStock = quantityInStock;

    }

    public int getQuantityInStock() {
        return quantityInStock ;
    }

    public void setQuantityInStock(int inStock) {
        this.quantityInStock = inStock;
    }




    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setItemSound(String itemSound) {
        this.itemSound = itemSound;
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemSound() {
        return itemSound;
    }




    public Item(String itemName, int itemQuantity, double itemPrice, String itemSlot, String itemSound, String itemType, int inStock) {
        this.itemName = itemName;
        this.quantityInStock = inStock;
        this.itemPrice = itemPrice;
        this.itemSound = itemSound;
        this.itemType = itemType;

        if(this.itemType.equals("Candy")){
            this.itemSound = "Munch, Munch, Yum!";
        }
        if (this.itemType.equals("Chip")){
            this.itemSound = "Crunch, Crunch, Yum!";
        }
        if(this.itemType.equals("Drink")){
            this.itemSound = "Glug, Glug, Yum!";
        }
        if(this.itemType.equals("Gum")){
            this.itemSound = "Chew, Chew, Yum!";
        }
    }

    public String getItemSlot() {
        return itemSlot;
    }

    public double getItemPrice() {
        return itemPrice;
    }



    public String getItemName() {
        return itemName;
    }



    public void setItemName(String itemName) {
        this.itemName = itemName;
    }



    public void setItemSlot(String itemSlot) {
        this.itemSlot = itemSlot;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
}