package csc3380.lsu.rmettig.ssid;

public class Item{
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
