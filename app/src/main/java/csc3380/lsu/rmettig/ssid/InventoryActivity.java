package csc3380.lsu.rmettig.ssid;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

public class InventoryActivity extends ListActivity {
   Gson gson=new Gson();
   Shark myShark;

   @Override
   protected void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      setContentView(R.layout.content_inventory);
      String jShark=getIntent().getStringExtra("myShark");
      myShark=gson.fromJson(jShark, Shark.class);
      ArrayAdapter<Item> myAdapter=new ArrayAdapter<>(this, R.layout.list_row,R.id.firstLine ,myShark.getInventory());
      setListAdapter(myAdapter);
   }
   @Override
   protected void onListItemClick(ListView list, View view, int position, long id){
      final Item selectedItem=(Item)getListView().getItemAtPosition(position);
      final AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle(selectedItem.getName());
            adb.setMessage("Buy one?");
            adb.setPositiveButton("Buy", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                  adb.setMessage(myShark.buyItem(selectedItem));
               }
            });
            adb.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                  dialog.dismiss();
               }
            });
            adb.create();
            adb.show();
   }

   @Override
    public void onBackPressed(){
       String jShark=gson.toJson(myShark);
       Intent userAreaIntent = new Intent(InventoryActivity.this, UserAreaActivity.class);
       userAreaIntent.putExtra("myShark", jShark);
       userAreaIntent.putExtra("sendingIntent","Inventory");
       InventoryActivity.this.startActivity(userAreaIntent);
       finish();
   }
}
