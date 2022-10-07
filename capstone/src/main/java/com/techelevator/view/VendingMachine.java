package com.techelevator.view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class VendingMachine extends Item{
    private Map<String, Item> listOfProducts;
    private double currentBalance;
    private Map<Item, Integer> itemAndQuantity = new HashMap<>();

    public double getCurrentBalance(){
        return currentBalance;
    }
    public double subtractMoney(double amountToSubtract){
        currentBalance = currentBalance - amountToSubtract;
        return currentBalance;
    }

    public String returnChange(){
        int numberOfQuarters = (int)Math.floor(currentBalance/ 0.25);
        int numberOfDimes = (int)Math.floor((currentBalance -(0.25 * numberOfQuarters) / 0.10));
        int numberOfNickels = (int)Math.round(((currentBalance - ((0.25 * numberOfQuarters) + (0.10 * numberOfDimes))) / 0.05));

        String returnMessage = "Your change is " + NumberFormat.getCurrencyInstance().format(currentBalance) + numberOfQuarters +numberOfDimes + numberOfNickels + "Will dispense";
        currentBalance = 0;
        return returnMessage;
    }
    public Map<String, String> displayVendingItems() throws IOException {
        File itemInventory = new File("C:\\Users\\Student\\workspace\\mod-1-capstone-java-team-0\\capstone\\vendingmachine.csv");
        Path path = Paths.get("C:\\Users\\Student\\workspace\\mod-1-capstone-java-team-0\\capstone\\vendingmachine.csv");
        long lines = 0;
        String itemDisplay = "";
        Map<String, String> inventoryMap = new TreeMap<>();

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
                    inventoryMap.put(getItemSlot(),itemDisplay);
                }


            }
        }
        catch (IOException e){
            e.getMessage();
        }
        return inventoryMap;

    }


        public Map<String, Integer> remainingStock ()throws IOException {
            Map<String, Integer> stockMap = new TreeMap<>();
            for(Map.Entry<String, String> displayItem : displayVendingItems().entrySet()){
                stockMap.put(displayItem.getKey(),getInStock());
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

    public String itemIntake (Item item) {
        String display = getItemName() + getItemPrice() +getInStock();
        return display;

    }
}








