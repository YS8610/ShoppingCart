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
    String workingDir;
    //contructor assumed default folder is already created
    public ShoppingCartDB()
    {
        this.workingDir = "./db";
    }
    public ShoppingCartDB(String userDefinedDir)
    {
        this.workingDir = userDefinedDir;
        File file = new File(this.workingDir);
        if (!file.exists()) // if not exist, then exit program
        {
            System.out.println("No such folder. Default folder will be used");
            this.workingDir = "./db";
        }
    }

    //login method - Read file and return shopping cart item in string done
    public List<String> login(String userName) throws FileNotFoundException, IOException
    {
        List<String> cartList = new ArrayList<>();
        String userDBfile = this.workingDir+"/"+userName+".db";
        File file = new File(userDBfile);
        if (file.exists())
        {
            cartList = Files.readAllLines(Paths.get(userDBfile)); //Get content from fileName
            System.out.println("login successfully");
            return cartList;
        }
        else
        {
            file.createNewFile();
            System.out.println("no such user. " + userName+" cart file will be created");
            return cartList;
        }
    }

    //Save method - Write file done
    public void save(List<String> cart, String userName) throws FileNotFoundException, IOException
    {
        String userDBfile = this.workingDir+"/"+userName+".db";
        File file = new File(userDBfile);
        if (file.exists())
        {
            file.delete();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userDBfile, true)))
        {
            for (String s : cart)
            {
                writer.write(s);
                writer.newLine();
            }
        }
    }

    //User method - display all user done
    public String[] users()
    {
        File f = new File(this.workingDir);
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
