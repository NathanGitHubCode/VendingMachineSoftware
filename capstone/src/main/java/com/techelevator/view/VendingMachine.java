package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
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
    private Map<Item, Integer> itemAndQuantity = new HashMap<>();
    private double balance;


    public double getBalance() {
        return balance;
    }




    public String returnChange(){
        int numberOfQuarters = (int)Math.floor(balance/ 0.25);
        int numberOfDimes = (int)Math.floor((balance -(0.25 * numberOfQuarters) / 0.10));
        int numberOfNickels = (int)Math.round(((balance - ((0.25 * numberOfQuarters) + (0.10 * numberOfDimes))) / 0.05));

        String returnMessage = "Your change is " + NumberFormat.getCurrencyInstance().format(balance) + numberOfQuarters +numberOfDimes + numberOfNickels + "Will dispense";
        balance = 0;
        return returnMessage;
    }
    public Map<String, Item> loadingVendingItems(){
        File itemInventory = new File("C:\\Users\\Student\\workspace\\mod-1-capstone-java-team-0\\capstone\\vendingmachine.csv");
        Path path = Paths.get("C:\\Users\\Student\\workspace\\mod-1-capstone-java-team-0\\capstone\\vendingmachine.csv");
        //long lines = 0;
        String itemDisplay = "";
        Map<String, Item> inventoryMap = new TreeMap<>();

        try (Scanner inventoryReader = new Scanner(itemInventory)) {
            while (inventoryReader.hasNextLine()) {
               // lines = Files.lines(path).count();
                String line = inventoryReader.nextLine();
                String[] inventoryArray = line.split("\\|");
                Item item = new Item(inventoryArray[1], Double.parseDouble(inventoryArray[2]), inventoryArray[3], 5);
                inventoryMap.put(inventoryArray[0], item);




//                for (int i = 0; i < lines; i++) {
//                    String[] inventoryArray = line.split("\\|");
//                    setItemSlot(inventoryArray[0]);
//                    setItemName(inventoryArray[1]);
//                    setItemPrice(Double.parseDouble(inventoryArray[2]));
//                    setItemType(inventoryArray[3]);
//
//                    itemDisplay = getItemName() + " $" + getItemPrice() +" " +getItemType() + " Quantity in stock: 5";
//                    inventoryMap.put(getItemSlot(),itemDisplay);
//                }


            }
        }
        catch (IOException e){
            e.getMessage();
        }
        return inventoryMap;

    }

    Map<String, Item> productsAndCurrentInventory = loadingVendingItems();


//        public Map<String, Integer> remainingStock (){
//            Map<String, Integer> stockMap = new TreeMap<>();
//            for(Map.Entry<String, Item> displayItem : productsAndCurrentInventory.entrySet()){
//                stockMap.put(displayItem.getKey(),getInStock());
//            }
//        return stockMap;
//        }
//

 public void outputVendingItems(){
        for(Map.Entry<String, Item> displayItem : productsAndCurrentInventory.entrySet()){
            System.out.print(displayItem.getKey() + " ");
            System.out.print(displayItem.getValue().getItemName() + " ");
            System.out.print(displayItem.getValue().getItemPrice() + " ");
            System.out.print(displayItem.getValue().getItemType() + " ");
            System.out.println(displayItem.getValue().getQuantityInStock() + " ");
        }
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
        String display = getItemName() + getItemPrice() +getQuantityInStock();
        return display;

    }
    public void feedMoney(int userMoney) {
        balance += userMoney;
        String returnTest = "Current Money Provided: " + balance;
        System.out.println(returnTest);
    }

    public void selectAndPurchase(String slot) {
        //outputVendingItems();
        int quantity;
        for(Map.Entry<String, Item> displayItem : productsAndCurrentInventory.entrySet()){
            if(displayItem.getKey().equals(slot) && getBalance() >= displayItem.getValue().getItemPrice() && displayItem.getValue().getQuantityInStock() > 0) {
                balance -= displayItem.getValue().getItemPrice();
                quantity =  displayItem.getValue().getQuantityInStock() - 1;
                Item itemUpdate = new Item(displayItem.getValue().getItemName(), displayItem.getValue().getItemPrice(), displayItem.getValue().getItemType(), quantity);
                productsAndCurrentInventory.put(displayItem.getKey(), itemUpdate);
                System.out.println(displayItem.getValue().getItemName() + " " + displayItem.getValue().getItemPrice() + " Your remaining balance is $" + balance);
            }
            else if(displayItem.getValue().getQuantityInStock() <= 0){
                System.out.println("**************************************************");
                System.out.println("This item is out of stock");
                System.out.println("**************************************************");

            }
            else if (getBalance() < displayItem.getValue().getItemPrice() ){
                System.out.println("**************************************************");
                System.out.println("Your balance is too low, please insert more money");
                System.out.println("**************************************************");

                break;
            }
        }
        outputVendingItems();
    }
}








