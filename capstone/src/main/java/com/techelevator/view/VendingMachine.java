package com.techelevator.view;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private Map<String, Inventory> listOfProducts;
    private double currentBalance;
    private Map<Inventory, Integer> itemAndQuantity = new HashMap<>();

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







}
