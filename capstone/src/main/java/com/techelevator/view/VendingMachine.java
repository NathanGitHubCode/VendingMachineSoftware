package com.techelevator.view;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class VendingMachine extends Item {
    private double balance;
    private int amountOrdered;
    private double totalSales;
    private int quantity;

    File log = new File("Log.log");

    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
    Map<String, Item> productsAndCurrentInventory = loadingVendingItems();

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


        System.out.println(numOfQuarters + " Quarters | " + numOfDimes + " Dimes | " + numOfNickels + " Nickels | Returned - Please check change compartment");
    }


    public Map<String, Item> loadingVendingItems() {
        File itemInventory = new File("C:\\Users\\Student\\workspace\\mod-1-capstone-java-team-0\\capstone\\vendingmachine.csv");
        Map<String, Item> inventoryMap = new TreeMap<>();
        try (Scanner inventoryReader = new Scanner(itemInventory)) {
            while (inventoryReader.hasNextLine()) {
                String line = inventoryReader.nextLine();
                String[] inventoryArray = line.split("\\|");
                Item item = new Item(inventoryArray[1], Double.parseDouble(inventoryArray[2]), inventoryArray[3], 5, 0);
                inventoryMap.put(inventoryArray[0], item);


            }
        } catch (IOException e) {
            e.getMessage();
        }
        return inventoryMap;

    }
//    public Map<String, Integer> loadSalesReport() {
//        File itemInventory = new File("C:\\Users\\Student\\workspace\\mod-1-capstone-java-team-0\\capstone\\vendingmachine.csv");
//        Map<String, Integer> inventoryMap = new TreeMap<>();
//        try (Scanner inventoryReader = new Scanner(itemInventory)) {
//            while (inventoryReader.hasNextLine()) {
//                String line = inventoryReader.nextLine();
//                String[] inventoryArray = line.split("\\|");
//                inventoryMap.put(inventoryArray[1], amountOrdered);
//
//
//            }
//        } catch (IOException e) {
//            e.getMessage();
//        }
//        return inventoryMap;
//
//    }


    public void outputVendingItems() {
        for (Map.Entry<String, Item> displayItem : productsAndCurrentInventory.entrySet()) {
            System.out.print(displayItem.getKey() + " ");
            System.out.print(displayItem.getValue().getItemName() + " ");
            System.out.print(currencyFormatter.format(displayItem.getValue().getItemPrice()) + " ");
            System.out.print(displayItem.getValue().getItemType() + " ");
            System.out.println("Quantity: " + displayItem.getValue().getQuantityInStock() + " ");
        }
    }


    public void feedMoney(double userMoney) {
        balance += userMoney;
        String returnTest = "Current Money Provided: " + currencyFormatter.format(balance);
        System.out.println(returnTest);
        feedMoneyFileWriter(userMoney);

    }

    public void selectAndPurchase(String slot) {
        for (Map.Entry<String, Item> displayItem : productsAndCurrentInventory.entrySet()) {
            if (displayItem.getKey().equals(slot) && getBalance() < displayItem.getValue().getItemPrice()) {
                System.out.println("**************************************************");
                System.out.println("Your balance is too low, please insert more money");
                System.out.println("**************************************************");
                break;
            } else if (displayItem.getKey().equals(slot) && displayItem.getValue().getQuantityInStock() <= 0) {
                System.out.println("**************************************************");
                System.out.println("This item is out of stock");
                System.out.println("**************************************************");
                break;

            } else if (displayItem.getKey().equals(slot) && getBalance() >= displayItem.getValue().getItemPrice() && displayItem.getValue().getQuantityInStock() > 0) {
                balance -= displayItem.getValue().getItemPrice();
                quantity = displayItem.getValue().getQuantityInStock() - 1;
                amountOrdered = displayItem.getValue().getAmountOrdered() + 1;
                Item itemUpdate = new Item(displayItem.getValue().getItemName(), displayItem.getValue().getItemPrice(), displayItem.getValue().getItemType(), quantity, amountOrdered);
                productsAndCurrentInventory.put(displayItem.getKey(), itemUpdate);
                System.out.println(displayItem.getValue().getItemName() + " " + currencyFormatter.format(displayItem.getValue().getItemPrice()) + " Your remaining balance is " + currencyFormatter.format(balance));
                if (displayItem.getValue().getItemType().equals("Candy")) {
                    setItemSound("Munch, Munch, Yum!");
                } else if (displayItem.getValue().getItemType().equals("Chip")) {
                    setItemSound("Crunch, Crunch, Yum!");
                } else if (displayItem.getValue().getItemType().equals("Drink")) {
                    setItemSound("Glug, Glug, Yum!");
                } else if (displayItem.getValue().getItemType().equals("Gum")) {
                    setItemSound("Chew, Chew, Yum!");
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


    public void feedMoneyFileWriter(double userMoney) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(log, true))) {
            writer.println(getCurrentTimeAsString("MM/dd/yyyy HH:mm:ss") + " FEED MONEY: " + currencyFormatter.format(userMoney) + " " + currencyFormatter.format(getBalance()));
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }

    public void selectAndPurchaseFileWriter(String slot) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(log, true))) {
            for (Map.Entry<String, Item> displayItem : productsAndCurrentInventory.entrySet()) {
                if (displayItem.getKey().equals(slot)) {
                    writer.println(getCurrentTimeAsString("MM/dd/yyyy HH:mm:ss") + " " + displayItem.getValue().getItemName() + " " + slot + " " + currencyFormatter.format(displayItem.getValue().getItemPrice()) + " " + currencyFormatter.format(getBalance()));
                }
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }

    public void getChangeFileWriter() {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(log, true))) {
            writer.println(getCurrentTimeAsString("MM/dd/yyyy HH:mm:ss") + " Give change: " + currencyFormatter.format(getBalance()) + " $0.00");
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }

    public void salesReportFileWriter() {
        String reportTitle = (getCurrentTimeAsString("MM-dd-yyyy-HH-mm-ss") + ".log");
        File salesReport = new File(reportTitle);
        try (PrintWriter writer = new PrintWriter((salesReport))) {
            for (Map.Entry<String, Item> displayItem : productsAndCurrentInventory.entrySet()) {
               writer.println(displayItem.getValue().getItemName() + "|" + displayItem.getValue().getAmountOrdered());
               if(displayItem.getValue().getAmountOrdered() > 0){
                   currencyFormatter.format(totalSales += displayItem.getValue().getItemPrice());
               }
            }
            writer.println("Total sales:" +  currencyFormatter.format(totalSales));
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }
}










