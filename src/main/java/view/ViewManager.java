/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Scanner;
import model.Exchange;
import controller.ExchangeController;

/**
 *
 * @author gabri
 */
public class ViewManager {
    private final Scanner scanner;
    private static ExchangeController controller = ExchangeController.getInstance();
    
    public ViewManager() {
        this.scanner = new Scanner(System.in);
    }
    
    public void displayMenu(){
        while(true){
            System.out.println("\n==CURRENCY EXCHANGE==");
            System.out.println("Menu:");
            System.out.println("1. Exchange currency");
            System.out.println("2. List supported currencies");
            System.out.println("3. Software info");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    exchangeInput();
                    break;
                case 2:
                    currenciesInput();
                    break;
                case 3:
                    softwareInfo();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid Option.");
            }
        }
    }
    
    public void currenciesInput(){
        controller.listCurrencies();
    }
    
    public void exchangeInput(){
        System.out.println("\nPlease enter initial currency (Example: USD): ");
        String initial_currency = currencyReader();
        System.out.println("Please enter amount: ");
        double amount = amountReader();
        System.out.println("Please enter final currency (Example: USD): ");
        String final_currency = currencyReader();
        double final_value = controller.initiateExchange(initial_currency, amount, final_currency);
        if (final_value != 0){
            System.out.println("Conversion value is: " + final_value);
        }
    }
    
    
    private String currencyReader() {
        while (true) {
            String currency = scanner.next().toUpperCase();
            if (currency.matches("[A-Z]{3}")) { // Regex
                return currency;
            } else {
                System.out.print("Invalid input. Try again: ");
            }
        }
    }
    
     private double amountReader() {
        while (true) {
            try {
                return Double.parseDouble(scanner.next());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Try again: ");
            }
        }
    }
     
    public void softwareInfo(){
        System.out.println("\n==SOFTWARE INFO & CREDITS==");
        System.out.println("Software developed in Pure Java, using ExchangeRate API, by Gabriel Jose Pereira.");
        System.out.println("November, 2024");
    }
}
