package csc3380.lsu.rmettig.ssid;

import android.content.SharedPreferences;
import android.net.wifi.ScanResult;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.io.*;
import java.util.List;

public class Shark implements Serializable{
   //private variables
   private long timeStamp=0;
   private int coins=1000;
   private int happ=0;
   private boolean hasHat=false;
   private String hatName="";
   private String species="";
   public ArrayList<Item> inventory=new ArrayList<>();
   public ArrayList<String> newNames=new ArrayList<>();
   private ArrayList<String> registeredNames = new ArrayList<>();
   private transient SharedPreferences sharkStats;


   //constructors
   public Shark(String type, SharedPreferences pref){
      species=type;
      sharkStats=pref;
      initializeInventory();
   }

   public Shark(){}

   //accessors
   public int getHapp(){return happ;}
   public String getHat(){return hatName;}
   public String getSpecies(){return species;}
   public int getCoins(){return coins;}
   public ArrayList<Item> getInventory(){return inventory;}

   //basic mutators
   public void setCoins(int delta){coins=coins+delta;}
   public void setHapp(int delta){if(happ<1000) happ=happ+delta;}
   public void petShark(){setHapp(5);}
   public void setHat(boolean set){hasHat=set;}

    public void initializeInventory(){
        inventory.add(new Item("candy", 25));
        inventory.add(new Item("cookie", 50));
        inventory.add(new Item("fish", 5));
        inventory.add(new Item("icecream", 200));
        inventory.add(new Item("lollipop", 100));
        inventory.add(new Item("cowboyhat", 200));
        inventory.add(new Item("witchhat", 400));
        inventory.add(new Item("copyrighthat", 1000));
        inventory.add(new Item("piratehat", 800));
        inventory.add(new Item("tophat",600));
    }

   //hat functions
   public void giveHat(String hatName){
      this.hatName=hatName;
      setHat(true);
   }

   public String buyItem(Item item){
      if (item.getPrice()>coins)
         return "Not enough money!";
      if(item.getHatName().contains("hat")){
         giveHat(item.getHatName());
      }
      setHapp(item.getPrice()/2);
      coins=coins-item.getPrice();
      return "Bought one!";
   }

   //save data to file
   public void saveShark(){
      SharedPreferences.Editor statEdit=sharkStats.edit();
      statEdit.clear();
      Gson gson=new Gson();
      String inv=gson.toJson(inventory);
      statEdit.putInt("shells",coins);
      statEdit.putInt("happ",happ);
      statEdit.putBoolean("hasHat",hasHat);
      statEdit.putLong("timeStamp",timeStamp);
      statEdit.putString("hatName",hatName);
      statEdit.putString("species",species);
      statEdit.putString("inventory",inv);
      statEdit.putBoolean("isFirstRun",false);
      statEdit.apply();
   }

   //load data from file
   public void loadShark(){
      coins=sharkStats.getInt("shells",0);
      happ=sharkStats.getInt("happ",0);
      hasHat=sharkStats.getBoolean("hasHat",false);
      hatName=sharkStats.getString("hatName",null);
      species=sharkStats.getString("species",null);
      timeStamp=sharkStats.getLong("timeStamp",0);
      Gson gson=new Gson();
      inventory=gson.fromJson(sharkStats.getString("inventory",null), ArrayList.class);
   }

   public void coinScan(List<ScanResult> scanList){
      newNames.clear();
      int cnt=0;
      long currTime=System.currentTimeMillis();
      //purge old names from list
      if(currTime-timeStamp > 14400000) {
         registeredNames.clear();
         timeStamp=currTime;
      }
      //add new names to list
      for (ScanResult res:scanList){
         if(!registeredNames.contains(res.SSID)) {
            registeredNames.add(res.SSID);
            newNames.add(res.SSID);
            cnt++;
         }
      }
      coins=coins+cnt;
   }
}//end shark class