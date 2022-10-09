package com.techelevator.view;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class VendingMachine extends Item {
    private Map<String, Item> listOfProducts;
    private Map<Item, Integer> itemAndQuantity = new HashMap<>();
    private double balance;

    public VendingMachine() {
    }


    public double getBalance() {
        return balance;
    }


    public void returnChange() {
        int moneyTracker = (int) Math.floor(balance * 100);
        int numOfQuarters = 0;
        int numOfDimes = 0;
        int numOfNickels = 0;

        int quarter = 25;
        int dime = 10;
        int nickel = 5;

        while (moneyTracker > 0) {
            if (moneyTracker >= quarter) {
                numOfQuarters++;
                moneyTracker -= quarter;
            } else if (moneyTracker >= dime) {
                numOfDimes++;
                moneyTracker -= dime;
            } else if (moneyTracker >= nickel) {
                numOfNickels++;
                moneyTracker -= nickel;
            }
        }


        System.out.println("Your change is  Number of Quarters: " + numOfQuarters + " Number of dimes: " + numOfDimes + " Number of nickels: " + numOfNickels + " Will dispense");
    }

    //        int numberOfQuarters = (int)Math.floor(balance/ 0.25);
//        int numberOfDimes = (int)Math.floor((balance - (0.25 * numberOfQuarters) / 0.10));
//        int numberOfNickels = (int)Math.round(((balance - ((0.25 * numberOfQuarters) + (0.10 * numberOfDimes))) / 0.05));
//
//        String returnMessage = "Your change is " + NumberFormat.getCurrencyInstance().format(balance) + " Number of Quarters: " + numberOfQuarters + " Number of dimes: " +numberOfDimes + " Number of nickels: " + numberOfNickels + "Will dispense";
//        balance = 0;
//        System.out.println(returnMessage);
//        return returnMessage;
//    }
    public Map<String, Item> loadingVendingItems() {
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
        } catch (IOException e) {
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

    public void outputVendingItems() {
        for (Map.Entry<String, Item> displayItem : productsAndCurrentInventory.entrySet()) {
            System.out.print(displayItem.getKey() + " ");
            System.out.print(displayItem.getValue().getItemName() + " ");
            System.out.print(displayItem.getValue().getItemPrice() + " ");
            System.out.print(displayItem.getValue().getItemType() + " ");
            System.out.println(displayItem.getValue().getQuantityInStock() + " ");
        }
    }



    public String itemIntake(Item item) {
        String display = getItemName() + getItemPrice() + getQuantityInStock();
        return display;

    }
    public void feedMoney(int userMoney)  {
        balance += userMoney;
        String returnTest = "Current Money Provided: " + balance;
        System.out.println(returnTest);
        feedMoneyFileWriter(userMoney);

    }

    public void selectAndPurchase(String slot) {
        //outputVendingItems();
        int quantity;
        for (Map.Entry<String, Item> displayItem : productsAndCurrentInventory.entrySet()) {
            if (getBalance() < displayItem.getValue().getItemPrice()) {
                System.out.println("**************************************************");
                System.out.println("Your balance is too low, please insert more money");
                System.out.println("**************************************************");

                break;
            } else if (displayItem.getValue().getQuantityInStock() <= 0) {
                System.out.println("**************************************************");
                System.out.println("This item is out of stock");
                System.out.println("**************************************************");

            } else if (displayItem.getKey().equals(slot) && getBalance() >= displayItem.getValue().getItemPrice() && displayItem.getValue().getQuantityInStock() > 0) {
                balance -= displayItem.getValue().getItemPrice();
                quantity = displayItem.getValue().getQuantityInStock() - 1;
                Item itemUpdate = new Item(displayItem.getValue().getItemName(), displayItem.getValue().getItemPrice(), displayItem.getValue().getItemType(), quantity);
                productsAndCurrentInventory.put(displayItem.getKey(), itemUpdate);
                System.out.println(displayItem.getValue().getItemName() + " " + displayItem.getValue().getItemPrice() + " Your remaining balance is $" + balance);
                if (displayItem.getValue().getItemType().equals("Candy")) {
                    setItemSound("Munch, Munch, Yum!");
                }
                else if (displayItem.getValue().getItemType().equals("Chip")) {
                    setItemSound("Crunch, Crunch, Yum!");
                }
                else  if (displayItem.getValue().getItemType().equals("Drink")) {
                    setItemSound("Glug, Glug, Yum!");
                }
                else if (displayItem.getValue().getItemType().equals("Gum")) {
                    setItemSound("Chew, Chew, Yum!");
                } else {
                    setItemSound("error");
                }
                System.out.println(getItemSound());
                break;
            }
        }
        outputVendingItems();
    }
    public static String getCurrentTimeAsString(String format) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return dateTimeFormatter.format(now);
    }

    File log = new File("Log.log");

    public void feedMoneyFileWriter(int userMoney) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(log, true))) {
            writer.println(getCurrentTimeAsString("MM/dd/yyyy HH:mm:ss" ) + " FEED MONEY: " +  userMoney + " " + getBalance());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void selectAndPurchaseFileWriter(String slot) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(log, true))) {
            for (Map.Entry<String, Item> displayItem : productsAndCurrentInventory.entrySet()) {
                if (displayItem.getKey().equals(slot)) {
                    writer.println(getCurrentTimeAsString("MM/dd/yyyy HH:mm:ss") + " " + displayItem.getValue().getItemName() + " " + slot + " $" + displayItem.getValue().getItemPrice() + " $" + getBalance());
                }
            }
            }catch(FileNotFoundException e){
                e.printStackTrace();
        }
    }
    public void getChangeFileWriter (){
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(log, true))) {
            writer.println(getCurrentTimeAsString("MM/dd/yyyy HH:mm:ss") + " Give change: $" + getBalance() + " $0.00");
        }catch (FileNotFoundException e) {
            e.printStackTrace();
    }
        }




    }










