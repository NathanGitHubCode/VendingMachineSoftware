package com.techelevator;

import com.techelevator.view.Item;
import com.techelevator.view.Menu;

import java.io.IOException;

public class VendingMachineCLI extends Item  {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION};

	private Menu menu;

	public VendingMachineCLI(Menu menu)  {
		this.menu = menu;
	}


	public void run() throws IOException {

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);


			String purchaseChoice = null;
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				menu.outputVendingItems();


			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

				while(purchaseChoice.equals(PURCHASE_MENU_FEED_MONEY)) {
					menu.feedMoney();
					purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				}
				while(purchaseChoice.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
					menu.selectProduct();
					purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

				}  if (choice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {

				}


			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(1);

			}
		}
	}








	public static void main(String[] args) throws IOException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
