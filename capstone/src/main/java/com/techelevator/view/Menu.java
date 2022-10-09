package com.techelevator.view;

import javax.swing.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.Map;
import java.util.Scanner;

public class Menu extends VendingMachine{

	private PrintWriter out;
	private Scanner in;
	private double userMoney = 0;
	private double currentMoneyProvided = 0;
	public Menu(InputStream input, OutputStream output) {

		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Menu() {

	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}


//	public void feedMoney() {
//		System.out.println("Please feed in money in Dollar Bills");
//		String userInput = in.nextLine();
//		userMoney = Double.parseDouble(userInput);
//		currentMoneyProvided += userMoney;
//
//		String returnTest = "Current Money Provided: " + currentMoneyProvided;
//		System.out.println(returnTest);
//	}


	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			if(optionNum <= 3){
				out.println(optionNum + ") " + options[i]);
			}
			out.print("");
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}
}
