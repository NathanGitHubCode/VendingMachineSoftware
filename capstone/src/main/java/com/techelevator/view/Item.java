package com.techelevator.view;

import javax.sound.sampled.Line;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;



public class Item {



    private String itemName;
    private int itemQuantity;
    private double itemPrice;

    public Item() {
    }

    public Item (String itemName, int itemQuantity, double itemPrice){
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
    }


    public String getItemSlot() {
        return itemSlot;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    private String itemSlot;


    public String getItemName() {
        return itemName;
    }



    public String displayVendingItems() {
        File itemInventory = new File("C:\\Users\\Student\\workspace\\mod-1-capstone-java-team-0\\capstone\\vendingmachine.csv");
        String itemDisplay = "";
    try (Scanner inventoryReader = new Scanner(itemInventory)) {
        while (inventoryReader.hasNextLine()) {
            String line = inventoryReader.nextLine();
            String[] inventoryArray = line.split("\\|");
             itemSlot = inventoryArray[0];
             itemName = inventoryArray[1];
             itemPrice = Double.parseDouble(inventoryArray[2]);
             itemDisplay = itemSlot + itemName + itemPrice;


        } catch (
                    FileNotFoundException e) {
                e.getMessage();
    }
    }
    return itemDisplay;
}

    public int getItemQuantity() {
        return itemQuantity;

    }
}
