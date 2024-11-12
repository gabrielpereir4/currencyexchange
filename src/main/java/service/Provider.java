/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author gabri
 */
public class Provider {
    private static Provider instance;
    private String apiKey;
    
    private Provider(){
        Dotenv dotenv = Dotenv.load();
        this.apiKey = dotenv.get("API_KEY");
    }
    
    public static Provider getInstance(){
        if (instance == null){
            instance = new Provider();
        }
        return instance;
    }

    public String getApiKey() {
        return apiKey;
    }
    
    
    
    
}
