/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiblijava;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.JsonParser;
import java.util.List;

/**
 *
 * @author WarSpite
 */
public class ShibliJava {
    private static final String baseUrl = "https://ghibliapi.herokuapp.com";
    private static List<Movie> movieList;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        movieList = getMovieList();
        Scanner sc = new Scanner(System.in);
        System.out.println("IST411 homework  Enter \"quit\" to quit this program");
        System.out.println("Hello, here is the movie list");
        System.out.println("");
        movieList.forEach((m) -> {
            System.out.println(m.getTitle()+"\n"+m.getDescription()+"\n\n");
        });
        int index = -1;
        while (true) {
            System.out.print("Select a movie and I will display the information: ");
            String name = sc.nextLine();
            name = name.toLowerCase();

            if (name.equals("quit")) {//quit
                System.out.println("Thank you for using this program");
                break;
            }
            for(int i = 0;i<movieList.size();i++){
                if(movieList.get(i).getTitle().toLowerCase().equals(name.toLowerCase())){
                    index = i;
                }
            }
            
            if(index>=0){
                System.out.println(movieList.get(index).getTitle()+"\n"+
                            movieList.get(index).getProducer()+"\n"+
                            movieList.get(index).getReleaseDate()+"\n"+
                            movieList.get(index).getDescription());
                index = -1;
            }else{
                System.out.println("Sorry, Movie not found");
            }
            
            System.out.println("");

        }
         
         
    }
    
    private static ArrayList<Movie> getMovieList() {
        ArrayList<Movie> tempList = new ArrayList<>();
        try {
            HttpURLConnection httpConn = getConnection("/films");
            BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            httpConn.connect();
            String str;
            String str1 = "";
            while ((str = in.readLine()) != null) {
                str1 += str;
            }
            JsonParser parser = new JsonParser();
            JsonArray movieArray = parser.parse(str1).getAsJsonArray();
            for (JsonElement s : movieArray) {
                JsonObject movieObj = s.getAsJsonObject();
                tempList.add(new Movie(movieObj.get("title").getAsString(),
                        movieObj.get("description").getAsString(),
                        movieObj.get("release_date").getAsString(),
                        movieObj.get("producer").getAsString()));
            }
        } catch (IOException ex) {
            Logger.getLogger(ShibliJava.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempList;
    }
    
    private static HttpURLConnection getConnection(String type) {
        HttpURLConnection httpConn = null;
        try {
            URL url = new URL(baseUrl + type);
            httpConn = (HttpURLConnection) url.openConnection();

            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Accept-Charset", "UTF-8");
            httpConn.setRequestProperty("Content-type", "application/json; utf-8");
            httpConn.setRequestProperty("User-Agent", "Mozilla/5.0");
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return httpConn;
    }
    
    
    
}
