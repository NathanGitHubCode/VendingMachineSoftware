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
    private int itemQuantity;
    private double itemPrice;

    public Item() {
    }

    public Item(String itemName, int itemQuantity, double itemPrice) {
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


    public Map<String, String> displayVendingItems() throws IOException {
        File itemInventory = new File("C:\\Users\\Student\\workspace\\mod-1-capstone-java-team-0\\capstone\\vendingmachine.csv");
        Path path = Paths.get("C:\\Users\\Student\\workspace\\mod-1-capstone-java-team-0\\capstone\\vendingmachine.csv");
        long lines = 0;
        String itemDisplay = "";
        Map<String, String> stockMap = new HashMap<>();
        try (Scanner inventoryReader = new Scanner(itemInventory)) {
            while (inventoryReader.hasNextLine()) {
                lines = Files.lines(path).count();
                String line = inventoryReader.nextLine();
                for (int i = 0; i < lines; i++) {
                    String[] inventoryArray = line.split("\\|");
                    itemSlot = inventoryArray[0];
                    itemName = inventoryArray[1];
                    itemPrice = Double.parseDouble(inventoryArray[2]);
                    itemDisplay = itemSlot + " " + itemPrice;
                    stockMap.put(itemName, itemDisplay);

                }
                System.out.println(stockMap);
            }
        }
        catch (IOException e){
            e.getMessage();
        }
        return stockMap;
    }
}