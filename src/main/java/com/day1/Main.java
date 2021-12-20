package com.day1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static private String cmdParser(String[] arg){
        String a = "./db";
        if (arg.length==1){
            a=arg[0];
        }
        return a;
    }
    public static void main(String[] args) throws IOException{
        ShoppingCartDB db = new ShoppingCartDB(cmdParser(args));
        List<String> cartContent = new ArrayList<>();
        ShoppingCart cart = new ShoppingCart(cartContent);
        Scanner scan = new Scanner(System.in);
        String userloggedIn = "";

        System.out.println("Welcome to your shopping cart");
        while(true){
            String cmd = scan.nextLine();
            if (cmd.equalsIgnoreCase("end")){
                scan.close();
                break;
            }
            try{
                String cleanCMD = cmd.trim().toLowerCase();
                if (cleanCMD.length()<2){
                    System.out.println("Input Error");
                }
                else{
                    // List
                    if (cleanCMD.equalsIgnoreCase("list")){
                        cart.list();
                    }
                    // add
                    else if (cleanCMD.substring(0,3).equalsIgnoreCase("add")){
                        String[] parsedAdd = cleanCMD.substring(3).trim().split(",");
                        if (parsedAdd.length>0){
                            List<String> listtoAdd = Arrays.asList(parsedAdd);
                            cart.add(listtoAdd);
                        }
                        else{
                            System.out.println("there is no item to add");
                        }
                    }
                    // save
                    else if (cleanCMD.substring(0,4).equalsIgnoreCase("save")){
                        db.save(userloggedIn, cart.cart );
                    }
                    // users
                    else if (cleanCMD.substring(0,5).equalsIgnoreCase("users")){
                        List<String> listofUser = db.getUsers();
                        if (listofUser.isEmpty() || listofUser==null){
                            System.out.println("no user is being registered");
                        }
                        else{
                            System.out.println("The following users are registered");
                            for (int i=0,n=listofUser.size();i<n;i++){
                                String userFileName = listofUser.get(i);
                                System.out.println((i+1)+". "+userFileName.substring(0,userFileName.length()-3));
                            }
                        }
                    }
                    // login
                    else if (cleanCMD.substring(0,5).equalsIgnoreCase("login")){
                        if (cleanCMD.substring(5).isBlank() || cleanCMD.substring(5).isEmpty()){
                            System.out.println("Please enter username to login");
                        }
                        else{
                            String username = cleanCMD.substring(5).trim();
                            cart = new ShoppingCart(db.getCart(username));
                            System.out.println("hello "+ username +" your cart contains the following items");
                            cart.list();
                            userloggedIn = username;
                        }
                    }
                    // delete
                    else if (cleanCMD.substring(0,6).equalsIgnoreCase("delete")){
                        String[] parsedDelete = cleanCMD.split(" ");
                        if (parsedDelete.length==2){
                            int indextoRemove = Integer.parseInt(parsedDelete[1]);
                            cart.delete(indextoRemove);
                        }
                    }
                }
            }
            catch (NumberFormatException e){
                System.out.println("input error");
            }
            catch(StringIndexOutOfBoundsException e){
                System.out.println("input error");
            }
        }
    }
}