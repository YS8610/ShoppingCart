package com.day1;

import java.util.List;

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
}
