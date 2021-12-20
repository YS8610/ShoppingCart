import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class ShoppingCart {
    List<String> cart;
//constructor
    public ShoppingCart(List<String> cart){
        this.cart = cart;
    }
//method
    public void add(List<String> items){
        for (String s : items){
            if(!s.isBlank()){
                String trimeditem = s.trim().toLowerCase();
                if (this.cart.isEmpty()){this.cart.add(trimeditem);}
                else{
                    if (this.cart.contains(trimeditem)){
                        System.out.println(trimeditem+" is already in the cart.");
                    }
                    else{
                        this.cart.add(trimeditem);
                    }
                }
            }
        }
    }

    public void list(){
        if (this.cart.isEmpty()){
            System.out.println("Shopping cart is empty");
        }
        else{
            int i =1;
            for (String s : this.cart){
                System.out.println(i+": "+s);
                i++;
            }
        }
    }

    public void delete(int index){
        if (index <1 || this.cart.size()+1<index){
            System.out.println("Error when deleting due to input error");
        }
        else{
            System.out.println(this.cart.get(index-1)+" is deleted");
            this.cart.remove(index-1);
        }
    }

    public void parser(String cmd){
        String cleanCMD = cmd.trim().toLowerCase();
        if (cleanCMD.length()<2){
            System.out.println("Input Error");
        }
        else{
            if (cleanCMD.equals("list")){
                list();
            }
            else if (cleanCMD.substring(0,3).equals("add")){
                String[] parsedAdd = cleanCMD.substring(3).trim().split(",");
                if (parsedAdd.length>0){
                    List<String> listtoAdd = Arrays.asList(parsedAdd);
                    add(listtoAdd);
                }
                else{
                    System.out.println("there is no item to add");
                }
            }
            else if (cleanCMD.substring(0,6).equals("delete")){
                String[] parsedDelete = cleanCMD.split(" ");
                if (parsedDelete.length==2){
                    int indextoRemove = Integer.parseInt(parsedDelete[1]);
                    delete(indextoRemove);
                }
            }
        }
    }
//main
    public static void main(String[] args) {
        System.out.println("Welcome to your shopping cart");
        List<String> cartContent = new ArrayList<>();
        ShoppingCart cart = new ShoppingCart(cartContent);
        Scanner scan = new Scanner(System.in);
        while(true){
            String cmd = scan.nextLine();
            if (cmd.equals("end")){
                scan.close();
                break;
            }
            try{
                cart.parser(cmd);
            }
            catch (NumberFormatException e){
                System.out.println("input error");
            }
        }
    }
}
