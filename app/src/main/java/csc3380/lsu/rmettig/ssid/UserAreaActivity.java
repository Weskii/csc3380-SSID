package csc3380.lsu.rmettig.ssid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class UserAreaActivity extends AppCompatActivity {

    Shark myShark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        myShark = new Shark("greatwhite"); //for testing purposes

        final ImageView btnPet = (ImageView) findViewById(R.id.btnPet);
        final ImageView btnInventory = (ImageView) findViewById(R.id.btnInventory);
        final ImageView imgShark = (ImageView) findViewById(R.id.myShark);

        if(myShark.getSpecies().equals("greatwhite"))
            imgShark.setImageResource(R.drawable.greatwhite);
        if(myShark.getSpecies().equals("hammerhead"))
            imgShark.setImageResource(R.drawable.hammerhead);
        if(myShark.getSpecies().equals("whale"))
            imgShark.setImageResource(R.drawable.whaleshark);

        btnPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myShark.petShark();
            }
        });

        btnInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new UI
                Intent inventoryIntent = new Intent(UserAreaActivity.this, InventoryActivity.class);
                UserAreaActivity.this.startActivity(inventoryIntent);
            }
        });


    }
}
