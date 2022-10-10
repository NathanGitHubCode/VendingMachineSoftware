package com.techelevator;

import com.techelevator.view.Item;
import com.techelevator.view.Menu;
import com.techelevator.view.VendingMachine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class VendingMachineCLI extends Item  {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_HIDDEN_MENU = "";

	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_HIDDEN_MENU};
	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String PURCHASE_MENU_EXIT_PURCHASE_MENU = "Exit Purchase Menu";

	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION};
	private static final String[] PURCHASE_MENU_OPTIONS_FINAL = {PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_EXIT_PURCHASE_MENU};
	private Menu menu;

	public VendingMachineCLI(Menu menu){
		this.menu = menu;
	}

	VendingMachine vendingMachine = new VendingMachine();
	public void run(){
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			String purchaseChoice = null;
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				vendingMachine.outputVendingItems();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)){
				purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

				while(purchaseChoice.equals(PURCHASE_MENU_FEED_MONEY)) {
					System.out.println("Please feed in money in Dollar Bills");
					String userInput = scanner.nextLine();
					vendingMachine.feedMoney(Integer.parseInt(userInput));
					purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				}
				while(purchaseChoice.equals(PURCHASE_MENU_SELECT_PRODUCT)){
					System.out.println("Please enter Product Code: ");
					String userInput = scanner.nextLine();
					vendingMachine.selectAndPurchase(userInput);
					vendingMachine.selectAndPurchaseFileWriter(userInput);
					vendingMachine.salesReportFileWriter();
					purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				}
				while (purchaseChoice.equals(PURCHASE_MENU_FINISH_TRANSACTION)){
					vendingMachine.returnChange();
					vendingMachine.getChangeFileWriter();
					 purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS_FINAL);
				}
				if (purchaseChoice.equals(MAIN_MENU_OPTION_EXIT)){
					System.exit(1);
				}
			}
			else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(1);
			}
			else if (choice.equals(MAIN_MENU_OPTION_HIDDEN_MENU)){
				vendingMachine.salesReportFileWriter();
				System.out.println("Sales report created");
			}
		}
	}








	public static void main(String[] args){
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
