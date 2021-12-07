package com.day1;

import java.util.ArrayList;
import java.util.List;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.*;

public class ShoppingCart 
{
    // function to parse add input string and return the string back
    static public String[] parseAdd(String input)
    {
        if (input.contains(","))
        {
            String[] splitInput = input.split(",");
            String[] parseInput = new String[splitInput.length];
            for (int i=0, n=splitInput.length; i<n; i++)
            {
                parseInput[i] = splitInput[i].trim();
            }
            return parseInput;
        }
        else
        {
            String[] parseInput = {input.trim()};
            return parseInput;
        }
    }
    // function to parse delete input string and return the string back
    static public String parseDelete(String input)
    {
        String[] parseInput = input.split(" ");
        return parseInput[1];
    }
    public static void main(String[] args) throws IOException, FileNotFoundException
    {
        List<String> cart = new ArrayList<>();
        System.out.println("Welcome to your shopping cart");
        ShoppingCartDB dataBase = new ShoppingCartDB();

        Console cons = System.console();
        String input = cons.readLine("");

        if (null!=args && args.length>0)
        {
            //read file
        }
        else
        {
            //create file
        }

        String fileName = "H:\\ys\\Desktop\\NUSISS\\JavaCode\\shoppingcart\\src\\main\\java\\com\\day1\\fred.db";
        cart = dataBase.login(fileName);

        //command input
        while (!input.equals("end"))
        {
            // list function
            if (input.toLowerCase().trim().equals("list"))
            {
                if (cart.isEmpty())
                {
                    System.out.println("Your cart is empty");
                }
                for (int i =0, n=cart.size();i<n;i++)
                {
                    int listno = i +1;
                    System.out.println(listno + ". " + cart.get(i));
                }
            }
            // Add function
            else if (input.length()==3 && input.substring(0,3).toLowerCase().equals("add"))
            {
                System.out.println("Item is required");
            }
            else if (input.length()>=4 && input.substring(0,3).toLowerCase().equals("add") && input.substring(3,4).equals(" "))
            {
                String[] arrayItem = parseAdd(input.substring(3,input.length()));
                for (String item : arrayItem)
                {
                    System.out.println(item);
                    if (cart.contains(item.toLowerCase())) //check for duplicate
                    {
                        System.out.println(item + " is already in the cart.");
                    }
                    else if (item.trim().equals("")) //Ignore blank entry
                    {
                        continue;
                    }
                    else
                    {
                        cart.add(item);
                    }
                }
            }
            // Delete function
            else if (input.length()>=7 && input.substring(0,6).toLowerCase().equals("delete"))
            {
                String a = parseDelete(input);
                if (Pattern.matches("\\d+",a)) // check whether the delete input is a integer and convert string to integer if true
                {
                    int deleteIndex = Integer.parseInt(a);
                    if (deleteIndex > 0 && deleteIndex <= cart.size()) // check to ensure that the deleteIndex is not more than the cart length
                    {
                        System.out.println("removing " + deleteIndex + ". " + cart.get(deleteIndex-1));
                        cart.remove(deleteIndex-1);
                    }
                    else
                    {
                        System.out.println("Incorrect item index.");
                    }
                }
                else
                {
                    System.out.println("Input for delete is not an integer");
                }
            }
            // Logout Function
            else if (input.length()>=6 && input.substring(0,6).toLowerCase().equals("logout"))
            {
                dataBase.logout(cart,fileName);
                break;
            }
            input = cons.readLine("");
        }
    }
}