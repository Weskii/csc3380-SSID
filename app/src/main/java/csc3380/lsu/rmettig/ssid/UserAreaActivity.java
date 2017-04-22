package csc3380.lsu.rmettig.ssid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class UserAreaActivity extends AppCompatActivity {

    Shark myShark = new Shark("whaleshark");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final ImageView btnPet = (ImageView) findViewById(R.id.btnPet);
        final ImageView btnInventory = (ImageView) findViewById(R.id.btnInventory);

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
                //Intent inventoryIntent = new Intent(UserAreaActivity.this, InventoryActivity.class);
                //UserAreaActivity.this.startActivity(inventoryIntent);
            }
        });

    }
}
