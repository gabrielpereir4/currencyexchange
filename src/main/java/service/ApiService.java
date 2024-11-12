/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 *
 * @author gabri
 */
public class ApiService {
    private static ApiService instance;
    private Provider provider;
    private static final String api_url = "https://v6.exchangerate-api.com/v6/";
    
    private ApiService(){
        provider = Provider.getInstance();
    }
    
    public static ApiService getInstance(){
        if (instance == null){
            instance = new ApiService();
        }
        return instance;
    }
    
    public double getExchange(String fromCurrency, String toCurrency){
        String requestUrl = api_url + provider.getApiKey() + "/pair/" + fromCurrency + "/" + toCurrency;
        
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // Casting
            connection.setRequestMethod("GET");
            
            // API call is done here
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read API response, as it comes in bytes, not in a String
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
            
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            in.close();
            
            JSONObject jsonResponse = new JSONObject(response.toString());
            return jsonResponse.getDouble("conversion_rate");
            }
            else{
                System.out.println("API call failed: Code " + responseCode);
                return 0;
            }
        }
        catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
    }
    
    public void getSupportedCurrencies(){
        String requestUrl = api_url + provider.getApiKey() + "/codes";
        
         try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject jsonResponse = new JSONObject(response.toString());

                if (jsonResponse.getString("result").equals("success")) {
                    JSONArray supportedCodes = jsonResponse.getJSONArray("supported_codes");
                    System.out.println("\n==SUPPORTED CURRENCIES==");
                    for (int i = 0; i < supportedCodes.length(); i++) {
                        JSONArray currency = supportedCodes.getJSONArray(i);
                        String code = currency.getString(0);
                        String name = currency.getString(1);
                        System.out.println(code + " - " + name);
                    }
                } else {
                    System.out.println("API call error.");
                }
            } else {
                System.out.println("API call error: Code " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("API connection error: " + e.getMessage());
        }
    }    
}
