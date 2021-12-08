package com.day1;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class ShoppingCartDB
{
    //Read file and return shopping cart item in string 
    public List<String> login(String fileName) throws FileNotFoundException, IOException
    {
        List<String> list1 = new ArrayList<>();
        list1 = Files.readAllLines(Paths.get(fileName)); //Get content from fileName
        // for (String s : list1)
        // {
        //     System.out.println(s);
        // }
        return list1;
    }

    //Write file 
    public void save(List<String> cart, String fileName) throws FileNotFoundException, IOException
    {
        File file = new File(fileName);
        if (file.exists())
        {
            file.delete();
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true)))
        {
            for (String s : cart)
            {
                writer.write(s);
                writer.newLine();
            }
        }
    }

    static public String[] users(String directory)
    {
        File f = new File(directory);
        String[] pathnames = f.list();
        String[] userName = new String[pathnames.length]; 
        int i=0;
        for (String pathname : pathnames)
        {
            System.out.println(pathname.substring(0,pathname.length()-3));
            userName[i] = (pathname.substring(0,pathname.length()-3));
            i++;
        }
        return userName;
    }
}