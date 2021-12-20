package com.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartDB
{
    String dir;
    // Constructor
    public ShoppingCartDB(){
        this.dir = "./db";
    }
    public ShoppingCartDB(String dir){
        Path folder = Path.of(dir);
        if (!Files.exists(folder) || !Files.isDirectory(folder)){
            try {
                Files.createDirectories(folder);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error! Folder cannot be created");
                System.exit(1);
            }
        }
        this.dir =dir;
    }
    // User function
    public List<String> getUsers(){
        Path folder = Path.of(this.dir);
        List<String> listofUser = new ArrayList<>();
        try {
            listofUser = Files.list(folder)
                .map(s -> s.getFileName().toString())
                .filter(s -> s.substring(s.length()-3, s.length()).equals(".db"))
                .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listofUser;
    }
    // login function
    public List<String> getCart(String userName) throws IOException{
        Path userDB = Path.of(this.dir+"/"+userName+".db");
        List<String> userCart = new ArrayList<>();
        if (Files.exists(userDB) ){
            userCart = Files.readAllLines(userDB);
        }
        else{
            System.out.println("User file does not exist. File will be created.");
            Files.createFile(userDB);
        }
        return userCart;
    }
    // save function
    public void save(String username, List<String> cart){
        if (username.isBlank()||username==null){
            System.out.println("Please login before you can save your cart");
        }
        else{
            // cart.add(0, this.dir+"/"+username+".db");
            String cartString ="";
            for (String s:cart){
                cartString += s+"\n";
            }
            writeFile(this.dir+"/"+username+".db",cartString);
            System.out.println("Cart is saved for "+username);
        }
    }
    private void writeFile(String userDbDir, String toWrite){
        Path userFile = Path.of(userDbDir);
        try {
            Files.writeString(userFile,toWrite);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}