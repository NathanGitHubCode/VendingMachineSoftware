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
    private int quantityInStock;
    private double itemPrice;
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

    public void setItemSound(String itemSound) {
        this.itemSound = itemSound;
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemSound() {
        return itemSound;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public String getItemName() {
        return itemName;
    }
}