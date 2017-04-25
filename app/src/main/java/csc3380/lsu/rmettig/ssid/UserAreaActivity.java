package csc3380.lsu.rmettig.ssid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    Shark myShark;
    long lastTimeStamp = 0;
    long currentTimeStamp;
    int happiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        myShark = new Shark("greatwhite"); //for testing purposes

        /*
        currentTimeStamp = System.currentTimeMillis()/10000;
        happiness = (int) (myShark.getHapp() - (currentTimeStamp - lastTimeStamp)); //may break
        lastTimeStamp = currentTimeStamp;

        myShark.setHapp(happiness);
*/
        final ImageView btnPet = (ImageView) findViewById(R.id.btnPet);
        final ImageView btnInventory = (ImageView) findViewById(R.id.btnInventory);
        final ImageView imgShark = (ImageView) findViewById(R.id.myShark);
        final TextView txtHappiness = (TextView) findViewById(R.id.txtHappiness);
        final TextView sharkName = (TextView) findViewById(R.id.sharkName);

        //txtHappiness.setText(myShark.getHapp());
        sharkName.setText("Sharkie"); //change input

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
                Intent inventoryIntent = new Intent(UserAreaActivity.this, InventoryActivity.class);
                UserAreaActivity.this.startActivity(inventoryIntent);
            }
        });


    }
}
