package csc3380.lsu.rmettig.ssid;

      import android.content.Context;
      import android.content.DialogInterface;
      import android.content.Intent;
      import android.content.IntentFilter;
      import android.content.SharedPreferences;
      import android.net.wifi.ScanResult;
      import android.net.wifi.WifiManager;
      import android.preference.PreferenceManager;
      import android.support.v7.app.AlertDialog;
      import android.support.v7.app.AppCompatActivity;
      import android.os.Bundle;
      import android.view.View;
      import android.view.animation.Animation;
      import android.view.animation.AnimationUtils;
      import android.widget.ImageView;
      import android.widget.TextView;
      import android.content.BroadcastReceiver;

      import com.google.gson.Gson;

      import java.util.List;
public class UserAreaActivity extends AppCompatActivity {
   Shark myShark;
   WifiManager wfm;
   Gson gson=new Gson();
   public static SharedPreferences sharkStats;
   BroadcastReceiver WifiRec = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent){}
   };

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_user_area);
      final Animation bubbleAnim=AnimationUtils.loadAnimation(this,R.anim.bubbleanim);
      final Animation petAnim=AnimationUtils.loadAnimation(this,R.anim.sharkwiggle);
      final Animation heartAnim=AnimationUtils.loadAnimation(this,R.anim.heartfade);
      sharkStats=PreferenceManager.getDefaultSharedPreferences(this);
      wfm = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
      registerReceiver(WifiRec, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
      wfm.startScan();
      final String sendingIntent = getIntent().getStringExtra("sendingIntent");
      if (sendingIntent.equals("RegisterActivity")) {
         myShark = new Shark(getIntent().getStringExtra("sharkType"), sharkStats);
         myShark.saveShark();
      } else if (sendingIntent.equals("Welcome")) {
         myShark = new Shark(sharkStats.getString("species",""),sharkStats);
         myShark.loadShark();
      } else if (sendingIntent.equals("Inventory")) {
         String jShark=getIntent().getStringExtra("myShark");
         myShark=gson.fromJson(jShark, Shark.class);
      }

      final ImageView btnPet = (ImageView) findViewById(R.id.btnPet);
      final ImageView btnInventory = (ImageView) findViewById(R.id.btnInventory);
      final ImageView imgShark = (ImageView) findViewById(R.id.myShark);
      final ImageView btnCoin = (ImageView) findViewById(R.id.btnCoin);
      final TextView txtHappiness = (TextView) findViewById(R.id.txtHappiness);
      final TextView txtShellCount = (TextView) findViewById(R.id.txtShellCount);
      final ImageView imgHearts=(ImageView) findViewById(R.id.hearts);
      final ImageView imgHat=(ImageView) findViewById(R.id.hat);

      switch(myShark.getHat()){
         case "":
            imgHat.setVisibility(View.INVISIBLE);
            break;
         case "cowboyhat":
            imgHat.setVisibility(View.VISIBLE);
            imgHat.setImageResource((R.drawable.cowboyhat));
            break;
         case "witchhat":
            imgHat.setVisibility(View.VISIBLE);
            imgHat.setImageResource((R.drawable.witchhat));
            break;
         case "copyrighthat":
            imgHat.setVisibility(View.VISIBLE);
            imgHat.setImageResource(R.drawable.copyrightinfringementhat);
            break;
         case "piratehat":
            imgHat.setVisibility((View.VISIBLE));
            imgHat.setImageResource(R.drawable.piratehat);
            break;
         case "tophat":
            imgHat.setVisibility(View.VISIBLE);
            imgHat.setImageResource(R.drawable.tophat);
            break;
      }
      txtShellCount.setText(String.valueOf(myShark.getCoins()));
      txtHappiness.setText(String.valueOf(myShark.getHapp()));

      switch ("greatwhite"){
         case "greatwhite":
            imgShark.setImageResource(R.drawable.greatwhite);
            break;
         case "hammerhead":
            imgShark.setImageResource(R.drawable.hammerhead);
            break;
         case "whale":
            imgShark.setImageResource(R.drawable.whaleshark);
            break;
      }

      btnPet.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            btnPet.startAnimation(bubbleAnim);
            myShark.petShark();
            imgShark.startAnimation(petAnim);
            imgHearts.setVisibility(View.VISIBLE);
            imgHearts.startAnimation(heartAnim);
            imgHearts.setVisibility(View.INVISIBLE);
            txtHappiness.setText(String.valueOf(myShark.getHapp()));
           // myShark.saveShark();
         }
      });

      btnCoin.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            btnCoin.startAnimation(bubbleAnim);
            List<ScanResult> WifiRes=wfm.getScanResults();
            myShark.coinScan(WifiRes);
            AlertDialog.Builder showNewNamesBuild=new AlertDialog.Builder(UserAreaActivity.this);
            if(myShark.newNames.isEmpty()){
               showNewNamesBuild.setMessage("No new SSIDs found :(");
               showNewNamesBuild.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {
                     dialog.cancel();
                  }
               });
               AlertDialog showNewNames=showNewNamesBuild.create();
               showNewNames.show();
            }else {
               showNewNamesBuild.setTitle("New SSIDs Found!");
               showNewNamesBuild.setMessage(myShark.newNames.toString());
               showNewNamesBuild.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {
                     dialog.cancel();
                  }
               });
               AlertDialog showNewNames=showNewNamesBuild.create();
               showNewNames.show();
            }
            txtShellCount.setText(String.valueOf(myShark.getCoins()));
            myShark.saveShark();
         }
      });

      btnInventory.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            btnInventory.startAnimation(bubbleAnim);
            Intent inventoryIntent = new Intent(UserAreaActivity.this, InventoryActivity.class);
            Gson gson=new Gson();
            String jShark=gson.toJson(myShark);
            inventoryIntent.putExtra("myShark", jShark);
            UserAreaActivity.this.startActivity(inventoryIntent);
            finish();
         }
      });
   }
}