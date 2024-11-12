/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author gabri
 */
public class Exchange {
    private static Exchange instance;
    private String initial_currency;
    private double initial_value;
    private String final_currency;
    private double final_value;
    private double exchange_rate;

    public Exchange(){}
    
    public static Exchange getInstance(){
        if (instance == null) {
            instance = new Exchange();
        }
        return instance;
    }
    
    public void reset(){
       this.initial_currency = null;
       this.initial_value = 0;
       this.final_currency = null;
       this.exchange_rate = 0;
       this.final_value = 0;
    }

    public void setup(String initial_currency, double initial_value, String final_currency) {
        this.initial_currency = initial_currency;
        this.initial_value = initial_value;
        this.final_currency = final_currency;
        this.exchange_rate = 0;
    }

    public void setExchange_rate(double exchange_rate) {
        this.exchange_rate = exchange_rate;
    }
    
    public double exchange(){
        if (this.initial_currency == null || this.final_currency == null || this.exchange_rate == 0) {
        throw new IllegalStateException("Invalid state for exchange.");
        }
        this.final_value = this.initial_value * this.exchange_rate;
        return this.final_value;
    }

    public String getInitial_currency() {
        return initial_currency;
    }

    public String getFinal_currency() {
        return final_currency;
    }
    
    
    
}   
    
