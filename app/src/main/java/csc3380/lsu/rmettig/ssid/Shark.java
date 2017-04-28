package csc3380.lsu.rmettig.ssid;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import java.util.ArrayList;
import java.io.*;
import java.util.List;

public class Shark implements Serializable{
   //private variables
   private long timeStamp=0;
   private int coins=0;
   private int happ=0;
   private int hunger=0;
   private boolean hasHat=false;
   private String hatName="";
   private String species="";
   public ArrayList<Item> inventory=new ArrayList<>();
   public ArrayList<String> newNames=new ArrayList<>();
   private ArrayList<String> registeredNames = new ArrayList<>();
   private WifiManager wifeMan;
   private File sharkStorage;

   //constructors
   public Shark(String type, File save){
      sharkStorage=save;
      species=type;
      initializeInventory();
   }

   public Shark(File save){
      sharkStorage=save;
      initializeInventory();
   }

   //accessors
   public int getHapp(){return happ;}
   public int getHunger(){return hunger;}
   public boolean getHat(){return hasHat;}
   public String getSpecies(){return species;}
   public int getCoins(){return coins;}

   //basic mutators
   public void setCoins(int delta){coins=coins+delta;}
   public void setHapp(int delta){if(happ<1000) happ=happ+delta;}
   public void setHunger(int delta){if(hunger>0) hunger=hunger+delta;}
   public void petShark(){setHapp(5);}
   public void setHat(boolean set){hasHat=set;}

    public void initializeInventory(){
        inventory.add(new Item("candy", 25));
        inventory.add(new Item("cookie", 50));
        inventory.add(new Item("fish", 10));
        inventory.add(new Item("cutefish", 75));
        inventory.add(new Item("icecream", 200));
        inventory.add(new Item("lollipop", 100));
        inventory.add(new Item("cowboyhat", 200));
        inventory.add(new Item("witchhat", 400));
        inventory.add(new Item("copyrightinfringementhat", 1000));
        inventory.add(new Item("piratehat", 800));
        inventory.add(new Item("tophat",600));
    }

   //hat functions
   public void giveHat(String hatName){
      this.hatName=hatName;
      setHat(true);
   }
   
   //inventory management
   public Item invItem(String name){
      return inventory.get(inventory.indexOf(name));
   }

   public void buyItem(String name){
      if (invItem(name).getPrice()>coins) return;
      invItem(name).incItem();
      setCoins(invItem(name).getPrice());
   }

   public void useItem(String name){
      if (invItem(name).getNumber()==0) return;
      if (invItem(name).getName().contains("hat")){
         giveHat(name);
      }
      else {
         invItem(name).decItem();
         setHapp(invItem(name).getPrice() / 2);
         setHunger(-10);
      }
   }

   //save data to file
   public void saveShark(){
      timeStamp = System.currentTimeMillis();
      String tmp=Long.toString(timeStamp)+coins+" "+happ+" "+hunger+" "+species+" ";
      if (hasHat) {tmp=tmp+"1"+hatName+"\n";}
      else tmp=tmp+"0\n";
      for (Item e:inventory){tmp=tmp+e.getNumber()+" ";}

      try{
         FileOutputStream out=new FileOutputStream(sharkStorage);
         out.write(tmp.getBytes());
         out.close();
      }catch(Exception e){/*ignore exceptions*/}
   }

   //load data from file
   public void loadShark(){
      if(sharkStorage.exists()) {
         long newTimeStamp = System.currentTimeMillis();
         String inString = "";
         String tmp = "";
         try {
            FileReader in = new FileReader(sharkStorage);
            BufferedReader read = new BufferedReader(in);
            while ((tmp = read.readLine()) != null) {
               inString = inString + tmp;
            }
         } catch (Exception e) {/*ignore exceptions*/}
         String[] inStringArr = inString.split("\\s+");
         timeStamp = Long.parseLong(inStringArr[0]);
         coins = Integer.parseInt(inStringArr[1]);
         happ = Integer.parseInt(inStringArr[2]);
         hunger = Integer.parseInt(inStringArr[3]);
         species = inStringArr[4];
         happ = happ - ((int) newTimeStamp - (int) timeStamp) / 60000;
         hunger = hunger + ((int) newTimeStamp - (int) timeStamp) / 60000;
         int cnt = 0;
         for (Item e : inventory) {
            e.setNumber(Integer.parseInt(inStringArr[4 + cnt]));
            cnt++;
         }
      }
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