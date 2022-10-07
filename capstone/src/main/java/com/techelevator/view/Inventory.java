package com.techelevator.view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Inventory extends Item{




    public Map<String, String> displayVendingItems() throws IOException {
        File itemInventory = new File("C:\\Users\\Student\\workspace\\mod-1-capstone-java-team-0\\capstone\\vendingmachine.csv");
        Path path = Paths.get("C:\\Users\\Student\\workspace\\mod-1-capstone-java-team-0\\capstone\\vendingmachine.csv");
        long lines = 0;
        String itemDisplay = "";
        Map<String, String> stockMap = new TreeMap<>();

        try (Scanner inventoryReader = new Scanner(itemInventory)) {
            while (inventoryReader.hasNextLine()) {
                lines = Files.lines(path).count();
                String line = inventoryReader.nextLine();
                for (int i = 0; i < lines; i++) {
                    String[] inventoryArray = line.split("\\|");
                    setItemSlot(inventoryArray[0]);
                    setItemName(inventoryArray[1]);
                    setItemPrice(Double.parseDouble(inventoryArray[2]));
                    setItemType(inventoryArray[3]);
                    itemDisplay = getItemName() + " $" + getItemPrice() +" " +getItemType() + " Quantity in stock: 5";
                    stockMap.put(getItemSlot(), itemDisplay);
                }


            }
        }
        catch (IOException e){
            e.getMessage();
        }
        return stockMap;
    }
    public Map<String, String> outputVendingItems() throws IOException {
        for(Map.Entry<String, String> displayItem : displayVendingItems().entrySet()){
            System.out.println(displayItem.getKey() + " "+ displayItem.getValue());
        }
        return null;
    }
    public String selectPurchaseOutput(){

        String test = getItemType();
        if(test.equals("Candy")){
            setItemSound("Munch, Munch, Yum!");
        }
        if (test.equals("Chip")){
            setItemSound("Crunch, Crunch, Yum!");
        }
        if(test.equals("Drink")){
            setItemSound("Glug, Glug, Yum!");
        }
        if(test.equals("Gum")){
            setItemSound("Chew, Chew, Yum!");
        }
        else{
            setItemSound("error");
        }
        String combinedString = getItemName() + " " + getItemPrice() + " " + getItemSound();
        return combinedString;
    }

}

