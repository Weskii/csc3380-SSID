package csc3380.lsu.rmettig.ssid;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity {
   public static SharedPreferences sharkStats;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
           ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},0);
        }
      sharkStats=PreferenceManager.getDefaultSharedPreferences(this);
      if(!sharkStats.getBoolean("isFirstRun",true)){
          Intent userAreaIntent = new Intent(Welcome.this, UserAreaActivity.class);
          userAreaIntent.putExtra("sendingIntent","RegisterActivity");
          startActivity(userAreaIntent);
          finish();
       }
       else {
          final Button btnRegister = (Button) findViewById(R.id.btnRegisterNew);
          btnRegister.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                Intent registerIntent = new Intent(Welcome.this, RegisterActivity.class);
                startActivity(registerIntent);
                finish();
             }
          });
       }
    }

   @Override
   public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
       if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){}
    }
}
