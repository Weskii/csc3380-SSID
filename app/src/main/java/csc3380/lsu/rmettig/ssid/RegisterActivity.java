package csc3380.lsu.rmettig.ssid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.net.wifi.WifiManager;
import android.content.Context;


public class RegisterActivity extends AppCompatActivity {


    protected String sharkType="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); //binds the xml file to this class

        //gets content from the elements in the form
        final Button btnRegister = (Button) findViewById(R.id.btnRegister);
        final RadioButton rbtGreatWhite = (RadioButton) findViewById(R.id.rbtGreatWhite);
        final RadioButton rbtHammerHead = (RadioButton) findViewById(R.id.rbtHammerHead);
        final RadioButton rbtWhale = (RadioButton) findViewById(R.id.rbtWhale);
        final ImageView eggWhite = (ImageView) findViewById(R.id.imgGreatWhite);
        final ImageView eggHammer = (ImageView) findViewById(R.id.imgHammerHead);
        final ImageView eggWhale = (ImageView) findViewById(R.id.imgWhale);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(RegisterActivity.this, UserAreaActivity.class);
                if(rbtGreatWhite.isChecked())
                    sharkType = "greatwhite";
                if(rbtHammerHead.isChecked())
                    sharkType = "hammerhead";
                if(rbtWhale.isChecked())
                    sharkType = "whale";

                if (!sharkType.equals("")){
                    registerIntent.putExtra("sharkType", sharkType);
                    registerIntent.putExtra("sendingIntent","RegisterActivity");
                    RegisterActivity.this.startActivity(registerIntent);
                    finish();
                }else{
                    new AlertDialog.Builder(RegisterActivity.this)
                          .setTitle("Error")
                          .setMessage("You must select an egg!!")
                          .setNeutralButton("Ok", null)
                          .create()
                          .show();
                }
            }
        });

        rbtGreatWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbtGreatWhite.setChecked(true);
                rbtHammerHead.setChecked(false);
                rbtWhale.setChecked(false);
            }
        });

        rbtHammerHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbtHammerHead.setChecked(true);
                rbtGreatWhite.setChecked(false);
                rbtWhale.setChecked(false);
            }
        });

        rbtWhale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbtWhale.setChecked(true);
                rbtGreatWhite.setChecked(false);
                rbtHammerHead.setChecked(false);
            }
        });




        eggWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbtGreatWhite.setChecked(true);
                rbtHammerHead.setChecked(false);
                rbtWhale.setChecked(false);
            }
        });

        eggHammer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbtHammerHead.setChecked(true);
                rbtGreatWhite.setChecked(false);
                rbtWhale.setChecked(false);
            }
        });

        eggWhale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbtWhale.setChecked(true);
                rbtGreatWhite.setChecked(false);
                rbtHammerHead.setChecked(false);
            }
        });
    }
}
