package csc3380.lsu.rmettig.ssid;

import java.io.Serializable;

public class Item implements Serializable{
   private String name="";
   private int numberOf;
   private int price;
   private String hatName;

   //constructors
   public Item(String name, int cost){
      this.name=name;
      hatName=name;
      numberOf=0;
      price=cost;
   }

   public Item(){}

   //accessors
   public String getName(){return name;}
   public String getHatName(){return hatName;}
   public int getNumber(){return numberOf;}
   public int getPrice(){return price;}

   //mutators
   public void incItem(){numberOf++;}
   public void decItem(){numberOf--;}
   public void setNumber(int num){numberOf=num;}

   @Override
   public String toString(){
      if(name.contains("hat")){
         name=name.substring(0,name.length()-3)+" Hat";
         return name+", "+price+" Shells";
      }
      return name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase()+", "+price+" Shells";
   }
}
