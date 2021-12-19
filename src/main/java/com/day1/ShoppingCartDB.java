package com.day1;

import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

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
        Path file = Paths.get(this.workingDir);
        if (!Files.isDirectory(file) && !Files.exists(file)){
            System.out.println("No such folder. Default folder will be used");
            this.workingDir = "./db";
        }
    }

    public List<String> login(String userName) throws IOException{
        String userDBfileName = this.workingDir+"/"+userName+".db";
        Path userDBfile = Paths.get(userDBfileName);
        if (Files.exists(userDBfile) && !Files.isDirectory(userDBfile)){
            List<String>cartlist = Files.readAllLines(userDBfile);
            System.out.println("login successfully");
            return cartlist;
        }
        else{
            Files.createFile(userDBfile);
            System.out.println("no such user. " + userName+" cart file will be created");
            List<String> cartlist = new ArrayList<>();
            return cartlist;
        }
    }

    //Save method - Write file done
    public void save(List<String> cart, String userName) throws FileNotFoundException, IOException
    {
        String userDBfile = this.workingDir+"/"+userName+".db";
        Path file = Paths.get(userDBfile);
        if (!Files.exists(file) && !Files.isDirectory(file)){
            Files.createFile(file);
        }
        BufferedWriter writer = Files.newBufferedWriter(file);
        cart.forEach(s -> {
                try{
                    writer.write(s);
                    writer.newLine();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            });
            writer.close();
        }

    public List<String> users() throws IOException{
        Path dbDir = Paths.get(this.workingDir);
        List<String> userList = new ArrayList<>();
        Files.list(dbDir).forEach(p ->{
            String userFileName = p.getFileName().toString();
            String username = userFileName.substring(0,userFileName.length()-3);
            userList.add(username);
            System.out.println(username);
        });
        return userList;
    }
}
