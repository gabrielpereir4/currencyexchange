/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import service.ApiService;
import model.Exchange;

/**
 *
 * @author gabri
 */
public class ExchangeController {
    private static ExchangeController instance;
    private ApiService service = ApiService.getInstance();
    
    private ExchangeController(){}
    
    public static ExchangeController getInstance(){
        if (instance == null){
            instance = new ExchangeController();
        }
        return instance;
    }
    
    public double initiateExchange(String initial_currency, double amount, String final_currency){
        Exchange exchange = Exchange.getInstance();
        exchange.reset();
        exchange.setup(initial_currency, amount, final_currency);
        
        double ex_rate = service.getExchange(exchange.getInitial_currency(), exchange.getFinal_currency());
        try {
            exchange.setExchange_rate(ex_rate);
            double final_value = exchange.exchange();
            return final_value;
        }
        catch (Exception e){
            System.err.println("Error completing exchange: " + e.getMessage());
            return 0;
        }
    }
    
    public void listCurrencies(){
        service.getSupportedCurrencies();
    }
}
