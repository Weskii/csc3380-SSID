package csc3380.lsu.rmettig.ssid;

import java.io.Serializable;

public class Item implements Serializable{
   private String name="";
   private int numberOf;
   private int price;

   //constructors
   public Item(String name, int cost){
      this.name=name;
      numberOf=0;
      price=cost;
   }

   //accessors
   public String getName(){return name;}
   public int getNumber(){return numberOf;}
   public int getPrice(){return price;}

   //mutators
   public void incItem(){numberOf++;}
   public void decItem(){numberOf--;}
   public void setNumber(int num){numberOf=num;}
}
