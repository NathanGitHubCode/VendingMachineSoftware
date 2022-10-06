package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Item {

    private File itemInventory = new File("vendingmachine.csv");
    private String itemName;
    private int itemQuantity;
    private double itemPrice;

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
        String itemDisplay = "";
    try (Scanner inventoryReader = new Scanner(itemInventory)) {
        while (inventoryReader.hasNextLine()) {
            String line = inventoryReader.nextLine();
             String[] inventoryArray = line.split("\\|");
             itemSlot = inventoryArray[0];
             itemName = inventoryArray[1];
             itemPrice = Double.parseDouble(inventoryArray[2]);
             itemDisplay = itemSlot + itemName + itemPrice;

        }
    } catch (
    FileNotFoundException e) {
        e.getMessage();
    }
    return itemDisplay;
}

    public int getItemQuantity() {
        return itemQuantity;
    }
    public File getItemInventory() {
        return itemInventory;
    }
}
